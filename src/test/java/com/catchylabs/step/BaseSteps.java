package com.catchylabs.step;

import com.catchylabs.base.BaseTest;
import com.catchylabs.model.ElementInfo;
import com.thoughtworks.gauge.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;
public class BaseSteps extends BaseTest {


    public static int DEFAULT_MAX_ITERATION_COUNT = 150;
    public static int DEFAULT_MILLISECOND_WAIT_AMOUNT = 150;

    private static String SAVED_ATTRIBUTE;
    public static WebDriverWait wait = new WebDriverWait(driver, 20);


    public BaseSteps() throws IOException {
        String currentWorkingDir = System.getProperty("user.dir");
        initMap(getFileList(currentWorkingDir + "/src"));

    }

    WebElement findElement(String key) {
        By infoParam = getElementInfoToBy(findElementInfoByKey(key));
        WebDriverWait webDriverWait = new WebDriverWait(driver, 60);
        WebElement webElement = webDriverWait
                .until(ExpectedConditions.presenceOfElementLocated(infoParam));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                webElement);
        return webElement;
    }

    List<WebElement> findElements(String key) {
        return driver.findElements(getElementInfoToBy(findElementInfoByKey(key)));
    }

    public By getElementInfoToBy(ElementInfo elementInfo) {
        By by = null;
        if (elementInfo.getType().equals("css")) {
            by = By.cssSelector(elementInfo.getValue());
        } else if (elementInfo.getType().equals(("name"))) {
            by = By.name(elementInfo.getValue());
        } else if (elementInfo.getType().equals("id")) {
            by = By.id(elementInfo.getValue());
        } else if (elementInfo.getType().equals("xpath")) {
            by = By.xpath(elementInfo.getValue());
        } else if (elementInfo.getType().equals("linkText")) {
            by = By.linkText(elementInfo.getValue());
        } else if (elementInfo.getType().equals(("partialLinkText"))) {
            by = By.partialLinkText(elementInfo.getValue());
        }
        return by;
    }

    private void clickElement(WebElement element) {
        element.click();
    }

    private void hoverElement(WebElement element) {
        actions.moveToElement(element).build().perform();
    }

    private boolean isDisplayedBy(By by) {
        return driver.findElement(by).isDisplayed();
    }

    private String getPageSource() {
        return driver.switchTo().alert().getText();
    }


    public String randomString(int stringLength) {

        Random random = new Random();
        char[] chars = "ABCDEFGHIJKLMNOPQR".toCharArray();
        String stringRandom = "";
        for (int i = 0; i < stringLength; i++) {

            stringRandom = stringRandom + chars[random.nextInt(chars.length)];
        }

        return stringRandom;
    }


    public void javaScriptClicker(WebDriver driver, WebElement element) {

        JavascriptExecutor jse = ((JavascriptExecutor) driver);
        jse.executeScript("var evt = document.createEvent('MouseEvents');"
                + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"
                + "arguments[0].dispatchEvent(evt);", element);
    }

    public void javascriptclicker(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    @Step({"Wait <value> seconds",
            "<int> saniye bekle"})
    public void waitBySeconds(int seconds) {
        try {
            logger.info(seconds + " saniye bekleniyor.");
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step({"Wait <value> milliseconds",
            "<long> milisaniye bekle"})
    public void waitByMilliSeconds(long milliseconds) {
        try {
           // logger.info(milliseconds + " milisaniye bekleniyor.");
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Step({"Click to element <key>",
            "Elementine tıkla <key>"})
    public void clickElement(String key) {
        if (!key.isEmpty()) {
            hoverElement(findElement(key));
            scrollToElementToBeVisiblest(findElement(key));
            clickElement(findElement(key));
            logger.info(key + " elementine tıklandı.");
        }
    }




    @Step({"<key> elementini kontrol et", "check <key> element is exist"})
    public void checkElement(String key) {
        assertTrue(findElement(key).isDisplayed(), "Aranan element bulunamadı");
    }


    @Step({"Check if element <key> exists",
            "Elementin yüklenmesini bekle <key>"})
    public void getElementWithKeyIfExists(String key) {
        ElementInfo elementInfo = findElementInfoByKey(key);
        By by = getElementInfoToBy(elementInfo);

        int loopCount = 0;
        while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
            if (driver.findElements(by).size() > 0) {
                logger.info(key + " elementi bulundu.");
                return;
            }
            loopCount++;
            waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
        }
        Assertions.fail("Element: '" + key + "' doesn't exist.");
    }



    @Step({"Check if element <key> exists else print message <message>",
            "Element <key> var mı kontrol et yoksa hata mesajı ver <message>"})
    public void getElementWithKeyIfExistsWithMessage(String key, String message) {
        ElementInfo elementInfo = findElementInfoByKey(key);
        By by = getElementInfoToBy(elementInfo);

        int loopCount = 0;
        while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
            if (driver.findElements(by).size() > 0) {
                logger.info(key + " elementi bulundu.");
                return;
            }
            loopCount++;
            waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
        }
        Assertions.fail(message);
    }


    @Step({"Write value <text> to element <key>",
            "<text> textini <key> elemente yaz"})
    public void ssendKeys(String text, String key) {
        if (!key.equals("")) {
            findElement(key).sendKeys(text);
            logger.info(key + " elementine " + text + " texti yazıldı.");
        }
    }


    @Step({"Check if current URL contains the value <expectedURL>",
            "Şuanki URL <url> değerini içeriyor mu kontrol et"})
    public void checkURLContainsRepeat(String expectedURL) {
        int loopCount = 0;
        String actualURL = "";
        while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
            actualURL = driver.getCurrentUrl();

            if (actualURL != null && actualURL.contains(expectedURL)) {
                logger.info("Şuanki URL " + expectedURL + " değerini içeriyor.");
                return;
            }
            loopCount++;
            waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
        }
        Assertions.fail(
                "Actual URL doesn't match the expected." + "Expected: " + expectedURL + ", Actual: "
                        + actualURL);
    }


    @Step({"Clear text of element <key>",
            "<key> elementinin text alanını temizle"})
    public void clearInputArea(String key) {
        LoadedofWebElement(findElement(key));
        findElement(key).clear();
    }

    @Step("<key> olarak comboboxdan bir değer seçilir")
    public void comboBoxRandom(String key) throws InterruptedException {

        List<WebElement> comboBoxElement = findElements(key);
        int randomIndex = new Random().nextInt(comboBoxElement.size());
        Thread.sleep(2000);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", comboBoxElement.get(randomIndex));
        logger.info(key + " comboboxından herhangi bir değer seçildi");

    }


    @Step({"Check if element <key> contains text <expectedText>",
            "<key> elementi <text> değerini içeriyor mu kontrol et"})
    public void checkElementContainsText(String key, String expectedText) {
        LoadedofWebElement(findElement(key));
        getElementWithKeyIfExists(key);

        String actualText = findElement(key).getText();
        logger.info(key + " elementinin text değeri: " + actualText);

        Boolean containsText = actualText.contains(expectedText);
        logger.info("Kontrol edilen metin: " + expectedText);

        assertTrue(containsText, "Expected text is not contained");
        logger.info(key + " elementi >>>> " + expectedText + " <<<< değerini içeriyor.");
    }

    @Step({"Write random value to element <key>",
            "<key> elementine random değer yaz"})
    public void writeRandomValueToElement(String key) {
        findElement(key).sendKeys(randomString(5));
    }


    @Step({"Write value <string> to element <key> with focus",
            "<string> değerini <key> elementine focus ile yaz"})
    public void sendKeysWithFocus(String text, String key) {
        actions.moveToElement(findElement(key));
        actions.click();
        actions.sendKeys(text);
        actions.build().perform();
        logger.info(key + " elementine " + text + " değeri focus ile yazıldı.");
    }


    @Step("popupa gec")
    public void switchTo() {
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }
    private JavascriptExecutor getJSExecutor() {
        return (JavascriptExecutor) driver;
    }

    private Object executeJS(String script, boolean wait) {
        return wait ? getJSExecutor().executeScript(script, "") : getJSExecutor().executeAsyncScript(script, "");
    }

    private void scrollTo(int x, int y) {
        String script = String.format("window.scrollTo(%d, %d);", x, y);
        executeJS(script, true);
    }


    public void scrollToElementToBeVisiblest(WebElement webElement) {
        if (webElement != null) {
            scrollTo(webElement.getLocation().getX(), webElement.getLocation().getY() - 100);
        }
    }


    @Step("<text> textini <key> elemente tek tek yaz")
    public void sendKeyOneByOne(String text, String key) throws InterruptedException {

        WebElement field = findElement(key);
        field.clear();
        if (!key.equals("")) {
            for (char ch : text.toCharArray())
                findElement(key).sendKeys(Character.toString(ch));
            Thread.sleep(10);
            logger.info(key + " elementine " + text + " texti karakterler tek tek girlilerek yazıldı.");
        }
    }

    @Step("<key> elementine <text> değerini js ile yaz")
    public void writeToKeyWithJavaScript(String key, String text) {
        waitBySeconds(2);
        WebElement element = findElement(key);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value=arguments[1]", element, text);
        logger.info(key + " elementine " + text + " değeri js ile yazıldı.");
    }



    @Step("<variable> değişkenini <key> elementine yaz")
    public void sendKeysVariable(String variable, String key) {
        if (!key.equals("")) {
            clearInputArea(key);
            findElement(key).sendKeys(getValue(variable));
            logger.info(key + " elementine " + getValue(variable) + " texti yazıldı.");
        }
    }

    public void LoadedofWebElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(element));

    }



    @Step({"<key> li elementin text degeri hafizada <saveKey> olarak saklanan text ile esit mi kontrol et"})
    public void equalsByKeyAndSaveKey(String key, String saveKey) {
        System.out.println("------------------------------------------------------");
        String savedText = getValue(saveKey);
        logger.info("Expected Value: " + savedText);

        String elementText =findElement(key).getText();
        logger.info("Actual Value: " + elementText);
        System.out.println("------------------------------------------------------");

        assertEquals(elementText, savedText, "Değerler birbirine eşit değil!");
    }
    @Step({"<key> li elementin text degeri hafizada <saveKey> olarak saklanan text ile Esit Olmadığını ve farkın doğru olduğunu kontrol et"})
    public void notEqualByKeyAndSaveKeY(String key, String saveKey) {
        System.out.println("------------------------------------------------------");

        String savedText = getValue(saveKey).replace(",", "").trim();
        logger.info("Expected Value (Saved Text): " + savedText);

        String elementText = findElement(key).getText().replace(",", "").trim();
        logger.info("Actual Value (Element Text): " + elementText);

        System.out.println("------------------------------------------------------");

        double savedValue = Double.parseDouble(savedText);
        double actualValue = Double.parseDouble(elementText);
        double expectedDifference = 1000.00;
        double actualDifference = savedValue - actualValue;

        if (actualDifference != expectedDifference) {
            logger.error("HATA: Beklenen fark: " + expectedDifference +
                    ", Gerçek fark: " + actualDifference);
            throw new AssertionError("Transfer sırasında beklenen fark oluşmadı! Fark: " + actualDifference);
        }

        logger.info("Doğrulama başarılı: Transfer sonrası fark doğru. Fark: " + actualDifference);
    }

    @Step({"<key> li elementin text degeri <saveKey> olarak saklanan text ile Esit Olmadığını kontrol et"})
    public void notEqualByKeyAndSave(String key, String saveKey) {

        System.out.println("------------------------------------------------------");
        String savedText = getValue(saveKey);
        logger.info("Expected Value: " + savedText);

        String elementText =findElement(key).getText();
        logger.info("Actual Value: " + elementText);
        System.out.println("------------------------------------------------------");
        assertFalse(elementText.contains(savedText), "Degerler birbirine esittir");
    }

    @Step("<textOfElement> elementinin textini <saveKey> olarak kaydet")
    public void saveTextOfElement(String key, String saveKey) {

        String textOfElement = findElement(key).getText();
        saveValue(saveKey, textOfElement);
        logger.info(key + "elementinin texti olan " + textOfElement + " değer " + saveKey + " olarak kaydedildi");

    }



}
























