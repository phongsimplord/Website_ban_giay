package com.example.demo.controller;

import com.example.demo.entity.KieuDang;
import com.example.demo.entity.PageDTO;
import com.example.demo.repository.KieuDangRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class KieuDangController {
    @Autowired
    KieuDangRepo KieuDangRepo;
    @RequestMapping("/admin/kieudang")
    public String KieuDang(@ModelAttribute("kieudang") KieuDang KieuDang, @RequestParam("page") Optional<Integer> page,Model model) {
       PageDTO<KieuDang> page1 = KieuDangRepo.getPageKieuDang(page.orElse(0));
       model.addAttribute("i",0);
       model.addAttribute("listPKieuDang",page1);
        return "product/kieu_dang";
    }

    @PostMapping("/admin/kieudang/create")
    public String createKieuDang(@ModelAttribute("kieudang") KieuDang KieuDang) {
        KieuDangRepo.createKieuDang(KieuDang);
        return "redirect:/admin/kieudang";
    }
    @PostMapping("/admin/kieudang/update/{x}")
    public String updateKieuDang(@PathVariable("x") String ma,@ModelAttribute("kieudang") KieuDang KieuDang) {
        KieuDangRepo.updateKieuDang(ma,KieuDang);
        return "redirect:/admin/kieudang";
    }
    @RequestMapping ("/admin/kieudang/delete/{x}")
    public String deleteKieuDang(@PathVariable("x") String ma) {
        KieuDangRepo.delete(ma);
        return "redirect:/admin/kieudang";
    }

    @RequestMapping("/admin/kieudang/detail/{ma}")
    public String createKieuDang(@PathVariable("ma") String ma,  @RequestParam("page") Optional<Integer> page,Model model) {
        PageDTO<KieuDang> page1 = KieuDangRepo.getPageKieuDang(page.orElse(0));
        model.addAttribute("listPKieuDang",page1);
        model.addAttribute("kieudang", KieuDangRepo.getKieuDangByMa(ma));
        return "product/kieu_dang";
    }

 }
