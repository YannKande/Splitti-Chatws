package nehe.chatappapi.controllers;


import nehe.chatappapi.Modals.*;
import nehe.chatappapi.Services.AuthService;
import nehe.chatappapi.Utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/")
public class AuthenticationController {

	private AuthUtil authUtil;
	private AuthService authService;
    
	@Autowired
	public AuthenticationController(AuthUtil authUtil,AuthService authService) {
		this.authUtil = authUtil;
	    this.authService = authService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginRequest request)
			throws Exception {

		String token = authUtil.authenticateAndGetToken(request.getEmail(), request.getPassword());

		return ResponseEntity.ok(new LoginResponse( token ));
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user)
			throws Exception {

		Objects.requireNonNull(user);

		var nonEncodedPass = user.getPassword();

		User savedUser = authService.addUser(user);

		String token = authUtil.authenticateAndGetToken(savedUser.getEmail(),nonEncodedPass);

		var modifiedUser = new UserRes( savedUser.getId(), savedUser.getFirstName() ,
				savedUser.getLastName(), savedUser.getEmail(), savedUser.getRole());

		return ResponseEntity.status(HttpStatus.CREATED).body(new RegisterResponse(token, modifiedUser));
	}

	

}
