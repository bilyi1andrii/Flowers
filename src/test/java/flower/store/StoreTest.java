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

        FlowerBucket bucket1 = new FlowerBucket();
        bucket1.addFlowerPack(new FlowerPack(new Flower(FlowerColor.RED,
        10, 30.0, FlowerType.ROSE), 5));
        bucket1.addFlowerPack(new FlowerPack(new Flower(FlowerColor.YELLOW,
        8, 20.0, FlowerType.CHAMOMILE), 10));

        FlowerBucket bucket2 = new FlowerBucket();
        bucket2.addFlowerPack(new FlowerPack(new Flower(FlowerColor.WHITE,
        12, 50.0, FlowerType.TULIP), 7));
        bucket2.addFlowerPack(new FlowerPack(new Flower(FlowerColor.RED,
        9, 40.0, FlowerType.ROSE), 3));

        buckets.add(bucket1);
        buckets.add(bucket2);

        return buckets;
    }

    @Test
    public void testSearchByFlowerCountRange() {
        List<FlowerPack> result = store.searchFlowerBucket(
            null, null, 0, 100,
            0, 100, 3, 7
        );

        Assertions.assertEquals(3, result.size());
        Assertions.assertTrue(result.stream().allMatch(pack ->
        pack.getCount() >= 3 && pack.getCount() <= 7));
    }

    @Test
    public void testSearchWithMultipleCriteria() {
        List<FlowerPack> result = store.searchFlowerBucket(
            FlowerType.ROSE, FlowerColor.RED, 30, 40,
            0, 100, 3, 5
        );
        Assertions.assertEquals(2, result.size());
        FlowerPack pack = result.get(0);
        Assertions.assertEquals(FlowerType.ROSE,
        pack.getFlower().getFlowerType());
        Assertions.assertEquals("#FF0000",
        pack.getFlower().getColor());
        Assertions.assertTrue(pack.getFlower().getPrice() >= 30 &&
        pack.getFlower().getPrice() <= 40);
        Assertions.assertTrue(pack.getCount() >= 3 &&
        pack.getCount() <= 5);
    }
    @Test
    public void testSearchByPriceRange() {
        List<FlowerPack> result = store.searchFlowerBucket(
            null, null, 20,
            40, 0, 100,
            0, 100
        );
        Assertions.assertEquals(3, result.size());
        Assertions.assertTrue(result.stream().allMatch(pack ->
        pack.getFlower().getPrice() >= 20 &&
        pack.getFlower().getPrice() <= 40));
    }

    @Test
    public void testSearchBySepalLengthRange() {
        List<FlowerPack> result = store.searchFlowerBucket(
            null, null, 0,
            100, 8, 10,
            0, 100
        );

        Assertions.assertEquals(3, result.size());
        Assertions.assertTrue(result.stream().allMatch(pack ->
        pack.getFlower().getSepalLength() >= 8 &&
        pack.getFlower().getSepalLength() <= 10));
    }

    @Test
    public void testSearchByFlowerType() {
        List<FlowerPack> result = store.searchFlowerBucket(
            FlowerType.ROSE, null, 0,
            100, 0, 100,
            0, 100
        );

        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.stream().allMatch(pack ->
        pack.getFlower().getFlowerType() == FlowerType.ROSE));
    }

}