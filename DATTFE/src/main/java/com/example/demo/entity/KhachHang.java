package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "khach_hang")
@Getter
@Setter
public class KhachHang implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "ho_ten")
    @NotBlank(message = "Không được để trống họ tên")
    private String hoten;

    @Column(name = "ngay_sinh")
    private Date ngaysinh;

    @Column(name = "sdt")
    private String sdt;

    @Column(name = "email")
    private String email;

    @Column(name = "mat_khau")
    private String matkhau;

    @Column(name = "trangthai")
    private Integer trangthai;
    @JsonIgnore
    @OneToOne(mappedBy = "khach_hang")
    private GioHang gio_hang;
    @JsonIgnore
    @OneToMany(mappedBy = "khachHang", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<DiaChi> diaChiList;
}
