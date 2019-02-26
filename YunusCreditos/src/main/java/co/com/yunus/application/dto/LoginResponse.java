package co.com.yunus.application.dto;

public class LoginResponse extends LoginRequest{
	private boolean valid;

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
