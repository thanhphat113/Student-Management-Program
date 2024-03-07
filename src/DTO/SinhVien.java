
package DTO;

import DAO.SinhVienDAO;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.TableModel;


public class SinhVien {
    private String maSinhVien;
    private String tenSinhVien;
    private String namSinh;
    private String gioiTinh;
    private String danToc;
    private String CCCD;
    private String soDienThoai;
    private String maKhoa;
    private String maLop;
    private String matKhau;
    private String tenCoVan;
    private String tenKhoa;
    private String tenLop;

    public SinhVien(String maSinhVien, String tenSinhVien, String namSinh, String gioiTinh, String danToc, String CCCD, String soDienThoai, String tenCoVan, String tenKhoa, String tenLop) {
        this.maSinhVien = maSinhVien;
        this.tenSinhVien = tenSinhVien;
        this.namSinh = namSinh;
        this.gioiTinh = gioiTinh;
        this.danToc = danToc;
        this.CCCD = CCCD;
        this.soDienThoai = soDienThoai;
        this.tenCoVan = tenCoVan;
        this.tenKhoa = tenKhoa;
        this.tenLop = tenLop;
    }
    
    public String getTenCoVan() {
        return tenCoVan;
    }

    public void setTenCoVan(String tenCoVan) {
        this.tenCoVan = tenCoVan;
    }

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }
    
    public SinhVien(){
        super();
    }


    
    

    public SinhVien(String maSinhVien, String tenSinhVien, String namSinh, String gioiTinh, String danToc, String CCCD, String soDienThoai, String maKhoa, String maLop) {
        this.maSinhVien = maSinhVien;
        this.tenSinhVien = tenSinhVien;
        this.namSinh = namSinh;
        this.gioiTinh = gioiTinh;
        this.danToc = danToc;
        this.CCCD = CCCD;
        this.soDienThoai = soDienThoai;
        this.maKhoa = maKhoa;
        this.maLop = maLop;
    }
    
    

    public String getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public String getTenSinhVien() {
        return tenSinhVien;
    }

    public void setTenSinhVien(String tenSinhVien) {
        this.tenSinhVien = tenSinhVien;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDanToc() {
        return danToc;
    }

    public void setDanToc(String danToc) {
        this.danToc = danToc;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
    
}
