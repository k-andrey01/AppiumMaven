import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AppPage {
    private AndroidDriver androidDriver;
    public AppPage(AppiumDriver driver) {
        this.androidDriver = (AndroidDriver) driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id="ru.yandex.mail:id/counter")
    public WebElement mailsCounter;

    @AndroidFindBy(accessibility = "Open menu")
    public WebElement menuButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Sent']")
    public WebElement sentButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Inbox']")
    public WebElement inboxButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Settings']")
    public WebElement settingsButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Log out']")
    public WebElement logOutButton;

    @AndroidFindBy(id="ru.yandex.mail:id/fab")
    public WebElement writeMesButton;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.view.ViewGroup/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[2]/android.widget.LinearLayout")
    public WebElement lastMes;

    public void writeMessage(AndroidDriver androidDriver){
        writeMesButton.click();
    }

    public void openMenu(AndroidDriver androidDriver){
        menuButton.click();
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
        sentButton.click();
    }
    public void openMyMails(AndroidDriver androidDriver){
        inboxButton.click();
    }
    public void openTools(AndroidDriver androidDriver){
        settingsButton.click();//tools
    }
    public void swipeLeft(AndroidDriver androidDriver){
        //Thread.sleep(3000);
        (new TouchAction(androidDriver)).press(971,1066).waitAction(1000).moveTo(115,1066).release().perform();
    }
    public void deleteMes(AndroidDriver androidDriver){
        WebDriverWait wb = new WebDriverWait(androidDriver,10);
        wb.until(ExpectedConditions.visibilityOf(lastMes));
        (new TouchAction(androidDriver)).press(939,699).waitAction(1000).moveTo(98,699).release().perform();
    }
    public void swipeRight(AndroidDriver androidDriver){
        //Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(androidDriver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ru.yandex.mail:id/fab")));
        (new TouchAction(androidDriver)).press(3,382).waitAction(1000).moveTo(1000,407).release().perform();
    }
    public void logOut(AndroidDriver androidDriver){
        logOutButton.click();//log out

    }
    public void allStepsLogOut(AndroidDriver androidDriver){
        WebDriverWait wb =new WebDriverWait(androidDriver,10);
        wb.until(ExpectedConditions.visibilityOf(mailsCounter));
        swipeRight(androidDriver);
        swipeDown(androidDriver);
        logOut(androidDriver);
    }
}
