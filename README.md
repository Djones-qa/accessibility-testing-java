# Accessibility Testing — Java

[![Accessibility Tests](https://github.com/Djones-qa/accessibility-testing-java/actions/workflows/accessibility-tests.yml/badge.svg)](https://github.com/Djones-qa/accessibility-testing-java/actions/workflows/accessibility-tests.yml)
[![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk)](https://openjdk.org/projects/jdk/17/)
[![Maven](https://img.shields.io/badge/Maven-3.8+-blue?logo=apachemaven)](https://maven.apache.org/)
[![Selenium](https://img.shields.io/badge/Selenium-4.18.1-green?logo=selenium)](https://www.selenium.dev/)
[![axe--core](https://img.shields.io/badge/axe--core-4.9.1-purple)](https://github.com/dequelabs/axe-core-maven-html)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Automated accessibility testing suite using [axe-core](https://github.com/dequelabs/axe-core-maven-html), Selenium WebDriver, and TestNG.

## Project Structure

```
src/test/java/com/accessibility/
├── config/
│   └── BaseTest.java          # WebDriver setup/teardown (headless Chrome)
├── tests/
│   ├── SauceDemoAccessibilityTest.java
│   └── DemoQAAccessibilityTest.java
└── utils/
    └── AccessibilityUtils.java  # axe-core scan helpers
```

## Prerequisites

- Java 17+
- Maven 3.8+
- Google Chrome installed

## Running the Tests

```bash
mvn test
```

This picks up `testng.xml` and runs both test suites.

## Test Suites

| Suite | URL | What it checks |
|---|---|---|
| SauceDemo | https://www.saucedemo.com | Login & inventory pages — critical/serious violations, WCAG 2.1 AA |
| DemoQA | https://demoqa.com | Home, forms, alerts, widgets, books pages — critical violations, WCAG 2.0 A |

## Key Utilities

- `AccessibilityUtils.runAxeScan(driver)` — full page scan
- `AccessibilityUtils.runAxeScanWithTags(driver, "wcag2a", "wcag2aa")` — tag-filtered scan
- `AccessibilityUtils.getViolationsByImpact(results, "critical")` — filter by impact level
- `AccessibilityUtils.logViolations(results)` — log all violations

## Dependencies

| Library | Version |
|---|---|
| Selenium | 4.18.1 |
| WebDriverManager | 5.7.0 |
| axe-core selenium | 4.9.1 |
| TestNG | 7.9.0 |
| Log4j2 | 2.23.1 |
