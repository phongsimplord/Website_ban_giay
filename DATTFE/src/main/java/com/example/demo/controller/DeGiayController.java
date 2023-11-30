package com.example.demo.controller;

import com.example.demo.entity.DeGiay;
import com.example.demo.entity.PageDTO;
import com.example.demo.repository.DeGiayRepo;
import com.example.demo.repository.DeGiayRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class DeGiayController {
    @Autowired
    DeGiayRepo deGiayRepo;
    @RequestMapping("/admin/degiay")
    public String DeGiay(@ModelAttribute("degiay") DeGiay DeGiay, @RequestParam("page") Optional<Integer> page,Model model) {
       PageDTO<DeGiay> page1 = deGiayRepo.getPageDeGiay(page.orElse(0));
       model.addAttribute("i",0);
       model.addAttribute("listPDeGiay",page1);
        return "product/de_giay";
    }

    @PostMapping("/admin/degiay/create")
    public String createDeGiay(@ModelAttribute("degiay") DeGiay DeGiay) {
        deGiayRepo.createDeGiay(DeGiay);
        return "redirect:/admin/degiay";
    }
    @PostMapping("/admin/degiay/update/{x}")
    public String updateDeGiay(@PathVariable("x") String ma,@ModelAttribute("degiay") DeGiay DeGiay) {
        deGiayRepo.updateDeGiay(ma,DeGiay);
        return "redirect:/admin/degiay";
    }
    @RequestMapping ("/admin/degiay/delete/{x}")
    public String deleteDeGiay(@PathVariable("x") String ma) {
        deGiayRepo.delete(ma);
        return "redirect:/admin/degiay";
    }

    @RequestMapping("/admin/degiay/detail/{ma}")
    public String createDeGiay(@PathVariable("ma") String ma,  @RequestParam("page") Optional<Integer> page,Model model) {
        PageDTO<DeGiay> page1 = deGiayRepo.getPageDeGiay(page.orElse(0));
        model.addAttribute("listPDeGiay",page1);
        model.addAttribute("degiay", deGiayRepo.getDeGiayByMa(ma));
        return "product/de_giay";
    }

    @ModelAttribute("listDeGiay")
    public List<DeGiay> getListDeGiay() {
        return deGiayRepo.getListDeGiay();
    }
}
