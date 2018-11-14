package org.guce.epayment.rest.dto;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author tadzotsa
 */
@Data
public class Message implements Serializable {

    private static final long serialVersionUID = -2155991280651961364L;

    private String name;
    private String text;

    public Message() {
    }

    private Message(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public static Message of(String name, String text) {

        return new Message(name, text);
    }

}

