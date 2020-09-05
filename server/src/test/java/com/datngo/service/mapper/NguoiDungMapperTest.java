package com.datngo.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class NguoiDungMapperTest {

    private NguoiDungMapper nguoiDungMapper;

    @BeforeEach
    public void setUp() {
        nguoiDungMapper = new NguoiDungMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(nguoiDungMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(nguoiDungMapper.fromId(null)).isNull();
    }
}
