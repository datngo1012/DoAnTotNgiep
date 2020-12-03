package com.datngo.web.rest;

import com.datngo.domain.*;
import com.datngo.repository.*;
import com.datngo.security.AuthoritiesConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DonHangResource {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Autowired
    private DonHangRepository donHangRepository;

    @PostMapping("/don-hangs")
    public DonHang createDonHang(Principal principal)
    {
        Long soTien = 80000L;
        User user = userRepository.findOneByLogin(principal.getName()).get();
        NguoiDung nguoiDung = nguoiDungRepository.findOneByUserId(user.getId()).get();
        List<ChiTietSanPham> chiTietSanPhams = chiTietSanPhamRepository.findListChiTietSanPhamByNguoiDungId(nguoiDung.getId());
        for(ChiTietSanPham chiTietSanPham : chiTietSanPhams) {
            DonHang donHang = new DonHang();
            donHang.setTenSanPham(chiTietSanPham.getSanPham().getNam());
            donHang.setSoLuong(Long.valueOf(chiTietSanPham.getSoLuong()));
            donHang.setTrangThai("Đang chờ lấy hàng");
            donHang.setNguoiDungId(nguoiDung.getId());
            donHangRepository.save(donHang);
            soTien += chiTietSanPham.getSoLuong()*chiTietSanPham.getSanPham().getStartPriceVND();
            chiTietSanPhamRepository.deleteById(chiTietSanPham.getId());
        }
        nguoiDung.setSoDu(nguoiDung.getSoDu()- soTien);
        nguoiDungRepository.save(nguoiDung);
        return null;
    }

    @GetMapping("/don-hangs")
    public List<DonHang> getDonHang(Principal principal) {
        User user = userRepository.findOneByLogin(principal.getName()).get();
        NguoiDung nguoiDung = nguoiDungRepository.findOneByUserId(user.getId()).get();
        return donHangRepository.findByNguoiDungId(nguoiDung.getId());
    }

    @GetMapping("/don-hangs/admin")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public List<DonHang> getTatCaDonHang() {
        return donHangRepository.findAll();
    }
}
