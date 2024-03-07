/*
 * Click nbfs:nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs:nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BUS.BUS;
import DTO.HocPhi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HocPhiDAO {
    public static HocPhi selectById(String ID) {
        HocPhi t=new HocPhi();
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            String sql="SELECT * FROM HocPhi WHERE maSinhvien='"+ID+"'";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                t.setMaHP(rs.getString("maHocPhi"));
                t.setMaSV(rs.getString("tenSinhVien"));
                t.setHocKy(rs.getString("hocKy"));
                t.setNamHoc(rs.getString("namHoc"));
                t.setTinhTrang(rs.getString("tinhTrang"));
                t.setTongTien(rs.getString("tongTien"));
            } 
            
            BUS.closeConnection(conn);
        }catch (SQLException e){
            e.printStackTrace(); 
        }
        return t;
    }
    
    public static boolean insert(HocPhi t) {
        try{
            //Buoc 1:
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            String sql="INSERT INTO HocPhi(maHocPhi, maSinhVien, hocKy, namHoc, tinhTrang, tongTien)" +
                       "VALUES('" +t.getMaHP()+ "',N'" +t.getMaSV()+ "','" +t.getHocKy()+ "',N'" +t.getNamHoc()+ "','" + t.getTinhTrang()+ "','" +t.getTongTien()+"','-',''))";
            
            int ketqua=st.executeUpdate(sql);
            if (ketqua>0) return true;
            
            BUS.closeConnection(conn);
        }catch (SQLException e){
            e.printStackTrace(); 
        }
        return false;
    }
    
    
    public static boolean update(HocPhi t) { 
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            String sql="UPDATE HocPhi SET maSinhVien= N'"+t.getMaSV()+"',hocKy= '"+t.getHocKy()+"',namHoc=N'"+t.getNamHoc()+
                    "',tinhTrang='"+t.getTinhTrang()+"',tongTien='"+t.getTongTien() +"'";     
            int ketqua=st.executeUpdate(sql);
            if (ketqua>0) return true;
            BUS.closeConnection(conn);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    
    public static boolean delete(String t) {
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            String sql="DELETE FROM HocPhi WHERE maSinhvien='"+ t +"'";
            
            int ketqua=st.executeUpdate(sql);
            if (ketqua>0) return true;
        }catch (SQLException e){
            e.printStackTrace(); 
        }
        return false;
    }

    
    public static ArrayList<HocPhi> selectAll() {
        ArrayList<HocPhi> list =new ArrayList<HocPhi>();
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            
            String sql="SELECT hp.*,l.tenLop,k.tenKhoa,gv.tenGiangVien AS tenCoVan "
                        + "FROM HocPhi hp JOIN Lop L ON hp.maLop=l.maLop " +
                                           "JOIN Khoa k ON hp.maKhoa=k.maKhoa " +
                                           "JOIN GiangVien gv ON l.coVanHocTap=gv.maGiangVien";
            
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                String maHP = rs.getString("maHocPhi");
                String maSV = rs.getString("maSinhVien");
                String hocKy = rs.getString("hocKy");
                String namHoc = rs.getString("namHoc");
                String tinhTrang = rs.getString("tinhTrang");
                String tongTien = rs.getString("tongTien");
            
                HocPhi hp=new HocPhi(maHP, maSV, hocKy, namHoc, tinhTrang, tongTien);
                list.add(hp);
            }
            
            BUS.closeConnection(conn);
            
        }catch (SQLException e){
            e.printStackTrace(); 
        }
    return list;   
}


    
    public static ArrayList<HocPhi> selectByCondition(String DuLieu,String dieuKien) {
        ArrayList<HocPhi> list =new ArrayList<HocPhi>();
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            
            String sql="SELECT hp.*,l.tenLop,k.tenKhoa, gv.tenGiangVien AS tenCoVan "
                        + "FROM HocPhi hp JOIN Lop L ON hp.maLop=l.maLop " +
                                           "JOIN Khoa k ON hp.maKhoa=k.maKhoa " +
                                           "JOIN GiangVien gv ON l.coVanHocTap=gv.maGiangVien"
                    + " WHERE "+DuLieu+" LIKE N'%"+dieuKien+"%' COLLATE Vietnamese_CI_AI";
            
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                String maHP = rs.getString("maHocPhi");
                String maSV = rs.getString("maSinhVien");
                String hocKy = rs.getString("hocKy");
                String namHoc = rs.getString("namHoc");
                String tinhTrang = rs.getString("tinhTrang");
                String tongTien = rs.getString("tongTien");
            
                HocPhi hp=new HocPhi(maHP, maSV, hocKy, namHoc, tinhTrang, tongTien);
                list.add(hp);
            }
            
                BUS.closeConnection(conn);

                }catch (SQLException e){
                    e.printStackTrace(); 
                }
            return list;   
        }
    
     public static ArrayList<HocPhi> selectBySpecialCondition(HocPhi t) {
        ArrayList<HocPhi> list =new ArrayList<HocPhi>();
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            String maHP=t.getMaHP();
            String maSV=t.getMaSV();
            String hocKy=t.getHocKy();
            String namHoc=t.getNamHoc();
            String tinhTrang=t.getTinhTrang();
            String tongTien=t.getTongTien();
            
            boolean flag=false;
            
            String sql="SELECT hp.*,maSV,hocKy, namHoc, tinhTrang, tongTieng"
                        + "FROM HocPhi hp JOIN Lop L ON hp.maHP=l.maHP " 
                    + " WHERE 1=1 ";
            if(!maHP.isEmpty()){
                sql +="AND maSinhvien LIKE '%"+maHP+"%'";
                flag=true;
            }
            if(!maSV.isEmpty()){
                sql +="AND tenHocPhi LIKE N'%"+maSV+"%'";
                flag=true;
            }
            if(!hocKy.isEmpty()){
                sql +="AND hocKy LIKE '%"+hocKy+"%'";
                flag=true;
            }
            if(!namHoc.isEmpty()){
                sql +="AND hp.gioiTinh LIKE N'%"+namHoc+"%'";
                flag=true;
            }
            if (flag)   sql +="COLLATE Vietnamese_CI_AI";
            
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                String maHP1 = rs.getString("maHocPhi");
                String maSV1  = rs.getString("maSinhVien");
                String hocKy1 = rs.getString("hocKy");
                String namHoc1 = rs.getString("namHoc");
                String tinhTrang1 = rs.getString("tinhTrang");
                String tongTien1 = rs.getString("tongTien");
                
                HocPhi hp=new HocPhi(maHP1, maSV1, hocKy1, namHoc1, tinhTrang1, tongTien1);
                list.add(hp);
            }
            
            BUS.closeConnection(conn);

                }catch (SQLException e){
                    e.printStackTrace(); 
                }
            return list;   

        }
     
   
    
}

