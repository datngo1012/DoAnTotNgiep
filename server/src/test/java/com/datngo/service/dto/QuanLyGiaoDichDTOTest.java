package com.datngo.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.datngo.web.rest.TestUtil;

public class QuanLyGiaoDichDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuanLyGiaoDichDTO.class);
        QuanLyGiaoDichDTO quanLyGiaoDichDTO1 = new QuanLyGiaoDichDTO();
        quanLyGiaoDichDTO1.setId(1L);
        QuanLyGiaoDichDTO quanLyGiaoDichDTO2 = new QuanLyGiaoDichDTO();
        assertThat(quanLyGiaoDichDTO1).isNotEqualTo(quanLyGiaoDichDTO2);
        quanLyGiaoDichDTO2.setId(quanLyGiaoDichDTO1.getId());
        assertThat(quanLyGiaoDichDTO1).isEqualTo(quanLyGiaoDichDTO2);
        quanLyGiaoDichDTO2.setId(2L);
        assertThat(quanLyGiaoDichDTO1).isNotEqualTo(quanLyGiaoDichDTO2);
        quanLyGiaoDichDTO1.setId(null);
        assertThat(quanLyGiaoDichDTO1).isNotEqualTo(quanLyGiaoDichDTO2);
    }
}
