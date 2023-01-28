package nehe.chatappapi.Modals;


public class RegisterResponse {

	private  String jwtToken;
	private  Object user;

	public RegisterResponse(String jwtToken, Object user) {
		this.jwtToken = jwtToken;
		this.user = user;
	}

	public Object getUser() {
		return user;
	}

	public String getJwtToken() {
		return jwtToken;
	}

}