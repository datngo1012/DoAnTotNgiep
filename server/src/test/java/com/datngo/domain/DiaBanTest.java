package com.datngo.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.datngo.web.rest.TestUtil;

public class DiaBanTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DiaBan.class);
        DiaBan diaBan1 = new DiaBan();
        diaBan1.setId(1L);
        DiaBan diaBan2 = new DiaBan();
        diaBan2.setId(diaBan1.getId());
        assertThat(diaBan1).isEqualTo(diaBan2);
        diaBan2.setId(2L);
        assertThat(diaBan1).isNotEqualTo(diaBan2);
        diaBan1.setId(null);
        assertThat(diaBan1).isNotEqualTo(diaBan2);
    }
}
