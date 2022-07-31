package net.ArcaneArtificer.arcanecore.world.feature;

import net.ArcaneArtificer.arcanecore.config.ArcaneCoreCommonConfigs;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModPlacedFeatures {
    public static final Holder<PlacedFeature> NETHER_BERYL_ORE_SMALL_PLACED = PlacementUtils.register("nether_beryl_ore_small_placed",
            ModConfiguredFeatures.NETHER_BERYL_ORE_SMALL, ModOrePlacement.rareOrePlacement(ArcaneCoreCommonConfigs.NETHER_BERYL_SMALL_VEINS_PER_CHUNK.get(),
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(0), VerticalAnchor.aboveBottom(80))));

    public static final Holder<PlacedFeature> NETHER_BERYL_ORE_MEDIUM_PLACED = PlacementUtils.register("nether_beryl_ore_medium_placed",
            ModConfiguredFeatures.NETHER_BERYL_ORE_MEDIUM, ModOrePlacement.rareOrePlacement(ArcaneCoreCommonConfigs.NETHER_BERYL_MEDIUM_VEINS_PER_CHUNK.get(),
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(0), VerticalAnchor.aboveBottom(80))));

    public static final Holder<PlacedFeature> NETHER_BERYL_ORE_LARGE_PLACED = PlacementUtils.register("nether_beryl_ore_large_placed",
            ModConfiguredFeatures.NETHER_BERYL_ORE_LARGE, ModOrePlacement.rareOrePlacement(ArcaneCoreCommonConfigs.NETHER_BERYL_LARGE_VEINS_PER_CHUNK.get(),
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.aboveBottom(80))));

    public static final Holder<PlacedFeature> NETHER_BERYL_ORE_END_PLACED = PlacementUtils.register("nether_beryl_ore_end_placed",
            ModConfiguredFeatures.NETHER_BERYL_ORE_END, ModOrePlacement.rareOrePlacement(ArcaneCoreCommonConfigs.NETHER_BERYL_END_VEINS_PER_CHUNK.get(),
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

    public static final Holder<PlacedFeature> END_TANZANITE_ORE_END_PLACED = PlacementUtils.register("end_tanzanite_ore_placed",
            ModConfiguredFeatures.END_TANZANITE_ORE_END, ModOrePlacement.rareOrePlacement(ArcaneCoreCommonConfigs.END_TANZANITE_VEINS_PER_CHUNK.get(),
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
}
