package org.example.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.config.AppConfig.APP_URL;
import static org.example.config.AppConfig.IS_THIS_A_BOTTOM_ORDER_TEST;

public class MainPage {
    private final WebDriver driver;
    private final By acceptCookies = By.className("App_CookieButton__3cvqF");

    private final By headerButtonOrder = By.xpath("//button[@class='Button_Button__ra12g']");
    private final By bottomButtonOrder = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        driver.get(APP_URL);
    }

    public void clickAcceptCookies() {
        driver.findElement(acceptCookies).click();
    }

    public void clickOnQuestionId(String questionNumber) {

        driver.findElement(By.id("accordion__heading-" + questionNumber)).click();

    }

    public String getActualAnswer(String answerNumber) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("accordion__panel-" + answerNumber)));
        return driver.findElement(By.id("accordion__panel-" + answerNumber)).getText();

    }

    public void clickOnNewOrderButton (){
        if (IS_THIS_A_BOTTOM_ORDER_TEST) {
            driver.findElement(bottomButtonOrder).click();
        } else {
            driver.findElement(headerButtonOrder).click();
        }
    }

}
