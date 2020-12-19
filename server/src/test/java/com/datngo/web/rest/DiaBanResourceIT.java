package com.datngo.web.rest;

import com.datngo.ServerApp;
import com.datngo.domain.DiaBan;
import com.datngo.repository.DiaBanRepository;
import com.datngo.service.DiaBanService;
import com.datngo.service.dto.DiaBanDTO;
import com.datngo.service.mapper.DiaBanMapper;
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
 * Integration tests for the {@link DiaBanResource} REST controller.
 */
@SpringBootTest(classes = ServerApp.class)
public class DiaBanResourceIT {

    private static final String DEFAULT_MA = "AAAAAAAAAA";
    private static final String UPDATED_MA = "BBBBBBBBBB";

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    private static final String DEFAULT_CAP = "AAAAAAAAAA";
    private static final String UPDATED_CAP = "BBBBBBBBBB";

    private static final String DEFAULT_MA_DIA_BAN_CHA = "AAAAAAAAAA";
    private static final String UPDATED_MA_DIA_BAN_CHA = "BBBBBBBBBB";

    @Autowired
    private DiaBanRepository diaBanRepository;

    @Autowired
    private DiaBanMapper diaBanMapper;

    @Autowired
    private DiaBanService diaBanService;

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

    private MockMvc restDiaBanMockMvc;

    private DiaBan diaBan;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DiaBanResource diaBanResource = new DiaBanResource(diaBanService);
        this.restDiaBanMockMvc = MockMvcBuilders.standaloneSetup(diaBanResource)
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
    public static DiaBan createEntity(EntityManager em) {
        DiaBan diaBan = new DiaBan()
            .ma(DEFAULT_MA)
            .ten(DEFAULT_TEN)
            .cap(DEFAULT_CAP)
            .maDiaBanCha(DEFAULT_MA_DIA_BAN_CHA);
        return diaBan;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DiaBan createUpdatedEntity(EntityManager em) {
        DiaBan diaBan = new DiaBan()
            .ma(UPDATED_MA)
            .ten(UPDATED_TEN)
            .cap(UPDATED_CAP)
            .maDiaBanCha(UPDATED_MA_DIA_BAN_CHA);
        return diaBan;
    }

    @BeforeEach
    public void initTest() {
        diaBan = createEntity(em);
    }

    @Test
    @Transactional
    public void createDiaBan() throws Exception {
        int databaseSizeBeforeCreate = diaBanRepository.findAll().size();

        // Create the DiaBan
        DiaBanDTO diaBanDTO = diaBanMapper.toDto(diaBan);
        restDiaBanMockMvc.perform(post("/api/dia-bans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diaBanDTO)))
            .andExpect(status().isCreated());

        // Validate the DiaBan in the database
        List<DiaBan> diaBanList = diaBanRepository.findAll();
        assertThat(diaBanList).hasSize(databaseSizeBeforeCreate + 1);
        DiaBan testDiaBan = diaBanList.get(diaBanList.size() - 1);
        assertThat(testDiaBan.getMa()).isEqualTo(DEFAULT_MA);
        assertThat(testDiaBan.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testDiaBan.getCap()).isEqualTo(DEFAULT_CAP);
        assertThat(testDiaBan.getMaDiaBanCha()).isEqualTo(DEFAULT_MA_DIA_BAN_CHA);
    }

    @Test
    @Transactional
    public void createDiaBanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = diaBanRepository.findAll().size();

        // Create the DiaBan with an existing ID
        diaBan.setId(1L);
        DiaBanDTO diaBanDTO = diaBanMapper.toDto(diaBan);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDiaBanMockMvc.perform(post("/api/dia-bans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diaBanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DiaBan in the database
        List<DiaBan> diaBanList = diaBanRepository.findAll();
        assertThat(diaBanList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDiaBans() throws Exception {
        // Initialize the database
        diaBanRepository.saveAndFlush(diaBan);

        // Get all the diaBanList
        restDiaBanMockMvc.perform(get("/api/dia-bans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(diaBan.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].cap").value(hasItem(DEFAULT_CAP)))
            .andExpect(jsonPath("$.[*].maDiaBanCha").value(hasItem(DEFAULT_MA_DIA_BAN_CHA)));
    }
    
    @Test
    @Transactional
    public void getDiaBan() throws Exception {
        // Initialize the database
        diaBanRepository.saveAndFlush(diaBan);

        // Get the diaBan
        restDiaBanMockMvc.perform(get("/api/dia-bans/{id}", diaBan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(diaBan.getId().intValue()))
            .andExpect(jsonPath("$.ma").value(DEFAULT_MA))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN))
            .andExpect(jsonPath("$.cap").value(DEFAULT_CAP))
            .andExpect(jsonPath("$.maDiaBanCha").value(DEFAULT_MA_DIA_BAN_CHA));
    }

    @Test
    @Transactional
    public void getNonExistingDiaBan() throws Exception {
        // Get the diaBan
        restDiaBanMockMvc.perform(get("/api/dia-bans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDiaBan() throws Exception {
        // Initialize the database
        diaBanRepository.saveAndFlush(diaBan);

        int databaseSizeBeforeUpdate = diaBanRepository.findAll().size();

        // Update the diaBan
        DiaBan updatedDiaBan = diaBanRepository.findById(diaBan.getId()).get();
        // Disconnect from session so that the updates on updatedDiaBan are not directly saved in db
        em.detach(updatedDiaBan);
        updatedDiaBan
            .ma(UPDATED_MA)
            .ten(UPDATED_TEN)
            .cap(UPDATED_CAP)
            .maDiaBanCha(UPDATED_MA_DIA_BAN_CHA);
        DiaBanDTO diaBanDTO = diaBanMapper.toDto(updatedDiaBan);

        restDiaBanMockMvc.perform(put("/api/dia-bans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diaBanDTO)))
            .andExpect(status().isOk());

        // Validate the DiaBan in the database
        List<DiaBan> diaBanList = diaBanRepository.findAll();
        assertThat(diaBanList).hasSize(databaseSizeBeforeUpdate);
        DiaBan testDiaBan = diaBanList.get(diaBanList.size() - 1);
        assertThat(testDiaBan.getMa()).isEqualTo(UPDATED_MA);
        assertThat(testDiaBan.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testDiaBan.getCap()).isEqualTo(UPDATED_CAP);
        assertThat(testDiaBan.getMaDiaBanCha()).isEqualTo(UPDATED_MA_DIA_BAN_CHA);
    }

    @Test
    @Transactional
    public void updateNonExistingDiaBan() throws Exception {
        int databaseSizeBeforeUpdate = diaBanRepository.findAll().size();

        // Create the DiaBan
        DiaBanDTO diaBanDTO = diaBanMapper.toDto(diaBan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDiaBanMockMvc.perform(put("/api/dia-bans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diaBanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DiaBan in the database
        List<DiaBan> diaBanList = diaBanRepository.findAll();
        assertThat(diaBanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDiaBan() throws Exception {
        // Initialize the database
        diaBanRepository.saveAndFlush(diaBan);

        int databaseSizeBeforeDelete = diaBanRepository.findAll().size();

        // Delete the diaBan
        restDiaBanMockMvc.perform(delete("/api/dia-bans/{id}", diaBan.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DiaBan> diaBanList = diaBanRepository.findAll();
        assertThat(diaBanList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
