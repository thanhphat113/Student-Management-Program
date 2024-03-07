
package DTO;

public class PhongHoc {
    private String maPhong;
    private String tenPhong; 
    private String chucNang; 
    private String maKhoaQuanLy; 

    public PhongHoc() {
    }

    public PhongHoc(String maPhong, String tenPhong, String chucNang, String maKhoaQuanLy) {
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
        this.chucNang = chucNang;
        this.maKhoaQuanLy = maKhoaQuanLy;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public String getChucNang() {
        return chucNang;
    }

    public void setChucNang(String chucNang) {
        this.chucNang = chucNang;
    }

    public String getMaKhoaQuanLy() {
        return maKhoaQuanLy;
    }

    public void setMaKhoaQuanLy(String maKhoaQuanLy) {
        this.maKhoaQuanLy = maKhoaQuanLy;
    }
    
    
}
