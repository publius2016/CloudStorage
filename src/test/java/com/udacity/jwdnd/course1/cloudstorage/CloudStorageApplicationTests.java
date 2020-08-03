package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.ArrayList;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CloudStorageApplicationTests {

	@LocalServerPort
	private Integer port;
	private WebDriver driver;
	private HomePage homePage;
	private LoginPage loginPage;
	private SignupPage signupPage;
	private WebDriverWait wait;
	private String davidUserFirstName = "David";
	private String davidUserLastName = "Ames";
	private String davidUserUsername = "dames";
	private String davidUserPassword = "vanilla";
	private String tylerUserFirstName = "Tyler";
	private String tylerUserLastName = "Durden";
	private String tylerUserUsername = "tdurden";
	private String tylerUserPassword = "fight";
	private String durdenTitle = "On civilization";
	private String durdenDescription = "Reject the basic assumptions of civilization, especially the importance of material possessions.";
	private String durdenEditNoteTitle = "On Losing";
	private String durdenEditNoteDescription = "It’s only after we’ve lost everything that we’re free to do anything.";

	@BeforeAll
	public void beforeAll() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		loginPage = new LoginPage(driver);
		signupPage = new SignupPage(driver);
		homePage = new HomePage(driver);

		driver.get("http://localhost:" + this.port + "/signup");
		signupPage.signUpNewUser(
				davidUserFirstName,
				davidUserLastName,
				davidUserUsername,
				davidUserPassword);
		Thread.sleep(1000);

		driver.get("http://localhost:" + this.port + "/signup");
		signupPage.signUpNewUser(
				tylerUserFirstName,
				tylerUserLastName,
				tylerUserUsername,
				tylerUserPassword);
	}

	@AfterAll
	public void afterAll() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@BeforeEach
	public void beforeEach() throws  InterruptedException{
		driver.get("http://localhost:" + this.port + "/login");
		Thread.sleep(1000);

		loginPage.loginUser(tylerUserUsername, tylerUserPassword);
		Thread.sleep(1000);
	}

	@AfterEach
	public void afterEach() {
		if(driver.getTitle().equals("Home")) {
			homePage.logoutUser();
		}
	}

	@Test
	public void shouldNotAccessHomePageIfNotLoggedIn() {
		if(driver.getTitle().equals("Home")) {
			homePage.logoutUser();
		}
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void shouldAllowAccessHomePageAfterLoginNoAccessAfterLogout() throws InterruptedException{
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
	public void shouldEditExistingNote() throws InterruptedException {
		homePage.selectNotesTab();
		Thread.sleep(1000);

		homePage.selectAddNote();
		Thread.sleep(1000);

		homePage.submitNewNote(durdenTitle, durdenDescription);
		Thread.sleep(1000);

		homePage.selectNotesTab();
		Thread.sleep(1000);

		homePage.selectEditNote();
		Thread.sleep(1000);

		homePage.submitNewNote(durdenEditNoteTitle, durdenEditNoteDescription);
		Thread.sleep(1000);

		homePage.selectNotesTab();
		Thread.sleep(1000);

		Assertions.assertEquals(durdenEditNoteTitle, homePage.getNotesTitleDisplay());
		Assertions.assertEquals(durdenEditNoteDescription, homePage.getNotesDescriptionDisplay());
	}

	@Test
	public void shouldDeleteExistingNote() throws InterruptedException {
		homePage.logoutUser();
		Thread.sleep(1000);

		loginPage.loginUser(davidUserUsername, davidUserPassword);
		Thread.sleep(1000);

		homePage.selectNotesTab();
		Thread.sleep(1000);

		homePage.selectAddNote();
		Thread.sleep(1000);

		homePage.submitNewNote(durdenTitle, durdenDescription);
		Thread.sleep(1000);

		homePage.selectNotesTab();
		Thread.sleep(1000);

		homePage.selectDeleteNote();
		Thread.sleep(1000);

		homePage.selectNotesTab();
		Thread.sleep(1000);

		Assertions.assertEquals(new ArrayList<>(), homePage.getNoteEditButton());
		Assertions.assertEquals(new ArrayList<>(), homePage.getNoteDeleteButton());
	}
}
