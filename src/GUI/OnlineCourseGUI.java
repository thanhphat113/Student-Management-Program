package GUI;



import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class OnlineCourseGUI extends JFrame{
//	OnlineCourseBUS olBUS= new OnlineCourseBUS();
	private Font fontSubTittle = new Font("Tahoma", Font.BOLD, 20);
	private Font fontTable = new Font("Segoe UI", Font.BOLD, 13);
	
	
	public OnlineCourseGUI() {
		unitGUI();
	}
	
	public void unitGUI() {
		setLayout(new BorderLayout());
                setSize(500,500);
		setVisible(true);
		JLabel lbTitle = new JLabel("KHOÁ HỌC ONLINE");
		JPanel pnTitle = new JPanel();
		lbTitle.setFont(fontSubTittle);
		pnTitle.add(lbTitle);
		pnTitle.setPreferredSize(new Dimension(0, 100));
//		add(pnTitle, BorderLayout.NORTH);
		JScrollPane listOnlineCourse =tblistCourse();
		add(listOnlineCourse,BorderLayout.CENTER);
	}
	
	public JScrollPane tblistCourse() {
            String columnName[] = {"ID", "Tên Môn Học", "Khoa", "Giá", "Url"};
            DefaultTableModel model = new DefaultTableModel(columnName, 0);
//        List<OnlineCourseDTO> list= olBUS.findAll();
//        for(OnlineCourseDTO course:list) {
        	Object[] duLieu = {1,2,3,4,5};
        	model.addRow(duLieu);
//        }
        JTable listCourse = new JTable(model);
        listCourse.setFont(fontTable);
        JScrollPane scrollPane = new JScrollPane(listCourse);
        return scrollPane;
	}
	
	

}
