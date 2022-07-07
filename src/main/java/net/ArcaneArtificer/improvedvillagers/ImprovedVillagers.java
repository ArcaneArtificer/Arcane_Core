package net.ArcaneArtificer.improvedvillagers;

import com.mojang.logging.LogUtils;
import net.ArcaneArtificer.improvedvillagers.block.Gem_and_Ore_Blocks;
import net.ArcaneArtificer.improvedvillagers.item.Gems;
import net.ArcaneArtificer.improvedvillagers.villager.ModVillagers;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
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

        ModVillagers.register(eventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork( () -> {
            ModVillagers.registerPOIs();
        });
    }

}
