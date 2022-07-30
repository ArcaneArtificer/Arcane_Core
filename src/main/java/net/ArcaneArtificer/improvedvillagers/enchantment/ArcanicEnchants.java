package net.ArcaneArtificer.improvedvillagers.enchantment;

import net.ArcaneArtificer.improvedvillagers.ImprovedVillagers;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ArcanicEnchants {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, ImprovedVillagers.MOD_ID);

    /*public static RegistryObject<Enchantment> SWINE_SWARM =
            ENCHANTMENTS.register("swine_swarm", () -> new SwineSwarm(Enchantment.Rarity.UNCOMMON,
                    EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));*/

    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}
