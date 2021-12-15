import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailPage {
    private AndroidDriver androidDriver;
    public MailPage(AppiumDriver driver) {
        this.androidDriver = (AndroidDriver) driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id="com.android.packageinstaller:id/permission_allow_button")
    public WebElement allowButton;

    public void allow(AndroidDriver androidDriver){
        WebDriverWait wb= new WebDriverWait(androidDriver,10);
        wb.until(ExpectedConditions.visibilityOf(allowButton));
        allowButton.click();
    }

    @AndroidFindBy(id="ru.yandex.mail:id/copy_edit_text")
    public WebElement addressButton;

    @AndroidFindBy(id="ru.yandex.mail:id/compose_bottom_space")
    public WebElement closeList;

    public void setAddress(AndroidDriver androidDriver){
        addressButton.sendKeys("k-andrey01@yandex.ru");
        //addressButton.click();
    }

    @AndroidFindBy(id="ru.yandex.mail:id/attach_file")
    public WebElement attach;

    @AndroidFindBy(id= "ru.yandex.mail:id/compose_attach_buttons")
    public WebElement confirmAttachButton;

    public void attach(AndroidDriver androidDriver) throws InterruptedException {
        Thread.sleep(2000);
        attach.click();
        attach.click();
        allowButton.click();
        allowButton.click();
    }

    @AndroidFindBy(id="ru.yandex.mail:id/compose_attach_item_checkbox")
    public WebElement addBox;

    @AndroidFindBy(id="ru.yandex.mail:id/compose_attach_confirm")
    public WebElement attachButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='High']")
    public WebElement highCompressBtn;

    public void addFile(AndroidDriver androidDriver){
        addBox.click();
        confirmAttachButton.click();
        highCompressBtn.click();
    }

    @AndroidFindBy(id="ru.yandex.mail:id/menu_send")
    public WebElement sendButton;

    public void sendMessage(AndroidDriver androidDriver){
        sendButton.click();
    }

    public void swipeDown(AndroidDriver androidDriver){
        //Thread.sleep(1000);
        (new TouchAction(androidDriver)).press(405,1573).waitAction(1000).moveTo(481,230).release().perform();
        (new TouchAction(androidDriver)).press(405,1573).waitAction(1000).moveTo(481,230).release().perform();
    }
}
