package com.qa.linkedin.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.linkedin.utils.BasePageWebActions;

public class LinkedinFeedPage extends BasePageWebActions{

private Logger log=LogManager.getLogger(LinkedinFeedPage.class);
	//create a constructor
public LinkedinFeedPage() {
	PageFactory.initElements(driver, this);
}

@FindBy(xpath = "//*[contains(@class,'feed-identity-module')]")
private WebElement profileRailCard;

@FindBy(css="img[class='global-nav__me-photo ember-view']")
private WebElement profileMeIcon;

@FindBy(xpath="//input[contains(@class,'search-global-typeahead__input')]")
private WebElement searchEditbox;

@FindBy(xpath = "//a[@class='global-nav__secondary-link mv1'][contains(.,'Sign Out')]")
private WebElement signOutLink;

/**
 * fetch the current page title
 */
public String getLinkedinFeedPageTitle() {
	log.info("fetch the Linkedin feed page title");
	return driver.getTitle();
}

/**
 * check whether SigninHeaderText element is present or not
 */
public boolean isprofileRailCardPresent() {
	log.info("check whether profileRailCard element is present or not");
	return profileRailCard.isDisplayed();
}

/**
 * perform the logout from the Linkedin
 * @throws InterruptedException 
 */
public void doLogout() throws InterruptedException {
		log.info("click on profileMeIcon in linkedin feedpage");
		click(profileMeIcon);
		log.info("click on Sign out link under profileme menu in linkedin feedpage");
		click(signOutLink);
}

/**
 * perform the people search to the linkedin
 * @throws InterruptedException 
 */
public LinkedinSearchResultsPage doPeopleSearch(String keyword) throws InterruptedException {
	log.info("type the people name in search editbox");
	type(searchEditbox,keyword);
	log.info("Press the ENTER key on search editbox");
	searchEditbox.sendKeys(Keys.ENTER);
	Thread.sleep(1000);
	return new LinkedinSearchResultsPage();
}


}
