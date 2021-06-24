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



public class InfoStepsDef {

    private Logger logger = LogManager.getLogger(RunCucumberTest.class);
    protected static WebDriver driver;


    @Given("Открыта страница FAQ")
    public void открытаСтраницаFAQ() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
        driver.get(cfg.hostnameOtus());
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.ByXPath.xpath("//a[@title='FAQ'][@class='header2_subheader-link']")));
        element.click();
        logger.info("Открыта страница "+driver.getTitle());


    }

    @When("Отображается текст")
    public void отображаетсяТекст() {
        //Считываем и сверяем текст
        WebElement question = driver.findElement(By.ByXPath.xpath(".//div[text() = 'Где посмотреть программу интересующего курса?']"));
        question.click();
        String textCheck = "Программу курса в сжатом виде можно увидеть на странице курса после блока с преподавателями. Подробную программу курса можно скачать кликнув на “Скачать подробную программу курса”";
        String text = driver.findElement(By.ByXPath.xpath(".//div[text() = 'Где посмотреть программу интересующего курса?']/following-sibling::*")).getText();
        logger.info("Считан текст: "+text);
        logger.info("Ожидался текст "+textCheck);
        Assert.assertEquals(textCheck,text);


    }

    @Then("Текст корректный")
    public void текстКорректный() {
        System.out.println("Информация о курсе корректная");
        if (driver != null) {
            driver.quit();
            logger.info("Драйвер погашен");
        }
    }
}
