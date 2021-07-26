package com.example.customtag;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;

public class MyTag extends SimpleTagSupport {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        StringWriter writer = new StringWriter();

        if (message != null) {
            out.println(message);
            return;
        }

        getJspBody().invoke(writer); // 
        out.println(writer.toString());
    }
}
