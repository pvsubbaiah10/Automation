package utils;

public class ExcelDataStore {

    // Excel file path
    private static String testDataFilePath;

    // Login related data
    private static String excelUsername;
    private static String excelPassword;

    // URL related data
    private static String excelUrl;

    //================= File Path =================
    public static String getTestDataFilePath() {
        return testDataFilePath;
    }

    public static void setTestDataFilePath(String testDataFilePath) {
        ExcelDataStore.testDataFilePath = testDataFilePath;
    }

    //================= Username =================
    public static String getExcelUsername() {
        return excelUsername;
    }

    public static void setExcelUsername(String excelUsername) {
        ExcelDataStore.excelUsername = excelUsername;
    }

    //================= Password =================
    public static String getExcelPassword() {
        return excelPassword;
    }

    public static void setExcelPassword(String excelPassword) {
        ExcelDataStore.excelPassword = excelPassword;
    }

    //================= URL =================
    public static String getExcelUrl() {
        return excelUrl;
    }

    public static void setExcelUrl(String excelUrl) {
        ExcelDataStore.excelUrl = excelUrl;
    }

    //================= Reset Data (Optional Utility) =================
    public static void resetExcelData() {
        excelUsername = null;
        excelPassword = null;
        excelUrl = null;
    }
}
