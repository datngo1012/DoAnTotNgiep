package com.datngo.web.rest;

import com.datngo.ServerApp;
import com.datngo.domain.GioHang;
import com.datngo.repository.GioHangRepository;
import com.datngo.service.GioHangService;
import com.datngo.service.dto.GioHangDTO;
import com.datngo.service.mapper.GioHangMapper;
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
 * Integration tests for the {@link GioHangResource} REST controller.
 */
@SpringBootTest(classes = ServerApp.class)
public class GioHangResourceIT {

    @Autowired
    private GioHangRepository gioHangRepository;

    @Autowired
    private GioHangMapper gioHangMapper;

    @Autowired
    private GioHangService gioHangService;

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

    private MockMvc restGioHangMockMvc;

    private GioHang gioHang;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GioHangResource gioHangResource = new GioHangResource(gioHangService);
        this.restGioHangMockMvc = MockMvcBuilders.standaloneSetup(gioHangResource)
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
    public static GioHang createEntity(EntityManager em) {
        GioHang gioHang = new GioHang();
        return gioHang;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GioHang createUpdatedEntity(EntityManager em) {
        GioHang gioHang = new GioHang();
        return gioHang;
    }

    @BeforeEach
    public void initTest() {
        gioHang = createEntity(em);
    }

    @Test
    @Transactional
    public void createGioHang() throws Exception {
        int databaseSizeBeforeCreate = gioHangRepository.findAll().size();

        // Create the GioHang
        GioHangDTO gioHangDTO = gioHangMapper.toDto(gioHang);
        restGioHangMockMvc.perform(post("/api/gio-hangs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gioHangDTO)))
            .andExpect(status().isCreated());

        // Validate the GioHang in the database
        List<GioHang> gioHangList = gioHangRepository.findAll();
        assertThat(gioHangList).hasSize(databaseSizeBeforeCreate + 1);
        GioHang testGioHang = gioHangList.get(gioHangList.size() - 1);
    }

    @Test
    @Transactional
    public void createGioHangWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = gioHangRepository.findAll().size();

        // Create the GioHang with an existing ID
        gioHang.setId(1L);
        GioHangDTO gioHangDTO = gioHangMapper.toDto(gioHang);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGioHangMockMvc.perform(post("/api/gio-hangs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gioHangDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GioHang in the database
        List<GioHang> gioHangList = gioHangRepository.findAll();
        assertThat(gioHangList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGioHangs() throws Exception {
        // Initialize the database
        gioHangRepository.saveAndFlush(gioHang);

        // Get all the gioHangList
        restGioHangMockMvc.perform(get("/api/gio-hangs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gioHang.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getGioHang() throws Exception {
        // Initialize the database
        gioHangRepository.saveAndFlush(gioHang);

        // Get the gioHang
        restGioHangMockMvc.perform(get("/api/gio-hangs/{id}", gioHang.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(gioHang.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingGioHang() throws Exception {
        // Get the gioHang
        restGioHangMockMvc.perform(get("/api/gio-hangs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGioHang() throws Exception {
        // Initialize the database
        gioHangRepository.saveAndFlush(gioHang);

        int databaseSizeBeforeUpdate = gioHangRepository.findAll().size();

        // Update the gioHang
        GioHang updatedGioHang = gioHangRepository.findById(gioHang.getId()).get();
        // Disconnect from session so that the updates on updatedGioHang are not directly saved in db
        em.detach(updatedGioHang);
        GioHangDTO gioHangDTO = gioHangMapper.toDto(updatedGioHang);

        restGioHangMockMvc.perform(put("/api/gio-hangs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gioHangDTO)))
            .andExpect(status().isOk());

        // Validate the GioHang in the database
        List<GioHang> gioHangList = gioHangRepository.findAll();
        assertThat(gioHangList).hasSize(databaseSizeBeforeUpdate);
        GioHang testGioHang = gioHangList.get(gioHangList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingGioHang() throws Exception {
        int databaseSizeBeforeUpdate = gioHangRepository.findAll().size();

        // Create the GioHang
        GioHangDTO gioHangDTO = gioHangMapper.toDto(gioHang);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGioHangMockMvc.perform(put("/api/gio-hangs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gioHangDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GioHang in the database
        List<GioHang> gioHangList = gioHangRepository.findAll();
        assertThat(gioHangList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGioHang() throws Exception {
        // Initialize the database
        gioHangRepository.saveAndFlush(gioHang);

        int databaseSizeBeforeDelete = gioHangRepository.findAll().size();

        // Delete the gioHang
        restGioHangMockMvc.perform(delete("/api/gio-hangs/{id}", gioHang.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GioHang> gioHangList = gioHangRepository.findAll();
        assertThat(gioHangList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
