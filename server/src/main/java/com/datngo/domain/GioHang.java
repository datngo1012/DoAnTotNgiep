package com.datngo.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A GioHang.
 */
@Entity
@Table(name = "gio_hang")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class GioHang implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @OneToOne
    @JoinColumn(unique = true)
    private NguoiDung nguoiDung;

    @OneToMany(mappedBy = "gioHang")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ChiTietSanPham> chiTietSanPhams = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public GioHang nguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
        return this;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public Set<ChiTietSanPham> getChiTietSanPhams() {
        return chiTietSanPhams;
    }

    public GioHang chiTietSanPhams(Set<ChiTietSanPham> chiTietSanPhams) {
        this.chiTietSanPhams = chiTietSanPhams;
        return this;
    }

    public GioHang addChiTietSanPham(ChiTietSanPham chiTietSanPham) {
        this.chiTietSanPhams.add(chiTietSanPham);
        //chiTietSanPham.setGioHang(this);
        return this;
    }

    public GioHang removeChiTietSanPham(ChiTietSanPham chiTietSanPham) {
        this.chiTietSanPhams.remove(chiTietSanPham);
        //chiTietSanPham.setGioHang(null);
        return this;
    }

    public void setChiTietSanPhams(Set<ChiTietSanPham> chiTietSanPhams) {
        this.chiTietSanPhams = chiTietSanPhams;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GioHang)) {
            return false;
        }
        return id != null && id.equals(((GioHang) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "GioHang{" +
            "id=" + getId() +
            "}";
    }
}
