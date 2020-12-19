package com.datngo.repository;

import com.datngo.domain.QuanLyGiaoDich;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the QuanLyGiaoDich entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuanLyGiaoDichRepository extends JpaRepository<QuanLyGiaoDich, Long> {

}
