package utils;

public class StepTracker {
    private static ThreadLocal<String> currentStep = new ThreadLocal<>();

    public static void setStep(String step) {
        currentStep.set(step);
    }

    public static String getStep() {
        return currentStep.get();
    }
}
