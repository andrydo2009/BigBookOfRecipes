package sky.pro.cookbook.service.impl;


import org.springframework.stereotype.Service;
import sky.pro.cookbook.model.Recipe;
import sky.pro.cookbook.service.RecipeService;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {



    private static final Map<Integer, Recipe> recipeMap = new HashMap<> ();
    private static int idRec = 0;

    @Override
    public Recipe showRecList(int numeric) {
        return recipeMap.get ( numeric );
    }

    @Override
    public Recipe addRecList(Recipe recipe) {
        recipeMap.putIfAbsent ( idRec++ , recipe );
        return recipe;
    }

    @Override
    public boolean removeRecipe(int numeric) {
        if (recipeMap.containsKey ( numeric )){
            recipeMap.remove ( numeric );
            return true;
        }
        return false;
    }

    @Override
    public Recipe modifyRecipe(int numeric , Recipe recipe){
        if (recipeMap.containsKey ( numeric )) {
            recipeMap.remove ( numeric );
            recipeMap.put ( numeric , recipe);
            return recipeMap.get ( numeric );
        }
        return null;
    }

    @Override
    public void showRecipeList() {
        recipeMap.forEach ( (key , value) -> System.out.println ( key + " - " + value ) );
    }

}
