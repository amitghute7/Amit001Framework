package com.qa.demoblaze.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.demoblaze.utils.Constants;
import com.qa.demoblaze.utils.ElementUtil;

import io.qameta.allure.Step;

public class ProductResultPage {

	private WebDriver driver;
	private ElementUtil eleutil;

	private By productDetails = By.cssSelector("h2.name,h3.price-container");
	private By productHeader = By.cssSelector("div#tbodyid h2.name");
	private By addToCartBtn = By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a");
	private By cartBtn = By.xpath("//a[text()='Cart']");

	public ProductResultPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	@Step("Get Product Header Text........")
	public String getProductHeaderText() {
		if (eleutil.doIsDisplayed(productHeader)) {
			return eleutil.doElementGetText(productHeader).trim();

		}
		return null;
	}

	@Step("Get Product Add To Cart.........")
	public CartPage getProductAddTocart() {
		if (getAddToCartExist()) {
			eleutil.waitForElementToBeVisible(addToCartBtn, Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_POLLING_TIME);
			eleutil.doActionsClick(addToCartBtn);
			eleutil.waitForAlert(Constants.DEFAULT_TIMEOUT).accept();
			eleutil.doClick(cartBtn);
		}
		return new CartPage(driver);
	}

	@Step("Get Add To Cart Button Exist or not.........")
	public boolean getAddToCartExist() {
		return eleutil.waitForElementPresent(addToCartBtn, Constants.DEFAULT_TIMEOUT).isDisplayed();
	}

	@Step("Get Product All Deatils..........")
	public Map<String, String> getProductDetails() {
		Map<String, String> productInfoData = new HashMap<String, String>();
		List<WebElement> productdetails = eleutil.waitForElementsToBeVisible(productDetails, Constants.DEFAULT_TIMEOUT);
		for (WebElement e : productdetails) {
			String productname = productdetails.get(0).getText().trim();
			String productprice = productdetails.get(1).getText().trim();
			productInfoData.put("Product Name", productname);
			productInfoData.put("Product price", productprice);

		}

		return productInfoData;

	}

}
