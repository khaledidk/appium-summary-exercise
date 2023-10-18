import POM.AddEventPage;
import POM.EventsPage;
import POM.MainPage;
import POM.SettingPage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.remote.DesiredCapabilities;


import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;

public class MyTest {
    private  AndroidDriver<MobileElement> driver;

    @Before
    public void setUp() throws MalformedURLException {
        // Set up desired capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "emulator-5554");
        caps.setCapability("platformVersion", "11");
        caps.setCapability("appPackage", "com.claudivan.taskagenda");
        caps.setCapability("appActivity", ".Activities.MainActivity");

        // Initialize the Android driver
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
    }

    //Test1: this test add new event
    @Test
    public void AddEvent()  {

        // arrange
        MainPage mainPage = new MainPage(driver);

        String eventName = "Event1";
        mainPage.addEventBtnClick();
        mainPage.TodayEventBtnClick();
        AddEventPage addEventPage = new AddEventPage(driver);
        addEventPage.writeEvent(eventName);
        addEventPage.saveBtnClick();
        mainPage.pendEventBtnClick();
        EventsPage eventPage = new EventsPage(driver);

        // assert
        Assert.assertTrue(eventPage.findEventNameInList(eventName , 0));

    }
    //Test2: this test add new event then delete it
    @Test
    public void DeleteEvent()  {
        // arrange
        MainPage mainPage = new MainPage(driver);

        String eventName = "Event2";
        mainPage.addEventBtnClick();
        mainPage.TodayEventBtnClick();
        AddEventPage addEventPage = new AddEventPage(driver);
        addEventPage.writeEvent(eventName);
        addEventPage.saveBtnClick();
        mainPage.pendEventBtnClick();
        EventsPage eventPage = new EventsPage(driver);
        eventPage.findEventNameInList(eventName , 1);
        eventPage.deleteEvent();
        eventPage.yesConfirm();
        // assert
        Assert.assertFalse(  eventPage.findEventNameInList(eventName , 0));

    }

    //Test3: this test change event name
    @Test
    public void ChangeNameEvent() throws ParseException {

        // arrange
        MainPage mainPage = new MainPage(driver);
        String eventName = "Event2";
        String eventChangeName = "Event3";
        mainPage.addEventBtnClick();
        mainPage.TodayEventBtnClick();
        AddEventPage addEventPage = new AddEventPage(driver);
        addEventPage.writeEvent(eventName);
        addEventPage.saveBtnClick();
        mainPage.pendEventBtnClick();
        EventsPage eventPage = new EventsPage(driver);
        eventPage.findEventNameInList(eventName , 1);
        eventPage.editeEvent();
        eventPage.changeName(eventChangeName);
        eventPage.saveBtn();

        // assert
        Assert.assertEquals( eventChangeName ,eventPage.getEventName() );


    }
    //Test4: this test check the date if the user go to the next week
    @Test
    public void GoWeekAfter() throws ParseException {

        // arrange
        MainPage mainPage = new MainPage(driver);
        String dateBefore = mainPage.getDate();

        mainPage.rightArrowBtnClick();
        String dateAfter = mainPage.getDate();

        // assert
        Assert.assertEquals( mainPage.updateDate(dateBefore , 1) , dateAfter);


    }
    //Test5: this test check the date if the user go to week before
    @Test
    public void GoWeekBefore() throws ParseException {

        // arrange
        MainPage mainPage = new MainPage(driver);
        String dateBefore = mainPage.getDate();

        mainPage.leftArrowBtnClick();
        String dateAfter = mainPage.getDate();
        // assert
        Assert.assertEquals( mainPage.updateDate(dateBefore , 0) , dateAfter);

    }
    //Test6: this test check numbers of events
    @Test
    public void CheckNumbersOfEvents()  {

        // arrange
        MainPage mainPage = new MainPage(driver);
        int howManyEvenys = 2;
        for(int i = 0 ; i < howManyEvenys ; i++) {
            String eventName = "Event" + i;
            mainPage.addEventBtnClick();
            mainPage.TodayEventBtnClick();
            AddEventPage addEventPage = new AddEventPage(driver);
            addEventPage.writeEvent(eventName);
            addEventPage.saveBtnClick();
        }
        int number = mainPage.getNumbersOfEvent();
        // assert
        Assert.assertEquals(2 , number);

    }
    //Test7: this test check the search
    @Test
    public void searchForEvent()  {

        // arrange
        MainPage mainPage = new MainPage(driver);
        int howManyEvenys = 1;
        String eventName = null;
        for(int i = 0 ; i < howManyEvenys ; i++) {
             eventName = "Event" + i;
            mainPage.addEventBtnClick();
            mainPage.TodayEventBtnClick();
            AddEventPage addEventPage = new AddEventPage(driver);
            addEventPage.writeEvent(eventName);
            addEventPage.saveBtnClick();
        }
        mainPage.pendEventBtnClick();
        EventsPage eventPage = new EventsPage(driver);
        boolean bool = eventPage.searchForEvent(eventName);

        // assert
        Assert.assertTrue(bool);

    }

    //Test8: this test check vibrate switch work from setting
    @Test
    public void CheckVibrateOff()  {

        // arrange
        MainPage mainPage = new MainPage(driver);
        mainPage.menuBtnClick();
        mainPage.settingBtnClick();
        SettingPage settingPage = new SettingPage(driver);
        settingPage.alarmNotificationsBtnClick();
        // assert
        Assert.assertTrue(settingPage.SwitchBtnClick());

    }

    //Test9: this test check about text
    @Test
    public void CheckAbout()  {

        // arrange
        String expectedText = "Developed by Apps CC\n" +
                "\n\r" +
                "Contact us:\n\r" +
                "taskagenda.app@gmail.com" ;
        MainPage mainPage = new MainPage(driver);
        mainPage.menuBtnClick();
        mainPage.aboutBtnClick();
        String actualText = mainPage.getAboutText();
        // assert
        Assert.assertEquals(expectedText,actualText);

    }

    //Test10: this test check the CheckBox for single event
    @Test
    public void CheckBox()  {

        // arrange
        MainPage mainPage = new MainPage(driver);

        String eventName = "Event" ;
        mainPage.addEventBtnClick();
        mainPage.TodayEventBtnClick();
        AddEventPage addEventPage = new AddEventPage(driver);
        addEventPage.writeEvent(eventName);
        addEventPage.saveBtnClick();
        mainPage.pendEventBtnClick();
        EventsPage eventsPage = new EventsPage(driver);
        // assert
        Assert.assertTrue(eventsPage.checkBox());

    }

    @After
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }


}
