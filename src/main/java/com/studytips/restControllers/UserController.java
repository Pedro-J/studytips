package com.studytips.restControllers;

import com.studytips.entities.User;
import com.studytips.enums.UserProfile;
import com.studytips.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * Created by comp-dev on 4/21/17.
 *
 */
@RestController
@RequestMapping(value = "/studytips")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/users/all")
    public List<User> query(){
        return userService.findAll();
    }

    @RequestMapping(value = "/users", method=RequestMethod.GET)
    public Page<User> query(Pageable pageable){
        return userService.findAllPageable(pageable);
    }

    @RequestMapping("/users/{id}")
    public User query(@PathVariable Integer id){
        return userService.findById(id);
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public User save(@RequestBody @Valid User user){
        return userService.save(user);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public User update(@RequestBody @Valid User user){
        return userService.update(user);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id){
        userService.delete(id);
    }

    @RequestMapping("/users/profiles")
    public List<String> getProfiles(){
        List<String> profiles = new ArrayList<>();
        for(UserProfile profile: UserProfile.values()){
            profiles.add(profile.name());
        }
        return profiles;
    }
}
