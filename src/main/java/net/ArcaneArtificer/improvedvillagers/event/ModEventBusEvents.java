package net.ArcaneArtificer.improvedvillagers.event;

import net.ArcaneArtificer.improvedvillagers.ImprovedVillagers;
import net.ArcaneArtificer.improvedvillagers.event.loot.WorkstationFromPiglinBarteringAdditionModifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = ImprovedVillagers.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
        event.getRegistry().registerAll(
                new WorkstationFromPiglinBarteringAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(ImprovedVillagers.MOD_ID, "workstation_from_piglin"))
        );
    }
}
