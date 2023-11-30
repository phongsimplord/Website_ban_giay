package com.example.demo.repository;

import com.example.demo.entity.Giay;
import com.example.demo.entity.KhachHang;
import com.example.demo.entity.San_Pham_Yeu_Thich;
import com.example.demo.entity.San_Pham_Yeu_Thich_Chi_Tiet11;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface sanphamyeuthichchitietdao extends JpaRepository<San_Pham_Yeu_Thich_Chi_Tiet11, UUID> {

    @Query("select spyt from San_Pham_Yeu_Thich_Chi_Tiet11 spyt where spyt.sanPhamYeuThich.khachHang.ma = ?1")
    List<San_Pham_Yeu_Thich_Chi_Tiet11> getSan_Pham_Yeu_ThichByMa(String ma);

    @Query("SELECT COUNT(ss) FROM San_Pham_Yeu_Thich_Chi_Tiet11 ss WHERE ss.giay.ma = :giayMa")
    Integer countYeuThichByGiayId(String giayMa);

    @Query("SELECT s FROM San_Pham_Yeu_Thich_Chi_Tiet11 s WHERE s.sanPhamYeuThich.khachHang = :khachHang AND s.giay = :giay")
    San_Pham_Yeu_Thich_Chi_Tiet11 findByKhachHangAndGiay(@Param("khachHang") KhachHang khachHang, @Param("giay") Giay giay);

    @Query("select spytct from San_Pham_Yeu_Thich_Chi_Tiet11 spytct where spytct.giay.ma = :giayMa")
    San_Pham_Yeu_Thich_Chi_Tiet11 getSan_Pham_Yeu_Thich_Chi_Tiet11Byma(String giayMa);
}
