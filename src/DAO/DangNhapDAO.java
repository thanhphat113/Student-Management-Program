/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BUS.BUS;
import DTO.SinhVien;
import java.sql.*;


/**
 *
 * @author lythanhphat9523
 */
public class DangNhapDAO {
    public static boolean DangNhapTaiKhoan(String user,String mk,int vaiTro){
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();

            //Buoc 3:
            String chucVu;
            String maSo;
            if(vaiTro==1){
                chucVu="SinhVien";
                maSo="maSinhvien";
            }
            else if(vaiTro==2){
                chucVu="GiangVien";
                maSo="maGiangvien";
            }
            else {
                chucVu="ADMIN";
                maSo="admin";
            }
            String sql = "SELECT * FROM "+chucVu+" WHERE "+ maSo +" ='"+user+"'  AND matKhau ='"+mk+"'" ;
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs=pre.executeQuery();
            if (rs.next()) {
                String  count= rs.getString(1);
                if (!count.isEmpty() ) {
                    return true; // authentication success
                }
            }
            //Buoc 4:
            BUS.closeConnection(conn);
        } catch (SQLException e){
            e.printStackTrace();
        }
    return false; // authentication failed
    }
    
}

