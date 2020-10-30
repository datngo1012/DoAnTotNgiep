package com.datngo.web.rest;

import com.datngo.domain.DanhmucDiaBan;
import com.datngo.service.DanhMucDiaBanService;
import com.datngo.service.dto.DiaBanDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DanhMucDiaBanResource {

    @Autowired
    DanhMucDiaBanService danhMucDiaBanService;

    @PostMapping("/get-diabans-theo-macha")
    public ResponseEntity<List<DanhmucDiaBan>> getDsDiaBanTheoMaCha(@RequestBody Map<String, Object> thongTin) {

        String maCha = thongTin.get("maCha").toString();
        Optional<List<DanhmucDiaBan>> danhmucDiaBans =  danhMucDiaBanService.getDsDiaBanTheoMaCha(maCha);
        return ResponseUtil.wrapOrNotFound(danhmucDiaBans);
    }
}
