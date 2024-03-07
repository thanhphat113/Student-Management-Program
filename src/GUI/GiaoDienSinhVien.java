package GUI;

import DAO.ChiTietDKMHDAO;
import DAO.HocPhanDAO;
import DAO.SinhVienDAO;
import DTO.ChiTietDKMH;
import DTO.HocPhan;
import DTO.SinhVien;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class GiaoDienSinhVien extends JFrame {

    private String userID;
    private String password;

    public GiaoDienSinhVien() {

        unitGUI();
    }

    public GiaoDienSinhVien(String a, String b) {
        this.userID = a;
        this.password = b;
        unitGUI();
    }

    public void unitGUI() {
        
        SinhVien sv= new SinhVien();
        sv=SinhVienDAO.selectById(userID);
        
        setSize(1150, 700);
        setLocationRelativeTo(null);
        setTitle("Ứng Dụng Quản Lý Sinh Viên");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setVisible(true);
        setIconImage(new ImageIcon(GiaoDienDNSinhVien.getPathIcon() + "title.png").getImage());

        Font font = new Font("Arial", Font.BOLD, 25);
        JLabel tieuDe = new JLabel("Màn Hình Sinh Viên");
        tieuDe.setFont(font);
        JLabel Xinchao = new JLabel("  Xin Chào, " +sv.getTenSinhVien()+ " (" + userID + ")");
        JLabel DangXuat = new JLabel("<html><u>Đăng Xuất</u></html>");
        DangXuat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new GiaoDienDNSinhVien();
                dispose();
            }
        });
        DangXuat.setAlignmentY(Component.RIGHT_ALIGNMENT);
        JPanel GioiThieu = new JPanel(new GridLayout(1, 2, 10, 0));
        GioiThieu.add(Xinchao);
        GioiThieu.add(DangXuat);
        GioiThieu.setBackground(new Color(213, 232, 212));
        tieuDe.setAlignmentX(Component.CENTER_ALIGNMENT);
        tieuDe.setAlignmentY(Component.CENTER_ALIGNMENT);
        GioiThieu.setAlignmentX(Component.CENTER_ALIGNMENT);
        GioiThieu.setAlignmentY(Component.CENTER_ALIGNMENT);
        JPanel titlePn = new JPanel();
        titlePn.setLayout(new BoxLayout(titlePn, BoxLayout.Y_AXIS));
        titlePn.add(Box.createVerticalGlue());
        titlePn.add(tieuDe);
        titlePn.add(GioiThieu);
        titlePn.add(Box.createVerticalGlue());
        titlePn.setPreferredSize(new Dimension(0, 60));
        titlePn.setBackground(new Color(213, 232, 212));
        titlePn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        //Trang Chủ
        JPanel trangChu = new JPanel(new BorderLayout());
        JPanel noiDung = new JPanel(new BorderLayout());
        JTextArea NoiDung = new JTextArea();
        NoiDung.setEditable(false);
        try {
            // Mở file và đọc dữ liệu
            FileReader fileReader = new FileReader("./NoiDung.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                // Ghi dữ liệu vào JTextArea
                NoiDung.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        NoiDung.add(noiDung, BorderLayout.CENTER);
        trangChu.add(NoiDung, BorderLayout.CENTER);

        // Xem Điểm-center
        JPanel xemDiem = new JPanel(new BorderLayout());
        String columnXemDiem[] = {"Mã Học Phần", "Tên Học Phần", "Số Tín Chỉ", "Điểm Giữa Kỳ", "%GK", "Điểm Cuối Kì", "%CK", "Điểm Tổng Kết", "Kết Quả"};
        DefaultTableModel modelXemDiem = new DefaultTableModel(columnXemDiem, 0);
        JTable bangDiem = new JTable(modelXemDiem);
        bangDiem.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPaneDiem = new JScrollPane(bangDiem);
        xemDiem.add(scrollPaneDiem, BorderLayout.CENTER);

        // Xem Điểm-bot
        JLabel diemtb = new JLabel("Điểm Trung Bình Hệ 10: ");
        JLabel diemtb4 = new JLabel("Điểm Trung Bình Hệ 4: ");
        JLabel tinChi = new JLabel("Số Tín Chỉ Tích Luỹ: ");
        JPanel TongKet = new JPanel();
        BoxLayout layout = new BoxLayout(TongKet, BoxLayout.Y_AXIS);
        TongKet.setLayout(layout);
        TongKet.add(diemtb);
        TongKet.add(diemtb4);
        TongKet.add(tinChi);
        xemDiem.add(TongKet, BorderLayout.SOUTH);

        //DKMH
        JPanel DKMH = new JPanel(new BorderLayout());
        JPanel Loc = new JPanel();
        Loc.setLayout(new BoxLayout(Loc, BoxLayout.X_AXIS));
        JLabel lb1 = new JLabel("Mã Học Phần: ");
        JTextField tf1 = new JTextField();
        JButton bt1 = new JButton("Lọc Học Phần");
        Loc.add(lb1);
        Loc.add(tf1);
        Loc.add(bt1);
        JPanel chonLoc = new JPanel(new GridLayout(1, 2));
        chonLoc.add(Loc);
        chonLoc.add(new JPanel());
        String columnDKMH[] = {"Mã Học Phần", "Tên môn học", "Số Tín Chỉ", "Tên Giảng Viên", "Tên Phòng","Thứ", "Tiết bắt đầu", "Ngày bắt đầu", "Ngày Kết Thúc"};
        DefaultTableModel modelDKMH = new DefaultTableModel(columnDKMH, 0);
        ArrayList<HocPhan> hpList = HocPhanDAO.selectAll();
        for (HocPhan gv : hpList) {
            Object[] duLieu = {gv.getMaHP(), gv.getTenMH(), gv.getTinChi(), gv.getTenGiangVien(), gv.getTenPhong(),gv.getThu(), gv.getTietBatDau(), gv.getTimeBatDau(), gv.getTimeKetThuc()};
            modelDKMH.addRow(duLieu);
        }
        
        JTable bangDKMH = new JTable(modelDKMH);
        bangDKMH.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPaneDKMH = new JScrollPane(bangDKMH);


        TableColumnModel columnModelmh = bangDKMH.getColumnModel();

        //Kich Thước Cột
        columnModelmh.getColumn(0).setPreferredWidth(30);
        columnModelmh.getColumn(1).setPreferredWidth(130);
        columnModelmh.getColumn(2).setPreferredWidth(10);
        columnModelmh.getColumn(3).setPreferredWidth(130);
        columnModelmh.getColumn(4).setPreferredWidth(130);
        
        String columnxnDKMH[] = {"Mã Học Phần", "Tên môn học", "Số Tín Chỉ", "Tên Giảng Viên", "Tên Phòng","Thứ", "Tiết bắt đầu", "Ngày bắt đầu", "Ngày Kết Thúc","Học Phí"};
        DefaultTableModel modelxnDKMH = new DefaultTableModel(columnxnDKMH, 0);
        ArrayList<ChiTietDKMH> List = ChiTietDKMHDAO.selectAll(userID);
        for (ChiTietDKMH gv : List) {
            Object[] duLieu = {gv.getMaLopHocPhan(), gv.getTenMH(), gv.getTinChi(), gv.getTenGiangVien(), gv.getTenPhong(),gv.getThu(), gv.getTietBD(), gv.getTimeBD(), gv.getTimeKT(),gv.getHocPhi()};
            modelxnDKMH.addRow(duLieu);
        }
        JTable bangXnDKMH = new JTable(modelxnDKMH);
        JScrollPane scrollPaneXnDKMH = new JScrollPane(bangXnDKMH);

        JLabel tieuDeXn = new JLabel("Danh Sách Môn Đã Đăng Ký");
        tieuDeXn.setAlignmentX(Component.CENTER_ALIGNMENT);
        tieuDeXn.setAlignmentY(Component.CENTER_ALIGNMENT);

        JPanel titlePnXn = new JPanel();
        titlePnXn.setLayout(new BoxLayout(titlePnXn, BoxLayout.Y_AXIS));
        titlePnXn.add(Box.createVerticalGlue());
        titlePnXn.add(tieuDeXn);
        titlePnXn.add(Box.createVerticalGlue());
        titlePnXn.setPreferredSize(new Dimension(0, 20));

        JPanel pnBtXn = new JPanel(new FlowLayout());
        JButton themXN = new JButton("Xác Nhận");
        themXN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = bangDKMH.getSelectedRow();
                if (selectedRow!=-1){ 
                    String value =(String) bangDKMH.getValueAt(selectedRow, 0);
                    ChiTietDKMH dkmh= new ChiTietDKMH(value,userID);
                    if(ChiTietDKMHDAO.insert(dkmh)){
                        JOptionPane.showMessageDialog(null, "Đăng Ký Thành Công");
                        DefaultTableModel model = (DefaultTableModel) bangXnDKMH.getModel();
                        model.setRowCount(0);
                        ArrayList<ChiTietDKMH> hpList = ChiTietDKMHDAO.selectAll(dkmh.getMaSinhVien());
                        for (ChiTietDKMH gv : hpList) {
                            Object[] duLieu = {gv.getMaLopHocPhan(), gv.getTenMH(), gv.getTinChi(), gv.getTenGiangVien(), gv.getTenPhong(),gv.getThu(), gv.getTietBD(), gv.getTimeBD(), gv.getTimeKT(),gv.getHocPhi()};
                            modelxnDKMH.addRow(duLieu);
                        }
                    }else JOptionPane.showMessageDialog(null, "Đăng Ký Không Thành Công");
                }else JOptionPane.showMessageDialog(null, "hãy Chọn Đối Tượng Để Thực Hiện Tác Vụ");
            }
        });
        
        JButton xoaXN=new JButton("Xóa");
        xoaXN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableModel model = bangXnDKMH.getModel();
                int selectedRow = bangXnDKMH.getSelectedRow();
                Object value = model.getValueAt(selectedRow, 0); // Lấy giá trị của ô đầu tiên trong dòng đã chọn
                // Lưu giá trị vào biến
                String MaHPXoa = value.toString();

                if (ChiTietDKMHDAO.delete(MaHPXoa)) {
                    JOptionPane.showMessageDialog(new JFrame(), "Đã Xóa Thành Công");
                    if (selectedRow != -1) {
                        modelxnDKMH.removeRow(selectedRow);
                    }
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Không Tìm Thấy Sinh Viên Cần Xóa");
                }
            }
        });
        
        pnBtXn.add(themXN);
        pnBtXn.add(xoaXN);

        JPanel khungXn = new JPanel(new BorderLayout());
        khungXn.add(pnBtXn, BorderLayout.SOUTH);
        khungXn.add(scrollPaneXnDKMH, BorderLayout.CENTER);
        khungXn.add(tieuDeXn, BorderLayout.NORTH);
        khungXn.setPreferredSize(new Dimension(0, 250));

        DKMH.add(khungXn, BorderLayout.SOUTH);
        DKMH.add(scrollPaneDKMH, BorderLayout.CENTER);
        DKMH.add(chonLoc, BorderLayout.NORTH);

        

        //Thông Tin Cá Nhân
        
        JPanel TTCN = new JPanel(new BorderLayout());
        //Top
        JLabel LabelTTCN = new JLabel("Thông Tin Cá Nhân");
        JPanel titleTTCN = new JPanel();
        LabelTTCN.setFont(font);
        LabelTTCN.setAlignmentX(Component.CENTER_ALIGNMENT);
        LabelTTCN.setAlignmentY(Component.CENTER_ALIGNMENT);
        titleTTCN.setLayout(new BoxLayout(titleTTCN, BoxLayout.Y_AXIS));
        titleTTCN.add(Box.createVerticalGlue());
        titleTTCN.add(LabelTTCN);
        titleTTCN.add(Box.createVerticalGlue());
        titleTTCN.setPreferredSize(new Dimension(0, 50));
        TTCN.add(titleTTCN, BorderLayout.NORTH);

        //CENTER
        JPanel pnTTCN = new JPanel();
        pnTTCN.setLayout(new BoxLayout(pnTTCN, BoxLayout.Y_AXIS));
        JPanel pnTTCN1 = new JPanel(new GridLayout(9, 1));

        //CENTER-MaSV
        JPanel MaSV = new JPanel(new FlowLayout());
        JLabel maSv = new JLabel("Mã Sinh Viên");
        maSv.setPreferredSize(new Dimension(100, 30));
        JLabel tfTTCN1 = new JLabel();
        tfTTCN1.setPreferredSize(new Dimension(200, 30));
        tfTTCN1.setText(userID);
        MaSV.add(maSv);
        MaSV.add(tfTTCN1);
        pnTTCN1.add(MaSV);

        //CENTER-TenSV
        JPanel TenSV = new JPanel(new FlowLayout());
        JLabel TenSv = new JLabel("Họ Và Tên");
        TenSv.setPreferredSize(new Dimension(100, 30));
        TenSv.setSize(100, 100);
        JTextField tfTTCN2 = new JTextField();
        tfTTCN2.setEditable(false);
        tfTTCN2.setText(sv.getTenSinhVien());
        tfTTCN2.setPreferredSize(new Dimension(200, 30));
        TenSV.add(TenSv);
        TenSV.add(tfTTCN2);
        pnTTCN1.add(TenSV);

        //CENTER-Nam Sinh
        JPanel NamSinh = new JPanel(new FlowLayout());
        JLabel namSinh = new JLabel("Năm Sinh");
        namSinh.setPreferredSize(new Dimension(100, 30));
        namSinh.setSize(100, 100);
        JTextField tfTTCN3 = new JTextField();
        tfTTCN3.setEditable(false);
        tfTTCN3.setText(sv.getNamSinh());
        tfTTCN3.setPreferredSize(new Dimension(200, 30));
        NamSinh.add(namSinh);
        NamSinh.add(tfTTCN3);
        pnTTCN1.add(NamSinh);

        //CENTER-Giới Tính
         JPanel GioiTinh = new JPanel(new FlowLayout());
        JLabel gioitinh = new JLabel("Giới Tính");
        String[] luaChonTTCN = {"Nam", "Nữ"};
        JComboBox<String> LuaChonTTCN = new JComboBox<>(luaChonTTCN);
        gioitinh.setPreferredSize(new Dimension(100, 30));
        LuaChonTTCN.setPreferredSize(new Dimension(200, 30));
        LuaChonTTCN.setEnabled(false);
        GioiTinh.add(gioitinh);
        GioiTinh.add(LuaChonTTCN);
        pnTTCN1.add(GioiTinh);

        //CENTER-Dân Tộc
        JPanel DanToc = new JPanel(new FlowLayout());
        JLabel dantoc = new JLabel("Dân Tộc");
        dantoc.setPreferredSize(new Dimension(100, 30));
        JTextField tfTTCN5 = new JTextField();
        tfTTCN5.setText(sv.getDanToc());
        tfTTCN5.setEditable(false);
        tfTTCN5.setPreferredSize(new Dimension(200, 30));
        DanToc.add(dantoc);
        DanToc.add(tfTTCN5);
        pnTTCN1.add(DanToc);

        //CENTER-CCCD
        JPanel CCCD = new JPanel(new FlowLayout());
        JLabel cccd = new JLabel("CCCD/CMND");
        cccd.setPreferredSize(new Dimension(100, 30));
        JTextField tfTTCN6 = new JTextField();
        tfTTCN6.setText(sv.getCCCD());
        tfTTCN6.setEditable(false);
        tfTTCN6.setPreferredSize(new Dimension(200, 30));
        CCCD.add(cccd);
        CCCD.add(tfTTCN6);
        pnTTCN1.add(CCCD);
        

        //CENTER-Sđt
        JPanel SDT = new JPanel(new FlowLayout());
        JLabel sdt = new JLabel("Số Điện Thoại");
        sdt.setPreferredSize(new Dimension(100, 30));
        JTextField tfTTCN7 = new JTextField();
        tfTTCN7.setText(sv.getSoDienThoai());
        tfTTCN7.setEditable(false);
        tfTTCN7.setPreferredSize(new Dimension(200, 30));
        SDT.add(sdt);
        SDT.add(tfTTCN7);
        pnTTCN1.add(SDT);
        
        //Mã Khoa
        JPanel KHOA = new JPanel(new FlowLayout());
        JLabel khoa = new JLabel("Mã Khoa");
        khoa.setPreferredSize(new Dimension(100, 30));
        JTextField tfTTCN8 = new JTextField();
        tfTTCN8.setEditable(false);
        tfTTCN8.setText(sv.getMaKhoa());
        tfTTCN8.setPreferredSize(new Dimension(200, 30));
        KHOA.add(khoa);
        KHOA.add(tfTTCN8);
        pnTTCN1.add(KHOA);
        
        //Mã Lớp
        JPanel LOP = new JPanel(new FlowLayout());
        JLabel lop = new JLabel("Mã Lớp");
        lop.setPreferredSize(new Dimension(100, 30));
        JTextField tfTTCN9 = new JTextField();
        tfTTCN9.setEditable(false);
        tfTTCN9.setText(sv.getMaLop());
        tfTTCN9.setPreferredSize(new Dimension(200, 30));
        LOP.add(lop);
        LOP.add(tfTTCN9);
        pnTTCN1.add(LOP);

