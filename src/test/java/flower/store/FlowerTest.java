package flower.store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import org.junit.jupiter.api.Assertions;

public class FlowerTest {
    private static final Random RANDOM_GENERATOR = new Random();
    private static final int MAX_PRICE = 100;
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
        FlowerColor color = FlowerColor.RED;
        flower.setColor(color);
        Assertions.assertEquals("#FF0000", flower.getColor());
    }

    @Test
    public void testFlowerType() {
        FlowerType flowerType = FlowerType.ROSE;
        flower.setFlowerType(flowerType);
        Assertions.assertEquals(flowerType, flower.getFlowerType());
    }

    @Test
    public void testSepalLength() {
        int sepalLength = 5;
        flower.setSepalLength(sepalLength);
        Assertions.assertEquals(sepalLength, flower.getSepalLength());
    }

    @Test
    public void testConstructor() {
        FlowerColor color = FlowerColor.RED;
        int sepalLength = 10;
        double price = 50.0;
        FlowerType flowerType = FlowerType.TULIP;

        Flower newFlower = new Flower(color,
        sepalLength, price, flowerType);

        Assertions.assertEquals(color.toString(),
        newFlower.getColor().toString());

        Assertions.assertEquals(sepalLength,
        newFlower.getSepalLength());

        Assertions.assertEquals(price, newFlower.getPrice());
        Assertions.assertEquals(flowerType, newFlower.getFlowerType());
    }

    @Test
    public void testCopyConstructor() {
        Flower originalFlower = new Flower(FlowerColor.YELLOW,
        8, 30.0, FlowerType.CHAMOMILE);
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
