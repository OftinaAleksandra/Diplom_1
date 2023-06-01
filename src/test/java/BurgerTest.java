import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

public class BurgerTest {
    private Burger burger;
    public Bun bunMock() {
        Bun bunMock = Mockito.mock(Bun.class);
        Mockito.when(bunMock.getPrice()).thenReturn(10.25f);
        Mockito.when(bunMock.getName()).thenReturn("Булочка");
        return bunMock;
    }

    public Ingredient fillingIngredientMock() {
        Ingredient fillingIngredientMock = Mockito.mock(Ingredient.class);
        Mockito.when(fillingIngredientMock.getName()).thenReturn("Котлета");
        Mockito.when(fillingIngredientMock.getType()).thenReturn(FILLING);
        Mockito.when(fillingIngredientMock.getPrice()).thenReturn(55.55f);
        return fillingIngredientMock;
    }

    public Ingredient sauceIngredientMock() {
        Ingredient fillingIngredientMock = Mockito.mock(Ingredient.class);
        Mockito.when(fillingIngredientMock.getName()).thenReturn("Кетчуп");
        Mockito.when(fillingIngredientMock.getType()).thenReturn(SAUCE);
        Mockito.when(fillingIngredientMock.getPrice()).thenReturn(100f);
        return fillingIngredientMock;
    }

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunTest() {
        Bun expectedBun = bunMock();
        burger.setBuns(expectedBun);
        assertEquals(expectedBun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        Ingredient expectedIngredient = fillingIngredientMock();
        burger.addIngredient(expectedIngredient);
        assertTrue(burger.ingredients.contains(expectedIngredient));
    }

    @Test
    public void removeIngredientTest() {
        Ingredient expectedIngredient = fillingIngredientMock();
        burger.addIngredient(expectedIngredient);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientTest() {
        Ingredient expected1Ingredient = fillingIngredientMock();
        burger.addIngredient(expected1Ingredient);
        Ingredient expected2Ingredient = sauceIngredientMock();
        burger.addIngredient(expected2Ingredient);

        burger.moveIngredient(0, 1);
        assertEquals(expected1Ingredient, burger.ingredients.get(1));
    }

    @Test
    public void getPriceTest() {
        Bun expectedBun = bunMock();
        burger.setBuns(expectedBun);

        Ingredient expected1Ingredient = fillingIngredientMock();
        burger.addIngredient(expected1Ingredient);
        Ingredient expected2Ingredient = sauceIngredientMock();
        burger.addIngredient(expected2Ingredient);

        float expectedPrice = expectedBun.getPrice() * 2 + expected1Ingredient.getPrice() + expected2Ingredient.getPrice();
        assertEquals(expectedPrice, burger.getPrice(), 0);
    }
    @Test
    public void getReceiptTest() {
        Bun expectedBun = bunMock();
        burger.setBuns(expectedBun);

        Ingredient expected1Ingredient = fillingIngredientMock();
        burger.addIngredient(expected1Ingredient);

        Ingredient expected2Ingredient = sauceIngredientMock();
        burger.addIngredient(expected2Ingredient);

        String expectedResult ="(==== Булочка ====)\r\n" +
                "= filling Котлета =\r\n" +
                "= sauce Кетчуп =\r\n" +
                "(==== Булочка ====)\r\n\r\n" +
                "Price: 176,050003\r\n";

        assertEquals(expectedResult, burger.getReceipt());
    }

}
