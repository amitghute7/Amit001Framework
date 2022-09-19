package com.qa.demoblaze.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.demoblaze.utils.Constants;
import com.qa.demoblaze.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	// private constructors

	private WebDriver driver;
	private ElementUtil eleutil;
	private By clicklogin = By.cssSelector("#login2.nav-link");
	private By username = By.xpath("//*[@id=\"loginusername\"]");
	private By password = By.id("loginpassword");
	private By loginbtn = By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]");
	private By welcomeuser = By.xpath("//*[@id=\"nameofuser\"]");
	private By headerText = By.id("nava");

	private By closeBtn = By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[1]");
	
	// public Page Actions
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	@Step("Login to the DemoBlaze Application with Username {0} and Password {1}.......")
	public ProductPage doLogin(String un, String Pwd) throws InterruptedException {
		if(eleutil.doIsDisplayed(clicklogin)) {
		eleutil.waitForElementToBeVisible(clicklogin, Constants.DEFAULT_TIMEOUT).click();
		Thread.sleep(2000);
		if(eleutil.doIsDisplayed(username)) {
			eleutil.waitForElementToBeVisible(username, Constants.DEFAULT_TIMEOUT).sendKeys(un);
		eleutil.doSendKeys(password, Pwd);
		eleutil.doClick(loginbtn);
		}
		}
		return new ProductPage(driver);

	}
	
	@Step("Login to the DemoBlaze Application with Username {0} and Password {1}.......")
	public boolean doInvalidLogin(String un, String Pwd) {
		WebElement loginBtn=eleutil.waitForElementToBeVisible(clicklogin, Constants.DEFAULT_TIMEOUT);
	if(loginBtn.isDisplayed()) {
		loginBtn.click();
		WebElement email_ele=eleutil.waitForElementToBeVisible(username, Constants.DEFAULT_TIMEOUT);
		email_ele.clear();
		email_ele.sendKeys(un);
		eleutil.doSendKeys(password, Pwd);
		eleutil.doClick(loginbtn);
		Alert alert= eleutil.waitForAlert(Constants.DEFAULT_TIMEOUT);
		System.out.println(alert.getText());
		alert.accept();
		eleutil.doClick(closeBtn);
		return true;
	}
	return false;
	}

	
	

	@Step("Checking Header Text Displayed or not.... ")
	public boolean getHeaderTextExist() throws InterruptedException {
		Thread.sleep(2000);
		eleutil.waitForElementToBeVisible(headerText, Constants.DEFAULT_TIMEOUT);
		return eleutil.doIsDisplayed(headerText);

	}

	@Step("Checking Login Header of Sucessfully login or not.....")
	public boolean getLoginSucess() throws InterruptedException {
		Thread.sleep(3000);
		return eleutil.doIsDisplayed(welcomeuser);
	}

	@Step("Getting login page tittle......")
	public String getLoginPageTitle() {
		return eleutil.waitForTitleIs(Constants.LOGIN_PAGE_TITLE_TIMEOUT, Constants.LOGIN_PAGE_TITLE);

	}

	@Step("Getting login page url......")
	public String getLoginPageUrl() {
		return eleutil.waitForUrlToBe(Constants.LOGIN_PAGE_TITLE_TIMEOUT, Constants.LOGIN_PAGE_URL);
	}

}
