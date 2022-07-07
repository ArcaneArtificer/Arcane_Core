package net.ArcaneArtificer.improvedvillagers.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.ArcaneArtificer.improvedvillagers.ImprovedVillagers;
import net.ArcaneArtificer.improvedvillagers.block.Gem_and_Ore_Blocks;
import net.ArcaneArtificer.improvedvillagers.config.ImprovedVillagersCommonConfigs;
import net.ArcaneArtificer.improvedvillagers.villager.ModVillagers;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Mod.EventBusSubscriber(modid = ImprovedVillagers.MOD_ID)
public class ModEvents {
    private static final Map<VillagerProfession, Int2ObjectMap<VillagerTrades.ItemListing[]>> VANILLA_TRADES = new HashMap<>();

    static {
        VillagerTrades.TRADES.forEach((key, value) -> {
            Int2ObjectMap<VillagerTrades.ItemListing[]> copy = new Int2ObjectOpenHashMap<>();
            value.int2ObjectEntrySet().forEach(ent -> copy.put(ent.getIntKey(), Arrays.copyOf(ent.getValue(), ent.getValue().length)));
            VANILLA_TRADES.put(key, copy);
        });
    }
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        //
        Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
        VillagerProfession villager = event.getType();// Default behavior

        //Remove all vanilla trades from all professions
        if (ImprovedVillagersCommonConfigs.CLEAR_VANILLA_TRADES.get()) {
            Int2ObjectMap<VillagerTrades.ItemListing[]> prof_trades = VANILLA_TRADES.get(villager);
            prof_trades.forEach((key, value) -> {
                for (VillagerTrades.ItemListing curr_trade : value) {
                    trades.get(key).remove(curr_trade);
                }
            });
        }

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
        // Custom Villager Trades
        if (event.getType() == ModVillagers.WIZARD.get()){
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
}
