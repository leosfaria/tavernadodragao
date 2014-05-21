package br.com.tavernadodragao.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class EditProfileTest extends AbstractSystemTests {
		
	@Test
	public void testChangeUsername() {
		browser.navigateTo(url);
		browser.setValue(browser.byId("emailLogin"), "teste@teste.com");
		browser.setValue(browser.byId("passwordLogin"), "123");
		browser.click(browser.byId("buttonLogin"));
		browser.click(browser.byId("editProfileButton"));
		browser.setValue(browser.byId("username"), "Teste Alterado");
		browser.click(browser.byId("buttonSave"));
		assertTrue(browser.heading3("Teste Alterado").exists());
	}
	
//	@Test
//	public void testChangeEmail() {
//	
//	}
//	
//	@Test
//	public void testChangePassword() {
//	
//	}
//	
//	@Test
//	public void testChangeImage() {
//	
//	}
//	
//	@Test
//	public void testInvalidChangeUsername() {
//		
//	}
//	
//	@Test
//	public void testInvalidChangeEmail() {
//	
//	}
//	
//	@Test
//	public void testInvalidChangePassword() {
//	
//	}
}
