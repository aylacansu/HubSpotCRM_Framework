package com.qa.hubspot.util;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.qa.hubspot.base.BasePage;


public class ElementUtil  extends BasePage{

WebDriver driver;
WebDriverWait wait;
JavaScriptUtil jsUtil;
Properties properties;

public ElementUtil(WebDriver driver){
	this.driver=driver;
	wait=new WebDriverWait(driver,AppConstants.DEFAULT_TIMEOUT );
        jsUtil=new JavaScriptUtil(driver);
}

/**
 * Wait For Title
 * @param title
 * @return
 */
public boolean waitForTitlePresent(String title){
wait.until(ExpectedConditions.titleIs(title));
return true;
	
}
/**
 * Wait For Element
 * @param locator
 * @return
 */
public boolean  waitForElementPresent(By locator){
wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
return true;
}

/**
 * wait for element visible
 * @param locator
 * @return
 */
public boolean waitForElementVisible(By locator){
	wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	return true;
}
/**
 * @author ic
 * GetTitle Method
 * @return
 */

public String doGetPageTitle(){
try {
	return driver.getTitle();
} catch (Exception e) {
	System.out.println("some exception got occured while getting the title");
}
return null;
}


/**
 *@author ic
 *GetElement Method 
 * @param locator
 */
public WebElement getElement(By locator){
	WebElement element=null;
	try {
		//if(waitForElementPresent(locator));
		element=driver.findElement(locator);
		if(highlightElement){
			jsUtil.flash(element);
		}
	} catch (Exception e) {
		System.out.println("Some exception occured while creating the Web Element");
	}
	
	return element;
	}

/**
 * ClickOn Method
 * @param locator
 */
public void doClick(By locator){
	try {
		getElement(locator).click();
	} catch (Exception e) {
		System.out.println("Some exception occured while clicking the Web Element");
	}

	
	
}

/**
 * DoSendKeys Method
 * @param locator
 * @param value
 */
public void doSendKeys(By locator,String value){
	
try {
	WebElement element=getElement(locator);
	element.clear();
	element.sendKeys(value);
} catch (Exception e) {
	System.out.println("Some exception occured while sending  keys to the field");
}	
	
	
}
/**
 * IsDisplayed
 * @param locator
 * @return
 */

public boolean  doIsDisplayed(By locator){
try {
	return getElement(locator).isDisplayed();
} catch (Exception e) {
	System.out.println("Some exception occured while isDisplayed");	
}	
	
return false;	
}

/**
 * IsEnabled
 * @param locator
 * @return
 */
public boolean  doIsEnabled(By locator){
try {
	return getElement(locator).isEnabled();
} catch (Exception e) {
	System.out.println("Some exception occured while isEnabled");	
}	
	
return false;	
}


/**
 * IsSelected
 * @param locator
 * @return
 */
public boolean  doIsSelected(By locator){
try {
	return getElement(locator).isSelected();
} catch (Exception e) {
	System.out.println("Some exception occured while isSelected");	
}	
	
return false;	
}	
/**
 * getText	
 * @param locator
 * @return
 */
public String dogetText(By locator){	
	try {
		return getElement(locator).getText();
		
	} catch (Exception e) {
		System.out.println("Some exception occured while getting Text");		
	}
	
	return null;
	
	
	
}
	
	
	
}
