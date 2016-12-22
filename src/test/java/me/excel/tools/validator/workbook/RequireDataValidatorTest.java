package me.excel.tools.validator.workbook;

import me.excel.tools.model.excel.ExcelCellBean;
import me.excel.tools.model.excel.ExcelRowBean;
import me.excel.tools.model.excel.ExcelSheetBean;
import me.excel.tools.model.excel.ExcelWorkbookBean;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by hanwen on 2016/12/22.
 */
public class RequireDataValidatorTest {

  @Test
  public void testValidate() throws Exception {

    RequireDataValidator requireDataValidator = new RequireDataValidator();

    ExcelWorkbookBean workbook = new ExcelWorkbookBean();
    ExcelSheetBean sheet = new ExcelSheetBean();
    workbook.addSheet(sheet);
    ExcelRowBean row = new ExcelRowBean(1);
    ExcelRowBean row1 = new ExcelRowBean(2);
    ExcelRowBean row2 = new ExcelRowBean(3);
    sheet.addRow(row);
    sheet.addRow(row1);
    sheet.addRow(row2);

    row.addCell(new ExcelCellBean(1, 1, null, "person.name"));
    row.addCell(new ExcelCellBean(1, 2, null, "person.age"));
    row.addCell(new ExcelCellBean(1, 3, null, "person.birthday"));

    row1.addCell(new ExcelCellBean(2, 1, null, "person.name"));
    row1.addCell(new ExcelCellBean(2, 2, null, "person.age"));
    row1.addCell(new ExcelCellBean(2, 3, null, "person.birthday"));

    assertFalse(requireDataValidator.validate(workbook));
  }

}