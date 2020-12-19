package com.datngo.web.rest;

import com.datngo.service.ChiTietSanPhamService;
import com.datngo.web.rest.errors.BadRequestAlertException;
import com.datngo.service.dto.ChiTietSanPhamDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.datngo.domain.ChiTietSanPham}.
 */
@RestController
@RequestMapping("/api")
public class ChiTietSanPhamResource {

    private final Logger log = LoggerFactory.getLogger(ChiTietSanPhamResource.class);

    private static final String ENTITY_NAME = "chiTietSanPham";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ChiTietSanPhamService chiTietSanPhamService;

    public ChiTietSanPhamResource(ChiTietSanPhamService chiTietSanPhamService) {
        this.chiTietSanPhamService = chiTietSanPhamService;
    }

    /**
     * {@code POST  /chi-tiet-san-phams} : Create a new chiTietSanPham.
     *
     * @param chiTietSanPhamDTO the chiTietSanPhamDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new chiTietSanPhamDTO, or with status {@code 400 (Bad Request)} if the chiTietSanPham has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/chi-tiet-san-phams")
    public ResponseEntity<ChiTietSanPhamDTO> createChiTietSanPham(@RequestBody ChiTietSanPhamDTO chiTietSanPhamDTO) throws URISyntaxException {
        log.debug("REST request to save ChiTietSanPham : {}", chiTietSanPhamDTO);
        if (chiTietSanPhamDTO.getId() != null) {
            throw new BadRequestAlertException("A new chiTietSanPham cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ChiTietSanPhamDTO result = chiTietSanPhamService.save(chiTietSanPhamDTO);
        return ResponseEntity.created(new URI("/api/chi-tiet-san-phams/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /chi-tiet-san-phams} : Updates an existing chiTietSanPham.
     *
     * @param chiTietSanPhamDTO the chiTietSanPhamDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chiTietSanPhamDTO,
     * or with status {@code 400 (Bad Request)} if the chiTietSanPhamDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the chiTietSanPhamDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/chi-tiet-san-phams")
    public ResponseEntity<ChiTietSanPhamDTO> updateChiTietSanPham(@RequestBody ChiTietSanPhamDTO chiTietSanPhamDTO) throws URISyntaxException {
        log.debug("REST request to update ChiTietSanPham : {}", chiTietSanPhamDTO);
        if (chiTietSanPhamDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ChiTietSanPhamDTO result = chiTietSanPhamService.save(chiTietSanPhamDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, chiTietSanPhamDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /chi-tiet-san-phams} : get all the chiTietSanPhams.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of chiTietSanPhams in body.
     */
    @GetMapping("/chi-tiet-san-phams")
    public List<ChiTietSanPhamDTO> getAllChiTietSanPhams() {
        log.debug("REST request to get all ChiTietSanPhams");
        return chiTietSanPhamService.findAll();
    }

    /**
     * {@code GET  /chi-tiet-san-phams/:id} : get the "id" chiTietSanPham.
     *
     * @param id the id of the chiTietSanPhamDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the chiTietSanPhamDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/chi-tiet-san-phams/{id}")
    public ResponseEntity<ChiTietSanPhamDTO> getChiTietSanPham(@PathVariable Long id) {
        log.debug("REST request to get ChiTietSanPham : {}", id);
        Optional<ChiTietSanPhamDTO> chiTietSanPhamDTO = chiTietSanPhamService.findOne(id);
        return ResponseUtil.wrapOrNotFound(chiTietSanPhamDTO);
    }

    /**
     * {@code DELETE  /chi-tiet-san-phams/:id} : delete the "id" chiTietSanPham.
     *
     * @param id the id of the chiTietSanPhamDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/chi-tiet-san-phams/{id}")
    public ResponseEntity<Void> deleteChiTietSanPham(@PathVariable Long id) {
        log.debug("REST request to delete ChiTietSanPham : {}", id);
        chiTietSanPhamService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
