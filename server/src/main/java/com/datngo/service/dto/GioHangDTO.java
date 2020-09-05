package com.datngo.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.datngo.domain.GioHang} entity.
 */
public class GioHangDTO implements Serializable {

    private Long id;


    private Long nguoiDungId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNguoiDungId() {
        return nguoiDungId;
    }

    public void setNguoiDungId(Long nguoiDungId) {
        this.nguoiDungId = nguoiDungId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GioHangDTO gioHangDTO = (GioHangDTO) o;
        if (gioHangDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), gioHangDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GioHangDTO{" +
            "id=" + getId() +
            ", nguoiDungId=" + getNguoiDungId() +
            "}";
    }
}
