package com.xenos.countryfinder;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ExcelReader {

    private List<String> countriesNames;
    private List<String> countriesCodes;

    public List<String> getCountriesNames() {
        return countriesNames;
    }

    public List<String> getCountriesCodes() {
        return countriesCodes;
    }

    void readXLSX(String filename) throws FileNotFoundException, IOException {

        FileInputStream fis = new FileInputStream(filename);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        countriesNames = new LinkedList<>();
        countriesCodes = new LinkedList<>();

        for (Row row : sheet) {
            String cell = row.getCell(0).getStringCellValue();
            countriesNames.add(cell);
        }

        for (Row row : sheet) {
            String prefix = null;
            String cell = String.valueOf(row.getCell(1));
            if (cell.endsWith(".0")) {
                prefix = cell.replace(".0", "");
                countriesCodes.add(prefix);
            } else {
                countriesCodes.add(cell);
            }
        }
        workbook.close();
        fis.close();
    }
}
