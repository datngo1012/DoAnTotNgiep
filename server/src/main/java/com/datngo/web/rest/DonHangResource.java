package com.datngo.web.rest;

import com.datngo.domain.ChiTietSanPham;
import com.datngo.domain.DonHang;
import com.datngo.domain.NguoiDung;
import com.datngo.domain.User;
import com.datngo.repository.ChiTietSanPhamRepository;
import com.datngo.repository.DonHangRepository;
import com.datngo.repository.NguoiDungRepository;
import com.datngo.repository.UserRepository;
import com.datngo.security.AuthoritiesConstants;
import com.datngo.service.dto.DonHangDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        double tienCoc =0L;
        User user = userRepository.findOneByLogin(principal.getName()).get();
        NguoiDung nguoiDung = nguoiDungRepository.findOneByUserId(user.getId()).get();
        List<ChiTietSanPham> chiTietSanPhams = chiTietSanPhamRepository.findListChiTietSanPhamByNguoiDungId(nguoiDung.getId());
        for(ChiTietSanPham chiTietSanPham : chiTietSanPhams) {
            DonHang donHang = new DonHang();
            donHang.setTenSanPham(chiTietSanPham.getSanPham().getNam());
            donHang.setSoLuong(Long.valueOf(chiTietSanPham.getSoLuong()));
            donHang.setNgayMua(LocalDateTime.now());
            donHang.setTrangThai("Đang chờ lấy hàng");
            donHang.setNguoiDungId(nguoiDung.getId());
            soTien += chiTietSanPham.getSoLuong()*chiTietSanPham.getSanPham().getStartPriceVND();
            tienCoc = (soTien + soTien*0.01)*0.7;
            donHang.setSoTien((long) (soTien + soTien*0.01));
            donHang.setSoTienDangThieu((long) ((soTien + soTien*0.01)*0.3));
            donHangRepository.save(donHang);
            chiTietSanPhamRepository.deleteById(chiTietSanPham.getId());
        }
        nguoiDung.setSoDu((long) (nguoiDung.getSoDu()- tienCoc));
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

    @PostMapping("/don-hangs/user")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public List<DonHang> getDonHangByNguoiDungId(@RequestBody Map<String, Object> thongTin) {
        Long nguoiDungId = Long.valueOf(thongTin.get("nguoiDungId").toString());
        return donHangRepository.findByNguoiDungId(nguoiDungId);
    }

    @PostMapping("/don-hang/capnhat")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public void capNhatDonHang(@RequestBody Map<String, Object> thongTin) {
        Long donHangId = Long.valueOf(thongTin.get("donHangId").toString());
        String trangThai = thongTin.get("trangThai").toString();
        DonHang donHang = donHangRepository.findById(donHangId).get();
        donHang.setTrangThai(trangThai);
        donHangRepository.save(donHang);
    }

    @PostMapping("/don-hang/order")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public List<DonHangDTO> orderDonHang() {
        List<DonHangDTO> donHangDTOS = new ArrayList<>();
        List<Object[]> datas = donHangRepository.findOrder();
        for(Object[] item : datas) {
            DonHangDTO donHangDTO = new DonHangDTO(item[0].toString(),item[2].toString(), item[3].toString(), item[4].toString()
            ,item[5].toString(), item[6].toString(),item[7].toString(), item[8].toString(), item[9].toString());
        }
        return donHangDTOS;
    }
}
