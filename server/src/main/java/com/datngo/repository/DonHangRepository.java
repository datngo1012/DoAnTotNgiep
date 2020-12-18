package com.datngo.repository;

import com.datngo.domain.DonHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DonHangRepository extends JpaRepository<DonHang, Long> {
    List<DonHang> findByNguoiDungId(Long nguoiDungId);

    @Query(value = "select dh.id, dh.ten_sp , dh.so_luong , dh.so_tien , dh.so_tien_dang_thieu , dh.trang_thai , nd.dia_chi , nd.xa_phuong , nd.quan_huyen , dh.link, nd.tinh_thanh, dh.ngay_mua, dh.nguoi_dung_id, nd.ho_ten from don_hang dh join nguoi_dung nd on dh.nguoi_dung_id = nd.id where dh.trang_thai = 'Đang chờ lấy hàng'", nativeQuery = true)
    List<Object[]> findOrder();
}
