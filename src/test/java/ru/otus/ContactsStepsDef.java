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

public class ContactsStepsDef {

    private Logger logger = LogManager.getLogger(RunCucumberTest.class);
    protected static WebDriver driver;



    @Given("Открыта страница Контакты")
    public void открытаСтраницаКонтакты() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
        driver.get(cfg.hostnameOtus());
        //Открываем страницу Контакты
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.ByXPath.xpath("//a[@title='Контакты'][@class='header2_subheader-link']")));
        element.click();
        logger.info("Открыта страница "+driver.getTitle());
    }


    @When("Отображается адрес")
    public void отображаетсяАдрес() {
        //Считываем и сверяем адрес
        WebElement address = driver.findElement(By.ByXPath.xpath(".//div[text() = 'Адрес']/following-sibling::*"));
        logger.info("Адрес на странице = "+address.getText());
        String addressCheck = "125167, г. Москва, Нарышкинская аллея., д. 5, стр. 2, тел. +7 499 938-92-02";
        Assert.assertEquals(addressCheck,address.getText());
    }

    @Then("Адрес корректный")
    public void адресКорректный() {
        System.out.println("Адрес корректный");
        if (driver != null) {
            driver.quit();
            logger.info("Драйвер погашен");
        }
    }


}
