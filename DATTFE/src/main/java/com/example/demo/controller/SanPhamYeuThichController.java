package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class SanPhamYeuThichController {

    @Autowired
    SanPhamYeuThichDAo sanPhamYeuThichDAo;

    @Autowired
    KhachHangDao khachHangDao;

    @Autowired
    GiayDAO giayDAO;

    @Autowired
    sanphamyeuthichchitietdao sanPhamYeuThichChiTietDao;

    private Authentication authentication;


    @RequestMapping("/qltk-kh/dssanphamyeuthich")
    public String index(Model model, @ModelAttribute San_Pham_Yeu_Thich sanphamyeuthich) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        KhachHang khachHang=  khachHangDao.getKhByEmail(authentication.getName());
        sanphamyeuthich.setKhachHang(khachHang);
        model.addAttribute("khachHang", khachHang);
        model.addAttribute("dssanphamyeuthich", sanPhamYeuThichChiTietDao.getSan_Pham_Yeu_ThichByMa(khachHang.getMa()));
        return "qltk_kh/san_pham_yeu_thich";
    }


    @PostMapping("/add-san-pham-yeu-thich")
    public String themSanPhamYeuThich(@RequestParam("Magiay") String Magiay) {
        Giay giay = giayDAO.getGiayByMa(Magiay);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KhachHang khachHang = khachHangDao.getKhByEmail(authentication.getName());

        if (khachHang != null && giay != null) {
            San_Pham_Yeu_Thich sanPhamYeuThich = sanPhamYeuThichDAo.findByKhachHang(khachHang);

            if (sanPhamYeuThich == null) {
                sanPhamYeuThich = new San_Pham_Yeu_Thich();
                sanPhamYeuThich.setKhachHang(khachHang);
                sanPhamYeuThich.setNgay_tao(LocalDate.now());
                sanPhamYeuThich.setTrangthai(1);

                sanPhamYeuThich = sanPhamYeuThichDAo.save(sanPhamYeuThich);
            }

            San_Pham_Yeu_Thich_Chi_Tiet11 sanPhamYeuThichChiTiet = new San_Pham_Yeu_Thich_Chi_Tiet11();
            sanPhamYeuThichChiTiet.setSanPhamYeuThich(sanPhamYeuThich);
            sanPhamYeuThichChiTiet.setGiay(giay);
            sanPhamYeuThichChiTiet.setNgay_tao(LocalDate.now());
            sanPhamYeuThichChiTiet.setTrangthai(1);

            sanPhamYeuThichChiTietDao.save(sanPhamYeuThichChiTiet);
        }else if (khachHang == null){
            return "redirect:/login";
        }

        return "redirect:/ctsp/" + Magiay;
    }




    @RequestMapping("/remove-san-pham-yeu-thich")
    public String xoaSanPhamYeuThich(@RequestParam("Magiay") String Magiay) {
        // Lấy thông tin sản phẩm và người dùng hiện tại
        Giay giay = giayDAO.getGiayByMa(Magiay);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KhachHang khachHang = khachHangDao.getKhByEmail(authentication.getName());

        // Tìm sản phẩm yêu thích cụ thể của người dùng
        San_Pham_Yeu_Thich sanPhamYeuThich = sanPhamYeuThichDAo.findByKhachHang(khachHang);

        if (sanPhamYeuThich != null) {
            // Tìm chi tiết sản phẩm yêu thích dựa trên sản phẩm và người dùng
            San_Pham_Yeu_Thich_Chi_Tiet11 sanPhamYeuThichChiTiet = sanPhamYeuThichChiTietDao.findByKhachHangAndGiay(khachHang, giay);

            if (sanPhamYeuThichChiTiet != null) {
                // Nếu sản phẩm tồn tại trong danh sách yêu thích, xóa nó
                sanPhamYeuThichChiTietDao.delete(sanPhamYeuThichChiTiet);
            }
        }

        return "redirect:/ctsp/" + Magiay;
    }


}
