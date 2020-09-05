package com.datngo.service;

import com.datngo.domain.DiaBan;
import com.datngo.repository.DiaBanRepository;
import com.datngo.service.dto.DiaBanDTO;
import com.datngo.service.mapper.DiaBanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link DiaBan}.
 */
@Service
@Transactional
public class DiaBanService {

    private final Logger log = LoggerFactory.getLogger(DiaBanService.class);

    private final DiaBanRepository diaBanRepository;

    private final DiaBanMapper diaBanMapper;

    public DiaBanService(DiaBanRepository diaBanRepository, DiaBanMapper diaBanMapper) {
        this.diaBanRepository = diaBanRepository;
        this.diaBanMapper = diaBanMapper;
    }

    /**
     * Save a diaBan.
     *
     * @param diaBanDTO the entity to save.
     * @return the persisted entity.
     */
    public DiaBanDTO save(DiaBanDTO diaBanDTO) {
        log.debug("Request to save DiaBan : {}", diaBanDTO);
        DiaBan diaBan = diaBanMapper.toEntity(diaBanDTO);
        diaBan = diaBanRepository.save(diaBan);
        return diaBanMapper.toDto(diaBan);
    }

    /**
     * Get all the diaBans.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DiaBanDTO> findAll() {
        log.debug("Request to get all DiaBans");
        return diaBanRepository.findAll().stream()
            .map(diaBanMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one diaBan by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DiaBanDTO> findOne(Long id) {
        log.debug("Request to get DiaBan : {}", id);
        return diaBanRepository.findById(id)
            .map(diaBanMapper::toDto);
    }

    /**
     * Delete the diaBan by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DiaBan : {}", id);
        diaBanRepository.deleteById(id);
    }
}
