package com.miro.tests;

import com.miro.page.SignUpPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutoTest {

    public ChromeDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        System.out.println("test start");
        SignUpPage signUpPage = PageFactory.initElements(driver, SignUpPage.class);
        signUpPage.open();
    }

    @Test //проверка загрузки страницы
    public void TestTitle() {
        String title = driver.getTitle();
        Assert.assertTrue(title.equals("Sign in | Miro"));
    }

    @Test //оба поля пустые
    public void TestNegativeAutorization() {
        SignUpPage signUpPage = PageFactory.initElements(driver, SignUpPage.class);
        signUpPage.clickSubmit();
        Assert.assertTrue(driver.findElement(By.className("signup__error-item")).isDisplayed());
    }

    @Test //пустой пароль
    public void TestPassNegative() {
        SignUpPage signUpPage = PageFactory.initElements(driver, SignUpPage.class);
        signUpPage.inputLogin("test@mail.ru");
        signUpPage.clickSubmit();
        Assert.assertTrue(driver.findElement(By.className("signup__error-item")).isDisplayed());
    }

    @Test //пустой email
    public void TestEmailNegative() {
        SignUpPage signUpPage = PageFactory.initElements(driver, SignUpPage.class);
        signUpPage.inputPass("12345");
        signUpPage.clickSubmit();
        Assert.assertTrue(driver.findElement(By.className("signup__error-item")).isDisplayed());
    }

    @Test //не успешная авторизация
    public void EmailFailPassFail() {
        SignUpPage signUpPage = PageFactory.initElements(driver, SignUpPage.class);
        signUpPage.inputLogin("test@mail.ru");
        signUpPage.inputPass("12345");
        signUpPage.clickSubmit();
        Assert.assertTrue(driver.findElement(By.className("signup__error-item")).isDisplayed());
    }

    @Test //успешная авторизация
    public void GoodAutorization() {
        SignUpPage signUpPage = PageFactory.initElements(driver, SignUpPage.class);
        signUpPage.inputLogin("tenderness1708@gmail.com");
        signUpPage.inputPass("17KTNFNm!");
        signUpPage.clickSubmit();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.urlContains("dashboard"));
        Assert.assertTrue(driver.findElement(By.className("user-profile")).isDisplayed());
    }

    @Test //успешная авторизация с пробелом
    public void GoodAutorizationEmailSpace() {
        SignUpPage signUpPage = PageFactory.initElements(driver, SignUpPage.class);
        signUpPage.inputLogin("  tenderness1708@gmail.com  ");
        signUpPage.inputPass("17KTNFNm!");
        signUpPage.clickSubmit();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.urlContains("dashboard"));
        Assert.assertTrue(driver.findElement(By.className("user-profile")).isDisplayed());
    }

    @Test //не успешная авторизация (неверный пароль)
    public void BadAutorizationWrongPass() {
        SignUpPage signUpPage = PageFactory.initElements(driver, SignUpPage.class);
        signUpPage.inputLogin("tenderness1708@gmail.com");
        signUpPage.inputPass("12345");
        signUpPage.clickSubmit();
        Assert.assertTrue(driver.findElement(By.className("signup__error-item")).isDisplayed());
    }

    @Test //не успешная авторизация (неверный логин)
    public void BadAutorizationWrongLogin() {
        SignUpPage signUpPage = PageFactory.initElements(driver, SignUpPage.class);
        signUpPage.inputLogin("tenderness1708@@gmail.com");
        signUpPage.inputPass("17KTNFNm!");
        signUpPage.clickSubmit();
        Assert.assertTrue(driver.findElement(By.className("signup__error-item")).isDisplayed());
    }

}
