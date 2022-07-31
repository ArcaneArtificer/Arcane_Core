package net.ArcaneArtificer.arcanecore.item;

import net.ArcaneArtificer.arcanecore.ArcaneCore;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Gems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ArcaneCore.MOD_ID);

    public static final RegistryObject<Item> NETHER_BERYL = ITEMS.register("nether_beryl",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ARCANE_CORE)));

    public static final RegistryObject<Item> END_TANZANITE = ITEMS.register("end_tanzanite",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ARCANE_CORE)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
