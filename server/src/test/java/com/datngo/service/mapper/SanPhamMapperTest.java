package com.datngo.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class SanPhamMapperTest {

    private SanPhamMapper sanPhamMapper;

    @BeforeEach
    public void setUp() {
        sanPhamMapper = new SanPhamMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(sanPhamMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(sanPhamMapper.fromId(null)).isNull();
    }
}
