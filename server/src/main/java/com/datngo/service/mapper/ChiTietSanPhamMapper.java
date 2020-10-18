package com.datngo.service.mapper;

import com.datngo.domain.*;
import com.datngo.service.dto.ChiTietSanPhamDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ChiTietSanPham} and its DTO {@link ChiTietSanPhamDTO}.
 */
@Mapper(componentModel = "spring", uses = {SanPhamMapper.class, GioHangMapper.class})
public interface ChiTietSanPhamMapper extends EntityMapper<ChiTietSanPhamDTO, ChiTietSanPham> {

    @Mapping(source = "sanPham.id", target = "sanPhamId")
    @Mapping(source = "gioHang.id", target = "gioHangId")
    ChiTietSanPhamDTO toDto(ChiTietSanPham chiTietSanPham);

    @Mapping(source = "sanPhamId", target = "sanPham")
    @Mapping(source = "gioHangId", target = "gioHang")
    ChiTietSanPham toEntity(ChiTietSanPhamDTO chiTietSanPhamDTO);

    default ChiTietSanPham fromId(Long id) {
        if (id == null) {
            return null;
        }
        ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
        chiTietSanPham.setId(id);
        return chiTietSanPham;
    }
}
