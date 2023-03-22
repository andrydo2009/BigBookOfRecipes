package sky.pro.cookbook.service;

import java.io.File;
import java.nio.file.Path;

public interface RecipeFileService {


    boolean saveRecipeToFile(String json);

    String readRecipeFromFile();

    boolean cleanRecipeDataFile();


    File getDataRecipeFile();

    Path createTempFile(String suffix);
}
//