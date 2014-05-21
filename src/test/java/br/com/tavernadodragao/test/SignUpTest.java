package br.com.tavernadodragao.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SignUpTest extends AbstractSystemTests {
	
	@Test
	public void testSignUp() {
		browser.navigateTo(url);
		browser.setValue(browser.byId("usernameSignup"), "Teste Signup");
		browser.setValue(browser.byId("emailSignup"), "testeSignup@teste.com");
		browser.setValue(browser.byId("passwordSignup"), "123");
		browser.setValue(browser.byId("confirmPasswordSignup"), "123");
		browser.click(browser.byId("buttonSignup"));
		assertTrue(browser.heading1("I used to be an adventurer like you, then I took an arrow to the knee...").exists());
		assertTrue(browser.heading3("Teste Signup").exists());
	}

	@Test
	public void testSignUpInvalid() {
		//Teste de Username vazio
		browser.navigateTo(url);
		browser.setValue(browser.byId("emailSignup"), "testeSignupInvalido@teste.com");
		browser.setValue(browser.byId("passwordSignup"), "123");
		browser.setValue(browser.byId("confirmPasswordSignup"), "123");
		browser.click(browser.byId("buttonSignup"));
		assertTrue(browser.containsText(browser.byId("usernameSignupError"), "Username deve ter de 3 caracteres a 30"));
		//Teste de Email vazio
		browser.navigateTo(url);
		browser.setValue(browser.byId("usernameSignup"), "Teste Signup Invalido");
		browser.setValue(browser.byId("passwordSignup"), "123");
		browser.setValue(browser.byId("confirmPasswordSignup"), "123");
		browser.click(browser.byId("buttonSignup"));
		assertTrue(browser.containsText(browser.byId("emailSignupError"), "Email deve ser um email válido"));
		//Teste de Password vazio
		browser.navigateTo(url);
		browser.setValue(browser.byId("usernameSignup"), "Teste Signup Invalido");
		browser.setValue(browser.byId("emailSignup"), "testeSignupInvalido@teste.com");
		browser.setValue(browser.byId("confirmPasswordSignup"), "123");
		browser.click(browser.byId("buttonSignup"));
		assertTrue(browser.containsText(browser.byId("passwordSignupError"), "Password deve ter de 3 a 15 caracteres"));
		//Teste de Confirm Password vazio
		browser.navigateTo(url);
		browser.setValue(browser.byId("usernameSignup"), "Teste Signup Invalido");
		browser.setValue(browser.byId("emailSignup"), "testeSignupInvalido@teste.com");
		browser.setValue(browser.byId("passwordSignup"), "123");
		browser.click(browser.byId("buttonSignup"));
		assertTrue(browser.containsText(browser.byId("confirmPasswordSignupError"), "Confirm Password deve ter de 3 a 15 caracteres"));
		//Teste de Email já cadastrado
		browser.navigateTo(url);
		browser.setValue(browser.byId("usernameSignup"), "Teste Signup Email Cadastrado");
		browser.setValue(browser.byId("emailSignup"), "teste@teste.com");
		browser.setValue(browser.byId("passwordSignup"), "123");
		browser.setValue(browser.byId("confirmPasswordSignup"), "123");
		browser.click(browser.byId("buttonSignup"));
		assertTrue(browser.containsText(browser.byId("emailSignupError"), "Email already in use"));
		//Teste de Senha e Confirmação não batem
		browser.navigateTo(url);
		browser.setValue(browser.byId("usernameSignup"), "Teste Signup Senha Confirm");
		browser.setValue(browser.byId("emailSignup"), "testeSignUpInvalido@teste.com");
		browser.setValue(browser.byId("passwordSignup"), "123");
		browser.setValue(browser.byId("confirmPasswordSignup"), "123456");
		browser.click(browser.byId("buttonSignup"));
		assertTrue(browser.containsText(browser.byId("passwordSignupError"), "Password e Confirm Password doesn't match"));
	}
}
