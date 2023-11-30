package com.example.demo.controller;

import com.example.demo.entity.ChatLieu;
import com.example.demo.entity.PageDTO;
import com.example.demo.repository.ChatLieuRepo;
import com.example.demo.repository.ChatLieuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ChatLieuController {
    @Autowired
    ChatLieuRepo chatLieuRepo;
    @RequestMapping("/admin/chatlieu")
    public String ChatLieu(@ModelAttribute("chatlieu") ChatLieu ChatLieu, @RequestParam("page") Optional<Integer> page,Model model) {
       PageDTO<ChatLieu> page1 = chatLieuRepo.getPageChatLieu(page.orElse(0));
       model.addAttribute("i",0);
       model.addAttribute("listPChatLieu",page1);
        return "product/chat_lieu";
    }

    @PostMapping("/admin/chatlieu/create")
    public String createChatLieu(@ModelAttribute("chatlieu") ChatLieu ChatLieu) {
        chatLieuRepo.createChatLieu(ChatLieu);
        return "redirect:/admin/chatlieu";
    }
    @PostMapping("/admin/chatlieu/update/{x}")
    public String updateChatLieu(@PathVariable("x") String ma,@ModelAttribute("chatlieu") ChatLieu ChatLieu) {
        chatLieuRepo.updateChatLieu(ma,ChatLieu);
        return "redirect:/admin/chatlieu";
    }
    @RequestMapping ("/admin/chatlieu/delete/{x}")
    public String deleteChatLieu(@PathVariable("x") String ma) {
        chatLieuRepo.delete(ma);
        return "redirect:/admin/chatlieu";
    }

    @RequestMapping("/admin/chatlieu/detail/{ma}")
    public String createChatLieu(@PathVariable("ma") String ma,  @RequestParam("page") Optional<Integer> page,Model model) {
        PageDTO<ChatLieu> page1 = chatLieuRepo.getPageChatLieu(page.orElse(0));
        model.addAttribute("listPChatLieu",page1);
        model.addAttribute("chatlieu", chatLieuRepo.getChatLieuByMa(ma));
        return "product/chat_lieu";
    }

 }
