package com.datngo.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ChiTietSanPhamMapperTest {

    private ChiTietSanPhamMapper chiTietSanPhamMapper;

    @BeforeEach
    public void setUp() {
        chiTietSanPhamMapper = new ChiTietSanPhamMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(chiTietSanPhamMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(chiTietSanPhamMapper.fromId(null)).isNull();
    }
}
