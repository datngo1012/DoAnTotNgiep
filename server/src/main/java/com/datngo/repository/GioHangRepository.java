package com.datngo.repository;

import com.datngo.domain.GioHang;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GioHang entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GioHangRepository extends JpaRepository<GioHang, Long> {

}
