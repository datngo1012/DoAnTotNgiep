package com.datngo.web.rest;

import com.datngo.service.DiaBanService;
import com.datngo.web.rest.errors.BadRequestAlertException;
import com.datngo.service.dto.DiaBanDTO;

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
 * REST controller for managing {@link com.datngo.domain.DiaBan}.
 */
@RestController
@RequestMapping("/api")
public class DiaBanResource {

    private final Logger log = LoggerFactory.getLogger(DiaBanResource.class);

    private static final String ENTITY_NAME = "diaBan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DiaBanService diaBanService;

    public DiaBanResource(DiaBanService diaBanService) {
        this.diaBanService = diaBanService;
    }

    /**
     * {@code POST  /dia-bans} : Create a new diaBan.
     *
     * @param diaBanDTO the diaBanDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new diaBanDTO, or with status {@code 400 (Bad Request)} if the diaBan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dia-bans")
    public ResponseEntity<DiaBanDTO> createDiaBan(@RequestBody DiaBanDTO diaBanDTO) throws URISyntaxException {
        log.debug("REST request to save DiaBan : {}", diaBanDTO);
        if (diaBanDTO.getId() != null) {
            throw new BadRequestAlertException("A new diaBan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DiaBanDTO result = diaBanService.save(diaBanDTO);
        return ResponseEntity.created(new URI("/api/dia-bans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dia-bans} : Updates an existing diaBan.
     *
     * @param diaBanDTO the diaBanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated diaBanDTO,
     * or with status {@code 400 (Bad Request)} if the diaBanDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the diaBanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dia-bans")
    public ResponseEntity<DiaBanDTO> updateDiaBan(@RequestBody DiaBanDTO diaBanDTO) throws URISyntaxException {
        log.debug("REST request to update DiaBan : {}", diaBanDTO);
        if (diaBanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DiaBanDTO result = diaBanService.save(diaBanDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, diaBanDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dia-bans} : get all the diaBans.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of diaBans in body.
     */
    @GetMapping("/dia-bans")
    public List<DiaBanDTO> getAllDiaBans() {
        log.debug("REST request to get all DiaBans");
        return diaBanService.findAll();
    }

    /**
     * {@code GET  /dia-bans/:id} : get the "id" diaBan.
     *
     * @param id the id of the diaBanDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the diaBanDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dia-bans/{id}")
    public ResponseEntity<DiaBanDTO> getDiaBan(@PathVariable Long id) {
        log.debug("REST request to get DiaBan : {}", id);
        Optional<DiaBanDTO> diaBanDTO = diaBanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(diaBanDTO);
    }

    /**
     * {@code DELETE  /dia-bans/:id} : delete the "id" diaBan.
     *
     * @param id the id of the diaBanDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dia-bans/{id}")
    public ResponseEntity<Void> deleteDiaBan(@PathVariable Long id) {
        log.debug("REST request to delete DiaBan : {}", id);
        diaBanService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
