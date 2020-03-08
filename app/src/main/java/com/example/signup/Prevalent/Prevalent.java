package com.example.signup.Prevalent;

import com.example.signup.Model_login_signp.Users;

/*
shared data for the remember me and forgot password functioning

storing values in UserEmailKey and UserPasswordKey
 */

public class Prevalent {
    private static Users currentOnlineUser;

    public static final String UserEmailKey="UserEmail";
    public static final String UserPasswordKey="UserPassword";
}
