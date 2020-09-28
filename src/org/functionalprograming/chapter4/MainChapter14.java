package org.functionalprograming.chapter4;


import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class MainChapter14 {
    public static void main(String[] args) {

        final List<Asset> assets = Arrays.asList(
                new Asset(Asset.AssetType.BOND, 1000),
                new Asset(Asset.AssetType.BOND, 2000),
                new Asset(Asset.AssetType.STOCK, 3000),
                new Asset(Asset.AssetType.STOCK, 4000)
        );

        System.out.println("Total of all assets: " + totalAssetValues(assets));
        System.out.println("Total of Bounds: " + totalBounds(assets));
        System.out.println("All asserts: " + totalAssetValues(assets, asset -> true));
        System.out.println("Total of bounds2: " + totalAssetValues(assets, asset -> asset.getType() == Asset.AssetType.BOND));
        System.out.println("Total of stocks: " +
                totalAssetValues(assets, asset -> asset.getType() == Asset.AssetType.STOCK));

        /* Designing API FluentMailer */
        FluentMailer.send(mailer ->
                mailer.from("build@agiledeveloper.com")
                        .to("venkats@agiledeveloper.com")
                        .subject("build notification")
                        .body("...much better..."));

    }

    private static int totalAssetValues(final List<Asset> assets) {
        return assets.stream()
                .mapToInt(Asset::getValue)
                .sum();
    }

    private static int totalBounds(final List<Asset> assets) {
        return assets.stream()
                .filter(asset -> asset.getType() == Asset.AssetType.BOND)
                .mapToInt(Asset::getValue)
                .sum();
    }

    private static int totalAssetValues(final List<Asset> assets,
                                        final Predicate<Asset> assetSelector) {
        return assets.stream()
                .filter(assetSelector)
                .mapToInt(Asset::getValue)
                .sum();
    }
}
