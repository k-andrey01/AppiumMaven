import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
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

    @BeforeMethod
    public void setup() throws MalformedURLException{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "pixel");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "9.0");
        capabilities.setCapability("udid","emulator-5554");
        capabilities.setCapability("appPackage","ru.yandex.mail");
        capabilities.setCapability("appActivity", "ru.yandex.mail.ui.LoginActivity");
        androidDriver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        androidDriver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
    }
//1007 123
    @Test
    public void logIn() throws InterruptedException {
        LoginPage loginPage = new LoginPage();
        AppPage appPage = new AppPage();
        if (androidDriver.findElementsById("ru.yandex.mail:id/go_to_mail_button").size()>0) {
            loginPage.goMail(androidDriver);
            appPage.allStepsLogOut(androidDriver);

            loginPage.logIn(androidDriver);
        }else if (androidDriver.findElementsById("ru.yandex.mail:id/list_yandex").size()>0){
            loginPage.logIn2(androidDriver);
        }
        Assert.assertTrue(androidDriver.findElementByXPath(ConfProperties.getProperty("InboxPath")).getText().equals("Inbox"));
    }

    @Test
    public void sent(){
        LoginPage loginPage = new LoginPage();
        AppPage appPage = new AppPage();
        loginPage.goMail(androidDriver);

        appPage.swipeRight(androidDriver);
        appPage.openSent(androidDriver);
        Assert.assertTrue(androidDriver.findElementByXPath(ConfProperties.getProperty("SentPath")).getText().equals("Sent"));
    }

    @Test
    public void myMails() throws InterruptedException {
        LoginPage loginPage = new LoginPage();
        AppPage appPage = new AppPage();
        loginPage.goMail(androidDriver);

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
        LoginPage loginPage = new LoginPage();
        AppPage appPage = new AppPage();
        loginPage.goMail(androidDriver);

        ToolsPage toolsPage = new ToolsPage();
        appPage.swipeRight(androidDriver);
        appPage.swipeDown(androidDriver);
        appPage.openTools(androidDriver);
        toolsPage.setTheme(androidDriver);
        WebDriverWait wait = new WebDriverWait(androidDriver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ConfProperties.getProperty("ThemePath"))));
        Assert.assertTrue(androidDriver.findElementByXPath(ConfProperties.getProperty("ThemePath")).getText().equals("ON"));
    }

    @Test
    public void logOut(){
        LoginPage loginPage = new LoginPage();
        loginPage.goMail(androidDriver);

        AppPage appPage = new AppPage();
        appPage.allStepsLogOut(androidDriver);
        Assert.assertTrue(androidDriver.findElementById("ru.yandex.mail:id/list_yandex").isDisplayed());
    }

    //@Test
    public void AllTogether() throws InterruptedException {
        LoginPage loginPage = new LoginPage();
        AppPage appPage = new AppPage();
        ToolsPage toolsPage = new ToolsPage();
        //loginPage.logIn(androidDriver);
        loginPage.goMail(androidDriver);
        appPage.openMenu(androidDriver);
        appPage.swipeDown(androidDriver);
        appPage.swipeUp(androidDriver);
        appPage.openSent(androidDriver);
        appPage.openMenu(androidDriver);
        appPage.openMyMails(androidDriver);
        appPage.openMenu(androidDriver);
        appPage.swipeDown(androidDriver);
        appPage.openTools(androidDriver);
        toolsPage.setTheme(androidDriver);
        toolsPage.goBack(androidDriver);
        appPage.swipeLeft(androidDriver);
        appPage.swipeRight(androidDriver);
        appPage.swipeDown(androidDriver);
        appPage.logOut(androidDriver);
    }

    @AfterMethod
    public void exit(){
        androidDriver.quit();
    }
}


















