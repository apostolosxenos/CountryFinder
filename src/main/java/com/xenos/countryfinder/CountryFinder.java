package com.xenos.countryfinder;

import java.io.IOException;
import java.util.Map.Entry;
import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.Map;


public class CountryFinder {

    static Map<String, String> countriesNamesAndCodes;
    private static final String NO_COUNTRY_FOUND = "Country is not found!";

    public static void main(String[] args) throws IOException {

        ExcelReader excelReader = new ExcelReader();
        excelReader.readXLSX("CountriesCodesList.xlsx");

        countriesNamesAndCodes = new HashMap<>();

        for (int i = 0; i < excelReader.getCountriesNames().size(); i++) {
            countriesNamesAndCodes.put(excelReader.getCountriesNames().get(i), 
                    excelReader.getCountriesCodes().get(i));
        }
                
        Map<String, String> sortedMap = MapUtilizer.sortByValueDesc(countriesNamesAndCodes);

        String input = String.valueOf(JOptionPane.showInputDialog("Type a telephone number with prefix:"));
        String country = getCountry(input,sortedMap);
        System.out.println(input+" belongs to "+country);

    }

    private static String removePlusPrefix(String telephoneNumber) {
        return telephoneNumber.replace("+", "");
    }

    private static String removeDoubleZeroPrefix(String telephoneNumber) {
        return telephoneNumber.replace("00", "");
    }

    private static String getCountry(String telephoneNumber, Map<String, String> map) {

        String fixedNumber = telephoneNumber;

        if (telephoneNumber.startsWith("+")) {
            fixedNumber = removePlusPrefix(telephoneNumber);
        } else if (telephoneNumber.startsWith("00")) {
            fixedNumber = removeDoubleZeroPrefix(telephoneNumber);
        }

        for (Entry<String, String> entry : map.entrySet()) {

            if (fixedNumber.startsWith(entry.getValue())) {
                return (entry.getKey());
            }
        }
        return NO_COUNTRY_FOUND;
    }
}