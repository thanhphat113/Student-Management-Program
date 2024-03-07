package DTO;

public class DanhGiaKQ {
    private String maMH;
    private String maSV;
    private String giuaKy;
    private String cuoiKy;
    private String diemTrungBinh;
    private String diemTrungBinhHe4;

    public DanhGiaKQ() {
    }

    public DanhGiaKQ(String maMH, String maSV, String giuaKy, String cuoiKy, String diemTrungBinh, String diemTrungBinhHe4) {
        this.maMH = maMH;
        this.maSV = maSV;
        this.giuaKy = giuaKy;
        this.cuoiKy = cuoiKy;
        this.diemTrungBinh = diemTrungBinh;
        this.diemTrungBinhHe4 = diemTrungBinhHe4;
    }
    
    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getGiuaKy() {
        return giuaKy;
    }

    public void setGiuaKy(String giuaKy) {
        this.giuaKy = giuaKy;
    }

    public String getCuoiKy() {
        return cuoiKy;
    }

    public void setCuoiKy(String cuoiKy) {
        this.cuoiKy = cuoiKy;
    }

    public String getDiemTrungBinh() {
        return diemTrungBinh;
    }

    public void setDiemTrungBinh(String diemTrungBinh) {
        this.diemTrungBinh = diemTrungBinh;
    }

    public String getDiemTrungBinhHe4() {
        return diemTrungBinhHe4;
    }

    public void setDiemTrungBinhHe4(String diemTrungBinhHe4) {
        this.diemTrungBinhHe4 = diemTrungBinhHe4;
    }
    
    
}
