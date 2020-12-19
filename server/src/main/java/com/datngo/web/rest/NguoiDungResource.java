package com.datngo.web.rest;

import com.datngo.domain.NguoiDung;
import com.datngo.domain.ThongBao;
import com.datngo.domain.User;
import com.datngo.repository.NguoiDungRepository;
import com.datngo.repository.ThongBaoRepository;
import com.datngo.repository.UserRepository;
import com.datngo.security.AuthoritiesConstants;
import com.datngo.service.NguoiDungService;
import com.datngo.service.dto.NguoiDungDTO;
import com.datngo.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * REST controller for managing {@link com.datngo.domain.NguoiDung}.
 */
@RestController
@RequestMapping("/api")
public class NguoiDungResource {

    private final Logger log = LoggerFactory.getLogger(NguoiDungResource.class);

    private static final String ENTITY_NAME = "nguoiDung";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NguoiDungService nguoiDungService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private ThongBaoRepository thongBaoRepository;

    public NguoiDungResource(NguoiDungService nguoiDungService) {
        this.nguoiDungService = nguoiDungService;
    }

    /**
     * {@code POST  /nguoi-dungs} : Create a new nguoiDung.
     *
     * @param nguoiDungDTO the nguoiDungDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nguoiDungDTO, or with status {@code 400 (Bad Request)} if the nguoiDung has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nguoi-dungs")
    public ResponseEntity<NguoiDungDTO> createNguoiDung(@RequestBody NguoiDungDTO nguoiDungDTO) throws URISyntaxException {
        log.debug("REST request to save NguoiDung : {}", nguoiDungDTO);
        if (nguoiDungDTO.getId() != null) {
            throw new BadRequestAlertException("A new nguoiDung cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NguoiDungDTO result = nguoiDungService.save(nguoiDungDTO);
        return ResponseEntity.created(new URI("/api/nguoi-dungs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nguoi-dungs} : Updates an existing nguoiDung.
     *
     * @param nguoiDungDTO the nguoiDungDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nguoiDungDTO,
     * or with status {@code 400 (Bad Request)} if the nguoiDungDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nguoiDungDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nguoi-dungs")
    public ResponseEntity<NguoiDungDTO> updateNguoiDung(@RequestBody NguoiDungDTO nguoiDungDTO) throws URISyntaxException {
        log.debug("REST request to update NguoiDung : {}", nguoiDungDTO);
        if (nguoiDungDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NguoiDungDTO result = nguoiDungService.save(nguoiDungDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nguoiDungDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nguoi-dungs} : get all the nguoiDungs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nguoiDungs in body.
     */
    @GetMapping("/nguoi-dungs")
    public List<NguoiDungDTO> getAllNguoiDungs() {
        log.debug("REST request to get all NguoiDungs");
        return nguoiDungService.findAll();
    }

    /**
     * {@code GET  /nguoi-dungs/:id} : get the "id" nguoiDung.
     *
     * @param id the id of the nguoiDungDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nguoiDungDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nguoi-dungs/{id}")
    public ResponseEntity<NguoiDungDTO> getNguoiDung(@PathVariable Long id) {
        log.debug("REST request to get NguoiDung : {}", id);
        Optional<NguoiDungDTO> nguoiDungDTO = nguoiDungService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nguoiDungDTO);
    }

    /**
     * {@code DELETE  /nguoi-dungs/:id} : delete the "id" nguoiDung.
     *
     * @param id the id of the nguoiDungDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nguoi-dungs/{id}")
    public ResponseEntity<Void> deleteNguoiDung(@PathVariable Long id) {
        log.debug("REST request to delete NguoiDung : {}", id);
        nguoiDungService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @PostMapping("/nguoi-dung-by-userid")
    public ResponseEntity<NguoiDungDTO> getNguoiDungByUserId(@RequestBody Map<String, Object> thongTin) {
        Long userId = Long.valueOf(thongTin.get("userId").toString());
        Optional<NguoiDungDTO> nguoiDungDTO = nguoiDungService.getNguoiDungByUserId(userId);
        return ResponseUtil.wrapOrNotFound(nguoiDungDTO);
    }

    @PostMapping("/nguoi-dung-by-nguoidungid")
    public ResponseEntity<NguoiDungDTO> getNguoiDungByNguoiDungId(@RequestBody Map<String, Object> thongTin) {
        Long userId = Long.valueOf(thongTin.get("userId").toString());
        Optional<NguoiDungDTO> nguoiDungDTO = nguoiDungService.findOne(userId);
        return ResponseUtil.wrapOrNotFound(nguoiDungDTO);
    }

    @PostMapping("/users/noptien")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public void nopTien(@RequestBody Map<String, Object> thongTin) {
        Long userid = Long.valueOf(thongTin.get("userid").toString());
        Long soTien = Long.valueOf(thongTin.get("soTien").toString());
        ThongBao thongBao = new ThongBao();
        thongBao.setNgayTao(LocalDateTime.now());
        thongBao.setNguoiDungId(userid);
        thongBao.setNoiDung("Quý khách đã nộp "+ soTien +" vào tài khoản");
        thongBaoRepository.save(thongBao);
        nguoiDungService.nopTien(userid, soTien);
    }

    @PostMapping("nguoi-dung/thong-bao")
    public List<ThongBao> getDonHang(Principal principal) {
        User user = userRepository.findOneByLogin(principal.getName()).get();
        NguoiDung nguoiDung = nguoiDungRepository.findOneByUserId(user.getId()).get();
        return thongBaoRepository.findByNguoiDungIdOrderByIdDesc(nguoiDung.getId());
    }
}
