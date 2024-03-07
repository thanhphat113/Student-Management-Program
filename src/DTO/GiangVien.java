/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author lythanhphat9523
 */
public class GiangVien {
    private String maGiangVien;
    private String tenGiangVien;
    private String trinhDo;
    private String sdt;
    private String namSinh;
    private String email;
    private String maKhoa;
    private String gioiTinh;
    private String matKhau;

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    
    
    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public GiangVien(String maGiangVien, String tenGiangVien, String gioiTinh,String namSinh, String trinhDo,  String sdt, String email, String maKhoa) {
        this.maGiangVien = maGiangVien;
        this.tenGiangVien = tenGiangVien;
        this.trinhDo = trinhDo;
        this.sdt = sdt;
        this.namSinh = namSinh;
        this.email = email;
        this.maKhoa = maKhoa;
        this.gioiTinh = gioiTinh;
    }
    
    

    

    
    
    public GiangVien() {
    }

    public String getMaGiangVien() {
        return maGiangVien;
    }

    public void setMaGiangVien(String maGiangVien) {
        this.maGiangVien = maGiangVien;
    }

    public String getTenGiangVien() {
        return tenGiangVien;
    }

    public void setTenGiangVien(String tenGiangVien) {
        this.tenGiangVien = tenGiangVien;
    }

    public String getTrinhDo() {
        return trinhDo;
    }

    public void setTrinhDo(String trinhDo) {
        this.trinhDo = trinhDo;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }
    
    
}
