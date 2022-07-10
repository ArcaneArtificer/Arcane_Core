package net.ArcaneArtificer.improvedvillagers;

import com.mojang.logging.LogUtils;
import net.ArcaneArtificer.improvedvillagers.block.Gem_and_Ore_Blocks;
import net.ArcaneArtificer.improvedvillagers.block.Workstation_Blocks;
import net.ArcaneArtificer.improvedvillagers.block.entity.ModBlockEntities;
import net.ArcaneArtificer.improvedvillagers.config.ImprovedVillagersClientConfigs;
import net.ArcaneArtificer.improvedvillagers.config.ImprovedVillagersCommonConfigs;
import net.ArcaneArtificer.improvedvillagers.item.Gems;
import net.ArcaneArtificer.improvedvillagers.recipe.ModRecipes;
import net.ArcaneArtificer.improvedvillagers.screen.LapidaryScreen;
import net.ArcaneArtificer.improvedvillagers.screen.ModMenuTypes;
import net.ArcaneArtificer.improvedvillagers.villager.ModVillagers;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ImprovedVillagers.MOD_ID)
public class ImprovedVillagers
{
    public static final String MOD_ID = "improvedvillagers";

    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public ImprovedVillagers()
    {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        Gems.register(eventBus);
        Gem_and_Ore_Blocks.register(eventBus);
        Workstation_Blocks.register(eventBus);

        ModVillagers.register(eventBus);
        ModBlockEntities.register(eventBus);
        ModMenuTypes.register(eventBus);
        ModRecipes.register(eventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ImprovedVillagersClientConfigs.SPEC, "improvedvillagers-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ImprovedVillagersCommonConfigs.SPEC, "improvedvillagers-common.toml");

        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(Workstation_Blocks.LAPIDARY.get(), RenderType.translucent());

        MenuScreens.register(ModMenuTypes.LAPIDARY_MENU.get(), LapidaryScreen::new);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork( () -> {
            ModVillagers.registerPOIs();
        });
    }

}
