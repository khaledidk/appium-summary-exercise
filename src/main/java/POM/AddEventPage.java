package POM;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

public class AddEventPage extends BasePage{

    private final MobileElement eventName;

    private final MobileElement saveBtn;

    public AddEventPage (AndroidDriver<MobileElement> driver){
        super(driver);
        this.eventName = this.driver.findElementById("com.claudivan.taskagenda:id/etTitulo");
        this.saveBtn = this.driver.findElementById("com.claudivan.taskagenda:id/item_salvar");
    }

    public void writeEvent(String eventName){
        Actions action = new Actions(driver);
        action.sendKeys(eventName).sendKeys(Keys.ENTER).build().perform();
    }
    public void saveBtnClick(){
        this.saveBtn.click();
    }
}
