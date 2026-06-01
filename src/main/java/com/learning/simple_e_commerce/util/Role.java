package com.learning.simple_e_commerce.util;

public enum Role {
    ADMIN,
    USER;

    public String toString(){
        return this.name().toLowerCase();
    }
}

