package com.datngo.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.datngo.domain.ChiTietSanPham} entity.
 */
public class ChiTietSanPhamDTO implements Serializable {

    private Long id;

    private Integer soLuong;

    private String propetiesName;


    private Long sanPhamId;

    private Long gioHangId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public String getPropetiesName() {
        return propetiesName;
    }

    public void setPropetiesName(String propetiesName) {
        this.propetiesName = propetiesName;
    }

    public Long getSanPhamId() {
        return sanPhamId;
    }

    public void setSanPhamId(Long sanPhamId) {
        this.sanPhamId = sanPhamId;
    }

    public Long getGioHangId() {
        return gioHangId;
    }

    public void setGioHangId(Long gioHangId) {
        this.gioHangId = gioHangId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ChiTietSanPhamDTO chiTietSanPhamDTO = (ChiTietSanPhamDTO) o;
        if (chiTietSanPhamDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), chiTietSanPhamDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ChiTietSanPhamDTO{" +
            "id=" + getId() +
            ", soLuong=" + getSoLuong() +
            ", propetiesName='" + getPropetiesName() + "'" +
            ", sanPhamId=" + getSanPhamId() +
            ", gioHangId=" + getGioHangId() +
            "}";
    }
}
