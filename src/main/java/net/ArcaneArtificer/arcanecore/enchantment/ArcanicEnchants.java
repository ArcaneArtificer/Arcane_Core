package net.ArcaneArtificer.arcanecore.enchantment;

import net.ArcaneArtificer.arcanecore.ArcaneCore;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ArcanicEnchants {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, ArcaneCore.MOD_ID);

    /*public static RegistryObject<Enchantment> SWINE_SWARM =
            ENCHANTMENTS.register("swine_swarm", () -> new SwineSwarm(Enchantment.Rarity.UNCOMMON,
                    EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));*/

    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}
