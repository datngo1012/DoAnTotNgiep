package com.datngo.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A NguoiDung.
 */
@Entity
@Table(name = "nguoi_dung")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NguoiDung implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ho_ten")
    private String hoTen;

    @Column(name = "sdt")
    private String sdt;

    @Column(name = "ngay_sinh")
    private Integer ngaySinh;

    @Column(name = "thang_sinh")
    private Integer thangSinh;

    @Column(name = "nam_sinh")
    private Integer namSinh;

    @Column(name = "gioi_tinh")
    private String gioiTinh;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "so_du")
    private Long soDu;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @Column(name = "ngay_tao")
    private LocalDate ngayTao;

    @Column(name = "ngay_sua")
    private LocalDate ngaySua;

    @Column(name = "email")
    private String email;

    @Column(name = "tinh_thanh")
    private String tinhThanh;

    @Column(name = "quan_huyen")
    private String quanHuyen;

    @Column(name = "xa_phuong")
    private String xaPhuong;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public NguoiDung hoTen(String hoTen) {
        this.hoTen = hoTen;
        return this;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public NguoiDung sdt(String sdt) {
        this.sdt = sdt;
        return this;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Integer getNgaySinh() {
        return ngaySinh;
    }

    public NguoiDung ngaySinh(Integer ngaySinh) {
        this.ngaySinh = ngaySinh;
        return this;
    }

    public void setNgaySinh(Integer ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public Integer getThangSinh() {
        return thangSinh;
    }

    public NguoiDung thangSinh(Integer thangSinh) {
        this.thangSinh = thangSinh;
        return this;
    }

    public void setThangSinh(Integer thangSinh) {
        this.thangSinh = thangSinh;
    }

    public Integer getNamSinh() {
        return namSinh;
    }

    public NguoiDung namSinh(Integer namSinh) {
        this.namSinh = namSinh;
        return this;
    }

    public void setNamSinh(Integer namSinh) {
        this.namSinh = namSinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public NguoiDung gioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
        return this;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public NguoiDung diaChi(String diaChi) {
        this.diaChi = diaChi;
        return this;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Long getSoDu() {
        return soDu;
    }

    public NguoiDung soDu(Long soDu) {
        this.soDu = soDu;
        return this;
    }

    public void setSoDu(Long soDu) {
        this.soDu = soDu;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public NguoiDung trangThai(Integer trangThai) {
        this.trangThai = trangThai;
        return this;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public LocalDate getNgayTao() {
        return ngayTao;
    }

    public NguoiDung ngayTao(LocalDate ngayTao) {
        this.ngayTao = ngayTao;
        return this;
    }

    public void setNgayTao(LocalDate ngayTao) {
        this.ngayTao = ngayTao;
    }

    public LocalDate getNgaySua() {
        return ngaySua;
    }

    public NguoiDung ngaySua(LocalDate ngaySua) {
        this.ngaySua = ngaySua;
        return this;
    }

    public void setNgaySua(LocalDate ngaySua) {
        this.ngaySua = ngaySua;
    }

    public String getEmail() {
        return email;
    }

    public NguoiDung email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTinhThanh() {
        return tinhThanh;
    }

    public NguoiDung tinhThanh(String tinhThanh) {
        this.tinhThanh = tinhThanh;
        return this;
    }

    public void setTinhThanh(String tinhThanh) {
        this.tinhThanh = tinhThanh;
    }

    public String getQuanHuyen() {
        return quanHuyen;
    }

    public NguoiDung quanHuyen(String quanHuyen) {
        this.quanHuyen = quanHuyen;
        return this;
    }

    public void setQuanHuyen(String quanHuyen) {
        this.quanHuyen = quanHuyen;
    }

    public String getXaPhuong() {
        return xaPhuong;
    }

    public NguoiDung xaPhuong(String xaPhuong) {
        this.xaPhuong = xaPhuong;
        return this;
    }

    public void setXaPhuong(String xaPhuong) {
        this.xaPhuong = xaPhuong;
    }

    public User getUser() {
        return user;
    }

    public NguoiDung user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NguoiDung)) {
            return false;
        }
        return id != null && id.equals(((NguoiDung) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NguoiDung{" +
            "id=" + getId() +
            ", hoTen='" + getHoTen() + "'" +
            ", sdt='" + getSdt() + "'" +
            ", ngaySinh=" + getNgaySinh() +
            ", thangSinh=" + getThangSinh() +
            ", namSinh=" + getNamSinh() +
            ", gioiTinh='" + getGioiTinh() + "'" +
            ", diaChi='" + getDiaChi() + "'" +
            ", soDu=" + getSoDu() +
            ", trangThai=" + getTrangThai() +
            ", ngayTao='" + getNgayTao() + "'" +
            ", ngaySua='" + getNgaySua() + "'" +
            ", email='" + getEmail() + "'" +
            ", tinhThanh='" + getTinhThanh() + "'" +
            ", quanHuyen='" + getQuanHuyen() + "'" +
            ", xaPhuong='" + getXaPhuong() + "'" +
            "}";
    }
}
