package sky.pro.cookbook.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import sky.pro.cookbook.model.Ingredient;
import sky.pro.cookbook.service.IngredientFileService;
import sky.pro.cookbook.service.IngredientService;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientFileService ingredientFileService;
    private static Map<Integer, Ingredient> ingredientMap = new HashMap<>();
    private static int idIng = 0;

    public IngredientServiceImpl(IngredientFileService ingredientFileService) {
        this.ingredientFileService = ingredientFileService;
    }

    @PostConstruct // когда метод отмечен этой аннотацией, он будет вызываться сразу после внедрения зависимости
    private void init() {
        readIngredientFromFile();
    }

    @Override
    public Ingredient addIngList(Ingredient ingList) {
        ingredientMap.putIfAbsent(++idIng, ingList);
        saveIngredientToFile();
        return ingList;
    }

    @Override
    public Ingredient showIngredient(int numeric) {
        return ingredientMap.get(numeric);
    }

    @Override
    public boolean removeIngredient(int numeric) {
        if (ingredientMap.containsKey(numeric)) {
            ingredientMap.remove(numeric);
            saveIngredientToFile();
            return true;
        }
        return false;
    }

    @Override
    public Ingredient modifyIngredient(int numeric, Ingredient ingredient) {
        if (ingredientMap.containsKey(numeric)) {
            ingredientMap.put(numeric, ingredient);
            saveIngredientToFile();
            return ingredientMap.get(numeric);
        }
        return null;
    }

    @Override
    public Map<Integer, Ingredient> showIngredientList() {
        return ingredientMap;
    }


    private void saveIngredientToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientMap);
            ingredientFileService.saveIngredientToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readIngredientFromFile() {
        try {
            String json = ingredientFileService.readIngredientFromFile();
            ingredientMap = new ObjectMapper().readValue(json, new TypeReference<HashMap<Integer, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
