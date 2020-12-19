package com.datngo.service.mapper;

import com.datngo.domain.*;
import com.datngo.service.dto.DiaBanDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DiaBan} and its DTO {@link DiaBanDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DiaBanMapper extends EntityMapper<DiaBanDTO, DiaBan> {



    default DiaBan fromId(Long id) {
        if (id == null) {
            return null;
        }
        DiaBan diaBan = new DiaBan();
        diaBan.setId(id);
        return diaBan;
    }
}
