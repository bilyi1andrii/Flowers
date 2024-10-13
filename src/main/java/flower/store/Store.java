package flower.store;

import java.util.ArrayList;
import java.util.List;

import lombok.Setter;

@Setter
public class Store {
    private List<FlowerBucket> flowerBuckets;

    public List<FlowerPack> searchFlowerBucket(
    FlowerType flowerType,
    FlowerColor color,
    double minPrice,
    double maxPrice,
    int minSepalLength,
    int maxSepalLength
) {
    List<FlowerPack> matchingPacks = new ArrayList<>();

    for (FlowerBucket flowerBucket : flowerBuckets) {
        for (FlowerPack flowerPack : flowerBucket.getFlowerPacks()) {
            Flower flower = flowerPack.getFlower();
            if ((flowerType == null || flower.getFlowerType() == flowerType)
            && (color == null || flower.getColor().equals(color.toString()))
            && (flower.getPrice() >= minPrice
            && flower.getPrice() <= maxPrice)
            && (flower.getSepalLength() >= minSepalLength
                && flower.getSepalLength() <= maxSepalLength)) {
                matchingPacks.add(flowerPack);
            }
        }
    }
    return matchingPacks;
}
}
