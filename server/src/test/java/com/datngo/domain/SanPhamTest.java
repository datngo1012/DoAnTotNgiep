package com.datngo.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.datngo.web.rest.TestUtil;

public class SanPhamTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SanPham.class);
        SanPham sanPham1 = new SanPham();
        sanPham1.setId(1L);
        SanPham sanPham2 = new SanPham();
        sanPham2.setId(sanPham1.getId());
        assertThat(sanPham1).isEqualTo(sanPham2);
        sanPham2.setId(2L);
        assertThat(sanPham1).isNotEqualTo(sanPham2);
        sanPham1.setId(null);
        assertThat(sanPham1).isNotEqualTo(sanPham2);
    }
}
