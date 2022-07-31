package net.ArcaneArtificer.arcanecore.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ArcaneCoreClientConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    static {
        BUILDER.push("Configs for Mod Turturial");
        // Define configs (same as common configs)
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
