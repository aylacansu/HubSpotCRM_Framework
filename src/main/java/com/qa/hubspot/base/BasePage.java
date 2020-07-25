package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xwpf.usermodel.TOC;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.itextpdf.text.log.SysoCounter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
//WebDriver driver;        // for screen shot concept we use ThreadLocal driver..
Properties prop;   //getProperty();
public static boolean highlightElement; 
public OptionsManager optionsManager;

//for screen shot concept we use ThreadLocal driver..
public static ThreadLocal<WebDriver> tldriver=new ThreadLocal<WebDriver>();

//for screen shot concept we use 
public static synchronized WebDriver getDriver() {
	return tldriver.get();
}
public  WebDriver init_driver(String browserName){
	
	highlightElement=prop.get("highlight").equals("yes") ? true : false;
	System.out.println("Browser name:: "+browserName);
    optionsManager=new OptionsManager(prop);
    
if (browserName.equalsIgnoreCase("chrome")) {
	WebDriverManager.chromedriver().setup();
	//driver=new ChromeDriver(optionsManager.getChromeOptions());
	
	//for screen shot concept we use 
	tldriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
	
	
}else if (browserName.equalsIgnoreCase("firefox")) {
	WebDriverManager.firefoxdriver().setup();
	//driver=new FirefoxDriver(optionsManager.getFirefoxOptions());
	
	//for screen shot concept we use 
	tldriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
	

} else if (browserName.equalsIgnoreCase("opera")) {
	WebDriverManager.operadriver().setup();
	//driver = new OperaDriver();
	
	//for screen shot concept we use
	tldriver.set(new OperaDriver());

} else if (browserName.equalsIgnoreCase("IE")) {
	WebDriverManager.iedriver().setup();
	//driver = new InternetExplorerDriver();
	
	//for screen shot concept we use
	tldriver.set(new InternetExplorerDriver());
	
}else if(browserName.equalsIgnoreCase("safari")){
	WebDriverManager.getInstance(SafariDriver.class).setup();
	//driver = new SafariDriver();
	
	//for screen shot concept we use
	tldriver.set(new SafariDriver());
	
}else {
	System.out.println("Browser name " +browserName + "is not found,pls pass the valid browser.");

}


//for screen shot concept we use

getDriver().manage().deleteAllCookies();	
getDriver().manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS );
getDriver().manage().window().maximize();
return getDriver();
}	
	

public Properties init_proporties(){	
	
	prop=new Properties();
	
	String path=null;
	String env= null;
	
	try {
		env=System.getProperty("env");
		if(env.equals("qa")){
		path="./src/main/java/com/qa/hubspot/config/config.qa.properties";	
		}else if(env.equals("stg")){
		path="./src/main/java/com/qa/hubspot/config/config.stg.properties";	
		}
	} catch (Exception e) {
	path="./src/main/java/com/qa/hubspot/config/config.properties";	
	}
	
    try {
		FileInputStream ip=new FileInputStream(path);
		prop.load(ip);
	} catch (FileNotFoundException e) {
		System.out.println("some issue with config proporties..pls correct your config..");
	}catch (IOException e) {
		e.printStackTrace();
	}

	return prop;
	
	
}

/*To  Take  ScreenShot From The System If Test Cases Failed
 * @return
 */


public String getScreenshot() {
	File src=  ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
String path=System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
File destination= new File(path);
try {
	FileUtils.copyFile(src, destination);
} catch (IOException e) {
	
System.err.println(" screenshot captured failed....");

}
return path;

}




	
}
