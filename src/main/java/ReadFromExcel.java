import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:   supeng
 * Date:     2019-05-27 20:10
 * Description:  读EXCEL文件
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class ReadFromExcel {

    public static void main(String[] args) {
        String path = "E:/studentExcel.xls";
        List<List<String>> lists = readFromExcel(path);

        for (List<String> list: lists)
        {
            for(String str: list){
                System.out.print(str);
            }
            System.out.println();
        }

    }

    /**
     *
     * @param path  文档路径
     * @return  返回所有数据
     */
    public static List<List<String>> readFromExcel(String path) {
        String fileType = path.substring(path.lastIndexOf(".") + 1);
        System.out.println(fileType);

        List<List<String>> lists = new ArrayList<List<String>>();

        InputStream is = null;

        try {
            is = new FileInputStream(path);
            //获取工作薄
            Workbook wb = null;

            if (fileType.equals("xls")) {
                wb = new HSSFWorkbook(is);
            } else if (fileType.equals("xlsx")) {
                wb = new XSSFWorkbook(is);
            } else {
                return null;
            }

            // 取第一个工作页
            Sheet sheet = wb.getSheetAt(0);

            for (Row row : sheet) {
                ArrayList<String> arrayList = new ArrayList<>();

                for(Cell cell: row){
                    arrayList.add(cell.getStringCellValue());
                }

                lists.add(arrayList);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(is !=null ){
                    is.close();
                }
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return lists;
    }


}