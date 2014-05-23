package br.com.tavernadodragao.test;

import net.sf.sahi.Proxy;
import net.sf.sahi.client.Browser;
import net.sf.sahi.config.Configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-servlet.xml" })
public abstract class AbstractSystemTests extends AbstractTransactionalJUnit4SpringContextTests{
	@Autowired
	private BasicDataSource dataSource;

	private static final String CREATE_DATABASE_SQL = "sql/createDatabase.sql";
	private static final String DROP_DATABASE_SQL = "sql/dropDatabase.sql";
	
	protected Browser browser;
	protected static boolean isProxyInSameProcess = false;
	protected String url = "http://localhost:8080/";
	private static Proxy proxy;
	private String browserName = "chrome";
	
	@BeforeClass
	public static void startSahi() throws Exception {
		String sahiBase = "C:\\Leo\\Sahi";
		String userDataDirectory = "C:\\Leo\\Sahi\\userdata";
		Configuration.initJava(sahiBase, userDataDirectory);
		proxy = new Proxy();
		proxy.start(true);
	}
	
	@Before
	public void setUp() throws Exception {	
		dataSource = chooseDataSource();
		setDataSource(dataSource);
		executeSqlScript(CREATE_DATABASE_SQL, false);
		
		Thread.sleep(5000L);
		browser = new Browser(browserName);
		browser.open();
	}
	
	public void loginTest(boolean navigateTo) {
		if(navigateTo)
			browser.navigateTo(url);
		
		browser.setValue(browser.byId("emailLogin"), "teste@teste.com");
		browser.setValue(browser.byId("passwordLogin"), "123");
		browser.click(browser.byId("buttonLogin"));
	}
	
	public void loginTest() {
		loginTest(true);
	}

	@After
	public void tearDown() {
		executeSqlScript(DROP_DATABASE_SQL, false);
		
		browser.close();
		if (isProxyInSameProcess) {
			proxy.stop();
		}
	}

	protected BasicDataSource chooseDataSource() {
		return dataSource;
	}

	protected Long getTimeToWaitForCommit() {
		return 0L;
	}
}
