package Export;

import DTO.SinhVien;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import static org.apache.poi.ss.usermodel.CellType.BOOLEAN;
import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import static org.apache.poi.ss.usermodel.CellType.STRING;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportExcel {
    
public static void ExportExcel(ArrayList<SinhVien> a) {
            XSSFWorkbook workbook=new XSSFWorkbook();   
            XSSFSheet sheet=workbook.createSheet("DanhSach");
            XSSFRow row=null;
            Cell cell=null;
            row=sheet.createRow(0);
            cell=row.createCell(0,CellType.STRING);
            cell.setCellValue("Mã Sinh Viên");
            cell=row.createCell(1,CellType.STRING);
            cell.setCellValue("Tên Sinh Viên");
            cell=row.createCell(2,CellType.STRING);
            cell.setCellValue("Năm Sinh");
            cell=row.createCell(3,CellType.STRING);
            cell.setCellValue("Giới Tính");
            cell=row.createCell(4,CellType.STRING);
            cell.setCellValue("Dân Tộc");
            cell=row.createCell(5,CellType.STRING);
            cell.setCellValue("CCCD");
            cell=row.createCell(6,CellType.STRING);
            cell.setCellValue("Số Điện Thoại");
            cell=row.createCell(7,CellType.STRING);
            cell.setCellValue("Tên Lớp");
            cell=row.createCell(8,CellType.STRING);
            cell.setCellValue("Tên Khoa");
            cell=row.createCell(9,CellType.STRING);
            cell.setCellValue("Tên Cố Vấn");
            for(int i=0;i<a.size();i++){
                row=sheet.createRow(i+1);
                
                cell=row.createCell(0,CellType.STRING);
                cell.setCellValue(a.get(i).getMaSinhVien());
                
                cell=row.createCell(1,CellType.STRING);
                cell.setCellValue(a.get(i).getTenSinhVien());
            
                cell=row.createCell(2,CellType.STRING);
                cell.setCellValue(a.get(i).getNamSinh());

                cell=row.createCell(3,CellType.STRING);
                cell.setCellValue(a.get(i).getGioiTinh());
                
                cell=row.createCell(4,CellType.STRING);
                cell.setCellValue(a.get(i).getDanToc());
                
                cell=row.createCell(5,CellType.STRING);
                cell.setCellValue(a.get(i).getCCCD());
                
                cell=row.createCell(6,CellType.STRING);
                cell.setCellValue(a.get(i).getSoDienThoai());
                
                cell=row.createCell(7,CellType.STRING);
                cell.setCellValue(a.get(i).getTenLop());
                
                cell=row.createCell(8,CellType.STRING);
                cell.setCellValue(a.get(i).getTenKhoa());
                
                cell=row.createCell(9,CellType.STRING);
                cell.setCellValue(a.get(i).getTenCoVan());
            }
            File F=new File("/Users/lythanhphat9523/Desktop/Export.xlsx");
            try{
                
                FileOutputStream fis= new FileOutputStream(F);
                workbook.write(fis);
                fis.close();
            }
            catch (FileNotFoundException ex){
                ex.printStackTrace();
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Xuất Thành Công");
    }

        public static ArrayList<String[]> ImportExcelData() {
        File F=new File("/Users/lythanhphat9523/Desktop/Import.xlsx");
        ArrayList<String[]> dataList = new ArrayList<String[]>();
        
        try {
            // Mở file Excel và lấy sheet tương ứng
            Workbook workbook = WorkbookFactory.create(F);
            Sheet sheet = workbook.getSheetAt(0);
            
            // Lấy iterator cho các dòng của sheet
            Iterator<Row> rowIterator = sheet.iterator();
            
            // Duyệt qua các dòng của sheet
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                ArrayList<String> rowData = new ArrayList<String>();
                
                // Lấy iterator cho các ô của dòng hiện tại
                Iterator<Cell> cellIterator = row.iterator();
                
                // Duyệt qua các ô của dòng hiện tại
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    
                    // Thêm giá trị của ô vào danh sách rowData
                    switch (cell.getCellType()) {
                        case STRING:
                            rowData.add(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            rowData.add(String.valueOf(cell.getNumericCellValue()).replace(".0", ""));
                            break;
                        case BOOLEAN:
                            rowData.add(String.valueOf(cell.getBooleanCellValue()));
                            break;
                        default:
                            rowData.add("");
                    }
                }
                
                // Chuyển đổi danh sách rowData thành mảng và thêm vào danh sách dữ liệu dataList
                dataList.add(rowData.toArray(new String[rowData.size()]));
            }
            
            // Đóng workbook
            workbook.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return dataList;
    }
}

        
             

