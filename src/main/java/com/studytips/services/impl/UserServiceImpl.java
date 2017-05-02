package com.studytips.services.impl;

import com.studytips.configuration.security.SecurityUser;
import com.studytips.entities.User;
import com.studytips.repositories.UserRepository;
import com.studytips.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service("userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
	private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("User "+username+" not found");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        if(user.getAuthorities() != null){
            for(String authority : user.getAuthorities()){
                authorities.add(new SimpleGrantedAuthority(authority));
            }
        }

        return new SecurityUser(user.getId(), user.getLogin(), user.getPassword(), user.getProfile(), authorities);
    }

    public User save(User user){
        return userRepository.saveAndFlush(user);
    }

    public User update(User user){
        User savedUser = userRepository.findOne(user.getId());
        savedUser.setProfile(user.getProfile());
        savedUser.setLogin(user.getLogin());
        savedUser.setFirstName(user.getFirstName());
        savedUser.setLastName(user.getLastName());
        savedUser.setBirthDate(user.getBirthDate());
        return userRepository.saveAndFlush(savedUser);
    }

    @Transactional(readOnly = true)
    public User findById(Integer id){
        return userRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    public List<User> findAll(){
        return userRepository.findAll();
    }

    public void delete(Integer id){
        userRepository.delete(id);
    }

    @Transactional(readOnly = true)
    public Page<User> findAllPageable(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
