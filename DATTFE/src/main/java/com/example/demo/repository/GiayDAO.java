package com.example.demo.repository;

import com.example.demo.entity.Giay;
import com.example.demo.entity.GiayChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
@Repository
public interface GiayDAO extends JpaRepository<Giay, UUID> {
    @Query("select p from Giay p where p.ma = ?1")
    Giay getGiayByMa(String ma);

    @Query("select p from Giay p where p.ten like ?1 and (?2 is null or p.giaban>?2)")
    List<Giay> getSearch(String tensp, BigDecimal giabn);

    @Query("select p from Giay p where p.ten like ?1 and (?2 is null or p.giaban > ?2) and (?3 is null or p.giaban < ?3) " +
            "and p.thuong_hieu.ten like ?4 and p.chat_lieu.ten like ?5 and p.xuat_xu.ten like ?6 " +
            "and p.mau_sac.ten like ?7 and p.gioi_tinh.ten like ?8 and p.kieu_dang.ten like ?9 and p.de_giay.ten like ?10 ")
    Page<Giay> getSearchsanphamByTT(String tensp, BigDecimal giabnmin, BigDecimal giabnmax, String thuong_hieu,
                                           String chat_lieu, String xuat_xu, String mau_sac,
                                           String gioi_tinh, String kieu_dang, String de_giay, Pageable pageable);

}
