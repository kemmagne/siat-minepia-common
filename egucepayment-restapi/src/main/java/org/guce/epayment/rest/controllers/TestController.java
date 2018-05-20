package org.guce.epayment.rest.controllers;

import org.guce.epayment.rest.dto.Message;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tadzotsa
 */
@Transactional
@RestController
public class TestController {

    @RequestMapping("hello")
    public String welcome() {

        return "Welcome to RestTemplate Example !";
    }

    @RequestMapping("hello/{player}")
    public Message message(@PathVariable String player) {

        return Message.of(player, "Hello " + player);
    }

    @RequestMapping("public/hello/{player}")
    public Message publicMessage(@PathVariable String player) {

        return Message.of(player, "Hello " + player);
    }

    @RequestMapping("admin/hello")
    public String adminMessage() {

        return "Welcome to RestTemplate Example admin !";
    }

}
