import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AppPage {
    public void openMenu(AndroidDriver androidDriver){
        androidDriver.findElementByAccessibilityId("Open menu").click();
    }
    public void swipeDown(AndroidDriver androidDriver){
        //Thread.sleep(1000);
        (new TouchAction(androidDriver)).waitAction(1000).press(405,1573).moveTo(481,230).release().perform();
    }
    public void swipeUp(AndroidDriver androidDriver){
        //Thread.sleep(1000);
        (new TouchAction(androidDriver)).waitAction(1000).press(481,230).moveTo(405,1573).release().perform();
    }
    public void openSent(AndroidDriver androidDriver){
        androidDriver.findElementByAccessibilityId("Folder Sent: 0 unread").click();
    }
    public void openMyMails(AndroidDriver androidDriver){
        androidDriver.findElementByAccessibilityId("Folder Inbox: 13 unread").click();
    }
    public void openTools(AndroidDriver androidDriver){
        androidDriver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Settings']")).click();//tools
    }
    public void swipeLeft(AndroidDriver androidDriver){
        //Thread.sleep(3000);
        (new TouchAction(androidDriver)).press(971,1066).waitAction(1000).moveTo(115,1066).release().perform();
    }
    public void swipeRight(AndroidDriver androidDriver){
        //Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(androidDriver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ru.yandex.mail:id/fab")));
        (new TouchAction(androidDriver)).press(3,382).waitAction(1000).moveTo(1000,407).release().perform();
    }
    public void logOut(AndroidDriver androidDriver){
        androidDriver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Log out']")).click();//log out

    }
    public void allStepsLogOut(AndroidDriver androidDriver){
        swipeRight(androidDriver);
        swipeDown(androidDriver);
        logOut(androidDriver);
    }
}
