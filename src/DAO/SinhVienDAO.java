/*
 * Click nbfs:nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs:nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BUS.BUS;
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
public class SinhVienDAO {
    
    public boolean hasMSSV(String mssv){
    try {
        Connection conn= BUS.getConnection();
        Statement st=conn.createStatement();
        String sql = "Select * from SinhVien where maSinhvien='" + mssv+"'";
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
        
    
    public static SinhVien selectById(String ID) {
        SinhVien t=new SinhVien();
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            String sql="SELECT * FROM SinhVien WHERE maSinhvien='"+ID+"'";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                t.setMaSinhVien(rs.getString("maSinhvien"));
                t.setTenSinhVien(rs.getString("tenSinhVien"));
                t.setNamSinh(rs.getString("namSinh"));
                t.setGioiTinh(rs.getString("gioiTinh"));
                t.setDanToc(rs.getString("danToc"));
                t.setCCCD(rs.getString("CCCD"));
                t.setSoDienThoai(rs.getString("soDienThoai"));
                t.setMaKhoa(rs.getString("maKhoa"));
                t.setMaLop(rs.getString("maLop"));
                t.setMatKhau(rs.getString("matKhau"));
            } 
            
            BUS.closeConnection(conn);
        }catch (SQLException e){
            e.printStackTrace(); 
        }
        return t;
    }
    
    public static boolean insert(SinhVien t) {
        try{
            //Buoc 1:
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            String sql="INSERT INTO SinhVien(maSinhvien,tenSinhVien,namSinh,gioiTinh,danToc,CCCD,soDienThoai,maKhoa,maLop,matKhau)" +
                       "VALUES('" +t.getMaSinhVien()+ "',N'" +t.getTenSinhVien()+ "','" +t.getNamSinh()+ "',N'" +t.getGioiTinh()+ "',N'" +
                                t.getDanToc()+ "','" +t.getCCCD()+ "','" +t.getSoDienThoai()+ "','" +t.getMaKhoa()+ "','" +t.getMaLop()+ "',REPLACE('"+t.getNamSinh()+"','-',''))";
            
            int ketqua=st.executeUpdate(sql);
            if (ketqua>0) return true;
            
            BUS.closeConnection(conn);
        }catch (SQLException e){
            e.printStackTrace(); 
        }
        return false;
    }
    
    
    public static boolean update(SinhVien t) { 
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

                //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            String sql="UPDATE SinhVien SET tenSinhVien= N'"+t.getTenSinhVien()+"',namSinh= '"+t.getNamSinh()+"',gioiTinh=N'"+t.getGioiTinh()+
                    "',CCCD='"+t.getCCCD()+"',maKhoa='"+t.getMaKhoa()+"',maLop='"+t.getMaLop()+"',soDienThoai='"+t.getSoDienThoai()+"',danToc=N'"+t.getDanToc()+"'"
                    + "WHERE maSinhvien='"+ t.getMaSinhVien() +"'";     
            int ketqua=st.executeUpdate(sql);
            if (ketqua>0) return true;
            BUS.closeConnection(conn);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    
    public static boolean delete(SinhVien sv) {
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            String sql="DELETE FROM SinhVien WHERE maSinhvien='"+ sv.getMaSinhVien() +"'";
            
            int ketqua=st.executeUpdate(sql);
            if (ketqua>0) return true;
        }catch (SQLException e){
            e.printStackTrace(); 
        }
        return false;
    }

    
    public static ArrayList<SinhVien> selectAll() {
        ArrayList<SinhVien> list =new ArrayList<SinhVien>();
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            
            String sql="SELECT sv.*,l.tenLop,k.tenKhoa,gv.tenGiangVien AS tenCoVan "
                        + "FROM SinhVien sv JOIN Lop L ON sv.maLop=l.maLop " +
                                           "JOIN Khoa k ON sv.maKhoa=k.maKhoa " +
                                           "JOIN GiangVien gv ON l.coVanHocTap=gv.maGiangVien";
            
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                String maSinhVien = rs.getString("maSinhvien");
                String tenSinhVien = rs.getString("tenSinhvien");
                String namSinh = rs.getString("namSinh");
                String gioiTinh = rs.getString("gioiTinh");
                String danToc = rs.getString("danToc");
                String CCCD = rs.getString("CCCD");
                String soDienThoai = rs.getString("soDienThoai");
                String tenKhoa = rs.getString("tenKhoa");
                String tenLop = rs.getString("tenLop");
                String tenCoVan=rs.getString("tenCoVan");
            
                SinhVien sv=new SinhVien(maSinhVien,tenSinhVien,namSinh,gioiTinh,danToc,CCCD,soDienThoai,tenCoVan,tenKhoa,tenLop);
                list.add(sv);
            }
            
            BUS.closeConnection(conn);
            
        }catch (SQLException e){
            e.printStackTrace(); 
        }
    return list;   
}


    
    public static ArrayList<SinhVien> selectByCondition(String DuLieu,String dieuKien) {
        ArrayList<SinhVien> list =new ArrayList<SinhVien>();
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            
            String sql="SELECT sv.*,l.tenLop,k.tenKhoa, gv.tenGiangVien AS tenCoVan "
                        + "FROM SinhVien sv JOIN Lop L ON sv.maLop=l.maLop " +
                                           "JOIN Khoa k ON sv.maKhoa=k.maKhoa " +
                                           "JOIN GiangVien gv ON l.coVanHocTap=gv.maGiangVien"
                    + " WHERE "+DuLieu+" LIKE N'%"+dieuKien+"%' COLLATE Vietnamese_CI_AI";
            System.out.println(sql);
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                String maSinhVien = rs.getString("maSinhvien");
                String tenSinhVien = rs.getString("tenSinhvien");
                String namSinh = rs.getString("namSinh");
                String gioiTinh = rs.getString("gioiTinh");
                String danToc = rs.getString("danToc");
                String CCCD = rs.getString("CCCD");
                String soDienThoai = rs.getString("soDienThoai");
                String tenKhoa = rs.getString("tenKhoa");
                String tenLop = rs.getString("tenLop");
                String tenCoVan=rs.getString("tenCoVan");
            
                SinhVien sv=new SinhVien(maSinhVien,tenSinhVien,namSinh,gioiTinh,danToc,CCCD,soDienThoai,tenCoVan,tenKhoa,tenLop);
                list.add(sv);
            }
            
                BUS.closeConnection(conn);

                }catch (SQLException e){
                    e.printStackTrace(); 
                }
            return list;   
        }
    
     public static ArrayList<SinhVien> selectBySpecialCondition(SinhVien t) {
        ArrayList<SinhVien> list =new ArrayList<SinhVien>();
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            String maSV=t.getMaSinhVien();
            String tenSV=t.getTenSinhVien();
            String namSinh=t.getNamSinh();
            String gioiTinh=t.getGioiTinh();
            String danToc=t.getDanToc();
            String CCCD=t.getCCCD();
            String sdt=t.getSoDienThoai();
            String tenKhoa=t.getTenKhoa();
            String tenLop=t.getTenLop();
            String tenCoVan=t.getTenCoVan();
            
            boolean flag=false;
            
            String sql="SELECT sv.*,tenLop,tenKhoa,gv.tenGiangVien AS tenCoVan "
                        + "FROM SinhVien sv JOIN Lop L ON sv.maLop=l.maLop " +
                                           "JOIN Khoa k ON sv.maKhoa=k.maKhoa " +
                                           "JOIN GiangVien gv ON l.coVanHocTap=gv.maGiangVien"
                    + " WHERE 1=1 ";
            if(!maSV.isEmpty()){
                sql +="AND maSinhvien LIKE '%"+maSV+"%'";
                flag=true;
            }
            if(!tenSV.isEmpty()){
                sql +="AND tenSinhVien LIKE N'%"+tenSV+"%'";
                flag=true;
            }
            if(!namSinh.isEmpty()){
                sql +="AND namSinh LIKE '%"+namSinh+"%'";
                flag=true;
            }
            if(!gioiTinh.isEmpty()){
                sql +="AND sv.gioiTinh LIKE N'%"+gioiTinh+"%'";
                flag=true;
            }
            if(!danToc.isEmpty()){
                sql +="AND danToc LIKE N'%"+danToc+"%'";
                flag=true;
            }
            if(!CCCD.isEmpty()){
                sql +="AND CCCD LIKE '%"+CCCD+"%'";
                flag=true;
            }
            if(!sdt.isEmpty()){
                sql +="AND soDienThoai LIKE '%"+sdt+"%'";
                flag=true;
            }
            if(!tenKhoa.isEmpty()){
                sql +="AND tenKhoa LIKE N'%"+tenKhoa+"%'";
                flag=true;
            }
            if(!tenLop.isEmpty()){
                sql +="AND tenLop LIKE '%"+tenLop+"%'";
                flag=true;
            }
            if(!tenCoVan.isEmpty()){
                sql +="AND gv.tenGiangVien LIKE N'%"+tenCoVan+"%'";
                flag=true;
            }
            if (flag)   sql +="COLLATE Vietnamese_CI_AI";
            
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                String maSinhVien = rs.getString("maSinhvien");
                String tenSinhVien = rs.getString("tenSinhvien");
                String namSinhSV = rs.getString("namSinh");
                String gioiTinhSV = rs.getString("gioiTinh");
                String danTocSV = rs.getString("danToc");
                String CCCDSV = rs.getString("CCCD");
                String soDienThoai = rs.getString("soDienThoai");
                String tenKhoaSV = rs.getString("tenKhoa");
                String tenLopSV = rs.getString("tenLop");
                String tenCoVanSV=rs.getString("tenCoVan");
            
                SinhVien sv=new SinhVien(maSinhVien,tenSinhVien,namSinhSV,gioiTinhSV,danTocSV,CCCDSV,soDienThoai,tenCoVanSV,tenKhoaSV,tenLopSV);
                list.add(sv);
            }
            
            BUS.closeConnection(conn);

                }catch (SQLException e){
                    e.printStackTrace(); 
                }
            return list;   

        }
     
    
    public static boolean changePass(SinhVien t,String newpass){
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            
            String sql="UPDATE SinhVien SET matKhau='"+newpass+"' WHERE maSinhvien='"+t.getMaSinhVien()+"'";
            int ketqua=st.executeUpdate(sql);
            if (ketqua>0) return true;
            }catch (SQLException e){
                e.printStackTrace(); 
        }
        
        return false;
    }
    
}

