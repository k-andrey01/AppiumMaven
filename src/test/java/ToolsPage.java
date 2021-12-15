import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ToolsPage {
    private AndroidDriver androidDriver;
    public ToolsPage(AppiumDriver driver) {
        this.androidDriver = (AndroidDriver) driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.Switch")
    public WebElement themeButton;

    @AndroidFindBy(accessibility = "Navigate up")
    public WebElement backButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Clear cache']")
    public WebElement clearButton;

    @AndroidFindBy(id="android:id/button1")
    public WebElement confirmButton;

    public void clearCache(AndroidDriver androidDriver){
        WebDriverWait wb = new WebDriverWait(androidDriver,10);
        wb.until(ExpectedConditions.visibilityOf(clearButton));
        clearButton.click();
        wb.until(ExpectedConditions.visibilityOf(confirmButton));
        confirmButton.click();
    }

    public void swipeDown(AndroidDriver androidDriver){
        //Thread.sleep(1000);
        (new TouchAction(androidDriver)).press(405,1700).waitAction(1000).moveTo(481,50).release().perform();
        (new TouchAction(androidDriver)).press(405,1700).waitAction(1000).moveTo(481,50).release().perform();
    }

    public void setTheme(AndroidDriver androidDriver){
        themeButton.click();//dark them
    }
    public void goBack(AndroidDriver androidDriver) throws InterruptedException {
        //Thread.sleep(1000);
        //androidDriver.wait(5000);
        WebDriverWait ww = new WebDriverWait(androidDriver,7);
        ww.until(ExpectedConditions.visibilityOf(backButton));
        backButton.click();//go back
    }
}
