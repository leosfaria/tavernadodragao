package br.com.tavernadodragao.error;

public enum ErrorType {

	USER_OR_PASS_INVALID("USER_OR_PASS_INVALID"),
	PASS_AND_CONFIRM_DOESNT_MATCH("PASS_AND_CONFIRM_DOESNT_MATCH"),
	EMAIL_ALREADY_IN_USE("EMAIL_ALREADY_IN_USE");
	
	private final String val;
	
	ErrorType(String val) {
		this.val = val;
	}
	
	public String getErrorType() {
		return val;
	}
}
