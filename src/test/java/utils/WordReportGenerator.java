package utils;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class WordReportGenerator {

    private static final Map<String, XWPFDocument> docMap = new HashMap<>();
    private static final Map<String, String> currentScenarioMap = new HashMap<>();
    private static final Map<String, Integer> passCount = new HashMap<>();
    private static final Map<String, Integer> failCount = new HashMap<>();
    private static final Map<String, Path> fileMap = new HashMap<>();
    private static final Map<String, String> dateFolderMap = new HashMap<>();

    public static void init(String featureName) {
        if (!docMap.containsKey(featureName)) {
            XWPFDocument doc = new XWPFDocument();
            docMap.put(featureName, doc);
            passCount.put(featureName, 0);
            failCount.put(featureName, 0);

            // Create date-wise folder
            String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            Path reportDir = Paths.get("word report", dateFolder);
            try {
                Files.createDirectories(reportDir);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Save report path in map
            String timestamp = new SimpleDateFormat("HHmmss").format(new Date());
            Path reportFile = reportDir.resolve(featureName + "_" + timestamp + ".docx");
            fileMap.put(featureName, reportFile);
            dateFolderMap.put(featureName, dateFolder);
        }
    }

    public static void setScenario(String scenarioName) {
        for (String featureName : docMap.keySet()) {
            currentScenarioMap.put(featureName, scenarioName);
        }
    }

    public static void addStep(String stepName, String status, String screenshotPath) {
        for (Map.Entry<String, XWPFDocument> entry : docMap.entrySet()) {
            String featureName = entry.getKey();
            XWPFDocument doc = entry.getValue();
            String scenarioName = currentScenarioMap.get(featureName);

            // Scenario Heading (only once)
            if (!scenarioAlreadyLogged(doc, scenarioName)) {
                XWPFParagraph scenarioPara = doc.createParagraph();
                XWPFRun run = scenarioPara.createRun();
                run.setBold(true);
                run.setFontSize(14);
                run.setText("Scenario: " + scenarioName);
            }

            // Step
            XWPFParagraph stepPara = doc.createParagraph();
            XWPFRun stepRun = stepPara.createRun();
            stepRun.setBold(true);
            stepRun.setText("Step: " + stepName + " - " + status);
            if (status.equalsIgnoreCase("PASS")) {
                stepRun.setColor("008000"); // Green
                passCount.put(featureName, passCount.get(featureName) + 1);
            } else {
                stepRun.setColor("FF0000"); // Red
                failCount.put(featureName, failCount.get(featureName) + 1);
            }

            // Screenshot
            if (screenshotPath != null && Files.exists(Paths.get(screenshotPath))) {
                try (InputStream pic = new FileInputStream(screenshotPath)) {
                    XWPFParagraph imgPara = doc.createParagraph();
                    XWPFRun imgRun = imgPara.createRun();
                    imgRun.addBreak();
                    imgRun.addPicture(
                            pic,
                            XWPFDocument.PICTURE_TYPE_PNG,
                            screenshotPath,
                            Units.toEMU(450),
                            Units.toEMU(300)
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static boolean scenarioAlreadyLogged(XWPFDocument doc, String scenarioName) {
        for (XWPFParagraph para : doc.getParagraphs()) {
            if (para.getText() != null && para.getText().trim().equals("Scenario: " + scenarioName)) {
                return true;
            }
        }
        return false;
    }

    public static void saveReport() throws IOException {
        for (Map.Entry<String, XWPFDocument> entry : docMap.entrySet()) {
            String featureName = entry.getKey();
            XWPFDocument doc = entry.getValue();

            // Summary
            XWPFParagraph summary = doc.createParagraph();
            XWPFRun run = summary.createRun();
            run.setBold(true);
            run.setText("\nExecution Summary:");
            run.addBreak();
            run.setText("Total Steps: " + (passCount.get(featureName) + failCount.get(featureName)));
            run.addBreak();
            run.setText("Passed: " + passCount.get(featureName));
            run.addBreak();
            run.setText("Failed: " + failCount.get(featureName));

            // Save the file using pre-generated path
            Path reportFile = fileMap.get(featureName);
            try (FileOutputStream out = new FileOutputStream(reportFile.toFile())) {
                doc.write(out);
            }
        }
    }

    public static String getDateFolder(String featureName) {
        return dateFolderMap.getOrDefault(featureName, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }
}
