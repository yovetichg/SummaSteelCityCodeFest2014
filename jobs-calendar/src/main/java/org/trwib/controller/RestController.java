package org.trwib.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RestController {

    @RequestMapping(value = "/helloworld/{name}", method = RequestMethod.GET)
    public @ResponseBody String getTextFromURL(@PathVariable("name") String name){
        return "Hello " + name;
    }
}
