package net.ArcaneArtificer.arcanecore.block;

import net.ArcaneArtificer.arcanecore.ArcaneCore;
import net.ArcaneArtificer.arcanecore.block.custom.LapidaryBlock;
import net.ArcaneArtificer.arcanecore.item.Gems;
import net.ArcaneArtificer.arcanecore.item.ModCreativeModeTab;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class Workstation_Blocks {
    public static final DeferredRegister<Block> WORKSTATION_BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ArcaneCore.MOD_ID);

    public static final RegistryObject<Block> LAPIDARY = registerBlock("lapidary",
            () -> new LapidaryBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(4f).requiresCorrectToolForDrops()), ModCreativeModeTab.ARCANE_CORE);

    // Helper method for block registration
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = WORKSTATION_BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    // Helper method to generate blocks
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return Gems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        WORKSTATION_BLOCKS.register(eventBus);
    }
}
