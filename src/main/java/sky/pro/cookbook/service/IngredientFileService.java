package sky.pro.cookbook.service;

import java.io.File;


public interface IngredientFileService {


    boolean saveIngredientToFile(String json);

    String readIngredientFromFile();


    //Path createTempFile(String suffix);

    boolean cleanIngredientDataFile();


    File getDataIngredientFile();
}
