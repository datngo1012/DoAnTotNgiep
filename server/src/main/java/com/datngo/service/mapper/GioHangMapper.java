package com.datngo.service.mapper;

import com.datngo.domain.*;
import com.datngo.service.dto.GioHangDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GioHang} and its DTO {@link GioHangDTO}.
 */
@Mapper(componentModel = "spring", uses = {NguoiDungMapper.class})
public interface GioHangMapper extends EntityMapper<GioHangDTO, GioHang> {

    @Mapping(source = "nguoiDung.id", target = "nguoiDungId")
    GioHangDTO toDto(GioHang gioHang);

    @Mapping(source = "nguoiDungId", target = "nguoiDung")
    GioHang toEntity(GioHangDTO gioHangDTO);

    default GioHang fromId(Long id) {
        if (id == null) {
            return null;
        }
        GioHang gioHang = new GioHang();
        gioHang.setId(id);
        return gioHang;
    }
}
