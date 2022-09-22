package com.qa.demoblaze.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.demoblaze.utils.Constants;
import com.qa.demoblaze.utils.ElementUtil;

import io.qameta.allure.Step;

public class CartPage {

	private WebDriver driver;
	private ElementUtil eleutil;

	private By cartProduct = By.xpath("//tbody[@id='tbodyid']/child::tr");
	private By headerText = By.xpath("//*[@id=\"page-wrapper\"]/div/div[1]/h2");
	private By deletebtn = By.xpath("//a[text()='Delete']");
	private By placeorderBtn = By.cssSelector("#page-wrapper > div > div.col-lg-1 > button");
	private By homeBtn = By.xpath("//*[@id=\"navbarExample\"]/ul/li[1]/a");
	private By Products = By.cssSelector("div.card-block h4");
	private By addToCartBtn = By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a");
	private By productHeader = By.cssSelector("div#tbodyid h2");
	private By clickCart = By.xpath("//*[@id=\"cartur\"]");

	private By name = By.id("name");
	private By country = By.id("country");
	private By city = By.id("city");
	private By creditcard = By.id("card");
	private By month = By.id("month");
	private By year = By.id("year");
	private By purchasebtn = By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]");

	private By successPurchase = By.xpath("/html/body/div[10]/h2");
	private By orderDetails = By.cssSelector("body > div.sweet-alert.showSweetAlert.visible > p ");
	private By okBtn = By.xpath("/html/body/div[10]/div[7]/div/button");
	private By logOutBtn = By.id("logout2");

	public CartPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	@Step("Get Cart Page Header..........")
	public String cartPageHeader() throws InterruptedException {
		Thread.sleep(3000);
		return eleutil.doElementGetText(headerText).trim();
	}

	@Step("Get Product Total List Count........")
	public int productListCount() {
		if (eleutil.doIsDisplayed(headerText)) {
			return eleutil.waitForElementsToBeVisible(cartProduct, Constants.DEFAULT_TIMEOUT).size();
		}
		return 0;
	}

	@Step("Get Delete Button......")
	public void deleteBtn() {
		eleutil.doClick(deletebtn);
	}

	@Step("Get Order Place Button........")
	public void orderPlace() {
		eleutil.doClick(placeorderBtn);
	}

	@Step("Get Select Product.........")
	public void selectProduct(String productName) {
		for (int i = 0; i < 3; i++) {
			if (eleutil.doIsDisplayed(homeBtn)) {
				eleutil.doClick(homeBtn);
				List<WebElement> products = eleutil.waitForElementsToBeVisible(Products, Constants.DEFAULT_TIMEOUT);
				System.out.println("selectd Product name is : " + productName);
				for (WebElement e : products) {
					String text = e.getText();
					if (text.equals(productName)) {
						e.click();
						break;
					}

				}
				eleutil.waitForElementToBeVisible(productHeader, Constants.DEFAULT_TIMEOUT);
				eleutil.doActionsClick(addToCartBtn);
				eleutil.acceptAlert(Constants.DEFAULT_TIMEOUT_FOR_ALERT);
				eleutil.doClick(homeBtn);
				eleutil.doActionsClick(clickCart);

			}
		}
	}

	@Step("Get Total Added Product Cart Count............")
	public void cartCount() throws InterruptedException {
		int count = productListCount();
		System.out.println(count);
		int i = count;
		while (i >= 2) {
			List<WebElement> delete = eleutil.waitForElementsToBeVisible(deletebtn, Constants.DEFAULT_TIMEOUT);
			if (delete.size() > 2) {
				eleutil.doClick(deletebtn);
				Thread.sleep(3000);

			} else {
				break;
			}

		}
		i--;

	}

	@Step("Get Place Order Button.........")
	public void palceOder() {
		WebElement placeOrderbtn = eleutil.waitForElementToBeVisible(placeorderBtn, Constants.DEFAULT_TIMEOUT);
		placeOrderbtn.click();
	}

	@Step("Purchase Oder--and Customers Details  name{0},Country{1},City{2},CreditCard{3},Month{4},Year{5}")
	public void purchaseOrder(String name, String country, String city, String creditCard, String month, String year) {
		eleutil.waitForElementToBeVisible(placeorderBtn, Constants.DEFAULT_TIMEOUT);
		if (eleutil.getElements(cartProduct).size() == 2) {
			eleutil.waitForElementToBeVisible(placeorderBtn, Constants.DEFAULT_TIMEOUT).click();
			WebElement username = eleutil.waitForElementToBeVisible(this.name, Constants.DEFAULT_TIMEOUT);
			username.sendKeys(name);
			eleutil.doSendKeys(this.country, country);
			eleutil.doSendKeys(this.city, city);
			eleutil.doSendKeys(this.creditcard, creditCard);
			eleutil.doSendKeys(this.month, month);
			eleutil.doSendKeys(this.year, year);
			eleutil.doClick(purchasebtn);
		}
	}

	@Step("Do Sucess Order Purchase.........")
	public String doSucessOrderPurchase() {
		if (eleutil.getElement(successPurchase).isDisplayed()) {
			String text = eleutil.getElement(successPurchase).getText();
			System.out.println(text);
			oderDetails();
			accLogout();

			return text;
		}
		return null;
	}

	@Step("Order Details......")
	public void oderDetails() {
		WebElement orderdetails = eleutil.waitForElementToBeVisible(orderDetails, Constants.DEFAULT_TIMEOUT);
		String text = orderdetails.getText();
		System.out.println(text);
		eleutil.doClick(okBtn);
	}

	@Step("Account Logout......")
	public void accLogout() {
		eleutil.waitForElementToBeVisible(logOutBtn, Constants.DEFAULT_TIMEOUT).click();
		;
	}

}
