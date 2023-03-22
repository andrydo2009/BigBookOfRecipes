package sky.pro.cookbook.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
// аннотация содержит все необходимые методы для создания объекта класса(equal,toString and ...)
@AllArgsConstructor
// аннотация создания конструктора со всеми параметрами
@NoArgsConstructor
//автоматическая генерация не параметризованного конструктора
@JsonIgnoreProperties(ignoreUnknown = true)
// указывает механизмам десериализации Jackson игнорировать поля в JSON-ответах,
// для которых не существует соответствующей переменной экземпляра.
public class Recipe {


    private String recipeName;                                                                                    // название рецепта
    private int cookingTime;                                                                                      // время приготовления
    private ArrayList<Ingredient> ingredientsList;                                                                // список ингредиентов
    private ArrayList<String> cookingMethod;                                                                      // способ приготовления, список шагов

}
