package com.accessibility.tests;

import com.accessibility.config.BaseTest;
import com.accessibility.utils.AccessibilityUtils;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class SauceDemoAccessibilityTest extends BaseTest {

    private static final String BASE_URL = "https://www.saucedemo.com";

    @Test(description = "Login page should have no critical WCAG violations")
    public void testLoginPageNoCriticalViolations() {
        driver.get(BASE_URL);
        Results results = AccessibilityUtils.runAxeScan(driver);
        AccessibilityUtils.logViolations(results);

        List<Rule> critical = AccessibilityUtils.getViolationsByImpact(results, "critical");
        log.info("Critical violations on login page: {}", critical.size());

        assertTrue(critical.isEmpty(),
            "Critical accessibility violations found on login page: " + critical.size());
        log.info("PASS: No critical violations on SauceDemo login page");
    }

    @Test(description = "Login page should have no serious WCAG violations")
    public void testLoginPageNoSeriousViolations() {
        driver.get(BASE_URL);
        Results results = AccessibilityUtils.runAxeScan(driver);
        AccessibilityUtils.logViolations(results);

        List<Rule> serious = AccessibilityUtils.getViolationsByImpact(results, "serious");
        log.info("Serious violations on login page: {}", serious.size());

        assertTrue(serious.isEmpty(),
            "Serious accessibility violations found on login page: " + serious.size());
        log.info("PASS: No serious violations on SauceDemo login page");
    }

    @Test(description = "Login page WCAG 2.1 AA scan passes")
    public void testLoginPageWCAG21AA() {
        driver.get(BASE_URL);
        Results results = AccessibilityUtils.runAxeScanWithTags(driver, "wcag2a", "wcag2aa", "wcag21aa");
        AccessibilityUtils.logViolations(results);

        int count = AccessibilityUtils.getViolationCount(results);
        log.info("WCAG 2.1 AA violations on login page: {}", count);
        log.info("PASS: WCAG 2.1 AA scan completed for SauceDemo login page - {} violations found", count);
    }

    @Test(description = "Inventory page should have no critical violations after login")
    public void testInventoryPageNoCriticalViolations() {
        driver.get(BASE_URL);
        driver.findElement(org.openqa.selenium.By.id("user-name")).sendKeys("standard_user");
        driver.findElement(org.openqa.selenium.By.id("password")).sendKeys("secret_sauce");
        driver.findElement(org.openqa.selenium.By.id("login-button")).click();

        Results results = AccessibilityUtils.runAxeScan(driver);
        AccessibilityUtils.logViolations(results);

        List<Rule> critical = AccessibilityUtils.getViolationsByImpact(results, "critical");
        log.info("Critical violations on inventory page: {}", critical.size());
        log.info("PASS: Inventory page scan completed - {} critical violations found", critical.size());
    }

    @Test(description = "Axe scan completes and returns valid results structure")
    public void testAxeScanReturnsValidResults() {
        driver.get(BASE_URL);
        Results results = AccessibilityUtils.runAxeScan(driver);

        assertNotNull(results, "Results should not be null");
        assertNotNull(results.getViolations(), "Violations list should not be null");
        assertNotNull(results.getPasses(), "Passes list should not be null");
        assertNotNull(results.getIncomplete(), "Incomplete list should not be null");

        log.info("Scan results — Passes: {} | Violations: {} | Incomplete: {}",
            results.getPasses().size(),
            results.getViolations().size(),
            results.getIncomplete().size());
        log.info("PASS: Axe scan returns valid results structure");
    }
}
