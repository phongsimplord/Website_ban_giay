package com.example.demo.controller;

import com.example.demo.entity.Anh;
import com.example.demo.entity.Giay;
import com.example.demo.entity.ThuongHieu;
import com.example.demo.entity.PageDTO;
import com.example.demo.repository.ThuongHieuDAO;
import com.example.demo.repository.ThuongHieuRepo;
import com.example.demo.repository.ThuongHieuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class ThuongHieuController {
    @Autowired
    ThuongHieuRepo thuongHieuRepo;
    @Autowired
    ThuongHieuDAO thuongHieuDAO;
    @RequestMapping("/admin/thuonghieu")
    public String ThuongHieu(@ModelAttribute("thuonghieu") ThuongHieu ThuongHieu, @RequestParam("page") Optional<Integer> page,Model model) {
       PageDTO<ThuongHieu> page1 = thuongHieuRepo.getPageThuongHieu(page.orElse(0));
       model.addAttribute("i",0);
       model.addAttribute("listPThuongHieu",page1);
        return "product/thuong_hieu";
    }

    @PostMapping("/admin/thuonghieu/create")
    public String createThuongHieu(@ModelAttribute("thuonghieu") ThuongHieu ThuongHieu) {
        thuongHieuRepo.createThuongHieu(ThuongHieu);
        return "redirect:/admin/thuonghieu";
    }
    @PostMapping("/admin/thuonghieu/update/{x}")
    public String updateThuongHieu(@PathVariable("x") String ma,@ModelAttribute("thuonghieu") ThuongHieu ThuongHieu) {
        thuongHieuRepo.updateThuongHieu(ma,ThuongHieu);
        return "redirect:/admin/thuonghieu";
    }
    @RequestMapping ("/admin/thuonghieu/delete/{x}")
    public String deleteThuongHieu(@PathVariable("x") String ma) {
        thuongHieuRepo.delete(ma);
        return "redirect:/admin/thuonghieu";
    }

    @RequestMapping("/admin/thuonghieu/detail/{ma}")
    public String createThuongHieu(@PathVariable("ma") String ma,  @RequestParam("page") Optional<Integer> page,Model model) {
        PageDTO<ThuongHieu> page1 = thuongHieuRepo.getPageThuongHieu(page.orElse(0));
        model.addAttribute("listPThuongHieu",page1);
        model.addAttribute("thuonghieu", thuongHieuRepo.getThuongHieuByMa(ma));
        return "product/thuong_hieu";
    }

    @ModelAttribute("listThuongHieu")
    public List<ThuongHieu> getListThuongHieu() {
        return thuongHieuRepo.getListThuongHieu();
    }
    @PostMapping("/admin/thuonghieu/createanh")
    public String createanhth(Model model, HttpServletRequest request, @RequestParam("idth") UUID idth, @RequestPart("ten_url1") MultipartFile file) throws IOException, ServletException {
        Path path = Paths.get("src/main/webapp/images/");
        try {
            InputStream inputStream = file.getInputStream();
            Files.copy(inputStream,path.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            e.printStackTrace();
        }
       ThuongHieu thuongHieu = thuongHieuDAO.findById(idth).get();
        thuongHieu.setTen_url(file.getOriginalFilename());
        return "product/thuong_hieu";
    }
}
