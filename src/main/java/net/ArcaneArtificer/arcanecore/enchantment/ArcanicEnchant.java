package net.ArcaneArtificer.arcanecore.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ArcanicEnchant extends Enchantment {
    /*
    TODO Need to override the BowItem, CrossbowItem, and TridentItem classes for enchantment functionality
    TODO Weapons that create projectiles will need to have methods to implement functionality outside the respective item class
     */
    private final CustomRarity customRarity;
    private final Boolean treasure;
    private final Boolean curse;

    public ArcanicEnchant(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot[] pApplicableSlots) {
        this(pRarity, pCategory, pApplicableSlots, CustomRarity.COMMON, false);
    }

    public ArcanicEnchant(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot[] pApplicableSlots, CustomRarity pCustomRarity) {
        this(pRarity, pCategory, pApplicableSlots, pCustomRarity, false);
    }

    public ArcanicEnchant(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot[] pApplicableSlots, CustomRarity pCustomRarity,
                          boolean pTreasure) {
        this(pRarity, pCategory, pApplicableSlots, pCustomRarity, false, false);
    }

    public ArcanicEnchant(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot[] pApplicableSlots, CustomRarity pCustomRarity,
                          boolean pTreasure, boolean isCurse) {
        super(pRarity, pCategory, pApplicableSlots);
        customRarity = pCustomRarity;
        treasure = pTreasure;
        curse = isCurse;
    }

    public ArcanicEnchant.CustomRarity getCustomRarity() {return this.customRarity;}

    @Override
    public boolean isTreasureOnly() {return this.treasure;}

    @Override
    public boolean isCurse() {return this.curse;}

    @Override
    public boolean isTradeable() {return this.customRarity.getRarity() < 7;}

    @Override
    public boolean isDiscoverable() {return this.customRarity.getRarity() < 7;}

    @Override
    public boolean isAllowedOnBooks() {return this.customRarity.getRarity() < 4;}




    public static enum CustomRarity {
        COMMON(1),
        UNCOMMON(2),
        RARE(3),
        EPIC(4),
        LEGENDARY(5),
        MYTHICAL(6),
        ARCANIC(7);

        private final int rarity;

        private CustomRarity(int rarity) {
            this.rarity = rarity;
        }

        /**
         * Retrieves the rarity.
         */
        public int getRarity() {
            return this.rarity;
        }
    }
}
