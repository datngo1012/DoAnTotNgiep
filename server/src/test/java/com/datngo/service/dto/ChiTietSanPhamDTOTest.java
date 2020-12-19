package com.datngo.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.datngo.web.rest.TestUtil;

public class ChiTietSanPhamDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChiTietSanPhamDTO.class);
        ChiTietSanPhamDTO chiTietSanPhamDTO1 = new ChiTietSanPhamDTO();
        chiTietSanPhamDTO1.setId(1L);
        ChiTietSanPhamDTO chiTietSanPhamDTO2 = new ChiTietSanPhamDTO();
        assertThat(chiTietSanPhamDTO1).isNotEqualTo(chiTietSanPhamDTO2);
        chiTietSanPhamDTO2.setId(chiTietSanPhamDTO1.getId());
        assertThat(chiTietSanPhamDTO1).isEqualTo(chiTietSanPhamDTO2);
        chiTietSanPhamDTO2.setId(2L);
        assertThat(chiTietSanPhamDTO1).isNotEqualTo(chiTietSanPhamDTO2);
        chiTietSanPhamDTO1.setId(null);
        assertThat(chiTietSanPhamDTO1).isNotEqualTo(chiTietSanPhamDTO2);
    }
}
