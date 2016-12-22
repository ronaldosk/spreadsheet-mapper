package me.excel.tools.validator.workbook;


import me.excel.tools.model.excel.ExcelCell;
import me.excel.tools.model.excel.ExcelWorkbook;

import java.util.Collections;
import java.util.List;

/**
 * sheet size validator
 * <p>
 * Created by hanwen on 4/26/16.
 */
public class SheetSizeValidator implements WorkbookValidator {

  private int size;

  public SheetSizeValidator(int size) {
    this.size = size;
  }

  @Override
  public String getErrorMessage() {
    return "只支持单sheet导入";
  }

  @Override
  public List<ExcelCell> getCausedByCells(ExcelWorkbook excelWorkbook) {
    return Collections.singletonList(excelWorkbook.getFirstSheet().getRow(1).getCell(1));
  }

  @Override
  public boolean validate(ExcelWorkbook excelWorkbook) {
    if (excelWorkbook == null) {
      throw new IllegalArgumentException("workbook is null");
    }
    if (excelWorkbook.sizeOfSheets() == 0) {
      throw new IllegalArgumentException("sheet is null");
    }
    return excelWorkbook.sizeOfSheets() == size;
  }
}
