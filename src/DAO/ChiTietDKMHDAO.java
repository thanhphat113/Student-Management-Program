/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BUS.BUS;
import DTO.ChiTietDKMH;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author lythanhphat9523
 */
public class ChiTietDKMHDAO {
        public static ChiTietDKMH selectById(String ID) {
        ChiTietDKMH t=new ChiTietDKMH();
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            String sql="SELECT * FROM ChiTietDangKyMonHoc WHERE maSinhVien='"+ID+"'";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                t.setMaLopHocPhan(rs.getString("maLopHocPhan"));
                t.setMaSinhVien(rs.getString("maSinhVien"));
            } 
            
            BUS.closeConnection(conn);
        }catch (SQLException e){
            e.printStackTrace(); 
        }
        return t;
    }
        
        public static boolean delete(String t) {
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            String sql="DELETE FROM ChiTietDangKyMonHoc WHERE maLopHocPhan='"+ t +"'";
            
            int ketqua=st.executeUpdate(sql);
            if (ketqua>0) return true;
        }catch (SQLException e){
            e.printStackTrace(); 
        }
        return false;
    }
    
       public static boolean insert(ChiTietDKMH t) {
        try{
            //Buoc 1:
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            String sql="INSERT INTO ChiTietDangKyMonHoc(maLopHocPhan,maSinhVien)" +
                       "VALUES('" +t.getMaLopHocPhan()+ "','" +t.getMaSinhVien()+"')";
            
            int ketqua=st.executeUpdate(sql);
            if (ketqua>0) return true;
            
            BUS.closeConnection(conn);
        }catch (SQLException e){
            e.printStackTrace(); 
        }
        return false;
    }
       
       public static ArrayList<ChiTietDKMH> selectAll(String t) {
        ArrayList<ChiTietDKMH> list =new ArrayList<ChiTietDKMH>();
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            
            String sql="select dk.maLopHocPhan,tenMonHoc,soTinChi,tenGiangVien,tenPhong,thoiGianBatDau,thoiGianKetThuc,thu,tietBatDau,hocPhi "
                        +"from ChiTietDangKyMonHoc dk JOIN LopHocPhan hp on hp.maLopHocPhan=dk.maLopHocPhan "
                            +                        "JOIN GiangVien gv on hp.maGiangVien=gv.maGiangVien "
                            +                        "JOIN PhongHoc ph on hp.maPhongHoc=ph.maPhong "
                            +                        "JOIN MonHoc mh on mh.maMonHoc=hp.maMonHoc"
                            + " where maSinhVien='"+ t +"'";
            
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                String maHP = rs.getString("maLopHocPhan");
                String tenMH = rs.getString("tenMonHoc");
                String tenGV= rs.getString("tenGiangVien");
                String tinChi= rs.getString("soTinChi");
                String timeBD= rs.getString("thoiGianBatDau");
                String timeKT= rs.getString("thoiGianKetThuc");
                String tietBatDau= rs.getString("tietBatDau");
                String tenPhong=rs.getString("tenPhong");
                int hocPhi=rs.getInt("hocPhi");
                int thu=rs.getInt("thu");
            
                ChiTietDKMH hp= new ChiTietDKMH(maHP,tenMH,tinChi,tenGV,tenPhong,thu,tietBatDau,timeBD,timeKT,hocPhi);
                list.add(hp);
            }
            
            BUS.closeConnection(conn);
            
        }catch (SQLException e){
            e.printStackTrace(); 
        }
    return list;   
}
}
