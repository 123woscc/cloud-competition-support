package cn.edu.seu.cloud.service;

import cn.edu.seu.cloud.dao.AwdDao;
import cn.edu.seu.cloud.domain.Awd;
import org.apache.poi.hssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Service
public class AwdService {

    @Autowired
    AwdDao awdDao;

    public void addAwdInfo(String id,String username, String idnum, String accountName,
                           String accountNumber, String coupletNumber, String telephone){
        awdDao.addAwdInfo(id,username,idnum, accountName, accountNumber, coupletNumber, telephone);
    }

    public List<Awd> getAllAwd() {
        return awdDao.selectAllAwd();
    }

    public Awd getAwdById(String id){
        return awdDao.selectAwdById(id);
    }


    public HSSFWorkbook getExcelFile() throws IOException {
        HSSFWorkbook hwb = new HSSFWorkbook();//第一步，创建一个workbook（一个excel文件）
        HSSFSheet hs = hwb.createSheet("奖金信息");//第二步，在workbook中添加一个sheet，对应excel文件中sheet
        HSSFRow hr = hs.createRow(0);//第三部，在sheet中添加表头第0行（相当于解释字段）
        HSSFCellStyle hcs = hwb.createCellStyle();//第四步，设置第0行（表头）居中

        //将表头的字段放入数组当中
        String [] excelHeader = {"姓名（必填）","证件类型（必填）",
                "证件号码（必填）","性别","国籍","来华时间","人员类型",
                "人员性质（必填）","卡类型（必填）",
                "账户名称（必填）","银行账号（必填）","联行号（必填）","状态","电话号码",
                "家庭住址","邮政编码",
                "户籍地址","职称","工作单位","职务","出生日期"};
        for (int i = 0; i < excelHeader.length; i++) {
            HSSFCell hc = hr.createCell(i);//顺序创建
            hc.setCellValue(excelHeader[i]);//顺序塞入
            hc.setCellStyle(hcs);//居中
            hs.autoSizeColumn((short) i);//设置 i 这一列为自动调整列宽
        }

        List<Awd> awdlist = awdDao.selectAllAwd();//查找全部实体属性字段
        for (int i = 0; i < awdlist.size(); i++) {
            hr = hs.createRow(i+1);//在sheet中自动随 i+1 增加一行（i 是表头）

            Awd awd = awdlist.get(i);

            hr.createCell(0).setCellValue(awd.getUsername());
            hr.createCell(1).setCellValue("身份证");
            hr.createCell(2).setCellValue(awd.getIdnum());
            hr.createCell(3).setCellValue("");
            hr.createCell(4).setCellValue("中国");
            hr.createCell(5).setCellValue("");
            hr.createCell(6).setCellValue("");
            hr.createCell(7).setCellValue("外聘人员");
            hr.createCell(8).setCellValue("校外人员卡");
            hr.createCell(9).setCellValue(awd.getAccountName());
            hr.createCell(10).setCellValue(awd.getAccountNumber());
            hr.createCell(11).setCellValue(awd.getCoupletNumber());
            hr.createCell(12).setCellValue("");
            hr.createCell(13).setCellValue(awd.getTelephone());
            hr.createCell(14).setCellValue("");
            hr.createCell(15).setCellValue("");
            hr.createCell(16).setCellValue("");
            hr.createCell(17).setCellValue("");
            hr.createCell(18).setCellValue("");
            hr.createCell(19).setCellValue("");
            hr.createCell(20).setCellValue("");

        }
        return hwb;
    }

}

