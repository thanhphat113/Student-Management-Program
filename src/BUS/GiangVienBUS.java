/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.GiangVienDAO;
import DTO.GiangVien;

import java.util.ArrayList;

/**
 *
 * @author lythanhphat9523
 */
public class GiangVienBUS {
    GiangVienDAO gvDAO = new GiangVienDAO();
    public String delete(GiangVien gv) {
        if(gvDAO.hasMSGV(gv.getMaGiangVien())==false) return "Người này không tồn tại";
        if(gvDAO.delete(gv))
        return "Xóa thành công";
        else return "Xóa thất bại";
    }
    
    public ArrayList<GiangVien> getlistGiangVien(){
        return gvDAO.selectAll();
    }
    
    public ArrayList<GiangVien> getlistGiangVienDK(String a,String b){
        return gvDAO.selectByCondition(a,b);
    }
    
    
    
    public String update(GiangVien sv) {
        if(gvDAO.hasMSGV(sv.getMaGiangVien())==false) 
            return "Người này không tồn tại";
        if(gvDAO.update(sv)) 
        return "Sửa thành công";
        else return "Sửa thất bại";
    }
    
    public String insert(GiangVien sv) {
        if (gvDAO.hasMSGV(sv.getMaGiangVien()))
            return "Người này đã tồn tại";
        if (gvDAO.insert(sv))
            return "Thêm thành công"; 
        return "Thêm thất bại"; 
    }
}
