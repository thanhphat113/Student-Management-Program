package DTO;

public class ChiTietGiangDay {
    private String maHP;
    private String maGV;
    private String tenHP;
    private String soTinChi;

    public ChiTietGiangDay(String maHP, String tenHP, String soTinChi) {
        this.maHP = maHP;
        this.tenHP = tenHP;
        this.soTinChi = soTinChi;
    }
    
    

    public String getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(String soTinChi) {
        this.soTinChi = soTinChi;
    }
    
    
    
    public ChiTietGiangDay() {
    }

    public ChiTietGiangDay(String maHP, String maGV) {
        this.maHP = maHP;
        this.maGV = maGV;
    }


    public String getTenHP() {
        return tenHP;
    }

    public void setTenHP(String tenHP) {
        this.tenHP = tenHP;
    }
    

    

    public String getMaHP() {
        return maHP;
    }

    public void setMaHP(String maHP) {
        this.maHP = maHP;
    }

    public String getMaGV() {
        return maGV;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }
    
}
