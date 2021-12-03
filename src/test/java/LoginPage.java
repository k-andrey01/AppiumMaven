import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import static java.time.Duration.ofMillis;

public class LoginPage {
    public void insertLogin(AndroidDriver androidDriver){
        androidDriver.findElementById("ru.yandex.mail:id/edit_login").sendKeys(ConfProperties.getProperty("login"));
    }
    public void getYandex(AndroidDriver androidDriver){
        androidDriver.findElementById("ru.yandex.mail:id/list_yandex").click();
    }
    public void pressEnter(AndroidDriver androidDriver){
        androidDriver.findElementById("ru.yandex.mail:id/button_next").click();
    }
    public void insertPassword(AndroidDriver androidDriver){
        androidDriver.findElementById("ru.yandex.mail:id/edit_password").sendKeys(ConfProperties.getProperty("password"));
    }
    public void insertAnswer(AndroidDriver androidDriver){
        androidDriver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]/android.view.View[2]/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View[2]/android.widget.EditText").sendKeys(ConfProperties.getProperty("famCheck"));
    }
    public void pressConfigOnSecretQuestion(AndroidDriver androidDriver){
        androidDriver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]/android.view.View[2]/android.view.View/android.view.View[2]/android.view.View[3]/android.widget.Button").click();
    }
    public void answerOnQuestion(AndroidDriver androidDriver){
        insertAnswer(androidDriver);
        pressConfigOnSecretQuestion(androidDriver);
    }
    public void goMail(AndroidDriver androidDriver){
        if (androidDriver.findElementsById("ru.yandex.mail:id/go_to_mail_button").size()>0) {
            androidDriver.findElementById("ru.yandex.mail:id/go_to_mail_button").click();
        }else{
            new TouchAction(androidDriver).tap(1007,123).perform();
        }
    }
    public void logIn(AndroidDriver androidDriver){
        getYandex(androidDriver);
        insertLogin(androidDriver);
        pressEnter(androidDriver);
        insertPassword(androidDriver);
        pressEnter(androidDriver);
        if (androidDriver.findElementsByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]/android.view.View[2]/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View[2]/android.widget.EditText").size()>0) {
            answerOnQuestion(androidDriver);
            goMail(androidDriver);
        }else if(androidDriver.findElementsById("ru.yandex.mail:id/go_to_mail_button").size()>0) {
            goMail(androidDriver);
        }
    }

    public void logIn2(AndroidDriver androidDriver) throws InterruptedException {
        getYandex(androidDriver);
        insertLogin(androidDriver);
        pressEnter(androidDriver);
        insertPassword(androidDriver);
        pressEnter(androidDriver);
        if (androidDriver.findElementsByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]/android.view.View[2]/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View[2]/android.widget.EditText").size()>0) {
            answerOnQuestion(androidDriver);
            goMail(androidDriver);
        }else{
            goMail(androidDriver);
        }
    }
}
