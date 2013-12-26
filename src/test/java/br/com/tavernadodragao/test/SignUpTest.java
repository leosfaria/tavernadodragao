package br.com.tavernadodragao.test;

import org.junit.Test;

public class SignUpTest extends SystemTest {
	
	@Test
	public void testSignUp() {		
		openURL(MAIN_URL);
		fillElementById("usernameSignup", "Teste");
		fillElementById("emailSignup", "teste2@teste.com");
		fillElementById("passwordSignup", "123");
		fillElementById("confirmPasswordSignup", "123");
		clickElementById("buttonSignup");
		waitSeconds(3L);
		pageContains("Eu era um aventureiro como você... Até levar uma flechada no joelho...");
	}
	
	@Test
	public void testSignUpInvalid() {
		openURL(MAIN_URL);
		clickElementById("buttonSignup");
		waitSeconds(3L);
		pageContains("Username deve ter de 3 caracteres a 30");
		pageContains("Email deve ser um email válido");
		pageContains("Password deve ter de 3 a 15 caracteres");
		pageContains("Confirm Password deve ter de 3 a 15 caracteres");
		
		fillElementById("usernameSignup", "Teste");
		fillElementById("emailSignup", "teste@teste.com");
		fillElementById("passwordSignup", "123");
		fillElementById("confirmPasswordSignup", "123");
		clickElementById("buttonSignup");
		waitSeconds(3L);
		pageContains("Email já cadastrado");
		
		fillElementById("usernameSignup", "Teste");
		fillElementById("emailSignup", "teste3@teste.com");
		fillElementById("passwordSignup", "123");
		fillElementById("confirmPasswordSignup", "321");
		clickElementById("buttonSignup");
		waitSeconds(3L);
		pageContains("Password e Confirmação não conferem");
	}
}
