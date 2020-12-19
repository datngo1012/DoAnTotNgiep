package com.datngo.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.datngo.web.rest.TestUtil;

public class ChiTietSanPhamTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChiTietSanPham.class);
        ChiTietSanPham chiTietSanPham1 = new ChiTietSanPham();
        chiTietSanPham1.setId(1L);
        ChiTietSanPham chiTietSanPham2 = new ChiTietSanPham();
        chiTietSanPham2.setId(chiTietSanPham1.getId());
        assertThat(chiTietSanPham1).isEqualTo(chiTietSanPham2);
        chiTietSanPham2.setId(2L);
        assertThat(chiTietSanPham1).isNotEqualTo(chiTietSanPham2);
        chiTietSanPham1.setId(null);
        assertThat(chiTietSanPham1).isNotEqualTo(chiTietSanPham2);
    }
}
