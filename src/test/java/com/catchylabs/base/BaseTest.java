package com.catchylabs.base;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.catchylabs.model.ElementInfo;
import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class BaseTest {

    protected static WebDriver driver;
    protected static Actions actions;
    protected Logger logger = LoggerFactory.getLogger(getClass());
    DesiredCapabilities capabilities;
    ChromeOptions chromeOptions;
    FirefoxOptions firefoxOptions;
    EdgeOptions edgeOptions;

    String browserName = "chrome";
    String selectPlatform = "mac";

    static File[] fileList = null;
    ConcurrentMap<String, Object> elementMapList;
    String currentWorkingDir = System.getProperty("user.dir");
    protected static ExtentReports extent;
    protected static ExtentTest test;

    @BeforeScenario
    public void setUp() {
        setupReport(); // ExtentReports'u başlat
        String baseUrl = "https://catchylabs-webclient.testinium.com/";
        logger.info("************************************  BeforeScenario  ************************************");
        if (StringUtils.isEmpty(System.getenv("key"))) {
            logger.info("Local cihazda " + selectPlatform + " ortamında " + browserName + " browserında test ayağa kalkacak");
            driver = initializeDriver(browserName);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            actions = new Actions(driver);
        }
        driver.get(baseUrl);
        test = extent.createTest("Test Started", "Navigated to: " + baseUrl);
        test.info("Browser and URL setup completed.");
    }

    public WebDriver initializeDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            return new ChromeDriver(chromeOptions());
        } else if (browser.equalsIgnoreCase("firefox")) {
            return new FirefoxDriver(firefoxOptions());
        } else if (browser.equalsIgnoreCase("edge")) {
            return new EdgeDriver(edgeOptions());
        }
        throw new IllegalArgumentException("Unsupported browser: " + browser);
    }

    public void setupReport() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/reportsExtent/ExtentReport.html";

            File reportDir = new File(System.getProperty("user.dir") + "/reportsExtent");
            if (!reportDir.exists()) {
                reportDir.mkdirs();
            }

            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setDocumentTitle("Automation Test Report");
            reporter.config().setReportName("CatchyLabs Automation");
            reporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Platform", selectPlatform);
            extent.setSystemInfo("Browser", browserName);

            logger.info("Extent Reports setup completed.");
        }
    }

    @AfterScenario
    public void tearDown() {
        if (driver != null) {
            test.info("Closing the browser.");
            driver.quit();
        }
        test.pass("Test completed successfully.");
        extent.flush(); // Raporu yaz
    }

    public void initMap(List<File> fileList) {
        elementMapList = new ConcurrentHashMap<>();
        Type elementType = new TypeToken<List<ElementInfo>>() {
        }.getType();
        Gson gson = new Gson();
        List<ElementInfo> elementInfoList = null;
        for (File file : fileList) {
            try {
                FileReader filez = new FileReader(file);
                elementInfoList = gson.fromJson(filez, elementType);
                elementInfoList.parallelStream()
                        .forEach(elementInfo -> elementMapList.put(elementInfo.getKey(), elementInfo));
            } catch (FileNotFoundException e) {
                logger.error("File not found: " + file.getName(), e);
                test.fail("File not found: " + file.getName());
            }
        }
    }

    public static List<File> getFileList(String directoryName) throws IOException {
        List<File> dirList = new ArrayList<>();
        try (Stream<Path> walkStream = Files.walk(Paths.get(directoryName))) {
            walkStream.filter(p -> p.toFile().isFile()).forEach(f -> {
                if (f.toString().endsWith(".json")) {
                    System.out.println(f.toFile().getName() + " json dosyası bulundu.");
                    dirList.add(f.toFile());
                }
            });
        }
        return dirList;
    }

    public ChromeOptions chromeOptions() {
        chromeOptions = new ChromeOptions();
        capabilities = DesiredCapabilities.chrome();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        chromeOptions.setExperimentalOption("prefs", prefs);
        chromeOptions.addArguments("--kiosk");
        chromeOptions.addArguments("--disable-notifications");
        System.setProperty("webdriver.chrome.driver", "web_driver/chromedriver");
        chromeOptions.merge(capabilities);
        return chromeOptions;
    }

    public EdgeOptions edgeOptions() {
        edgeOptions = new EdgeOptions();
        capabilities = DesiredCapabilities.edge();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        System.setProperty("webdriver.edge.driver", "web_driver/edgedriver");
        edgeOptions.merge(capabilities);
        return edgeOptions;
    }

    public FirefoxOptions firefoxOptions() {
        firefoxOptions = new FirefoxOptions();
        capabilities = DesiredCapabilities.firefox();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        firefoxOptions.addArguments("--kiosk");
        firefoxOptions.addArguments("--disable-notifications");
        FirefoxProfile profile = new FirefoxProfile();
        capabilities.setCapability(FirefoxDriver.PROFILE, profile);
        capabilities.setCapability("marionette", true);
        firefoxOptions.merge(capabilities);
        System.setProperty("webdriver.gecko.driver", "web_driver/geckodriver");
        return firefoxOptions;
    }

    public ElementInfo findElementInfoByKey(String key) {
        return (ElementInfo) elementMapList.get(key);
    }

    public void saveValue(String key, String value) {
        elementMapList.put(key, value);
    }

    public String getValue(String key) {
        return elementMapList.get(key).toString();
    }
}
