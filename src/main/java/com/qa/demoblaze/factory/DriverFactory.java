package com.qa.demoblaze.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;


import com.qa.demoblaze.utils.Browser;
import com.qa.demoblaze.utils.Errors;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public static final Logger log = Logger.getLogger(DriverFactory.class);

	/**
	 * This method is used to initialize Webdriver on the basis of given browser
	 * name. This method will take care of local and remote execution
	 * 
	 * @param Browsername
	 * @return
	 */

	public WebDriver init_driver(Properties prop) {
		String Browsername = prop.getProperty("browser").trim();
		System.out.println("Browser Name is : " + Browsername);
		log.info("Browser Name is : " + Browsername);
		highlight = prop.getProperty("highlight").trim();
		optionsManager = new OptionsManager(prop);

		if (Browsername.equalsIgnoreCase(Browser.CHROME_BROWSER_VALUE)) {
			log.info("running test on chrome browser.....");
			WebDriverManager.chromedriver().setup();
			// driver=new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		} else if (Browsername.equalsIgnoreCase(Browser.FIREFOX_BROWSER_VALUE)) {
			WebDriverManager.firefoxdriver().setup();
			// driver=new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		} else if (Browsername.equalsIgnoreCase(Browser.SAFARI_BROWSER_VALUE)) {
			// driver=new SafariDriver();
			tlDriver.set(new SafariDriver());
		} else {
			System.out.println(Errors.BROWSER_NOT_FOUND_ERROR_MSSG + Browsername);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
          log.info(prop.getProperty("url") + ".....url is launched....");
		return getDriver();

	}

	/**
	 * this will return the thread local copy of the WebDriver(driver)
	 * 
	 * @return
	 */
	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * This method is used to initialize properties on the basis of given
	 * environment environment: QA/Stage/Dev/PROD
	 * 
	 * @return this return prop
	 */

	public Properties init_prop() {

		prop = new Properties();
		FileInputStream ip = null;

		// mvn clean install -Denv="qa"
		// mvn clean install

		String envName = System.getProperty("env");
		System.out.println("Running tests on environment : " + envName);
          log.info("Running tests on environment : " + envName);
		if (envName == null) {
			System.out.println("No environment is given....hence running it on QA");
			log.info("No environment is given....hence running it on QA");
			try {
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		} else {
			try {
				switch (envName.toLowerCase()) {
				case "qa":
					log.info("running it on QA");
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "stage":
					log.info("running it on Stage");
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "dev":
					log.info("running it on Dev");
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "uat":
					log.info("running it on UAT");
					ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
					break;
				case "prod":
					log.info("running it on Prod");
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;
				default:
					System.out.println("Please pass the right environment name: " + envName);
					log.error("Please pass the right environment name: " + envName);
					log.warn("env is not found....");
					break;
				}
			} catch (Exception e) {

			}
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

	}

	/**
	 * this is the getScreenshot method provided by selenium
	 * 
	 * @return
	 */
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

}
