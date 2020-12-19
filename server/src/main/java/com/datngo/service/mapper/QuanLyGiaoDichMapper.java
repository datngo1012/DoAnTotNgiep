package com.datngo.service.mapper;

import com.datngo.domain.*;
import com.datngo.service.dto.QuanLyGiaoDichDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link QuanLyGiaoDich} and its DTO {@link QuanLyGiaoDichDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QuanLyGiaoDichMapper extends EntityMapper<QuanLyGiaoDichDTO, QuanLyGiaoDich> {



    default QuanLyGiaoDich fromId(Long id) {
        if (id == null) {
            return null;
        }
        QuanLyGiaoDich quanLyGiaoDich = new QuanLyGiaoDich();
        quanLyGiaoDich.setId(id);
        return quanLyGiaoDich;
    }
}
