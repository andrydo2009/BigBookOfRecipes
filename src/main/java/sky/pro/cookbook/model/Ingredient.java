package sky.pro.cookbook.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data                                                                                                                   // аннотация содержит все необходимые методы для создания объекта класса(equal,toString and ...)
@AllArgsConstructor                                                                                                     // аннотация создания конструктора со всеми параметрами
public class Ingredient {

    private final String nameIngredient;                                                                                // название ингредиента
    private final int ingredientsQuantity;                                                                              // количество ингредиента
    private final String measure;                                                                                       // мера измерения ингредиента

}
