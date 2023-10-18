package POM;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class EventsPage  extends BasePage{
//    private final MobileElement eventList;

    public EventsPage (AndroidDriver<MobileElement> driver){
        super(driver);

    }

    public void deleteEvent() {
        MobileElement deleteBtn = this.driver.findElementById("com.claudivan.taskagenda:id/item_excluir");

        deleteBtn.click();

    }

    public void editeEvent() {
        MobileElement editeBtn = this.driver.findElementById("com.claudivan.taskagenda:id/item_editar");

        editeBtn.click();

    }
    public void saveBtn() {
        MobileElement saveBtn = this.driver.findElementById("com.claudivan.taskagenda:id/item_salvar");

        saveBtn.click();

    }

    public boolean checkBox() {
        waitElement(By.id("com.claudivan.taskagenda:id/cbEventoConcluido"));
        MobileElement checkBox = this.driver.findElementById("com.claudivan.taskagenda:id/cbEventoConcluido");
        checkBox.click();
        System.out.println( checkBox.isSelected());
        return checkBox.getAttribute("checked").equals("true");

    }
    public String getEventName() {
        MobileElement eventName = this.driver.findElementById("com.claudivan.taskagenda:id/tvTitulo");

        return eventName.getText();

    }
    public boolean searchForEvent(String eventName) {
        waitElement(By.id("com.claudivan.taskagenda:id/search"));
        MobileElement searchBtn= this.driver.findElementById("com.claudivan.taskagenda:id/search");
        searchBtn.click();
        Actions action = new Actions(driver);
        action.sendKeys(eventName).sendKeys(Keys.ENTER).build().perform();
        return findEventNameInList(eventName , 0);

    }

    public void yesConfirm() {
        waitElement(By.id("android:id/button1"));
        MobileElement yes = this.driver.findElementById("android:id/button1");

        yes.click();

    }

    public void changeName(String newName) {
        waitElement(By.id("com.claudivan.taskagenda:id/btApagarTitulo"));
        MobileElement clear = this.driver.findElementById("com.claudivan.taskagenda:id/btApagarTitulo");
        clear.click();

        MobileElement input = this.driver.findElementById("com.claudivan.taskagenda:id/etTitulo");

        Actions action = new Actions(driver);
        action.sendKeys(newName).sendKeys(Keys.ENTER).build().perform();


    }
    public boolean findEventNameInList(String eventName , int flag){

        waitElement(By.id("com.claudivan.taskagenda:id/lvListaEventos"));
        // Locate the layout element
        MobileElement layoutElement = driver.findElementById("com.claudivan.taskagenda:id/lvListaEventos");

        // Locate and verify text elements within the layout
        List<MobileElement> textElements = layoutElement.findElements(By.className("android.widget.TextView"));

        for (MobileElement textElement : textElements) {
            String text = textElement.getText();
            if (text.contains(eventName)) {
                System.out.println("Desired text found within the layout");
                if(flag == 1) {
                    textElement.click();
                }
              return true;
            }
        }
        return false;

    }
}
