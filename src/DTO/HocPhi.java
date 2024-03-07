package DTO;

public class HocPhi {
    private String maHP;
    private String maSV;
    private String hocKy;
    private String namHoc;
    private String tinhTrang;
    private String tongTien;

    public HocPhi() {
    }

    public HocPhi(String maHP, String maSV, String hocKy, String namHoc, String tinhTrang, String tongTien) {
        this.maHP = maHP;
        this.maSV = maSV;
        this.hocKy = hocKy;
        this.namHoc = namHoc;
        this.tinhTrang = tinhTrang;
        this.tongTien = tongTien;
    }
    
    public String getMaHP() {
        return maHP;
    }

    public void setMaHP(String maHP) {
        this.maHP = maHP;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getHocKy() {
        return hocKy;
    }

    public void setHocKy(String hocKy) {
        this.hocKy = hocKy;
    }

    public String getNamHoc() {
        return namHoc;
    }

    public void setNamHoc(String namHoc) {
        this.namHoc = namHoc;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }
    
    
}
