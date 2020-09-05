package com.datngo.repository;

import com.datngo.domain.ChiTietSanPham;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ChiTietSanPham entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham, Long> {

}
