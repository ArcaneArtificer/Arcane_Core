package net.ArcaneArtificer.improvedvillagers.world.gen;

import net.ArcaneArtificer.improvedvillagers.world.feature.ModPlacedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;

public class ModOreGeneration {
    public static void generateOres(final BiomeLoadingEvent event) {
        List<Holder<PlacedFeature>> base =
                event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);
        if(event.getCategory() == Biome.BiomeCategory.NETHER) {
            base.add(ModPlacedFeatures.NETHER_BERYL_ORE_SMALL_PLACED);
            base.add(ModPlacedFeatures.NETHER_BERYL_ORE_MEDIUM_PLACED);
            base.add(ModPlacedFeatures.NETHER_BERYL_ORE_LARGE_PLACED);
        }
        if(event.getCategory() == Biome.BiomeCategory.THEEND) {
            base.add(ModPlacedFeatures.NETHER_BERYL_ORE_END_PLACED);
            base.add(ModPlacedFeatures.END_TANZANITE_ORE_END_PLACED);
        }
    }

}
