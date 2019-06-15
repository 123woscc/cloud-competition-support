package cn.edu.seu.cloud.service;

import cn.edu.seu.cloud.dao.MailInfoDao;
import cn.edu.seu.cloud.dao.ResultDao;
import cn.edu.seu.cloud.dao.UserDao;
import cn.edu.seu.cloud.domain.MailInfo;
import cn.edu.seu.cloud.domain.Result;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by hhzhang on 2018/4/21.
 */
@Service
public class MailInfoService {

    @Autowired
    private MailInfoDao mailInfoDao;
    @Autowired
    private ResultDao resultDao;
    @Autowired
    private UserDao userDao;

    public void addMailInfo(String uid, String name, String telephone, String backlog, String address) {
        mailInfoDao.insertMailInfo(uid, name, telephone, backlog, address);
    }

    public List<MailInfo> getAllMailInfo() {
        return mailInfoDao.selectAllMailInfo();
    }

    public MailInfo getMailInfoByUid(String uid) {
        return mailInfoDao.selectMailInfoByUid(uid);
    }

    public HSSFWorkbook exportToExcel() throws IOException {
        HSSFWorkbook hwb = new HSSFWorkbook();//第一步，创建一个workbook（一个excel文件）
        HSSFSheet hs = hwb.createSheet("地址信息");//第二步，在workbook中添加一个sheet，对应excel文件中sheet
        HSSFRow hr = hs.createRow(0);//第三部，在sheet中添加表头第0行（相当于解释字段）
        HSSFCellStyle hcs = hwb.createCellStyle();//第四步，设置第0行（表头）居中

//        //将表头的字段放入数组当中
        String[] excelHeader = {"收件人姓名*", "收件人联系电话*", "收件人公司", "省*", "市*", "区", "详细地址*",
                "托寄物内容*", "重量\n（不填默认为1）", "数量\n（不填默认为1）", "件数\n（不填默认为1）", "快递产品*",
                "付款方式*", "月结卡号", "是否保价", "声明价值", "是否自取", "行业型增值服务", "备注1", "备注2", "备注3", "证书数量"};
        for (int i = 0; i < excelHeader.length; i++) {
            HSSFCell hc = hr.createCell(i);
            hc.setCellValue(excelHeader[i]);
            hc.setCellStyle(hcs);
            hs.autoSizeColumn((short) i);
        }

        List<MailInfo> mailInfos = mailInfoDao.selectAllMailInfo();
        for (int i = 0; i < mailInfos.size(); i++) {
            hr = hs.createRow(i + 1);//在sheet中自动随 i+1 增加一行（i 是表头）

            MailInfo mailInfo = mailInfos.get(i);

            hr.createCell(0).setCellValue(mailInfo.getName());
            hr.createCell(1).setCellValue(mailInfo.getTelephone());
            hr.createCell(2).setCellValue("");
            hr.createCell(5).setCellValue("");
            hr.createCell(6).setCellValue(mailInfo.getAddress());
            hr.createCell(7).setCellValue("文件");
            hr.createCell(8).setCellValue("");
            hr.createCell(9).setCellValue("");
            hr.createCell(10).setCellValue("");
            hr.createCell(11).setCellValue("顺丰隔日");
            hr.createCell(12).setCellValue("寄付现结");
            hr.createCell(13).setCellValue("");
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
