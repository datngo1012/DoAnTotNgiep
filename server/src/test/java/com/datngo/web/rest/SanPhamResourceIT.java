package com.datngo.web.rest;

import com.datngo.ServerApp;
import com.datngo.domain.SanPham;
import com.datngo.repository.SanPhamRepository;
import com.datngo.service.SanPhamService;
import com.datngo.service.dto.SanPhamDTO;
import com.datngo.service.mapper.SanPhamMapper;
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
 * Integration tests for the {@link SanPhamResource} REST controller.
 */
@SpringBootTest(classes = ServerApp.class)
public class SanPhamResourceIT {

    private static final String DEFAULT_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DETAIL_CONTENT = false;
    private static final Boolean UPDATED_IS_DETAIL_CONTENT = true;

    private static final String DEFAULT_NAM = "AAAAAAAAAA";
    private static final String UPDATED_NAM = "BBBBBBBBBB";

    private static final Long DEFAULT_PRODUCT_ID = 1L;
    private static final Long UPDATED_PRODUCT_ID = 2L;

    private static final Integer DEFAULT_RATE = 1;
    private static final Integer UPDATED_RATE = 2;

    private static final Long DEFAULT_REPURCHASE_RATE = 1L;
    private static final Long UPDATED_REPURCHASE_RATE = 2L;

    private static final Long DEFAULT_SELL_PRICE = 1L;
    private static final Long UPDATED_SELL_PRICE = 2L;

    private static final Long DEFAULT_SELL_PRICE_DISPLAY = 1L;
    private static final Long UPDATED_SELL_PRICE_DISPLAY = 2L;

    private static final Long DEFAULT_START_PRICE_VND = 1L;
    private static final Long UPDATED_START_PRICE_VND = 2L;

    private static final Long DEFAULT_STOCK = 1L;
    private static final Long UPDATED_STOCK = 2L;

    private static final String DEFAULT_TRANSACTION = "";
    private static final String UPDATED_TRANSACTION = "";

    private static final Boolean DEFAULT_TRANSALATED = false;
    private static final Boolean UPDATED_TRANSALATED = true;

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Autowired
    private SanPhamMapper sanPhamMapper;

    @Autowired
    private SanPhamService sanPhamService;

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

    private MockMvc restSanPhamMockMvc;

