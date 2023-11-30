package com.example.demo.controller;

import com.example.demo.entity.MauSac;
import com.example.demo.entity.PageDTO;
import com.example.demo.repository.MauSacRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class MauSacController {
    @Autowired
    MauSacRepo mauSacRepo;
    @RequestMapping("/admin/mausac")
    public String MauSac(@ModelAttribute("mausac") MauSac MauSac, @RequestParam("page") Optional<Integer> page,Model model) {
       PageDTO<MauSac> page1 = mauSacRepo.getPageMauSac(page.orElse(0));
       model.addAttribute("i",0);
       model.addAttribute("listPMauSac",page1);
        return "product/mau_sac";
    }

    @PostMapping("/admin/mausac/create")
    public String createMauSac(@ModelAttribute("mausac") MauSac MauSac) {
        mauSacRepo.createMauSac(MauSac);
        return "redirect:/admin/mausac";
    }
    @PostMapping("/admin/mausac/update/{x}")
    public String updateMauSac(@PathVariable("x") String ma,@ModelAttribute("mausac") MauSac MauSac) {
        mauSacRepo.updateMauSac(ma,MauSac);
        return "redirect:/admin/mausac";
    }
    @RequestMapping ("/admin/mausac/delete/{x}")
    public String deleteMauSac(@PathVariable("x") String ma) {
        mauSacRepo.delete(ma);
        return "redirect:/admin/mausac";
    }

    @RequestMapping("/admin/mausac/detail/{ma}")
    public String createMauSac(@PathVariable("ma") String ma,  @RequestParam("page") Optional<Integer> page,Model model) {
        PageDTO<MauSac> page1 = mauSacRepo.getPageMauSac(page.orElse(0));
        model.addAttribute("listPMauSac",page1);
        model.addAttribute("mausac", mauSacRepo.getMauSacByMa(ma));
        return "product/mau_sac";
    }

    @ModelAttribute("listMauSac")
    public List<MauSac> getListMauSac() {
        return mauSacRepo.getListMauSac();
    }
}
