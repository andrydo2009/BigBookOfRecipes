package sky.pro.cookbook.controllers;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sky.pro.cookbook.service.IngredientFileService;

import java.io.*;

@RestController
@RequestMapping("/file/ingredient")
public class IngredientsFileController {
    private final IngredientFileService ingredientFileService;

    public IngredientsFileController(IngredientFileService ingredientFileService) {
        this.ingredientFileService = ingredientFileService;
    }

    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> downloadIngredientFile() throws FileNotFoundException {
        File loadFile = ingredientFileService.getDataIngredientFile();
        if (loadFile.exists())//проверяем что файл существует
        {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(loadFile));
            // FileInputStream открываем поток
            return ResponseEntity.ok()
                    //.contentType(MediaType.APPLICATION_OCTET_STREAM)//указываем что пеедаем поток байт
                    .contentType(MediaType.APPLICATION_JSON)//указываем что пеедаем поток байт
                    .contentLength(loadFile.length())//указываем длинну файла
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"ingredinets.json\"")
                    .body(resource);//возвращаем в теле ответа файл
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/import",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
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
