package org.guce.epayment.rest.dto;

import lombok.Data;

/**
 *
 * @author tadzotsa
 */
@Data
public class Message {

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
