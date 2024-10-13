package flower.store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import org.junit.jupiter.api.Assertions;

public class FlowerTest {
    private static final Random RANDOM_GENERATOR = new Random();
    private static final int MAX_PRICE = 100;
    private static final int DEFAULT_SEPAL_LENGTH = 5;
    private static final int TEST_SEPAL_LENGTH_1 = 8;
    private static final int TEST_SEPAL_LENGTH_2 = 10;
    private static final double TEST_PRICE_1 = 30.0;
    private static final double TEST_PRICE_2 = 50.0;
    private static final FlowerColor TEST_COLOR_1 = FlowerColor.RED;
    private static final FlowerColor TEST_COLOR_2 = FlowerColor.YELLOW;
    private static final FlowerType TEST_FLOWER_TYPE_1 = FlowerType.ROSE;
    private static final FlowerType TEST_FLOWER_TYPE_2 = FlowerType.TULIP;
    private static final FlowerType TEST_FLOWER_TYPE_3 = FlowerType.CHAMOMILE;

    private Flower flower;

    @BeforeEach
    public void init() {
        flower = new Flower();
    }

    @Test
    public void testPrice() {
        int price = RANDOM_GENERATOR.nextInt(MAX_PRICE);
        flower.setPrice(price);
        Assertions.assertEquals(price, flower.getPrice());
    }

    @Test
    public void testColor() {
        flower.setColor(TEST_COLOR_1);
        Assertions.assertEquals(TEST_COLOR_1.toString(), flower.getColor());
    }

    @Test
    public void testFlowerType() {
        flower.setFlowerType(TEST_FLOWER_TYPE_1);
        Assertions.assertEquals(TEST_FLOWER_TYPE_1, flower.getFlowerType());
    }

    @Test
    public void testSepalLength() {
        flower.setSepalLength(DEFAULT_SEPAL_LENGTH);
        Assertions.assertEquals(DEFAULT_SEPAL_LENGTH, flower.getSepalLength());
    }

    @Test
    public void testConstructor() {
        Flower newFlower = new Flower(TEST_COLOR_1, TEST_SEPAL_LENGTH_2, TEST_PRICE_2, TEST_FLOWER_TYPE_2);

        Assertions.assertEquals(TEST_COLOR_1.toString(), newFlower.getColor());
        Assertions.assertEquals(TEST_SEPAL_LENGTH_2, newFlower.getSepalLength());
        Assertions.assertEquals(TEST_PRICE_2, newFlower.getPrice());
        Assertions.assertEquals(TEST_FLOWER_TYPE_2, newFlower.getFlowerType());
    }

    @Test
    public void testCopyConstructor() {
        Flower originalFlower = new Flower(TEST_COLOR_2, TEST_SEPAL_LENGTH_1, TEST_PRICE_1, TEST_FLOWER_TYPE_3);
        Flower copiedFlower = new Flower(originalFlower);

        Assertions.assertEquals(originalFlower.getColor(), copiedFlower.getColor());
        Assertions.assertEquals(originalFlower.getSepalLength(), copiedFlower.getSepalLength());
        Assertions.assertEquals(originalFlower.getPrice(), copiedFlower.getPrice());
        Assertions.assertEquals(originalFlower.getFlowerType(), copiedFlower.getFlowerType());
    }
}