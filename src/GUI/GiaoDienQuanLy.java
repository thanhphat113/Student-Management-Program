package GUI;

import BUS.GiangVienBUS;
import BUS.HocPhanBUS;
import BUS.SinhVienBUS;
import DAO.ChiTietGiangDayDAO;
import DAO.GiangVienDAO;
import DAO.HocPhanDAO;
import DAO.SinhVienDAO;
import DTO.ChiTietGiangDay;
import DTO.GiangVien;
import DTO.HocPhan;
import DTO.SinhVien;
import Export.ExportExcel;
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
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import net.sourceforge.jdatepicker.JDateComponentFactory;
import net.sourceforge.jdatepicker.JDatePicker;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class GiaoDienQuanLy extends JFrame {

    private String userID;
    private String password;
    SinhVienBUS svBUS = new SinhVienBUS();
    GiangVienBUS gvBUS = new GiangVienBUS();
    HocPhanBUS hpBUS = new HocPhanBUS();

    public GiaoDienQuanLy() {
        unitGUI();
    }

    public GiaoDienQuanLy(String a, String b) {
        this.userID = a;
        this.password = b;
        unitGUI();
    }

    public void unitGUI() {
        setSize(1150, 700);
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("Ứng dụng quản lí sinh viên");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(GiaoDienDNSinhVien.getPathIcon() + "title.png").getImage());

        Font font = new Font("Arial", Font.BOLD, 25);
        Font font1 = new Font("Arial", Font.BOLD, 15);

        Border border = BorderFactory.createLineBorder(Color.BLACK);

        JLabel tieuDe = new JLabel("Màn hình Quản Lý");
        tieuDe.setFont(font);

        JLabel xinchao = new JLabel("Xin chào " + "(" + userID + ")");
        JLabel DangXuat = new JLabel("<html><u>Đăng xuất</u></html>");
        DangXuat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new GiaoDienDNSinhVien();
                dispose();
            }
        });
        DangXuat.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JPanel GioiThieu = new JPanel(new GridLayout(1, 2, 10, 0));
        GioiThieu.add(xinchao);
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

        //Trang Chủ==========================================================================================
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

        noiDung.add(NoiDung, BorderLayout.CENTER);
        JButton thongbao = new JButton("Thêm thông báo!", new ImageIcon(GiaoDienDNSinhVien.getPathIcon() + "add_1.png"));
        thongbao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame f1 = new JFrame("Thay Đổi Nội Dung");
                f1.setVisible(true);
                f1.setSize(500, 500);
                f1.setLocationRelativeTo(null);
                f1.setLayout(new BorderLayout());
                f1.setIconImage(new ImageIcon(GiaoDienDNSinhVien.getPathIcon() + "update_1.png").getImage());

                JTextArea text = new JTextArea();
                text.setText(NoiDung.getText());

                JPanel nut = new JPanel(new GridLayout(1, 2));
                JButton btXacNhan = new JButton("Xác Nhận", new ImageIcon(GiaoDienDNSinhVien.getPathIcon() + "yes_1.png"));
                btXacNhan.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        NoiDung.setText(text.getText());
                        try {
                            FileWriter writer = new FileWriter("./NoiDung.txt");
                            writer.write(text.getText());
                            writer.close();
                        } catch (IOException evt) {
                            evt.printStackTrace();
                        }
                    }
                });
                JButton btHuy = new JButton("Hủy", new ImageIcon(GiaoDienDNSinhVien.getPathIcon() + "no_1.png"));
                btHuy.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        f1.dispose();
                    }
                });

                nut.add(btXacNhan);
                nut.add(btHuy);
                f1.add(text, BorderLayout.CENTER);
                f1.add(nut, BorderLayout.SOUTH);

            }
        });

        trangChu.add(noiDung, BorderLayout.CENTER);
        trangChu.add(thongbao, BorderLayout.SOUTH);

        //Danh sách sinh viên=======================================================================================
        JPanel DSSV = new JPanel(new BorderLayout());
        JPanel Loc = new JPanel();
        Loc.setLayout(new BoxLayout(Loc, BoxLayout.X_AXIS));

        String columnDSSV[] = {"Mã SV", "Tên Sinh Viên", "Năm sinh", "Giới tính", "Dân tộc", "CCCD/CMND", "Số điện thoại", "Tên Lớp", "Tên Khoa", "Tên Cố Vấn"};
        DefaultTableModel modelDSSV = new DefaultTableModel(columnDSSV, 0);

        ArrayList<SinhVien> svList = svBUS.getlistSinhVien();
        for (SinhVien sv : svList) {
            Object[] duLieu = {sv.getMaSinhVien(), sv.getTenSinhVien(), sv.getNamSinh(), sv.getGioiTinh(), sv.getDanToc(), sv.getCCCD(), sv.getSoDienThoai(), sv.getTenLop(), sv.getTenKhoa(), sv.getTenCoVan()};
            modelDSSV.addRow(duLieu);
        }
        JTable bangDSSV = new JTable(modelDSSV);
        bangDSSV.getTableHeader().setResizingAllowed(false);
        TableColumnModel columnModel = bangDSSV.getColumnModel();

        //Kich Thước Cột
        columnModel.getColumn(0).setPreferredWidth(30);
        columnModel.getColumn(1).setPreferredWidth(130);
        columnModel.getColumn(3).setPreferredWidth(10);
        columnModel.getColumn(4).setPreferredWidth(30);
        columnModel.getColumn(5).setPreferredWidth(80);
        columnModel.getColumn(7).setPreferredWidth(20);
        columnModel.getColumn(9).setPreferredWidth(130);

        bangDSSV.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPaneDSSV = new JScrollPane(bangDSSV);

        JTextField jcb1 = new JTextField();
        jcb1.setEnabled(false);
        String[] TIMKIEM = {"Tất Cả", "Mã Sinh Viên", "Tên Sinh Viên", "Năm Sinh", "Giới Tính", "Dân Tộc", "CCCD", "Số Điện Thoại", "Tên Lớp", "Tên Khoa", "Tên Cố Vấn"};
        JComboBox<String> DULIEU = new JComboBox<>(TIMKIEM);
        DULIEU.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (DULIEU.getSelectedIndex() == 0) {
                    // Nếu chọn phần tử đầu tiên thì không cho phép chỉnh sửa trường văn bản
                    jcb1.setEnabled(false);
                } else {
                    // Nếu chọn bất kỳ phần tử nào khác thì cho phép chỉnh sửa trường văn bản
                    jcb1.setEnabled(true);
                }
            }
        });

        JButton bt1 = new JButton("Lọc >>");
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag = false;
                String luaChonTimKiem = (String) DULIEU.getSelectedItem();
                String noiDung = new String();
                if (luaChonTimKiem.equalsIgnoreCase("Tất Cả")) {
                    DefaultTableModel model = (DefaultTableModel) bangDSSV.getModel();
                    model.setRowCount(0);
                    ArrayList<SinhVien> svList = svBUS.getlistSinhVien();
                    for (SinhVien sv : svList) {
                        Object[] duLieu = {sv.getMaSinhVien(), sv.getTenSinhVien(), sv.getNamSinh(), sv.getGioiTinh(), sv.getDanToc(), sv.getCCCD(), sv.getSoDienThoai(), sv.getTenLop(), sv.getTenKhoa(), sv.getTenCoVan()};
                        modelDSSV.addRow(duLieu);
                        flag = true;
                    }
                } else if (luaChonTimKiem.equalsIgnoreCase("Tên Sinh Viên")) {
                    noiDung = "sv.tenSinhVien";
                } else if (luaChonTimKiem.equalsIgnoreCase("Mã Sinh Viên")) {
                    noiDung = "sv.maSinhvien";
                } else if (luaChonTimKiem.equalsIgnoreCase("Dân Tộc")) {
                    noiDung = "sv.danToc";
                } else if (luaChonTimKiem.equalsIgnoreCase("Giới Tính")) {
                    noiDung = "sv.gioiTinh";
                } else if (luaChonTimKiem.equalsIgnoreCase("Năm Sinh")) {
                    noiDung = "sv.namSinh";
                } else if (luaChonTimKiem.equalsIgnoreCase("CCCD")) {
                    noiDung = "sv.CCCD";
                } else if (luaChonTimKiem.equalsIgnoreCase("Số Điện Thoại")) {
                    noiDung = "sv.soDienThoai";
                } else if (luaChonTimKiem.equalsIgnoreCase("Tên Khoa")) {
                    noiDung = "k.tenKhoa";
                } else if (luaChonTimKiem.equalsIgnoreCase("Tên Lớp")) {
                    noiDung = "l.tenLop";
                } else {
                    noiDung = "gv.tenGiangVien";
                }
                if (!flag) {
                    ArrayList<SinhVien> list = svBUS.getlistSinhVienDK(noiDung, jcb1.getText());
                    DefaultTableModel model = (DefaultTableModel) bangDSSV.getModel();
                    model.setRowCount(0);
                    for (SinhVien sv : list) {
                        Object[] duLieuTimKiem = {sv.getMaSinhVien(), sv.getTenSinhVien(), sv.getNamSinh(), sv.getGioiTinh(), sv.getDanToc(), sv.getCCCD(), sv.getSoDienThoai(), sv.getTenLop(), sv.getTenKhoa(), sv.getTenCoVan()};
                        modelDSSV.addRow(duLieuTimKiem);
                        jcb1.setText("");
                    }
                }
            }
        });
        Loc.add(DULIEU);
        Loc.add(jcb1);
        Loc.add(bt1);
        JPanel chonLoc = new JPanel(new GridLayout(1, 2));
        chonLoc.add(Loc);
        chonLoc.add(new JPanel());

        JPanel pn1 = new JPanel();

        //Năm Sinh
        UtilDateModel dateModel = new UtilDateModel();
        dateModel.setDate(2000, 0, 1); // Đặt giá trị ban đầu cho model
        dateModel.setSelected(true); // Chọn giá trị ban đầu

        // Tạo DatePicker
        JDatePicker namsinh = new JDateComponentFactory().createJDatePicker(dateModel);
        namsinh.setTextEditable(false); // Cho phép nhập ngày tháng năm bằng tay
        namsinh.setShowYearButtons(true); // Hiển thị nút điều khiển chọn năm
        namsinh.setButtonFocusable(true); // Cho phép focus vào nút điều khiển
        JPanel ngaysinh = new JPanel(new BorderLayout());
        ngaysinh.add((Component) namsinh, BorderLayout.CENTER);
        ngaysinh.setPreferredSize(new Dimension(200, 30));

        JTextField msv = new JTextField();
        msv.setPreferredSize(new Dimension(130, 30));
        JLabel lbm = new JLabel("Mã sv: ");
        JTextField tensv = new JTextField();
        tensv.setPreferredSize(new Dimension(200, 30));
        JLabel lbt = new JLabel("Tên: ");
        JTextField lop = new JTextField();
        lop.setPreferredSize(new Dimension(70, 30));
        JLabel lbl = new JLabel("Mã Lớp");
        JLabel lbk = new JLabel("Mã Khoa");
        JTextField khoa = new JTextField();
        khoa.setPreferredSize(new Dimension(70, 30));
        JLabel lbn = new JLabel("Năm sinh");
        String[] genders = {"Nam", "Nữ"};
        JComboBox<String> gioitinh = new JComboBox<>(genders);
        JLabel lbg = new JLabel("Giới tính: ");
        JTextField dantoc = new JTextField();
        dantoc.setPreferredSize(new Dimension(60, 30));
        JLabel lbd = new JLabel("Dân tộc");
        JTextField cccd = new JTextField();
        cccd.setPreferredSize(new Dimension(180, 30));
        JLabel lbc = new JLabel("CCCĐ/CMND");
        JTextField sdt = new JTextField();
        sdt.setPreferredSize(new Dimension(130, 30));
        JLabel lbs = new JLabel("Số ĐT:");
        pn1.setLayout(new FlowLayout());
        pn1.add(lbm);
        pn1.add(msv);
        pn1.add(lbt);
        pn1.add(tensv);
        pn1.add(lbg);
        pn1.add(gioitinh);
        pn1.add(lbl);
        pn1.add(lop);
        pn1.add(lbk);
        pn1.add(khoa);
        pn1.setPreferredSize(new Dimension(0, 50));

        JPanel pn2 = new JPanel();
        pn2.setLayout(new FlowLayout());
        pn2.add(lbn);
        pn2.add(ngaysinh);
        pn2.add(lbd);
        pn2.add(dantoc);
        pn2.add(lbc);
        pn2.add(cccd);
        pn2.add(lbs);
        pn2.add(sdt);

        JPanel pn3 = new JPanel();

        JButton them = new JButton("Thêm");
        them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String MaSV = msv.getText();
                String TenSV = tensv.getText();
                String Lop = lop.getText();

                Date dateOfBirth = (Date) namsinh.getModel().getValue();
                // Định dạng lại định dạng ngày tháng năm
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = dateFormat.format(dateOfBirth);

                String GioiTinh = (String) gioitinh.getSelectedItem();
                String CCCD = cccd.getText();
                String Sdt = sdt.getText();
                String DanToc = dantoc.getText();
                String maKhoa = khoa.getText();
                SinhVien sv = new SinhVien(MaSV, TenSV, dateString, GioiTinh, DanToc, CCCD, Sdt, maKhoa, Lop);
                JOptionPane.showMessageDialog(null, svBUS.insert(sv));
                DefaultTableModel model = (DefaultTableModel) bangDSSV.getModel();
                model.setRowCount(0);
                ArrayList<SinhVien> list = svBUS.getlistSinhVien();
                for (SinhVien svThem : list) {
                    Object[] duLieuTimKiem = {svThem.getMaSinhVien(), svThem.getTenSinhVien(), svThem.getNamSinh(), svThem.getGioiTinh(), svThem.getDanToc(), svThem.getCCCD(), svThem.getSoDienThoai(), svThem.getTenLop(), svThem.getTenKhoa(), svThem.getTenCoVan()};
                    modelDSSV.addRow(duLieuTimKiem);
                }

                msv.setText("");
                tensv.setText("");
                dantoc.setText("");
                lop.setText("");
                gioitinh.setSelectedIndex(0);
                cccd.setText("");
                sdt.setText("");
                dantoc.setText("");
                khoa.setText("");

            }
        });

        JButton sua = new JButton("Sửa");
        sua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = bangDSSV.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một dòng để thực hiện tác vụ");
                    return;
                } else {
                    JFrame btSua = new JFrame("Sửa Đổi Thông Tin");
                    btSua.setSize(500, 500);
                    btSua.setVisible(true);
                    btSua.setLocationRelativeTo(null);
                    btSua.setLayout(new BorderLayout());

                    TableModel model = bangDSSV.getModel();
                    Object value = new Object();

                    value = model.getValueAt(selectedRow, 0);
                    String SVSua = value.toString();

                    SinhVien sv = new SinhVien();
                    sv = SinhVienDAO.selectById(SVSua);
                    JPanel pnTTCN = new JPanel();
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
                    if (sv.getGioiTinh().equalsIgnoreCase("Nam")) {
                        LuaChonTTCN.setSelectedIndex(0);
                    } else {
                        LuaChonTTCN.setSelectedIndex(1);
                    }
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
                    btSua.add(pnTTCN, BorderLayout.CENTER);

                    JPanel btXacNhan = new JPanel(new FlowLayout());
                    JButton XacNhan = new JButton("Xác Nhận");
                    XacNhan.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String gioiTinh = (String) LuaChonTTCN.getSelectedItem();
                            String TenSV = tfTTCN2.getText();
                            String namSinh = tfTTCN3.getText();
                            String danToc = tfTTCN5.getText();
                            String CCCD = tfTTCN6.getText();
                            String sdt = tfTTCN7.getText();
                            String maKhoa = tfTTCN8.getText();
                            String maLop = tfTTCN9.getText();
                            SinhVien sv = new SinhVien(SVSua, TenSV, namSinh, gioiTinh, danToc, CCCD, sdt, maKhoa, maLop);
                            if (SinhVienDAO.update(sv) && TenSV != null && namSinh != null && danToc != null && CCCD != null && sdt != null && maKhoa != null && maLop != null) {
                                JOptionPane.showMessageDialog(new JFrame(), "Sửa Thông Tin Thành Công");
                                DefaultTableModel model = (DefaultTableModel) bangDSSV.getModel();
                                model.setRowCount(0);
                                ArrayList<SinhVien> list = svBUS.getlistSinhVien();
                                for (SinhVien svup : list) {
                                    Object[] duLieuTimKiem = {svup.getMaSinhVien(), svup.getTenSinhVien(), svup.getNamSinh(), svup.getGioiTinh(), svup.getDanToc(), svup.getCCCD(), svup.getSoDienThoai(), svup.getTenLop(), svup.getTenKhoa(), svup.getTenCoVan()};
                                    modelDSSV.addRow(duLieuTimKiem);
                                }
                                btSua.dispose();
                            } else {
                                JOptionPane.showMessageDialog(new JFrame(), "Sửa Thông Tin Không Thành Công");
                            }
                        }
                    });
                    JButton Huy = new JButton("Hủy");
                    Huy.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            btSua.dispose();
                        }
                    });
                    btXacNhan.add(XacNhan);
                    btXacNhan.add(Huy);
                    btSua.add(btXacNhan, BorderLayout.SOUTH);

                }
            }
        });

        JButton xoa = new JButton("Xóa");
        xoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableModel model = bangDSSV.getModel();
                int selectedRow = bangDSSV.getSelectedRow();
                Object value = model.getValueAt(selectedRow, 0); // Lấy giá trị của ô đầu tiên trong dòng đã chọn
                // Lưu giá trị vào biến

                String MaSVXoa = value.toString();
                SinhVien sv = new SinhVien();
                sv = SinhVienDAO.selectById(MaSVXoa);
                JOptionPane.showMessageDialog(null, svBUS.delete(sv));
                DefaultTableModel model1 = (DefaultTableModel) bangDSSV.getModel();
                model1.setRowCount(0);
                ArrayList<SinhVien> list = svBUS.getlistSinhVien();
                for (SinhVien svup : list) {
                    Object[] duLieuTimKiem = {svup.getMaSinhVien(), svup.getTenSinhVien(), svup.getNamSinh(), svup.getGioiTinh(), svup.getDanToc(), svup.getCCCD(), svup.getSoDienThoai(), svup.getTenLop(), svup.getTenKhoa(), svup.getTenCoVan()};
                    modelDSSV.addRow(duLieuTimKiem);
                }
            }
        });

        JButton timKiem = new JButton("Tìm Kiếm");
        timKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame btTimKiem = new JFrame("Tìm Kiếm Nâng Cao");
                btTimKiem.setSize(400, 700);
                btTimKiem.setVisible(true);
                btTimKiem.setLocationRelativeTo(null);

                JPanel pnTTCN = new JPanel();
                pnTTCN.setLayout(new BoxLayout(pnTTCN, BoxLayout.Y_AXIS));
                JPanel pnTTCN1 = new JPanel(new GridLayout(10, 1));

                //CENTER-MaSV
                JPanel MaSV = new JPanel(new FlowLayout());
                JLabel maSv = new JLabel("Mã Sinh Viên");
                maSv.setPreferredSize(new Dimension(100, 30));
                JTextField tfTTCN1TK = new JTextField();
                tfTTCN1TK.setPreferredSize(new Dimension(200, 30));
                MaSV.add(maSv);
                MaSV.add(tfTTCN1TK);
                pnTTCN1.add(MaSV);

                //CENTER-TenSV
                JPanel TenSV = new JPanel(new FlowLayout());
                JLabel TenSv = new JLabel("Họ Và Tên");
                TenSv.setPreferredSize(new Dimension(100, 30));
                TenSv.setSize(100, 100);
                JTextField tfTTCN2TK = new JTextField();

                tfTTCN2TK.setPreferredSize(new Dimension(200, 30));
                TenSV.add(TenSv);
                TenSV.add(tfTTCN2TK);
                pnTTCN1.add(TenSV);

                //CENTER-Nam Sinh
                JPanel NamSinh = new JPanel(new FlowLayout());
                JLabel namSinh = new JLabel("Năm Sinh");
                namSinh.setPreferredSize(new Dimension(100, 30));
                namSinh.setSize(100, 100);
                JTextField tfTTCN3TK = new JTextField();
                tfTTCN3TK.setPreferredSize(new Dimension(200, 30));
                NamSinh.add(namSinh);
                NamSinh.add(tfTTCN3TK);
                pnTTCN1.add(NamSinh);

                //CENTER-Giới Tính
                JPanel GioiTinh = new JPanel(new FlowLayout());
                JLabel gioitinh = new JLabel("Giới Tính");
                String[] luaChonTTCN = {"", "Nam", "Nữ"};
                JComboBox<String> LuaChonTTCNTK = new JComboBox<>(luaChonTTCN);
                gioitinh.setPreferredSize(new Dimension(100, 30));
                LuaChonTTCNTK.setPreferredSize(new Dimension(200, 30));
                GioiTinh.add(gioitinh);
                GioiTinh.add(LuaChonTTCNTK);
                pnTTCN1.add(GioiTinh);

                //CENTER-Dân Tộc
                JPanel DanToc = new JPanel(new FlowLayout());
                JLabel dantoc = new JLabel("Dân Tộc");
                dantoc.setPreferredSize(new Dimension(100, 30));
                JTextField tfTTCN5TK = new JTextField();
                tfTTCN5TK.setPreferredSize(new Dimension(200, 30));
                DanToc.add(dantoc);
                DanToc.add(tfTTCN5TK);
                pnTTCN1.add(DanToc);

                //CENTER-CCCD
                JPanel CCCD = new JPanel(new FlowLayout());
                JLabel cccd = new JLabel("CCCD/CMND");
                cccd.setPreferredSize(new Dimension(100, 30));
                JTextField tfTTCN6TK = new JTextField();
                tfTTCN6TK.setPreferredSize(new Dimension(200, 30));
                CCCD.add(cccd);
                CCCD.add(tfTTCN6TK);
                pnTTCN1.add(CCCD);

                //CENTER-Sđt
                JPanel SDT = new JPanel(new FlowLayout());
                JLabel sdt = new JLabel("Số Điện Thoại");
                sdt.setPreferredSize(new Dimension(100, 30));
                JTextField tfTTCN7TK = new JTextField();
                tfTTCN7TK.setPreferredSize(new Dimension(200, 30));
                SDT.add(sdt);
                SDT.add(tfTTCN7TK);
                pnTTCN1.add(SDT);

                //Mã Khoa
                JPanel KHOA = new JPanel(new FlowLayout());
                JLabel khoa = new JLabel("Tên Khoa");
                khoa.setPreferredSize(new Dimension(100, 30));
                JTextField tfTTCN8TK = new JTextField();
                tfTTCN8TK.setPreferredSize(new Dimension(200, 30));
                KHOA.add(khoa);
                KHOA.add(tfTTCN8TK);
                pnTTCN1.add(KHOA);

                //Mã Lớp
                JPanel LOP = new JPanel(new FlowLayout());
                JLabel lop = new JLabel("Tên Lớp");
                lop.setPreferredSize(new Dimension(100, 30));
                JTextField tfTTCN9TK = new JTextField();
                tfTTCN9TK.setPreferredSize(new Dimension(200, 30));
                LOP.add(lop);
                LOP.add(tfTTCN9TK);
                pnTTCN1.add(LOP);

                //Tên Cố Vấn
                JPanel COVAN = new JPanel(new FlowLayout());
                JLabel covan = new JLabel("Tên Cố Vấn");
                covan.setPreferredSize(new Dimension(100, 30));
                JTextField tfTTCN10TK = new JTextField();
                tfTTCN10TK.setPreferredSize(new Dimension(200, 30));
                COVAN.add(covan);
                COVAN.add(tfTTCN10TK);
                pnTTCN1.add(COVAN);

                JButton btXacNhan = new JButton("Xác Nhận");
                btXacNhan.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String maSinhVien = tfTTCN1TK.getText();
                        String tenSinhVien = tfTTCN2TK.getText();
                        String namSinh = tfTTCN3TK.getText();
                        String gioiTinh = (String) LuaChonTTCNTK.getSelectedItem();
                        String danToc = tfTTCN5TK.getText();
                        String CCCD = tfTTCN6TK.getText();
                        String soDienThoai = tfTTCN7TK.getText();
                        String tenKhoa = tfTTCN8TK.getText();
                        String tenLop = tfTTCN9TK.getText();
                        String tenCoVan = tfTTCN10TK.getText();
                        SinhVien sv1 = new SinhVien(maSinhVien, tenSinhVien, namSinh, gioiTinh, danToc, CCCD, soDienThoai, tenCoVan, tenKhoa, tenLop);

                        DefaultTableModel model = (DefaultTableModel) bangDSSV.getModel();
                        model.setRowCount(0);
                        ArrayList<SinhVien> svListTK = svBUS.getlistSinhVienDKDB(sv1);
                        for (SinhVien sv : svListTK) {
                            Object[] duLieu = {sv.getMaSinhVien(), sv.getTenSinhVien(), sv.getNamSinh(), sv.getGioiTinh(), sv.getDanToc(), sv.getCCCD(), sv.getSoDienThoai(), sv.getTenLop(), sv.getTenKhoa(), sv.getTenCoVan()};
                            modelDSSV.addRow(duLieu);
                        }
                    }
                });
                JButton thoat = new JButton("Thoát");
                thoat.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        btTimKiem.dispose();
                    }
                });
                pnTTCN.add(pnTTCN1);
                btTimKiem.add(pnTTCN, BorderLayout.CENTER);
                JPanel pnXacNhan = new JPanel(new FlowLayout());
                pnXacNhan.add(btXacNhan);
                pnXacNhan.add(thoat);
                btTimKiem.add(pnXacNhan, BorderLayout.SOUTH);
            }
        });

        JButton xemdiem = new JButton("Xem điểm");
        xemdiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frXemDiem = new JFrame("Xem Điểm");
            }
        });
        JButton themds = new JButton("Thêm Từ File");
        themds.addActionListener(e -> {
            ArrayList<String[]> list = ExportExcel.ImportExcelData();
            boolean flag = false;
            for (int i = 0; i < list.size(); i++) {
                int j = 0;
                String masvim = list.get(i)[j++];
                String tensvim = list.get(i)[j++];
                String namsinhim = list.get(i)[j++];
                String gioitinhim = list.get(i)[j++];
                String dantocim = list.get(i)[j++];
                String cccdim = list.get(i)[j++];
                String sdtim = list.get(i)[j++];
                String makhoaim = list.get(i)[j++];
                String malopim = list.get(i)[j];
                j = 0;

                SinhVien sv = new SinhVien(masvim, tensvim, namsinhim, gioitinhim, dantocim, cccdim, sdtim, makhoaim, malopim);
                if (SinhVienDAO.insert(sv)) {
                    flag = true;
                }
            }
            if (flag) {
                JOptionPane.showMessageDialog(null, "Thêm Từ Danh Sách Thành Công");
            } else {
                JOptionPane.showMessageDialog(null, "Thêm Không Thành Công");
            }

            DefaultTableModel model = (DefaultTableModel) bangDSSV.getModel();
            model.setRowCount(0);
            ArrayList<SinhVien> svListTK = svBUS.getlistSinhVien();
            for (SinhVien sv : svListTK) {
                Object[] duLieu = {sv.getMaSinhVien(), sv.getTenSinhVien(), sv.getNamSinh(), sv.getGioiTinh(), sv.getDanToc(), sv.getCCCD(), sv.getSoDienThoai(), sv.getTenLop(), sv.getTenKhoa(), sv.getTenCoVan()};
                modelDSSV.addRow(duLieu);
            }
        });

        JButton xuatds = new JButton("Xuất Danh Sách");
        xuatds.addActionListener(e -> {
            ArrayList<SinhVien> sv = new ArrayList<SinhVien>();
            sv = svBUS.getlistSinhVien();
            ExportExcel.ExportExcel(sv);
        });

        pn3.setLayout(new FlowLayout());
        pn3.add(them);
        pn3.add(sua);
        pn3.add(xoa);
        pn3.add(timKiem);
        pn3.add(xemdiem);
        pn3.add(themds);
        pn3.add(xuatds);
        pn3.setPreferredSize(new Dimension(0, 30));
        JPanel pnp = new JPanel();
        pnp.setPreferredSize(new Dimension(0, 150));
        pnp.setLayout(new BoxLayout(pnp, BoxLayout.Y_AXIS));
        pnp.add(pn1);
        pnp.add(pn2);
        pnp.add(pn3);
        pnp.add(chonLoc);

        pnp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        DSSV.add(scrollPaneDSSV, BorderLayout.CENTER);
        DSSV.add(pnp, BorderLayout.NORTH);

        //Danh sách giảng viên
        JPanel DSGV = new JPanel(new BorderLayout());
        JPanel locgv = new JPanel();
        locgv.setLayout(new BoxLayout(locgv, BoxLayout.X_AXIS));

        //Tìm Kiếm GV    
        String columnDSGV[] = {"Mã GV", "Tên giảng viên", "Giới Tính", "Năm Sinh", "Trình độ", "Số điện thoại", "Email", "Tên Khoa"};
        DefaultTableModel modelDSGV = new DefaultTableModel(columnDSGV, 0);

        ArrayList<GiangVien> gvList = gvBUS.getlistGiangVien();
        for (GiangVien gv : gvList) {
            Object[] duLieu = {gv.getMaGiangVien(), gv.getTenGiangVien(), gv.getGioiTinh(), gv.getNamSinh(), gv.getTrinhDo(), gv.getSdt(), gv.getEmail(), gv.getMaKhoa()};
            modelDSGV.addRow(duLieu);
        }
        JTable bangDSGV = new JTable(modelDSGV);
        bangDSGV.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPaneDSGV = new JScrollPane(bangDSGV);
        bangDSGV.getTableHeader().setResizingAllowed(false);
        TableColumnModel columnModelgv = bangDSGV.getColumnModel();

        //Kich Thước Cột
        columnModelgv.getColumn(0).setPreferredWidth(30);
        columnModelgv.getColumn(1).setPreferredWidth(130);
        columnModelgv.getColumn(2).setPreferredWidth(30);
        columnModelgv.getColumn(6).setPreferredWidth(130);

        String[] TIMKIEMGV = {"Tất Cả", "Mã Giảng Viên", "Tên Giảng Viên", "Năm Sinh", "Giới Tính", "Trình Độ", "Số Điện Thoại", "Email", "Tên Khoa"};
        JComboBox<String> DULIEUGV = new JComboBox<>(TIMKIEMGV);

        JTextField jcbgv = new JTextField();
        JButton btlocgv = new JButton("Lọc >>");
        btlocgv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag = false;
                String luaChonTimKiemgv = (String) DULIEUGV.getSelectedItem();
                String noiDunggv = new String();
                if (luaChonTimKiemgv.equalsIgnoreCase("Tất Cả")) {
                    DefaultTableModel model = (DefaultTableModel) bangDSGV.getModel();
                    model.setRowCount(0);
                    ArrayList<GiangVien> gvList = gvBUS.getlistGiangVien();
                    for (GiangVien gvThem : gvList) {
                        Object[] duLieuTimKiemgv = {gvThem.getMaGiangVien(), gvThem.getTenGiangVien(), gvThem.getGioiTinh(), gvThem.getNamSinh(), gvThem.getTrinhDo(), gvThem.getSdt(), gvThem.getEmail(), gvThem.getMaKhoa()};
                        modelDSGV.addRow(duLieuTimKiemgv);
                        flag = true;
                    }
                } else if (luaChonTimKiemgv.equalsIgnoreCase("Tên Giảng Viên")) {
                    noiDunggv = "tenGiangVien";
                } else if (luaChonTimKiemgv.equalsIgnoreCase("Mã Giảng Viên")) {
                    noiDunggv = "maGiangVien";
                } else if (luaChonTimKiemgv.equalsIgnoreCase("Giới Tính")) {
                    noiDunggv = "gioiTinh";
                } else if (luaChonTimKiemgv.equalsIgnoreCase("Năm Sinh")) {
                    noiDunggv = "namSinh";
                } else if (luaChonTimKiemgv.equalsIgnoreCase("Trình Độ")) {
                    noiDunggv = "trinhDo";
                } else if (luaChonTimKiemgv.equalsIgnoreCase("Email")) {
                    noiDunggv = "email";
                } else if (luaChonTimKiemgv.equalsIgnoreCase("Số Điện Thoại")) {
                    noiDunggv = "soDienThoai";
                } else if (luaChonTimKiemgv.equalsIgnoreCase("Tên Khoa")) {
                    noiDunggv = "tenKhoa";
                }

                if (!flag) {
                    ArrayList<GiangVien> listgv = gvBUS.getlistGiangVienDK(noiDunggv, jcbgv.getText());
                    DefaultTableModel model = (DefaultTableModel) bangDSGV.getModel();
                    model.setRowCount(0);
                    for (GiangVien gv : listgv) {
                        Object[] duLieuTimKiemgv = {gv.getMaGiangVien(), gv.getTenGiangVien(), gv.getGioiTinh(), gv.getNamSinh(), gv.getTrinhDo(), gv.getEmail(), gv.getSdt(), gv.getMaKhoa()};
                        modelDSGV.addRow(duLieuTimKiemgv);
                    }
                    jcb1.setText("");
                }
            }
        });
        locgv.add(DULIEUGV);
        locgv.add(jcbgv);
        locgv.add(btlocgv);
        JPanel chonlocgv = new JPanel(new GridLayout(1, 2));
        chonlocgv.add(locgv);
        chonlocgv.add(new JPanel());

        JPanel pn4 = new JPanel();

        JDatePicker namSinhGV = new JDateComponentFactory().createJDatePicker(dateModel);
        namSinhGV.setTextEditable(false); // Cho phép nhập ngày tháng năm bằng tay
        namSinhGV.setShowYearButtons(true); // Hiển thị nút điều khiển chọn năm
        namSinhGV.setButtonFocusable(true); // Cho phép focus vào nút điều khiển
        JPanel sinhGV = new JPanel(new BorderLayout());
        sinhGV.add((Component) namSinhGV, BorderLayout.CENTER);

        JLabel lbNamSinh = new JLabel("Năm Sinh ");
        JTextField khoaGV = new JTextField();
        JLabel lbkhoa = new JLabel("Mã Khoa ");
        JTextField mGV = new JTextField();
        khoaGV.setPreferredSize(new Dimension(80, 30));
        JLabel lbmg = new JLabel("Mã Giảng Viên ");
        JTextField tenGV = new JTextField();
        JLabel lbtg = new JLabel("Tên Giảng Viên ");
        String[] gendersGV = {"Nam", "Nữ"};
        JComboBox<String> gioitinhGV = new JComboBox<>(genders);
        JLabel lbgGV = new JLabel("Giới Tính ");
        JTextField trinhdo = new JTextField();
        JLabel lbtd = new JLabel("Trình Độ ");
        JTextField sdtgv = new JTextField();
        JLabel lbsg = new JLabel("Số Điện Thoại ");
        JTextField email = new JTextField();
        JLabel lbe = new JLabel("Email ");
        pn4.setLayout(new FlowLayout());
        pn4.add(lbmg);
        pn4.add(mGV);
        mGV.setPreferredSize(new Dimension(80, 30));
        pn4.add(lbtg);
        pn4.add(tenGV);
        tenGV.setPreferredSize(new Dimension(150, 30));
        pn4.add(lbNamSinh);
        pn4.add(sinhGV);
        pn4.add(lbkhoa);
        pn4.add(khoaGV);

        JPanel pn5 = new JPanel();
        pn5.setLayout(new FlowLayout());
        pn5.add(lbgGV);
        pn5.add(gioitinhGV);
        pn5.add(lbtd);
        pn5.add(trinhdo);
        trinhdo.setPreferredSize(new Dimension(100, 30));
        pn5.add(lbsg);
        pn5.add(sdtgv);
        sdtgv.setPreferredSize(new Dimension(130, 30));
        pn5.add(lbe);
        pn5.add(email);
        email.setPreferredSize(new Dimension(180, 30));

        JPanel pn6 = new JPanel();

        //NÚT THÊM
        JButton themg = new JButton("Thêm");
        themg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String MaGV = mGV.getText();
                String TenGV = tenGV.getText();
                String trinhDo = trinhdo.getText();
                String Email = email.getText();

                Date dateOfBirth = (Date) namSinhGV.getModel().getValue();
                // Định dạng lại định dạng ngày tháng năm
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = dateFormat.format(dateOfBirth);

                String GioiTinh = (String) gioitinhGV.getSelectedItem();

                String Sdt = sdtgv.getText();

                String maKhoa = khoaGV.getText();
                GiangVien gv = new GiangVien(MaGV, TenGV, GioiTinh, dateString, trinhDo, Sdt, Email, maKhoa);

                JOptionPane.showMessageDialog(null, gvBUS.insert(gv));
                DefaultTableModel model = (DefaultTableModel) bangDSGV.getModel();
                model.setRowCount(0);
                ArrayList<GiangVien> listgv = gvBUS.getlistGiangVien();
                for (GiangVien gvThem : listgv) {
                    Object[] duLieuTimKiemgv = {gvThem.getMaGiangVien(), gvThem.getTenGiangVien(), gvThem.getGioiTinh(), gvThem.getNamSinh(), gvThem.getTrinhDo(), gvThem.getSdt(), gvThem.getEmail(), gvThem.getMaKhoa()};
                    modelDSGV.addRow(duLieuTimKiemgv);
                }

                mGV.setText("");
                tenGV.setText("");
                dantoc.setText("");
                trinhdo.setText("");
                gioitinh.setSelectedIndex(0);
                sdtgv.setText("");
                email.setText("");
                khoaGV.setText("");

            }
        });

        JButton suag = new JButton("Sửa");
        suag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = bangDSGV.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một dòng để thực hiện tác vụ");
                    return;
                } else {
                    JFrame btSua = new JFrame("Sửa Đổi Thông Tin");
                    btSua.setSize(500, 500);
                    btSua.setVisible(true);
                    btSua.setLocationRelativeTo(null);
                    btSua.setLayout(new BorderLayout());

                    TableModel model = bangDSGV.getModel();
                    Object value = model.getValueAt(selectedRow, 0);
                    String GVSua = value.toString();

                    GiangVien gv = new GiangVien();
                    gv = GiangVienDAO.selectById(GVSua);
                    JPanel pnTTCN = new JPanel();
                    pnTTCN.setLayout(new BoxLayout(pnTTCN, BoxLayout.Y_AXIS));
                    JPanel pnTTCN1 = new JPanel(new GridLayout(9, 1));

                    //CENTER-MaSV
                    JPanel MaGV = new JPanel(new FlowLayout());
                    JLabel maGv = new JLabel("Mã Giảng Viên");
                    maGv.setPreferredSize(new Dimension(100, 30));
                    JLabel tfTTCN1 = new JLabel();
                    tfTTCN1.setPreferredSize(new Dimension(200, 30));
                    tfTTCN1.setText(gv.getMaGiangVien());
                    MaGV.add(maGv);
                    MaGV.add(tfTTCN1);
                    pnTTCN1.add(MaGV);

                    //CENTER-TenSV
                    JPanel TenGV = new JPanel(new FlowLayout());
                    JLabel TenGv = new JLabel("Họ Và Tên");
                    TenGv.setPreferredSize(new Dimension(100, 30));
                    TenGv.setSize(100, 100);
                    JTextField tfTTCN2 = new JTextField();
                    tfTTCN2.setText(gv.getTenGiangVien());
                    tfTTCN2.setPreferredSize(new Dimension(200, 30));
                    TenGV.add(TenGv);
                    TenGV.add(tfTTCN2);
                    pnTTCN1.add(TenGV);

                    //CENTER-Nam Sinh
                    JPanel NamSinh = new JPanel(new FlowLayout());
                    JLabel namSinh = new JLabel("Năm Sinh");
                    namSinh.setPreferredSize(new Dimension(100, 30));
                    namSinh.setSize(100, 100);
                    JTextField tfTTCN3 = new JTextField();
                    tfTTCN3.setText(gv.getNamSinh());
                    tfTTCN3.setPreferredSize(new Dimension(200, 30));
                    NamSinh.add(namSinh);
                    NamSinh.add(tfTTCN3);
                    pnTTCN1.add(NamSinh);

                    //CENTER-Giới Tính
                    JPanel GioiTinh = new JPanel(new FlowLayout());
                    JLabel gioitinh = new JLabel("Giới Tính");
                    String[] luaChonTTCN = {"Nam", "Nữ"};
                    JComboBox<String> LuaChonTTCN = new JComboBox<>(luaChonTTCN);
                    if (gv.getGioiTinh().equalsIgnoreCase("Nam")) {
                        LuaChonTTCN.setSelectedIndex(0);
                    } else {
                        LuaChonTTCN.setSelectedIndex(1);
                    }
                    gioitinh.setPreferredSize(new Dimension(100, 30));
                    LuaChonTTCN.setPreferredSize(new Dimension(200, 30));
                    GioiTinh.add(gioitinh);
                    GioiTinh.add(LuaChonTTCN);
                    pnTTCN1.add(GioiTinh);

                    //CENTER-Dân Tộc
                    JPanel DanToc = new JPanel(new FlowLayout());
                    JLabel dantoc = new JLabel("Trình Độ");
                    dantoc.setPreferredSize(new Dimension(100, 30));
                    JTextField tfTTCN5 = new JTextField();
                    tfTTCN5.setText(gv.getTrinhDo());
                    tfTTCN5.setPreferredSize(new Dimension(200, 30));
                    DanToc.add(dantoc);
                    DanToc.add(tfTTCN5);
                    pnTTCN1.add(DanToc);

                    //CENTER-CCCD
                    JPanel CCCD = new JPanel(new FlowLayout());
                    JLabel cccd = new JLabel("Email");
                    cccd.setPreferredSize(new Dimension(100, 30));
                    JTextField tfTTCN6 = new JTextField();
                    tfTTCN6.setText(gv.getEmail());
                    tfTTCN6.setPreferredSize(new Dimension(200, 30));
                    CCCD.add(cccd);
                    CCCD.add(tfTTCN6);
                    pnTTCN1.add(CCCD);

                    //CENTER-Sđt
                    JPanel SDT = new JPanel(new FlowLayout());
                    JLabel sdt = new JLabel("Số Điện Thoại");
                    sdt.setPreferredSize(new Dimension(100, 30));
                    JTextField tfTTCN7 = new JTextField();
                    tfTTCN7.setText(gv.getSdt());
                    tfTTCN7.setPreferredSize(new Dimension(200, 30));
                    SDT.add(sdt);
                    SDT.add(tfTTCN7);
                    pnTTCN1.add(SDT);

                    //Mã Khoa
                    JPanel KHOA = new JPanel(new FlowLayout());
                    JLabel khoa = new JLabel("Mã Khoa");
                    khoa.setPreferredSize(new Dimension(100, 30));
                    JTextField tfTTCN8 = new JTextField();
                    tfTTCN8.setText(gv.getMaKhoa());
                    tfTTCN8.setPreferredSize(new Dimension(200, 30));
                    KHOA.add(khoa);
                    KHOA.add(tfTTCN8);
                    pnTTCN1.add(KHOA);

                    pnTTCN.add(pnTTCN1);
                    btSua.add(pnTTCN, BorderLayout.CENTER);

                    JPanel btXacNhan = new JPanel(new FlowLayout());
                    JButton XacNhan = new JButton("Xác Nhận");
                    XacNhan.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String gioiTinh = (String) LuaChonTTCN.getSelectedItem();
                            String TenGV = tfTTCN2.getText();
                            String namSinh = tfTTCN3.getText();
                            String trinhDo = tfTTCN5.getText();
                            String email = tfTTCN6.getText();
                            String sdt = tfTTCN7.getText();
                            String maKhoa = tfTTCN8.getText();

                            GiangVien gvSua = new GiangVien(GVSua, TenGV, gioiTinh, namSinh, trinhDo, sdt, email, maKhoa);
                            if (GiangVienDAO.update(gvSua) && TenGV != null && gioiTinh != null && namSinh != null && trinhDo != null && email != null && sdt != null && maKhoa != null) {
                                JOptionPane.showMessageDialog(new JFrame(), "Sửa Thông Tin Thành Công");
                                DefaultTableModel model = (DefaultTableModel) bangDSGV.getModel();
                                model.setRowCount(0);
                                ArrayList<GiangVien> listgv = gvBUS.getlistGiangVien();
                                for (GiangVien gvThem : listgv) {
                                    Object[] duLieuupdategv = {gvThem.getMaGiangVien(), gvThem.getTenGiangVien(), gvThem.getGioiTinh(), gvThem.getNamSinh(), gvThem.getTrinhDo(), gvThem.getSdt(), gvThem.getEmail(), gvThem.getMaKhoa()};
                                    modelDSGV.addRow(duLieuupdategv);
                                }
                                btSua.dispose();
                            } else {
                                JOptionPane.showMessageDialog(new JFrame(), "Sửa Thông Tin Không Thành Công");
                            }
                        }
                    });
                    JButton Huy = new JButton("Hủy");
                    Huy.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            btSua.dispose();
                        }
                    });
                    btXacNhan.add(XacNhan);
                    btXacNhan.add(Huy);
                    btSua.add(btXacNhan, BorderLayout.SOUTH);

                }
            }
        });

        //NÚT XÓA
        JButton xoag = new JButton("Xóa");
        xoag.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TableModel model = bangDSGV.getModel();
                int selectedRow = bangDSGV.getSelectedRow();
                Object value = model.getValueAt(selectedRow, 0); // Lấy giá trị của ô đầu tiên trong dòng đã chọn
                // Lưu giá trị vào biến
                String MaGVXoa = value.toString();
                GiangVien gvXoa = new GiangVien();
                gvXoa = GiangVienDAO.selectById(MaGVXoa);
                JOptionPane.showMessageDialog(null, gvBUS.delete(gvXoa));
                DefaultTableModel model3 = (DefaultTableModel) bangDSGV.getModel();
                model3.setRowCount(0);
                ArrayList<GiangVien> listgv = gvBUS.getlistGiangVien();
                for (GiangVien gvThem : listgv) {
                    Object[] duLieuupdategv = {gvThem.getMaGiangVien(), gvThem.getTenGiangVien(), gvThem.getGioiTinh(), gvThem.getNamSinh(), gvThem.getTrinhDo(), gvThem.getSdt(), gvThem.getEmail(), gvThem.getMaKhoa()};
                    modelDSGV.addRow(duLieuupdategv);
                }
            }
        });

        JButton xemlichgiang = new JButton("Xem Môn Giảng Dạy");
        xemlichgiang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRowGD = bangDSGV.getSelectedRow();

                if (selectedRowGD == -1) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một dòng để thực hiện tác vụ");
                } else {
                    JFrame frLichGiang = new JFrame("Danh Sách Môn Giảng Dạy");
                    frLichGiang.setSize(500, 300);
                    frLichGiang.setVisible(true);
                    frLichGiang.setLayout(new BorderLayout());
                    frLichGiang.setLocationRelativeTo(null);

                    String columnDSGD[] = {"Mã Môn Học", "Tên Môn Học", "Số Tín Chỉ"};
                    DefaultTableModel modelDSGD = new DefaultTableModel(columnDSGD, 0);
                    String value = (String) bangDSGV.getValueAt(selectedRowGD, 0);
                    ArrayList<ChiTietGiangDay> gdList = ChiTietGiangDayDAO.selectAll(value);
                    for (ChiTietGiangDay gd : gdList) {
                        Object[] duLieuGD = {gd.getMaHP(), gd.getTenHP(), gd.getSoTinChi()};
                        modelDSGD.addRow(duLieuGD);
                    }
                    JTable bangDSGD = new JTable(modelDSGD);

                    bangDSGD.getTableHeader().setResizingAllowed(false);

                    bangDSGD.getTableHeader().setReorderingAllowed(false);
                    JScrollPane scrollPaneDSGD = new JScrollPane(bangDSGD);

                    JPanel khungNhap = new JPanel(new FlowLayout());

                    JLabel maGD = new JLabel("Mã Môn Học");

                    JTextField khungMH = new JTextField();
                    khungMH.setPreferredSize(new Dimension(100, 30));
                    khungNhap.add(maGD);
                    khungNhap.add(khungMH);
                    JPanel pnButton = new JPanel();
                    pnButton.setLayout(new BoxLayout(pnButton, BoxLayout.Y_AXIS));
                    JButton ThemGD = new JButton("Thêm");
                    ThemGD.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            ChiTietGiangDay ctgd = new ChiTietGiangDay(khungMH.getText(), value);
                            if (ChiTietGiangDayDAO.insert(ctgd)) {
                                DefaultTableModel model = (DefaultTableModel) bangDSGD.getModel();
                                model.setRowCount(0);
                                JOptionPane.showMessageDialog(new JFrame(), "Đã Thêm Thành Công");
                                Object[] rowDataGD = {khungMH.getText(), value};

                                ArrayList<ChiTietGiangDay> gdList = ChiTietGiangDayDAO.selectAll(value);
                                for (ChiTietGiangDay gd : gdList) {
                                    Object[] duLieuGD1 = {gd.getMaHP(), gd.getTenHP(), gd.getSoTinChi()};
                                    modelDSGD.addRow(duLieuGD1);
                                }
                            }

                        }
                    });
                    JButton XoaGD = new JButton("Xóa");
                    XoaGD.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            TableModel model = bangDSGD.getModel();
                            int selectedRow = bangDSGD.getSelectedRow();
                            Object value1 = model.getValueAt(selectedRow, 0); // Lấy giá trị của ô đầu tiên trong dòng đã chọn
                            // Lưu giá trị vào biến
                            String MaGDXoa = value1.toString();

                            if (ChiTietGiangDayDAO.delete(MaGDXoa, value)) {
                                JOptionPane.showMessageDialog(new JFrame(), "Đã Xóa Thành Công");
                                if (selectedRow != -1) {
                                    modelDSGD.removeRow(selectedRow);
                                }
                            } else {
                                JOptionPane.showMessageDialog(new JFrame(), "Không Tìm Thấy Sinh Vien Cần Xóa");
                            }
                        }
                    });

                    JPanel pnBT = new JPanel(new FlowLayout());
                    pnBT.add(ThemGD);
                    pnBT.add(XoaGD);

                    pnButton.add(khungNhap);
                    pnButton.add(pnBT);

                    frLichGiang.add(scrollPaneDSGD, BorderLayout.CENTER);
                    frLichGiang.add(pnButton, BorderLayout.NORTH);
                }
            }
        });

        pn6.setLayout(new FlowLayout());
        pn6.add(themg);
        pn6.add(suag);
        pn6.add(xoag);
        pn6.add(xemlichgiang);
        pn6.setPreferredSize(new Dimension(0, 55));
        JPanel pn = new JPanel();
        pn.setPreferredSize(new Dimension(0, 150));
        pn.setLayout(new BoxLayout(pn, BoxLayout.Y_AXIS));
        pn.add(pn4);
        pn.add(pn5);
        pn.add(pn6);
        pn.add(chonlocgv);
        pn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        DSGV.add(scrollPaneDSGV, BorderLayout.CENTER);
        DSGV.add(pn, BorderLayout.NORTH);

        //DSMH
        JPanel DSMH = new JPanel(new BorderLayout());
        String columnDSMH[] = {"Mã Học Phần", "Tên môn học", "Số Tín Chỉ", "Tên Giảng Viên", "Tên Phòng", "Thứ", "Tiết bắt đầu", "Ngày bắt đầu", "Ngày Kết Thúc"};
        DefaultTableModel modelDSMH = new DefaultTableModel(columnDSMH, 0);

        ArrayList<HocPhan> hpList = hpBUS.getlistHocPhan();
        for (HocPhan gv : hpList) {
            Object[] duLieu = {gv.getMaHP(), gv.getTenMH(), gv.getTinChi(), gv.getTenGiangVien(), gv.getTenPhong(), gv.getThu(), gv.getTietBatDau(), gv.getTimeBatDau(), gv.getTimeKetThuc()};
            modelDSMH.addRow(duLieu);
        }

        JTable bangDSMH = new JTable(modelDSMH);
        bangDSMH.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPaneDSMH = new JScrollPane(bangDSMH);

        TableColumnModel columnModelmh = bangDSMH.getColumnModel();

        //Kich Thước Cột
        columnModelmh.getColumn(0).setPreferredWidth(30);
        columnModelmh.getColumn(1).setPreferredWidth(130);
        columnModelmh.getColumn(2).setPreferredWidth(10);
        columnModelmh.getColumn(3).setPreferredWidth(130);
        columnModelmh.getColumn(4).setPreferredWidth(130);

        JPanel locmh = new JPanel();
        locmh.setLayout(new BoxLayout(locmh, BoxLayout.X_AXIS));
        String[] TIMKIEMMH = {"Tất Cả", "Mã Học Phần", "Tên Môn Học", "Số Tín Chỉ", "Tên Giảng Viên", "Tên Phòng", "Thứ", "Tiết Bắt Đầu", "Ngày Bắt Đầu", "Ngày Kết Thúc"};
        JComboBox<String> DULIEUMH = new JComboBox<>(TIMKIEMMH);

        JTextField jcbmh = new JTextField();
        JButton btlocmh = new JButton("Lọc >>");
        btlocmh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag = false;
                String luaChonTimKiemgv = (String) DULIEUMH.getSelectedItem();
                String noiDung = new String();
                if (luaChonTimKiemgv.equalsIgnoreCase("Tất Cả")) {
                    DefaultTableModel model = (DefaultTableModel) bangDSMH.getModel();
                    model.setRowCount(0);
                    ArrayList<HocPhan> hpList = hpBUS.getlistHocPhan();
                    for (HocPhan gv : hpList) {
                        Object[] duLieu = {gv.getMaHP(), gv.getTenMH(), gv.getTinChi(), gv.getTenGiangVien(), gv.getThu(), gv.getTenPhong(), gv.getTietBatDau(), gv.getTimeBatDau(), gv.getTimeKetThuc()};
                        modelDSMH.addRow(duLieu);
                    }
                    flag = true;
                } else if (luaChonTimKiemgv.equalsIgnoreCase("Mã Học Phần")) {
                    noiDung = "maLopHocPhan";
                } else if (luaChonTimKiemgv.equalsIgnoreCase("Tên Môn Học")) {
                    noiDung = "tenMonHoc";
                } else if (luaChonTimKiemgv.equalsIgnoreCase("Số Tín Chỉ")) {
                    noiDung = "soTinChi";
                } else if (luaChonTimKiemgv.equalsIgnoreCase("Thứ")) {
                    noiDung = "thu";
                } else if (luaChonTimKiemgv.equalsIgnoreCase("Tên Giảng Viên")) {
                    noiDung = "tenGiangVien";
                } else if (luaChonTimKiemgv.equalsIgnoreCase("Tên Phòng")) {
                    noiDung = "tenPhongHoc";
                } else if (luaChonTimKiemgv.equalsIgnoreCase("Tiết Bắt Đầu")) {
                    noiDung = "tietBatDau";
                } else if (luaChonTimKiemgv.equalsIgnoreCase("Ngày Bắt Đầu")) {
                    noiDung = "thoiGianBatDau";
                } else if (luaChonTimKiemgv.equalsIgnoreCase("Ngày Kết Thúc")) {
                    noiDung = "thoiGianKetThuc";
                }

                if (!flag) {
                    ArrayList<HocPhan> listgv = hpBUS.getlistHocPhanDK(noiDung, jcbmh.getText());
                    DefaultTableModel model = (DefaultTableModel) bangDSMH.getModel();
                    model.setRowCount(0);
                    for (HocPhan gv : listgv) {
                        Object[] duLieu = {gv.getMaHP(), gv.getTenMH(), gv.getTinChi(), gv.getTenGiangVien(), gv.getTenPhong(), gv.getTietBatDau(), gv.getTimeBatDau(), gv.getTimeKetThuc()};
                        modelDSMH.addRow(duLieu);
                    }
                    jcbmh.setText("");
                }
            }
        });
        locmh.add(DULIEUMH);
        locmh.add(jcbmh);
        locmh.add(btlocmh);
        JPanel chonlocmh = new JPanel(new GridLayout(1, 2));
        chonlocmh.add(locmh);
        chonlocmh.add(new JPanel());

        JPanel pn7 = new JPanel();
        JTextField mMH = new JTextField();
        mMH.setPreferredSize(new Dimension(80, 30));
        JLabel lbmmh = new JLabel("Mã Học Phần ");
        lbmmh.setPreferredSize(new Dimension(110, 30));
        JTextField tenMH = new JTextField();
        tenMH.setPreferredSize(new Dimension(80, 30));
        JLabel lbtm = new JLabel("Mã Môn Học ");
        String[] tbd = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        JComboBox<String> tietbatdau = new JComboBox<>(tbd);
        JLabel lbtbd = new JLabel("Tiết bắt đầu ");

        JTextField giangvien = new JTextField();
        JLabel lbmpmh = new JLabel("Mã Phòng ");
        String[] tkb = {"2", "3", "4", "5", "6", "7"};
        JComboBox<String> tkbb = new JComboBox<>(tkb);
        JLabel lbthu = new JLabel("Thứ");

        JTextField mp = new JTextField();
        mp.setPreferredSize(new Dimension(80, 30));
        giangvien.setPreferredSize(new Dimension(100, 30));
        JLabel lbtgv = new JLabel("Mã Giảng viên ");

        UtilDateModel dateModelBD = new UtilDateModel();
        dateModelBD.setDate(2023, 0, 1); // Đặt giá trị ban đầu cho model
        dateModelBD.setSelected(true);

        JDatePicker ngayBatDau = new JDateComponentFactory().createJDatePicker(dateModelBD);
        ngayBatDau.setTextEditable(false); // Cho phép nhập ngày tháng năm bằng tay
        ngayBatDau.setShowYearButtons(true); // Hiển thị nút điều khiển chọn năm
        ngayBatDau.setButtonFocusable(true); // Cho phép focus vào nút điều khiển
        JPanel ngayBD = new JPanel(new BorderLayout());
        ngayBD.add((Component) ngayBatDau, BorderLayout.CENTER);

        UtilDateModel dateModelKT = new UtilDateModel();
        dateModelKT.setDate(2023, 0, 1); // Đặt giá trị ban đầu cho model
        dateModelKT.setSelected(true);
        JDatePicker ngayKetThuc = new JDateComponentFactory().createJDatePicker(dateModelKT);
        ngayKetThuc.setTextEditable(false); // Cho phép nhập ngày tháng năm bằng tay
        ngayKetThuc.setShowYearButtons(true); // Hiển thị nút điều khiển chọn năm
        ngayKetThuc.setButtonFocusable(true); // Cho phép focus vào nút điều khiển
        JPanel ngayKT = new JPanel(new BorderLayout());
        ngayKT.add((Component) ngayKetThuc, BorderLayout.CENTER);

        JLabel lbnbd = new JLabel("Ngày bắt đầu ");
        JLabel lbnkt = new JLabel("Ngày kết thúc ");
        pn7.setLayout(new FlowLayout());
        pn7.add(lbmmh);
        pn7.add(mMH);
        pn7.add(lbtm);
        pn7.add(tenMH);
        pn7.add(lbmpmh);
        pn7.add(mp);
        pn7.add(lbtbd);
        pn7.add(tietbatdau);
        pn7.add(lbthu);
        pn7.add(tkbb);

        pn7.setPreferredSize(new Dimension(0, 50));
        JPanel pn8 = new JPanel();
        pn8.setLayout(new FlowLayout());
        pn8.add(lbtgv);
        pn8.add(giangvien);
        pn8.add(lbnbd);
        pn8.add(ngayBD);
        pn8.add(lbnkt);
        pn8.add(ngayKT);
        pn8.setPreferredSize(new Dimension(0, 50));
        JPanel pn9 = new JPanel();
        JButton t = new JButton("Thêm");
        t.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String maHP = mMH.getText();
                String maPhong = mp.getText();
                String maMH = tenMH.getText();
                String tietBatDau = (String) tietbatdau.getSelectedItem();
                int thu = (int) tkbb.getSelectedItem();
                String maGiangVien = giangvien.getText();

                Date dateOfBirthBD = (Date) ngayBatDau.getModel().getValue();
                // Định dạng lại định dạng ngày tháng năm
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String dateStringBD = dateFormat.format(dateOfBirthBD);

                Date dateOfBirthKT = (Date) ngayKetThuc.getModel().getValue();

                String dateStringKT = dateFormat.format(dateOfBirthKT);

                HocPhan hp = new HocPhan(maHP, maMH, maPhong, maGiangVien, thu, tietBatDau, dateStringBD, dateStringKT);

                if (HocPhanDAO.insert(hp)) {
                    DefaultTableModel model = (DefaultTableModel) bangDSMH.getModel();
                    model.setRowCount(0);
                    ArrayList<HocPhan> hpList = hpBUS.getlistHocPhan();
                    for (HocPhan gv : hpList) {
                        Object[] duLieu = {gv.getMaHP(), gv.getTenMH(), gv.getTinChi(), gv.getTenGiangVien(), gv.getTenPhong(), gv.getTietBatDau(), gv.getTimeBatDau(), gv.getTimeKetThuc()};
                        modelDSMH.addRow(duLieu);
                    }

                    mMH.setText("");
                    mp.setText("");
                    tenMH.setText("");
                    tietbatdau.setSelectedIndex(0);
                    giangvien.setText("");
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Thêm Không Thành Công");
                }

            }
        });

        JButton s = new JButton("Sửa");
        s.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = bangDSMH.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một dòng để thực hiện tác vụ");
                    return;
                } else {
                    JFrame btSua = new JFrame("Sửa Đổi Thông Tin");
                    btSua.setSize(500, 500);
                    btSua.setVisible(true);
                    btSua.setLocationRelativeTo(null);
                    btSua.setLayout(new BorderLayout());

                    TableModel model = bangDSMH.getModel();
                    Object value = model.getValueAt(selectedRow, 0);
                    String HPSua = value.toString();

                    HocPhan hp = new HocPhan();
                    hp = HocPhanDAO.selectById(HPSua);
                    JPanel pnTTCN = new JPanel();
                    pnTTCN.setLayout(new BoxLayout(pnTTCN, BoxLayout.Y_AXIS));
                    JPanel pnTTCN1 = new JPanel(new GridLayout(8, 1));

                    //CENTER-MaHP
                    JPanel MAHP = new JPanel(new FlowLayout());
                    JLabel mahp = new JLabel("Mã Học Phần");
                    mahp.setPreferredSize(new Dimension(100, 30));
                    JLabel tfTTCN1 = new JLabel();
                    tfTTCN1.setPreferredSize(new Dimension(200, 30));
                    tfTTCN1.setText(hp.getMaHP());
                    MAHP.add(mahp);
                    MAHP.add(tfTTCN1);
                    pnTTCN1.add(MAHP);

                    //CENTER-MaMH
                    JPanel MAMH = new JPanel(new FlowLayout());
                    JLabel mamh = new JLabel("Mã Môn Học");
                    mamh.setPreferredSize(new Dimension(100, 30));
                    mamh.setSize(100, 100);
                    JTextField tfTTCN2 = new JTextField();
                    tfTTCN2.setText(hp.getMaMH());
                    tfTTCN2.setPreferredSize(new Dimension(200, 30));
                    MAMH.add(mamh);
                    MAMH.add(tfTTCN2);
                    pnTTCN1.add(MAMH);

                    //CENTER-MaGV
                    JPanel MAGV = new JPanel(new FlowLayout());
                    JLabel magv = new JLabel("Mã Giảng Viên");
                    magv.setPreferredSize(new Dimension(100, 30));
                    magv.setSize(100, 100);
                    JTextField tfTTCN3 = new JTextField();
                    tfTTCN3.setText(hp.getMaGiangVien());
                    tfTTCN3.setPreferredSize(new Dimension(200, 30));
                    MAGV.add(magv);
                    MAGV.add(tfTTCN3);
                    pnTTCN1.add(MAGV);

                    //CENTER-Mã Phòng
                    JPanel MAPHONG = new JPanel(new FlowLayout());
                    JLabel maphong = new JLabel("Mã Phòng");
                    maphong.setPreferredSize(new Dimension(100, 30));
                    maphong.setSize(100, 100);
                    JTextField tfTTCN4 = new JTextField();
                    tfTTCN4.setText(hp.getMaPhong());
                    tfTTCN4.setPreferredSize(new Dimension(200, 30));
                    MAPHONG.add(maphong);
                    MAPHONG.add(tfTTCN4);
                    pnTTCN1.add(MAPHONG);

                    //CENTER-Time Bắt Đầu
                    JPanel TIMEBD = new JPanel(new FlowLayout());
                    JLabel timebd = new JLabel("Ngày Bắt Đầu");
                    timebd.setPreferredSize(new Dimension(100, 30));
                    JTextField tfTTCN5 = new JTextField();
                    tfTTCN5.setText(hp.getTimeBatDau());
                    tfTTCN5.setPreferredSize(new Dimension(200, 30));
                    TIMEBD.add(timebd);
                    TIMEBD.add(tfTTCN5);
                    pnTTCN1.add(TIMEBD);

                    //CENTER-CCCD
                    JPanel TIMEKT = new JPanel(new FlowLayout());
                    JLabel timekt = new JLabel("Ngày Kết Thúc");
                    timekt.setPreferredSize(new Dimension(100, 30));
                    JTextField tfTTCN6 = new JTextField();
                    tfTTCN6.setText(hp.getTimeKetThuc());
                    tfTTCN6.setPreferredSize(new Dimension(200, 30));
                    TIMEKT.add(timekt);
                    TIMEKT.add(tfTTCN6);
                    pnTTCN1.add(TIMEKT);

                    //CENTER-Sđt
                    JPanel TIETBD = new JPanel(new FlowLayout());
                    JLabel tietbd = new JLabel("Tiết Bắt Đầu");
                    tietbd.setPreferredSize(new Dimension(100, 30));
                    JTextField tfTTCN7 = new JTextField();
                    tfTTCN7.setText(hp.getTietBatDau());
                    tfTTCN7.setPreferredSize(new Dimension(200, 30));
                    TIETBD.add(tietbd);
                    TIETBD.add(tfTTCN7);
                    pnTTCN1.add(TIETBD);

                    //CENTER-Sđt
                    JPanel THU = new JPanel(new FlowLayout());
                    JLabel Thu = new JLabel("Thứ");
                    Thu.setPreferredSize(new Dimension(100, 30));
                    JTextField tfTTCN8 = new JTextField();
                    tfTTCN8.setText(Integer.toString(hp.getThu()));
                    tfTTCN8.setPreferredSize(new Dimension(200, 30));
                    THU.add(Thu);
                    THU.add(tfTTCN8);
                    pnTTCN1.add(THU);

                    pnTTCN.add(pnTTCN1);
                    btSua.add(pnTTCN, BorderLayout.CENTER);

                    JPanel btXacNhan = new JPanel(new FlowLayout());
                    JButton XacNhan = new JButton("Xác Nhận");
                    XacNhan.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String maHP = tfTTCN1.getText();
                            String maMH = tfTTCN2.getText();
                            String tenGV = tfTTCN3.getText();
                            String maPhong = tfTTCN4.getText();
                            String timeBD = tfTTCN5.getText();
                            String timeKT = tfTTCN6.getText();
                            String tietBD = tfTTCN7.getText();
                            int thu = Integer.parseInt(tfTTCN8.getText());

                            HocPhan hpSua = new HocPhan(maHP, maMH, maPhong, tenGV, thu, tietBD, timeBD, timeKT);
                            if (HocPhanDAO.update(hpSua) && maMH != null && maHP != null && maPhong != null && tenGV != null && tietBD != null && timeBD != null && timeKT != null) {
                                JOptionPane.showMessageDialog(new JFrame(), "Sửa Thông Tin Thành Công");
                                DefaultTableModel model = (DefaultTableModel) bangDSMH.getModel();
                                model.setRowCount(0);
                                ArrayList<HocPhan> hpList = hpBUS.getlistHocPhan();
                                for (HocPhan gv : hpList) {
                                    Object[] duLieu = {gv.getMaHP(), gv.getTenMH(), gv.getTinChi(), gv.getTenGiangVien(), gv.getTenPhong(), gv.getThu(), gv.getTietBatDau(), gv.getTimeBatDau(), gv.getTimeKetThuc()};
                                    modelDSMH.addRow(duLieu);
                                }
                                btSua.dispose();
                            } else {
                                JOptionPane.showMessageDialog(new JFrame(), "Sửa Thông Tin Không Thành Công");
                            }
                        }
                    });
                    JButton Huy = new JButton("Hủy");
                    Huy.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            btSua.dispose();
                        }
                    });
                    btXacNhan.add(XacNhan);
                    btXacNhan.add(Huy);
                    btSua.add(btXacNhan, BorderLayout.SOUTH);
                }
            }
        });

        JButton x = new JButton("Xóa");
        x.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TableModel model = bangDSMH.getModel();
                int selectedRow = bangDSMH.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Vui Lòng Chọn Dòng Để Thực Hiện Tác Vụ");
                } else {
                    Object value = model.getValueAt(selectedRow, 0); // Lấy giá trị của ô đầu tiên trong dòng đã chọn
                    // Lưu giá trị vào biến
                    String MaMHXoa = value.toString();
                    HocPhan hp = new HocPhan();
                    hp = HocPhanDAO.selectById(MaMHXoa);
                    JOptionPane.showMessageDialog(null, hpBUS.delete(hp));
                    ArrayList<HocPhan> listhp = hpBUS.getlistHocPhan();
                    DefaultTableModel model2 = (DefaultTableModel) bangDSMH.getModel();
                    model2.setRowCount(0);
                    ArrayList<HocPhan> hpList = hpBUS.getlistHocPhan();
                    for (HocPhan gv : hpList) {
                        Object[] duLieu = {gv.getMaHP(), gv.getTenMH(), gv.getTinChi(), gv.getTenGiangVien(), gv.getTenPhong(), gv.getTietBatDau(), gv.getTimeBatDau(), gv.getTimeKetThuc()};
                        modelDSMH.addRow(duLieu);
                    }
                }
            }
        });

        pn9.setLayout(new FlowLayout());
        pn9.add(t);
        pn9.add(s);
        pn9.add(x);
        pn9.setPreferredSize(new Dimension(0, 55));
        JPanel pnmh = new JPanel();
        pnmh.setPreferredSize(new Dimension(0, 150));
        pnmh.setLayout(new BoxLayout(pnmh, BoxLayout.Y_AXIS));
        pnmh.add(pn7);
        pnmh.add(pn8);
        pnmh.add(pn9);
        pnmh.add(chonlocmh);
        pnmh.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        DSMH.add(scrollPaneDSMH, BorderLayout.CENTER);
        DSMH.add(pnmh, BorderLayout.NORTH);

        // Phòng học
        // Thêm Vô tabbedPane
        JTabbedPane thanhChon = new JTabbedPane();
        thanhChon.addTab("Trang Chủ", new ImageIcon(GiaoDienDNSinhVien.getPathIcon() + "home_1.png"), trangChu);
        thanhChon.addTab("Danh Sách Sinh Viên", DSSV);
        thanhChon.addTab("Danh Sách Giảng Viên", DSGV);
        thanhChon.addTab("Danh Sách Môn Học", DSMH);

        //Thêm Vào Jframe
        add(titlePn, BorderLayout.NORTH);
        add(thanhChon, BorderLayout.CENTER);
        add(new JPanel(), BorderLayout.WEST);
        add(new JPanel(), BorderLayout.EAST);
        add(new JPanel(), BorderLayout.SOUTH);

    }

    public static void main(String[] args) {
        new GiaoDienQuanLy();
    }
}
