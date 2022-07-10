package net.ArcaneArtificer.improvedvillagers.block.entity;

import net.ArcaneArtificer.improvedvillagers.ImprovedVillagers;
import net.ArcaneArtificer.improvedvillagers.block.Workstation_Blocks;
import net.ArcaneArtificer.improvedvillagers.block.entity.custom.LapidaryEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
         DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, ImprovedVillagers.MOD_ID);

    public static final RegistryObject<BlockEntityType<LapidaryEntity>> LAPIDARY_ENTITY =
            BLOCK_ENTITIES.register("lapidary_entity", () ->
                    BlockEntityType.Builder.of(LapidaryEntity::new, Workstation_Blocks.LAPIDARY.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
