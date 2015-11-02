package edu.boun.cmpe451.group2.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
@Scope("request")
public class Comment {
    public Long id = null;
    public Long userID= null;
    public String comment = "";


}
