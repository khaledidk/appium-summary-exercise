package logic.POM;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class SettingPage extends BasePage{

    private final MobileElement alarmNotifications;

    public SettingPage(AndroidDriver<MobileElement> driver) {
        super(driver);
        waitElement(By.id("com.claudivan.taskagenda:id/itemNotificacoes"));
        this.alarmNotifications = this.driver.findElementById("com.claudivan.taskagenda:id/itemNotificacoes");


    }

    public void alarmNotificationsBtnClick() {
        this.alarmNotifications.click();
    }

    public boolean SwitchBtnClick() {
        waitElement(By.id("com.claudivan.taskagenda:id/swVibracaoAlarmeEvento"));
        MobileElement  Switch = this.driver.findElementById("com.claudivan.taskagenda:id/swVibracaoAlarmeEvento");
        Switch.click();

        return Switch.getAttribute("checked").equals("false");
    }


}
