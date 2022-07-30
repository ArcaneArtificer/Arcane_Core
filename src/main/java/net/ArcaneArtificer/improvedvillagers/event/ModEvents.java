package net.ArcaneArtificer.improvedvillagers.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.ArcaneArtificer.improvedvillagers.ImprovedVillagers;
import net.ArcaneArtificer.improvedvillagers.block.Gem_and_Ore_Blocks;
import net.ArcaneArtificer.improvedvillagers.config.ImprovedVillagersCommonConfigs;
import net.ArcaneArtificer.improvedvillagers.enchantment.ArcanicEnchant;
import net.ArcaneArtificer.improvedvillagers.item.Gems;
import net.ArcaneArtificer.improvedvillagers.villager.ModVillagers;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Mod.EventBusSubscriber(modid = ImprovedVillagers.MOD_ID)
public class ModEvents {
    private static final Map<VillagerProfession, Int2ObjectMap<VillagerTrades.ItemListing[]>> VANILLA_TRADES = new HashMap<>();
    private static final float CHEAP_TO_EXPENSIVE_THRESHOLD = 45f / 64f;
    private static final Integer[][] SCALED_QUANTITIES = new Integer[64][64];
    private static final float[] BIAS = { -1f, -0.7f, -0.4f, -0.15f, 0f, 0.1f, 0.18f, 0.25f, 0.32f, 0.4f};

    public static class tradeItems {
        public static ItemLike tradeItem;
        public static int cost;
        public static int variance;

        public tradeItems(int pCost, @Nonnull ItemLike cheapItem, ItemLike expensiveItem) {
            init(pCost, 9, cheapItem, expensiveItem, 0);
        }

        public tradeItems(int pCost, @Nonnull ItemLike cheapItem, ItemLike expensiveItem, int pVariance) {
            init(pCost, 9, cheapItem, expensiveItem, pVariance);
        }

        public tradeItems(int pCost, int divisor, @Nonnull ItemLike cheapItem, ItemLike expensiveItem) {
            init(pCost, divisor, cheapItem, expensiveItem, 0);
        }

        public tradeItems(int pCost, int divisor, @Nonnull ItemLike cheapItem, ItemLike expensiveItem, int pVariance) {
            init(pCost, divisor, cheapItem, expensiveItem, pVariance);
        }

        private void init(int pCost, int divisor, @Nonnull ItemLike cheapItem, ItemLike expensiveItem, int pVariance) {
            variance = pVariance;
            if (pCost <= (cheapItem.asItem().getMaxStackSize() * CHEAP_TO_EXPENSIVE_THRESHOLD)) {
                cost = pCost;
                tradeItem = cheapItem;
            } else if (expensiveItem == null) {
                cost = Math.min(pCost, cheapItem.asItem().getMaxStackSize());
                tradeItem = cheapItem;
            } else {
                cost = Math.min(pCost / divisor, expensiveItem.asItem().getMaxStackSize());
                tradeItem = expensiveItem;
            }
        }

        public ItemStack generateStack() {
            return new ItemStack(tradeItem,
                    Mth.clamp(cost + ThreadLocalRandom.current().nextInt(0, variance), 1,
                            tradeItem.asItem().getMaxStackSize()));
        }
    }

    static {
        // Generate list of all vanilla trades
        VillagerTrades.TRADES.forEach((key, value) -> {
            Int2ObjectMap<VillagerTrades.ItemListing[]> copy = new Int2ObjectOpenHashMap<>();
            value.int2ObjectEntrySet().forEach(ent -> copy.put(ent.getIntKey(), Arrays.copyOf(ent.getValue(), ent.getValue().length)));
            VANILLA_TRADES.put(key, copy);
        });

        // Generate lookup table for all costs
        for (int minDiffVal = 1; minDiffVal < 65; minDiffVal++) {
            for (int typDiffVal = 1; typDiffVal < 65; typDiffVal++) {
                SCALED_QUANTITIES[minDiffVal][typDiffVal] = scaleQuadratic(minDiffVal, typDiffVal);
            }
        }
    }
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        //
        Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
        VillagerProfession villager = event.getType();// Default behavior
        Enchantment randEnchant = getRandomEnchant();

