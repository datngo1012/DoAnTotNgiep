package com.datngo.service;

import com.datngo.domain.QuanLyGiaoDich;
import com.datngo.repository.QuanLyGiaoDichRepository;
import com.datngo.service.dto.QuanLyGiaoDichDTO;
import com.datngo.service.mapper.QuanLyGiaoDichMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link QuanLyGiaoDich}.
 */
@Service
@Transactional
public class QuanLyGiaoDichService {

    private final Logger log = LoggerFactory.getLogger(QuanLyGiaoDichService.class);

    private final QuanLyGiaoDichRepository quanLyGiaoDichRepository;

    private final QuanLyGiaoDichMapper quanLyGiaoDichMapper;

    public QuanLyGiaoDichService(QuanLyGiaoDichRepository quanLyGiaoDichRepository, QuanLyGiaoDichMapper quanLyGiaoDichMapper) {
        this.quanLyGiaoDichRepository = quanLyGiaoDichRepository;
        this.quanLyGiaoDichMapper = quanLyGiaoDichMapper;
    }

    /**
     * Save a quanLyGiaoDich.
     *
     * @param quanLyGiaoDichDTO the entity to save.
     * @return the persisted entity.
     */
    public QuanLyGiaoDichDTO save(QuanLyGiaoDichDTO quanLyGiaoDichDTO) {
        log.debug("Request to save QuanLyGiaoDich : {}", quanLyGiaoDichDTO);
        QuanLyGiaoDich quanLyGiaoDich = quanLyGiaoDichMapper.toEntity(quanLyGiaoDichDTO);
        quanLyGiaoDich = quanLyGiaoDichRepository.save(quanLyGiaoDich);
        return quanLyGiaoDichMapper.toDto(quanLyGiaoDich);
    }

    /**
     * Get all the quanLyGiaoDiches.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<QuanLyGiaoDichDTO> findAll() {
        log.debug("Request to get all QuanLyGiaoDiches");
        return quanLyGiaoDichRepository.findAll().stream()
            .map(quanLyGiaoDichMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one quanLyGiaoDich by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<QuanLyGiaoDichDTO> findOne(Long id) {
        log.debug("Request to get QuanLyGiaoDich : {}", id);
        return quanLyGiaoDichRepository.findById(id)
            .map(quanLyGiaoDichMapper::toDto);
    }

    /**
     * Delete the quanLyGiaoDich by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete QuanLyGiaoDich : {}", id);
        quanLyGiaoDichRepository.deleteById(id);
    }
}
