/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.SinhVienDAO;
import DTO.SinhVien;
import java.util.ArrayList;

/**
 *
 * @author lythanhphat9523
 */
public class SinhVienBUS {
    SinhVienDAO svDAO = new SinhVienDAO();
    public String delete(SinhVien sv) {
        if(svDAO.hasMSSV(sv.getMaSinhVien())==false) return "Người này không tồn tại";
        if(svDAO.delete(sv))
        return "Xóa thành công";
        else return "Xóa thất bại";
    }
    
    public ArrayList<SinhVien> getlistSinhVien(){
        return svDAO.selectAll();
    }
    
    public ArrayList<SinhVien> getlistSinhVienDK(String a,String b){
        return svDAO.selectByCondition(a,b);
    }
    
    public ArrayList<SinhVien> getlistSinhVienDKDB(SinhVien a){
        return svDAO.selectBySpecialCondition(a);
    }
    
    public String update(SinhVien sv) {
        if(svDAO.hasMSSV(sv.getMaSinhVien())==false) 
            return "Người này không tồn tại";
        if(svDAO.update(sv)) 
        return "Sửa thành công";
        else return "Sửa thất bại";
    }
    
    public String insert(SinhVien sv) {
        if (svDAO.hasMSSV(sv.getMaSinhVien()))
            return "Người này đã tồn tại";
        if (svDAO.insert(sv))
            return "Thêm thành công"; 
        return "Thêm thất bại"; 
    }
    
    
}
