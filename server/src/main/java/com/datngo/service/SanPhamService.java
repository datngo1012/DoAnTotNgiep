package com.datngo.service;

import com.datngo.domain.SanPham;
import com.datngo.repository.SanPhamRepository;
import com.datngo.service.dto.SanPhamDTO;
import com.datngo.service.mapper.SanPhamMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link SanPham}.
 */
@Service
@Transactional
public class SanPhamService {

    private final Logger log = LoggerFactory.getLogger(SanPhamService.class);

    private final SanPhamRepository sanPhamRepository;

    private final SanPhamMapper sanPhamMapper;

    public SanPhamService(SanPhamRepository sanPhamRepository, SanPhamMapper sanPhamMapper) {
        this.sanPhamRepository = sanPhamRepository;
        this.sanPhamMapper = sanPhamMapper;
    }

    /**
     * Save a sanPham.
     *
     * @param sanPhamDTO the entity to save.
     * @return the persisted entity.
     */
    public SanPhamDTO save(SanPhamDTO sanPhamDTO) {
        log.debug("Request to save SanPham : {}", sanPhamDTO);
        SanPham sanPham = sanPhamMapper.toEntity(sanPhamDTO);
        sanPham = sanPhamRepository.save(sanPham);
        return sanPhamMapper.toDto(sanPham);
    }

    /**
     * Get all the sanPhams.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<SanPhamDTO> findAll() {
        log.debug("Request to get all SanPhams");
        return sanPhamRepository.findAll().stream()
            .map(sanPhamMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one sanPham by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SanPhamDTO> findOne(Long id) {
        log.debug("Request to get SanPham : {}", id);
        return sanPhamRepository.findById(id)
            .map(sanPhamMapper::toDto);
    }

    /**
     * Delete the sanPham by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SanPham : {}", id);
        sanPhamRepository.deleteById(id);
    }
}
