package sky.pro.cookbook.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
// аннотация содержит все необходимые методы для создания объекта класса(equal,toString and ...)
@AllArgsConstructor
// аннотация создания конструктора со всеми параметрами
public class Recipe {


    private final String recipeName;                                                                                    // название рецепта
    private final int cookingTime;                                                                                      // время приготовления
    private final ArrayList<Ingredient> ingredientsList;                                                                // список ингредиентов
    private final ArrayList<String> cookingMethod;                                                                      // способ приготовления, список шагов

}

