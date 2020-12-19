package com.datngo.web.rest;

import com.datngo.ServerApp;
import com.datngo.domain.QuanLyGiaoDich;
import com.datngo.repository.QuanLyGiaoDichRepository;
import com.datngo.service.QuanLyGiaoDichService;
import com.datngo.service.dto.QuanLyGiaoDichDTO;
import com.datngo.service.mapper.QuanLyGiaoDichMapper;
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
 * Integration tests for the {@link QuanLyGiaoDichResource} REST controller.
 */
@SpringBootTest(classes = ServerApp.class)
public class QuanLyGiaoDichResourceIT {

    private static final Long DEFAULT_SO_TIEN_GIAO_DICH = 1L;
    private static final Long UPDATED_SO_TIEN_GIAO_DICH = 2L;

    private static final String DEFAULT_LOAI_GIAO_DICH = "AAAAAAAAAA";
    private static final String UPDATED_LOAI_GIAO_DICH = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NGAY_GIAO_DICH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_GIAO_DICH = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_TRANG_THAI = 1;
    private static final Integer UPDATED_TRANG_THAI = 2;

    @Autowired
    private QuanLyGiaoDichRepository quanLyGiaoDichRepository;

    @Autowired
    private QuanLyGiaoDichMapper quanLyGiaoDichMapper;

    @Autowired
    private QuanLyGiaoDichService quanLyGiaoDichService;

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

    private MockMvc restQuanLyGiaoDichMockMvc;

