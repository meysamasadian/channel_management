package com.meysamasadian.channelmanagement.service.treasury;

/**
 * Created by rahnema on 9/6/2017.
 */
public enum Path {
    REGISTER_ACCOUNT("/account/register"),
    LOGIN("/account/login/"),
    ISSUE_DOCUMENT("/document/issue/"),
    REVERSE_DOCUMENT("/document/reverse/"),;

    private String path;

    Path(String path) {
        this.path = path;
    }


    @Override
    public String toString() {
        return path;
    }
}
