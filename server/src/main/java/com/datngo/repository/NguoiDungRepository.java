package com.datngo.repository;

import com.datngo.domain.NguoiDung;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NguoiDung entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Long> {

}