    private QuanLyGiaoDich quanLyGiaoDich;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final QuanLyGiaoDichResource quanLyGiaoDichResource = new QuanLyGiaoDichResource(quanLyGiaoDichService);
        this.restQuanLyGiaoDichMockMvc = MockMvcBuilders.standaloneSetup(quanLyGiaoDichResource)
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
    public static QuanLyGiaoDich createEntity(EntityManager em) {
        QuanLyGiaoDich quanLyGiaoDich = new QuanLyGiaoDich()
            .soTienGiaoDich(DEFAULT_SO_TIEN_GIAO_DICH)
            .loaiGiaoDich(DEFAULT_LOAI_GIAO_DICH)
            .ngayGiaoDich(DEFAULT_NGAY_GIAO_DICH)
            .trangThai(DEFAULT_TRANG_THAI);
        return quanLyGiaoDich;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuanLyGiaoDich createUpdatedEntity(EntityManager em) {
        QuanLyGiaoDich quanLyGiaoDich = new QuanLyGiaoDich()
            .soTienGiaoDich(UPDATED_SO_TIEN_GIAO_DICH)
            .loaiGiaoDich(UPDATED_LOAI_GIAO_DICH)
            .ngayGiaoDich(UPDATED_NGAY_GIAO_DICH)
            .trangThai(UPDATED_TRANG_THAI);
        return quanLyGiaoDich;
    }

    @BeforeEach
    public void initTest() {
        quanLyGiaoDich = createEntity(em);
    }

    @Test
    @Transactional
    public void createQuanLyGiaoDich() throws Exception {
        int databaseSizeBeforeCreate = quanLyGiaoDichRepository.findAll().size();

        // Create the QuanLyGiaoDich
        QuanLyGiaoDichDTO quanLyGiaoDichDTO = quanLyGiaoDichMapper.toDto(quanLyGiaoDich);
        restQuanLyGiaoDichMockMvc.perform(post("/api/quan-ly-giao-diches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quanLyGiaoDichDTO)))
            .andExpect(status().isCreated());

        // Validate the QuanLyGiaoDich in the database
        List<QuanLyGiaoDich> quanLyGiaoDichList = quanLyGiaoDichRepository.findAll();
        assertThat(quanLyGiaoDichList).hasSize(databaseSizeBeforeCreate + 1);
        QuanLyGiaoDich testQuanLyGiaoDich = quanLyGiaoDichList.get(quanLyGiaoDichList.size() - 1);
        assertThat(testQuanLyGiaoDich.getSoTienGiaoDich()).isEqualTo(DEFAULT_SO_TIEN_GIAO_DICH);
        assertThat(testQuanLyGiaoDich.getLoaiGiaoDich()).isEqualTo(DEFAULT_LOAI_GIAO_DICH);
        assertThat(testQuanLyGiaoDich.getNgayGiaoDich()).isEqualTo(DEFAULT_NGAY_GIAO_DICH);
        assertThat(testQuanLyGiaoDich.getTrangThai()).isEqualTo(DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    public void createQuanLyGiaoDichWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = quanLyGiaoDichRepository.findAll().size();

        // Create the QuanLyGiaoDich with an existing ID
        quanLyGiaoDich.setId(1L);
        QuanLyGiaoDichDTO quanLyGiaoDichDTO = quanLyGiaoDichMapper.toDto(quanLyGiaoDich);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuanLyGiaoDichMockMvc.perform(post("/api/quan-ly-giao-diches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quanLyGiaoDichDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QuanLyGiaoDich in the database
        List<QuanLyGiaoDich> quanLyGiaoDichList = quanLyGiaoDichRepository.findAll();
        assertThat(quanLyGiaoDichList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllQuanLyGiaoDiches() throws Exception {
        // Initialize the database
        quanLyGiaoDichRepository.saveAndFlush(quanLyGiaoDich);

        // Get all the quanLyGiaoDichList
        restQuanLyGiaoDichMockMvc.perform(get("/api/quan-ly-giao-diches?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(quanLyGiaoDich.getId().intValue())))
            .andExpect(jsonPath("$.[*].soTienGiaoDich").value(hasItem(DEFAULT_SO_TIEN_GIAO_DICH.intValue())))
            .andExpect(jsonPath("$.[*].loaiGiaoDich").value(hasItem(DEFAULT_LOAI_GIAO_DICH)))
            .andExpect(jsonPath("$.[*].ngayGiaoDich").value(hasItem(DEFAULT_NGAY_GIAO_DICH.toString())))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI)));
    }
    
    @Test
    @Transactional
    public void getQuanLyGiaoDich() throws Exception {
        // Initialize the database
        quanLyGiaoDichRepository.saveAndFlush(quanLyGiaoDich);

        // Get the quanLyGiaoDich
        restQuanLyGiaoDichMockMvc.perform(get("/api/quan-ly-giao-diches/{id}", quanLyGiaoDich.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(quanLyGiaoDich.getId().intValue()))
            .andExpect(jsonPath("$.soTienGiaoDich").value(DEFAULT_SO_TIEN_GIAO_DICH.intValue()))
            .andExpect(jsonPath("$.loaiGiaoDich").value(DEFAULT_LOAI_GIAO_DICH))
            .andExpect(jsonPath("$.ngayGiaoDich").value(DEFAULT_NGAY_GIAO_DICH.toString()))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI));
    }

    @Test
    @Transactional
    public void getNonExistingQuanLyGiaoDich() throws Exception {
        // Get the quanLyGiaoDich
        restQuanLyGiaoDichMockMvc.perform(get("/api/quan-ly-giao-diches/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQuanLyGiaoDich() throws Exception {
        // Initialize the database
        quanLyGiaoDichRepository.saveAndFlush(quanLyGiaoDich);

        int databaseSizeBeforeUpdate = quanLyGiaoDichRepository.findAll().size();

        // Update the quanLyGiaoDich
        QuanLyGiaoDich updatedQuanLyGiaoDich = quanLyGiaoDichRepository.findById(quanLyGiaoDich.getId()).get();
        // Disconnect from session so that the updates on updatedQuanLyGiaoDich are not directly saved in db
        em.detach(updatedQuanLyGiaoDich);
        updatedQuanLyGiaoDich
            .soTienGiaoDich(UPDATED_SO_TIEN_GIAO_DICH)
            .loaiGiaoDich(UPDATED_LOAI_GIAO_DICH)
            .ngayGiaoDich(UPDATED_NGAY_GIAO_DICH)
            .trangThai(UPDATED_TRANG_THAI);
        QuanLyGiaoDichDTO quanLyGiaoDichDTO = quanLyGiaoDichMapper.toDto(updatedQuanLyGiaoDich);

        restQuanLyGiaoDichMockMvc.perform(put("/api/quan-ly-giao-diches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quanLyGiaoDichDTO)))
            .andExpect(status().isOk());

        // Validate the QuanLyGiaoDich in the database
        List<QuanLyGiaoDich> quanLyGiaoDichList = quanLyGiaoDichRepository.findAll();
        assertThat(quanLyGiaoDichList).hasSize(databaseSizeBeforeUpdate);
        QuanLyGiaoDich testQuanLyGiaoDich = quanLyGiaoDichList.get(quanLyGiaoDichList.size() - 1);
        assertThat(testQuanLyGiaoDich.getSoTienGiaoDich()).isEqualTo(UPDATED_SO_TIEN_GIAO_DICH);
        assertThat(testQuanLyGiaoDich.getLoaiGiaoDich()).isEqualTo(UPDATED_LOAI_GIAO_DICH);
        assertThat(testQuanLyGiaoDich.getNgayGiaoDich()).isEqualTo(UPDATED_NGAY_GIAO_DICH);
        assertThat(testQuanLyGiaoDich.getTrangThai()).isEqualTo(UPDATED_TRANG_THAI);
    }

    @Test
    @Transactional
    public void updateNonExistingQuanLyGiaoDich() throws Exception {
        int databaseSizeBeforeUpdate = quanLyGiaoDichRepository.findAll().size();

        // Create the QuanLyGiaoDich
        QuanLyGiaoDichDTO quanLyGiaoDichDTO = quanLyGiaoDichMapper.toDto(quanLyGiaoDich);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuanLyGiaoDichMockMvc.perform(put("/api/quan-ly-giao-diches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quanLyGiaoDichDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QuanLyGiaoDich in the database
        List<QuanLyGiaoDich> quanLyGiaoDichList = quanLyGiaoDichRepository.findAll();
        assertThat(quanLyGiaoDichList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQuanLyGiaoDich() throws Exception {
        // Initialize the database
        quanLyGiaoDichRepository.saveAndFlush(quanLyGiaoDich);

        int databaseSizeBeforeDelete = quanLyGiaoDichRepository.findAll().size();

        // Delete the quanLyGiaoDich
        restQuanLyGiaoDichMockMvc.perform(delete("/api/quan-ly-giao-diches/{id}", quanLyGiaoDich.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<QuanLyGiaoDich> quanLyGiaoDichList = quanLyGiaoDichRepository.findAll();
        assertThat(quanLyGiaoDichList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
