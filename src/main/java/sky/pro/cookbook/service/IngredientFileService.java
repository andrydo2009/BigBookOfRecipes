package sky.pro.cookbook.service;

public interface IngredientFileService {


    boolean saveIngredientToFile(String json);

    String readIngredientFromFile();

    boolean cleanIngredientDataFile();
}
