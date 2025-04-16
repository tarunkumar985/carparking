package com.example.carparking.auth.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.carparking.auth.dto.RegisterWithRolesRequest;
import com.example.carparking.auth.dto.UserRequestDTO;
import com.example.carparking.auth.entity.Role;
import com.example.carparking.auth.entity.Users;
import com.example.carparking.auth.mapper.UserMapper;
import com.example.carparking.auth.repository.RoleRepository;
import com.example.carparking.auth.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private JWTService jwtService;

	@Autowired
	AuthenticationManager authManager;

	@Autowired
	private UserRepo repo;

	@Autowired
	private RoleRepository roleRepository;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	@Autowired
	private UserMapper userMapper;

	public String register(UserRequestDTO requestDTO) {
		Users users = userMapper.toEntity(requestDTO);

		Role userRole = roleRepository.findById("ROLE_USER")
				.orElseThrow(() -> new RuntimeException("Default role not found"));
		users.setRoles(Set.of(userRole));
		users.setPassword(encoder.encode(users.getPassword()));
		repo.save(users);
		
		return "User registered with ROLE_USER";
	}

	public String verify(Users user) {
		Authentication authentication = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		if (authentication.isAuthenticated()) {
			return jwtService.generateToken(userDetails);
		} else {
			return "fail";
		}
	}

	public String customregisterUser(RegisterWithRolesRequest registerWithRolesRequest) {
		Set<Role> roles = registerWithRolesRequest.getRoleNames().stream()
				.map(roleName -> roleRepository.findById(roleName)
						.orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
				.collect(Collectors.toSet());

		Users users = new Users();
		users.setUsername(registerWithRolesRequest.getUsername());
		users.setPassword(encoder.encode(registerWithRolesRequest.getPassword()));
		users.setRoles(roles);
		repo.save(users);
		return "User registered with custom roles";
	}
}
