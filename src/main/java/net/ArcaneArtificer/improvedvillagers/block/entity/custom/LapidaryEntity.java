package net.ArcaneArtificer.improvedvillagers.block.entity.custom;

import net.ArcaneArtificer.improvedvillagers.block.entity.ModBlockEntities;
import net.ArcaneArtificer.improvedvillagers.recipe.LapidaryRecipe;
import net.ArcaneArtificer.improvedvillagers.screen.LapidaryMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Optional;

public class LapidaryEntity extends BlockEntity implements MenuProvider {
    // size refers to the number of slots in the custom station.
    private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 72;

    public LapidaryEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.LAPIDARY_ENTITY.get(), pWorldPosition, pBlockState);

        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> LapidaryEntity.this.progress;
                    case 1 -> LapidaryEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> LapidaryEntity.this.progress = pValue;
                    case 1 -> LapidaryEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("Lapidary");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new LapidaryMenu(pContainerId, pInventory, this, this.data);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    public void saveAdditional(@Nonnull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("lapidary.progress", progress);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("lapidary.progress");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, LapidaryEntity pEntity) {
        if(hasRecipe(pEntity)) {
            if (pEntity.progress <= pEntity.maxProgress) {
                pEntity.progress++;
            }
            setChanged(pLevel, pPos, pState);
            if (pEntity.progress > pEntity.maxProgress) {
                craftItem(pEntity);
            }
        } else {
            pEntity.resetProgress();
            setChanged(pLevel, pPos, pState);
        }
    }

    public static void craftItem(LapidaryEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int n = 0; n < entity.itemHandler.getSlots(); n++) {
            inventory.setItem(n, entity.itemHandler.getStackInSlot(n));
        }

        Optional<LapidaryRecipe> match = level.getRecipeManager().getRecipeFor(
                LapidaryRecipe.Type.INSTANCE, inventory, level);

        if (match.isPresent() && (outputPossible(entity, match))) {
            entity.itemHandler.extractItem(0, 1, false);
            entity.itemHandler.extractItem(1, 1, false);
            entity.itemHandler.extractItem(2, 1, false);

            entity.itemHandler.setStackInSlot(3, new ItemStack(match.get().getResultItem().getItem(),
                    entity.itemHandler.getStackInSlot(3).getCount() + match.get().getResultItem().getCount()));

            entity.resetProgress();
        }
    }

    public static boolean hasRecipe(LapidaryEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int n = 0; n < entity.itemHandler.getSlots(); n++) {
            inventory.setItem(n, entity.itemHandler.getStackInSlot(n));
        }

        Optional<LapidaryRecipe> match = level.getRecipeManager().getRecipeFor(
                LapidaryRecipe.Type.INSTANCE, inventory, level);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory) &&
                canInsertItemIntoOutputSlot(inventory, match.get().getResultItem());

    }

    private static boolean outputPossible(LapidaryEntity entity, Optional<LapidaryRecipe> match) {
        boolean stack_check;
        if (match.isPresent()) {
            stack_check = (entity.itemHandler.getStackInSlot(3).getCount() + match.get().getResultItem().getCount()) < entity.itemHandler.getStackInSlot(3).getMaxStackSize();
        } else {
            stack_check = entity.itemHandler.getStackInSlot(3).getCount() + 1 < entity.itemHandler.getStackInSlot(3).getMaxStackSize();
        }
        boolean output_empty = entity.itemHandler.getStackInSlot(3).isEmpty();
        return stack_check || output_empty;
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
        return inventory.getItem(3).isEmpty() || inventory.getItem(3).getItem() == output.getItem();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(3).getMaxStackSize() > inventory.getItem(3).getCount();
    }
}
