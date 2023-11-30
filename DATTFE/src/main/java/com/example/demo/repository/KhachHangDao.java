package com.example.demo.repository;

import com.example.demo.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface KhachHangDao extends JpaRepository<KhachHang, UUID> {
    @Query("select p from KhachHang p where p.email=?1")
    KhachHang getKhByEmail(String email);

    @Query("select kh from KhachHang kh where kh.id = ?1")
    KhachHang GetBykhachhang(UUID id);


    @Query("select kh from KhachHang kh where kh.ma = ?1")
    KhachHang GetKhachhangByma(String ma);

    @Query(value = "SELECT email\n" +
            "FROM khach_hang",nativeQuery = true)
    List<String> findEmailByMa();


    //lay ra so cua makh co so lon nhat
    @Query(value = "SELECT TOP 1 SUBSTRING(ma, 3, LEN(ma) - 2) " +
            "FROM khach_hang " +
            "WHERE ma LIKE 'KH%' ORDER BY CAST(SUBSTRING(ma, 3, LEN(ma) - 2) AS INT) DESC",nativeQuery = true)
    Integer getMaMax();

    @Query("SELECT MAX(CAST(SUBSTRING(kh.ma, 3, LENGTH(kh.ma) - 2) AS int)) FROM  KhachHang kh")
    Integer findMaxMaHoaDonNumber();

    default String generateNextMaKhachHang() {
        Integer maxMaNumber = findMaxMaHoaDonNumber();
        int nextNumber;

        if (maxMaNumber != null) {
            nextNumber = maxMaNumber + 1;
        } else {
            nextNumber = 1;
        }

        return "KH" + nextNumber;
    }
}
