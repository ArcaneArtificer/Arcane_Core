package net.ArcaneArtificer.improvedvillagers.block;

import net.ArcaneArtificer.improvedvillagers.ImprovedVillagers;
import net.ArcaneArtificer.improvedvillagers.item.Gems;
import net.ArcaneArtificer.improvedvillagers.item.ModCreativeModeTab;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class Gem_and_Ore_Blocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ImprovedVillagers.MOD_ID);

    // Block of Nether Beryl
    public static final RegistryObject<Block> NETHER_BERYL_BLOCK = registerBlock("nether_beryl_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(7f).requiresCorrectToolForDrops()), ModCreativeModeTab.IMPROVED_VILLAGERS);

    // Block of End Tanzanite
    public static final RegistryObject<Block> END_TANZANITE_BLOCK = registerBlock("end_tanzanite_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(9f).requiresCorrectToolForDrops()), ModCreativeModeTab.IMPROVED_VILLAGERS);

    // Reference for ore values:
    // public static final Block DEEPSLATE_DIAMOND_ORE = register("deepslate_diamond_ore", new OreBlock(BlockBehaviour.Properties.copy(DIAMOND_ORE).color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE), UniformInt.of(3, 7)));

    // Nether Beryl Ore - Nether
    public static final RegistryObject<Block> NETHER_BERYL_ORE_NETHER = registerBlock("nether_beryl_ore_nether",
            () -> new OreBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(5.0f, 4.0f).requiresCorrectToolForDrops().sound(SoundType.NETHER_ORE),
                    UniformInt.of(4, 9)),
            ModCreativeModeTab.IMPROVED_VILLAGERS);

    // Nether Beryl Ore - End
    public static final RegistryObject<Block> NETHER_BERYL_ORE_END = registerBlock("nether_beryl_ore_end",
            () -> new OreBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(5.0f, 2.5f).requiresCorrectToolForDrops().sound(SoundType.NETHER_ORE),
                    UniformInt.of(6, 10)),
            ModCreativeModeTab.IMPROVED_VILLAGERS);

    // End Tanzanite Ore
    public static final RegistryObject<Block> END_TANZANITE_ORE = registerBlock("end_tanzanite_ore",
            () -> new OreBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(6.0f, 3.0f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE),
                    UniformInt.of(7, 12)),
            ModCreativeModeTab.IMPROVED_VILLAGERS);

    // Helper method for block registration
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    // Helper method to generate blocks
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return Gems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
