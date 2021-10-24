package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.servcie.MemberService;
import com.atguigu.servcie.ReportService;
import com.atguigu.servcie.SetmealService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

/**
 * ReportController
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-08-03
 * @Description:
 */
@RestController
@RequestMapping("/report")
public class ReportController {

    @Reference
    private ReportService reportService;

    @Reference
    private MemberService memberService;

    @Reference
    private SetmealService setmealService;

    @RequestMapping("/exportBusinessReport")
    public Result exportBusinessReport(HttpServletRequest request , HttpServletResponse response){
        try {
            Map<String, Object> result = reportService.getBusinessReportData();
            String reportDate = (String) result.get("reportDate");
            Integer todayNewMember = (Integer) result.get("todayNewMember");
            Integer totalMember = (Integer) result.get("totalMember");
            Integer thisWeekNewMember = (Integer) result.get("thisWeekNewMember");
            Integer thisMonthNewMember = (Integer) result.get("thisMonthNewMember");
            Integer todayOrderNumber = (Integer) result.get("todayOrderNumber");
            Integer thisWeekOrderNumber = (Integer) result.get("thisWeekOrderNumber");
            Integer thisMonthOrderNumber = (Integer) result.get("thisMonthOrderNumber");
            Integer todayVisitsNumber = (Integer) result.get("todayVisitsNumber");
            Integer thisWeekVisitsNumber = (Integer) result.get("thisWeekVisitsNumber");
            Integer thisMonthVisitsNumber = (Integer) result.get("thisMonthVisitsNumber");
            List<Map> hotSetmeal = (List<Map>) result.get("hotSetmeal");

            // 找到excel表格的路径 template/excle表格的名称
            String path =  request.getSession().getServletContext().getRealPath("template") +
                    File.separator + "report_template.xlsx";

            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(path)));
            XSSFSheet sheetAt = workbook.getSheetAt(0);
            XSSFRow row = sheetAt.getRow(2);
            row.getCell(5).setCellValue(reportDate);
            row = sheetAt.getRow(5);
            row.getCell(5).setCellValue(todayNewMember);
            row.getCell(7).setCellValue(totalMember);

            ServletOutputStream out = response.getOutputStream();
            // 下载的数据类型（excel类型）
            response.setContentType("application/vnd.ms-excel");
            // 设置下载形式(通过附件的形式下载)
            response.setHeader("content-Disposition", "attachment;filename=report.xlsx");
            workbook.write(out);

            out.flush();
            out.close();
            workbook.close();




        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping("/getBusinessReportData")
    public Result getBusinessReportData(){
       Map<String,Object> map = reportService.getBusinessReportData();
       return new Result(true,MessageConstant.GET_SETMEAL_LIST_SUCCESS,map);
    }

    /**
     * res.data.data.setmealNames：['澳门威尼斯人商圈酒店双飞3-5日自由行套餐', '香港九龙尖沙咀商圈双飞3-8日自由行套餐', '联盟广告', '视频广告', '搜索引擎']
     * res.data.data.setmealCount：[
     {value: 335, name: '澳门威尼斯人商圈酒店双飞3-5日自由行套餐'},
     {value: 310, name: '香港九龙尖沙咀商圈双飞3-8日自由行套餐'},
     {value: 234, name: '联盟广告'},
     {value: 135, name: '视频广告'},
     {value: 1548, name: '搜索引擎'}
     ],
     */
    @RequestMapping("/getSetmealReport")
    public Result getSetmealReport(){
        // 查询套餐的名称
        List<Map<String,Object>> lists =   setmealService.findSetmealCount();
        Map<String, Object> map = new HashMap<>();
        List<String> setmealNames = new ArrayList<>();
        for (Map<String, Object> list : lists) {
            String name = (String) list.get("name");
            setmealNames.add(name);
        }
        map.put("setmealNames",setmealNames);
        map.put("setmealCount",lists);
        return new Result(true,MessageConstant.GET_SETMEAL_COUNT_REPORT_SUCCESS,map);

    }

    /**
     * res.data.data.months:["2020-09","2020-10","2020-11","2020-12","2021-01","2021-02"]
     * res.data.data.memberCount:[5, 20, 36, 10, 10, 20]
     */
    @RequestMapping("/getMemberReport")
    public Result getMemberReport(){
        // 获取过去12个月，每个月注册的会员数量
        // 获取日期对象 年月日
        Calendar calendar = Calendar.getInstance();
        //  往前面推12个月
        calendar.add(Calendar.MONTH,-12);
        List<String> lists = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            // 一个月一个月的添加
            calendar.add(Calendar.MONTH,1);
            lists.add(new SimpleDateFormat("yyyy-MM").format(calendar.getTime()));
        }
        Map<String, Object> map = new HashMap<>();
        map.put("months",lists);
        List<Integer> memberCount =  memberService.findMemberCount(lists);
        map.put("memberCount",memberCount);
        return new Result(true, MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS,map);
    }
}