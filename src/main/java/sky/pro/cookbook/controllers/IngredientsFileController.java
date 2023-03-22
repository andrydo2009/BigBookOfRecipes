package sky.pro.cookbook.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sky.pro.cookbook.model.Ingredient;
import sky.pro.cookbook.service.IngredientFileService;

import java.io.*;
//
@RestController
@RequestMapping("/file/ingredient")
@Tag(name = " Работа с файлами ", description = " Операции с файлами ингредиентами ")
public class IngredientsFileController {
    private final IngredientFileService ingredientFileService;

    public IngredientsFileController(IngredientFileService ingredientFileService) {
        this.ingredientFileService = ingredientFileService;
    }

    @GetMapping("/export")
    @Operation(
            summary = "Скачивание файла",
            description = "Скачиваем  список всех ингредиентов в JSON формате"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Загрузка файла прошла успешно",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema =
                                            @Schema(implementation = Ingredient.class))
                                    )
                            }
                    ) ,
                    @ApiResponse(
                            responseCode = "404",
                            description = "Загрузка файла не удалась"
                    ) ,
                    @ApiResponse(
                            responseCode = "500",
                            description = "Во время выполнения запроса произошла ошибка на сервере"
                    )

            }
    )
    public ResponseEntity<InputStreamResource> downloadIngredientFile() throws FileNotFoundException {
        File loadFile = ingredientFileService.getDataIngredientFile();
        if (loadFile.exists())//проверяем что файл существует
        {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(loadFile));
            // FileInputStream открываем поток
            return ResponseEntity.ok()
                    //.contentType(MediaType.APPLICATION_OCTET_STREAM)//указываем что передаем поток байт
                    .contentType(MediaType.APPLICATION_JSON)//указываем что передаем поток байт
                    .contentLength(loadFile.length())//указываем длину файла
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"ingredinets.json\"")
                    .body(resource);//возвращаем в теле ответа файл
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/import",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Загрузка файла",
            description = "Загружаем  список всех ингредиентов в JSON формате"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Загрузка файла прошла успешно",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema =
                                            @Schema(implementation = Ingredient.class))
                                    )
                            }
                    ) ,
                    @ApiResponse(
                            responseCode = "404",
                            description = "Загрузка файла не удалась"
                    ) ,
                    @ApiResponse(
                            responseCode = "500",
                            description = "Во время выполнения запроса произошла ошибка на сервере"
                    )

            }
    )
    public ResponseEntity<Void> uploadIngredientFile(@RequestParam MultipartFile file) {
        ingredientFileService.cleanIngredientDataFile();
        File uploadFile = ingredientFileService.getDataIngredientFile();
        try (FileOutputStream fos = new FileOutputStream(uploadFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


}
