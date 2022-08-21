package net.ArcaneArtificer.arcanecore.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.checkerframework.checker.units.qual.A;

import java.util.*;

public class ArcanicEnchant extends Enchantment {
    private final CustomRarity customRarity;
    private final Boolean treasure;
    private final Boolean curse;

    private final Map<Integer, ArrayList<VillagerProfession>> TRADER_LEVEL_TO_PROFESSION = new HashMap<>();

    public ArcanicEnchant(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        this(CustomRarity.COMMON, false, false, pRarity, pCategory, pApplicableSlots);
    }

    public ArcanicEnchant(CustomRarity pCustomRarity, Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        this(pCustomRarity, false, false, pRarity, pCategory, pApplicableSlots);
    }

    public ArcanicEnchant(CustomRarity pCustomRarity, boolean isTreasure, Rarity pRarity, EnchantmentCategory pCategory,
                          EquipmentSlot... pApplicableSlots) {
        this(pCustomRarity, isTreasure, false, pRarity, pCategory, pApplicableSlots);
    }

    public ArcanicEnchant(CustomRarity pCustomRarity, boolean isTreasure, boolean isCurse, Rarity pRarity, EnchantmentCategory pCategory,
                          EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
        customRarity = pCustomRarity;
        treasure = isTreasure;
        curse = isCurse;
        TRADER_LEVEL_TO_PROFESSION.put(1, new ArrayList<>(List.of(VillagerProfession.LIBRARIAN)));
        TRADER_LEVEL_TO_PROFESSION.put(2, new ArrayList<>(List.of(VillagerProfession.LIBRARIAN)));
        TRADER_LEVEL_TO_PROFESSION.put(3, new ArrayList<>(List.of(VillagerProfession.LIBRARIAN)));
        TRADER_LEVEL_TO_PROFESSION.put(4, new ArrayList<>(List.of(VillagerProfession.LIBRARIAN)));
        TRADER_LEVEL_TO_PROFESSION.put(5, new ArrayList<>(List.of(VillagerProfession.LIBRARIAN)));
    }

    public ArcanicEnchant(Map<Integer, ArrayList<VillagerProfession>> pTrade_Level_to_Profession, CustomRarity pCustomRarity, boolean isTreasure,
                          boolean isCurse, Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
        customRarity = pCustomRarity;
        treasure = isTreasure;
        curse = isCurse;
        TRADER_LEVEL_TO_PROFESSION.putAll(pTrade_Level_to_Profession);
    }

    public ArcanicEnchant.CustomRarity getCustomRarity() {return this.customRarity;}

    public Map<Integer, ArrayList<VillagerProfession>> getProfessionTrades() {return this.TRADER_LEVEL_TO_PROFESSION;}

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
