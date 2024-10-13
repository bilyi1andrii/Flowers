package flower.store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class StoreTest {
    private Store store;

    @BeforeEach
    public void setUp() {
        store = new Store();
        store.setFlowerBuckets(createSampleBuckets());
    }

    private List<FlowerBucket> createSampleBuckets() {
        List<FlowerBucket> buckets = new ArrayList<>();

        // Bucket 1: Roses and Chamomiles
        FlowerBucket bucket1 = new FlowerBucket();
        bucket1.addFlowerPack(new FlowerPack(new Flower(FlowerColor.RED, 10, 30.0, FlowerType.ROSE), 5));
        bucket1.addFlowerPack(new FlowerPack(new Flower(FlowerColor.YELLOW, 8, 20.0, FlowerType.CHAMOMILE), 10));

        // Bucket 2: Tulips and Roses
        FlowerBucket bucket2 = new FlowerBucket();
        bucket2.addFlowerPack(new FlowerPack(new Flower(FlowerColor.WHITE, 12, 50.0, FlowerType.TULIP), 7));
        bucket2.addFlowerPack(new FlowerPack(new Flower(FlowerColor.RED, 9, 40.0, FlowerType.ROSE), 3));

        buckets.add(bucket1);
        buckets.add(bucket2);

        return buckets;
    }

    @Test
    public void testSearchByFlowerType() {
        // Search for ROSE flower packs
        List<FlowerPack> result = store.searchFlowerBucket(
            FlowerType.ROSE, null, 0, 100, 0, 100
        );

        // Expecting 2 packs with ROSE
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.stream().allMatch(pack -> pack.getFlower().getFlowerType() == FlowerType.ROSE));
    }

    @Test
    public void testSearchByColor() {
        // Search for RED flowers
        List<FlowerPack> result = store.searchFlowerBucket(
            null, FlowerColor.RED, 0, 100, 0, 100
        );

        // Expecting 2 packs with RED flowers
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.stream().allMatch(pack -> pack.getFlower().getColor().equals("#FF0000")));
    }

    @Test
    public void testSearchByPriceRange() {
        // Search for flowers with prices between 20 and 40
        List<FlowerPack> result = store.searchFlowerBucket(
            null, null, 20, 40, 0, 100
        );

        // Expecting 3 packs within this price range
        Assertions.assertEquals(3, result.size());
        Assertions.assertTrue(result.stream().allMatch(pack -> pack.getFlower().getPrice() >= 20 && pack.getFlower().getPrice() <= 40));
    }

    @Test
    public void testSearchBySepalLengthRange() {
        // Search for flowers with sepal lengths between 8 and 10
        List<FlowerPack> result = store.searchFlowerBucket(
            null, null, 0, 100, 8, 10
        );

        // Expecting 3 packs with sepal lengths in this range
        Assertions.assertEquals(3, result.size());
        Assertions.assertTrue(result.stream().allMatch(pack -> pack.getFlower().getSepalLength() >= 8 && pack.getFlower().getSepalLength() <= 10));
    }

    @Test
    public void testSearchWithMultipleCriteria() {
        // Search for RED ROSE flowers with prices between 30 and 40
        List<FlowerPack> result = store.searchFlowerBucket(
            FlowerType.ROSE, FlowerColor.RED, 30, 40, 0, 100
        );

        // Expecting 1 pack that matches all the criteria
        Assertions.assertEquals(2, result.size());
        FlowerPack pack = result.get(0);
        Assertions.assertEquals(FlowerType.ROSE, pack.getFlower().getFlowerType());
        Assertions.assertEquals("#FF0000", pack.getFlower().getColor());
        Assertions.assertTrue(pack.getFlower().getPrice() >= 30 && pack.getFlower().getPrice() <= 40);
    }
}