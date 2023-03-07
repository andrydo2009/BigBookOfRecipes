package sky.pro.cookbook.service.impl;


import org.springframework.stereotype.Service;
import sky.pro.cookbook.model.Ingredient;
import sky.pro.cookbook.service.IngredientService;

import java.util.HashMap;
import java.util.Map;


@Service
public class IngredientServiceImpl implements IngredientService {

    private static final Map<Integer, Ingredient> ingredientMap = new HashMap<> ();
    private static int idIng = 0;


    @Override
    public Ingredient addIngList(Ingredient ingList) {
        ingredientMap.putIfAbsent ( idIng++ , ingList );
        return ingList;
    }

    @Override
    public Ingredient showIngredient(int numeric) {
        return ingredientMap.get ( numeric );
    }

    @Override
    public boolean removeIngredient(int numeric) {
        if (ingredientMap.containsKey ( numeric )){
            ingredientMap.remove ( numeric );
            return true;
        }
        return false;
    }

    @Override
    public Ingredient modifyIngredient(int numeric , Ingredient ingredient) {
        if (ingredientMap.containsKey ( numeric )) {
            ingredientMap.remove ( numeric );
            ingredientMap.put ( numeric , ingredient );
            return ingredientMap.get ( numeric );
        }
        return null;
    }

    @Override
    public void showIngredientList() {
        ingredientMap.forEach ( (key , value) -> System.out.println ( key + " - " + value ) );
    }

}
