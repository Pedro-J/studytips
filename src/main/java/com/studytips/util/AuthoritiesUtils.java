package com.studytips.util;

import com.studytips.enums.UserProfile;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by comp-dev on 2/19/17.
 */
public class AuthoritiesUtils {

    private static Map<UserProfile,List<String>> authorities =  new HashMap(){{
        put(UserProfile.ADMIN, Arrays.asList("ROLE_ADMIN","ROLE_MANAGER","ROLE_USER"));
        put(UserProfile.USER, Arrays.asList("ROLE_USER"));
        put(UserProfile.MANAGER, Arrays.asList("ROLE_MANAGER","ROLE_USER"));
    }};

    public static List<String> get(UserProfile key){
        return authorities.get(key);
    }
}
