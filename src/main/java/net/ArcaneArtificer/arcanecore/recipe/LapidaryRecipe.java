package net.ArcaneArtificer.arcanecore.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.ArcaneArtificer.arcanecore.ArcaneCore;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class LapidaryRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;

    public LapidaryRecipe (ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        boolean check_gem_slot = recipeItems.get(0).test(pContainer.getItem(0));
        boolean check_cutter_slot = recipeItems.get(1).test(pContainer.getItem(1));
        boolean check_grit_slot = recipeItems.get(2).test(pContainer.getItem(2));
        return check_gem_slot && check_cutter_slot && check_grit_slot;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<LapidaryRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "lapidary";
    }

    public static class Serializer implements RecipeSerializer<LapidaryRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(ArcaneCore.MOD_ID, "lapidary");

        @Override
        public LapidaryRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(3, Ingredient.EMPTY);

            for (int n = 0; n < inputs.size(); n++) {
                inputs.set(n, Ingredient.fromJson(ingredients.get(n)));
            }

            return new LapidaryRecipe(pRecipeId, output, inputs);
        }

        @Nullable
        @Override
        public LapidaryRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);

            // This line is different from tutorial if errors
            inputs.replaceAll(ignored -> Ingredient.fromNetwork(pBuffer));

            ItemStack output = pBuffer.readItem();
            return new LapidaryRecipe(pRecipeId, output, inputs);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, LapidaryRecipe pRecipe) {
            pBuffer.writeInt(pRecipe.getIngredients().size());
            for (Ingredient ing : pRecipe.getIngredients()) {
                ing.toNetwork(pBuffer);
            }
            pBuffer.writeItemStack(pRecipe.getResultItem(), false);
        }

        @Override
        public RecipeSerializer<?> setRegistryName(ResourceLocation name) {
            return INSTANCE;
        }

        @Nullable
        @Override
        public ResourceLocation getRegistryName() {
            return ID;
        }

        @Override
        public Class<RecipeSerializer<?>> getRegistryType() {
            return Serializer.castClass(RecipeSerializer.class);
        }

        //Need this wrapper for generics
        private static <G> Class<G> castClass(Class<?> cls) {
            return (Class<G>) cls;
        }
    }
}
