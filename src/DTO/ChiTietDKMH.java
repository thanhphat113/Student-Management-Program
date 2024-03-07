package DTO;

/**
 *
 * @author lythanhphat9523
 */
public class ChiTietDKMH {
    private String maLopHocPhan;
    private String maSinhVien;
    private int HocPhi;
    private String tenGiangVien;
    private String tenPhong;
    private String tietBD;
    private String timeBD;
    private String timeKT;
    private String tenMH;
    private String tinChi;
    private int thu;

    public int getThu() {
        return thu;
    }

    public void setThu(int thu) {
        this.thu = thu;
    }
            

    public ChiTietDKMH(String maLopHocPhan,String tenMH,  String tinChi, String tenGiangVien, String tenPhong,int thu, String tietBD, String timeBD, String timeKT,int hocPhi) {
        this.maLopHocPhan = maLopHocPhan;
        this.tenGiangVien = tenGiangVien;
        this.tenPhong = tenPhong;
        this.tietBD = tietBD;
        this.thu=thu;
        this.timeBD = timeBD;
        this.timeKT = timeKT;
        this.tenMH = tenMH;
        this.tinChi = tinChi;
        this.HocPhi=hocPhi;
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

    public String getTietBD() {
        return tietBD;
    }

    public void setTietBD(String tietBD) {
        this.tietBD = tietBD;
    }

    public String getTimeBD() {
        return timeBD;
    }

    public void setTimeBD(String timeBD) {
        this.timeBD = timeBD;
    }

    public String getTimeKT() {
        return timeKT;
    }

    public void setTimeKT(String timeKT) {
        this.timeKT = timeKT;
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
    
    

    public ChiTietDKMH() {
    }

    public ChiTietDKMH(String maLopHocPhan, String maSinhVien) {
        this.maLopHocPhan = maLopHocPhan;
        this.maSinhVien = maSinhVien;
    }

    public String getMaLopHocPhan() {
        return maLopHocPhan;
    }

    public void setMaLopHocPhan(String maLopHocPhan) {
        this.maLopHocPhan = maLopHocPhan;
    }

    public String getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public int getHocPhi() {
        return HocPhi;
    }

    public void setMaHocPhi(int maHocPhi) {
        this.HocPhi = maHocPhi;
    }
}

    
    