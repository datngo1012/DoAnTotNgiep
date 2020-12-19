package com.datngo.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class QuanLyGiaoDichMapperTest {

    private QuanLyGiaoDichMapper quanLyGiaoDichMapper;

    @BeforeEach
    public void setUp() {
        quanLyGiaoDichMapper = new QuanLyGiaoDichMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(quanLyGiaoDichMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(quanLyGiaoDichMapper.fromId(null)).isNull();
    }
}
