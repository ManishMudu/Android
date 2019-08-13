package com.example.trafficnewsandrules;

import com.example.trafficnewsandrules.BLL.LoginBLL;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class LoginTest {

    @Test
    public void testLogin(){
        LoginBLL loginBLL = new LoginBLL("m", "m");
        boolean result = loginBLL.checkUser();
        assertEquals(true, result);
    }

}
