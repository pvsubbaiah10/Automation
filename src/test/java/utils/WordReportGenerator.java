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
	private static final Map<String, Set<String>> scenarioTracker = new HashMap<>();

	public static void init(String featureName) {
		if (!docMap.containsKey(featureName)) {
			XWPFDocument doc = new XWPFDocument();
			docMap.put(featureName, doc);
			passCount.put(featureName, 0);
			failCount.put(featureName, 0);
			scenarioTracker.put(featureName, new HashSet<>());

			// Use single Date object for consistent folder and timestamp
			Date now = new Date();

			// Date folder (one folder per execution)
			String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(now);
			dateFolderMap.put(featureName, dateFolder);

			Path reportDir = Paths.get("word report", dateFolder);
			try {
				Files.createDirectories(reportDir);
			} catch (IOException e) {
				e.printStackTrace();
			}

			// Report file with time-based unique name
			String timestamp = new SimpleDateFormat("hh-mm-ssa").format(now); // e.g., 09-27-PM
			Path reportFile = reportDir.resolve(featureName + "_" + timestamp + ".docx");
			fileMap.put(featureName, reportFile);
		}
	}

	public static void setScenario(String featureName, String scenarioName) {
		if (docMap.containsKey(featureName)) {
			currentScenarioMap.put(featureName, scenarioName);
		}
	}

	public static void addStep(String featureName, String stepName, String status, String screenshotPath) {
		XWPFDocument doc = docMap.get(featureName);
		if (doc == null)
			return;

		String scenarioName = currentScenarioMap.get(featureName);
		Set<String> seenScenarios = scenarioTracker.get(featureName);

		if (!seenScenarios.contains(scenarioName)) {
			XWPFParagraph scenarioPara = doc.createParagraph();
			XWPFRun run = scenarioPara.createRun();
			run.setBold(true);
			run.setFontSize(14);
			run.setText("Scenario: " + scenarioName);
			seenScenarios.add(scenarioName);
		}

		XWPFParagraph stepPara = doc.createParagraph();
		XWPFRun stepRun = stepPara.createRun();
		stepRun.setBold(true);
		stepRun.setText("Step: " + stepName + " - " + status);
		if (status.equalsIgnoreCase("PASS")) {
			stepRun.setColor("008000");
			passCount.put(featureName, passCount.get(featureName) + 1);
		} else {
			stepRun.setColor("FF0000");
			failCount.put(featureName, failCount.get(featureName) + 1);
		}

		if (screenshotPath != null && Files.exists(Paths.get(screenshotPath))) {
			try (InputStream pic = new FileInputStream(screenshotPath)) {
				XWPFParagraph imgPara = doc.createParagraph();
				XWPFRun imgRun = imgPara.createRun();
				imgRun.addBreak();
				imgRun.addPicture(pic, XWPFDocument.PICTURE_TYPE_PNG, screenshotPath, Units.toEMU(450),
						Units.toEMU(300));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
