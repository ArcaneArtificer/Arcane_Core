package net.ArcaneArtificer.improvedvillagers.recipe;

import net.ArcaneArtificer.improvedvillagers.ImprovedVillagers;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
           DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ImprovedVillagers.MOD_ID);

    public static final RegistryObject<RecipeSerializer<LapidaryRecipe>> LAPIDARY_SERIALIZER =
            SERIALIZERS.register("lapidary", () -> LapidaryRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
