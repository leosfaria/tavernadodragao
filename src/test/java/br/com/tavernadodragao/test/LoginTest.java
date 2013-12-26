package br.com.tavernadodragao.test;

import org.junit.Test;

public class LoginTest extends SystemTest {
	
	@Test
	public void testLogin() {		
		openURL(MAIN_URL);
		fillElementById("emailLogin", "teste@teste.com");
		fillElementById("passwordLogin", "123");
		clickElementById("buttonLogin");
		waitSeconds(3L);
		pageContains("Eu era um aventureiro como você... Até levar uma flechada no joelho...");
	}
	
	@Test
	public void testLoginInvalid() {
		openURL(MAIN_URL);
		fillElementById("emailLogin", "testeInvalido@teste.com");
		fillElementById("passwordLogin", "123");
		clickElementById("buttonLogin");
		waitSeconds(3L);
		pageContains("Email ou senha inválido");
		
		fillElementById("emailLogin", "teste@teste.com");
		fillElementById("passwordLogin", "123456");
		clickElementById("buttonLogin");
		waitSeconds(3L);
		pageContains("Email ou senha inválido");
		
		fillElementById("emailLogin", "");
		fillElementById("passwordLogin", "123");
		clickElementById("buttonLogin");
		waitSeconds(3L);
		pageContains("Email ou senha inválido");
		
		fillElementById("emailLogin", "teste@teste.com");
		fillElementById("passwordLogin", "");
		clickElementById("buttonLogin");
		waitSeconds(3L);
		pageContains("Email ou senha inválido");
	}
}
