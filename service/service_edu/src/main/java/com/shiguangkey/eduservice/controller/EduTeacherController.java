package com.shiguangkey.eduservice.controller;


import com.shiguangkey.eduservice.entity.EduTeacher;
import com.shiguangkey.eduservice.service.EduTeacherService;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-09-03
 */
@RestController
@RequestMapping("/eduservice/eduteacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    @GetMapping
    public List<EduTeacher> getAllTeacher(){
        List<EduTeacher> teacherList = teacherService.list(null);
        return teacherList;
    }

}

