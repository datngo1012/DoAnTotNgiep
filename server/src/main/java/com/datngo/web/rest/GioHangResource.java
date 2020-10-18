package com.datngo.web.rest;

import com.datngo.domain.GioHang;
import com.datngo.service.GioHangService;
import com.datngo.web.rest.errors.BadRequestAlertException;
import com.datngo.service.dto.GioHangDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.datngo.domain.GioHang}.
 */
@RestController
@RequestMapping("/api")
public class GioHangResource {

    private final Logger log = LoggerFactory.getLogger(GioHangResource.class);

    private static final String ENTITY_NAME = "gioHang";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GioHangService gioHangService;

    public GioHangResource(GioHangService gioHangService) {
        this.gioHangService = gioHangService;
    }

    /**
     * {@code POST  /gio-hangs} : Create a new gioHang.
     *
     * @param gioHangDTO the gioHangDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new gioHangDTO, or with status {@code 400 (Bad Request)} if the gioHang has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/gio-hangs")
    public ResponseEntity<GioHangDTO> createGioHang(@RequestBody GioHangDTO gioHangDTO, Principal principal) throws URISyntaxException {
        log.debug("REST request to save GioHang : {}", gioHangDTO);
        if (gioHangDTO.getId() != null) {
            throw new BadRequestAlertException("A new gioHang cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GioHangDTO result = gioHangService.save(gioHangDTO);
        return ResponseEntity.created(new URI("/api/gio-hangs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /gio-hangs} : Updates an existing gioHang.
     *
     * @param gioHangDTO the gioHangDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gioHangDTO,
     * or with status {@code 400 (Bad Request)} if the gioHangDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the gioHangDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/gio-hangs")
    public ResponseEntity<GioHangDTO> updateGioHang(@RequestBody GioHangDTO gioHangDTO) throws URISyntaxException {
        log.debug("REST request to update GioHang : {}", gioHangDTO);
        if (gioHangDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GioHangDTO result = gioHangService.save(gioHangDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, gioHangDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /gio-hangs} : get all the gioHangs.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gioHangs in body.
     */
    @GetMapping("/gio-hangs")
    public List<GioHangDTO> getAllGioHangs() {
        log.debug("REST request to get all GioHangs");
        return gioHangService.findAll();
    }

    /**
     * {@code GET  /gio-hangs/:id} : get the "id" gioHang.
     *
     * @param id the id of the gioHangDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gioHangDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gio-hangs/{id}")
    public ResponseEntity<GioHangDTO> getGioHang(@PathVariable Long id) {
        log.debug("REST request to get GioHang : {}", id);
        Optional<GioHangDTO> gioHangDTO = gioHangService.findOne(id);
        return ResponseUtil.wrapOrNotFound(gioHangDTO);
    }

    /**
     * {@code DELETE  /gio-hangs/:id} : delete the "id" gioHang.
     *
     * @param id the id of the gioHangDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/gio-hangs/{id}")
    public ResponseEntity<Void> deleteGioHang(@PathVariable Long id) {
        log.debug("REST request to delete GioHang : {}", id);
        gioHangService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/gio-hangs-by-nguoidung/{nguoiDungId}")
    public ResponseEntity<GioHang> getGioHangByNguoiDung(@PathVariable Long nguoiDungId) {
        Optional<GioHang> gioHang = gioHangService.findOneByNguoiDung(nguoiDungId);
        return ResponseUtil.wrapOrNotFound(gioHang);
    }
}
