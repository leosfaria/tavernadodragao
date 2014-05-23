package br.com.tavernadodragao.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class CampaignTest extends AbstractSystemTests {
		
	@Test
	public void testCreateCampaign() {
		executeSqlScript("sql/createUser2andFriendRelationship.sql", false);
		
		loginTest();
		browser.click(browser.byId("createCampaignButton"));
		browser.setValue(browser.byId("name"), "Campanha Teste");
		browser.click(browser.byId("Teste 2"));
		browser.click(browser.byId("buttonCreate"));
		assertTrue(browser.paragraph("-   You create a new campaign: Campanha Teste!").exists());
		assertTrue(browser.heading3("Campanha Teste").exists());
		
		browser.click(browser.byId("Campanha Teste"));
		assertTrue(browser.heading3("Campaign: Campanha Teste").exists());
		assertTrue(browser.heading2("Notes").exists());
		
		browser.click(browser.byId("logout"));
		browser.setValue(browser.byId("emailLogin"), "teste2@teste.com");
		browser.setValue(browser.byId("passwordLogin"), "123");
		browser.click(browser.byId("buttonLogin"));
		assertTrue(browser.heading3("Campanha Teste, Teste").exists());
		browser.click(browser.byId("Campanha Teste"));
		assertTrue(browser.heading2("Master").exists());
		assertTrue(browser.heading3("Teste").exists());
	}
		
//	@Test
//	public void testCreateCampaignInvalid() {
//		//Nome da campanha com menos de 3 caracteres
//		
//		//Nome da campanha com mais de 30 caracteres
//		
//		//Nome da campanha já existente
//	}
}
