package utils;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.nio.file.*;


public class WordReportGenerator {

    private static XWPFDocument document;
    private static String featureName;
    private static Path reportPath;

    private static int totalSteps = 0;
    private static int passedSteps = 0;
    private static int failedSteps = 0;

    private static boolean isInitialized = false;

    public static void init(String feature) {
        featureName = feature;

        try {
            Path reportDir = Paths.get("word report");
            if (!Files.exists(reportDir)) {
                Files.createDirectories(reportDir);
            }

            reportPath = reportDir.resolve(featureName + ".docx");

            if (Files.exists(reportPath)) {
                // Append to existing doc
                FileInputStream fis = new FileInputStream(reportPath.toFile());
                document = new XWPFDocument(fis);
                fis.close();

                // To calculate previous stats, optional: Could be implemented
            } else {
                document = new XWPFDocument();

                // Add Title Heading
                XWPFParagraph title = document.createParagraph();
                title.setAlignment(ParagraphAlignment.CENTER);
                XWPFRun run = title.createRun();
                run.setText("Execution Report for Feature: " + featureName);
                run.setBold(true);
                run.setFontSize(16);
            }

            isInitialized = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addStep(String stepText, String status, String screenshotPath) {
        if (!isInitialized) return;

        totalSteps++;

        XWPFParagraph p = document.createParagraph();
        XWPFRun run = p.createRun();
        run.setText("Step " + totalSteps + ": " + stepText + " - [" + status + "]");
        run.setFontSize(12);

        if ("FAIL".equalsIgnoreCase(status)) {
            failedSteps++;
            run.setColor("FF0000");  // Red color for fail
            run.setBold(true);
        } else {
            passedSteps++;
        }

        // Add screenshot if exists
        if (screenshotPath != null && !screenshotPath.isEmpty()) {
            try (InputStream pic = new FileInputStream(screenshotPath)) {
                String filename = Paths.get(screenshotPath).getFileName().toString();
                int format = filename.endsWith(".png") ? XWPFDocument.PICTURE_TYPE_PNG : XWPFDocument.PICTURE_TYPE_JPEG;

                // Add line break before picture
                document.createParagraph().createRun().addBreak();

                XWPFParagraph picParagraph = document.createParagraph();
                picParagraph.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun picRun = picParagraph.createRun();
                picRun.addPicture(pic, format, filename, Units.toEMU(400), Units.toEMU(200)); // width, height in EMUs

                // Add line break after picture
                picRun.addBreak();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveReport() throws IOException {
        if (!isInitialized) return;

        // Add summary at the end
        XWPFParagraph summaryPara = document.createParagraph();
        summaryPara.setSpacingBefore(500);
        XWPFRun summaryRun = summaryPara.createRun();
        summaryRun.addBreak();
        summaryRun.setBold(true);
        summaryRun.setFontSize(14);
        summaryRun.setText("Summary:");
        summaryRun.addBreak();
        summaryRun.setText("Total Steps: " + totalSteps);
        summaryRun.addBreak();
        summaryRun.setText("Passed Steps: " + passedSteps);
        summaryRun.addBreak();
        summaryRun.setText("Failed Steps: " + failedSteps);

        try (FileOutputStream fos = new FileOutputStream(reportPath.toFile())) {
            document.write(fos);
        }
        document.close();
        isInitialized = false;
    }
}
