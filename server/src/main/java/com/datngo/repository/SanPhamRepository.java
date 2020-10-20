package com.datngo.repository;

import com.datngo.domain.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamRepository  extends JpaRepository<SanPham, Long> {
}
