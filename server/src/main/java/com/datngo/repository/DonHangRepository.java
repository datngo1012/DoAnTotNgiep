package com.datngo.repository;

import com.datngo.domain.DonHang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonHangRepository extends JpaRepository<DonHang, Long> {
    List<DonHang> findByNguoiDungId(Long nguoiDungId);
}
