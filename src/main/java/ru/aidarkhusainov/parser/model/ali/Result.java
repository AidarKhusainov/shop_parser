package ru.aidarkhusainov.parser.model.ali;

public class Result {
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

    @Override
    public String toString() {
        return "Result{" +
                "minPrice='" + minPrice + '\'' +
                ", maxPrice='" + maxPrice + '\'' +
                ", productDetailUrl='" + productDetailUrl + '\'' +
                ", productId='" + productId + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", productTitle='" + productTitle + '\'' +
                ", productImage='" + productImage + '\'' +
                ", originalPrice='" + originalPrice + '\'' +
                ", discount='" + discount + '\'' +
                ", productAvgStar='" + productAvgStar + '\'' +
                '}';
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getProductDetailUrl() {
        return productDetailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.productDetailUrl = detailUrl;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getProductAvgStar() {
        return productAvgStar;
    }

    public void setProductAvgStar(String productAvgStar) {
        this.productAvgStar = productAvgStar;
    }
}
