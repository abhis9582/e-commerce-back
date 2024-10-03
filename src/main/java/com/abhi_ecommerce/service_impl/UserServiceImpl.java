package com.abhi_ecommerce.service_impl;

import com.abhi_ecommerce.entity.User;
import com.abhi_ecommerce.exception.UserException;
import com.abhi_ecommerce.jwtutill.JwtProvider;
import com.abhi_ecommerce.repository.UserRepository;
import com.abhi_ecommerce.response.ProductRepository;
import com.abhi_ecommerce.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private JwtProvider jwtProvider;
    public UserServiceImpl(
            UserRepository userRepository,
            JwtProvider jwtProvider
    ){
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }
    @Override
    public User findUserById(int userid) throws UserException {
        Optional<User> user = userRepository.findById(userid);
        if(user.isPresent()){
           return user.get();
        }
        throw new UserException("User not found with userId: "+userid);
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        String email = jwtProvider.getEmailFromToken(jwt);
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UserException("User not found with email "+email);
        }

        return user;
    }
}
