package org.ranger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.context.annotation.Scope;

@Controller
@Scope("request")
@RequestMapping("student")
public class StudentAction {

}