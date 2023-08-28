package org.example;

import org.example.page.MainPage;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import java.io.File;

import static org.example.config.AppConfig.DRIVER_LOCATION;

@RunWith(Parameterized.class)
public class ScooterTestMainPage {
    WebDriver driver;
    MainPage mainPage;
    private final String expectedAnswer;
    private final String questionId;

    public ScooterTestMainPage(String questionId, String expectedAnswer){
        this.questionId = questionId;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                { "0", "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",},
                { "1", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                { "2", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                { "3", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                { "4", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                { "5", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                { "6", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                { "7", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Before
    public void initDriver(){
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(DRIVER_LOCATION))
                .build();
        driver = new ChromeDriver(service);
    }

    @Test
    public void checkListOfMainAnswers() {
        mainPage = new MainPage(driver);
        mainPage.clickAcceptCookies();
        mainPage.clickOnQuestionId(questionId);
        String actualAnswer = mainPage.getActualAnswer(questionId);
        Assert.assertEquals("Ожидаемый текст не верный или не появился.",expectedAnswer,actualAnswer);

    }

    @After
    public void quitDriver() {
        driver.quit();
    }

}
