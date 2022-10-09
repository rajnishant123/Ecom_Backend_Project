package com.ecom.project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecom.project.exception.ResourceNotFoundException;
import com.ecom.project.models.Role;
import com.ecom.project.models.User;
import com.ecom.project.payload.UserDto;
import com.ecom.project.respositories.RoleRepository;
import com.ecom.project.respositories.UserRespositories;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRespositories userRepo;
	@Autowired
	private ModelMapper modelMapper;

    @Autowired
    private RoleRepository roleRepository;

	@Override
	public UserDto create(UserDto userDto) {
		// TODO Auto-generated method stub
		
		//Dto to Entity
		User user= this.toEntity(userDto);
		 Role role = this.roleRepository.findById(2345).get();
	        user.getRoles().add(role);
		User createdUser=this.userRepo.save(user);
		
		//Entity to Dto
		return this.toDto(createdUser);
	}

	@Override
	public UserDto update(UserDto t, int userId) {
		// TODO Auto-generated method stub
		User u=this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found wiht this userId "+userId));
		u.setAbout(t.getAbout());
		u.setActive(t.isActive());
		u.setAddress(t.getAddress());
		u.setCreateAt(t.getCreateAt());
		u.setEmail(t.getEmail());
		u.setName(t.getName());
		u.setPassword(t.getPassword());
		u.setPhone(t.getPhone());
		User updatedUser= this.userRepo.save(u);
		
		return this.toDto(updatedUser);
	}

	@Override
	public void delete(int userId) {
		// TODO Auto-generated method stub
		User u=this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found with this userId "+userId));
		this.userRepo.delete(u);
		
	}

	@Override
	public UserDto getByUserId(int userId) {
		// TODO Auto-generated method stub
		User u=this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found with this userId "+userId));
		return this.toDto(u);
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		
	List<User> allUser=this.userRepo.findAll();
	List<UserDto> allDtoUSer=allUser.stream().map(u->this.toDto(u)).collect(Collectors.toList());
		return allDtoUSer;
	}

	@Override
	public UserDto getByEmail(String email) {
		// TODO Auto-generated method stub
		User u=this.userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User Not Found with this email "+email));
		return this.toDto(u);
	}
	
	public UserDto toDto(User user) {
		UserDto userDto= this.modelMapper.map(user,UserDto.class);
		/*
		 * UserDto dto= new UserDto(); dto.setUserId(user.getUserId());
		 * dto.setAbout(user.getAbout()); dto.setActive(user.isActive());
		 * dto.setAddress(user.getAddress()); dto.setEmail(user.getEmail());
		 * dto.setName(user.getName()); dto.setPassword(user.getPassword());
		 * dto.setPhone(user.getPhone()); dto.setCreateAt(user.getCreateAt());
		 */
		return userDto;
		
		
	}
	
	public User toEntity(UserDto t) {
		User user= this.modelMapper.map(t,User.class);
		/*
		 * User u= new User(); u.setAbout(t.getAbout()); u.setActive(t.isActive());
		 * u.setAddress(t.getAddress()); u.setCreateAt(t.getCreateAt());
		 * u.setEmail(t.getEmail()); u.setName(t.getName());
		 * u.setPassword(t.getPassword()); u.setPhone(t.getPhone());
		 * u.setUserId(t.getUserId());
		 */
		return user;
		
	}

}
