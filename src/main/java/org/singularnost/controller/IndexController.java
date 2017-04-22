package org.singularnost.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Aidar Shaifutdinov.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "ping";
    }

}
