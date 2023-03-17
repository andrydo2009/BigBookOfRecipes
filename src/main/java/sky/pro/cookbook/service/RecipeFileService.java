package sky.pro.cookbook.service;

import java.io.File;

public interface RecipeFileService {


    boolean saveRecipeToFile(String json);

    String readRecipeFromFile();

    boolean cleanRecipeDataFile();


    File getDataRecipeFile();
}
