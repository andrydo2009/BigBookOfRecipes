package sky.pro.cookbook.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sky.pro.cookbook.service.RecipeFileService;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class RecipeFileServiceImpl implements RecipeFileService {

    @Value("${path.to.data.files}")
    private String recipesFilePath;

    @Value("${name.of.recipes.file}")
    private String recipesFileName;


    @Override
    public boolean saveRecipeToFile(String json) {
        try {
            cleanRecipeDataFile ();
            Files.writeString ( Path.of ( recipesFilePath , recipesFileName ) , json );
            return true;
        } catch (IOException e) {
            e.printStackTrace ();
            return false;
        }
    }

    @Override
    public String readRecipeFromFile() {
        try {
            return Files.readString ( Path.of ( recipesFilePath , recipesFileName ) );
        } catch (IOException e) {
            throw new RuntimeException ( e );
        }
    }

    public boolean cleanRecipeDataFile() {
        try {
            Path path = Path.of ( recipesFilePath , recipesFileName );
            Files.deleteIfExists ( path );
            Files.createFile ( path );
            return true;
        } catch (IOException e) {
            e.printStackTrace ();
            return false;
        }
    }
}