//        pnTTCN.add(Box.createVerticalGlue());
        pnTTCN.add(pnTTCN1);
//        pnTTCN.add(Box.createVerticalGlue());
        TTCN.add(pnTTCN, BorderLayout.CENTER);

        JPanel pnlChinhSua = new JPanel(new FlowLayout());
        JButton btSua = new JButton("Xác Nhận Chỉnh Sửa", new ImageIcon(GiaoDienDNSinhVien.getPathIcon() + "set_1.png"));
        btSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame btSua=new JFrame ("Sửa Đổi Thông Tin");
                btSua.setSize(500,500);
                btSua.setVisible(true);
                btSua.setLocationRelativeTo(null);
                btSua.setLayout(new BorderLayout());
                
                
                SinhVien sv= new SinhVien();
                sv=SinhVienDAO.selectById(userID);
                JPanel pnTTCN= new JPanel();
                pnTTCN.setLayout(new BoxLayout(pnTTCN, BoxLayout.Y_AXIS));
                JPanel pnTTCN1 = new JPanel(new GridLayout(9, 1));

                //CENTER-MaSV
                JPanel MaSV = new JPanel(new FlowLayout());
                JLabel maSv = new JLabel("Mã Sinh Viên");
                maSv.setPreferredSize(new Dimension(100, 30));
                JLabel tfTTCN1 = new JLabel();
                tfTTCN1.setPreferredSize(new Dimension(200, 30));
                tfTTCN1.setText(sv.getMaSinhVien());
                MaSV.add(maSv);
                MaSV.add(tfTTCN1);
                pnTTCN1.add(MaSV);

                //CENTER-TenSV
                JPanel TenSV = new JPanel(new FlowLayout());
                JLabel TenSv = new JLabel("Họ Và Tên");
                TenSv.setPreferredSize(new Dimension(100, 30));
                TenSv.setSize(100, 100);
                JTextField tfTTCN2 = new JTextField();
                tfTTCN2.setText(sv.getTenSinhVien());
                tfTTCN2.setPreferredSize(new Dimension(200, 30));
                TenSV.add(TenSv);
                TenSV.add(tfTTCN2);
                pnTTCN1.add(TenSV);

                //CENTER-Nam Sinh
                JPanel NamSinh = new JPanel(new FlowLayout());
                JLabel namSinh = new JLabel("Năm Sinh");
                namSinh.setPreferredSize(new Dimension(100, 30));
                namSinh.setSize(100, 100);
                JTextField tfTTCN3 = new JTextField();
                tfTTCN3.setText(sv.getNamSinh());
                tfTTCN3.setPreferredSize(new Dimension(200, 30));
                NamSinh.add(namSinh);
                NamSinh.add(tfTTCN3);
                pnTTCN1.add(NamSinh);

                //CENTER-Giới Tính
                 JPanel GioiTinh = new JPanel(new FlowLayout());
                JLabel gioitinh = new JLabel("Giới Tính");
                String[] luaChonTTCN = {"Nam", "Nữ"};
                JComboBox<String> LuaChonTTCN = new JComboBox<>(luaChonTTCN);
                if(sv.getGioiTinh().equalsIgnoreCase("Nam")){
                    LuaChonTTCN.setSelectedIndex(0);
                }
                else LuaChonTTCN.setSelectedIndex(1);
                gioitinh.setPreferredSize(new Dimension(100, 30));
                LuaChonTTCN.setPreferredSize(new Dimension(200, 30));
                GioiTinh.add(gioitinh);
                GioiTinh.add(LuaChonTTCN);
                pnTTCN1.add(GioiTinh);

                //CENTER-Dân Tộc
                JPanel DanToc = new JPanel(new FlowLayout());
                JLabel dantoc = new JLabel("Dân Tộc");
                dantoc.setPreferredSize(new Dimension(100, 30));
                JTextField tfTTCN5 = new JTextField();
                tfTTCN5.setText(sv.getDanToc());
                tfTTCN5.setPreferredSize(new Dimension(200, 30));
                DanToc.add(dantoc);
                DanToc.add(tfTTCN5);
                pnTTCN1.add(DanToc);

                //CENTER-CCCD
                JPanel CCCD = new JPanel(new FlowLayout());
                JLabel cccd = new JLabel("CCCD/CMND");
                cccd.setPreferredSize(new Dimension(100, 30));
                JTextField tfTTCN6 = new JTextField();
                tfTTCN6.setText(sv.getCCCD());
                tfTTCN6.setPreferredSize(new Dimension(200, 30));
                CCCD.add(cccd);
                CCCD.add(tfTTCN6);
                pnTTCN1.add(CCCD);

                //CENTER-Sđt
                JPanel SDT = new JPanel(new FlowLayout());
                JLabel sdt = new JLabel("Số Điện Thoại");
                sdt.setPreferredSize(new Dimension(100, 30));
                JTextField tfTTCN7 = new JTextField();
                tfTTCN7.setText(sv.getSoDienThoai());
                tfTTCN7.setPreferredSize(new Dimension(200, 30));
                SDT.add(sdt);
                SDT.add(tfTTCN7);
                pnTTCN1.add(SDT);

                //Mã Khoa
                JPanel KHOA = new JPanel(new FlowLayout());
                JLabel khoa = new JLabel("Mã Khoa");
                khoa.setPreferredSize(new Dimension(100, 30));
                JTextField tfTTCN8 = new JTextField();
                tfTTCN8.setText(sv.getMaKhoa());
                tfTTCN8.setPreferredSize(new Dimension(200, 30));
                KHOA.add(khoa);
                KHOA.add(tfTTCN8);
                pnTTCN1.add(KHOA);

                //Mã Lớp
                JPanel LOP = new JPanel(new FlowLayout());
                JLabel lop = new JLabel("Mã Lớp");
                lop.setPreferredSize(new Dimension(100, 30));
                JTextField tfTTCN9 = new JTextField();
                tfTTCN9.setText(sv.getMaLop());
                tfTTCN9.setPreferredSize(new Dimension(200, 30));
                LOP.add(lop);
                LOP.add(tfTTCN9);
                pnTTCN1.add(LOP);

                pnTTCN.add(pnTTCN1);
                btSua.add(pnTTCN,BorderLayout.CENTER);
                
                JPanel btXacNhanTT=new JPanel(new FlowLayout());
                JButton XacNhan=new JButton("Xác Nhận");
                XacNhan.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String gioiTinh = (String) LuaChonTTCN.getSelectedItem();
                        String TenSV=tfTTCN2.getText();
                        String namSinh=tfTTCN3.getText();             
                        String danToc=tfTTCN5.getText();
                        String CCCD=tfTTCN6.getText();
                        String sdt=tfTTCN7.getText();
                        String maKhoa=tfTTCN8.getText();
                        String maLop=tfTTCN9.getText();
                        SinhVien sv=new SinhVien(userID,TenSV,namSinh,gioiTinh,danToc,CCCD,sdt,maKhoa,maLop);
                        if(SinhVienDAO.update(sv)&&TenSV!=null&&namSinh!=null&&danToc!=null&&CCCD!=null&&sdt!=null&&maKhoa!=null&&maLop!=null){
                            JOptionPane.showMessageDialog(new JFrame(), "Sửa Thông Tin Thành Công");
                            tfTTCN1.setText(userID);
                            tfTTCN2.setText(sv.getTenSinhVien());
                            tfTTCN3.setText(sv.getNamSinh());
                            if(gioiTinh.equalsIgnoreCase("Nam")){
                                LuaChonTTCN.setSelectedIndex(0);
                            }
                            else LuaChonTTCN.setSelectedIndex(1);
                            tfTTCN5.setText(sv.getDanToc());
                            tfTTCN6.setText(sv.getCCCD());
                            tfTTCN7.setText(sv.getSoDienThoai());
                            tfTTCN8.setText(sv.getMaKhoa());
                            tfTTCN9.setText(sv.getMaLop());
                            btSua.dispose();
                        }
                        else JOptionPane.showMessageDialog(new JFrame(), "Sửa Thông Tin Không Thành Công");
                    }
                
            });
                JButton Huy=new JButton("Hủy");
                Huy.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            btSua.dispose();
                        } 
            });
                btXacNhanTT.add(XacNhan);
                btXacNhanTT.add(Huy);
                btSua.add(btXacNhanTT,BorderLayout.SOUTH);
            }
    });
            
        JButton btDoiMK = new JButton("Đổi Mật Khẩu");
        btDoiMK.addActionListener(e -> {
            JFrame fDoiMK = new JFrame("Đổi Mật Khẩu");
            fDoiMK.setLayout(new BorderLayout());
            fDoiMK.setSize(450, 150);
            
            JPanel DoiMK = new JPanel(new GridLayout(2, 2));
            DoiMK.add(new JLabel("Mật Khẩu Cũ"));
            JPasswordField DoiMkTF1 = new JPasswordField();
            DoiMK.add(DoiMkTF1);
            DoiMK.add(new JLabel("Mật Khẩu Mới"));
            JPasswordField DoiMkTF2 = new JPasswordField();
            DoiMK.add(DoiMkTF2);

            JPanel titlePnMK2 = new JPanel(new FlowLayout());
            titlePnMK2.setPreferredSize(new Dimension(0, 50));
            JButton btXacNhan = new JButton("Xác Nhận Thay Đổi", new ImageIcon(GiaoDienDNSinhVien.getPathIcon() + "change_1.png"));
            btXacNhan.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    SinhVien sv=new SinhVien();
                    sv=SinhVienDAO.selectById(userID);
                    char[] pass=DoiMkTF1.getPassword();
                    String mkCu=new String(pass);
                    char[] newpass=DoiMkTF2.getPassword();
                    String mkMoi=new String(newpass);
                    if(mkCu.equalsIgnoreCase(sv.getMatKhau())){
                        if(SinhVienDAO.changePass(sv, mkMoi))   JOptionPane.showMessageDialog(new JFrame(), "Đổi Mật Khẩu Thành Công");
                        else JOptionPane.showMessageDialog(new JFrame(), "Đổi Mật Khẩu Không Thành Công");
                    }
                    else JOptionPane.showMessageDialog(new JFrame(), "Mật Khẩu Cũ Không Trùng Khớp");
                }
            });
            titlePnMK2.add(btXacNhan);
            JPanel titlePnMK3 = new JPanel();
            titlePnMK3.setPreferredSize(new Dimension(40, 0));
            JPanel titlePnMK4 = new JPanel();
            titlePnMK4.setPreferredSize(new Dimension(40, 0));
            
            
            

            fDoiMK.add(DoiMK, BorderLayout.CENTER);
            fDoiMK.add(titlePnMK2, BorderLayout.SOUTH);
            fDoiMK.add(titlePnMK3, BorderLayout.WEST);
            fDoiMK.add(titlePnMK4, BorderLayout.EAST);
            fDoiMK.setVisible(true);
            fDoiMK.setLocationRelativeTo(null);
        });

        pnlChinhSua.add(btSua);
        pnlChinhSua.add(btDoiMK);
        pnlChinhSua.setPreferredSize(new Dimension(0, 40));
        TTCN.add(pnlChinhSua, BorderLayout.SOUTH);

        JPanel pnlRong1 = new JPanel();
        pnlRong1.setPreferredSize(new Dimension(180, 0));
        TTCN.add(pnlRong1, BorderLayout.WEST);

        JPanel pnlRong2 = new JPanel();
        pnlRong2.setPreferredSize(new Dimension(180, 0));
        TTCN.add(pnlRong2, BorderLayout.EAST);

        // Thêm Vô tabbedPane
        JTabbedPane thanhChon = new JTabbedPane();
        thanhChon.addTab("Trang Chủ", new ImageIcon(GiaoDienDNSinhVien.getPathIcon() + "home_1.png"), trangChu);
        thanhChon.addTab("Xem Điểm", xemDiem);
        thanhChon.addTab("ĐKMH", DKMH);
        thanhChon.addTab("Thông Tin Cá Nhân", TTCN);

        //Thêm Vào Jframe
        add(titlePn, BorderLayout.NORTH);
        add(thanhChon, BorderLayout.CENTER);
        add(new JPanel(), BorderLayout.WEST);
        add(new JPanel(), BorderLayout.EAST);
        add(new JPanel(), BorderLayout.SOUTH);
    }
    
    public static void main(String[] args){
            new GiaoDienSinhVien("60001","phat7733419");
        }
}