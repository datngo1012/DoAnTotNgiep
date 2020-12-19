package com.datngo.repository;

import com.datngo.domain.DiaBan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DiaBan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DiaBanRepository extends JpaRepository<DiaBan, Long> {

}
