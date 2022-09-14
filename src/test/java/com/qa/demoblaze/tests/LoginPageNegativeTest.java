package com.qa.demoblaze.tests;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.demoblaze.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100- Design login page for DemoBlaze application")
@Story("US 101-Design login page features")

public class LoginPageNegativeTest extends BaseTest {
	
	
	@DataProvider
	public Object[][] getInvalidLoginData() {
		return new Object[][] {
			{"tom212@gmail.com","12321"},
			{"@@@m","123"},
			{" ","tom"},
			{" ","#$42 "}
		};
	}
	
	
	
	@Test(dataProvider = "getInvalidLoginData")
	@Description("Login Test with incorrect username and incorrect password........")
	@Severity(SeverityLevel.NORMAL)
	public void invalidLoginTest(String username, String password) {
		Assert.assertTrue(loginpage.doInvalidLogin(username, password));
		
	}
	
	
	

}
