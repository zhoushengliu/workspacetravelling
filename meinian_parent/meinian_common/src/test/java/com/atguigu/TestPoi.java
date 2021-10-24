package com.atguigu;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileOutputStream;

/**
 * TestPoi
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-07-30
 * @Description:
 */
public class TestPoi {

    /**
     * 在excel表格里面写数据
     */
    @Test
    public void test02() throws Exception{
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 创建页签
        XSSFSheet sheet = workbook.createSheet("zsl");
        // 创建行对象
        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("姓名");
        row.createCell(1).setCellValue("性别");
        row.createCell(2).setCellValue("地址");
        // 创建第二行数据
        XSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("哈哈");
        row1.createCell(1).setCellValue("男");
        row1.createCell(2).setCellValue("深圳");
        // 创建第二行数据
        XSSFRow row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue("嘎嘎");
        row2.createCell(1).setCellValue("女");
        row2.createCell(2).setCellValue("深圳");
        // 创建流对象
        FileOutputStream stream = new FileOutputStream("d:\\aliu.xlsx");
        workbook.write(stream);
        stream.flush();
        stream.close();
        workbook.close();
    }


    /**
     * 读取excel表格里面的数据
     */
    @Test
    public void test01() throws Exception{
        // 创建excel对象
        XSSFWorkbook workbook = new XSSFWorkbook("d:\\hello.xlsx");
        // 创建工作簿对象
        XSSFSheet sheetAt = workbook.getSheetAt(0);
        // 获取行对象
        for (Row row : sheetAt) {
            for (Cell cell : row) {
                // 判断当前数据的类型
                String result = cell.getStringCellValue();
                System.out.println(result);
            }
        }

    }
}