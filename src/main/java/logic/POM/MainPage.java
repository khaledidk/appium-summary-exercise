package logic.POM;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainPage extends BasePage {
    private final MobileElement pendEventBtn;

    private final MobileElement addEventBtn;
    private final MobileElement rightArrowBtn;
    private final MobileElement leftArrowBtn;
    private final MobileElement menu;
    private final MobileElement date;

    public MainPage(AndroidDriver<MobileElement> driver) {
        super(driver);
        this.pendEventBtn = this.driver.findElementById("com.claudivan.taskagenda:id/btEventosSemana");
        this.rightArrowBtn = this.driver.findElementById("com.claudivan.taskagenda:id/ibtAvancar");
        this.leftArrowBtn = this.driver.findElementById("com.claudivan.taskagenda:id/ibtRetroceder");
        this.addEventBtn = this.driver.findElementById("com.claudivan.taskagenda:id/btNovoEvento");
        this.date = this.driver.findElementById("com.claudivan.taskagenda:id/tvVisor");
        this.menu = this.driver.findElementById("com.claudivan.taskagenda:id/hamburguer");
    }

    public void pendEventBtnClick() {

        waitElement(By.id("com.claudivan.taskagenda:id/btNovoEvento"));
        this.pendEventBtn.click();
    }

    public void addEventBtnClick() {
        this.addEventBtn.click();
    }

    public void rightArrowBtnClick() {
        this.rightArrowBtn.click();
    }
    public void settingBtnClick() {
        MobileElement setting = this.driver.findElementById("com.claudivan.taskagenda:id/btAjustes");
        setting.click();
    }


    public void aboutBtnClick() {
        MobileElement about = this.driver.findElementById("com.claudivan.taskagenda:id/btSobre");
        about.click();
    }

    public String getAboutText() {
        waitElement(By.id("com.claudivan.taskagenda:id/tvTexto"));
        MobileElement text = this.driver.findElementById("com.claudivan.taskagenda:id/tvTexto");
        return text.getText();
    }


    public void leftArrowBtnClick() {
        this.leftArrowBtn.click();
    }
    public void menuBtnClick() {
        this.menu.click();
    }

    public String getDate() {
        return this.date.getText();
    }

    public void TodayEventBtnClick() {
        waitElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[1]"));
        MobileElement todayEventBtn = this.driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[1]");
        todayEventBtn.click();
    }
    public int getNumbersOfEvent() {
        waitElement(By.id("com.claudivan.taskagenda:id/btNovoEvento"));
        String s = this.pendEventBtn.getText();
        String[] parts = s.split(" ");
        int number = Integer.parseInt(parts[0]);
        return number;
    }

    public String updateDate(String  dateRange , int flag) throws ParseException {

        String[] dateParts = dateRange.split(" - ");

        if (dateParts.length == 2) {
            String startDateStr = dateParts[0];
            String endDateStr = dateParts[1];

            SimpleDateFormat dateFormat = new SimpleDateFormat("d/MMM");

            try {
                Date startDate = dateFormat.parse(startDateStr);
                Date endDate = dateFormat.parse(endDateStr);

                // Add 1 day to the start date
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(startDate);
                if(flag == 1) {
                    calendar.add(Calendar.DAY_OF_MONTH, 7);
                }else{
                    calendar.add(Calendar.DAY_OF_MONTH, -7);
                }
                startDate = calendar.getTime();

                // Add 7 days to the end date
                calendar.setTime(endDate);
                if(flag == 1) {
                    calendar.add(Calendar.DAY_OF_MONTH, 7);
                }else{
                    calendar.add(Calendar.DAY_OF_MONTH, -7);
                }
                endDate = calendar.getTime();

                // Format the updated dates
                String updatedStartDateStr = dateFormat.format(startDate);

                String updatedEndDateStr = dateFormat.format(endDate);

                String updatedDateRange = updatedStartDateStr + " - " + updatedEndDateStr;
                System.out.println("Updated Date Range: " + updatedDateRange);
                return updatedDateRange;
            } catch (ParseException e) {
                System.err.println("Error parsing the date strings.");
            }
        } else {
            System.err.println("Invalid date range format.");
        }
        return "";
    }
}
