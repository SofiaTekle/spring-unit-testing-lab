package se.iths.sofia.springunittestinglab.e2e;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ATMControllerE2ETest {

    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    Page page;

    // starta Playwright + browser en gång per klass
    @BeforeAll
    static void setupBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }

    // stäng browser + Playwright en gång per klass
    @AfterAll
    static void closeBrowser() {
        browser.close();
        playwright.close();
    }

    // ny context + flik för varje test
    @BeforeEach
    void setup() {
        context = browser.newContext();
        page = context.newPage();
    }

    // stäng context efter varje test
    @AfterEach
    void teardown() {
        context.close();
    }

    // sidan går att nå
    @Test
    void balancePageShouldBeReachable() {
        page.navigate("http://localhost:8080/");
    }

    // sidan laddas korrekt
    @Test
    void balancePageShouldLoadCorrectly() {
        page.navigate("http://localhost:8080/");
        assertTrue(page.isVisible("h1"));
    }

    // saldot visas i HTML
    @Test
    void balanceShouldBeDisplayedInHtml() {
        page.navigate("http://localhost:8080/");
        String bodyText = page.textContent("body");
        assertTrue(bodyText.contains("0"));
    }


}
