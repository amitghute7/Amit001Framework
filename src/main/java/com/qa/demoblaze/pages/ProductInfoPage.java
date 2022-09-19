package com.qa.demoblaze.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.demoblaze.utils.Constants;
import com.qa.demoblaze.utils.ElementUtil;

import io.qameta.allure.Step;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleutil;

	private By products = By.cssSelector("h4.card-title");
	private By cartProduct = By.xpath("//*[@id=\"tbodyid\"]/tr/td[2]");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);

	}

	@Step("Get Product Header....... ")
	public List<String> getproductHeader() {
		List<WebElement> cartProducts = eleutil.waitForElementsToBeVisible(cartProduct, Constants.DEFAULT_TIMEOUT);
		List<String> actProductLists = new ArrayList<String>();
		for (WebElement e : cartProducts) {
			String textlist = e.getText();
			actProductLists.add(textlist);
		}
		return actProductLists;
	}

	@Step("Get Product Result Count......")
	public int getproductResultCount() throws InterruptedException {
		Thread.sleep(2000);
		return eleutil.waitForElementsToBeVisible(products, Constants.DEFAULT_TIMEOUT).size();

	}

	@Step("Main Product Page Url........ ")
	public String mainProductPgUrl() {
		String url = eleutil.waitForUrl(Constants.DEFAULT_TIMEOUT, "/index.html#");
		System.out.println(url);
		return url;
	}

	@Step("Select Main Product.........")
	public ProductResultPage getSelectMainProduct(String mainProduct) throws InterruptedException {
		Thread.sleep(2000);
		List<WebElement> ActProduct = eleutil.waitForElementsToBeVisible(products, Constants.DEFAULT_TIMEOUT);
		System.out.println("selected Actual Product name is : " + mainProduct);
		for (WebElement e : ActProduct) {
			String Text = e.getText();
			if (Text.equalsIgnoreCase(mainProduct)) {
				e.click();
				break;
			}
		}

		return new ProductResultPage(driver);
	}

	@Step("Get Main Product Result List............")
	public List<String> getMainProductResultList() throws InterruptedException {
		Thread.sleep(2000);
		List<WebElement> productList = eleutil.waitForElementsToBeVisible(products, Constants.DEFAULT_TIMEOUT);
		
		List<String> actProductList = new ArrayList<String>();
		for (WebElement e : productList) {
			String text = e.getText();
			actProductList.add(text);
		}
		return actProductList;

	}

}
