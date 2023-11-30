package com.example.demo.repository;

import com.example.demo.entity.Giay;
import com.example.demo.entity.KhachHang;
import com.example.demo.entity.San_Pham_Yeu_Thich;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SanPhamYeuThichDAo extends JpaRepository<San_Pham_Yeu_Thich, UUID> {

    San_Pham_Yeu_Thich findByKhachHang(KhachHang khachHang);


}

