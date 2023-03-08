package com.shopproject.dataprovider;

import com.shopproject.utility.NewExcelLibrary;
import org.testng.annotations.DataProvider;

/**
 * @author Hitendra
 *
 */
public class DataProviders {

    NewExcelLibrary obj = new NewExcelLibrary();

//Class --> LoginPageTest  Test Case--> testValidLogin, testInvalidLogin

    @DataProvider(name = "credentials")
    public Object[][] getCredentials() {
        // Totals rows count
        int rows = obj.getRowCount("LoginData");
        // Total Columns
        int column = obj.getColumnCount("LoginData");
        int actRows = rows - 1;

        Object[][] data = new Object[actRows][column];

        for (int i = 0; i < actRows; i++) {
            for (int j = 0; j < column; j++) {
                data[i][j] = obj.getCellData("LoginData", j, i + 2);
            }
        }
        return data;
    }

    // Class --> RegisterPageTest, Test Case--> testRegisterAccount
    @DataProvider(name = "signUp")
    public Object[][] getProductPrice() {
        // Totals rows count
        int rows = obj.getRowCount("SignUpData");
        // Total Columns
        int column = obj.getColumnCount("SignUpData");
        int actRows = rows - 1;

        Object[][] data = new Object[actRows][column];

        for (int i = 0; i < actRows; i++) {
            for (int j = 0; j < column; j++) {
                data[i][j] = obj.getCellData("SignUpData", j, i + 2);
            }
        }
        return data;
    }
}
