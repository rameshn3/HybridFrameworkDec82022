package com.qa.linkedin.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.linkedin.utils.BasePageWebActions;

public class LinkedinHomePage extends BasePageWebActions{

private Logger log=LogManager.getLogger(LinkedinHomePage.class);
	//create a constructor
public LinkedinHomePage() {
	PageFactory.initElements(driver, this);
}

@FindBy(css="a[class*='nav__logo-link']")
private WebElement linkedinLogo;

@FindBy(css="a[class^='nav__button-secondary']")
private WebElement signinLink;

/**
 * fetch the current page title
 */
public String getLinkedinHomePageTitle() {
	log.info("fetch the Linkedin home page title");
	return driver.getTitle();
}

/**
 * check whether LinkedinLogo element is present or not
 */
public boolean isLinkedinLogoPresent() {
	log.info("check whether LinkedinLogo element is present or not");
	return linkedinLogo.isDisplayed();
}

/**
 * perform the click action on the sign in link
 * @throws InterruptedException 
 */
public LinkedinLoginPage clickOnSignInLink() throws InterruptedException {
		log.info("click on sign in link");
		click(signinLink);
		return new LinkedinLoginPage();
	}

}
