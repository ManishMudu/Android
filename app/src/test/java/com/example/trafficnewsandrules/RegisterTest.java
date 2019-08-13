package com.example.trafficnewsandrules;

import com.example.trafficnewsandrules.BLL.RegisterBLL;
import com.example.trafficnewsandrules.Models.User;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class RegisterTest {
    @Test
    public void testResgister() {
        RegisterBLL registerBLL = new RegisterBLL();
        User user = new User("l", "l", "lm", "l", "l", "l");
        boolean result = registerBLL.registerUser(user);
        assertEquals(true, result);
    }
}
