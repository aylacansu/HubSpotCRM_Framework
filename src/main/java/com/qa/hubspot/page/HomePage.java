package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.ElementUtil;

public class HomePage extends BasePage {
WebDriver driver;
ElementUtil elementUtil;

By header=By.xpath("//i18n-string[contains(text(),'Set up your HubSpot account')]");
By accountName=By.cssSelector("div.user-info-name");

By mainContactsLink=By.id("nav-primary-contacts-branch");
By childContactsLink=By.id("nav-secondary-contacts");




public HomePage(WebDriver driver){
	this.driver=driver;
	elementUtil=new ElementUtil(driver);
}
	
public String getHomePageTitle(){
	elementUtil.waitForTitlePresent(AppConstants.HOME_PAGE_TITLE);
	return elementUtil.doGetPageTitle();
}
	
public String getHomePageHeader(){	
	return elementUtil.dogetText(header);
}
public String getLoginUserAccountName(){	
	return elementUtil.dogetText(accountName);
}
	
public void clickOnContacts(){
	elementUtil.waitForElementPresent(mainContactsLink);
	elementUtil.doClick(mainContactsLink);
	elementUtil.waitForElementPresent(childContactsLink);
	elementUtil.doClick(childContactsLink);
}
public ContactsPage goToContactsPage(){
	clickOnContacts();
	return new ContactsPage(driver);
}

}