        //Remove all vanilla trades from all professions
        if (ImprovedVillagersCommonConfigs.CLEAR_VANILLA_TRADES.get()) {
            Int2ObjectMap<VillagerTrades.ItemListing[]> prof_trades = VANILLA_TRADES.get(villager);
            prof_trades.forEach((key, value) -> {
                for (VillagerTrades.ItemListing curr_trade : value) {
                    trades.get(key).remove(curr_trade);
                }
            });
            // Replace all trade options for villagers
            if (VillagerProfession.ARMORER.equals(villager)) {

            } else if (VillagerProfession.BUTCHER.equals(villager)) {

            } else if (VillagerProfession.CARTOGRAPHER.equals(villager)) {

            } else if (VillagerProfession.CLERIC.equals(villager)) {

            } else if (VillagerProfession.FARMER.equals(villager)) {

            } else if (VillagerProfession.FISHERMAN.equals(villager)) {

            } else if (VillagerProfession.FLETCHER.equals(villager)) {

            } else if (VillagerProfession.LEATHERWORKER.equals(villager)) {

            } else if (VillagerProfession.LIBRARIAN.equals(villager)) {

            } else if (VillagerProfession.MASON.equals(villager)) {

            } else if (VillagerProfession.SHEPHERD.equals(villager)) {

            } else if (VillagerProfession.TOOLSMITH.equals(villager)) {

            } else if (VillagerProfession.WEAPONSMITH.equals(villager)) {

            }
        }
        // Custom Villager Trades
        if (event.getType() == ModVillagers.WIZARD.get()){
            // Villager level 1 trades
            trades.get(1).add((trader, rand) -> generateTrade(
                    new tradeItems(10, Items.EMERALD, Items.EMERALD_BLOCK, 5),
                    new tradeItems(10, Items.COAL_BLOCK, null, 5)
            ));

            trades.get(1).add((trader, rand) -> generateTrade(
                    new tradeItems(SCALED_QUANTITIES[15][20], Items.EMERALD, Items.EMERALD_BLOCK, 8),
                    new tradeItems(1, Items.BOOK, null),
                    new tradeItems(SCALED_QUANTITIES[5][3], Items.COAL_BLOCK, null, 3)
            ));

            trades.get(1).add((trader, rand) -> generateTrade(
                    new tradeItems(10, 3, Items.EMERALD, Gems.NETHER_BERYL.get(), 2),
                    new tradeItems(10, 4, Items.COAL_BLOCK, Gems.NETHER_BERYL.get()),
                    10, 8, 0.02F
            ));

            // Villager level 2 trades
            trades.get(2).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.GOLD_BLOCK, ThreadLocalRandom.current().nextInt(1, 4)), new ItemStack(Items.IRON_CHESTPLATE, 1),
                    10, 8, 0.02F));
            trades.get(2).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.DIAMOND_BLOCK, ThreadLocalRandom.current().nextInt(1, 4)), new ItemStack(Items.IRON_LEGGINGS, 1),
                    10, 8, 0.02F));

            // Villager level 3 trades
            trades.get(3).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.LAPIS_BLOCK, ThreadLocalRandom.current().nextInt(1, 4)), new ItemStack(Items.COAL_BLOCK, 10),
                    10, 8, 0.02F));
            trades.get(3).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.LAPIS_LAZULI, 1), new ItemStack(Items.COAL, ThreadLocalRandom.current().nextInt(5, 20)),
                    10, 8, 0.02F));

            // Villager level 4 trades
            trades.get(4).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Gem_and_Ore_Blocks.NETHER_BERYL_BLOCK.get(), ThreadLocalRandom.current().nextInt(1, 4)), new ItemStack(Items.COAL_BLOCK, 10),
                    10, 8, 0.02F));
            trades.get(4).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Gem_and_Ore_Blocks.NETHER_BERYL_BLOCK.get(), 1), new ItemStack(Items.COAL, ThreadLocalRandom.current().nextInt(5, 20)),
                    10, 8, 0.02F));

            // Villager level 5 trades
            trades.get(5).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Gem_and_Ore_Blocks.END_TANZANITE_BLOCK.get(), ThreadLocalRandom.current().nextInt(1, 4)), new ItemStack(Items.COAL_BLOCK, 10),
                    10, 8, 0.02F));
            trades.get(5).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Gem_and_Ore_Blocks.END_TANZANITE_BLOCK.get(), 1), new ItemStack(Items.COAL, ThreadLocalRandom.current().nextInt(5, 20)),
                    10, 8, 0.02F));
        } else if (event.getType() == ModVillagers.JEWELER.get()){
            // Villager level 1 trades
            trades.get(1).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, ThreadLocalRandom.current().nextInt(5, 10)), new ItemStack(Items.COAL_BLOCK, 10),
                    10, 2, 0.02F));
            trades.get(1).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.COAL, ThreadLocalRandom.current().nextInt(20, 40)), new ItemStack(Items.LAPIS_BLOCK, ThreadLocalRandom.current().nextInt(2, 5)),
                    10, 4, 0.02F));

            // Villager level 2 trades
            trades.get(2).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.GOLD_BLOCK, ThreadLocalRandom.current().nextInt(1, 4)), new ItemStack(Items.IRON_CHESTPLATE, 1),
                    10, 8, 0.02F));
            trades.get(2).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.DIAMOND_BLOCK, ThreadLocalRandom.current().nextInt(1, 4)), new ItemStack(Items.IRON_LEGGINGS, 1),
                    10, 8, 0.02F));

            // Villager level 3 trades
            trades.get(3).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.LAPIS_BLOCK, ThreadLocalRandom.current().nextInt(1, 4)), new ItemStack(Items.COAL_BLOCK, 10),
                    10, 8, 0.02F));
            trades.get(3).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.LAPIS_LAZULI, 1), new ItemStack(Items.COAL, ThreadLocalRandom.current().nextInt(5, 20)),
                    10, 8, 0.02F));

            // Villager level 4 trades
            trades.get(4).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Gem_and_Ore_Blocks.NETHER_BERYL_BLOCK.get(), ThreadLocalRandom.current().nextInt(1, 4)), new ItemStack(Items.COAL_BLOCK, 10),
                    10, 8, 0.02F));
            trades.get(4).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Gem_and_Ore_Blocks.NETHER_BERYL_BLOCK.get(), 1), new ItemStack(Items.COAL, ThreadLocalRandom.current().nextInt(5, 20)),
                    10, 8, 0.02F));

            // Villager level 5 trades
            trades.get(5).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Gem_and_Ore_Blocks.END_TANZANITE_BLOCK.get(), ThreadLocalRandom.current().nextInt(1, 4)), new ItemStack(Items.COAL_BLOCK, 10),
                    10, 8, 0.02F));
            trades.get(5).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Gem_and_Ore_Blocks.END_TANZANITE_BLOCK.get(), 1), new ItemStack(Items.COAL, ThreadLocalRandom.current().nextInt(5, 20)),
                    10, 8, 0.02F));
        }
    }

    private static MerchantOffer generateTrade(tradeItems itemA, tradeItems result) {
        return generateTrade(itemA, null, result, 12, 8, 0.05f);
    }

    private static MerchantOffer generateTrade(tradeItems itemA, tradeItems result, int maxUses, int xp, float priceMultiplier) {
        return generateTrade(itemA, null, result, maxUses, xp, priceMultiplier);
    }

    private static MerchantOffer generateTrade(tradeItems itemA, tradeItems itemB, tradeItems result) {
        return generateTrade(itemA, itemB, result, 12, 8, 0.05f);
    }

    private static MerchantOffer generateTrade(tradeItems itemA, tradeItems itemB, tradeItems itemR, int maxUses, int xp, float priceMultiplier) {
        if (itemB == null) {
            return new MerchantOffer(itemA.generateStack(), ItemStack.EMPTY, itemR.generateStack(),
                    maxUses, xp, scalePriceMultiplier(priceMultiplier));
        } else {
            return new MerchantOffer(itemA.generateStack(), itemB.generateStack(), itemR.generateStack(),
                    maxUses, xp, scalePriceMultiplier(priceMultiplier));
        }
    }

    // Helper method for generating the 2D cost scaling array.
    private static int scaleQuadratic(int minDiffVal, int typDiffVal) {
        int difficulty = ImprovedVillagersCommonConfigs.TRADER_DIFFICULTY.get();
        int typDiff = 5;
        /*
         Curve fit adjCost = A*difficulty^2 + b*difficulty + c
         Assume slope = 0 @ difficulty 1 (min cost)
         Solving gives adjCost = A*difficulty^2 - 2A*difficulty + minCost + A
         to get A, (typDiffVal - minDiffVal) / (typDiff^2 - 2*typDiff + 1)
        */
        double A = ((float)(typDiffVal - minDiffVal)) / ((float)(typDiff^2 - 2 * typDiff + 1));
        return (int) Math.round(A * (difficulty^2) - 2 * A * difficulty + minDiffVal + A);
    }

    private static float scalePriceMultiplier(float priceMultiplier) {
        // multiplier * -100 corresponds to the reduction in price of the first input item and HotV gives an extra 55% off
        float minMultVal = 0.50f;
        float typMultVal = 1.00f;
        int difficulty = ImprovedVillagersCommonConfigs.TRADER_DIFFICULTY.get();
        int typDiff = 5;
        /*
         Curve fit y = Ax^2 + bx + c
         Assume slope = 0 @ difficulty 1 (min cost)
         Solving gives y = Ax^2 - 2Ax + minCost + A
         to get A, (typDiffVal - minDiffVal) / (typDiff^2 - 2*typDiff + 1)
        */
        float A = ((float)(typMultVal - minMultVal)) / ((float)(typDiff^2 - 2 * typDiff + 1));
        return (float) priceMultiplier / Math.round(100.0f * A * (difficulty^2) - 2 * A * difficulty + minMultVal + A) / 100.0f;
    }

    private static int scaleEnchantLevel(int maxLevel) {
        if (ImprovedVillagersCommonConfigs.MAX_LEVEL_ENCHANTMENTS.get()) {
            int difficulty = ImprovedVillagersCommonConfigs.TRADER_DIFFICULTY.get();
            float bias = BIAS[difficulty];
            float input = ThreadLocalRandom.current().nextFloat(0, 1);
            float adjustedLevel = maxLevel * (input * bias) / (input * (bias - 1) + 1);
            return Math.round(adjustedLevel);
        }else {
            return 1;
        }
    }

    private static Enchantment getRandomEnchant() {
        for(Enchantment enchantment : ForgeRegistries.ENCHANTMENTS.getValues()) {
            if (enchantment instanceof ArcanicEnchant) {
                int rarity = ((ArcanicEnchant) enchantment).getCustomRarity().getRarity();
            } else {
                int rarity = enchantment.getRarity().getWeight();
            }
            int maxLevel = enchantment.getMaxLevel();
        }
        return Enchantments.UNBREAKING;
    }


}

