package com.studytips;


import com.studytips.builder.MockUsers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application test
 * Created by Michael DESIGAUD on 15/02/2016.
 */
@SpringBootApplication
public class StudyTipsApplicationTest {

    public static void main(String[] args) {
        MockUsers.mock();
        SpringApplication.run(StudyTipsApplication.class, args);
    }
}
