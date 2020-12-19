package com.datngo.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.datngo.web.rest.TestUtil;

public class NguoiDungDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NguoiDungDTO.class);
        NguoiDungDTO nguoiDungDTO1 = new NguoiDungDTO();
        nguoiDungDTO1.setId(1L);
        NguoiDungDTO nguoiDungDTO2 = new NguoiDungDTO();
        assertThat(nguoiDungDTO1).isNotEqualTo(nguoiDungDTO2);
        nguoiDungDTO2.setId(nguoiDungDTO1.getId());
        assertThat(nguoiDungDTO1).isEqualTo(nguoiDungDTO2);
        nguoiDungDTO2.setId(2L);
        assertThat(nguoiDungDTO1).isNotEqualTo(nguoiDungDTO2);
        nguoiDungDTO1.setId(null);
        assertThat(nguoiDungDTO1).isNotEqualTo(nguoiDungDTO2);
    }
}
