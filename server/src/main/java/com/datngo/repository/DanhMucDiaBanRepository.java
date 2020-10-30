package com.datngo.repository;

import com.datngo.domain.DanhmucDiaBan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DanhMucDiaBanRepository extends JpaRepository<DanhmucDiaBan, Long> {

    @Query("from DanhmucDiaBan ddb where ddb.maDiaBanCha = ?1")
    Optional<List<DanhmucDiaBan>> getDsDiaBanTheoMaCha(String maCha);
}
