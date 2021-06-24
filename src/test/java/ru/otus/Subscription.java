package ru.otus;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.otus.RunCucumberTest;
import ru.otus.ServerConfig;

public class Subscription {


    private Logger logger = LogManager.getLogger(RunCucumberTest.class);
    protected static WebDriver driver;

    @Given("Открыта главная страница")
    public void открытаГлавнаяСтраница() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
        driver.get(cfg.hostnameOtus());

    }

    @When("Оформлена подписка")
    public void оформленаПодписка() {
        //Ищем элемент ввода email и вводим email
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.ByXPath.xpath("//input[@type='email'][@class='input footer2__subscribe-input']")));
        element.clear();
        element.sendKeys("a@a.a");
        element.sendKeys("TAB");

        //жмём кнопку Подписаться
        driver.findElement(By.ByXPath.xpath("//button[contains(text(),'Подписаться')]")).click();

        //Ожидаем успешного оповещения
        WebElement text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.ByXPath.xpath(".//p[@class='subscribe-modal__success']")));

        //Сравниваем с ожидаемым
        String actualText = text.getText();
        String textCheck = "Вы успешно подписались";
        logger.info("Считан текст: "+actualText);
        logger.info("Ожидался текст "+textCheck);
        Assert.assertEquals(textCheck,actualText);
    }

    @Then("Подписка успешна")
    public void подпискаУспешна() {
        System.out.println("Подписка успешно оформлена");

        if (driver != null) {
            driver.quit();
            logger.info("Драйвер погашен");
        }
    }
}
