package com.datngo.web.rest;

import com.datngo.service.QuanLyGiaoDichService;
import com.datngo.web.rest.errors.BadRequestAlertException;
import com.datngo.service.dto.QuanLyGiaoDichDTO;

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
 * REST controller for managing {@link com.datngo.domain.QuanLyGiaoDich}.
 */
@RestController
@RequestMapping("/api")
public class QuanLyGiaoDichResource {

    private final Logger log = LoggerFactory.getLogger(QuanLyGiaoDichResource.class);

    private static final String ENTITY_NAME = "quanLyGiaoDich";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QuanLyGiaoDichService quanLyGiaoDichService;

    public QuanLyGiaoDichResource(QuanLyGiaoDichService quanLyGiaoDichService) {
        this.quanLyGiaoDichService = quanLyGiaoDichService;
    }

    /**
     * {@code POST  /quan-ly-giao-diches} : Create a new quanLyGiaoDich.
     *
     * @param quanLyGiaoDichDTO the quanLyGiaoDichDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new quanLyGiaoDichDTO, or with status {@code 400 (Bad Request)} if the quanLyGiaoDich has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/quan-ly-giao-diches")
    public ResponseEntity<QuanLyGiaoDichDTO> createQuanLyGiaoDich(@RequestBody QuanLyGiaoDichDTO quanLyGiaoDichDTO) throws URISyntaxException {
        log.debug("REST request to save QuanLyGiaoDich : {}", quanLyGiaoDichDTO);
        if (quanLyGiaoDichDTO.getId() != null) {
            throw new BadRequestAlertException("A new quanLyGiaoDich cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuanLyGiaoDichDTO result = quanLyGiaoDichService.save(quanLyGiaoDichDTO);
        return ResponseEntity.created(new URI("/api/quan-ly-giao-diches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /quan-ly-giao-diches} : Updates an existing quanLyGiaoDich.
     *
     * @param quanLyGiaoDichDTO the quanLyGiaoDichDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated quanLyGiaoDichDTO,
     * or with status {@code 400 (Bad Request)} if the quanLyGiaoDichDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the quanLyGiaoDichDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/quan-ly-giao-diches")
    public ResponseEntity<QuanLyGiaoDichDTO> updateQuanLyGiaoDich(@RequestBody QuanLyGiaoDichDTO quanLyGiaoDichDTO) throws URISyntaxException {
        log.debug("REST request to update QuanLyGiaoDich : {}", quanLyGiaoDichDTO);
        if (quanLyGiaoDichDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QuanLyGiaoDichDTO result = quanLyGiaoDichService.save(quanLyGiaoDichDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, quanLyGiaoDichDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /quan-ly-giao-diches} : get all the quanLyGiaoDiches.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of quanLyGiaoDiches in body.
     */
    @GetMapping("/quan-ly-giao-diches")
    public List<QuanLyGiaoDichDTO> getAllQuanLyGiaoDiches() {
        log.debug("REST request to get all QuanLyGiaoDiches");
        return quanLyGiaoDichService.findAll();
    }

    /**
     * {@code GET  /quan-ly-giao-diches/:id} : get the "id" quanLyGiaoDich.
     *
     * @param id the id of the quanLyGiaoDichDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the quanLyGiaoDichDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/quan-ly-giao-diches/{id}")
    public ResponseEntity<QuanLyGiaoDichDTO> getQuanLyGiaoDich(@PathVariable Long id) {
        log.debug("REST request to get QuanLyGiaoDich : {}", id);
        Optional<QuanLyGiaoDichDTO> quanLyGiaoDichDTO = quanLyGiaoDichService.findOne(id);
        return ResponseUtil.wrapOrNotFound(quanLyGiaoDichDTO);
    }

    /**
     * {@code DELETE  /quan-ly-giao-diches/:id} : delete the "id" quanLyGiaoDich.
     *
     * @param id the id of the quanLyGiaoDichDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/quan-ly-giao-diches/{id}")
    public ResponseEntity<Void> deleteQuanLyGiaoDich(@PathVariable Long id) {
        log.debug("REST request to delete QuanLyGiaoDich : {}", id);
        quanLyGiaoDichService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
