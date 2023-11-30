package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "san_pham_yeu_thich_chi_tiet")
public class San_Pham_Yeu_Thich_Chi_Tiet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "id_san_pham_yeu_thich")
    private San_Pham_Yeu_Thich sanPhamYeuThich;
    @ManyToOne
    @JoinColumn(name = "id_giay")
    private Giay giay;
    private LocalDate ngay_tao;
    private Integer trangthai;
}
