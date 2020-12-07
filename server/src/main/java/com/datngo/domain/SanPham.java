package com.datngo.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A SanPham.
 */
@Entity
@Table(name = "san_pham")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SanPham implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "image")
    private String image;

    @Column(name = "is_detail_content")
    private Boolean isDetailContent;

    @Column(name = "nam")
    private String nam;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "rate")
    private Integer rate;

    @Column(name = "repurchase_rate")
    private Long repurchaseRate;

    @Column(name = "sell_price")
    private Long sellPrice;

    @Column(name = "sell_price_display")
    private Long sellPriceDisplay;

    @Column(name = "start_price_vnd")
    private Long startPriceVND;

    @Column(name = "stock")
    private Long stock;

    @Column(name = "transaction")
    private String transaction;

    @Column(name = "transalated")
    private Boolean transalated;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public SanPham image(String image) {
        this.image = image;
        return this;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean isIsDetailContent() {
        return isDetailContent;
    }

    public SanPham isDetailContent(Boolean isDetailContent) {
        this.isDetailContent = isDetailContent;
        return this;
    }

    public void setIsDetailContent(Boolean isDetailContent) {
        this.isDetailContent = isDetailContent;
    }

    public String getNam() {
        return nam;
    }

    public SanPham nam(String nam) {
        this.nam = nam;
        return this;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public Long getProductId() {
        return productId;
    }

    public SanPham productId(Long productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getRate() {
        return rate;
    }

    public SanPham rate(Integer rate) {
        this.rate = rate;
        return this;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Long getRepurchaseRate() {
        return repurchaseRate;
    }

    public SanPham repurchaseRate(Long repurchaseRate) {
        this.repurchaseRate = repurchaseRate;
        return this;
    }

    public void setRepurchaseRate(Long repurchaseRate) {
        this.repurchaseRate = repurchaseRate;
    }

    public Long getSellPrice() {
        return sellPrice;
    }

    public SanPham sellPrice(Long sellPrice) {
        this.sellPrice = sellPrice;
        return this;
    }

    public void setSellPrice(Long sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Long getSellPriceDisplay() {
        return sellPriceDisplay;
    }

    public SanPham sellPriceDisplay(Long sellPriceDisplay) {
        this.sellPriceDisplay = sellPriceDisplay;
        return this;
    }

    public void setSellPriceDisplay(Long sellPriceDisplay) {
        this.sellPriceDisplay = sellPriceDisplay;
    }

    public Long getStartPriceVND() {
        return startPriceVND;
    }

    public SanPham startPriceVND(Long startPriceVND) {
        this.startPriceVND = startPriceVND;
        return this;
    }

    public void setStartPriceVND(Long startPriceVND) {
        this.startPriceVND = startPriceVND;
    }

    public Long getStock() {
        return stock;
    }

    public SanPham stock(Long stock) {
        this.stock = stock;
        return this;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public String getTransaction() {
        return transaction;
    }

    public SanPham transaction(String transaction) {
        this.transaction = transaction;
        return this;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public Boolean isTransalated() {
        return transalated;
    }

    public SanPham transalated(Boolean transalated) {
        this.transalated = transalated;
        return this;
    }

    public void setTransalated(Boolean transalated) {
        this.transalated = transalated;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SanPham)) {
            return false;
        }
        return id != null && id.equals(((SanPham) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SanPham{" +
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
