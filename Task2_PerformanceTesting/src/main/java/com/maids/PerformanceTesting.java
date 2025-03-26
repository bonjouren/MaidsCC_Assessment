package com.maids;// Task 2: Performance Testing Exercise

import java.util.*;

public class PerformanceTesting {

    public static void main(String[] args) {
        identifyOptimalTestCases();
    }

    private static void identifyOptimalTestCases() {
        // Functionality mapping for each test case
        Map<String, Set<String>> testCases = new HashMap<>();
        testCases.put("TC1", new HashSet<>(Arrays.asList("F1", "F2")));
        testCases.put("TC2", new HashSet<>(Arrays.asList("F2", "F3")));
        testCases.put("TC3", new HashSet<>(Collections.singletonList("F3")));
        testCases.put("TC4", new HashSet<>(Arrays.asList("F4", "F5")));
        testCases.put("TC5", new HashSet<>(Arrays.asList("F1", "F5", "F6")));
        testCases.put("TC6", new HashSet<>(Arrays.asList("F6", "F4")));

        // Find the best pair of test cases covering the most functionalities
        int maxCoverage = 0;
        String bestPair = "";
        Set<String> allFunctionalities = new HashSet<>(Arrays.asList("F1", "F2", "F3", "F4", "F5", "F6"));

        for (String tc1 : testCases.keySet()) {
            for (String tc2 : testCases.keySet()) {
                if (!tc1.equals(tc2)) {
                    Set<String> combined = new HashSet<>(testCases.get(tc1));
                    combined.addAll(testCases.get(tc2));
                    if (combined.size() > maxCoverage) {
                        maxCoverage = combined.size();
                        bestPair = tc1 + " + " + tc2;
                    }
                }
            }
        }

        System.out.println("Optimal Test Cases: " + bestPair);
        System.out.println("Functionalities Covered: " + maxCoverage);

        // Identify uncovered functionalities
        Set<String> covered = new HashSet<>();
        for (String tc : bestPair.split(" \\+ ")) {
            covered.addAll(testCases.get(tc));
        }

        allFunctionalities.removeAll(covered);
        System.out.println("Uncovered Functionalities: " + (allFunctionalities.isEmpty() ? "None" : allFunctionalities));
    }
}