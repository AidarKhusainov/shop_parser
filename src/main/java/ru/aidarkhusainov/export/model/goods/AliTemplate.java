package ru.aidarkhusainov.export.model.goods;

import java.util.Arrays;
import java.util.List;

public class AliTemplate {
    private String minPrice;
    private String maxPrice;
    private String currency;
    private String detailUrl;
    private String productId;
    private String sellerId;
    private String productTitle;
    private String productImage;
    private String originalPrice;
    private String discount;
    private String productAvgStar;

    public AliTemplate() {
    }

    public AliTemplate(String minPrice, String maxPrice,
                       String currency, String detailUrl,
                       String productId, String sellerId,
                       String productTitle, String productImage,
                       String originalPrice, String discount,
                       String productAvgStar) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.currency = currency;
        this.detailUrl = detailUrl;
        this.productId = productId;
        this.sellerId = sellerId;
        this.productTitle = productTitle;
        this.productImage = productImage;
        this.originalPrice = originalPrice;
        this.discount = discount;
        this.productAvgStar = productAvgStar;
    }

    public List<String> getFields() {
        return Arrays.asList(
                "minPrice",
                "maxPrice",
                "currency",
                "detailUrl",
                "productId",
                "sellerId",
                "productTitle",
                "productImage",
                "originalPrice",
                "discount",
                "productAvgStar"
        );
    }

}
