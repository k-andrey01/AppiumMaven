import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.aspectj.lang.annotation.Before;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class CalcTest {
    AndroidDriver androidDriver;
    private DesiredCapabilities capabilities;

    @BeforeMethod
    public void setUp() throws JSONException {
//        switch (Configuration.DEVICE) {
//            case emulator:
//                setAndroidCapabilities("emulatorCapability.json");
//                break;
//            case browserstack:
//                setAndroidCapabilities("browserStackCapability.json");
//                break;
//        }
        setAndroidCapabilities("emulatorCapability.json");
    }

    private void setAndroidCapabilities(String path) throws JSONException {
        this.capabilities = new DesiredCapabilities();
        JSONObject appiumJson = SetCapability.readJsonFromFile(this.getClass().getClassLoader().getResource(path).getPath());
        JSONObject caps = SetCapability.getCapabilities(appiumJson);
        caps.keySet().forEach(keyStr -> this.capabilities.setCapability(keyStr, caps.get(keyStr)));
        try {
            this.androidDriver = new AndroidDriver<MobileElement>(new URL(SetCapability.getUrl(appiumJson)), this.capabilities);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void logIn() throws InterruptedException {
        LoginPage loginPage = new LoginPage(androidDriver);
        AppPage appPage = new AppPage(androidDriver);
        if (androidDriver.findElementsById("ru.yandex.mail:id/list_yandex").size()>0){
            loginPage.logIn2(androidDriver);
        }else{
            loginPage.goMail(androidDriver);
            appPage.allStepsLogOut(androidDriver);
            loginPage.logIn(androidDriver);
        }
        WebDriverWait w = new WebDriverWait(androidDriver,5);
        w.until(ExpectedConditions.visibilityOf(androidDriver.findElementByXPath(ConfProperties.getProperty("InboxPath"))));
        Assert.assertTrue(androidDriver.findElementByXPath(ConfProperties.getProperty("InboxPath")).getText().equals("Inbox"));
    }

    @Test
    public void sent() throws InterruptedException {
        LoginPage loginPage = new LoginPage(androidDriver);
        AppPage appPage = new AppPage(androidDriver);
        if (androidDriver.findElementsById("ru.yandex.mail:id/list_yandex").size()>0) {
            loginPage.logIn2(androidDriver);
        }else {
            loginPage.goMail(androidDriver);
        }

        appPage.swipeRight(androidDriver);
        appPage.openSent(androidDriver);
        Assert.assertTrue(androidDriver.findElementByXPath(ConfProperties.getProperty("SentPath")).getText().equals("Sent"));
    }

    @Test
    public void myMails() throws InterruptedException {
        LoginPage loginPage = new LoginPage(androidDriver);
        AppPage appPage = new AppPage(androidDriver);
        if (androidDriver.findElementsById("ru.yandex.mail:id/list_yandex").size()>0) {
            loginPage.logIn2(androidDriver);
        }else {
            loginPage.goMail(androidDriver);
        }

        appPage.swipeRight(androidDriver);
        appPage.openSent(androidDriver);
        appPage.swipeRight(androidDriver);
        appPage.swipeLeft(androidDriver);
        appPage.swipeRight(androidDriver);
        appPage.openMyMails(androidDriver);
        Assert.assertTrue(androidDriver.findElementByXPath(ConfProperties.getProperty("InboxPath")).getText().equals("Inbox"));
    }

    @Test
    public void setTheme() throws InterruptedException {
        LoginPage loginPage = new LoginPage(androidDriver);
        AppPage appPage = new AppPage(androidDriver);
        if (androidDriver.findElementsById("ru.yandex.mail:id/list_yandex").size()>0) {
            loginPage.logIn2(androidDriver);
        }else {
            loginPage.goMail(androidDriver);
        }

        ToolsPage toolsPage = new ToolsPage(androidDriver);
        appPage.swipeRight(androidDriver);
        appPage.swipeDown(androidDriver);
        appPage.openTools(androidDriver);
        toolsPage.setTheme(androidDriver);
        WebDriverWait wait = new WebDriverWait(androidDriver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ConfProperties.getProperty("ThemePath"))));
        Assert.assertTrue(androidDriver.findElementByXPath(ConfProperties.getProperty("ThemePath")).getText().equals("ON"));
    }

    @Test
    public void logOut() throws InterruptedException {
        LoginPage loginPage = new LoginPage(androidDriver);
        if (androidDriver.findElementsById("ru.yandex.mail:id/list_yandex").size()>0) {
            loginPage.logIn2(androidDriver);
        }else {
            loginPage.goMail(androidDriver);
        }

        AppPage appPage = new AppPage(androidDriver);
        appPage.allStepsLogOut(androidDriver);
        Assert.assertTrue(androidDriver.findElementById("ru.yandex.mail:id/list_yandex").isDisplayed());
    }

    @Test
    public void sendMes() throws InterruptedException {
        LoginPage loginPage = new LoginPage(androidDriver);
        if (androidDriver.findElementsById("ru.yandex.mail:id/list_yandex").size()>0) {
            loginPage.logIn2(androidDriver);
        }else {
            loginPage.goMail(androidDriver);
        }

        AppPage appPage = new AppPage(androidDriver);
        appPage.writeMessage(androidDriver);

        MailPage mailPage = new MailPage(androidDriver);
        mailPage.allow(androidDriver);
        mailPage.setAddress(androidDriver);
        mailPage.attach(androidDriver);
        mailPage.addFile(androidDriver);
        mailPage.sendMessage(androidDriver);

        appPage.swipeRight(androidDriver);
        appPage.openSent(androidDriver);
        appPage.swipeRight(androidDriver);
        appPage.swipeLeft(androidDriver);
        appPage.swipeRight(androidDriver);
        appPage.openMyMails(androidDriver);
        Assert.assertTrue(appPage.mailsCounter.getText().equals("14"));//3
    }

    @Test
    public void zDelMes() throws InterruptedException {
        LoginPage loginPage = new LoginPage(androidDriver);
        if (androidDriver.findElementsById("ru.yandex.mail:id/list_yandex").size()>0) {
            loginPage.logIn2(androidDriver);
        }else {
            loginPage.goMail(androidDriver);
        }

        AppPage appPage = new AppPage(androidDriver);
        appPage.deleteMes(androidDriver);
        Thread.sleep(5000);
        Assert.assertTrue(appPage.mailsCounter.getText().equals("13"));//2
    }

    @Test
    public void cleanCash() throws InterruptedException {
        LoginPage loginPage = new LoginPage(androidDriver);
        AppPage appPage = new AppPage(androidDriver);
        if (androidDriver.findElementsById("ru.yandex.mail:id/list_yandex").size()>0) {
            loginPage.logIn2(androidDriver);
        }else {
            loginPage.goMail(androidDriver);
        }

        ToolsPage toolsPage = new ToolsPage(androidDriver);
        appPage.swipeRight(androidDriver);
        appPage.swipeDown(androidDriver);
        appPage.openTools(androidDriver);

        toolsPage.swipeDown(androidDriver);

        WebElement cc1 = androidDriver.findElementByXPath(ConfProperties.getProperty("CachePath"));
        String c1 =cc1.getText().toString();
        toolsPage.clearCache(androidDriver);
        WebElement cc2 = androidDriver.findElementByXPath(ConfProperties.getProperty("CachePath"));
        Thread.sleep(2000);
        String c2 =cc2.getText().toString();
        Assert.assertTrue(c1.compareTo(c2)>=0);
    }

    @AfterMethod
    public void exit(){
        androidDriver.quit();
    }
}


















