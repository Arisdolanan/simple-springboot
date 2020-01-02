package com.example.restfullspring.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "gangguan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GangguanModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_gangguan;
    private int id_produk;
    private int user_id;
    private String nama_gangguan;
    private String detail_gangguan;
    private int harga;
    private int is_active;
    private String date_created;
    private String date_updated;

    public int getId_gangguan() {
        return id_gangguan;
    }

    public void setId_gangguan(int id_gangguan) {
        this.id_gangguan = id_gangguan;
    }

    public int getId_produk() {
        return id_produk;
    }

    public void setId_produk(int id_produk) {
        this.id_produk = id_produk;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNama_gangguan() {
        return nama_gangguan;
    }

    public void setNama_gangguan(String nama_gangguan) {
        this.nama_gangguan = nama_gangguan;
    }

    public String getDetail_gangguan() {
        return detail_gangguan;
    }

    public void setDetail_gangguan(String detail_gangguan) {
        this.detail_gangguan = detail_gangguan;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getDate_updated() {
        return date_updated;
    }

    public void setDate_updated(String date_updated) {
        this.date_updated = date_updated;
    }
}
