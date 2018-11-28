package com.arco.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arco.bo.user.UserBO;
import com.arco.model.ArcoUser;
import com.arco.model.RequestResponse;


@RestController
@RequestMapping("/user")
public class UserManagementService {
	
	
	@Autowired
	private UserBO userBo;
 
	
	@PostMapping("/login")
	public RequestResponse login(@RequestBody ArcoUser arcoUser) {
		RequestResponse response = new RequestResponse();
		try {
			Map<String, Object> model = new HashMap<>();
			ArcoUser user = userBo.login(arcoUser.getUsername(), arcoUser.getPassword());
			
			if(user!=null) {
				response.setMessage("success");
				model.put("user", user);
				response.setModel(model);
			}else {
				response.setHasError(true);
				response.setMessage("Invalid credentials");
			}
			
		}catch(Exception e) {
				e.printStackTrace();
				response.setHasError(true);
				response.setMessage("User could not be added");
			}
			
			return response;
		}
	
	   
	
	@PostMapping("/createuser")
	public RequestResponse createUser(@RequestBody ArcoUser arcoUser) {
		RequestResponse response = new RequestResponse();
		
		try {
			userBo.createUser(arcoUser);
			Map<String,Object> model = new HashMap<>();
			model.put("user", arcoUser);
			response.setModel(model);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setHasError(true);
			response.setMessage("User could not be added");
		}

		return response;
	}
	
	
	
	
	@GetMapping("/getusers")
	public RequestResponse getUser() {
		RequestResponse response = new RequestResponse();
		
		try {
			List<ArcoUser> arcoUsers = userBo.getUsers();
			Map<String,Object> model = new HashMap<>();
			model.put("users", arcoUsers);
			response.setModel(model);
		} catch (Exception e) {
			e.printStackTrace();
			response.setHasError(true);
			response.setMessage("Users could not be fetched");
		}
		
		return response;
		
	}
}