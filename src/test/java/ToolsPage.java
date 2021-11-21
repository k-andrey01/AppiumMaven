import io.appium.java_client.android.AndroidDriver;

public class ToolsPage {
    public void setTheme(AndroidDriver androidDriver) throws InterruptedException {
        androidDriver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.Switch").click();//dark them
    }
    public void goBack(AndroidDriver androidDriver) throws InterruptedException {
        //Thread.sleep(1000);
        androidDriver.wait(5000);
        androidDriver.findElementByAccessibilityId("Navigate up").click();//go back
    }
}
