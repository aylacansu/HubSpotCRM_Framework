package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;
import com.qa.hubspot.util.ElementUtil;

public class LoginPage extends BasePage {

	WebDriver driver;
	ElementUtil elementUtil;
	
	//Locators
	
	By emailId=By.id("username");
	By password=By.id("password");
	By loginButton=By.id("loginBtn");
	By signUpLink=By.linkText("Sign up");
	By loginErrorText=By.xpath("//div[@class='private-alert__inner']");
	
	
	//Constructor
	
	public LoginPage(WebDriver driver){
		this.driver=driver;
	elementUtil=new ElementUtil(driver);
	}
	
	
	//Page Actions
	
	public String  getPageTitle(){
		elementUtil.waitForTitlePresent(AppConstants.LOGIN_PAGE_TITLE);
	return elementUtil.doGetPageTitle();	
	}
	

	public boolean checkSignUpLink(){
	//elementUtil.waitForElementVisible(signUpLink);
	return elementUtil.doIsDisplayed(signUpLink);	
	}
	
	public HomePage doLogin(Credentials userCred){
		elementUtil.waitForElementPresent(emailId);
		elementUtil.doSendKeys(emailId,userCred.getAppUserName());
		elementUtil.doSendKeys(password,userCred.getAppPassword());
		elementUtil.doClick(loginButton);
	
//	driver.findElement(emailId).sendKeys(username);	
//	driver.findElement(password1).sendKeys(password);
//	driver.findElement(login).click();
		
	    return new HomePage(driver);
		
	}
	
	public boolean checkLoginErrorMessage(){
		return elementUtil.doIsDisplayed(loginErrorText);
	}
	
	
	
	
	
	
	
	
	
}
