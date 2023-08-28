package org.example.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class OrderPage {
    private final By nameField = By.xpath("//input[@placeholder = '* Имя']");

    private final By surNameField = By.xpath("//input[@placeholder = '* Фамилия']");

    private final By addressField = By.xpath("//input[@placeholder = '* Адрес: куда привезти заказ']");

    private final By metroField = By.xpath("//input[@placeholder = '* Станция метро']");

    private final By phoneField = By.xpath("//input[@placeholder = '* Телефон: на него позвонит курьер']");

    private final By buttonMoveNext = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    private final By scooterArrivalDateField = By.xpath("//input[@placeholder = '* Когда привезти самокат']");

    private final By buttonScooterRentalField = By.className("Dropdown-placeholder");

    private final By commentsField = By.xpath("//input[@placeholder = 'Комментарий для курьера']");

    private final By scooterNewOrder = By.xpath("//div[@class = 'Order_Buttons__1xGrp']/button[text()='Заказать']");

    private final By scooterNewOrderConfirmationYes = By.xpath("//div[@class = 'Order_Buttons__1xGrp']/button[text()='Да']");

    private final By orderConfirmed = By.xpath("//*[text() = 'Заказ оформлен']");
    private final WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setNameField(String clientName){
        driver.findElement(nameField).sendKeys(clientName);
    }

    public void setSurNameField(String clientSurName){
        driver.findElement(surNameField).sendKeys(clientSurName);
    }

    public void setAddressField(String clientAddress){
        driver.findElement(addressField).sendKeys(clientAddress);
    }

    public void setPhone(String clientPhone){
        driver.findElement(phoneField).sendKeys(clientPhone);
    }

    public void clickMetro(){
        driver.findElement(metroField).click();
    }

    public void setMetro(String selectMetroNumber){
        driver.findElement(By.cssSelector("[data-index=" + "\"" + selectMetroNumber + "\"" + "]")).click();
    }

    public void clickMoveNext(){
        driver.findElement(buttonMoveNext).click();
    }

    public void setArrivalDate(String scooterArrivalDate){
        driver.findElement(scooterArrivalDateField).sendKeys(scooterArrivalDate);
        driver.findElement(scooterArrivalDateField).sendKeys(Keys.ENTER);
    }

    public void setScooterRental(String scooterRentalDates){
        driver.findElement(buttonScooterRentalField).click();
        driver.findElement(By.xpath("//*[text() = '" + scooterRentalDates + "']")).click();
    }

    public void enterComments (String commentsInput) {
        driver.findElement(commentsField).sendKeys(commentsInput);

    }

    public void colourSelection (String scooterColour) {
        driver.findElement(By.id(scooterColour)).click();
    }

    public void clickCreateOrder () {
        driver.findElement(scooterNewOrder).click();
    }

    public void clickOrderConfirmation () {
        driver.findElement(scooterNewOrderConfirmationYes).click();
    }

    public boolean isOrderConfirmed(){
        return driver.findElement(orderConfirmed).isDisplayed();
    }
}
