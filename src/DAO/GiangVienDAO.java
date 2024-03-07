/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BUS.BUS;
import DTO.GiangVien;
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
public class GiangVienDAO {
    public boolean hasMSGV(String msgv){
    try {
        Connection conn= BUS.getConnection();
        Statement st=conn.createStatement();
        String sql = "Select * from GiangVien where maGiangVien='" + msgv+"'";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next())
            return true;
        BUS.closeConnection(conn);
        } catch (SQLException ex) {
            System.out.println(ex);
        } 
        return false;
    }
    
    public static GiangVien selectById(String ID) {
        GiangVien t=new GiangVien();
        try{
             //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            String sql="SELECT * FROM GiangVien WHERE maGiangVien='"+ID+"'";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                t.setMaGiangVien(rs.getString("maGiangVien"));
                t.setTenGiangVien(rs.getString("tenGiangVien"));
                t.setSdt(rs.getString("soDienThoai"));
                t.setTrinhDo(rs.getString("TrinhDo"));
                t.setEmail(rs.getString("email"));
                t.setMaKhoa(rs.getString("maKhoa"));
                t.setGioiTinh(rs.getString("gioiTinh"));
                t.setNamSinh(rs.getString("namSinh"));
                t.setMatKhau(rs.getString("matKhau"));
            } 
            
            BUS.closeConnection(conn);
        }catch (SQLException e){
            e.printStackTrace(); 
        }
            return t;
    }
    
    public static boolean insert(GiangVien t) {
        try{
             //Buoc 1:i
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            String sql="INSERT INTO GiangVien(maGiangVien,tenGiangVien,gioiTinh,namSinh,trinhDo,soDienThoai,email,maKhoa,matKhau)" +
                       "VALUES('" +t.getMaGiangVien()+ "',N'" +t.getTenGiangVien()+ "',N'" +t.getGioiTinh()+ "','" +t.getNamSinh()+ "',N'" +
                                t.getTrinhDo()+ "','" +t.getSdt()+ "','" +t.getEmail()+ "','" +t.getMaKhoa()+ "',REPLACE('"+t.getNamSinh()+"','-','')) ";
            
            
            int ketqua=st.executeUpdate(sql);
            if (ketqua>0) return true;
            
            BUS.closeConnection(conn);
        }catch (SQLException e){
            e.printStackTrace(); 
        }
        return false;
    }
    
        
    
    
    public static boolean update(GiangVien t) {
        try{
             //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            String sql="UPDATE GiangVien SET tenGiangVien= N'"+t.getTenGiangVien()+"',namSinh= '"+t.getNamSinh()+"',gioiTinh=N'"+t.getGioiTinh()+
                    "',trinhDo=N'"+t.getTrinhDo()+"',maKhoa='"+t.getMaKhoa()+"',soDienThoai='"+t.getSdt()+"',email='"+t.getEmail()+"'"
                    + "WHERE maGiangVien='"+ t.getMaGiangVien() +"'";          
            int ketqua=st.executeUpdate(sql);
            if (ketqua>0) return true;
            BUS.closeConnection(conn);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    
    public static boolean delete(GiangVien t) {
        try{
             //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            String sql="DELETE FROM GiangVien WHERE maGiangVien='"+ t.getMaGiangVien() +"'";
            
            int ketqua=st.executeUpdate(sql);
            if (ketqua>0) return true;
        }catch (SQLException e){
            e.printStackTrace(); 
        }
        return false;
    }

    
    public static ArrayList<GiangVien> selectAll() {
        ArrayList<GiangVien> list =new ArrayList<GiangVien>();
        try{
             //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            
            String sql="SELECT gv.*,tenKhoa "
                    + "FROM GiangVien gv JOIN Khoa k ON gv.maKhoa=k.maKhoa";
            
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                String maGiangVien = rs.getString("maGiangVien");
                String tenGiangVien = rs.getString("tenGiangVien");
                String namSinh = rs.getString("namSinh");
                String gioiTinh = rs.getString("gioiTinh");
                String email = rs.getString("email");
                String soDienThoai = rs.getString("soDienThoai");
                String tenKhoa = rs.getString("tenKhoa");
                String trinhDo = rs.getString("trinhDo");
            
                GiangVien gv=new GiangVien(maGiangVien,tenGiangVien,gioiTinh,namSinh,trinhDo,soDienThoai,email,tenKhoa);
                list.add(gv);
            }
            
            BUS.closeConnection(conn);
            
        }catch (SQLException e){
            e.printStackTrace(); 
        }
    return list;   
}


    
    public static ArrayList<GiangVien> selectByCondition(String DuLieu,String dieuKien) {
        ArrayList<GiangVien> list =new ArrayList<GiangVien>();
        try{
             //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            
            String sql="SELECT * "
                    + "FROM GiangVien gv JOIN Khoa k ON k.maKhoa=gv.maKhoa "
                    + "WHERE "+DuLieu+" LIKE N'%"+dieuKien+"%' COLLATE Vietnamese_CI_AI";
            
            
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                String maGiangVien = rs.getString("maGiangVien");
                String tenGiangVien = rs.getString("tenGiangVien");
                String namSinh = rs.getString("namSinh");
                String gioiTinh = rs.getString("gioiTinh");
                String email = rs.getString("email");
                String soDienThoai = rs.getString("soDienThoai");
                String tenKhoa = rs.getString("tenKhoa");
                String trinhDo = rs.getString("trinhDo");
            
                GiangVien gv=new GiangVien(maGiangVien,tenGiangVien,gioiTinh,namSinh,trinhDo,soDienThoai,email,tenKhoa);
                list.add(gv);
            }
            
                BUS.closeConnection(conn);

                }catch (SQLException e){
                    e.printStackTrace(); 
                }
            return list;   
        }
    
    public static boolean changePass(GiangVien t,String newpass){
        try{
             //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            
            String sql="UPDATE GiangVien SET matKhau='"+newpass+"' WHERE maGiangVien='"+t.getMaGiangVien()+"'";
            int ketqua=st.executeUpdate(sql);
            if (ketqua>0) return true;
            }catch (SQLException e){
                e.printStackTrace(); 
        }
        
        return false;
    }
    
}
