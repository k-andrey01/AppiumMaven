import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofMillis;

public class LoginPage {
    private AndroidDriver androidDriver;
    public LoginPage(AppiumDriver driver) {
        this.androidDriver = (AndroidDriver) driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "ru.yandex.mail:id/edit_login")
    public WebElement loginField;

    @AndroidFindBy(id = "ru.yandex.mail:id/list_yandex")
    public WebElement yandButton;

    @AndroidFindBy(id = "ru.yandex.mail:id/button_next")
    public WebElement enter;

    @AndroidFindBy(id = "ru.yandex.mail:id/edit_password")
    public WebElement passField;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]/android.view.View[2]/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View[2]")
    public WebElement secretField;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]/android.view.View[2]/android.view.View/android.view.View[2]/android.view.View[3]/android.widget.Button")
    public WebElement secretButton;

    @AndroidFindBy(id="ru.yandex.mail:id/go_to_mail_button")
    public WebElement goMailButton;

    @AndroidFindBy(id="ru.yandex.mail:id/close_button")
    public WebElement closeButton;

    @AndroidFindBy(id = "ru.yandex.mail:id/next_button")
    public WebElement contButton;

    public void insertLogin(AndroidDriver androidDriver){
        WebDriverWait wb = new WebDriverWait(androidDriver,5);
        wb.until(ExpectedConditions.visibilityOf(loginField));
        loginField.sendKeys(ConfProperties.getProperty("login"));
        //androidDriver.findElementById("ru.yandex.mail:id/edit_login").sendKeys(ConfProperties.getProperty("login"));
    }
    public void getYandex(AndroidDriver androidDriver){
        yandButton.click();
    }
    public void pressEnter(AndroidDriver androidDriver){
        enter.click();
    }
    public void insertPassword(AndroidDriver androidDriver){
        passField.sendKeys(ConfProperties.getProperty("password"));
    }
    public void insertAnswer(AndroidDriver androidDriver){
        secretField.sendKeys(ConfProperties.getProperty("famCheck"));
    }
    public void pressConfigOnSecretQuestion(AndroidDriver androidDriver){
        secretButton.click();
    }
    public void answerOnQuestion(AndroidDriver androidDriver){
        insertAnswer(androidDriver);
        pressConfigOnSecretQuestion(androidDriver);
    }
    public void goMail(AndroidDriver androidDriver) throws InterruptedException {
        WebDriverWait wb = new WebDriverWait(androidDriver,20);
        wb.until(ExpectedConditions.visibilityOf(goMailButton));
        //if (androidDriver.findElementsById("ru.yandex.mail:id/go_to_mail_button").size()>0) {
        goMailButton.click();
//        }else{
//              closeButton.click();
////            contButton.click();
////            contButton.click();
////            contButton.click();
        //}
    }
    public void logIn(AndroidDriver androidDriver) throws InterruptedException {
        getYandex(androidDriver);
        insertLogin(androidDriver);
        pressEnter(androidDriver);
        insertPassword(androidDriver);
        pressEnter(androidDriver);
        if (androidDriver.findElementsByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]/android.view.View[2]/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View[2]").size()>0) {
            answerOnQuestion(androidDriver);
            goMail(androidDriver);
        }else{
            goMail(androidDriver);
        }
    }

    public void logIn2(AndroidDriver androidDriver) throws InterruptedException {
        getYandex(androidDriver);
        insertLogin(androidDriver);
        pressEnter(androidDriver);
        insertPassword(androidDriver);
        pressEnter(androidDriver);
        if (androidDriver.findElementsByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]/android.view.View[2]/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View[2]").size()>0) {
            answerOnQuestion(androidDriver);
            goMail(androidDriver);
        }else{
            goMail(androidDriver);
        }
    }
}
