package com.qa.hubspot.tests;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

import org.apache.log4j.Logger;


//   Allure Features
@Epic("Epic - 101 : create login features")
@Feature("US - 501 : create test for login on HubSpot")
public class LoginPageTest {

	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	Credentials userCred;

	//for log properties
	Logger log = Logger.getLogger(LoginPageTest.class);
	
	@BeforeMethod (alwaysRun=true)
	
	//For parallel Testing
	@Parameters(value = {"browser"})    
	
	public void setUp(String browser) {  //For parallel Testing
	
		//For parallel Testing
		String browserName=null;
		
		basePage = new BasePage();
		prop = basePage.init_proporties();
		
		//For parallel Testing
		if(browser.equals(null)) {
		browserName = prop.getProperty("browser");	
		}else {
		browserName=browser;	
		}
	 
		
		
		driver = basePage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		
		//for log properties
        log.info("url is launched "+ prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
	}

	// Groups annotatins works with Before and After Method
	
	@Test(priority = 1, groups="sanity",description="verify page title",enabled=true)
	@Description("verify Login page title ")
	@Severity(SeverityLevel.NORMAL)
	public void verifyLoginPageTitle() throws InterruptedException {
		
		//for log properties
		log.info("starting ------------->>> verifyLoginPagetest");
		String title = loginPage.getPageTitle();
		System.out.println("Login Page Title  is " + title);
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE);
		 
		//for log properties
		log.info("ending ------------->>> verifyLoginPagetest");
		log.warn("Some warning");
		log.error("Some error");
		log.fatal("Fatal error");
	}

	@Test(priority = 2  ,description="verify sign Up",enabled=true)
	@Description("verify Login page sign Up link ")
	@Severity(SeverityLevel.NORMAL)
   public void verifySignUpLinkTest() {
		
   Assert.assertTrue(loginPage.checkSignUpLink());

	}

	@Test(priority = 3 ,description="verify do login test",enabled= true)
	
      //   Allure Features
	@Description("verify Login Page  Test feature")
	@Severity(SeverityLevel.BLOCKER)
     public void loginTest() {
		HomePage homePage=loginPage.doLogin(userCred);
		String accountName=homePage.getLoginUserAccountName();
		System.out.println("logged in account name"+ accountName);
		Assert.assertEquals(accountName,prop.getProperty("accountName"));
	}
	
	@DataProvider
	public Object[][] getLoginInvalidData(){
	//first[]=username   second []=password
		Object data[][]={{"bill@gmail.com","test1234"},
				{"tilly@gmail.com",""},
				{"","test6789"},
				{"yummy","yummy"},
				{"",""}};
		return data;
	}
	
	@Test(priority=4 ,dataProvider = "getLoginInvalidData",description="verify invalid credentials", enabled = false)
	public void loginInvalidTestcase(String username,String pswd){
		userCred.setAppUserName(username);
		userCred.setAppPassword(pswd);
		loginPage.doLogin(userCred);
		Assert.assertTrue(loginPage.checkLoginErrorMessage());
		
		
		
	}
	

	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.quit();
	}

}
