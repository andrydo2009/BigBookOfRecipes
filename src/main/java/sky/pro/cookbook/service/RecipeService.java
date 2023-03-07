package sky.pro.cookbook.service;


import sky.pro.cookbook.model.Recipe;

public interface RecipeService {

    Recipe addRecList(Recipe recipe) ;
    Recipe showRecList(int numeric) ;

    boolean removeRecipe(int numeric);

    Recipe modifyRecipe(int numeric , Recipe recipe);

    void showRecipeList();

}
