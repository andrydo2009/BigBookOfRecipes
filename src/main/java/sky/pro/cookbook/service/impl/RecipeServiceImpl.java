package sky.pro.cookbook.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import sky.pro.cookbook.model.Recipe;
import sky.pro.cookbook.service.RecipeFileService;
import sky.pro.cookbook.service.RecipeService;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeFileService recipeFileService;
    private static Map<Integer, Recipe> recipeMap = new HashMap<> ();
    private static int idRec = 0;

    public RecipeServiceImpl(RecipeFileService recipeFileService) {
        this.recipeFileService = recipeFileService;
    }
    @PostConstruct // когда метод отмечен этой аннотацией, он будет вызываться сразу после внедрения зависимости
    private void init() {
        readRecipeFromFile ();
        idRec=recipeMap.size();
    }


    @Override
    public Recipe showRecList(int numeric) {
        return recipeMap.get ( numeric );
    }


    @Override
    public Recipe addRecList(Recipe recipe) {
        recipeMap.putIfAbsent ( ++idRec , recipe );
        saveRecipeToFile ();
        return recipe;
    }

    @Override
    public boolean removeRecipe(int numeric) {
        if (recipeMap.containsKey ( numeric )) {
            recipeMap.remove ( numeric );
            saveRecipeToFile ();
            return true;
        }
        return false;
    }

    @Override
    public Recipe modifyRecipe(int numeric , Recipe recipe) {
        if (recipeMap.containsKey ( numeric )) {
            recipeMap.put ( numeric , recipe );
            saveRecipeToFile ();
            return recipeMap.get ( numeric );
        }
        return null;
    }

    @Override
    public Map<Integer, Recipe> showRecipeList() {
        return recipeMap;
    }


    private void saveRecipeToFile() {
        try {
            String json = new ObjectMapper ().writeValueAsString ( recipeMap );
            recipeFileService.saveRecipeToFile ( json );
        } catch (JsonProcessingException e) {
            throw new RuntimeException ( e );
        }
    }

    private void readRecipeFromFile() {
        try {
            String json = recipeFileService.readRecipeFromFile ();
            recipeMap = new ObjectMapper ().readValue ( json , new TypeReference<HashMap<Integer, Recipe>> () {
            } );
        } catch (JsonProcessingException e) {
            throw new RuntimeException ( e );
        }
    }
}
