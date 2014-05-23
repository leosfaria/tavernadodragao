package br.com.tavernadodragao.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class LoginTest extends AbstractSystemTests {
		
	@Test
	public void testLogin() {
		loginTest();
		assertTrue(browser.heading1("I used to be an adventurer like you, then I took an arrow to the knee...").exists());
	}
	
	@Test
	public void testInvalidLogin() {
		//Teste de Login inválido
		browser.navigateTo(url);
		browser.setValue(browser.byId("emailLogin"), "testeInvalido@teste.com");
		browser.setValue(browser.byId("passwordLogin"), "123");
		browser.click(browser.byId("buttonLogin"));
		assertTrue(browser.containsText(browser.byId("loginError"), "Email or password invalid"));
		//Teste de Senha inválido
		browser.navigateTo(url);
		browser.setValue(browser.byId("emailLogin"), "teste@teste.com");
		browser.setValue(browser.byId("passwordLogin"), "123456");
		browser.click(browser.byId("buttonLogin"));
		assertTrue(browser.containsText(browser.byId("loginError"), "Email or password invalid"));
		//Teste de Login em branco
		browser.navigateTo(url);
		browser.setValue(browser.byId("emailLogin"), "");
		browser.setValue(browser.byId("passwordLogin"), "123");
		browser.click(browser.byId("buttonLogin"));
		assertTrue(browser.containsText(browser.byId("loginError"), "Email or password invalid"));
		//Teste de Senha em branco
		browser.navigateTo(url);
		browser.setValue(browser.byId("emailLogin"), "teste@teste.com");
		browser.setValue(browser.byId("passwordLogin"), "");
		browser.click(browser.byId("buttonLogin"));
		assertTrue(browser.containsText(browser.byId("loginError"), "Email or password invalid"));
	}
}
