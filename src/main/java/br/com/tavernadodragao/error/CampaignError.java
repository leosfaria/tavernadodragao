package br.com.tavernadodragao.error;

public class CampaignError {

	private ErrorType errorType;
	private String message;
	
	public CampaignError(ErrorType errorType, String message) {
		this.errorType = errorType;
		this.message = message;
	}
	
	public ErrorType getErrorType() {
		return errorType;
	}
	
	public String getMessage() {
		return message;
	}
}
