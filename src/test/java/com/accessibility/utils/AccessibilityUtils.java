package com.accessibility.utils;

import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AccessibilityUtils {

    private static final Logger log = LogManager.getLogger(AccessibilityUtils.class);

    private AccessibilityUtils() {}

    /** Run a full axe-core scan on the current page. */
    public static Results runAxeScan(WebDriver driver) {
        return new AxeBuilder().analyze(driver);
    }

    /** Run an axe-core scan filtered to specific WCAG tag(s), e.g. "wcag2a", "wcag2aa". */
    public static Results runAxeScanWithTags(WebDriver driver, String... tags) {
        return new AxeBuilder()
                .withTags(Arrays.asList(tags))
                .analyze(driver);
    }

    /** Return violations filtered by impact level (critical, serious, moderate, minor). */
    public static List<Rule> getViolationsByImpact(Results results, String impact) {
        if (results == null || results.getViolations() == null) {
            return Collections.emptyList();
        }
        return results.getViolations().stream()
                .filter(rule -> impact.equalsIgnoreCase(rule.getImpact()))
                .collect(Collectors.toList());
    }

    /** Return total number of violations. */
    public static int getViolationCount(Results results) {
        if (results == null || results.getViolations() == null) {
            return 0;
        }
        return results.getViolations().size();
    }

    /** Log all violations to the console/log output. */
    public static void logViolations(Results results) {
        if (results == null || results.getViolations() == null || results.getViolations().isEmpty()) {
            log.info("No violations found.");
            return;
        }
        log.warn("Total violations: {}", results.getViolations().size());
        for (Rule rule : results.getViolations()) {
            log.warn("  [{}] {} — impact: {} | help: {}",
                    rule.getId(),
                    rule.getDescription(),
                    rule.getImpact(),
                    rule.getHelpUrl());
        }
    }
}
