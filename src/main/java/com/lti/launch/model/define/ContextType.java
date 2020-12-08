package com.lti.launch.model.define;

public enum ContextType {
    COURSE("Course"), TEST(" ");

    private String value;

    ContextType(String course) {
        value = course;
    }

    public String getValue(){ return value;}

}
