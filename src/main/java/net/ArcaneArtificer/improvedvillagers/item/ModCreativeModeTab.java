package net.ArcaneArtificer.improvedvillagers.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab IMPROVED_VILLAGERS = new CreativeModeTab("improvedvillagerstab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Gems.NETHER_BERYL.get());
        }
    };
}
