package com.fast87.study.application;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController {

    @RequestMapping("")
    public String index() {
        return "pages/index";
    }

    @RequestMapping("/anonymous")
    public String anonymous() {
        return "pages/anonymous";
    }

    @RequestMapping("user")
    @PreAuthorize("hasAnyAuthority('USER')")
    public String user() {
        return "pages/user";
    }

    @RequestMapping("admin")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String admin() {
        return "pages/admin";
    }

}
