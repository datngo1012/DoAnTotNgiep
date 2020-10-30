package com.datngo.repository;

import com.datngo.domain.ChiTietSanPham;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the ChiTietSanPham entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham, Long> {

    @Query("from ChiTietSanPham ctsp inner join GioHang gh on gh.id = ctsp.gioHang.id inner join NguoiDung nd on nd.id = gh.nguoiDung.id where nd.id = ?1")
    List<ChiTietSanPham> findListChiTietSanPhamByNguoiDungId(Long nguoiDungId);
}
