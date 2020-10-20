package com.datngo.service.dto;

public class SanPhamItemInput {
    private Long itemId;
    private String itemImage;
    private String itemLink;
    private String itemName;
    private String itemPriceNDT;
    private String propetiesId;
    private String note;
    private String propetiesImage;
    private String propetiesName;
    private String propetiesType;
    private Integer quantity;
    private String saleLocation;
    private Long stock ;
    private Long totalAmountNDT;
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemLink() {
        return itemLink;
    }

    public void setItemLink(String itemLink) {
        this.itemLink = itemLink;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPriceNDT() {
        return itemPriceNDT;
    }

    public void setItemPriceNDT(String itemPriceNDT) {
        this.itemPriceNDT = itemPriceNDT;
    }

    public String getPropetiesId() {
        return propetiesId;
    }

    public void setPropetiesId(String propetiesId) {
        this.propetiesId = propetiesId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPropetiesImage() {
        return propetiesImage;
    }

    public void setPropetiesImage(String propetiesImage) {
        this.propetiesImage = propetiesImage;
    }

    public String getPropetiesName() {
        return propetiesName;
    }

    public void setPropetiesName(String propetiesName) {
        this.propetiesName = propetiesName;
    }

    public String getPropetiesType() {
        return propetiesType;
    }

    public void setPropetiesType(String propetiesType) {
        this.propetiesType = propetiesType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSaleLocation() {
        return saleLocation;
    }

    public void setSaleLocation(String saleLocation) {
        this.saleLocation = saleLocation;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Long getTotalAmountNDT() {
        return totalAmountNDT;
    }

    public void setTotalAmountNDT(Long totalAmountNDT) {
        this.totalAmountNDT = totalAmountNDT;
    }
}
