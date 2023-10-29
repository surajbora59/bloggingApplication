package com.app.blog.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.app.blog.dto.UserDto;
import com.app.blog.entity.User;
import com.app.blog.exception.ResourceNotFoundException;
import com.app.blog.repository.UserRepository;
import com.app.blog.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto,User.class);
        userRepository.save(user);
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public UserDto getUserById(Integer id) {
        User user = userRepository.findById(Long.valueOf(id)).orElseThrow(()-> new ResourceNotFoundException("User", "Id", Long.valueOf(id)));
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer id) {
        User user = userRepository.findById(Long.valueOf(id)).orElseThrow(()-> new ResourceNotFoundException("User", "Id", Long.valueOf(id)));
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        user.setUsername(userDto.getUsername());
        userRepository.save(user);
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        users.forEach(user -> userDtoList.add(modelMapper.map(user,UserDto.class)));
        return userDtoList;
    }

    @Override
    public void deleteUser(Integer id) {
        User user = userRepository.findById(Long.valueOf(id)).orElseThrow(()-> new ResourceNotFoundException("User", "Id", Long.valueOf(id)));
        userRepository.delete(user);
    }

//    public User dtoToUser(UserDto userDto) {
//        User user = new User();
//        user.setUsername(userDto.getUsername());
//        user.setEmail(userDto.getEmail());
//        user.setAbout(userDto.getAbout());
//        user.setPassword(userDto.getPassword());
//        return user;
//    }

//    public UserDto userToDto(User user) {
//        UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
//        userDto.setUsername(user.getUsername());
//        userDto.setEmail(user.getEmail());
//        userDto.setAbout(user.getAbout());
//        userDto.setPassword(user.getPassword());
//        return userDto;
//    }
}
