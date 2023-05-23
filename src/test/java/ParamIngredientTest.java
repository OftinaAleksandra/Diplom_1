import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParamIngredientTest {

    private Ingredient ingredient;
    private final String name;
    private final float price;
    private final IngredientType ingredientType;

    public ParamIngredientTest(IngredientType ingredientType, String name, float price) {
        this.name = name;
        this.price = price;
        this.ingredientType = ingredientType;
    }

    @Parameterized.Parameters(name = "Тест с типом ингредиента {0}, именем {1} и ценой {2}")
    public static Object[][] getIngredientData() {
        return new Object[][]{
                {IngredientType.FILLING, "Котлета", 10.50f},
                {IngredientType.SAUCE, "Кетчуп", 0.5f},
                {null, "<.>", 100},
                {IngredientType.SAUCE, "Нечто", 1525.3999f},
                {IngredientType.FILLING, null, 0},
                {IngredientType.SAUCE, "", 0},
        };
    }

    @Before
    public void setUp() {
        ingredient = new Ingredient(ingredientType, name, price);
    }

    @Test
    public void getPriceIngredientTest() {
        float actualResult = ingredient.getPrice();
        assertEquals(price, actualResult, 0);
    }

    @Test
    public void getNameIngredientTest() {
        String actualResult = ingredient.getName();
        assertEquals(name, actualResult);
    }

    @Test
    public void getTypeIngredientTest() {
        IngredientType actualResult = ingredient.getType();
        assertEquals(ingredientType, actualResult);
    }

}
