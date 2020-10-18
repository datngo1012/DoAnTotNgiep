package com.datngo.repository;

import com.datngo.domain.GioHang;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Spring Data  repository for the GioHang entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GioHangRepository extends JpaRepository<GioHang, Long> {

    Optional<GioHang> findByNguoiDungId(Long nguoiDungId);
}
