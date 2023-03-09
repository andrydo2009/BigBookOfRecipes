package sky.pro.cookbook.controllers;




import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sky.pro.cookbook.model.Ingredient;
import sky.pro.cookbook.service.IngredientService;

import java.util.Map;


@RestController
@RequestMapping("/ingredient")
@Tag (name = " Ингредиенты ", description = " Операции с ингредиентами ")
public class IngredientsController {
    public IngredientService ingredientService;

    public IngredientsController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/show/{numericIng}")// метод получает ингредиент из карты используя параметр из адреса URl
    @Operation(
          summary = "Получаем ингредиент" ,
            description = "Введите номер ингредиента из списка"
    )
    @Parameters(

            value = {
            @Parameter(name = "Номер ингредиента", example = " Например '1' ")
    }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ингредиент найден",
                            content = {
                                    @Content(
                                            mediaType = "application/json"
                                    )
                            }
                    )
            }
    )
    public ResponseEntity<Ingredient> showIngredient(@PathVariable(required = false) int numericIng) {
        Ingredient showNewIngredient = ingredientService.showIngredient ( numericIng );
        if (showNewIngredient == null) {
            return ResponseEntity.notFound ().build ();
        }
        return ResponseEntity.ok ( showNewIngredient );
    }

    @PostMapping("/add") //метод добавляет ингредиент
    public ResponseEntity<Ingredient> addIngredient(@RequestBody Ingredient ingList) {
        Ingredient addNewIngredient = ingredientService.addIngList ( ingList );
        return ResponseEntity.ok ( addNewIngredient );
    }
    @PutMapping("/edit/{numericIng}")//метод редактирует ингредиент
    public ResponseEntity<Ingredient> editIngredient(@PathVariable int numericIng,@RequestBody Ingredient ingredient){
        Ingredient editIng= ingredientService.modifyIngredient ( numericIng,ingredient);
        if ((ingredient==null)){
            ResponseEntity.notFound ().build ();
        }
        return ResponseEntity.ok (editIng);
    }
    @DeleteMapping("/delete/{numericIng}")// удаляет ингредиент
    public ResponseEntity<Ingredient> deleteIngredient(@PathVariable int numericIng){
        if (ingredientService.removeIngredient ( numericIng )){
            return ResponseEntity.ok ().build ();
        }
        return ResponseEntity.notFound ().build ();
    }

    @GetMapping("/showall")//выводит список всех ингредиентов
    public ResponseEntity<Map<Integer, Ingredient>> showAll(){
        Map<Integer, Ingredient> ingredientMap= ingredientService.showIngredientList ();
        return ResponseEntity.ok (ingredientMap);
    }


}
