package nehe.chatappapi.Modals;

public class LoginRequest{

	private String email;
	private String password;

	public LoginRequest() { }

	public LoginRequest(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}


	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}