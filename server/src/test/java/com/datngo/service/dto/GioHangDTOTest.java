package com.datngo.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.datngo.web.rest.TestUtil;

public class GioHangDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GioHangDTO.class);
        GioHangDTO gioHangDTO1 = new GioHangDTO();
        gioHangDTO1.setId(1L);
        GioHangDTO gioHangDTO2 = new GioHangDTO();
        assertThat(gioHangDTO1).isNotEqualTo(gioHangDTO2);
        gioHangDTO2.setId(gioHangDTO1.getId());
        assertThat(gioHangDTO1).isEqualTo(gioHangDTO2);
        gioHangDTO2.setId(2L);
        assertThat(gioHangDTO1).isNotEqualTo(gioHangDTO2);
        gioHangDTO1.setId(null);
        assertThat(gioHangDTO1).isNotEqualTo(gioHangDTO2);
    }
}
