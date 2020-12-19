package com.datngo.web.rest;

import com.datngo.service.SanPhamService;
import com.datngo.web.rest.errors.BadRequestAlertException;
import com.datngo.service.dto.SanPhamDTO;

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
 * REST controller for managing {@link com.datngo.domain.SanPham}.
 */
@RestController
@RequestMapping("/api")
public class SanPhamResource {

    private final Logger log = LoggerFactory.getLogger(SanPhamResource.class);

    private static final String ENTITY_NAME = "sanPham";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SanPhamService sanPhamService;

    public SanPhamResource(SanPhamService sanPhamService) {
        this.sanPhamService = sanPhamService;
    }

    /**
     * {@code POST  /san-phams} : Create a new sanPham.
     *
     * @param sanPhamDTO the sanPhamDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sanPhamDTO, or with status {@code 400 (Bad Request)} if the sanPham has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/san-phams")
    public ResponseEntity<SanPhamDTO> createSanPham(@RequestBody SanPhamDTO sanPhamDTO) throws URISyntaxException {
        log.debug("REST request to save SanPham : {}", sanPhamDTO);
        if (sanPhamDTO.getId() != null) {
            throw new BadRequestAlertException("A new sanPham cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SanPhamDTO result = sanPhamService.save(sanPhamDTO);
        return ResponseEntity.created(new URI("/api/san-phams/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /san-phams} : Updates an existing sanPham.
     *
     * @param sanPhamDTO the sanPhamDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sanPhamDTO,
     * or with status {@code 400 (Bad Request)} if the sanPhamDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sanPhamDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/san-phams")
    public ResponseEntity<SanPhamDTO> updateSanPham(@RequestBody SanPhamDTO sanPhamDTO) throws URISyntaxException {
        log.debug("REST request to update SanPham : {}", sanPhamDTO);
        if (sanPhamDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SanPhamDTO result = sanPhamService.save(sanPhamDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sanPhamDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /san-phams} : get all the sanPhams.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sanPhams in body.
     */
    @GetMapping("/san-phams")
    public List<SanPhamDTO> getAllSanPhams() {
        log.debug("REST request to get all SanPhams");
        return sanPhamService.findAll();
    }

    /**
     * {@code GET  /san-phams/:id} : get the "id" sanPham.
     *
     * @param id the id of the sanPhamDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sanPhamDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/san-phams/{id}")
    public ResponseEntity<SanPhamDTO> getSanPham(@PathVariable Long id) {
        log.debug("REST request to get SanPham : {}", id);
        Optional<SanPhamDTO> sanPhamDTO = sanPhamService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sanPhamDTO);
    }

    /**
     * {@code DELETE  /san-phams/:id} : delete the "id" sanPham.
     *
     * @param id the id of the sanPhamDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/san-phams/{id}")
    public ResponseEntity<Void> deleteSanPham(@PathVariable Long id) {
        log.debug("REST request to delete SanPham : {}", id);
        sanPhamService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
