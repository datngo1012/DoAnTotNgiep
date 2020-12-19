package com.datngo.service.dto;

import java.util.List;

public class SanPhamInputDTO {
    private Long shopId;
    private String shopLink;
    private String shopName;
    private String website;

    private List<SanPhamItemInput> items;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopLink() {
        return shopLink;
    }

    public void setShopLink(String shopLink) {
        this.shopLink = shopLink;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<SanPhamItemInput> getItems() {
        return items;
    }

    public void setItems(List<SanPhamItemInput> items) {
        this.items = items;
    }
}
