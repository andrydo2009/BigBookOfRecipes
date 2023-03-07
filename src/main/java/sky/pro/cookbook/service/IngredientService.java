package sky.pro.cookbook.service;


import sky.pro.cookbook.model.Ingredient;

public interface IngredientService {

    Ingredient addIngList(Ingredient ingList);

    Ingredient showIngredient(int numeric);

    boolean removeIngredient(int numeric);

    Ingredient modifyIngredient(int numeric , Ingredient ingredient);

    void showIngredientList();

}
