/*
 * Click nbfs:nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs:nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BUS.BUS;
import DTO.DanhGiaKQ;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DanhGiaKQDAO {
    public static DanhGiaKQ selectById(String ID) {
        DanhGiaKQ t=new DanhGiaKQ();
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            String sql="SELECT * FROM DanhGiaK WHERE maSinhvien='"+ID+"'";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                t.setMaMH(rs.getString("maMonHoc"));
                t.setMaSV(rs.getString("tenSinhVien"));
                t.setGiuaKy(rs.getString("giuaKy"));
                t.setCuoiKy(rs.getString("cuoiKy"));
                t.setDiemTrungBinh(rs.getString("diemTrungBinh"));
                t.setDiemTrungBinhHe4(rs.getString("diemTBHe4"));
            } 
            
            BUS.closeConnection(conn);
        }catch (SQLException e){
            e.printStackTrace(); 
        }
        return t;
    }
    
    public static boolean insert(DanhGiaKQ t) {
        try{
            //Buoc 1:
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            String sql="INSERT INTO DanhGiaK(maMonHoc, maSinhVien, giuaKy, cuoiKy, diemTrungBinh, diemTrungBinhHe4)" +
                       "VALUES('" +t.getMaMH()+ "',N'" +t.getMaSV()+ "','" +t.getGiuaKy()+ "',N'" +t.getCuoiKy()+ "','" + t.getDiemTrungBinh()+ "','" +t.getDiemTrungBinhHe4()+"','-',''))";
            
            int K=st.executeUpdate(sql);
            if (K>0) return true;
            
            BUS.closeConnection(conn);
        }catch (SQLException e){
            e.printStackTrace(); 
        }
        return false;
    }
    
    
    public static boolean update(DanhGiaKQ t) { 
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            String sql="UPDATE DanhGiaK SET maSinhVien= N'"+t.getMaSV()+"',giuaKy= '"+t.getGiuaKy()+"',cuoiKy=N'"+t.getCuoiKy()+
                    "',diemTrungBinh='"+t.getDiemTrungBinh()+"',diemTrungBinhHe4='"+t.getDiemTrungBinhHe4() +"'";     
            int K=st.executeUpdate(sql);
            if (K>0) return true;
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
            String sql="DELETE FROM DanhGiaK WHERE maSinhvien='"+ t +"'";
            
            int K=st.executeUpdate(sql);
            if (K>0) return true;
        }catch (SQLException e){
            e.printStackTrace(); 
        }
        return false;
    }

    
    public static ArrayList<DanhGiaKQ> selectAll() {
        ArrayList<DanhGiaKQ> list =new ArrayList<DanhGiaKQ>();
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            
            String sql="SELECT t.*,l.tenLop,k.tenKhoa,gv.tenGiangVien AS tenCoVan "
                        + "FROM DanhGiaK t JOIN Lop L ON t.maLop=l.maLop " +
                                           "JOIN Khoa k ON t.maKhoa=k.maKhoa " +
                                           "JOIN GiangVien gv ON l.coVanHocTap=gv.maGiangVien";
            
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                String maHP = rs.getString("maMonHoc");
                String maSV = rs.getString("maSinhVien");
                String giuaKy = rs.getString("giuaKy");
                String cuoiKy = rs.getString("cuoiKy");
                String diemTrungBinh = rs.getString("diemTrungBinh");
                String diemTrungBinhHe4 = rs.getString("diemTrungBinhHe4");
            
                DanhGiaKQ t=new DanhGiaKQ(maHP, maSV, giuaKy, cuoiKy, diemTrungBinh, diemTrungBinhHe4);
                list.add(t);
            }
            
            BUS.closeConnection(conn);
            
        }catch (SQLException e){
            e.printStackTrace(); 
        }
    return list;   
}


    
    public static ArrayList<DanhGiaKQ> selectByCondition(String DuLieu,String dieuKien) {
        ArrayList<DanhGiaKQ> list =new ArrayList<DanhGiaKQ>();
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            
            String sql="SELECT t.*,l.tenLop,k.tenKhoa, gv.tenGiangVien AS tenCoVan "
                        + "FROM DanhGiaK t JOIN Lop L ON t.maLop=l.maLop " +
                                           "JOIN Khoa k ON t.maKhoa=k.maKhoa " +
                                           "JOIN GiangVien gv ON l.coVanHocTap=gv.maGiangVien"
                    + " WHERE "+DuLieu+" LIKE N'%"+dieuKien+"%' COLLATE Vietnamese_CI_AI";
            
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                String maHP = rs.getString("maMonHoc");
                String maSV = rs.getString("maSinhVien");
                String giuaKy = rs.getString("giuaKy");
                String cuoiKy = rs.getString("cuoiKy");
                String diemTrungBinh = rs.getString("diemTrungBinh");
                String diemTrungBinhHe4 = rs.getString("diemTrungBinhHe4");
            
                DanhGiaKQ t=new DanhGiaKQ(maHP, maSV, giuaKy, cuoiKy, diemTrungBinh, diemTrungBinhHe4);
                list.add(t);
            }
            
                BUS.closeConnection(conn);

                }catch (SQLException e){
                    e.printStackTrace(); 
                }
            return list;   
        }
    
     public static ArrayList<DanhGiaKQ> selectBySpecialCondition(DanhGiaKQ t) {
        ArrayList<DanhGiaKQ> list =new ArrayList<DanhGiaKQ>();
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            String maHP=t.getMaMH();
            String maSV=t.getMaSV();
            String giuaKy=t.getGiuaKy();
            String cuoiKy=t.getCuoiKy();
            String diemTrungBinh=t.getDiemTrungBinh();
            String diemTrungBinhHe4=t.getDiemTrungBinhHe4();
            
            boolean flag=false;
            
            String sql="SELECT t.*,maSV,giuaKy, cuoiKy, diemTrungBinh, diemTrungBinhHe4g"
                        + "FROM DanhGiaK t JOIN Lop L ON t.maHP=l.maHP " 
                    + " WHERE 1=1 ";
            if(!maHP.isEmpty()){
                sql +="AND maSinhvien LIKE '%"+maHP+"%'";
                flag=true;
            }
            if(!maSV.isEmpty()){
                sql +="AND tenDanhGiaK LIKE N'%"+maSV+"%'";
                flag=true;
            }
            if(!giuaKy.isEmpty()){
                sql +="AND giuaKy LIKE '%"+giuaKy+"%'";
                flag=true;
            }
            if(!cuoiKy.isEmpty()){
                sql +="AND t.gioiTinh LIKE N'%"+cuoiKy+"%'";
                flag=true;
            }
            if (flag)   sql +="COLLATE Vietnamese_CI_AI";
            
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                String maHP1 = rs.getString("maMonHoc");
                String maSV1  = rs.getString("maSinhVien");
                String giuaKy1 = rs.getString("giuaKy");
                String cuoiKy1 = rs.getString("cuoiKy");
                String diemTrungBinh1 = rs.getString("diemTrungBinh");
                String diemTrungBinhHe41 = rs.getString("diemTrungBinhHe4");
                
                DanhGiaKQ t1 = new DanhGiaKQ(maHP1, maSV1, giuaKy1, cuoiKy1, diemTrungBinh1, diemTrungBinhHe41);
                list.add(t1);
            }
            
            BUS.closeConnection(conn);

                }catch (SQLException e){
                    e.printStackTrace(); 
                }
            return list;   

        }
     
   
    
}

