package com.datngo.web.rest;

import com.datngo.ServerApp;
import com.datngo.domain.NguoiDung;
import com.datngo.repository.NguoiDungRepository;
import com.datngo.service.NguoiDungService;
import com.datngo.service.dto.NguoiDungDTO;
import com.datngo.service.mapper.NguoiDungMapper;
import com.datngo.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.datngo.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link NguoiDungResource} REST controller.
 */
@SpringBootTest(classes = ServerApp.class)
public class NguoiDungResourceIT {

    private static final String DEFAULT_HO_TEN = "AAAAAAAAAA";
    private static final String UPDATED_HO_TEN = "BBBBBBBBBB";

    private static final String DEFAULT_SDT = "AAAAAAAAAA";
    private static final String UPDATED_SDT = "BBBBBBBBBB";

    private static final Integer DEFAULT_NGAY_SINH = 1;
    private static final Integer UPDATED_NGAY_SINH = 2;

    private static final Integer DEFAULT_THANG_SINH = 1;
    private static final Integer UPDATED_THANG_SINH = 2;

    private static final Integer DEFAULT_NAM_SINH = 1;
    private static final Integer UPDATED_NAM_SINH = 2;

    private static final String DEFAULT_GIOI_TINH = "AAAAAAAAAA";
    private static final String UPDATED_GIOI_TINH = "BBBBBBBBBB";

    private static final String DEFAULT_DIA_CHI = "AAAAAAAAAA";
    private static final String UPDATED_DIA_CHI = "BBBBBBBBBB";

    private static final Long DEFAULT_SO_DU = 1L;
    private static final Long UPDATED_SO_DU = 2L;

    private static final Integer DEFAULT_TRANG_THAI = 1;
    private static final Integer UPDATED_TRANG_THAI = 2;

    private static final LocalDate DEFAULT_NGAY_TAO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_TAO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_NGAY_SUA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_SUA = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private NguoiDungMapper nguoiDungMapper;

    @Autowired
    private NguoiDungService nguoiDungService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restNguoiDungMockMvc;