    private SanPham sanPham;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SanPhamResource sanPhamResource = new SanPhamResource(sanPhamService);
        this.restSanPhamMockMvc = MockMvcBuilders.standaloneSetup(sanPhamResource)
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
    public static SanPham createEntity(EntityManager em) {
        SanPham sanPham = new SanPham()
            .image(DEFAULT_IMAGE)
            .isDetailContent(DEFAULT_IS_DETAIL_CONTENT)
            .nam(DEFAULT_NAM)
            .productId(DEFAULT_PRODUCT_ID)
            .rate(DEFAULT_RATE)
            .repurchaseRate(DEFAULT_REPURCHASE_RATE)
            .sellPrice(DEFAULT_SELL_PRICE)
            .sellPriceDisplay(DEFAULT_SELL_PRICE_DISPLAY)
            .startPriceVND(DEFAULT_START_PRICE_VND)
            .stock(DEFAULT_STOCK)
            .transaction(DEFAULT_TRANSACTION)
            .transalated(DEFAULT_TRANSALATED);
        return sanPham;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SanPham createUpdatedEntity(EntityManager em) {
        SanPham sanPham = new SanPham()
            .image(UPDATED_IMAGE)
            .isDetailContent(UPDATED_IS_DETAIL_CONTENT)
            .nam(UPDATED_NAM)
            .productId(UPDATED_PRODUCT_ID)
            .rate(UPDATED_RATE)
            .repurchaseRate(UPDATED_REPURCHASE_RATE)
            .sellPrice(UPDATED_SELL_PRICE)
            .sellPriceDisplay(UPDATED_SELL_PRICE_DISPLAY)
            .startPriceVND(UPDATED_START_PRICE_VND)
            .stock(UPDATED_STOCK)
            .transaction(UPDATED_TRANSACTION)
            .transalated(UPDATED_TRANSALATED);
        return sanPham;
    }

    @BeforeEach
    public void initTest() {
        sanPham = createEntity(em);
    }

    @Test
    @Transactional
    public void createSanPham() throws Exception {
        int databaseSizeBeforeCreate = sanPhamRepository.findAll().size();

        // Create the SanPham
        SanPhamDTO sanPhamDTO = sanPhamMapper.toDto(sanPham);
        restSanPhamMockMvc.perform(post("/api/san-phams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sanPhamDTO)))
            .andExpect(status().isCreated());

        // Validate the SanPham in the database
        List<SanPham> sanPhamList = sanPhamRepository.findAll();
        assertThat(sanPhamList).hasSize(databaseSizeBeforeCreate + 1);
        SanPham testSanPham = sanPhamList.get(sanPhamList.size() - 1);
        assertThat(testSanPham.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testSanPham.isIsDetailContent()).isEqualTo(DEFAULT_IS_DETAIL_CONTENT);
        assertThat(testSanPham.getNam()).isEqualTo(DEFAULT_NAM);
        assertThat(testSanPham.getProductId()).isEqualTo(DEFAULT_PRODUCT_ID);
        assertThat(testSanPham.getRate()).isEqualTo(DEFAULT_RATE);
        assertThat(testSanPham.getRepurchaseRate()).isEqualTo(DEFAULT_REPURCHASE_RATE);
        assertThat(testSanPham.getSellPrice()).isEqualTo(DEFAULT_SELL_PRICE);
        assertThat(testSanPham.getSellPriceDisplay()).isEqualTo(DEFAULT_SELL_PRICE_DISPLAY);
        assertThat(testSanPham.getStartPriceVND()).isEqualTo(DEFAULT_START_PRICE_VND);
        assertThat(testSanPham.getStock()).isEqualTo(DEFAULT_STOCK);
        assertThat(testSanPham.getTransaction()).isEqualTo(DEFAULT_TRANSACTION);
        assertThat(testSanPham.isTransalated()).isEqualTo(DEFAULT_TRANSALATED);
    }

    @Test
    @Transactional
    public void createSanPhamWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sanPhamRepository.findAll().size();

        // Create the SanPham with an existing ID
        sanPham.setId(1L);
        SanPhamDTO sanPhamDTO = sanPhamMapper.toDto(sanPham);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSanPhamMockMvc.perform(post("/api/san-phams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sanPhamDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SanPham in the database
        List<SanPham> sanPhamList = sanPhamRepository.findAll();
        assertThat(sanPhamList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSanPhams() throws Exception {
        // Initialize the database
        sanPhamRepository.saveAndFlush(sanPham);

        // Get all the sanPhamList
        restSanPhamMockMvc.perform(get("/api/san-phams?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sanPham.getId().intValue())))
            .andExpect(jsonPath("$.[*].image").value(hasItem(DEFAULT_IMAGE)))
            .andExpect(jsonPath("$.[*].isDetailContent").value(hasItem(DEFAULT_IS_DETAIL_CONTENT.booleanValue())))
            .andExpect(jsonPath("$.[*].nam").value(hasItem(DEFAULT_NAM)))
            .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID.intValue())))
            .andExpect(jsonPath("$.[*].rate").value(hasItem(DEFAULT_RATE)))
            .andExpect(jsonPath("$.[*].repurchaseRate").value(hasItem(DEFAULT_REPURCHASE_RATE.intValue())))
            .andExpect(jsonPath("$.[*].sellPrice").value(hasItem(DEFAULT_SELL_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].sellPriceDisplay").value(hasItem(DEFAULT_SELL_PRICE_DISPLAY.intValue())))
            .andExpect(jsonPath("$.[*].startPriceVND").value(hasItem(DEFAULT_START_PRICE_VND.intValue())))
            .andExpect(jsonPath("$.[*].stock").value(hasItem(DEFAULT_STOCK.intValue())))
            .andExpect(jsonPath("$.[*].transaction").value(hasItem(DEFAULT_TRANSACTION)))
            .andExpect(jsonPath("$.[*].transalated").value(hasItem(DEFAULT_TRANSALATED.booleanValue())));
    }

    @Test
    @Transactional
    public void getSanPham() throws Exception {
        // Initialize the database
        sanPhamRepository.saveAndFlush(sanPham);

        // Get the sanPham
        restSanPhamMockMvc.perform(get("/api/san-phams/{id}", sanPham.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sanPham.getId().intValue()))
            .andExpect(jsonPath("$.image").value(DEFAULT_IMAGE))
            .andExpect(jsonPath("$.isDetailContent").value(DEFAULT_IS_DETAIL_CONTENT.booleanValue()))
            .andExpect(jsonPath("$.nam").value(DEFAULT_NAM))
            .andExpect(jsonPath("$.productId").value(DEFAULT_PRODUCT_ID.intValue()))
            .andExpect(jsonPath("$.rate").value(DEFAULT_RATE))
            .andExpect(jsonPath("$.repurchaseRate").value(DEFAULT_REPURCHASE_RATE.intValue()))
            .andExpect(jsonPath("$.sellPrice").value(DEFAULT_SELL_PRICE.intValue()))
            .andExpect(jsonPath("$.sellPriceDisplay").value(DEFAULT_SELL_PRICE_DISPLAY.intValue()))
            .andExpect(jsonPath("$.startPriceVND").value(DEFAULT_START_PRICE_VND.intValue()))
            .andExpect(jsonPath("$.stock").value(DEFAULT_STOCK.intValue()))
            .andExpect(jsonPath("$.transaction").value(DEFAULT_TRANSACTION))
            .andExpect(jsonPath("$.transalated").value(DEFAULT_TRANSALATED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSanPham() throws Exception {
        // Get the sanPham
        restSanPhamMockMvc.perform(get("/api/san-phams/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSanPham() throws Exception {
        // Initialize the database
        sanPhamRepository.saveAndFlush(sanPham);

        int databaseSizeBeforeUpdate = sanPhamRepository.findAll().size();

        // Update the sanPham
        SanPham updatedSanPham = sanPhamRepository.findById(sanPham.getId()).get();
        // Disconnect from session so that the updates on updatedSanPham are not directly saved in db
        em.detach(updatedSanPham);
        updatedSanPham
            .image(UPDATED_IMAGE)
            .isDetailContent(UPDATED_IS_DETAIL_CONTENT)
            .nam(UPDATED_NAM)
            .productId(UPDATED_PRODUCT_ID)
            .rate(UPDATED_RATE)
            .repurchaseRate(UPDATED_REPURCHASE_RATE)
            .sellPrice(UPDATED_SELL_PRICE)
            .sellPriceDisplay(UPDATED_SELL_PRICE_DISPLAY)
            .startPriceVND(UPDATED_START_PRICE_VND)
            .stock(UPDATED_STOCK)
            .transaction(UPDATED_TRANSACTION)
            .transalated(UPDATED_TRANSALATED);
        SanPhamDTO sanPhamDTO = sanPhamMapper.toDto(updatedSanPham);

        restSanPhamMockMvc.perform(put("/api/san-phams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sanPhamDTO)))
            .andExpect(status().isOk());

        // Validate the SanPham in the database
        List<SanPham> sanPhamList = sanPhamRepository.findAll();
        assertThat(sanPhamList).hasSize(databaseSizeBeforeUpdate);
        SanPham testSanPham = sanPhamList.get(sanPhamList.size() - 1);
        assertThat(testSanPham.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testSanPham.isIsDetailContent()).isEqualTo(UPDATED_IS_DETAIL_CONTENT);
        assertThat(testSanPham.getNam()).isEqualTo(UPDATED_NAM);
        assertThat(testSanPham.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
        assertThat(testSanPham.getRate()).isEqualTo(UPDATED_RATE);
        assertThat(testSanPham.getRepurchaseRate()).isEqualTo(UPDATED_REPURCHASE_RATE);
        assertThat(testSanPham.getSellPrice()).isEqualTo(UPDATED_SELL_PRICE);
        assertThat(testSanPham.getSellPriceDisplay()).isEqualTo(UPDATED_SELL_PRICE_DISPLAY);
        assertThat(testSanPham.getStartPriceVND()).isEqualTo(UPDATED_START_PRICE_VND);
        assertThat(testSanPham.getStock()).isEqualTo(UPDATED_STOCK);
        assertThat(testSanPham.getTransaction()).isEqualTo(UPDATED_TRANSACTION);
        assertThat(testSanPham.isTransalated()).isEqualTo(UPDATED_TRANSALATED);
    }

    @Test
    @Transactional
    public void updateNonExistingSanPham() throws Exception {
        int databaseSizeBeforeUpdate = sanPhamRepository.findAll().size();

        // Create the SanPham
        SanPhamDTO sanPhamDTO = sanPhamMapper.toDto(sanPham);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSanPhamMockMvc.perform(put("/api/san-phams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sanPhamDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SanPham in the database
        List<SanPham> sanPhamList = sanPhamRepository.findAll();
        assertThat(sanPhamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSanPham() throws Exception {
        // Initialize the database
        sanPhamRepository.saveAndFlush(sanPham);

        int databaseSizeBeforeDelete = sanPhamRepository.findAll().size();

        // Delete the sanPham
        restSanPhamMockMvc.perform(delete("/api/san-phams/{id}", sanPham.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SanPham> sanPhamList = sanPhamRepository.findAll();
        assertThat(sanPhamList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
