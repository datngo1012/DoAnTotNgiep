package com.datngo.domain;
// Generated Oct 4, 2018 9:29:18 AM by Hibernate Tools 5.2.11.Final

import javax.persistence.*;
import java.util.Date;


/**
 * DanhmucDiaBan generated by hbm2java
 */
@Entity
@Table(name = "danhmuc_dia_ban", schema = "public")
public class DanhmucDiaBan implements java.io.Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String ma;
	private String ten;
	private String tenTiengAnh;
	private String cap;
	private Integer danSo;
	private String nguoiTao;
	private Date ngayTao;
	private Date ngaySua;
	private String nguoiSua;
	private short daXoa;
	private String  maDiaBanCha;
	private Date ngayDongBo;

	public DanhmucDiaBan() {
	}

	public DanhmucDiaBan(int id, short daXoa, String maDiaBanCha) {
		this.id = id;
		this.daXoa = daXoa;
		this.maDiaBanCha = maDiaBanCha;
	}

	public DanhmucDiaBan(int id, String ma, String ten, String tenTiengAnh, String cap, Integer danSo, String nguoiTao,
                         Date ngayTao, Date ngaySua, String nguoiSua, short daXoa, String maDiaBanCha) {
		this.id = id;
		this.ma = ma;
		this.ten = ten;
		this.tenTiengAnh = tenTiengAnh;
		this.cap = cap;
		this.danSo = danSo;
		this.nguoiTao = nguoiTao;
		this.ngayTao = ngayTao;
		this.ngaySua = ngaySua;
		this.nguoiSua = nguoiSua;
		this.daXoa = daXoa;
		this.maDiaBanCha = maDiaBanCha;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "ma")
	public String getMa() {
		return this.ma;
	}

	public void setMa(String ma) {
		this.ma = ma;
	}

	@Column(name = "ten")
	public String getTen() {
		return this.ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	@Column(name = "ten_tieng_anh")
	public String getTenTiengAnh() {
		return this.tenTiengAnh;
	}

	public void setTenTiengAnh(String tenTiengAnh) {
		this.tenTiengAnh = tenTiengAnh;
	}

	@Column(name = "cap")
	public String getCap() {
		return this.cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	@Column(name = "dan_so")
	public Integer getDanSo() {
		return this.danSo;
	}

	public void setDanSo(Integer danSo) {
		this.danSo = danSo;
	}

	@Column(name = "nguoi_tao")
	public String getNguoiTao() {
		return this.nguoiTao;
	}

	public void setNguoiTao(String nguoiTao) {
		this.nguoiTao = nguoiTao;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ngay_tao", length = 29)
	public Date getNgayTao() {
		return this.ngayTao;
	}

	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ngay_sua", length = 29)
	public Date getNgaySua() {
		return this.ngaySua;
	}

	public void setNgaySua(Date ngaySua) {
		this.ngaySua = ngaySua;
	}

	@Column(name = "nguoi_sua")
	public String getNguoiSua() {
		return this.nguoiSua;
	}

	public void setNguoiSua(String nguoiSua) {
		this.nguoiSua = nguoiSua;
	}

	@Column(name = "da_xoa", nullable = false)
	public short getDaXoa() {
		return this.daXoa;
	}

	public void setDaXoa(short daXoa) {
		this.daXoa = daXoa;
	}

	@Column(name = "ma_dia_ban_cha", nullable = false)
	public String getMaDiaBanCha() {
		return maDiaBanCha;
	}

	public void setMaDiaBanCha(String maDiaBanCha) {
		this.maDiaBanCha = maDiaBanCha;
	}

	@Column(name = "ngay_dong_bo", nullable = false)
	public Date getNgayDongBo() {
		return ngayDongBo;
	}

	public void setNgayDongBo(Date ngayDongBo) {
		this.ngayDongBo = ngayDongBo;
	}
}