    private NguoiDung nguoiDung;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NguoiDungResource nguoiDungResource = new NguoiDungResource(nguoiDungService);
        this.restNguoiDungMockMvc = MockMvcBuilders.standaloneSetup(nguoiDungResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NguoiDung createEntity(EntityManager em) {
        NguoiDung nguoiDung = new NguoiDung()
            .hoTen(DEFAULT_HO_TEN)
            .sdt(DEFAULT_SDT)
            .ngaySinh(DEFAULT_NGAY_SINH)
            .thangSinh(DEFAULT_THANG_SINH)
            .namSinh(DEFAULT_NAM_SINH)
            .gioiTinh(DEFAULT_GIOI_TINH)
            .diaChi(DEFAULT_DIA_CHI)
            .soDu(DEFAULT_SO_DU)
            .trangThai(DEFAULT_TRANG_THAI)
            .ngayTao(DEFAULT_NGAY_TAO)
            .ngaySua(DEFAULT_NGAY_SUA);
        return nguoiDung;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NguoiDung createUpdatedEntity(EntityManager em) {
        NguoiDung nguoiDung = new NguoiDung()
            .hoTen(UPDATED_HO_TEN)
            .sdt(UPDATED_SDT)
            .ngaySinh(UPDATED_NGAY_SINH)
            .thangSinh(UPDATED_THANG_SINH)
            .namSinh(UPDATED_NAM_SINH)
            .gioiTinh(UPDATED_GIOI_TINH)
            .diaChi(UPDATED_DIA_CHI)
            .soDu(UPDATED_SO_DU)
            .trangThai(UPDATED_TRANG_THAI)
            .ngayTao(UPDATED_NGAY_TAO)
            .ngaySua(UPDATED_NGAY_SUA);
        return nguoiDung;
    }

    @BeforeEach
    public void initTest() {
        nguoiDung = createEntity(em);
    }

    @Test
    @Transactional
    public void createNguoiDung() throws Exception {
        int databaseSizeBeforeCreate = nguoiDungRepository.findAll().size();

        // Create the NguoiDung
        NguoiDungDTO nguoiDungDTO = nguoiDungMapper.toDto(nguoiDung);
        restNguoiDungMockMvc.perform(post("/api/nguoi-dungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nguoiDungDTO)))
            .andExpect(status().isCreated());

        // Validate the NguoiDung in the database
        List<NguoiDung> nguoiDungList = nguoiDungRepository.findAll();
        assertThat(nguoiDungList).hasSize(databaseSizeBeforeCreate + 1);
        NguoiDung testNguoiDung = nguoiDungList.get(nguoiDungList.size() - 1);
        assertThat(testNguoiDung.getHoTen()).isEqualTo(DEFAULT_HO_TEN);
        assertThat(testNguoiDung.getSdt()).isEqualTo(DEFAULT_SDT);
        assertThat(testNguoiDung.getNgaySinh()).isEqualTo(DEFAULT_NGAY_SINH);
        assertThat(testNguoiDung.getThangSinh()).isEqualTo(DEFAULT_THANG_SINH);
        assertThat(testNguoiDung.getNamSinh()).isEqualTo(DEFAULT_NAM_SINH);
        assertThat(testNguoiDung.getGioiTinh()).isEqualTo(DEFAULT_GIOI_TINH);
        assertThat(testNguoiDung.getDiaChi()).isEqualTo(DEFAULT_DIA_CHI);
        assertThat(testNguoiDung.getSoDu()).isEqualTo(DEFAULT_SO_DU);
        assertThat(testNguoiDung.getTrangThai()).isEqualTo(DEFAULT_TRANG_THAI);
        assertThat(testNguoiDung.getNgayTao()).isEqualTo(DEFAULT_NGAY_TAO);
        assertThat(testNguoiDung.getNgaySua()).isEqualTo(DEFAULT_NGAY_SUA);
    }

    @Test
    @Transactional
    public void createNguoiDungWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nguoiDungRepository.findAll().size();

        // Create the NguoiDung with an existing ID
        nguoiDung.setId(1L);
        NguoiDungDTO nguoiDungDTO = nguoiDungMapper.toDto(nguoiDung);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNguoiDungMockMvc.perform(post("/api/nguoi-dungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nguoiDungDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NguoiDung in the database
        List<NguoiDung> nguoiDungList = nguoiDungRepository.findAll();
        assertThat(nguoiDungList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllNguoiDungs() throws Exception {
        // Initialize the database
        nguoiDungRepository.saveAndFlush(nguoiDung);

        // Get all the nguoiDungList
        restNguoiDungMockMvc.perform(get("/api/nguoi-dungs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nguoiDung.getId().intValue())))
            .andExpect(jsonPath("$.[*].hoTen").value(hasItem(DEFAULT_HO_TEN)))
            .andExpect(jsonPath("$.[*].sdt").value(hasItem(DEFAULT_SDT)))
            .andExpect(jsonPath("$.[*].ngaySinh").value(hasItem(DEFAULT_NGAY_SINH)))
            .andExpect(jsonPath("$.[*].thangSinh").value(hasItem(DEFAULT_THANG_SINH)))
            .andExpect(jsonPath("$.[*].namSinh").value(hasItem(DEFAULT_NAM_SINH)))
            .andExpect(jsonPath("$.[*].gioiTinh").value(hasItem(DEFAULT_GIOI_TINH)))
            .andExpect(jsonPath("$.[*].diaChi").value(hasItem(DEFAULT_DIA_CHI)))
            .andExpect(jsonPath("$.[*].soDu").value(hasItem(DEFAULT_SO_DU.intValue())))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI)))
            .andExpect(jsonPath("$.[*].ngayTao").value(hasItem(DEFAULT_NGAY_TAO.toString())))
            .andExpect(jsonPath("$.[*].ngaySua").value(hasItem(DEFAULT_NGAY_SUA.toString())));
    }
    
    @Test
    @Transactional
    public void getNguoiDung() throws Exception {
        // Initialize the database
        nguoiDungRepository.saveAndFlush(nguoiDung);

        // Get the nguoiDung
        restNguoiDungMockMvc.perform(get("/api/nguoi-dungs/{id}", nguoiDung.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nguoiDung.getId().intValue()))
            .andExpect(jsonPath("$.hoTen").value(DEFAULT_HO_TEN))
            .andExpect(jsonPath("$.sdt").value(DEFAULT_SDT))
            .andExpect(jsonPath("$.ngaySinh").value(DEFAULT_NGAY_SINH))
            .andExpect(jsonPath("$.thangSinh").value(DEFAULT_THANG_SINH))
            .andExpect(jsonPath("$.namSinh").value(DEFAULT_NAM_SINH))
            .andExpect(jsonPath("$.gioiTinh").value(DEFAULT_GIOI_TINH))
            .andExpect(jsonPath("$.diaChi").value(DEFAULT_DIA_CHI))
            .andExpect(jsonPath("$.soDu").value(DEFAULT_SO_DU.intValue()))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI))
            .andExpect(jsonPath("$.ngayTao").value(DEFAULT_NGAY_TAO.toString()))
            .andExpect(jsonPath("$.ngaySua").value(DEFAULT_NGAY_SUA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingNguoiDung() throws Exception {
        // Get the nguoiDung
        restNguoiDungMockMvc.perform(get("/api/nguoi-dungs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNguoiDung() throws Exception {
        // Initialize the database
        nguoiDungRepository.saveAndFlush(nguoiDung);

        int databaseSizeBeforeUpdate = nguoiDungRepository.findAll().size();

        // Update the nguoiDung
        NguoiDung updatedNguoiDung = nguoiDungRepository.findById(nguoiDung.getId()).get();
        // Disconnect from session so that the updates on updatedNguoiDung are not directly saved in db
        em.detach(updatedNguoiDung);
        updatedNguoiDung
            .hoTen(UPDATED_HO_TEN)
            .sdt(UPDATED_SDT)
            .ngaySinh(UPDATED_NGAY_SINH)
            .thangSinh(UPDATED_THANG_SINH)
            .namSinh(UPDATED_NAM_SINH)
            .gioiTinh(UPDATED_GIOI_TINH)
            .diaChi(UPDATED_DIA_CHI)
            .soDu(UPDATED_SO_DU)
            .trangThai(UPDATED_TRANG_THAI)
            .ngayTao(UPDATED_NGAY_TAO)
            .ngaySua(UPDATED_NGAY_SUA);
        NguoiDungDTO nguoiDungDTO = nguoiDungMapper.toDto(updatedNguoiDung);

        restNguoiDungMockMvc.perform(put("/api/nguoi-dungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nguoiDungDTO)))
            .andExpect(status().isOk());

        // Validate the NguoiDung in the database
        List<NguoiDung> nguoiDungList = nguoiDungRepository.findAll();
        assertThat(nguoiDungList).hasSize(databaseSizeBeforeUpdate);
        NguoiDung testNguoiDung = nguoiDungList.get(nguoiDungList.size() - 1);
        assertThat(testNguoiDung.getHoTen()).isEqualTo(UPDATED_HO_TEN);
        assertThat(testNguoiDung.getSdt()).isEqualTo(UPDATED_SDT);
        assertThat(testNguoiDung.getNgaySinh()).isEqualTo(UPDATED_NGAY_SINH);
        assertThat(testNguoiDung.getThangSinh()).isEqualTo(UPDATED_THANG_SINH);
        assertThat(testNguoiDung.getNamSinh()).isEqualTo(UPDATED_NAM_SINH);
        assertThat(testNguoiDung.getGioiTinh()).isEqualTo(UPDATED_GIOI_TINH);
        assertThat(testNguoiDung.getDiaChi()).isEqualTo(UPDATED_DIA_CHI);
        assertThat(testNguoiDung.getSoDu()).isEqualTo(UPDATED_SO_DU);
        assertThat(testNguoiDung.getTrangThai()).isEqualTo(UPDATED_TRANG_THAI);
        assertThat(testNguoiDung.getNgayTao()).isEqualTo(UPDATED_NGAY_TAO);
        assertThat(testNguoiDung.getNgaySua()).isEqualTo(UPDATED_NGAY_SUA);
    }

    @Test
    @Transactional
    public void updateNonExistingNguoiDung() throws Exception {
        int databaseSizeBeforeUpdate = nguoiDungRepository.findAll().size();

        // Create the NguoiDung
        NguoiDungDTO nguoiDungDTO = nguoiDungMapper.toDto(nguoiDung);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNguoiDungMockMvc.perform(put("/api/nguoi-dungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nguoiDungDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NguoiDung in the database
        List<NguoiDung> nguoiDungList = nguoiDungRepository.findAll();
        assertThat(nguoiDungList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNguoiDung() throws Exception {
        // Initialize the database
        nguoiDungRepository.saveAndFlush(nguoiDung);

        int databaseSizeBeforeDelete = nguoiDungRepository.findAll().size();

        // Delete the nguoiDung
        restNguoiDungMockMvc.perform(delete("/api/nguoi-dungs/{id}", nguoiDung.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NguoiDung> nguoiDungList = nguoiDungRepository.findAll();
        assertThat(nguoiDungList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
