package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import page.QuickDraftPage;
import util.BrowserFactory;

public class QuickDraftTest {

	WebDriver driver;

	@BeforeMethod
	public void launchBrowser() {
		driver = BrowserFactory.startBrowser();

	}

	@Test
	public void userShouldBeAbleToPostDraft() throws InterruptedException {
		// Go to site
		driver.get("https://s1.demo.opensourcecms.com/wordpress/wp-login.php");

		// Calling the page Class
		QuickDraftPage quickDraftPage = PageFactory.initElements(driver, QuickDraftPage.class); // Object Reference

		// Validate you went to the right site
		String expectedTitle = "Log In ‹ opensourcecms — WordPress";
		String actualTitle = quickDraftPage.getPageTitle();
		Assert.assertEquals(actualTitle, expectedTitle, "Wrong Page!!!");

		// Call the login method from the LoginPage Class
		quickDraftPage.login("opensourcecms", "opensourcecms");

		// Validate page show up using the Explicit Wait
		quickDraftPage.waitForCreateDraftPage();

		// Create a quick draft
		quickDraftPage.createQuickDraft();

		// Validate the post appeared below under "Your recent Drafts"
		quickDraftPage.isPosted();

		// Click on the Posts Menu, click on All Posts to access the table
		quickDraftPage.postButton();

		// Validate the post appeared in the table
		quickDraftPage.waitForDraftTablePage();
		quickDraftPage.isPostedOnTheTable();
	}

	@AfterMethod
	public void close() {
		driver.close();
		driver.quit();
	}

}
