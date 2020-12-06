package com.datngo.service;

import com.datngo.domain.NguoiDung;
import com.datngo.repository.NguoiDungRepository;
import com.datngo.service.dto.NguoiDungDTO;
import com.datngo.service.mapper.NguoiDungMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link NguoiDung}.
 */
@Service
@Transactional
public class NguoiDungService {

    private final Logger log = LoggerFactory.getLogger(NguoiDungService.class);

    private final NguoiDungRepository nguoiDungRepository;

    private final NguoiDungMapper nguoiDungMapper;

    public NguoiDungService(NguoiDungRepository nguoiDungRepository, NguoiDungMapper nguoiDungMapper) {
        this.nguoiDungRepository = nguoiDungRepository;
        this.nguoiDungMapper = nguoiDungMapper;
    }

    /**
     * Save a nguoiDung.
     *
     * @param nguoiDungDTO the entity to save.
     * @return the persisted entity.
     */
    public NguoiDungDTO save(NguoiDungDTO nguoiDungDTO) {
        log.debug("Request to save NguoiDung : {}", nguoiDungDTO);

        NguoiDung nguoiDungUpdate = nguoiDungRepository.findById(nguoiDungDTO.getId()).get();
        nguoiDungUpdate.setSdt(nguoiDungDTO.getSdt());
        nguoiDungUpdate.setHoTen(nguoiDungDTO.getHoTen());
        nguoiDungUpdate.setDiaChi(nguoiDungDTO.getDiaChi());
        nguoiDungUpdate.setNgaySinh(nguoiDungDTO.getNgaySinh());
        nguoiDungUpdate.setThangSinh(nguoiDungDTO.getThangSinh());
        nguoiDungUpdate.setNamSinh(nguoiDungDTO.getNamSinh());
        nguoiDungUpdate.setTinhThanh(nguoiDungDTO.getTinhThanh());
        nguoiDungUpdate.setQuanHuyen(nguoiDungDTO.getQuanHuyen());
        nguoiDungUpdate.setXaPhuong(nguoiDungDTO.getXaPhuong());
        nguoiDungUpdate.setGioiTinh(nguoiDungDTO.getGioiTinh());
        nguoiDungUpdate = nguoiDungRepository.save(nguoiDungUpdate);
        return nguoiDungMapper.toDto(nguoiDungUpdate);
    }

    /**
     * Get all the nguoiDungs.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<NguoiDungDTO> findAll() {
        log.debug("Request to get all NguoiDungs");
        return nguoiDungRepository.findAll().stream()
            .map(nguoiDungMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one nguoiDung by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NguoiDungDTO> findOne(Long id) {
        log.debug("Request to get NguoiDung : {}", id);
        return nguoiDungRepository.findById(id)
            .map(nguoiDungMapper::toDto);
    }

    public Optional<NguoiDungDTO> getNguoiDungByUserId(Long userid) {
        return nguoiDungRepository.findOneByUserId(userid)
            .map(nguoiDungMapper::toDto);
    }

    /**
     * Delete the nguoiDung by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete NguoiDung : {}", id);
        nguoiDungRepository.deleteById(id);
    }

    public void nopTien(Long userid, Long soTien) {
        NguoiDung nguoiDung = nguoiDungRepository.findById(userid).get();
        nguoiDung.setSoDu(nguoiDung.getSoDu() + soTien);
        nguoiDungRepository.save(nguoiDung);
    }
}
