package sky.pro.cookbook.service;

import java.io.File;


public interface IngredientFileService {


    boolean saveIngredientToFile(String json);

    String readIngredientFromFile();



    boolean cleanIngredientDataFile();


    File getDataIngredientFile();
}
