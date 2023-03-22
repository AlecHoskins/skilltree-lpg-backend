package com.alechoskins.skilltreelpgbackend.security;

public final class AbstractAuthConst {
    public static final class Roles{
        public static final String USER = "USER";
        public static final String ADMIN = "ADMIN";
    }
    public static final class Permissions{
        public final String USER_GET = "USER_GET";
        public final String USER_GET_ALL = "USER_GET_ALL";
        public final String USER_CREATE = "USER_CREATE";
        public final String USER_UPDATE = "USER_UPDATE";
        public final String USER_DELETE = "USER_DELETE";
    }
}
