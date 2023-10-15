package com.userTest.app.ws.service;

import com.userTest.app.ws.entity.UserEntity;
import com.userTest.app.ws.repository.UserRepository;
import com.userTest.app.ws.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto user) {
       UserEntity checkUser= userRepository.findByUsername(user.getUsername());
        if (!isAdult(user.getBirthdate()) || !isFrenchResident(user.getCountry())) {
            throw new IllegalArgumentException("L'utilisateur ne remplit pas les conditions requises.");
        }
       if (checkUser!=null) throw new RuntimeException("User Already Exists !");
        UserEntity userEntity=new UserEntity();
        BeanUtils.copyProperties(user,userEntity);
        UserEntity newUser=userRepository.save(userEntity);
        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(newUser,userDto);
        return userDto;
    }

    @Override
    public UserDto getUserById(String id) {
        UserEntity userEntity=userRepository.findByUserId(id);
        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(userEntity,userDto);
        return userDto;
    }

    private boolean isAdult(Date birthdate) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -18);
        Date eighteenYearsAgo = cal.getTime();

        return birthdate.before(eighteenYearsAgo);
    }

    private boolean isFrenchResident(String country) {
        return "France".equalsIgnoreCase(country);
    }
}
