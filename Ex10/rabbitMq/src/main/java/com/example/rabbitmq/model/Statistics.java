package com.example.rabbitmq.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "statistics")
public class Statistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ma_kho")
    private String maKho;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaKho() {
        return maKho;
    }

    public void setMaKho(String maKho) {
        this.maKho = maKho;
    }

    public int getSoLuongSP() {
        return soLuongSP;
    }

    public void setSoLuongSP(int soLuongSP) {
        this.soLuongSP = soLuongSP;
    }

    public LocalDate getNgayThongKe() {
        return ngayThongKe;
    }

    public void setNgayThongKe(LocalDate ngayThongKe) {
        this.ngayThongKe = ngayThongKe;
    }

    @Column(name = "so_luong_sp")
    private int soLuongSP;
    @Column(name = "ngay_thong_ke")
    private LocalDate ngayThongKe;

    @Override
    public String toString() {
        return "Statistics{" +
                "id=" + id +
                ", maKho='" + maKho + '\'' +
                ", soLuongSP=" + soLuongSP +
                ", ngayThongKe=" + ngayThongKe +
                '}';
    }
// Constructors, getters và setters
}

