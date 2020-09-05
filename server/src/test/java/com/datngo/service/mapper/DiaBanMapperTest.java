package com.datngo.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DiaBanMapperTest {

    private DiaBanMapper diaBanMapper;

    @BeforeEach
    public void setUp() {
        diaBanMapper = new DiaBanMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(diaBanMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(diaBanMapper.fromId(null)).isNull();
    }
}
