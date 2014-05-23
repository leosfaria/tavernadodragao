package br.com.tavernadodragao.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class MainTest extends AbstractSystemTests {
		
	@Test
	public void testAddFriend() {
		executeSqlScript("sql/createUser2.sql", false);
		
		//Teste da Busca por Amigo
		loginTest();
		browser.setValue(browser.byId("searchInput"), "teste");
		browser.click(browser.byId("searchLink"));
		assertTrue(browser.heading3("Teste 2").exists());
		//Fim: Teste da Busca por Amigo
		
		//Teste de Request Pending 
		browser.click(browser.byId("addFriendButton"));
		assertTrue(browser.paragraph("-   You add Teste 2 to your friend list. Request pending...").exists());
		
		browser.setValue(browser.byId("searchInput"), "teste");
		browser.click(browser.byId("searchLink"));
		assertTrue(browser.byId("friendRequestPending").exists());
		//FIm: Teste de Request Pending
		
		//Teste de aprovar Request
		browser.click(browser.byId("logout"));
		browser.setValue(browser.byId("emailLogin"), "teste2@teste.com");
		browser.setValue(browser.byId("passwordLogin"), "123");
		browser.click(browser.byId("buttonLogin"));
		assertTrue(browser.byId("acceptFriendRequest").exists());
		
		browser.click(browser.byId("acceptFriendRequest"));
		assertTrue(browser.paragraph("-   You and Teste are friends now!").exists());
		assertTrue(browser.heading3("Teste").exists());
		
		browser.click(browser.byId("logout"));
		loginTest(false);
		assertTrue(browser.paragraph("-   Teste 2 approved your friendship request!").exists());
		assertTrue(browser.heading3("Teste 2").exists());
		//Fim: Teste de aprovar Request
	}
	
	@Test
	public void testDeclineFriendRequest() {
		executeSqlScript("sql/createUser2WithFriendRequest.sql", false);
		
		loginTest();
		browser.click(browser.byId("declineFriendRequest"));
		assertFalse(browser.heading3("Teste 2").exists());
		
		browser.click(browser.byId("logout"));
		browser.setValue(browser.byId("emailLogin"), "teste2@teste.com");
		browser.setValue(browser.byId("passwordLogin"), "123");
		browser.click(browser.byId("buttonLogin"));
		browser.setValue(browser.byId("searchInput"), "teste");
		browser.click(browser.byId("searchLink"));
		assertTrue(browser.byId("addFriendButton").exists());
	}
	
	@Test
	public void testUnfriend() {
		executeSqlScript("sql/createUser2andFriendRelationship.sql", false);
		
		loginTest();
		assertTrue(browser.heading3("Teste 2").exists());
		browser.setValue(browser.byId("searchInput"), "teste");
		browser.click(browser.byId("searchLink"));
		browser.click(browser.byId("unFriendButton"));
		assertFalse(browser.heading3("Teste 2").exists());
	}
}
