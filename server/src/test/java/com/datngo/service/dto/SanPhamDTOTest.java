package com.datngo.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.datngo.web.rest.TestUtil;

public class SanPhamDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SanPhamDTO.class);
        SanPhamDTO sanPhamDTO1 = new SanPhamDTO();
        sanPhamDTO1.setId(1L);
        SanPhamDTO sanPhamDTO2 = new SanPhamDTO();
        assertThat(sanPhamDTO1).isNotEqualTo(sanPhamDTO2);
        sanPhamDTO2.setId(sanPhamDTO1.getId());
        assertThat(sanPhamDTO1).isEqualTo(sanPhamDTO2);
        sanPhamDTO2.setId(2L);
        assertThat(sanPhamDTO1).isNotEqualTo(sanPhamDTO2);
        sanPhamDTO1.setId(null);
        assertThat(sanPhamDTO1).isNotEqualTo(sanPhamDTO2);
    }
}
