package page;

import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class QuickDraftPage extends BasePage {

	WebDriver driver; // Global

	// Every Page must have a constructor to invite the driver
	public QuickDraftPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	// Elements Library
	// Elements needed to login
	@FindBy(how = How.ID, using = "user_login")
	WebElement UserName;
	@FindBy(how = How.ID, using = "user_pass")
	WebElement Password;
	@FindBy(how = How.NAME, using = "wp-submit")
	WebElement LogInButton;

	// Elements needed to create a post/draft
	@FindBy(how = How.XPATH, using = "//h1[contains(text(),'Dashboard')]")
	WebElement PageTitle;
	@FindBy(how = How.ID, using = "title")
	WebElement DraftTitle;
	@FindBy(how = How.ID, using = "content")
	WebElement DraftContent;
	@FindBy(how = How.ID, using = "save-post")
	WebElement SaveDraft;

	// Elements needed to validate post has appeared under recent drafts
	@FindBy(how = How.XPATH, using = "//h2[contains(text(),'Your Recent Drafts')]/parent::div/descendant::a")
	WebElement PostedTitle;
	@FindBy(how = How.XPATH, using = "//h2[contains(text(),'Your Recent Drafts')]/parent::div/descendant::p")
	WebElement PostedContent;

	// Elements needed to validate post appeared in the table
	@FindBy(how = How.XPATH, using = "//div[@class='wp-menu-image dashicons-before dashicons-admin-post']")
	WebElement PostButton;
	@FindBy(how = How.LINK_TEXT, using = "All Posts")
	WebElement AllPostButton;
	@FindBy(how = How.XPATH, using = "//h1[contains(text(),'Posts')]")
	WebElement PostsPage;
	@FindBy(how = How.CLASS_NAME, using = "row-title")
	WebElement titleInTable;


	// Methods to interact with the elements
	public void waitForCreateDraftPage() {
		waitForElement(PageTitle, driver);
	}

	// Method used to login
	public void login(String userName, String password) {
		UserName.sendKeys(userName);
		Password.sendKeys(password);
		LogInButton.click();
	}

	// Method used to create a quick draft
	public void createQuickDraft() {
		Random rnd = new Random();
		int randomNumber = rnd.nextInt(9999);
		String title = "Dede'sTitle" + randomNumber;
		String content = "Dede'sContent" + randomNumber;
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", SaveDraft);
		DraftTitle.sendKeys(title);
		DraftContent.sendKeys(content);
		SaveDraft.click();

	}

	// Methods to validate draft was posted (under recent drafts)
	public boolean isPosted() {
		PostedTitle.isDisplayed();
		return PostedTitle.isDisplayed();

	}

	// Method used to access the table
	public void postButton() {
		PostButton.click();
		AllPostButton.click();
	}

	// Methods to validate table appeared;
	public void waitForDraftTablePage() {
		waitForElement(PostsPage, driver);
	}
	
	// Methods to validate draft was posted on the table;
	public boolean isPostedOnTheTable() {
		titleInTable.isDisplayed();
		return titleInTable.isDisplayed();

	}

}
