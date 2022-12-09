package com.qa.linkedin.testcases;

import org.testng.annotations.Test;
import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.pages.LinkedinFeedPage;
import com.qa.linkedin.pages.LinkedinHomePage;
import com.qa.linkedin.pages.LinkedinLoginPage;
import com.qa.linkedin.pages.LinkedinSearchResultsPage;
import com.qa.linkedin.utils.ExcelUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;


public class LinkedinSearchResultsPageTest extends TestBase{
 private Logger log=LogManager.getLogger(LinkedinSearchResultsPageTest.class);
 LinkedinHomePage lhmPg;
 LinkedinLoginPage loginPg;
 LinkedinFeedPage lfeedPg;
 LinkedinSearchResultsPage lSrPg;
 private static String fPath=System.getProperty("user.dir")+"\\src\\test\\java\\com\\qa\\linkedin\\data\\searchTestData.xlsx";
  @BeforeClass
  public void beforeClass() throws InterruptedException, IOException {
	  lhmPg=new LinkedinHomePage();
	  loginPg=new LinkedinLoginPage();
	  lfeedPg=new LinkedinFeedPage();
	  lSrPg=new LinkedinSearchResultsPage();
	  log.info("clear the cache");
	  driver.manage().deleteAllCookies();
	  log.info("validating the sign in link");
	  loginPg= lhmPg.clickOnSignInLink();
	  log.info("performing login action to the linkedin");
	  lfeedPg=loginPg.doLogin(readPropertyValue("username"), readPropertyValue("pwd"));
	 Thread.sleep(3000);
  }

  @Test(priority=1)
  public void verifyLinkedinFeedPageTitleTest() {
	  log.info("verifying the Linkedin Feed page title");
	  Assert.assertTrue(lfeedPg.getLinkedinFeedPageTitle().contains("Feed | LinkedIn"),"LinkedIn feed page title is not present");
  }
   @Test(priority=2)
  public void verifyProfileRailCardTest() {
	  log.info("verify linkedin Feed page Profile Rail card element presence in the page");
	  Assert.assertTrue(lfeedPg.isprofileRailCardPresent(), "Linkedin Feed page Profile Rail card is not present");
  }
  @Test(priority=3,dataProvider="searchData")
  public void doPeopleSearchTest(String pplname) throws InterruptedException, IOException {
	log.info("perform the people search");
	lSrPg=lfeedPg.doPeopleSearch(pplname);
	log.info("click on see all people results link");
	lSrPg.clickOnSeeAllPeopleResultsLink();
	log.info("verify the search reslts pagetitle");
	Assert.assertTrue(lSrPg.getLinkedinSearchResultsPageTitle().contains("Search | LinkedIn"), "search results page title is not correct");
	log.info("search results count for:"+pplname);
	long count=lSrPg.getPeopleSearchResultsCount();
	log.info(pplname+" has search results count is:"+count);
	log.info("click on home tab");
	lSrPg.clickOnHomeTab();
	Thread.sleep(1000);
  }
  
  @DataProvider
  public Object[][] searchData() throws InvalidFormatException, IOException{
	  Object[][] data=new ExcelUtils().getTestData(fPath, "Sheet1");
	  return data;
  }
  
  @Test(dependsOnMethods= {"doPeopleSearchTest"})
  public void doLogOutTest() throws InterruptedException {
	  log.info("log out from linkedin page ");
	  lfeedPg.doLogout();
  }

}
