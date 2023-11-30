package com.example.demo.controller;

import com.example.demo.entity.GiayDTO;
import com.example.demo.entity.HoaDon;
import com.example.demo.entity.HoaDonChiTiet;
import com.example.demo.entity.PageDTO;
import com.example.demo.repository.HoaDonChiTietDAO;
import com.example.demo.repository.HoaDonChiTietRepo;
import com.example.demo.repository.HoaDonDAO;
import com.example.demo.repository.HoaDonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class BanHangController {

    @Autowired
    private HoaDonRepo hoaDonRepo;

    @Autowired
    private HoaDonDAO hoaDonDAO;

    @Autowired
    private HoaDonChiTietRepo hoaDonChiTietRepo;

    @Autowired
    private HoaDonChiTietDAO hoaDonChiTietDAO;

    //Hiển thị all
    @RequestMapping("/admin/ban-hang")
    public String hienThiAll(@RequestParam("page0") Optional<Integer> page0,
                             @RequestParam("page1") Optional<Integer> page1,
                             @RequestParam("page2") Optional<Integer> page2,
                             @RequestParam("page3") Optional<Integer> page3,
                             @RequestParam("page4") Optional<Integer> page4,
                             Model model) {
        PageDTO<HoaDon> hoaDonTrangThai0 = hoaDonRepo.getPageHDByTrangThai(0, page0.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai1 = hoaDonRepo.getPageHDByTrangThai(1, page1.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai2 = hoaDonRepo.getPageHDByTrangThai(2, page2.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai3 = hoaDonRepo.getPageHDByTrangThai(3, page3.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai4 = hoaDonRepo.getPageHDByTrangThai(4, page4.orElse(0));
        model.addAttribute("PageHoaDonTT0", hoaDonTrangThai0); // Page hóa đơn đang chờ
        model.addAttribute("PageHoaDonTT1", hoaDonTrangThai1); // Page hóa đơn chờ giao
        model.addAttribute("PageHoaDonTT2", hoaDonTrangThai2); // Page hóa đơn đang giao
        model.addAttribute("PageHoaDonTT3", hoaDonTrangThai3); // Page hóa đơn hoàn thành
        model.addAttribute("PageHoaDonTT4", hoaDonTrangThai4); // Page hóa đơn đã hủy
        return "banhangtaiquay/ban_hang";
    }

    @GetMapping("/admin/ban-hang/getHoaDonChiTiet/{maHoaDon}")
    @ResponseBody
    public List<GiayDTO> getHoaDonChiTiet(@PathVariable String maHoaDon) {
        List<HoaDonChiTiet> hoaDonChiTiet = hoaDonChiTietDAO.findHoaDonChiTietByMaHD(maHoaDon);
        List<GiayDTO> giayDTOList = new ArrayList<>();

        for (HoaDonChiTiet hdct : hoaDonChiTiet) {
            GiayDTO giayDTO = new GiayDTO();
            giayDTO.setTen(hdct.getGiayChiTiet().getGiay().getTen());
            giayDTO.setKichCo(hdct.getGiayChiTiet().getKich_co().getTen());
            giayDTO.setSoLuong(hdct.getSo_luong());
            giayDTO.setDonGia(hdct.getDon_gia());
            giayDTO.setAnhDau(hdct.getGiayChiTiet().getGiay().getAnhDau(hdct.getGiayChiTiet().getGiay().getAnhs()));

            giayDTOList.add(giayDTO);
        }

        return giayDTOList;
    }

    @RequestMapping("/admin/ban-hang/cho-giao-don-hang/{maHD}")
    @Transactional
    public String choGiaoDonHang(@PathVariable("maHD") String maHD) {
        HoaDon hoaDon = hoaDonRepo.getHoaDonByMa(maHD);
        if(hoaDon.getHinh_thuc_mua() == 0)
        {
            hoaDon.setTrangthai(3);
            LocalDate currentDate = LocalDate.now();
            hoaDon.setNgay_thanh_toan(currentDate);
            hoaDonRepo.createHoaDon(hoaDon);
        }
        else
        {
            hoaDon.setTrangthai(1);
            LocalDate currentDate = LocalDate.now();
            hoaDon.setNgay_thanh_toan(currentDate);
            hoaDonRepo.createHoaDon(hoaDon);
        }
        return "redirect:/admin/ban-hang";
    }

    @RequestMapping("/admin/ban-hang/van-chuyen-don-hang/{maHD}")
    @Transactional
    public String vanChuyenDonHang(@PathVariable("maHD") String maHD) {
        HoaDon hoaDon = hoaDonRepo.getHoaDonByMa(maHD);
        hoaDon.setTrangthai(2);
        hoaDonRepo.createHoaDon(hoaDon);
        return "redirect:/admin/ban-hang";
    }

    @RequestMapping("/admin/ban-hang/hoan-thanh-don-hang/{maHD}")
    @Transactional
    public String hoanThanhDonHang(@PathVariable("maHD") String maHD) {
        HoaDon hoaDon = hoaDonRepo.getHoaDonByMa(maHD);
        hoaDon.setTrangthai(3);
        hoaDonRepo.createHoaDon(hoaDon);
        return "redirect:/admin/ban-hang";
    }

    @PostMapping("/admin/ban-hang/xac-nhan-don-hang")
    @Transactional
    public String xacNhanNhieuDonHang(@RequestParam(value = "selectedMa1", required = false) List<String> selectedMa1,
                                      @RequestParam(value = "selectedMa2", required = false) List<String> selectedMa2,
                                      @RequestParam(value = "selectedMa3", required = false) List<String> selectedMa3,
                                      @RequestParam(value = "huyxacnhan1", required = false, defaultValue = "xac-nhan") String huyxacnhan1,
                                      @RequestParam(value = "huyxacnhan2", required = false, defaultValue = "xac-nhan") String huyxacnhan2,
                                      @RequestParam(value = "huyxacnhan3", required = false, defaultValue = "xac-nhan") String huyxacnhan3,
                                      @RequestParam(value = "giaoHang", required = false, defaultValue = "huy") String giaohang,
                                      @RequestParam(value = "hoanthanh", required = false, defaultValue = "huy") String hoanthanh,
                                      RedirectAttributes redirectAttributes) {

        if (selectedMa1 != null) {
            if ("huy".equals(huyxacnhan1)) {
                for (String maHD : selectedMa1) {
                    HoaDon hoaDon = hoaDonRepo.getHoaDonByMa(maHD);
                    hoaDonRepo.deleteHD(hoaDon.getId());
                }
            } else if ("xac-nhan".equals(huyxacnhan1)) {
                for (String maHD : selectedMa1) {
                    HoaDon hoaDon = hoaDonRepo.getHoaDonByMa(maHD);
                    LocalDate currentDate = LocalDate.now();
                    hoaDon.setNgay_thanh_toan(currentDate);
                    if (hoaDon.getHinh_thuc_mua() == 0) {
                        hoaDon.setTrangthai(3);
                    } else {
                        hoaDon.setTrangthai(1);
                    }
                    hoaDonRepo.createHoaDon(hoaDon);
                }
            }
        } else if (selectedMa2 != null) {
            if ("giaohang".equals(giaohang)) {
                for (String maHD : selectedMa2) {
                    HoaDon hoaDon = hoaDonRepo.getHoaDonByMa(maHD);
                    hoaDon.setTrangthai(2);
                    hoaDonRepo.createHoaDon(hoaDon);
                }
            } else if ("huy".equals(huyxacnhan2)) {
                for (String maHD : selectedMa2) {
                    HoaDon hoaDon = hoaDonRepo.getHoaDonByMa(maHD);
                    hoaDon.setTrangthai(4);
                    hoaDonRepo.createHoaDon(hoaDon);
                }
            }
        } else if (selectedMa3 != null) {
            if ("hoanthanh".equals(hoanthanh)) {
                for (String maHD : selectedMa3) {
                    HoaDon hoaDon = hoaDonRepo.getHoaDonByMa(maHD);
                    hoaDon.setTrangthai(3);
                    hoaDonRepo.createHoaDon(hoaDon);
                }
            } else if ("huy".equals(huyxacnhan3)) {
                for (String maHD : selectedMa3) {
                    HoaDon hoaDon = hoaDonRepo.getHoaDonByMa(maHD);
                    hoaDon.setTrangthai(4);
                    hoaDonRepo.createHoaDon(hoaDon);
                }
            }
        }

        return "redirect:/admin/ban-hang";
    }

    @RequestMapping(value = "/admin/ban-hang/tim-kiem-hoa-don", method = RequestMethod.GET)
    public String timKiemHoaDon(@RequestParam("timTheo") String timTheo,
                                @RequestParam("keyword") String keyword,
                                @RequestParam("trangThai") Integer trangThai,
                                @RequestParam("page0") Optional<Integer> page0,
                                @RequestParam("page1") Optional<Integer> page1,
                                @RequestParam("page2") Optional<Integer> page2,
                                @RequestParam("page3") Optional<Integer> page3,
                                @RequestParam("page4") Optional<Integer> page4,
                                Model model) {

        PageDTO<HoaDon> hoaDonTrangThai0 = hoaDonRepo.getPageHDByTrangThai1(trangThai, keyword, timTheo, page0.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai1 = hoaDonRepo.getPageHDByTrangThai(1, page1.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai2 = hoaDonRepo.getPageHDByTrangThai(2, page2.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai3 = hoaDonRepo.getPageHDByTrangThai(3, page3.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai4 = hoaDonRepo.getPageHDByTrangThai(4, page4.orElse(0));
        model.addAttribute("PageHoaDonTT0", hoaDonTrangThai0);
        model.addAttribute("PageHoaDonTT1", hoaDonTrangThai1);
        model.addAttribute("PageHoaDonTT2", hoaDonTrangThai2);
        model.addAttribute("PageHoaDonTT3", hoaDonTrangThai3);
        model.addAttribute("PageHoaDonTT4", hoaDonTrangThai4);
        // Đặt các thuộc tính khác cần thiết và trả về view tìm kiếm
        return "banhangtaiquay/ban_hang";
    }

    @RequestMapping(value = "/admin/ban-hang/tim-kiem-hoa-don1", method = RequestMethod.GET)
    public String timKiemHoaDon1(@RequestParam("timTheo") String timTheo,
                                 @RequestParam("keyword") String keyword,
                                 @RequestParam("trangThai") Integer trangThai,
                                 @RequestParam("page0") Optional<Integer> page0,
                                 @RequestParam("page1") Optional<Integer> page1,
                                 @RequestParam("page2") Optional<Integer> page2,
                                 @RequestParam("page3") Optional<Integer> page3,
                                 @RequestParam("page4") Optional<Integer> page4,
                                 Model model) {

        PageDTO<HoaDon> hoaDonTrangThai1 = hoaDonRepo.getPageHDByTrangThai1(trangThai, keyword, timTheo, page1.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai0 = hoaDonRepo.getPageHDByTrangThai(0, page0.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai2 = hoaDonRepo.getPageHDByTrangThai(2, page2.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai3 = hoaDonRepo.getPageHDByTrangThai(3, page3.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai4 = hoaDonRepo.getPageHDByTrangThai(4, page4.orElse(0));
        model.addAttribute("PageHoaDonTT0", hoaDonTrangThai0);
        model.addAttribute("PageHoaDonTT1", hoaDonTrangThai1); // Page hóa đơn chuẩn bị
        model.addAttribute("PageHoaDonTT2", hoaDonTrangThai2);
        model.addAttribute("PageHoaDonTT3", hoaDonTrangThai3);
        model.addAttribute("PageHoaDonTT4", hoaDonTrangThai4);
        // Đặt các thuộc tính khác cần thiết và trả về view tìm kiếm
        return "banhangtaiquay/ban_hang";
    }

    @RequestMapping(value = "/admin/ban-hang/tim-kiem-hoa-don2", method = RequestMethod.GET)
    public String timKiemHoaDon2(@RequestParam("timTheo") String timTheo,
                                 @RequestParam("keyword") String keyword,
                                 @RequestParam("trangThai") Integer trangThai,
                                 @RequestParam("page0") Optional<Integer> page0,
                                 @RequestParam("page1") Optional<Integer> page1,
                                 @RequestParam("page2") Optional<Integer> page2,
                                 @RequestParam("page3") Optional<Integer> page3,
                                 @RequestParam("page4") Optional<Integer> page4,
                                 Model model) {

        PageDTO<HoaDon> hoaDonTrangThai2 = hoaDonRepo.getPageHDByTrangThai1(trangThai, keyword, timTheo, page2.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai0 = hoaDonRepo.getPageHDByTrangThai(0, page0.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai1 = hoaDonRepo.getPageHDByTrangThai(1, page1.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai3 = hoaDonRepo.getPageHDByTrangThai(3, page3.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai4 = hoaDonRepo.getPageHDByTrangThai(4, page4.orElse(0));
        model.addAttribute("PageHoaDonTT0", hoaDonTrangThai0);
        model.addAttribute("PageHoaDonTT1", hoaDonTrangThai1);
        model.addAttribute("PageHoaDonTT2", hoaDonTrangThai2);
        model.addAttribute("PageHoaDonTT3", hoaDonTrangThai3);
        model.addAttribute("PageHoaDonTT4", hoaDonTrangThai4);
        // Đặt các thuộc tính khác cần thiết và trả về view tìm kiếm
        return "banhangtaiquay/ban_hang";
    }

    @RequestMapping(value = "/admin/ban-hang/tim-kiem-hoa-don3", method = RequestMethod.GET)
    public String timKiemHoaDon3(@RequestParam("timTheo") String timTheo,
                                 @RequestParam("keyword") String keyword,
                                 @RequestParam("trangThai") Integer trangThai,
                                 @RequestParam("page0") Optional<Integer> page0,
                                 @RequestParam("page1") Optional<Integer> page1,
                                 @RequestParam("page2") Optional<Integer> page2,
                                 @RequestParam("page3") Optional<Integer> page3,
                                 @RequestParam("page4") Optional<Integer> page4,
                                 Model model) {

        PageDTO<HoaDon> hoaDonTrangThai3 = hoaDonRepo.getPageHDByTrangThai1(trangThai, keyword, timTheo, page3.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai0 = hoaDonRepo.getPageHDByTrangThai(0, page0.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai1 = hoaDonRepo.getPageHDByTrangThai(1, page1.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai2 = hoaDonRepo.getPageHDByTrangThai(2, page2.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai4 = hoaDonRepo.getPageHDByTrangThai(4, page4.orElse(0));
        model.addAttribute("PageHoaDonTT0", hoaDonTrangThai0);
        model.addAttribute("PageHoaDonTT1", hoaDonTrangThai1);
        model.addAttribute("PageHoaDonTT2", hoaDonTrangThai2);
        model.addAttribute("PageHoaDonTT3", hoaDonTrangThai3);
        model.addAttribute("PageHoaDonTT4", hoaDonTrangThai4);
        return "banhangtaiquay/ban_hang";
    }

    @RequestMapping(value = "/admin/ban-hang/tim-kiem-hoa-don4", method = RequestMethod.GET)
    public String timKiemHoaDon4(@RequestParam("timTheo") String timTheo,
                                 @RequestParam("keyword") String keyword,
                                 @RequestParam("trangThai") Integer trangThai,
                                 @RequestParam("page0") Optional<Integer> page0,
                                 @RequestParam("page1") Optional<Integer> page1,
                                 @RequestParam("page2") Optional<Integer> page2,
                                 @RequestParam("page3") Optional<Integer> page3,
                                 @RequestParam("page4") Optional<Integer> page4,
                                 Model model) {

        PageDTO<HoaDon> hoaDonTrangThai4 = hoaDonRepo.getPageHDByTrangThai1(trangThai, keyword, timTheo, page4.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai0 = hoaDonRepo.getPageHDByTrangThai(0, page0.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai1 = hoaDonRepo.getPageHDByTrangThai(1, page1.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai2 = hoaDonRepo.getPageHDByTrangThai(2, page2.orElse(0));
        PageDTO<HoaDon> hoaDonTrangThai3 = hoaDonRepo.getPageHDByTrangThai(3, page3.orElse(0));
        model.addAttribute("PageHoaDonTT0", hoaDonTrangThai0);
        model.addAttribute("PageHoaDonTT1", hoaDonTrangThai1);
        model.addAttribute("PageHoaDonTT2", hoaDonTrangThai2);
        model.addAttribute("PageHoaDonTT3", hoaDonTrangThai3);
        model.addAttribute("PageHoaDonTT4", hoaDonTrangThai4);
        // Đặt các thuộc tính khác cần thiết và trả về view tìm kiếm
        return "banhangtaiquay/ban_hang";
    }

}