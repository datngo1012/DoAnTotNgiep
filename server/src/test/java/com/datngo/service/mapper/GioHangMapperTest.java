package com.datngo.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class GioHangMapperTest {

    private GioHangMapper gioHangMapper;

    @BeforeEach
    public void setUp() {
        gioHangMapper = new GioHangMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(gioHangMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(gioHangMapper.fromId(null)).isNull();
    }
}
