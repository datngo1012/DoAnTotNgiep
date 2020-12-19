package com.datngo.service.mapper;

import com.datngo.domain.*;
import com.datngo.service.dto.SanPhamDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SanPham} and its DTO {@link SanPhamDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SanPhamMapper extends EntityMapper<SanPhamDTO, SanPham> {



    default SanPham fromId(Long id) {
        if (id == null) {
            return null;
        }
        SanPham sanPham = new SanPham();
        sanPham.setId(id);
        return sanPham;
    }
}
