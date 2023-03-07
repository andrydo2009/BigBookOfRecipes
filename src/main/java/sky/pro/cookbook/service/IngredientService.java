package sky.pro.cookbook.service;


import sky.pro.cookbook.model.Ingredient;

import java.util.Map;

public interface IngredientService {

    Ingredient addIngList(Ingredient ingList);

    Ingredient showIngredient(int numeric);

    boolean removeIngredient(int numeric);

    Ingredient modifyIngredient(int numeric , Ingredient ingredient);

    Map<Integer, Ingredient> showIngredientList();

}
