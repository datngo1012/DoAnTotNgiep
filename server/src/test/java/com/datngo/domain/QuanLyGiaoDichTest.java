package com.datngo.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.datngo.web.rest.TestUtil;

public class QuanLyGiaoDichTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuanLyGiaoDich.class);
        QuanLyGiaoDich quanLyGiaoDich1 = new QuanLyGiaoDich();
        quanLyGiaoDich1.setId(1L);
        QuanLyGiaoDich quanLyGiaoDich2 = new QuanLyGiaoDich();
        quanLyGiaoDich2.setId(quanLyGiaoDich1.getId());
        assertThat(quanLyGiaoDich1).isEqualTo(quanLyGiaoDich2);
        quanLyGiaoDich2.setId(2L);
        assertThat(quanLyGiaoDich1).isNotEqualTo(quanLyGiaoDich2);
        quanLyGiaoDich1.setId(null);
        assertThat(quanLyGiaoDich1).isNotEqualTo(quanLyGiaoDich2);
    }
}
