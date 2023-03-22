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
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sky.pro.cookbook.model.Recipe;
import sky.pro.cookbook.service.RecipeService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@RestController
@RequestMapping("/recipe")
@Tag(name = " Рецепты ", description = " Операции с рецептами ")
public class RecipesController {

    private final RecipeService recipeService;

    public RecipesController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/show/{numericRec}")// метод получает рецепт из карты используя параметр из адреса URl
    @Operation(
            summary = "Получаем рецепт",
            description = "Введите номер рецепта из списка"
    )
    @Parameters(

            value = {
                    @Parameter(name = "Номер рецепта", example = " Введите например '1' ")
            }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "рецепт найден",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema =
                                            @Schema(implementation = Recipe.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Рецепт не найден. Проверьте правильность ввода номера рецепта.",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema =
                                            @Schema(implementation = Recipe.class))
                                    )
                            }
                    )

            }
    )
    public ResponseEntity<Recipe> showRecipe(@PathVariable int numericRec) {
        Recipe showNewRecipe = recipeService.showRecList ( numericRec );
        if (showNewRecipe == null) {
            return ResponseEntity.notFound ().build ();
        }
        return ResponseEntity.ok ( showNewRecipe );
    }

    @PostMapping("/add") //метод добавляет рецепт
    @Operation(
            summary = "Добавляем рецепт в список"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Рецепт добавлен",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema =
                                            @Schema(implementation = Recipe.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Рецепт не получилось добавить в список",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema =
                                            @Schema(implementation = Recipe.class))
                                    )
                            }
                    )

            }
    )
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
        Recipe addNewRecipe = recipeService.addRecList ( recipe );
        return ResponseEntity.ok ( addNewRecipe );
    }

    @PutMapping("/edit/{numericRec}")//метод редактирует рецепт
    @Operation(
            summary = "Редактируем рецепт",
            description = "Введите номер рецепта из списка"
    )
    @Parameters(
            value = {
                    @Parameter(name = "Номер рецепта", example = " Введите например '1' ")
            }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Рецепт изменен",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema =
                                            @Schema(implementation = Recipe.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Рецепт не получилось изменить",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema =
                                            @Schema(implementation = Recipe.class))
                                    )
                            }
                    )

            }
    )
    public ResponseEntity<Recipe> editRecipe(@PathVariable int numericRec , @RequestBody Recipe recipe) {
        Recipe editRec = recipeService.modifyRecipe ( numericRec , recipe );
        if ((recipe == null)) {
            ResponseEntity.notFound ().build ();
        }
        return ResponseEntity.ok ( editRec );
    }

    @DeleteMapping("/delete/{numericRec}")// удаляет рецепт
    @Operation(
            summary = "Удаляем рецепт",
            description = "Введите номер рецепта из списка"
    )
    @Parameters(
            value = {
                    @Parameter(name = "Номер рецепта", example = " Введите например '1' ")
            }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Рецепт удален",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema =
                                            @Schema(implementation = Recipe.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Рецепт не получилось удалить",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema =
                                            @Schema(implementation = Recipe.class))
                                    )
                            }
                    )

            }
    )
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable int numericRec) {
        if (recipeService.removeRecipe ( numericRec )) {
            return ResponseEntity.ok ().build ();
        }
        return ResponseEntity.notFound ().build ();
    }

    @GetMapping("/showall")//выводит список всех рецептов
    @Operation(
            summary = "Список рецептов",
            description = "Выводим список рецептов"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Доступен список рецептов",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema =
                                            @Schema(implementation = Recipe.class))
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
                                            @Schema(implementation = Recipe.class))
                                    )
                            }
                    )

            }
    )
    public ResponseEntity<Map<Integer, Recipe>> showAll() {
        Map<Integer, Recipe> recipeMap = recipeService.showRecipeList ();
        return ResponseEntity.ok ( recipeMap );
    }

    @GetMapping("/export/user")
    public ResponseEntity<Object> downloadUserFileRecipe() throws FileNotFoundException {
        try {
            Path path = recipeService.createUserFileRecipe ();
            if (Files.size ( path ) == 0) {
                return ResponseEntity.noContent ().build ();
            }
            InputStreamResource resource = new InputStreamResource ( new FileInputStream ( path.toFile () ) );
            return ResponseEntity.ok ()
                    .contentType ( MediaType.TEXT_PLAIN )
                    .contentLength ( Files.size ( path ) )
                    .header ( HttpHeaders.CONTENT_DISPOSITION , "attachment; filename=\"report.txt\"" )
                    .body ( resource );
        } catch (IOException e) {
            e.printStackTrace ();
            return ResponseEntity.internalServerError ().body ( e.toString () );
        }

    }
}

