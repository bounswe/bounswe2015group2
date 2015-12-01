package edu.boun.cmpe451.group2.client;

/**
 * Created by Mustafa Taha on 1.12.2015.
 */
public class Response {
    public enum STATUS{
        OK,ERROR
    }
    public STATUS status;
    public String message;
    public String api_key;
}
