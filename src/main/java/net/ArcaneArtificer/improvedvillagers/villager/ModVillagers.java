package net.ArcaneArtificer.improvedvillagers.villager;

import com.google.common.collect.ImmutableSet;
import net.ArcaneArtificer.improvedvillagers.ImprovedVillagers;
import net.ArcaneArtificer.improvedvillagers.block.Workstation_Blocks;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.InvocationTargetException;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES
            = DeferredRegister.create(ForgeRegistries.POI_TYPES, ImprovedVillagers.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS
            = DeferredRegister.create(ForgeRegistries.PROFESSIONS, ImprovedVillagers.MOD_ID);

    public static final RegistryObject<PoiType> ENCHANTING_TABLE_POI = POI_TYPES.register("enchanting_table_poi",
            () -> new PoiType("enchanting_table_poi",
                    PoiType.getBlockStates(Blocks.ENCHANTING_TABLE),
                    1, 1)); // Number of villagers, search range (default 1 except bells, which is 6)

    public static final RegistryObject<PoiType> LAPIDARY_POI = POI_TYPES.register("lapidary_poi",
            () -> new PoiType("lapidary_poi",
                    PoiType.getBlockStates(Workstation_Blocks.LAPIDARY.get()),
                    1, 1));

    public static final RegistryObject<VillagerProfession> WIZARD =
            VILLAGER_PROFESSIONS.register("wizard",
                    () -> new VillagerProfession("wizard",
                            ENCHANTING_TABLE_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                            SoundEvents.VILLAGER_WORK_LIBRARIAN));

    public static final RegistryObject<VillagerProfession> JEWELER =
            VILLAGER_PROFESSIONS.register("jeweler",
                    () -> new VillagerProfession("jeweler",
                            LAPIDARY_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                            SoundEvents.VILLAGER_WORK_ARMORER));

    public static void registerPOIs() {
        try {
            ObfuscationReflectionHelper.findMethod(PoiType.class,
                    "registerBlockStates", PoiType.class).invoke(null, ENCHANTING_TABLE_POI.get());
        } catch (InvocationTargetException | IllegalAccessException exception) {
            exception.printStackTrace();
        }

        try {
            ObfuscationReflectionHelper.findMethod(PoiType.class,
                    "registerBlockStates", PoiType.class).invoke(null, LAPIDARY_POI.get());
        } catch (InvocationTargetException | IllegalAccessException exception) {
            exception.printStackTrace();
        }
    }

    public static void register (IEventBus eventBus){
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}
