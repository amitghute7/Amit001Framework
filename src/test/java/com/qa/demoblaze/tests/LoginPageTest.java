package com.qa.demoblaze.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.demoblaze.base.BaseTest;
import com.qa.demoblaze.utils.Constants;
import com.qa.demoblaze.utils.Errors;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Epic("Epic 100- Design login page for DemoBlaze application")
@Story("US 101-Design login page features")

public class LoginPageTest extends BaseTest {

	@Test
	@Description("Login Page Tittle Test.......")
	@Severity(SeverityLevel.NORMAL)
	public void loginPageTittleTest() {
		String actTittle = loginpage.getLoginPageTitle();
		System.out.println("Page Tittle is : " + actTittle);
		Assert.assertEquals(actTittle, Constants.LOGIN_PAGE_TITLE, Errors.LOGIN_PAGE_TITLE_MISMATCHED);
	}

	@Test
	@Description("Login Page Header Test.......")
	@Severity(SeverityLevel.NORMAL)
	public void loginHeaderTest() throws InterruptedException {
		boolean headerText = loginpage.getHeaderTextExist();
		Assert.assertTrue(headerText);
	}

	@Test
	@Description("Login Page Url Test.......")
	@Severity(SeverityLevel.NORMAL)
	public void loginPageUrlTest() {
		String actUrl = loginpage.getLoginPageUrl();
		System.out.println("Page Url is : " + actUrl);
		Assert.assertEquals(actUrl, Constants.LOGIN_PAGE_URL,Errors.LOGIN_PAGE_URL_IS_NOT_MATCH);
	}

	@Test
	@Description("Login Test with correct username and correct password........")
	@Severity(SeverityLevel.BLOCKER)
	public void doLoginTest() throws InterruptedException  {
		productPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(loginpage.getLoginSucess());
	}

}
