package com.example.demo.controller;

import com.example.demo.entity.DanhGia;
import com.example.demo.repository.DanhGiaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/danh-gia")
public class DanhGiaController {

    @Autowired
    private DanhGiaDAO danhGiaDAO;

    Integer numberCurrent=0;

    @GetMapping("")
    public String danhGia(Model model, @RequestParam(defaultValue = "0") String number){
        Pageable pageable= PageRequest.of(Integer.valueOf(number),10);
        numberCurrent=Integer.valueOf(number);
        model.addAttribute("page",danhGiaDAO.findDanhGiasByTrangThai0(pageable));
        return "danh_gia/danh_gia";
    }


    @GetMapping("/duyet/{id}")
    public String duyet(@PathVariable String id){
        danhGiaDAO.duyetOne(UUID.fromString(id));
        return "redirect:/admin/danh-gia?number="+numberCurrent;
    }

    @GetMapping("/duyet-tat-ca")
    public String duyetAll(){
        danhGiaDAO.duyetAll();
        return "redirect:/admin/danh-gia";
    }

    @ResponseBody
    @PostMapping("/duyet-nhieu")
    public ResponseEntity<?> duyetNhieu(@RequestBody List<String> list){
        list.stream().forEach(item -> danhGiaDAO.duyetOne(UUID.fromString(item)));
        return ResponseEntity.ok(numberCurrent);
    }

    @ResponseBody
    @PostMapping("/xoa-nhieu")
    public ResponseEntity<?> xoaNhieu(@RequestBody List<String> list){
        list.stream().forEach(item -> danhGiaDAO.deleteById(UUID.fromString(item)));
        return ResponseEntity.ok(numberCurrent);
    }

}
