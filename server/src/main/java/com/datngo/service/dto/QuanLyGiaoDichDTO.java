package com.datngo.service.dto;
import io.swagger.annotations.ApiModel;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.datngo.domain.QuanLyGiaoDich} entity.
 */
@ApiModel(description = "not an ignored comment")
public class QuanLyGiaoDichDTO implements Serializable {

    private Long id;

    private Long soTienGiaoDich;

    private String loaiGiaoDich;

    private LocalDate ngayGiaoDich;

    private Integer trangThai;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSoTienGiaoDich() {
        return soTienGiaoDich;
    }

    public void setSoTienGiaoDich(Long soTienGiaoDich) {
        this.soTienGiaoDich = soTienGiaoDich;
    }

    public String getLoaiGiaoDich() {
        return loaiGiaoDich;
    }

    public void setLoaiGiaoDich(String loaiGiaoDich) {
        this.loaiGiaoDich = loaiGiaoDich;
    }

    public LocalDate getNgayGiaoDich() {
        return ngayGiaoDich;
    }

    public void setNgayGiaoDich(LocalDate ngayGiaoDich) {
        this.ngayGiaoDich = ngayGiaoDich;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QuanLyGiaoDichDTO quanLyGiaoDichDTO = (QuanLyGiaoDichDTO) o;
        if (quanLyGiaoDichDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quanLyGiaoDichDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuanLyGiaoDichDTO{" +
            "id=" + getId() +
            ", soTienGiaoDich=" + getSoTienGiaoDich() +
            ", loaiGiaoDich='" + getLoaiGiaoDich() + "'" +
            ", ngayGiaoDich='" + getNgayGiaoDich() + "'" +
            ", trangThai=" + getTrangThai() +
            "}";
    }
}
