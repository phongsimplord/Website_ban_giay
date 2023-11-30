package com.example.demo.repository;

import com.example.demo.entity.GiamGiaHoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface GGHDDAO extends JpaRepository<GiamGiaHoaDon, UUID> {
}
