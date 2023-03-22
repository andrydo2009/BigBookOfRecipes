package sky.pro.cookbook.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
// аннотация содержит все необходимые методы для создания объекта класса(equal,toString and ...)
@AllArgsConstructor
// аннотация создания конструктора со всеми параметрами
@NoArgsConstructor
//автоматическая генерация не параметризованного конструктора
@JsonIgnoreProperties(ignoreUnknown = true)
//указывает механизмам десериализации Jackson игнорировать поля в JSON-ответах,
// для которых несуществует соответствующей переменной экземпляра.
public class Ingredient {

    private  String nameIngredient;                                                                                // название ингредиента
    private  int ingredientsQuantity;                                                                              // количество ингредиента
    private  String measure;                                                                                       // мера измерения ингредиента

}
