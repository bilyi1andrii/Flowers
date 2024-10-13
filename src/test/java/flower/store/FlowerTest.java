package flower.store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import org.junit.jupiter.api.Assertions;

public class FlowerTest {
    private static final Random RANDOM_GENERATOR = new Random();
    private static final int MAX_PRICE = 100;
    private static final int DEFAULT_SEPAL_LENGTH = 5;
    private static final int SEPAL_LENGTH_SMALL = 8;
    private static final int SEPAL_LENGTH_LARGE = 10;
    private static final double PRICE_LOW = 30.0;
    private static final double PRICE_HIGH = 50.0;
    private static final FlowerColor COLOR_RED = FlowerColor.RED;
    private static final FlowerColor COLOR_YELLOW = FlowerColor.YELLOW;
    private static final FlowerType TYPE_ROSE = FlowerType.ROSE;
    private static final FlowerType TYPE_TULIP = FlowerType.TULIP;
    private static final FlowerType TYPE_CHAMOMILE = FlowerType.CHAMOMILE;

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
        flower.setColor(COLOR_RED);
        Assertions.assertEquals(COLOR_RED.toString(), flower.getColor());
    }

    @Test
    public void testFlowerType() {
        flower.setFlowerType(TYPE_ROSE);
        Assertions.assertEquals(TYPE_ROSE, flower.getFlowerType());
    }

    @Test
    public void testSepalLength() {
        flower.setSepalLength(DEFAULT_SEPAL_LENGTH);
        Assertions.assertEquals(DEFAULT_SEPAL_LENGTH, flower.getSepalLength());
    }

    @Test
    public void testConstructor() {
        Flower newFlower = new Flower(COLOR_RED,
        SEPAL_LENGTH_LARGE, PRICE_HIGH, TYPE_TULIP);

        Assertions.assertEquals(COLOR_RED.toString(), newFlower.getColor());
        Assertions.assertEquals(SEPAL_LENGTH_LARGE, newFlower.getSepalLength());
        Assertions.assertEquals(PRICE_HIGH, newFlower.getPrice());
        Assertions.assertEquals(TYPE_TULIP, newFlower.getFlowerType());
    }

    @Test
    public void testCopyConstructor() {
        Flower originalFlower = new Flower(COLOR_YELLOW,
        SEPAL_LENGTH_SMALL, PRICE_LOW, TYPE_CHAMOMILE);
        Flower copiedFlower = new Flower(originalFlower);

        Assertions.assertEquals(originalFlower.getColor(),
        copiedFlower.getColor());
        Assertions.assertEquals(originalFlower.getSepalLength(),
        copiedFlower.getSepalLength());
        Assertions.assertEquals(originalFlower.getPrice(),
        copiedFlower.getPrice());
        Assertions.assertEquals(originalFlower.getFlowerType(),
        copiedFlower.getFlowerType());
    }
}