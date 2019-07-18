package com.book.controller;

import com.book.entity.Role;
import com.book.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class CommonControl {

    @RequestMapping("/dashboard")
    public String dashboard(HttpSession httpSession, Model model) {
        Role role = (Role) httpSession.getAttribute("role");
        String roleName = role.getName();

        if (roleName.equals("ROLE_ADMIN")) {
            return "admin/index";
        } else {
            model.addAttribute("classActiveLogin", true);
            return "myAccount";
        }

    }
}
