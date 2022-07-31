package net.ArcaneArtificer.arcanecore.world.feature;

import net.ArcaneArtificer.arcanecore.block.Gem_and_Ore_Blocks;
import net.ArcaneArtificer.arcanecore.config.ArcaneCoreCommonConfigs;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final List<OreConfiguration.TargetBlockState> NETHERGEN_NETHER_BERYL_ORES = List.of(
            OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES,
                    Gem_and_Ore_Blocks.NETHER_BERYL_ORE_NETHER.get().defaultBlockState())
    );

    public static final List<OreConfiguration.TargetBlockState> ENDGEN_END_TANZANITE_ORES = List.of(
            OreConfiguration.target(new BlockMatchTest(Blocks.END_STONE),
                    Gem_and_Ore_Blocks.NETHER_BERYL_ORE_END.get().defaultBlockState())
    );

    public static final List<OreConfiguration.TargetBlockState> ENDGEN_NETHER_BERYL_ORES = List.of(
            OreConfiguration.target(new BlockMatchTest(Blocks.END_STONE),
                    Gem_and_Ore_Blocks.END_TANZANITE_ORE.get().defaultBlockState())
    );

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> NETHER_BERYL_ORE_SMALL = FeatureUtils.register("nether_beryl_ore_small", Feature.ORE, new OreConfiguration(NETHERGEN_NETHER_BERYL_ORES, ArcaneCoreCommonConfigs.NETHER_BERYL_SMALL_VEIN_SIZE.get()));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> NETHER_BERYL_ORE_MEDIUM = FeatureUtils.register("nether_beryl_ore_medium", Feature.ORE, new OreConfiguration(NETHERGEN_NETHER_BERYL_ORES, ArcaneCoreCommonConfigs.NETHER_BERYL_MEDIUM_VEIN_SIZE.get()));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> NETHER_BERYL_ORE_LARGE = FeatureUtils.register("nether_beryl_ore_large", Feature.ORE, new OreConfiguration(NETHERGEN_NETHER_BERYL_ORES, ArcaneCoreCommonConfigs.NETHER_BERYL_LARGE_VEIN_SIZE.get()));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> END_TANZANITE_ORE_END = FeatureUtils.register("end_tanzanite_ore_end", Feature.ORE, new OreConfiguration(ENDGEN_END_TANZANITE_ORES, ArcaneCoreCommonConfigs.NETHER_BERYL_END_VEIN_SIZE.get()));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> NETHER_BERYL_ORE_END = FeatureUtils.register("nether_beryl_ore_end", Feature.ORE, new OreConfiguration(ENDGEN_NETHER_BERYL_ORES, ArcaneCoreCommonConfigs.END_TANZANITE_VEIN_SIZE.get()));
}
