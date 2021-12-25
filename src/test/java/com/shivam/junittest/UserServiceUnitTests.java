package com.shivam.junittest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

public class UserServiceUnitTests extends UnitTests {


    @Test
    public void testNewUserIntroduction() {
        ResponseEntity responseEntity = userService.addUserToTheSystem("2", "User1");
        Assertions.assertEquals("200 OK", responseEntity.getStatusCode().toString());
    }
}
