package net.ArcaneArtificer.improvedvillagers.util;


import net.ArcaneArtificer.improvedvillagers.ImprovedVillagers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(ImprovedVillagers.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name){
            return BlockTags.create(new ResourceLocation("forge", name));
        }

    }

    public static class Items {
        public static final TagKey<Item> NETHER_BERYL_GEM = forgeTag("gems/nether_beryl");
        public static final TagKey<Item> END_TANZANITE_GEM = forgeTag("gems/end_tanzanite");

        private static TagKey<Item> tag(String name){
            return ItemTags.create(new ResourceLocation(ImprovedVillagers.MOD_ID, name));
        }

        private static TagKey<Item> forgeTag(String name){
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}
