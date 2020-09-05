package com.datngo.web.rest;

import com.datngo.ServerApp;
import com.datngo.domain.ChiTietSanPham;
import com.datngo.repository.ChiTietSanPhamRepository;
import com.datngo.service.ChiTietSanPhamService;
import com.datngo.service.dto.ChiTietSanPhamDTO;
import com.datngo.service.mapper.ChiTietSanPhamMapper;
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
import java.util.List;

import static com.datngo.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ChiTietSanPhamResource} REST controller.
 */
@SpringBootTest(classes = ServerApp.class)
public class ChiTietSanPhamResourceIT {

    private static final Integer DEFAULT_SO_LUONG = 1;
    private static final Integer UPDATED_SO_LUONG = 2;

    private static final String DEFAULT_PROPETIES_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PROPETIES_NAME = "BBBBBBBBBB";

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Autowired
    private ChiTietSanPhamMapper chiTietSanPhamMapper;

    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;

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

    private MockMvc restChiTietSanPhamMockMvc;

    private ChiTietSanPham chiTietSanPham;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ChiTietSanPhamResource chiTietSanPhamResource = new ChiTietSanPhamResource(chiTietSanPhamService);
        this.restChiTietSanPhamMockMvc = MockMvcBuilders.standaloneSetup(chiTietSanPhamResource)
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
    public static ChiTietSanPham createEntity(EntityManager em) {
        ChiTietSanPham chiTietSanPham = new ChiTietSanPham()
            .soLuong(DEFAULT_SO_LUONG)
            .propetiesName(DEFAULT_PROPETIES_NAME);
        return chiTietSanPham;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChiTietSanPham createUpdatedEntity(EntityManager em) {
        ChiTietSanPham chiTietSanPham = new ChiTietSanPham()
            .soLuong(UPDATED_SO_LUONG)
            .propetiesName(UPDATED_PROPETIES_NAME);
        return chiTietSanPham;
    }

    @BeforeEach
    public void initTest() {
        chiTietSanPham = createEntity(em);
    }

    @Test
    @Transactional
    public void createChiTietSanPham() throws Exception {
        int databaseSizeBeforeCreate = chiTietSanPhamRepository.findAll().size();

        // Create the ChiTietSanPham
        ChiTietSanPhamDTO chiTietSanPhamDTO = chiTietSanPhamMapper.toDto(chiTietSanPham);
        restChiTietSanPhamMockMvc.perform(post("/api/chi-tiet-san-phams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chiTietSanPhamDTO)))
            .andExpect(status().isCreated());

        // Validate the ChiTietSanPham in the database
        List<ChiTietSanPham> chiTietSanPhamList = chiTietSanPhamRepository.findAll();
        assertThat(chiTietSanPhamList).hasSize(databaseSizeBeforeCreate + 1);
        ChiTietSanPham testChiTietSanPham = chiTietSanPhamList.get(chiTietSanPhamList.size() - 1);
        assertThat(testChiTietSanPham.getSoLuong()).isEqualTo(DEFAULT_SO_LUONG);
        assertThat(testChiTietSanPham.getPropetiesName()).isEqualTo(DEFAULT_PROPETIES_NAME);
    }

    @Test
    @Transactional
    public void createChiTietSanPhamWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = chiTietSanPhamRepository.findAll().size();

        // Create the ChiTietSanPham with an existing ID
        chiTietSanPham.setId(1L);
        ChiTietSanPhamDTO chiTietSanPhamDTO = chiTietSanPhamMapper.toDto(chiTietSanPham);

        // An entity with an existing ID cannot be created, so this API call must fail
        restChiTietSanPhamMockMvc.perform(post("/api/chi-tiet-san-phams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chiTietSanPhamDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ChiTietSanPham in the database
        List<ChiTietSanPham> chiTietSanPhamList = chiTietSanPhamRepository.findAll();
        assertThat(chiTietSanPhamList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllChiTietSanPhams() throws Exception {
        // Initialize the database
        chiTietSanPhamRepository.saveAndFlush(chiTietSanPham);

        // Get all the chiTietSanPhamList
        restChiTietSanPhamMockMvc.perform(get("/api/chi-tiet-san-phams?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chiTietSanPham.getId().intValue())))
            .andExpect(jsonPath("$.[*].soLuong").value(hasItem(DEFAULT_SO_LUONG)))
            .andExpect(jsonPath("$.[*].propetiesName").value(hasItem(DEFAULT_PROPETIES_NAME)));
    }
    
    @Test
    @Transactional
    public void getChiTietSanPham() throws Exception {
        // Initialize the database
        chiTietSanPhamRepository.saveAndFlush(chiTietSanPham);

        // Get the chiTietSanPham
        restChiTietSanPhamMockMvc.perform(get("/api/chi-tiet-san-phams/{id}", chiTietSanPham.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(chiTietSanPham.getId().intValue()))
            .andExpect(jsonPath("$.soLuong").value(DEFAULT_SO_LUONG))
            .andExpect(jsonPath("$.propetiesName").value(DEFAULT_PROPETIES_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingChiTietSanPham() throws Exception {
        // Get the chiTietSanPham
        restChiTietSanPhamMockMvc.perform(get("/api/chi-tiet-san-phams/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateChiTietSanPham() throws Exception {
        // Initialize the database
        chiTietSanPhamRepository.saveAndFlush(chiTietSanPham);

        int databaseSizeBeforeUpdate = chiTietSanPhamRepository.findAll().size();

        // Update the chiTietSanPham
        ChiTietSanPham updatedChiTietSanPham = chiTietSanPhamRepository.findById(chiTietSanPham.getId()).get();
        // Disconnect from session so that the updates on updatedChiTietSanPham are not directly saved in db
        em.detach(updatedChiTietSanPham);
        updatedChiTietSanPham
            .soLuong(UPDATED_SO_LUONG)
            .propetiesName(UPDATED_PROPETIES_NAME);
        ChiTietSanPhamDTO chiTietSanPhamDTO = chiTietSanPhamMapper.toDto(updatedChiTietSanPham);

        restChiTietSanPhamMockMvc.perform(put("/api/chi-tiet-san-phams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chiTietSanPhamDTO)))
            .andExpect(status().isOk());

        // Validate the ChiTietSanPham in the database
        List<ChiTietSanPham> chiTietSanPhamList = chiTietSanPhamRepository.findAll();
        assertThat(chiTietSanPhamList).hasSize(databaseSizeBeforeUpdate);
        ChiTietSanPham testChiTietSanPham = chiTietSanPhamList.get(chiTietSanPhamList.size() - 1);
        assertThat(testChiTietSanPham.getSoLuong()).isEqualTo(UPDATED_SO_LUONG);
        assertThat(testChiTietSanPham.getPropetiesName()).isEqualTo(UPDATED_PROPETIES_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingChiTietSanPham() throws Exception {
        int databaseSizeBeforeUpdate = chiTietSanPhamRepository.findAll().size();

        // Create the ChiTietSanPham
        ChiTietSanPhamDTO chiTietSanPhamDTO = chiTietSanPhamMapper.toDto(chiTietSanPham);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChiTietSanPhamMockMvc.perform(put("/api/chi-tiet-san-phams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chiTietSanPhamDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ChiTietSanPham in the database
        List<ChiTietSanPham> chiTietSanPhamList = chiTietSanPhamRepository.findAll();
        assertThat(chiTietSanPhamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteChiTietSanPham() throws Exception {
        // Initialize the database
        chiTietSanPhamRepository.saveAndFlush(chiTietSanPham);

        int databaseSizeBeforeDelete = chiTietSanPhamRepository.findAll().size();

        // Delete the chiTietSanPham
        restChiTietSanPhamMockMvc.perform(delete("/api/chi-tiet-san-phams/{id}", chiTietSanPham.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ChiTietSanPham> chiTietSanPhamList = chiTietSanPhamRepository.findAll();
        assertThat(chiTietSanPhamList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
