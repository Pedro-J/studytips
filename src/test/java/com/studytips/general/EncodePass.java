package com.studytips.general;

import com.studytips.entities.User;
import com.studytips.enums.UserProfile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by comp-dev on 2/20/17.
 */
public class EncodePass {
    public static void main(String[] args) throws JsonProcessingException {
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        String admin = encode.encode("admin");
        String manager = encode.encode("manager");
        String user = encode.encode("user");

        System.out.println("admin: " + admin);
        System.out.println("manager: " + manager);
        System.out.println("user: " + user);

        User userJson = new User();
        userJson.setProfile(UserProfile.ADMIN);
        ObjectMapper mapper = new ObjectMapper();

        String resJson = mapper.writeValueAsString(userJson);

        System.out.println("json: " + resJson);
    }
}
