/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BUS.BUS;
import DTO.PhongHoc;
import DTO.SinhVien;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author lythanhphat9523
 */
public class PhongHocDAO {
    public static PhongHoc selectById(String ID) {
        PhongHoc p=new PhongHoc();
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            String sql="SELECT * FROM PhongHoc WHERE maPhong='"+ID+"'";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                p.setMaPhong(rs.getString("maPhong"));
                p.setTenPhong(rs.getString("tenPhong"));
                p.setChucNang(rs.getString("ChucNang"));
                p.setMaKhoaQuanLy(rs.getString("maKhoaQuanLy"));
            } 
            
            BUS.closeConnection(conn);
        }catch (SQLException e){
            e.printStackTrace(); 
        }
        return p;
    }
    
    public static boolean insert(PhongHoc p) {
        try{
            //Buoc 1:
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            String sql="INSERT INTO PhongHoc(maPhong,tenPhong,ChucNang,maKhoaQuanLy" +
                       "VALUES('" +p.getMaPhong()+ "',N'" +p.getTenPhong()+ "','" +p.getChucNang()+ "',N'" +p.getMaKhoaQuanLy()+ "'";
            
            int ketqua=st.executeUpdate(sql);
            if (ketqua>0) return true;
            
            BUS.closeConnection(conn);
        }catch (SQLException e){
            e.printStackTrace(); 
        }
        return false;
    }
    
    public static boolean update(PhongHoc p) {
        
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            String sql="UPDATE PhongHoc SET tenPhong= N'"+p.getTenPhong()+"',ChucNang= '"+p.getChucNang()+"',maKhoaQuanLy=N'"+p.getMaKhoaQuanLy()+"'";       
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
            String sql="DELETE FROM PhongHoc WHERE maPhong='"+ t +"'";
            
            int ketqua=st.executeUpdate(sql);
            if (ketqua>0) return true;
        }catch (SQLException e){
            e.printStackTrace(); 
        }
        return false;
    }
    
    public static ArrayList<PhongHoc> selectAll() {
        ArrayList<PhongHoc> listp =new ArrayList<PhongHoc>();
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            
            String sql="SELECT * FROM PhongHoc";
            
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                String maPhong = rs.getString("maPhong");
                String tenPhong = rs.getString("tenPhong");
                String chucNang= rs.getString("ChucNang");
                String maKhoaQuanLy= rs.getString("maKhoaQuanLy");
            
                PhongHoc ph= new PhongHoc(maPhong,tenPhong,chucNang,maKhoaQuanLy);
                listp.add(ph);
            }
            
            BUS.closeConnection(conn);
            
        }catch (SQLException e){
            e.printStackTrace(); 
        }
    return listp;   
}
    
    public static ArrayList<PhongHoc> selectByCondition(String DuLieu,String dieuKien) {
        ArrayList<PhongHoc> listp =new ArrayList<PhongHoc>();
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            
            String sql="SELECT * FROM PhongHoc WHERE "+DuLieu+" LIKE N'%"+dieuKien+"%' COLLATE Vietnamese_CI_AI";
            
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                String maPhong = rs.getString("maPhong");
                String tenPhong = rs.getString("tenPhong");
                String chucNang= rs.getString("ChucNang");
                String maKhoaQuanLy= rs.getString("maKhoaQuanLy");
            
                PhongHoc ph= new PhongHoc(maPhong,tenPhong,chucNang,maKhoaQuanLy);
                listp.add(ph);
            }
            
                BUS.closeConnection(conn);

                }catch (SQLException e){
                    e.printStackTrace(); 
                }
            return listp;   
        }
    
    public static ArrayList<PhongHoc> selectBySpecialCondition(PhongHoc p) {
        ArrayList<PhongHoc> listp =new ArrayList<PhongHoc>();
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            String maPhong=p.getMaPhong();
            String tenPhong=p.getTenPhong();
            String chucNang=p.getChucNang();
            String maKhoaQuanLy=p.getMaKhoaQuanLy();
            
            boolean flag=false;
            
            String sql="SELECT * FROM SinhVien WHERE 1=1 ";
            if(!maPhong.isEmpty()){
                sql +="AND maPhong LIKE '%"+maPhong+"%'";
                flag=true;
            }
            if(!tenPhong.isEmpty()){
                sql +="AND tenPhong LIKE N'%"+tenPhong+"%'";
                flag=true;
            }
            if(!chucNang.isEmpty()){
                sql +="AND ChucNang LIKE '%"+chucNang+"%'";
                flag=true;
            }
            if(!maKhoaQuanLy.isEmpty()){
                sql +="AND maKhoaQuanLy LIKE N'%"+maKhoaQuanLy+"%'";
                flag=true;
            }
            
            if (flag)   sql +="COLLATE Vietnamese_CI_AI";
            
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                String maPhong1 = rs.getString("maPhong");
                String tenPhong1 = rs.getString("tenPhong");
                String chucNang1= rs.getString("ChucNang");
                String maKhoaQuanLy1= rs.getString("maKhoaQuanLy");
            
                PhongHoc ph= new PhongHoc(maPhong1,tenPhong1,chucNang1,maKhoaQuanLy1);
                listp.add(ph);
            }
            
            BUS.closeConnection(conn);

                }catch (SQLException e){
                    e.printStackTrace(); 
                }
            return listp;   

        }
}
