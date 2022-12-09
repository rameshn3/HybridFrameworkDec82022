package com.qa.linkedin.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.linkedin.utils.BasePageWebActions;

public class LinkedinLoginPage extends BasePageWebActions{

private Logger log=LogManager.getLogger(LinkedinLoginPage.class);
	//create a constructor
public LinkedinLoginPage() {
	PageFactory.initElements(driver, this);
}

@FindBy(xpath = "//h1[@class='header__content__heading '][contains(.,'Sign in')]")
private WebElement signinHeaderText;

@FindBy(id="username")
private WebElement emailEditbox;

@FindBy(name="session_password")
private WebElement passwordEditbox;

@FindBy(xpath = "//button[@type='submit']")
private WebElement signinBtn;

/**
 * fetch the current page title
 */
public String getLinkedinLoginPageTitle() {
	log.info("fetch the Linkedin login page title");
	return driver.getTitle();
}

/**
 * check whether SigninHeaderText element is present or not
 */
public boolean isSigninHeaderTextPresent() {
	log.info("check whether SigninHeaderText element is present or not");
	return signinHeaderText.isDisplayed();
}

/**
 * perform the click action on the sign in link
 * @throws InterruptedException 
 */
public void clickOnSigninBtn() throws InterruptedException {
		log.info("click on sign in button");
		click(signinBtn);	
}

/**
 * perform the login to the linkedin
 * @throws InterruptedException 
 */
public LinkedinFeedPage doLogin(String uname,String pwd) throws InterruptedException {
	log.info("type the username in email editbox");
	type(emailEditbox,uname);
	log.info("type the password in password editbox");
	type(passwordEditbox,pwd);
	log.info("click on signin button");
	clickOnSigninBtn();
	return new LinkedinFeedPage();
}


}
