package sky.pro.cookbook.service;

public interface RecipeFileService {


    boolean saveRecipeToFile(String json);

    String readRecipeFromFile();

    boolean cleanRecipeDataFile();
}
