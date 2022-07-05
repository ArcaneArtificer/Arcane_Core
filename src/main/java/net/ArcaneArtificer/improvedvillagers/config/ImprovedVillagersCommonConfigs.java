package net.ArcaneArtificer.improvedvillagers.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ImprovedVillagersCommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> CLEAR_VANILLA_TRADES;
    public static final ForgeConfigSpec.ConfigValue<Integer> NETHER_BERYL_SMALL_VEIN_SIZE;
    public static final ForgeConfigSpec.ConfigValue<Integer> NETHER_BERYL_SMALL_VEINS_PER_CHUNK;
    public static final ForgeConfigSpec.ConfigValue<Integer> NETHER_BERYL_MEDIUM_VEIN_SIZE;
    public static final ForgeConfigSpec.ConfigValue<Integer> NETHER_BERYL_MEDIUM_VEINS_PER_CHUNK;
    public static final ForgeConfigSpec.ConfigValue<Integer> NETHER_BERYL_LARGE_VEIN_SIZE;
    public static final ForgeConfigSpec.ConfigValue<Integer> NETHER_BERYL_LARGE_VEINS_PER_CHUNK;
    public static final ForgeConfigSpec.ConfigValue<Integer> NETHER_BERYL_END_VEIN_SIZE;
    public static final ForgeConfigSpec.ConfigValue<Integer> NETHER_BERYL_END_VEINS_PER_CHUNK;
    public static final ForgeConfigSpec.ConfigValue<Integer> END_TANZANITE_VEIN_SIZE;
    public static final ForgeConfigSpec.ConfigValue<Integer> END_TANZANITE_VEINS_PER_CHUNK;

    static {
        BUILDER.push("Configs");

        CLEAR_VANILLA_TRADES = BUILDER.comment("Removes vanilla trades from traders.").define("Vanilla Trades", Boolean.FALSE);
        NETHER_BERYL_SMALL_VEIN_SIZE = BUILDER.comment("Nether Beryl Small Vein Size").defineInRange("Small Nether Beryl Vein Size", 4, 2, 10);
        NETHER_BERYL_SMALL_VEINS_PER_CHUNK = BUILDER.comment("Nether Beryl Small Veins Per Chunk").defineInRange("Small Nether Beryl Veins Per Chunk", 6, 2, 12);
        NETHER_BERYL_MEDIUM_VEIN_SIZE = BUILDER.comment("Nether Beryl Medium Vein Size").defineInRange("Medium Nether Beryl Vein Size", 6, 2, 15);
        NETHER_BERYL_MEDIUM_VEINS_PER_CHUNK = BUILDER.comment("Nether Beryl Medium Veins Per Chunk").defineInRange("Medium Nether Beryl Veins Per Chunk", 5, 2, 12);
        NETHER_BERYL_LARGE_VEIN_SIZE = BUILDER.comment("Nether Beryl Large Vein Size").defineInRange("Large Nether Beryl Vein Size", 8, 2, 20);
        NETHER_BERYL_LARGE_VEINS_PER_CHUNK = BUILDER.comment("Nether Beryl Large Veins Per Chunk").defineInRange("Large Nether Beryl Veins Per Chunk", 4, 2, 12);
        NETHER_BERYL_END_VEIN_SIZE = BUILDER.comment("Nether Beryl End Vein Size").defineInRange("End Nether Beryl Vein Size", 5, 2, 10);
        NETHER_BERYL_END_VEINS_PER_CHUNK = BUILDER.comment("Nether Beryl End Veins Per Chunk").defineInRange("End Nether Beryl Veins Per Chunk", 6, 2, 12);
        END_TANZANITE_VEIN_SIZE = BUILDER.comment("End Tanzanite Vein Size").defineInRange("End Nether Beryl Vein Size", 8, 2, 20);
        END_TANZANITE_VEINS_PER_CHUNK = BUILDER.comment("End Tanzanite Veins Per Chunk").defineInRange("End Nether Beryl Veins Per Chunk", 8, 2, 12);

        BUILDER.pop();
        SPEC = BUILDER.build();

    }
}
