/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author lythanhphat9523
 */
public class HocPhan {
    private String maHP;
    private String maMH;
    private String tenMH;
    private String tinChi;
    private String HocPhi;
    private String timeBatDau;
    private String timeKetThuc;
    private String tenGiangVien;
    private String tenPhong;
    private String maPhong;
    private String maGiangVien;
    private String tietBatDau;
    private int thu;

    public int getThu() {
        return thu;
    }

    public void setThu(int thu) {
        this.thu = thu;
    }

    
    public String getMaHP() {
        return maHP;
    }

    public void setMaHP(String maHP) {
        this.maHP = maHP;
    }

    public String getTimeBatDau() {
        return timeBatDau;
    }

    public void setTimeBatDau(String timeBatDau) {
        this.timeBatDau = timeBatDau;
    }

    public String getTimeKetThuc() {
        return timeKetThuc;
    }

    public void setTimeKetThuc(String timeKetThuc) {
        this.timeKetThuc = timeKetThuc;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getMaGiangVien() {
        return maGiangVien;
    }

    public void setMaGiangVien(String maGiangVien) {
        this.maGiangVien = maGiangVien;
    }

    public String getTietBatDau() {
        return tietBatDau;
    }

    public void setTietBatDau(String tietBatDau) {
        this.tietBatDau = tietBatDau;
    }

   

    public String getTenGiangVien() {
        return tenGiangVien;
    }

    public void setTenGiangVien(String tenGiangVien) {
        this.tenGiangVien = tenGiangVien;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public HocPhan(String maHP,  String tenMH, String tinChi,String tenGiangVien,String tenPhong,int thu,String tietBatDau, String timeBatDau, String timeKetThuc) {
        this.maHP = maHP;
        this.tenMH = tenMH;
        this.tinChi = tinChi;
        this.timeBatDau = timeBatDau;
        this.timeKetThuc = timeKetThuc;
        this.tenGiangVien = tenGiangVien;
        this.tenPhong = tenPhong;
        this.tietBatDau = tietBatDau;
        this.thu=thu;
    }

    public HocPhan(String maHP, String maMH, String maPhong, String maGiangVien,int thu, String tietBatDau,String timeBatDau, String timeKetThuc) {
        this.maHP = maHP;
        this.maMH = maMH;
        this.timeBatDau = timeBatDau;
        this.timeKetThuc = timeKetThuc;
        this.maPhong = maPhong;
        this.maGiangVien = maGiangVien;
        this.tietBatDau = tietBatDau;
        this.thu=thu;
    }

    
    
    
    
    public HocPhan() {
    }

    

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public String getTenMH() {
        return tenMH;
    }

    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }

    public String getTinChi() {
        return tinChi;
    }

    public void setTinChi(String tinChi) {
        this.tinChi = tinChi;
    }

    public String getHocPhi() {
        return HocPhi;
    }

    public void setHocPhi(String HocPhi) {
        this.HocPhi = HocPhi;
    }
    
    
}
