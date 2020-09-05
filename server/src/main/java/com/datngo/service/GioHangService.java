package com.datngo.service;

import com.datngo.domain.GioHang;
import com.datngo.repository.GioHangRepository;
import com.datngo.service.dto.GioHangDTO;
import com.datngo.service.mapper.GioHangMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link GioHang}.
 */
@Service
@Transactional
public class GioHangService {

    private final Logger log = LoggerFactory.getLogger(GioHangService.class);

    private final GioHangRepository gioHangRepository;

    private final GioHangMapper gioHangMapper;

    public GioHangService(GioHangRepository gioHangRepository, GioHangMapper gioHangMapper) {
        this.gioHangRepository = gioHangRepository;
        this.gioHangMapper = gioHangMapper;
    }

    /**
     * Save a gioHang.
     *
     * @param gioHangDTO the entity to save.
     * @return the persisted entity.
     */
    public GioHangDTO save(GioHangDTO gioHangDTO) {
        log.debug("Request to save GioHang : {}", gioHangDTO);
        GioHang gioHang = gioHangMapper.toEntity(gioHangDTO);
        gioHang = gioHangRepository.save(gioHang);
        return gioHangMapper.toDto(gioHang);
    }

    /**
     * Get all the gioHangs.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<GioHangDTO> findAll() {
        log.debug("Request to get all GioHangs");
        return gioHangRepository.findAll().stream()
            .map(gioHangMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one gioHang by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GioHangDTO> findOne(Long id) {
        log.debug("Request to get GioHang : {}", id);
        return gioHangRepository.findById(id)
            .map(gioHangMapper::toDto);
    }

    /**
     * Delete the gioHang by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete GioHang : {}", id);
        gioHangRepository.deleteById(id);
    }
}
