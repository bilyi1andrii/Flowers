package flower.store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class StoreTest {
    private static final int MAX_PRICE = 100;
    private static final int MAX_SEPAL_LENGTH = 100;
    private static final int MIN_SEPAL_LENGTH = 0;
    private static final int MIN_COUNT = 3;

    private static final double LOW_PRICE = 20.0;
    private static final double HIGH_PRICE = 40.0;

    private static final int SEPAL_LENGTH_SMALL = 8;
    private static final int SEPAL_LENGTH_LARGE = 12;
    private static final int SEPAL_LENGTH_AVERAGE = 10;

    private static final double PRICE_ROSE = 30.0;
    private static final double PRICE_CHAMOMILE = 20.0;
    private static final double PRICE_TULIP = 50.0;

    private static final int COUNT_ROSE = 5;
    private static final int COUNT_CHAMOMILE = 10;
    private static final int COUNT_TULIP = 7;

    private Store store;

    @BeforeEach
    public void setUp() {
        store = new Store();
        store.setFlowerBuckets(createSampleBuckets());
    }

    private List<FlowerBucket> createSampleBuckets() {
        List<FlowerBucket> buckets = new ArrayList<>();


        FlowerBucket roseAndChamomileBucket = new FlowerBucket();
        roseAndChamomileBucket.addFlowerPack(new FlowerPack(
            new Flower(FlowerColor.RED, SEPAL_LENGTH_AVERAGE,
            PRICE_ROSE, FlowerType.ROSE), COUNT_ROSE));
        roseAndChamomileBucket.addFlowerPack(new FlowerPack(
            new Flower(FlowerColor.YELLOW, SEPAL_LENGTH_SMALL,
            PRICE_CHAMOMILE, FlowerType.CHAMOMILE), COUNT_CHAMOMILE));


        FlowerBucket tulipAndRoseBucket = new FlowerBucket();
        tulipAndRoseBucket.addFlowerPack(new FlowerPack(
            new Flower(FlowerColor.WHITE, SEPAL_LENGTH_LARGE, PRICE_TULIP,
            FlowerType.TULIP), COUNT_TULIP));
        tulipAndRoseBucket.addFlowerPack(new FlowerPack(
            new Flower(FlowerColor.RED, SEPAL_LENGTH_AVERAGE,
            HIGH_PRICE, FlowerType.ROSE), MIN_COUNT));

        buckets.add(roseAndChamomileBucket);
        buckets.add(tulipAndRoseBucket);

        return buckets;
    }

    @Test
    public void testSearchByFlowerType() {

        List<FlowerPack> result = store.searchFlowerBucket(
            FlowerType.ROSE, null, 0,
            MAX_PRICE, MIN_SEPAL_LENGTH, MAX_SEPAL_LENGTH
        );


        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.stream().allMatch(pack ->
            pack.getFlower().getFlowerType() == FlowerType.ROSE));
    }

    @Test
    public void testSearchByColor() {

        List<FlowerPack> result = store.searchFlowerBucket(
            null, FlowerColor.RED, 0,
            MAX_PRICE, MIN_SEPAL_LENGTH, MAX_SEPAL_LENGTH
        );


        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.stream().allMatch(pack ->
            pack.getFlower().getColor().equals("#FF0000")));
    }

    @Test
    public void testSearchByPriceRange() {
        List<FlowerPack> result = store.searchFlowerBucket(
            null, null,
            LOW_PRICE, HIGH_PRICE, MIN_SEPAL_LENGTH,
            MAX_SEPAL_LENGTH
        );


        Assertions.assertEquals(MIN_COUNT, result.size());
        Assertions.assertTrue(result.stream().allMatch(pack ->
            pack.getFlower().getPrice() >= LOW_PRICE
            && pack.getFlower().getPrice() <= HIGH_PRICE));
    }

    @Test
    public void testSearchBySepalLengthRange() {
        List<FlowerPack> result = store.searchFlowerBucket(
            null, null, 0,
            MAX_PRICE, SEPAL_LENGTH_SMALL, SEPAL_LENGTH_AVERAGE
        );


        Assertions.assertEquals(MIN_COUNT, result.size());
        Assertions.assertTrue(result.stream().allMatch(pack ->
            pack.getFlower().getSepalLength() >= SEPAL_LENGTH_SMALL
            && pack.getFlower().getSepalLength() <= SEPAL_LENGTH_AVERAGE));
    }

    @Test
    public void testSearchWithMultipleCriteria() {

        List<FlowerPack> result = store.searchFlowerBucket(
            FlowerType.ROSE, FlowerColor.RED,
            PRICE_ROSE, HIGH_PRICE, MIN_SEPAL_LENGTH,
            MAX_SEPAL_LENGTH
        );


        Assertions.assertEquals(2, result.size());
        FlowerPack pack = result.get(0);
        Assertions.assertEquals(FlowerType.ROSE,
        pack.getFlower().getFlowerType());
        Assertions.assertEquals("#FF0000",
        pack.getFlower().getColor());
        Assertions.assertTrue(pack.getFlower().getPrice() >= PRICE_ROSE
        && pack.getFlower().getPrice() <= HIGH_PRICE);
    }
}