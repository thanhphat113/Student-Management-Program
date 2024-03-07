/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BUS.BUS;
import DTO.HocPhan;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author lythanhphat9523
 */
public class HocPhanDAO {
    public boolean hasMSHP(String mshp){
    try {
        Connection conn= BUS.getConnection();
        Statement st=conn.createStatement();
        String sql = "Select * from LopHocPhan where maLopHocPhan='" + mshp+"'";
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
    
    
    public static HocPhan selectById(String ID) {
        HocPhan t=new HocPhan();
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            String sql="SELECT * FROM LopHocPhan WHERE maLopHocPhan='"+ID+"'";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                t.setMaHP(rs.getString("maLopHocPhan"));
                t.setMaMH(rs.getString("maMonHoc"));
                t.setMaGiangVien(rs.getString("maGiangVien"));
                t.setTimeBatDau(rs.getString("thoiGianBatDau"));
                t.setTimeKetThuc(rs.getString("thoiGianKetThuc"));
                t.setTietBatDau(rs.getString("tietBatDau"));
                t.setMaPhong(rs.getString("maPhongHoc"));
                t.setThu(rs.getInt("thu"));
            } 
            
            BUS.closeConnection(conn);
        }catch (SQLException e){
            e.printStackTrace(); 
        }
        return t;
    }
    
    public static boolean insert(HocPhan t) {
        try{
            //Buoc 1:
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            String sql="INSERT INTO LopHocPhan(maLopHocPhan,maMonHoc,maPhongHoc,maGiangVien,thu,tietBatDau,thoiGianBatDau,thoiGianKetThuc)" +
                       "VALUES('" +t.getMaHP()+ "','" +t.getMaMH()+ "','" +t.getMaPhong()+ "','" +t.getMaGiangVien()+"',"+t.getThu()+ "',"+t.getTietBatDau()+",'"+t.getTimeKetThuc()+"','"+t.getTimeBatDau()+"')";
            
            int ketqua=st.executeUpdate(sql);
            if (ketqua>0) return true;
            
            BUS.closeConnection(conn);
        }catch (SQLException e){
            e.printStackTrace(); 
        }
        return false;
    }
    
    public static boolean update(HocPhan t) {
        
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            String sql="UPDATE LopHocPhan SET maMonHoc= '"+t.getMaMH()+"',maPhongHoc=N'"+t.getMaPhong()+"',maGiangVien='"+t.getMaGiangVien()+"',thoiGianBatDau='"+t.getTimeBatDau()+"',thoiGianKetThuc='"+t.getTimeKetThuc()+"',tietBatDau='"+t.getTietBatDau()+"',thu='"+t.getThu()+"'"
                    + "WHERE maLopHocPhan='"+ t.getMaHP() +"'";   
            int ketqua=st.executeUpdate(sql);
            if (ketqua>0) return true;
            BUS.closeConnection(conn);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean delete(HocPhan t) {
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            String sql="DELETE FROM LopHocPhan WHERE maLopHocPhan='"+ t.getMaHP() +"'";
            
            int ketqua=st.executeUpdate(sql);
            if (ketqua>0) return true;
        }catch (SQLException e){
            e.printStackTrace(); 
        }
        return false;
    }
    
    public static ArrayList<HocPhan> selectAll() {
        ArrayList<HocPhan> list =new ArrayList<HocPhan>();
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            
            String sql="SELECT mh.*,tenGiangVien,lhp.maLopHocPhan,thu,lhp.tietBatDau,lhp.thoiGianBatDau,lhp.thoiGianKetThuc,ph.tenPhong " +
                        "FROM MonHoc mh JOIN LopHocPhan lhp on mh.maMonHoc=lhp.maMonHoc " +
                        "JOIN GiangVien gv on gv.maGiangVien=lhp.maGiangVien " +
                        "JOIN PhongHoc ph on ph.maPhong=lhp.maPhongHoc";
            
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
                int thu=rs.getInt("thu");
            
                HocPhan hp= new HocPhan(maHP,tenMH,tinChi,tenGV,tenPhong,thu,tietBatDau,timeBD,timeKT);
                list.add(hp);
            }
            
            BUS.closeConnection(conn);
            
        }catch (SQLException e){
            e.printStackTrace(); 
        }
    return list;   
}
    
    public static ArrayList<HocPhan> selectByCondition(String DuLieu,String dieuKien) {
        ArrayList<HocPhan> list =new ArrayList<HocPhan>();
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            
            String sql="SELECT mh.*,tenGiangVien,lhp.maLopHocPhan,thu,lhp.tietBatDau,lhp.thoiGianBatDau,lhp.thoiGianKetThuc,ph.tenPhong " +
                        "FROM MonHoc mh JOIN LopHocPhan lhp on mh.maMonHoc=lhp.maMonHoc " +
                        "JOIN GiangVien gv on gv.maGiangVien=lhp.maGiangVien " +
                        "JOIN PhongHoc ph on ph.maPhong=lhp.maPhongHoc "+
                        "WHERE "+DuLieu+" LIKE N'%"+dieuKien+"%' COLLATE Vietnamese_CI_AI";
            
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
                int thu=rs.getInt("thu");
            
                HocPhan hp= new HocPhan(maHP,tenMH,tinChi,tenGV,tenPhong,thu,tietBatDau,timeBD,timeKT);
                list.add(hp);
            }
            
                BUS.closeConnection(conn);

                }catch (SQLException e){
                    e.printStackTrace(); 
                }
            return list;   
        }
    
}
