package com.qa.demoblaze.tests;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.demoblaze.base.BaseTest;
import com.qa.demoblaze.utils.Constants;
import com.qa.demoblaze.utils.Errors;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void productInfoSetUp()  {
		productPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	@Description("Success Login Test.......")
	@Severity(SeverityLevel.CRITICAL)
	public void sucessLoginTest() throws InterruptedException {
		Assert.assertTrue(productPage.getSucessLoginMsg());
	}

	@DataProvider
	public Object[][] getProduct() {
		return new Object[][] {
			{"Laptops"}
			
		};
	}
	
	
	
	@Test(dataProvider = "getProduct")
	@Description("Total Product Result List Test.......")
	@Severity(SeverityLevel.CRITICAL)
	public void productResultListTest(String product) throws InterruptedException {
		productInfoPage = productPage.getSelectProduct(product);
		List<String> actProductList = productInfoPage.getMainProductResultList();
		System.out.println(actProductList);
    	Assert.assertEquals(actProductList, Constants.MAIN_PRODUCT_RESULT_LIST , Errors.MAIN_PRODUCT_RESULT_LIST_NOT_CORRECT);

	}

	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"Laptops","2017 Dell 15.6 Inch"}
			
		};
	}
	@Test(dataProvider = "getProductData")
	@Description("Main Product Header Test.......")
	@Severity(SeverityLevel.NORMAL)
	public void selectHeaderTest(String product,String actProduct) throws InterruptedException {
		productInfoPage = productPage.getSelectProduct(product);
		String header = productResultPage.getProductHeaderText();
		Assert.assertTrue(header.contains(actProduct));

	}

	@Test(dataProvider = "getProduct")
	@Description("Product List Count Test.......")
	@Severity(SeverityLevel.MINOR)
	public void productListCountTest(String product) throws InterruptedException {
		productInfoPage = productPage.getSelectProduct(product);
		int actProductCount = productInfoPage.getproductResultCount();
		Assert.assertEquals(actProductCount, Constants.LAPTOPS_PRODUCT_COUNT ,Errors.LAPTOP_PRODUCT_COUNT_NOT_CORRECT);
	}

	@DataProvider
	public Object[][] getProductsData() {
		return new Object[][] {
			{"Laptops","2017 Dell 15.6 Inch","$700 *includes tax"}
			
		};
	}
	
	@Test(dataProvider = "getProductsData")
	@Description("Product Information Test.......")
	@Severity(SeverityLevel.CRITICAL)
	public void productSelectInfoTest(String product,String actProduct,String productPrice) throws InterruptedException {
		productInfoPage = productPage.getSelectProduct(product);
		productResultPage = productInfoPage.getSelectMainProduct(actProduct);
		Map<String, String> actProductInfo = productResultPage.getProductDetails();
		actProductInfo.forEach((k, v) -> System.out.println(k + ":" + v));
		Assert.assertEquals(actProductInfo.get("Product Name"), actProduct);
		Assert.assertEquals(actProductInfo.get("Product price"), productPrice);

	}

}
