package com.datngo.service;

import com.datngo.domain.DanhmucDiaBan;
import com.datngo.repository.DanhMucDiaBanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DanhMucDiaBanService {

    @Autowired
    DanhMucDiaBanRepository danhMucDiaBanRepository;

    public Optional<List<DanhmucDiaBan>> getDsDiaBanTheoMaCha(String maCha) {
        return danhMucDiaBanRepository.getDsDiaBanTheoMaCha(maCha);
    }
}
