package com.qa.demoblaze.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.demoblaze.factory.DriverFactory;
import com.qa.demoblaze.pages.CartPage;
import com.qa.demoblaze.pages.LoginPage;
import com.qa.demoblaze.pages.ProductInfoPage;
import com.qa.demoblaze.pages.ProductPage;
import com.qa.demoblaze.pages.ProductResultPage;
import com.qa.demoblaze.utils.ElementUtil;




public class BaseTest {
	
	public WebDriver driver;
	public LoginPage loginpage;
	public ProductPage productPage;
	public ProductInfoPage productInfoPage;
	public  ProductResultPage productResultPage;
	public CartPage cartPage;
	public DriverFactory df;
	public Properties prop;
    public ElementUtil eleutil;
    
    
	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.init_prop();
		driver = df.init_driver(prop);
		loginpage = new LoginPage(driver);
		}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
