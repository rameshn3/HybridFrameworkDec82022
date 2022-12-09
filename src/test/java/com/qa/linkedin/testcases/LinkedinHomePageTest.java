package com.qa.linkedin.testcases;

import org.testng.annotations.Test;

import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.pages.LinkedinHomePage;
import com.qa.linkedin.pages.LinkedinLoginPage;

import org.testng.annotations.BeforeClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class LinkedinHomePageTest extends TestBase{
 private Logger log=LogManager.getLogger(LinkedinHomePageTest.class);
 LinkedinHomePage lhmPg;
 LinkedinLoginPage loginPg;
  @BeforeClass
  public void beforeClass() {
	  lhmPg=new LinkedinHomePage();
	  loginPg=new LinkedinLoginPage();
  }

  @Test(priority=1)
  public void verifyLinkedinHomePageTitleTest() {
	  log.debug("verifying the Linkedin home page");
	  Assert.assertEquals(lhmPg.getLinkedinHomePageTitle(),"LinkedIn: Log In or Sign Up");
  }
   @Test(priority=2)
  public void verifyLinkedinLogoTest() {
	  log.debug("verify linkedin logo element presence in the page");
	  Assert.assertTrue(lhmPg.isLinkedinLogoPresent(), "Linkedin logo element is not present in home page");
  }
  @Test(priority=3)
  public void validateSigninLinkTest() throws InterruptedException {
	  log.debug("validating the sign in link");
	  loginPg= lhmPg.clickOnSignInLink();
  }
  @AfterClass
  public void afterClass() {
	  log.debug("come back to home page");
	  driver.navigate().back();
  }

}
