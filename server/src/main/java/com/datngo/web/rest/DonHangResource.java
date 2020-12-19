package com.datngo.web.rest;

import com.datngo.domain.*;
import com.datngo.repository.*;
import com.datngo.security.AuthoritiesConstants;
import com.datngo.service.dto.DonHangDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger log = LoggerFactory.getLogger(DonHangResource.class);


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Autowired
    private DonHangRepository donHangRepository;

    @Autowired
    private ThongBaoRepository thongBaoRepository;

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
            donHang.setLink(chiTietSanPham.getSanPham().getTransaction());
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
        String tenSP = thongTin.get("tenSP").toString();
        Long nguoiDungId = Long.valueOf(thongTin.get("nguoiDungId").toString());
        DonHang donHang = donHangRepository.findById(donHangId).get();
        donHang.setTrangThai(trangThai);

        ThongBao thongBao = new ThongBao();
        thongBao.setNgayTao(LocalDateTime.now());
        thongBao.setNguoiDungId(nguoiDungId);
        if(trangThai.equals("Hết hàng")) {
            thongBao.setNoiDung("Xin lỗi sản phẩm "+ tenSP +" hiện đã hết hàng");
        }
        else if(trangThai.equals("Đã nhận hàng")) {
            thongBao.setNoiDung("Bạn đã nhận sản phẩm "+ tenSP +". Có gì thắc mắc xin vui lòng liên hệ 0986512835");
        }
        thongBaoRepository.save(thongBao);
        donHangRepository.save(donHang);
    }

    @PostMapping("/don-hang/order")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public List<DonHangDTO> orderDonHang() {
        List<DonHangDTO> donHangDTOS = new ArrayList<>();
        List<Object[]> datas = donHangRepository.findOrder();
        for(Object[] item : datas) {
            DonHangDTO donHangDTO = new DonHangDTO(Long.valueOf(item[0].toString()),item[1].toString(),Long.valueOf(item[2].toString()), Long.valueOf(item[3].toString()), Long.valueOf(item[4].toString())
            ,item[5].toString(), item[6].toString(),item[7].toString(), item[8].toString(), item[9].toString(), item[10].toString(), item[11].toString(), Long.valueOf(item[12].toString()), item[13].toString());
            donHangDTOS.add(donHangDTO);
        }
        return donHangDTOS;
    }
}
