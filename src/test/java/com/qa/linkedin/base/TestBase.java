package com.qa.linkedin.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {
public static WebDriver driver;
public WebDriverWait wait;
private static final Logger log=LogManager.getLogger(TestBase.class);

/**
 * read  the properties file data
 * @throws IOException 
 */
public String readPropertyValue(String key) throws IOException {
	log.info("Create Object for Properties class");
	Properties prop = new Properties();
	log.info("Read the properties file");
	try {
		FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir")
				+ "\\src\\test\\java\\com\\qa\\linkedin\\config\\config.properties"));
		prop.load(fis);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return prop.getProperty(key);
}

	
@BeforeSuite
public void setup() throws IOException {
	String browserName=readPropertyValue("browser");
	if (browserName.equalsIgnoreCase("chrome")) {
		ChromeOptions opt=new ChromeOptions();
		opt.setAcceptInsecureCerts(true);
		driver = new ChromeDriver(opt);
		log.info("chromebrowser is launched");
	} else if (browserName.equalsIgnoreCase("firefox")) {
		FirefoxOptions opt=new FirefoxOptions();
		opt.setAcceptInsecureCerts(true);
		// interface refvar=new implementedclass();
		driver = new FirefoxDriver(opt);
		log.info("firefox browser is launched");
	} else if (browserName.equalsIgnoreCase("edge")) {
		EdgeOptions opt=new EdgeOptions();
		opt.setAcceptInsecureCerts(true);
		driver = new EdgeDriver(opt);
	}
	log.info("edge browser is launched");
	log.debug("maximize the window");
	driver.manage().window().maximize();
	log.info("add implicitwait");
	driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
	log.info("add explicitwait object", true);
	wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	log.info("launch the application url:"+readPropertyValue("appUrl"));
	driver.get(readPropertyValue("appUrl"));
	
}

@AfterSuite
public void tearDown() {
	log.info("close the browser");
	if (driver != null) {
		driver.quit();
	}	
}
	
}
