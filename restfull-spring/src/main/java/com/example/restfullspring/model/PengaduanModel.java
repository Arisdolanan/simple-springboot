package com.example.restfullspring.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pengaduan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PengaduanModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int Penyedia;
    private int Layanan;
    private int gangguan;
    private int user_id;
    private String notiket;
    private String keluhan;
    private String gambar;
    private String nohp;
    private float lat;
    private float lng;
    private String alamat;
    private int is_active;
    private String date_created;
    private String date_updated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPenyedia() {
        return Penyedia;
    }

    public void setPenyedia(int penyedia) {
        Penyedia = penyedia;
    }

    public int getLayanan() {
        return Layanan;
    }

    public void setLayanan(int layanan) {
        Layanan = layanan;
    }

    public int getGangguan() {
        return gangguan;
    }

    public void setGangguan(int gangguan) {
        this.gangguan = gangguan;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNotiket() {
        return notiket;
    }

    public void setNotiket(String notiket) {
        this.notiket = notiket;
    }

    public String getKeluhan() {
        return keluhan;
    }

    public void setKeluhan(String keluhan) {
        this.keluhan = keluhan;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
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
