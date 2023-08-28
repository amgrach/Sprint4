package org.example;

import org.example.page.MainPage;
import org.example.page.OrderPage;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import java.io.File;

import static org.example.config.AppConfig.DRIVER_LOCATION;

public class ScooterTestNewOrder {

    WebDriver driver;
    MainPage mainPage;
    OrderPage orderPage;
    private String clientName = "Иван" ;
    private String clientSurName = "Иванов" ;
    private String clientAdress = "проспект Мира" ;
    private String clientMetro = "5";
    private String clientPhone = "89998887766";

    private String scooterArrivalDate = "01.01.2023";

    private String scooterRentalDates = "сутки";

    private String commentsForDelivery = "код двери 333";

    private String scooterColour = "grey";

    @Before
    public void initDriver(){
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(DRIVER_LOCATION))
                .build();
        driver = new ChromeDriver(service);
    }

    @Test
    public void checkNewScooterOrder(){
        mainPage = new MainPage(driver);
        mainPage.clickAcceptCookies();
        mainPage.clickOnNewOrderButton();
        orderPage = new OrderPage(driver);
        orderPage.setNameField(clientName);
        orderPage.setSurNameField(clientSurName);
        orderPage.setAddressField(clientAdress);
        orderPage.setPhone(clientPhone);
        orderPage.clickMetro();
        orderPage.setMetro(clientMetro);
        orderPage.clickMoveNext();
        orderPage.setArrivalDate(scooterArrivalDate);
        orderPage.setScooterRental(scooterRentalDates);
        orderPage.enterComments(commentsForDelivery);
        orderPage.colourSelection(scooterColour);
        orderPage.clickCreateOrder();
        orderPage.clickOrderConfirmation();
        boolean actualAnswer = orderPage.isOrderConfirmed();
        Assert.assertEquals("Заказ не смог быть оформлен",true, actualAnswer);
    }

    @After
    public void quitDriver() {
        driver.quit();
    }

}
