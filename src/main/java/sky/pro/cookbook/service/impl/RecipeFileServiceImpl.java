package sky.pro.cookbook.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sky.pro.cookbook.service.RecipeFileService;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
//
@Service
public class RecipeFileServiceImpl implements RecipeFileService {

    @Value("${path.to.data.files}")
    private String recipesFilePath;

    @Value("${name.of.recipes.file}")
    private String recipesFileName;


    @Override
    public boolean saveRecipeToFile(String json) {
        try {
            cleanRecipeDataFile ();//подготавливаем файл
            Files.writeString ( Path.of ( recipesFilePath , recipesFileName ) , json ); // записывает файл в строку
            return true;
        } catch (IOException e) {
            e.printStackTrace ();
            return false;
        }
    }

    @Override
    public String readRecipeFromFile() {
        try {
            return Files.readString ( Path.of ( recipesFilePath , recipesFileName ) ); // метод читает файл, в скобках укзано имя и путь
        } catch (IOException e) {
            throw new RuntimeException ( e );
        }
    }

    @Override
    public boolean cleanRecipeDataFile() { //метод удаляет/обновляет файл
        try {
            Path path = Path.of ( recipesFilePath , recipesFileName ); // class Path предназначен для работы с файлами, в скобках имя файла затем путь
            Files.deleteIfExists ( path );// удаляем старый файл если он есть
            Files.createFile ( path );// создаем новый файл
            return true;
        } catch (IOException e) {
            e.printStackTrace ();
            return false;
        }
    }

    @Override
    public File getDataRecipeFile() {
        return new File ( recipesFilePath + "/" + recipesFileName );
    }


    @Override
    public Path createTempFile(String suffix) {
        try {
            return Files.createTempFile ( Path.of ( recipesFilePath ) , "tempFile" , suffix ); //генерируем временный файл
        } catch (IOException e) {
            throw new RuntimeException ( e );
        }
    }

}
