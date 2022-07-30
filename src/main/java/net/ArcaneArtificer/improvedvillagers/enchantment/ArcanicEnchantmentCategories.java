package net.ArcaneArtificer.improvedvillagers.enchantment;

import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentCategory;


public class ArcanicEnchantmentCategories {
    public static final EnchantmentCategory HORSE_ARMOR = EnchantmentCategory.create("arcanic_enchants_horse_armor",
            item -> item instanceof HorseArmorItem);

    public static final EnchantmentCategory PICKAXE = EnchantmentCategory.create("arcanic_enchants_pickaxe",
            item -> item instanceof PickaxeItem);

    public static final EnchantmentCategory AXE = EnchantmentCategory.create("arcanic_enchants_axe",
            item -> item instanceof AxeItem);

    public static final EnchantmentCategory SHOVEL = EnchantmentCategory.create("arcanic_enchants_shovel",
            item -> item instanceof ShovelItem);

    public static final EnchantmentCategory HOE = EnchantmentCategory.create("arcanic_enchants_hoe",
            item -> item instanceof HoeItem);

    public static final EnchantmentCategory ARROWS = EnchantmentCategory.create("arcanic_enchants_arrows",
            item -> item instanceof ArrowItem);

    public static final EnchantmentCategory MELEE_WEAPONS = EnchantmentCategory.create("arcanic_enchants_melee_weapons",
            item -> item instanceof SwordItem || item instanceof AxeItem);

    public static final EnchantmentCategory SUMMONING_WEAPONS = EnchantmentCategory.create("arcanic_enchants_summoning_weapons",
            item -> item instanceof HoeItem);

    public static final EnchantmentCategory RANGED_WEAPONS = EnchantmentCategory.create("arcanic_enchants_ranged_weapons",
            item -> item instanceof BowItem || item instanceof CrossbowItem);

    public static final EnchantmentCategory PROJECTILE_WEAPONS = EnchantmentCategory.create("arcanic_enchants_projectile_weapons",
            item -> item instanceof BowItem || item instanceof CrossbowItem);

    public static final EnchantmentCategory ELYTRA = EnchantmentCategory.create("arcanic_enchants_elytra",
            item -> item instanceof ElytraItem);
}
