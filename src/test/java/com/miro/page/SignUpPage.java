package com.miro.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class SignUpPage {

    WebDriver driver;
    public SignUpPage(WebDriver driver){
                this.driver = driver;
    }
    public void open() {
        driver.get("https://miro.com/login/");
    }

    @FindBy(id= "password")
    private WebElement fillPass;

    @FindBy(id = "email")
    private WebElement fillEmail;

    @FindBy(className = "signup__submit")
    private WebElement SubmitButton;

    public void inputPass(String pass) { fillPass.sendKeys(pass); }
    public void inputLogin (String login) { fillEmail.sendKeys(login); }
    public void clickSubmit() { SubmitButton.click(); }

}
