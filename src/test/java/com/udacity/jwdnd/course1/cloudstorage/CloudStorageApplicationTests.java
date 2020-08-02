package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private Integer port;
	private static WebDriver driver;
	private HomePage homePage;
	private LoginPage loginPage;
	private SignupPage signupPage;
	private WebDriverWait wait;

	@BeforeEach
	public void beforeEach() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		loginPage = new LoginPage(driver);
		signupPage = new SignupPage(driver);
		homePage = new HomePage(driver);
		wait = new WebDriverWait(driver, 10000);
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void shouldNotAccessHomePageIfNotLoggedIn() {
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void shouldAllowAccessHomePageAfterLoginNoAccessAfterLogout() throws InterruptedException{
		String firstName = "David";
		String lastName = "Ames";
		String username = "dames";
		String password = "vanilla";

	    driver.get("http://localhost:" + this.port + "/signup");
		signupPage.signUpNewUser(firstName, lastName, username, password);
		Thread.sleep(1000);
		Assertions.assertEquals("Login", driver.getTitle());

		loginPage.loginUser(username, password);
		Thread.sleep(1000);
		Assertions.assertEquals("Home", driver.getTitle());

		homePage.logoutUser();
		Thread.sleep(1000);
		Assertions.assertEquals("Login", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/home");
		Thread.sleep(1000);
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void shouldCreateNewNote() throws InterruptedException {
		String durdenTitle = "On civilization";
		String durdenDescription = "Reject the basic assumptions of civilization, especially the importance of material possessions.";
        String firstName = "Tyler";
        String lastName = "Durden";
        String username = "tdurden";
        String password = "fight";

		driver.get("http://localhost:" + this.port + "/signup");
		signupPage.signUpNewUser(firstName, lastName, username, password);
		Thread.sleep(1000);

		loginPage.loginUser(username, password);
		Thread.sleep(1000);

		homePage.selectNotesTab();
		Thread.sleep(1000);

		homePage.selectAddNote();
		Thread.sleep(1000);

		homePage.submitNewNote(durdenTitle, durdenDescription);
		Thread.sleep(1000);

		homePage.selectNotesTab();
		Thread.sleep(1000);

		Assertions.assertEquals(durdenTitle, homePage.getNotesTitleDisplay());
		Assertions.assertEquals(durdenDescription, homePage.getNotesDescriptionDisplay());
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}
}
