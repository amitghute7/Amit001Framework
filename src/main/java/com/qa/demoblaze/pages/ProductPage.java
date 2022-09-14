package com.qa.demoblaze.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.demoblaze.utils.Constants;
import com.qa.demoblaze.utils.ElementUtil;

import io.qameta.allure.Step;

public class ProductPage {
	private WebDriver driver;
	private ElementUtil eleutil;

	private By sucessLoginMsg = By.xpath("//a[text()='Welcome amitghute']");
	private By categoryList = By.xpath("//*[@id=\"itemc\"]");

	public ProductPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	@Step("Get Sucess Login Msg........")
	public boolean getSucessLoginMsg() throws InterruptedException {
		Thread.sleep(2000);
		eleutil.waitForElementToBeVisible(sucessLoginMsg, Constants.DEFAULT_TIMEOUT);
		return eleutil.doIsDisplayed(sucessLoginMsg);

	}

	@Step("Get Select Product........")
	public ProductInfoPage getSelectProduct(String productName) throws InterruptedException {
		if (getSucessLoginMsg()) {
			List<WebElement> ProductList = eleutil.getElements(categoryList);

			System.out.println("Product name is : " + productName);
			for (WebElement e : ProductList) {
				String text = e.getText();
				if (text.equalsIgnoreCase(productName)) {
					e.click();
					break;
				}
			}
		}
		return new ProductInfoPage(driver);

	}

}
