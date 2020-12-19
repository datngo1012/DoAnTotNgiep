package com.datngo.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.datngo.domain.SanPham} entity.
 */
public class SanPhamDTO implements Serializable {

    private Long id;

    private String image;

    private Boolean isDetailContent;

    private String nam;

    private Long productId;

    private Integer rate;

    private Long repurchaseRate;

    private Long sellPrice;

    private Long sellPriceDisplay;

    private Long startPriceVND;

    private Long stock;

    private String transaction;

    private Boolean transalated;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean isIsDetailContent() {
        return isDetailContent;
    }

    public void setIsDetailContent(Boolean isDetailContent) {
        this.isDetailContent = isDetailContent;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Long getRepurchaseRate() {
        return repurchaseRate;
    }

    public void setRepurchaseRate(Long repurchaseRate) {
        this.repurchaseRate = repurchaseRate;
    }

    public Long getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Long sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Long getSellPriceDisplay() {
        return sellPriceDisplay;
    }

    public void setSellPriceDisplay(Long sellPriceDisplay) {
        this.sellPriceDisplay = sellPriceDisplay;
    }

    public Long getStartPriceVND() {
        return startPriceVND;
    }

    public void setStartPriceVND(Long startPriceVND) {
        this.startPriceVND = startPriceVND;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public Boolean isTransalated() {
        return transalated;
    }

    public void setTransalated(Boolean transalated) {
        this.transalated = transalated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SanPhamDTO sanPhamDTO = (SanPhamDTO) o;
        if (sanPhamDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sanPhamDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SanPhamDTO{" +
            "id=" + getId() +
            ", image='" + getImage() + "'" +
            ", isDetailContent='" + isIsDetailContent() + "'" +
            ", nam='" + getNam() + "'" +
            ", productId=" + getProductId() +
            ", rate=" + getRate() +
            ", repurchaseRate=" + getRepurchaseRate() +
            ", sellPrice=" + getSellPrice() +
            ", sellPriceDisplay=" + getSellPriceDisplay() +
            ", startPriceVND=" + getStartPriceVND() +
            ", stock=" + getStock() +
            ", transaction=" + getTransaction() +
            ", transalated='" + isTransalated() + "'" +
            "}";
    }
}
