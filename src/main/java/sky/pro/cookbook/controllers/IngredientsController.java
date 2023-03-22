package sky.pro.cookbook.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Tag(name = " Ингредиенты ", description = " Операции с ингредиентами ")
public class IngredientsController {
    public IngredientService ingredientService;

    public IngredientsController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/show/{numericIng}")// метод получает ингредиент из карты используя параметр из адреса URl
    @Operation(
            summary = "Получаем ингредиент",
            description = "Введите номер ингредиента из списка"
    )
    @Parameters(

            value = {
                    @Parameter(name = "Номер ингредиента", example = " Введите например '1' ")
            }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ингредиент найден",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema =
                                            @Schema(implementation = Ingredient.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Ингредиент не найден. Проверьте правильность ввода номера ингредиента.",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema =
                                            @Schema(implementation = Ingredient.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Во время выполнения запроса произошла ошибка на сервере"
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
    @Operation(
            summary = "Добавляем ингредиент в список"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ингредиент добавлен",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema =
                                            @Schema(implementation = Ingredient.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Ингредиент не получилось добавить в список",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema =
                                            @Schema(implementation = Ingredient.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Во время выполнения запроса произошла ошибка на сервере"
                    )

            }
    )
    public ResponseEntity<Ingredient> addIngredient(@RequestBody Ingredient ingList) {
        Ingredient addNewIngredient = ingredientService.addIngList ( ingList );
        return ResponseEntity.ok ( addNewIngredient );
    }

    @PutMapping("/edit/{numericIng}")//метод редактирует ингредиент
    @Operation(
            summary = "Редактируем ингредиент",
            description = "Введите номер ингредиента из списка"
    )
    @Parameters(
            value = {
                    @Parameter(name = "Номер ингредиента", example = " Введите например '1' ")
            }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ингредиент изменен",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema =
                                            @Schema(implementation = Ingredient.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Ингредиент не получилось изменить",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema =
                                            @Schema(implementation = Ingredient.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Во время выполнения запроса произошла ошибка на сервере"
                    )

            }
    )
    public ResponseEntity<Ingredient> editIngredient(@PathVariable int numericIng , @RequestBody Ingredient ingredient) {
        Ingredient editIng = ingredientService.modifyIngredient ( numericIng , ingredient );
        if ((ingredient == null)) {
            ResponseEntity.notFound ().build ();
        }
        return ResponseEntity.ok ( editIng );
    }

    @DeleteMapping("/delete/{numericIng}")// удаляет ингредиент
    @Operation(
            summary = "Удаляем ингредиент",
            description = "Введите номер ингредиента из списка"
    )
    @Parameters(
            value = {
                    @Parameter(name = "Номер ингредиента", example = " Введите например '1' ")
            }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ингредиент удален",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema =
                                            @Schema(implementation = Ingredient.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Ингредиент не получилось удалить",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema =
                                            @Schema(implementation = Ingredient.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Во время выполнения запроса произошла ошибка на сервере"
                    )

            }
    )
    public ResponseEntity<Ingredient> deleteIngredient(@PathVariable int numericIng) {
        if (ingredientService.removeIngredient ( numericIng )) {
            return ResponseEntity.ok ().build ();
        }
        return ResponseEntity.notFound ().build ();
    }

    @GetMapping("/showall")//выводит список всех ингредиентов
    @Operation(
            summary = "Список ингредиентов",
            description = "Выводим список ингредиентов"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Доступен список ингредиентов",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema =
                                            @Schema(implementation = Ingredient.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Список не доступен",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema =
                                            @Schema(implementation = Ingredient.class))
                                    )
                            }
                    )

            }
    )
    public ResponseEntity<Map<Integer, Ingredient>> showAll() {
        Map<Integer, Ingredient> ingredientMap = ingredientService.showIngredientList ();
        return ResponseEntity.ok ( ingredientMap );
    }

}
