/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.GiangVienDAO;
import DAO.HocPhanDAO;
import DTO.GiangVien;
import DTO.HocPhan;
import java.util.ArrayList;

/**
 *
 * @author lythanhphat9523
 */
public class HocPhanBUS {
    HocPhanDAO hpDAO = new HocPhanDAO();
    public String delete(HocPhan hp) {
        if(hpDAO.hasMSHP(hp.getMaHP())==false) return "Học phần này không tồn tại";
        if(hpDAO.delete(hp))
        return "Xóa thành công";
        else return "Xóa thất bại";
    }
    
    public ArrayList<HocPhan> getlistHocPhan(){
        return hpDAO.selectAll();
    }
    
    public ArrayList<HocPhan> getlistHocPhanDK(String a,String b){
        return hpDAO.selectByCondition(a,b);
    }
    

    public String update(HocPhan hp) {
        if(hpDAO.hasMSHP(hp.getMaHP())==false) 
            return "Người này không tồn tại";
        if(hpDAO.update(hp)) 
        return "Sửa thành công";
        else return "Sửa thất bại";
    }
    
    public String insert(HocPhan hp) {
        if (hpDAO.hasMSHP(hp.getMaHP()))
            return "Người này đã tồn tại";
        if (hpDAO.insert(hp))
            return "Thêm thành công"; 
        return "Thêm thất bại"; 
    }
}
