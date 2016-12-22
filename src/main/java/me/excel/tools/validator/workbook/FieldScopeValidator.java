package me.excel.tools.validator.workbook;

import me.excel.tools.ExcelConstants;
import me.excel.tools.FieldUtils;
import me.excel.tools.model.excel.ExcelCell;
import me.excel.tools.model.excel.ExcelWorkbook;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * field scope validator
 * <p>
 * Created by hanwen on 4/26/16.
 */
public class FieldScopeValidator implements WorkbookValidator {

  private List<String> fieldScopes = new ArrayList<>();

  private ExcelWorkbook excelWorkbook;

  public FieldScopeValidator(List<String> fieldScopes) {
    this.fieldScopes = fieldScopes;
  }

  @Override
  public String getErrorMessage() {
    if (this.excelWorkbook == null) {
      return ExcelConstants.EMPTY_VALUE;
    }
    return "以下字段不在处理范围内:" + StringUtils.join(getOutDealFields(this.excelWorkbook), ExcelConstants.SEPARATOR);
  }

  @Override
  public List<ExcelCell> getCausedByCells(ExcelWorkbook excelWorkbook) {
    return Collections.singletonList(excelWorkbook.getFirstSheet().getRow(1).getCell(1));
  }

  @Override
  public boolean validate(ExcelWorkbook excelWorkbook) {
    boolean result = getOutDealFields(excelWorkbook).isEmpty();
    if (!result) {
      this.excelWorkbook = excelWorkbook;
      return false;
    }
    return true;
  }

  private List<String> getOutDealFields(ExcelWorkbook excelWorkbook) {

    List<String> keyRowFields = excelWorkbook.getSheet(1).getKeyRowFields();
    return keyRowFields.stream()
        .filter(keyRowField -> !fieldScopes.contains(keyRowField) && !StringUtils.contains(keyRowField, FieldUtils.BUSINESS_KEY_PREFIX))
        .collect(Collectors.toList());
  }
}
