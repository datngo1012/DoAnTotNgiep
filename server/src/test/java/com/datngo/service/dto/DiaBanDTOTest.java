package com.datngo.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.datngo.web.rest.TestUtil;

public class DiaBanDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DiaBanDTO.class);
        DiaBanDTO diaBanDTO1 = new DiaBanDTO();
        diaBanDTO1.setId(1L);
        DiaBanDTO diaBanDTO2 = new DiaBanDTO();
        assertThat(diaBanDTO1).isNotEqualTo(diaBanDTO2);
        diaBanDTO2.setId(diaBanDTO1.getId());
        assertThat(diaBanDTO1).isEqualTo(diaBanDTO2);
        diaBanDTO2.setId(2L);
        assertThat(diaBanDTO1).isNotEqualTo(diaBanDTO2);
        diaBanDTO1.setId(null);
        assertThat(diaBanDTO1).isNotEqualTo(diaBanDTO2);
    }
}
