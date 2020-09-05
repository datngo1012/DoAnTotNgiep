package com.datngo.service;

import com.datngo.domain.ChiTietSanPham;
import com.datngo.repository.ChiTietSanPhamRepository;
import com.datngo.service.dto.ChiTietSanPhamDTO;
import com.datngo.service.mapper.ChiTietSanPhamMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ChiTietSanPham}.
 */
@Service
@Transactional
public class ChiTietSanPhamService {

    private final Logger log = LoggerFactory.getLogger(ChiTietSanPhamService.class);

    private final ChiTietSanPhamRepository chiTietSanPhamRepository;

    private final ChiTietSanPhamMapper chiTietSanPhamMapper;

    public ChiTietSanPhamService(ChiTietSanPhamRepository chiTietSanPhamRepository, ChiTietSanPhamMapper chiTietSanPhamMapper) {
        this.chiTietSanPhamRepository = chiTietSanPhamRepository;
        this.chiTietSanPhamMapper = chiTietSanPhamMapper;
    }

    /**
     * Save a chiTietSanPham.
     *
     * @param chiTietSanPhamDTO the entity to save.
     * @return the persisted entity.
     */
    public ChiTietSanPhamDTO save(ChiTietSanPhamDTO chiTietSanPhamDTO) {
        log.debug("Request to save ChiTietSanPham : {}", chiTietSanPhamDTO);
        ChiTietSanPham chiTietSanPham = chiTietSanPhamMapper.toEntity(chiTietSanPhamDTO);
        chiTietSanPham = chiTietSanPhamRepository.save(chiTietSanPham);
        return chiTietSanPhamMapper.toDto(chiTietSanPham);
    }

    /**
     * Get all the chiTietSanPhams.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ChiTietSanPhamDTO> findAll() {
        log.debug("Request to get all ChiTietSanPhams");
        return chiTietSanPhamRepository.findAll().stream()
            .map(chiTietSanPhamMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one chiTietSanPham by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ChiTietSanPhamDTO> findOne(Long id) {
        log.debug("Request to get ChiTietSanPham : {}", id);
        return chiTietSanPhamRepository.findById(id)
            .map(chiTietSanPhamMapper::toDto);
    }

    /**
     * Delete the chiTietSanPham by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ChiTietSanPham : {}", id);
        chiTietSanPhamRepository.deleteById(id);
    }
}
