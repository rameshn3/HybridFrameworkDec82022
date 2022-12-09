package com.qa.linkedin.testcases;

import org.testng.annotations.Test;

import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.pages.LinkedinFeedPage;
import com.qa.linkedin.pages.LinkedinHomePage;
import com.qa.linkedin.pages.LinkedinLoginPage;

import org.testng.annotations.BeforeClass;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class LinkedinLoginPageTest extends TestBase{
 private Logger log=LogManager.getLogger(LinkedinLoginPageTest.class);
 LinkedinHomePage lhmPg;
 LinkedinLoginPage loginPg;
 LinkedinFeedPage lfeedPg;
 
  @BeforeClass
  public void beforeClass() throws InterruptedException {
	  lhmPg=new LinkedinHomePage();
	  loginPg=new LinkedinLoginPage();
	  lfeedPg=new LinkedinFeedPage();
	  log.debug("validating the sign in link");
	  loginPg= lhmPg.clickOnSignInLink();
  }

  @Test(priority=1)
  public void verifyLinkedinLoginPageTitleTest() {
	  log.debug("verifying the Linkedin Login page");
	  Assert.assertEquals(loginPg.getLinkedinLoginPageTitle(),"LinkedIn Login, Sign in | LinkedIn");
  }
   @Test(priority=2)
  public void verifyLinkedinSigninHeaderTest() {
	  log.debug("verify linkedin signin header text element presence in the page");
	  Assert.assertTrue(loginPg.isSigninHeaderTextPresent(), "Linkedin sign in header text element is not present in home page");
  }
  @Test(priority=3)
  public void doLoginTest() throws InterruptedException, IOException {
	log.debug("performing login action to the linkedin");
	lfeedPg=loginPg.doLogin(readPropertyValue("username"), readPropertyValue("pwd"));
	Thread.sleep(3000);
  }
  
  
  @Test(dependsOnMethods= {"doLoginTest"})
  public void doLogOutTest() throws InterruptedException {
	  log.debug("log out from linkedin page ");
	  lfeedPg.doLogout();
  }

}
