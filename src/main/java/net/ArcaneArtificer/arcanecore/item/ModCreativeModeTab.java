package net.ArcaneArtificer.arcanecore.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab ARCANE_CORE = new CreativeModeTab("arcanecoretab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Gems.NETHER_BERYL.get());
        }
    };
}
