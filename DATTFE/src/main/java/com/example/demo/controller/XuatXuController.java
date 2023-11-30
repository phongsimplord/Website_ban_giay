package com.example.demo.controller;

import com.example.demo.entity.XuatXu;
import com.example.demo.entity.PageDTO;
import com.example.demo.repository.XuatXuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class XuatXuController {
    @Autowired
    XuatXuRepo XuatXuRepo;
    @RequestMapping("/admin/xuatxu")
    public String XuatXu(@ModelAttribute("xuatxu") XuatXu XuatXu, @RequestParam("page") Optional<Integer> page,Model model) {
       PageDTO<XuatXu> page1 = XuatXuRepo.getPageXuatXu(page.orElse(0));
       model.addAttribute("i",0);
       model.addAttribute("listPXuatXu",page1);
        return "product/xuat_xu";
    }

    @PostMapping("/admin/xuatxu/create")
    public String createXuatXu(@ModelAttribute("xuatxu") XuatXu XuatXu) {
        XuatXuRepo.createXuatXu(XuatXu);
        return "redirect:/admin/xuatxu";
    }
    @PostMapping("/admin/XuatXu/update/{x}")
    public String updateXuatXu(@PathVariable("x") String ma,@ModelAttribute("xuatxu") XuatXu XuatXu) {
        XuatXuRepo.updateXuatXu(ma,XuatXu);
        return "redirect:/admin/xuatxu";
    }
    @RequestMapping ("/admin/xuatxu/delete/{x}")
    public String deleteXuatXu(@PathVariable("x") String ma) {
        XuatXuRepo.delete(ma);
        return "redirect:/admin/xuatxu";
    }

    @RequestMapping("/admin/xuatxu/detail/{ma}")
    public String createXuatXu(@PathVariable("ma") String ma,  @RequestParam("page") Optional<Integer> page,Model model) {
        PageDTO<XuatXu> page1 = XuatXuRepo.getPageXuatXu(page.orElse(0));
        model.addAttribute("listPXuatXu",page1);
        model.addAttribute("xuatxu", XuatXuRepo.getXuatXuByMa(ma));
        return "product/xuat_xu";
    }

 }
