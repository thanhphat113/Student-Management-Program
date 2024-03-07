package DAO;

import BUS.BUS;
import DTO.ChiTietGiangDay;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ChiTietGiangDayDAO {
    public boolean hasMSHP(String mshp,String a){
    try {
        Connection conn= BUS.getConnection();
        Statement st=conn.createStatement();
        String sql = "Select * from ChiTietGiangDay where maLopHocPhan='" + mshp+"' AND maGiangVien='"+ a +"'";
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
    
    
    public static ChiTietGiangDay selectById(String ID) {
        ChiTietGiangDay c = new ChiTietGiangDay();
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            String sql="SELECT maHocPhan,tenMonHoc,soTinChi"
                    + " FROM ChiTietGiangDay ct JOIN MonHoc mh ON ct.maHocPhan=mh.maHocPhan"
                    + " WHERE maGiangVien='"+ID+"'";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                c.setMaHP(rs.getString("maHocPhan"));
                c.setMaGV(rs.getString("maGiangVien"));
                c.setSoTinChi(rs.getString("soTinChi"));
            }  
            
            BUS.closeConnection(conn);
        }catch (SQLException e){
            e.printStackTrace(); 
        }
        return c;
    }
    
    public static boolean insert(ChiTietGiangDay c) {
        try{
            //Buoc 1:
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            String sql="INSERT INTO ChiTietGiangDay(maHocPhan,maGiangVien)" +
                       "VALUES('" +c.getMaHP()+ "',N'" +c.getMaGV()+ "')";
            
            int ketqua=st.executeUpdate(sql);
            if (ketqua>0) return true;
            
            BUS.closeConnection(conn);
        }catch (SQLException e){
            e.printStackTrace(); 
        }
        return false;
    }
    
    public static boolean update(ChiTietGiangDay c) {
        
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            String sql="UPDATE ChiTietGiangDay SET maGiangVien= N'"+c.getMaGV()+"'";       
            int ketqua=st.executeUpdate(sql);
            if (ketqua>0) return true;
            BUS.closeConnection(conn);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean delete(String c,String gv) {
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            String sql="DELETE FROM ChiTietGiangDay WHERE maHocPhan='"+ c +"' AND maGiangVien='"+gv+"'";
            
            int ketqua=st.executeUpdate(sql);
            if (ketqua>0) return true;
        }catch (SQLException e){
            e.printStackTrace(); 
        }
        return false;
    }
    
    public static ArrayList<ChiTietGiangDay> selectAll(String ID) {
        ArrayList<ChiTietGiangDay> listctgd =new ArrayList<ChiTietGiangDay>();
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            
            String sql="SELECT maHocPhan,tenMonHoc,soTinChi"
                    + " FROM ChiTietGiangDay ct JOIN MonHoc mh ON ct.maHocPhan=mh.maMonHoc"
                    + " WHERE maGiangVien='"+ID+"'";
            
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                String maHP = rs.getString("maHocPhan");
                String tenHP = rs.getString("tenMonHoc");
                String soTinChi = rs.getString("soTinChi");
                ChiTietGiangDay ctgd = new ChiTietGiangDay(maHP,tenHP,soTinChi);
                listctgd.add(ctgd);
            }
            
            BUS.closeConnection(conn);
            
        }catch (SQLException e){
            e.printStackTrace(); 
        }
    return listctgd;   
}
    
    public static ArrayList<ChiTietGiangDay> selectByCondition(String DuLieu,String dieuKien) {
        ArrayList<ChiTietGiangDay> listctgd =new ArrayList<ChiTietGiangDay>();
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            
            String sql="SELECT * FROM ChiTietGiangDay WHERE "+DuLieu+" LIKE N'%"+dieuKien+"%' COLLATE Vietnamese_CI_AI";
            
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                String maHP = rs.getString("maHocPhan");
                String maGV = rs.getString("maGiangVien");
                ChiTietGiangDay ctgd= new ChiTietGiangDay(maHP,maGV);
                listctgd.add(ctgd);
            }
            
                BUS.closeConnection(conn);

                }catch (SQLException e){
                    e.printStackTrace(); 
                }
            return listctgd;   
        }
    
    public static ArrayList<ChiTietGiangDay> selectBySpecialCondition(ChiTietGiangDay c) {
        ArrayList<ChiTietGiangDay> listctgd =new ArrayList<ChiTietGiangDay>();
        try{
            //Buoc 1:Ket noi
            Connection conn= BUS.getConnection();

            //Buoc 2:
            Statement st=conn.createStatement();
            
            //Buoc 3:
            String maHP=c.getMaHP();
            String maGV=c.getMaGV();
            
            boolean flag=false;
            
            String sql="SELECT * FROM SinhVien WHERE 1=1 ";
            if(!maHP.isEmpty()){
                sql +="AND maHocPhan LIKE '%"+maHP+"%'";
                flag=true;
            }
            if(!maGV.isEmpty()){
                sql +="AND maGiangVien LIKE N'%"+maGV+"%'";
                flag=true;
            }
            
            if (flag)   sql +="COLLATE Vietnamese_CI_AI";
            
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                String maHP1 = rs.getString("maHocPhan");
                String maGV1 = rs.getString("maGiangVien");
                ChiTietGiangDay ctgd = new ChiTietGiangDay(maHP1,maGV1);
                listctgd.add(ctgd);
            }
            
            BUS.closeConnection(conn);

                }catch (SQLException e){
                    e.printStackTrace(); 
                }
            return listctgd;   

        }
}