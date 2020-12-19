package com.datngo.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.datngo.web.rest.TestUtil;

public class GioHangTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GioHang.class);
        GioHang gioHang1 = new GioHang();
        gioHang1.setId(1L);
        GioHang gioHang2 = new GioHang();
        gioHang2.setId(gioHang1.getId());
        assertThat(gioHang1).isEqualTo(gioHang2);
        gioHang2.setId(2L);
        assertThat(gioHang1).isNotEqualTo(gioHang2);
        gioHang1.setId(null);
        assertThat(gioHang1).isNotEqualTo(gioHang2);
    }
}
