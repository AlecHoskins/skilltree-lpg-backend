package com.alechoskins.skilltreelpgbackend.global;

public class Endpoints {

    public static final String AUTH = "/Auth";
    public static final String AUTH_REGISTER = AUTH + "/Register";
    public static final String AUTH_AUTHENTICATE = AUTH + "/Authenticate";

    public static final String USER = "/User";
    public static final String USER_GET = USER+"/{id}";
    public static final String USER_GET_ALL = USER+"/All";

}
