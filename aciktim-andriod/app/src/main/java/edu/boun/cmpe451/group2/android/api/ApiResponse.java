package edu.boun.cmpe451.group2.android.api;

/**
 * Created by Mustafa Taha on 1.12.2015.
 */
public class ApiResponse {
    public enum STATUS{
        OK,ERROR
    }
    public STATUS status;
    public String message;
    public String api_key;
}
