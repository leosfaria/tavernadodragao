package br.com.tavernadodragao.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class EditProfileTest extends AbstractSystemTests {
		
	@Test
	public void testChangeUsername() {
		loginTest();
		browser.click(browser.byId("editProfileButton"));
		browser.setValue(browser.byId("username"), "Teste Alterado");
		browser.click(browser.byId("buttonSave"));
		assertTrue(browser.heading3("Teste Alterado").exists());
	}
	
	@Test
	public void testChangeEmail() {
		loginTest();
		browser.click(browser.byId("editProfileButton"));
		browser.setValue(browser.byId("email"), "testeAlterado@test.com");
		browser.click(browser.byId("buttonSave"));
		browser.click(browser.byId("logout"));
		loginTest(false);
		assertFalse(browser.heading1("I used to be an adventurer like you, then I took an arrow to the knee...").exists());
		browser.setValue(browser.byId("emailLogin"), "testeAlterado@test.com");
		browser.setValue(browser.byId("passwordLogin"), "123");
		browser.click(browser.byId("buttonLogin"));
		assertTrue(browser.heading1("I used to be an adventurer like you, then I took an arrow to the knee...").exists());
	}
	
	@Test
	public void testChangePassword() {
		loginTest();
		browser.click(browser.byId("editProfileButton"));
		browser.setValue(browser.byId("oldPassword"), "123");
		browser.setValue(browser.byId("newPassword"), "456");
		browser.setValue(browser.byId("confirmPassword"), "456");
		browser.click(browser.byId("buttonSave"));
		browser.click(browser.byId("logout"));
		loginTest(false);
		assertFalse(browser.heading1("I used to be an adventurer like you, then I took an arrow to the knee...").exists());
		browser.setValue(browser.byId("emailLogin"), "teste@teste.com");
		browser.setValue(browser.byId("passwordLogin"), "456");
		browser.click(browser.byId("buttonLogin"));
		assertTrue(browser.heading1("I used to be an adventurer like you, then I took an arrow to the knee...").exists());
	}
	

	@Test
	public void testInvalidChangeUsername() {
		//Username vazio
		loginTest();
		browser.click(browser.byId("editProfileButton"));
		browser.setValue(browser.byId("username"), "");
		browser.click(browser.byId("buttonSave"));
		assertTrue(browser.containsText(browser.byId("usernameError"), "Username must have 3 to 30 characters"));
		//Username com mais de 30 caracteres
		browser.setValue(browser.byId("username"), "User com mais de trinta caracteres");
		browser.click(browser.byId("buttonSave"));
		assertTrue(browser.containsText(browser.byId("usernameError"), "Username must have 3 to 30 characters"));
	}
	
	@Test
	public void testInvalidChangeEmail() {
		executeSqlScript("sql/createUser2.sql", false);
		
		//Email vazio
		loginTest();
		browser.click(browser.byId("editProfileButton"));
		browser.setValue(browser.byId("email"), "");
		browser.click(browser.byId("buttonSave"));
		assertTrue(browser.containsText(browser.byId("emailError"), "Email must be a valid email account"));
		//Email já existente
		browser.setValue(browser.byId("email"), "teste2@teste.com");
		browser.click(browser.byId("buttonSave"));
		assertTrue(browser.containsText(browser.byId("emailError"), "Email already in use"));
	}
	
	@Test
	public void testInvalidChangePassword() {
		//Senha menor que 3 digitos
		loginTest();
		browser.click(browser.byId("editProfileButton"));
		browser.setValue(browser.byId("oldPassword"), "123");
		browser.setValue(browser.byId("newPassword"), "12");
		browser.setValue(browser.byId("confirmPassword"), "12");
		browser.click(browser.byId("buttonSave"));
		assertTrue(browser.containsText(browser.byId("passwordError"), "Password must have 3 to 15 characters"));
		//Senha maior que 15 digitos
		browser.setValue(browser.byId("newPassword"), "12345678910111213");
		browser.setValue(browser.byId("confirmPassword"), "12345678910111213");
		browser.click(browser.byId("buttonSave"));
		assertTrue(browser.containsText(browser.byId("passwordError"), "Password must have 3 to 15 characters"));
		//Senha antiga não confere
		browser.setValue(browser.byId("oldPassword"), "456");
		browser.setValue(browser.byId("newPassword"), "456");
		browser.setValue(browser.byId("confirmPassword"), "456");
		browser.click(browser.byId("buttonSave"));
		assertTrue(browser.containsText(browser.byId("currentPasswordError"), "Old Password doesn't match your current password"));
		//Senha e Confirmação não conferem
		browser.setValue(browser.byId("oldPassword"), "123");
		browser.setValue(browser.byId("newPassword"), "456");
		browser.setValue(browser.byId("confirmPassword"), "123");
		browser.click(browser.byId("buttonSave"));
		assertTrue(browser.containsText(browser.byId("passwordError"), "Password e Confirm Password doesn't match"));
	}

//	@Test
//	public void testChangeImage() {
//	
//	}
//	
}
