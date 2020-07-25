package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.ContactsPage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.Credentials;
import com.qa.hubspot.util.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
@Epic("Epic - 103 : create  Contacts page home features")
@Feature("US - 503 : create test for Contacts on HubSpot")
public class ContactsPageTest {
	
	BasePage basePage;
	Properties prop;
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	Credentials userCred;
	

	@BeforeTest
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.init_proporties();
		String browserName = prop.getProperty("browser");
		driver = basePage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		homePage = loginPage.doLogin(userCred);
		contactsPage = homePage.goToContactsPage();
	}

	@Test(priority = 1,description="verify contacts page title")
	@Description("verify Contacts page title ")
	@Severity(SeverityLevel.NORMAL)
	public void verifyContactsPageTitle() {
		String title = contactsPage.getContactsPageTitle();
		System.out.println(title);
		Assert.assertEquals(title, "Contacts");
	}

	@DataProvider
	public Object[][] getContactsTestData() {
		Object[][] data = ExcelUtil.getTestData("constants");
		return data;
	}

	@Test(priority = 2, dataProvider = "getContactsTestData",description="create new contact")
	@Description("Create new Contact ")
	@Severity(SeverityLevel.BLOCKER)
	public void createContactsTest(String email, String firstname, String lastname, String jobtitle) {
		
		contactsPage.createNewContact(email, firstname, lastname, jobtitle);

	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
