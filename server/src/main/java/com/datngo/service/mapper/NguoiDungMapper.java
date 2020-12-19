package com.datngo.service.mapper;


import com.datngo.domain.*;
import com.datngo.service.dto.NguoiDungDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link NguoiDung} and its DTO {@link NguoiDungDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface NguoiDungMapper extends EntityMapper<NguoiDungDTO, NguoiDung> {

    @Mapping(source = "user.id", target = "userId")
    NguoiDungDTO toDto(NguoiDung nguoiDung);

    @Mapping(source = "userId", target = "user")
    NguoiDung toEntity(NguoiDungDTO nguoiDungDTO);

    default NguoiDung fromId(Long id) {
        if (id == null) {
            return null;
        }
        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.setId(id);
        return nguoiDung;
    }
}
