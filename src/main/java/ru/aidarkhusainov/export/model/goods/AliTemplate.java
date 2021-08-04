package ru.aidarkhusainov.export.model.goods;

import java.util.Arrays;
import java.util.List;

public class AliTemplate {
    private String minPrice;
    private String maxPrice;
    private String productDetailUrl;
    private String productId;
    private String sellerId;
    private String productTitle;
    private String productImage;
    private String originalPrice;
    private String discount;
    private String productAvgStar;


    public AliTemplate() {
    }

    public AliTemplate(String minPrice, String maxPrice, String productDetailUrl, String productId, String sellerId,
                       String productTitle, String productImage, String originalPrice, String discount, String productAvgStar) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.productDetailUrl = productDetailUrl;
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
                "productDetailUrl",
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
