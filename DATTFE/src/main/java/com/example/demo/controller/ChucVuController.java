package com.example.demo.controller;

import com.example.demo.entity.ChucVu;
import com.example.demo.entity.PageDTO;
import com.example.demo.repository.ChucVuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RequestMapping("/admin/chuc-vu")
@Controller
public class ChucVuController {

    @Autowired
    private ChucVuRepository chucVuRepository;

    private PageDTO<ChucVu> page;

    @GetMapping()
    public String page(@ModelAttribute ChucVu chucVu,@RequestParam(defaultValue = "0") String number,Model model) {
        page=chucVuRepository.getPage(number);
        model.addAttribute("page",page);
        return "nhan_vien/chuc_vu/chuc_vu";
    }

    @GetMapping("/detail/{ma}")
    public String detail(@PathVariable("ma") String ma, Model model,@ModelAttribute ChucVu chucVu) {
        model.addAttribute("page",page);
        model.addAttribute("chucVu",chucVuRepository.getByMa(ma));
        return "nhan_vien/chuc_vu/chuc_vu_view_update";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("chucVu") @Valid ChucVu chucVu, BindingResult result,Model model) {
        if(result.hasErrors()){
            model.addAttribute("page",page);
            return "nhan_vien/chuc_vu/chuc_vu";
        }
        chucVuRepository.create(chucVu);
        return "redirect:/admin/chuc-vu";
    }

    @GetMapping("/delete/{ma}")
    public String delete(@PathVariable String ma) {
        chucVuRepository.delete(ma);
        return "redirect:/admin/chuc-vu";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute ChucVu chucVu) {
        chucVuRepository.update(chucVu);
        return "redirect:/admin/chuc-vu";
    }

    @ModelAttribute("listChucVu")
    public List<ChucVu> getListChucVu() {
        return chucVuRepository.getList();
    }

}
