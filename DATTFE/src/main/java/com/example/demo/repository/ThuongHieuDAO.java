package com.example.demo.repository;

import com.example.demo.entity.Anh;
import com.example.demo.entity.ThuongHieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ThuongHieuDAO extends JpaRepository<ThuongHieu, UUID> {
}
