package br.com.tavernadodragao.test;

import java.util.concurrent.TimeUnit;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/main-servlet.xml" })
public abstract class SystemTest extends AbstractTransactionalJUnit4SpringContextTests{
	protected static final String MAIN_URL = "http://localhost:8081/";
	protected WebDriver webDriver;
	protected WebDriverWait wait;
	
	@Autowired
	private BasicDataSource dataSource;

	private static final String CREATE_DATABASE_SQL = "sql/createDatabase.sql";
	private static final String DROP_DATABASE_SQL = "sql/dropDatabase.sql";
	
	@Before
	public void setUp() {
		webDriver = new FirefoxDriver();
		wait = new WebDriverWait(webDriver, 30L);
		webDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
		
		dataSource = chooseDataSource();
		setDataSource(dataSource);
		executeSqlScript(CREATE_DATABASE_SQL, false);
	}

	@After
	public void tearDown() {
		webDriver.quit();
		executeSqlScript(DROP_DATABASE_SQL, false);
	}

	protected BasicDataSource chooseDataSource() {
		return dataSource;
	}

	protected Long getTimeToWaitForCommit() {
		return 0L;
	}
	
	protected void waitSeconds(Long time) {
		wait.withTimeout(time, TimeUnit.SECONDS);
	}

	protected void openURL(String url) {
		webDriver.get(url);
	}

	protected void findElementByName(String name) {
		webDriver.findElement(By.name(name));
	}

	protected void findElementById(String id) {
		webDriver.findElement(By.id(id));
	}

	protected void pageContains(String text) {
		webDriver.getPageSource().contains(text);
	}

	protected void fillElementByName(String name, String text) {
		webDriver.findElement(By.name(name)).sendKeys(text);
	}

	protected void fillElementById(String id, String text) {
		webDriver.findElement(By.id(id)).sendKeys(text);
	}

	protected void clickElementByName(String name) {
		webDriver.findElement(By.name(name)).click();
	}

	protected void clickElementById(String id) {
		webDriver.findElement(By.id(id)).click();
	}
}
