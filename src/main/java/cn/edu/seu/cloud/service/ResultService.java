package cn.edu.seu.cloud.service;
import cn.edu.seu.cloud.dao.ResultDao;
import cn.edu.seu.cloud.domain.Result;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hhzhang on 2018/4/21.
 */
@Service
public class ResultService {

    @Autowired
    private ResultDao resultDao;

    public void addResult(InputStream inputStream) throws IOException, InvalidFormatException {
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        List<List<String>> lists = new ArrayList<List<String>>();
        for (Row row: sheet) {
            ArrayList<String> excelResult = new ArrayList<String>();
            for (Cell cell: row) {
                cell.setCellType(Cell.CELL_TYPE_STRING);
                excelResult.add(cell.getStringCellValue());
            }
            lists.add(excelResult);
        }
        parseExcelAndSave(lists);
    }

    private void parseExcelAndSave(List<List<String>> arrayLists) {
        for (int i = 0; i < arrayLists.size(); i++) {
            if (arrayLists.get(i).get(0).isEmpty())
                continue;
            List<String> stringList = arrayLists.get(i);
            resultDao.insertResult(stringList.get(0).substring(2), stringList.get(1),
                    stringList.get(2), stringList.get(3), stringList.get(4), stringList.get(5),
                    stringList.get(6), stringList.get(7), stringList.get(8), "0",
                    stringList.get(9), "0");
        }
    }

    public List<Result> getResultByTeamId(String teamId) {
        return resultDao.selectResultsByTeamId(teamId);
    }
}
