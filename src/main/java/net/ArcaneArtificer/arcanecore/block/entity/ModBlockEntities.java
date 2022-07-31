package net.ArcaneArtificer.arcanecore.block.entity;

import net.ArcaneArtificer.arcanecore.ArcaneCore;
import net.ArcaneArtificer.arcanecore.block.Workstation_Blocks;
import net.ArcaneArtificer.arcanecore.block.entity.custom.LapidaryEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
         DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, ArcaneCore.MOD_ID);

    public static final RegistryObject<BlockEntityType<LapidaryEntity>> LAPIDARY_ENTITY =
            BLOCK_ENTITIES.register("lapidary_entity", () ->
                    BlockEntityType.Builder.of(LapidaryEntity::new, Workstation_Blocks.LAPIDARY.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
