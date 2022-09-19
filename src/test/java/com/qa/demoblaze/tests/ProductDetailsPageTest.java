package com.qa.demoblaze.tests;

import static org.testng.Assert.assertEquals;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.demoblaze.base.BaseTest;
import com.qa.demoblaze.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 200- Design product info page for DemoBlaze application")
@Story("US 102-Design product info page features")

public class ProductDetailsPageTest extends BaseTest {

	@BeforeClass
	public void ProductDetailsPageTestSetUp() throws InterruptedException {
		productPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		productInfoPage = productPage.getSelectProduct("Laptops");

	}
	

	@DataProvider
	public Object[][] getProductDatas() {
		return new Object[][] {
			{"Laptops","2017 Dell 15.6 Inch"}
			
		};
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"2017 Dell 15.6 Inch"}
			
		};
	}
	
	
	@Test(dataProvider = "getProductData")
	@Description("Product add to cart Test.......")
	@Severity(SeverityLevel.BLOCKER)
	public void productAddToCartTest(String actProduct) throws InterruptedException {
	//	productInfoPage = productPage.getSelectProduct(product);
		productResultPage = productInfoPage.getSelectMainProduct(actProduct);
		cartPage = productResultPage.getProductAddTocart();
		Assert.assertTrue(productInfoPage.getproductHeader().contains(actProduct));
	}

	@Test
	@Description("Product Cart Page Header Test.......")
	@Severity(SeverityLevel.NORMAL)
	public void productcartPageHeaderTest() throws InterruptedException {
		Assert.assertTrue(cartPage.cartPageHeader().contains(Constants.CART_PAGE_HEADER_TEXT));
	}
	
	
@DataProvider
	public Object[][] getCustomerDeatail() {
		return new Object[][] {
			{"Amit Patil", "India", "Pune", "123456789", "September", "2022"}
			
		};
	}
	
	
	@Test(dataProvider = "getCustomerDeatail")
	@Description("Select Main Product Test.......")
	@Severity(SeverityLevel.BLOCKER)
	public void selectProductTest(String Name,String state,String city,String cardNo,String Month,String year) throws InterruptedException {
		cartPage.selectProduct("HTC One M9");
		cartPage.cartCount();
		cartPage.purchaseOrder(Name, state, city, cardNo,Month,year);
		Assert.assertTrue(cartPage.doSucessOrderPurchase().contains(Constants.SUCESSFUL_PURCHASE_MESSAGE));
	}

}
