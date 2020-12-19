package com.datngo.repository;

import com.datngo.domain.ThongBao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThongBaoRepository extends JpaRepository<ThongBao, Long> {

    List<ThongBao> findByNguoiDungId(Long id);
}
