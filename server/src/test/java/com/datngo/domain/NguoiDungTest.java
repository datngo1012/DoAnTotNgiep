package com.datngo.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.datngo.web.rest.TestUtil;

public class NguoiDungTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NguoiDung.class);
        NguoiDung nguoiDung1 = new NguoiDung();
        nguoiDung1.setId(1L);
        NguoiDung nguoiDung2 = new NguoiDung();
        nguoiDung2.setId(nguoiDung1.getId());
        assertThat(nguoiDung1).isEqualTo(nguoiDung2);
        nguoiDung2.setId(2L);
        assertThat(nguoiDung1).isNotEqualTo(nguoiDung2);
        nguoiDung1.setId(null);
        assertThat(nguoiDung1).isNotEqualTo(nguoiDung2);
    }
}
