package com.datngo.repository;

import com.datngo.domain.SanPham;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SanPham entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Long> {

}
