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

        // First bucket with Roses and Chamomiles
        FlowerBucket roseAndChamomileBucket = new FlowerBucket();
        roseAndChamomileBucket.addFlowerPack(new FlowerPack(
            new Flower(FlowerColor.RED, 10, PRICE_ROSE, FlowerType.ROSE), COUNT_ROSE));
        roseAndChamomileBucket.addFlowerPack(new FlowerPack(
            new Flower(FlowerColor.YELLOW, SEPAL_LENGTH_SMALL, PRICE_CHAMOMILE, FlowerType.CHAMOMILE), COUNT_CHAMOMILE));

        // Second bucket with Tulips and Roses
        FlowerBucket tulipAndRoseBucket = new FlowerBucket();
        tulipAndRoseBucket.addFlowerPack(new FlowerPack(
            new Flower(FlowerColor.WHITE, SEPAL_LENGTH_LARGE, PRICE_TULIP, FlowerType.TULIP), COUNT_TULIP));
        tulipAndRoseBucket.addFlowerPack(new FlowerPack(
            new Flower(FlowerColor.RED, 9, HIGH_PRICE, FlowerType.ROSE), MIN_COUNT));

        buckets.add(roseAndChamomileBucket);
        buckets.add(tulipAndRoseBucket);

        return buckets;
    }

    @Test
    public void testSearchByFlowerType() {
        // Search for ROSE flower packs
        List<FlowerPack> result = store.searchFlowerBucket(
            FlowerType.ROSE, null, 0, MAX_PRICE, MIN_SEPAL_LENGTH, MAX_SEPAL_LENGTH
        );

        // Expecting 2 packs with ROSE
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.stream().allMatch(pack ->
            pack.getFlower().getFlowerType() == FlowerType.ROSE));
    }

    @Test
    public void testSearchByColor() {
        // Search for RED flowers
        List<FlowerPack> result = store.searchFlowerBucket(
            null, FlowerColor.RED, 0, MAX_PRICE, MIN_SEPAL_LENGTH, MAX_SEPAL_LENGTH
        );

        // Expecting 2 packs with RED flowers
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.stream().allMatch(pack ->
            pack.getFlower().getColor().equals("#FF0000")));
    }

    @Test
    public void testSearchByPriceRange() {
        // Search for flowers with prices between 20 and 40
        List<FlowerPack> result = store.searchFlowerBucket(
            null, null, LOW_PRICE, HIGH_PRICE, MIN_SEPAL_LENGTH, MAX_SEPAL_LENGTH
        );

        // Expecting 3 packs within this price range
        Assertions.assertEquals(3, result.size());
        Assertions.assertTrue(result.stream().allMatch(pack ->
            pack.getFlower().getPrice() >= LOW_PRICE && pack.getFlower().getPrice() <= HIGH_PRICE));
    }

    @Test
    public void testSearchBySepalLengthRange() {
        // Search for flowers with sepal lengths between 8 and 10
        List<FlowerPack> result = store.searchFlowerBucket(
            null, null, 0, MAX_PRICE, SEPAL_LENGTH_SMALL, 10
        );

        // Expecting 3 packs with sepal lengths in this range
        Assertions.assertEquals(3, result.size());
        Assertions.assertTrue(result.stream().allMatch(pack ->
            pack.getFlower().getSepalLength() >= SEPAL_LENGTH_SMALL && pack.getFlower().getSepalLength() <= 10));
    }

    @Test
    public void testSearchWithMultipleCriteria() {
        // Search for RED ROSE flowers with prices between 30 and 40
        List<FlowerPack> result = store.searchFlowerBucket(
            FlowerType.ROSE, FlowerColor.RED, PRICE_ROSE, HIGH_PRICE, MIN_SEPAL_LENGTH, MAX_SEPAL_LENGTH
        );

        // Expecting 2 packs that match all the criteria
        Assertions.assertEquals(2, result.size());
        FlowerPack pack = result.get(0);
        Assertions.assertEquals(FlowerType.ROSE, pack.getFlower().getFlowerType());
        Assertions.assertEquals("#FF0000", pack.getFlower().getColor());
        Assertions.assertTrue(pack.getFlower().getPrice() >= PRICE_ROSE && pack.getFlower().getPrice() <= HIGH_PRICE);
    }
}