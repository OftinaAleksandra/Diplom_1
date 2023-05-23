import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParamBunTest {
    private Bun bun;
    private final String name;
    private final float price;

    public ParamBunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }
    @Parameterized.Parameters(name = "Тест с именем {0} и ценой {1}")
    public static Object[][] getBunData() {
        return new Object[][]{
                {"Булочка", 10.50f},
                {"Булка!№;%:@", 55.999f},
                {"Bulochka", -1000},
                {"123", 123},
                {null, 0},
                {"", 0},
        };
    }

    @Before
    public void setUp() {
        bun = new Bun(name, price);
    }

    @Test
    public void getNameBunTest() {
        String actualResult = bun.getName();
        assertEquals(name, actualResult);
    }
    @Test
    public void getPriceBunTest() {
        float actualResult = bun.getPrice();
        assertEquals(price, actualResult, 0);
    }
}
