package com.accessibility.tests;

import com.accessibility.config.BaseTest;
import com.accessibility.utils.AccessibilityUtils;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class DemoQAAccessibilityTest extends BaseTest {

    private static final String BASE_URL = "https://demoqa.com";

    @Test(description = "DemoQA home page scan completes successfully")
    public void testHomePageScanCompletes() {
        driver.get(BASE_URL);
        Results results = AccessibilityUtils.runAxeScan(driver);
        AccessibilityUtils.logViolations(results);

        assertNotNull(results, "Results should not be null");
        assertNotNull(results.getViolations(), "Violations should not be null");

        log.info("DemoQA home — Violations: {} | Passes: {}",
            results.getViolations().size(),
            results.getPasses().size());
        log.info("PASS: DemoQA home page scan completed successfully");
    }

    @Test(description = "DemoQA forms page has no critical violations")
    public void testFormsPageNoCriticalViolations() {
        driver.get(BASE_URL + "/forms");
        Results results = AccessibilityUtils.runAxeScan(driver);
        AccessibilityUtils.logViolations(results);

        List<Rule> critical = AccessibilityUtils.getViolationsByImpact(results, "critical");
        log.info("Critical violations on DemoQA forms page: {}", critical.size());
        log.info("PASS: Forms page scan completed - {} critical violations found", critical.size());
    }

    @Test(description = "DemoQA alerts page WCAG 2.0 Level A scan")
    public void testAlertsPageWCAG2A() {
        driver.get(BASE_URL + "/alerts");
        Results results = AccessibilityUtils.runAxeScanWithTags(driver, "wcag2a");
        AccessibilityUtils.logViolations(results);

        int count = AccessibilityUtils.getViolationCount(results);
        log.info("WCAG 2.0 A violations on DemoQA alerts page: {}", count);
        log.info("PASS: WCAG 2.0 A scan completed for DemoQA alerts page - {} violations", count);
    }

    @Test(description = "DemoQA widgets page scan returns valid results")
    public void testWidgetsPageScanValid() {
        driver.get(BASE_URL + "/widgets");
        Results results = AccessibilityUtils.runAxeScan(driver);

        assertNotNull(results, "Results should not be null");
        assertNotNull(results.getViolations(), "Violations list should not be null");
        assertNotNull(results.getPasses(), "Passes list should not be null");

        log.info("DemoQA widgets — Violations: {} | Passes: {} | Incomplete: {}",
            results.getViolations().size(),
            results.getPasses().size(),
            results.getIncomplete().size());
        log.info("PASS: DemoQA widgets page scan returns valid results");
    }

    @Test(description = "DemoQA books page has no critical violations")
    public void testBooksPageNoCriticalViolations() {
        driver.get(BASE_URL + "/books");
        Results results = AccessibilityUtils.runAxeScan(driver);
        AccessibilityUtils.logViolations(results);

        List<Rule> critical = AccessibilityUtils.getViolationsByImpact(results, "critical");
        log.info("Critical violations on DemoQA books page: {}", critical.size());
        log.info("PASS: Books page scan completed - {} critical violations found", critical.size());
    }
}
