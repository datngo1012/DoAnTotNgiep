package com.datngo.service.dto;

import java.time.LocalDateTime;

public class DonHangDTO {
    private Long id;

    private String tenSanPham;

    private Long soLuong;

    private String trangThai;

    private long soTien;

    private Long soTienDangThieu;

    private Long nguoiDungId;

    private LocalDateTime ngayMua;

    private String diaChi;

    private String xaPhuong;

    private String quanHuyen;

    private String tinhThanh;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public Long getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Long soLuong) {
        this.soLuong = soLuong;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public long getSoTien() {
        return soTien;
    }

    public void setSoTien(long soTien) {
        this.soTien = soTien;
    }

    public Long getSoTienDangThieu() {
        return soTienDangThieu;
    }

    public void setSoTienDangThieu(Long soTienDangThieu) {
        this.soTienDangThieu = soTienDangThieu;
    }

    public Long getNguoiDungId() {
        return nguoiDungId;
    }

    public void setNguoiDungId(Long nguoiDungId) {
        this.nguoiDungId = nguoiDungId;
    }

    public LocalDateTime getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(LocalDateTime ngayMua) {
        this.ngayMua = ngayMua;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getXaPhuong() {
        return xaPhuong;
    }

    public void setXaPhuong(String xaPhuong) {
        this.xaPhuong = xaPhuong;
    }

    public String getQuanHuyen() {
        return quanHuyen;
    }

    public void setQuanHuyen(String quanHuyen) {
        this.quanHuyen = quanHuyen;
    }

    public String getTinhThanh() {
        return tinhThanh;
    }

    public void setTinhThanh(String tinhThanh) {
        this.tinhThanh = tinhThanh;
    }

    public DonHangDTO() {
    }

    public DonHangDTO(Long id, String tenSanPham, Long soLuong, String trangThai, long soTien, Long soTienDangThieu,
                      Long nguoiDungId, String diaChi, String xaPhuong, String quanHuyen) {
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
        this.soTien = soTien;
        this.soTienDangThieu = soTienDangThieu;
        this.nguoiDungId = nguoiDungId;
        this.ngayMua = ngayMua;
        this.diaChi = diaChi;
        this.xaPhuong = xaPhuong;
        this.quanHuyen = quanHuyen;
        this.tinhThanh = tinhThanh;
    }
}
