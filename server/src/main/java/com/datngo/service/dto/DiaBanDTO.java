package com.datngo.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.datngo.domain.DiaBan} entity.
 */
public class DiaBanDTO implements Serializable {

    private Long id;

    private String ma;

    private String ten;

    private String cap;

    private String maDiaBanCha;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getMaDiaBanCha() {
        return maDiaBanCha;
    }

    public void setMaDiaBanCha(String maDiaBanCha) {
        this.maDiaBanCha = maDiaBanCha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DiaBanDTO diaBanDTO = (DiaBanDTO) o;
        if (diaBanDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), diaBanDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DiaBanDTO{" +
            "id=" + getId() +
            ", ma='" + getMa() + "'" +
            ", ten='" + getTen() + "'" +
            ", cap='" + getCap() + "'" +
            ", maDiaBanCha='" + getMaDiaBanCha() + "'" +
            "}";
    }
}
