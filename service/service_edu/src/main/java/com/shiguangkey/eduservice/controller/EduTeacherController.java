package com.shiguangkey.eduservice.controller;


import com.shiguangkey.eduservice.entity.EduTeacher;
import com.shiguangkey.eduservice.service.EduTeacherService;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 查询所有讲师
     * @return
     */
    @GetMapping
    public List<EduTeacher> getAllTeacher(){
        List<EduTeacher> teacherList = teacherService.list(null);
        return teacherList;
    }

    /**
     * 根据ID逻辑删除讲师
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public boolean delTeacher(@PathVariable String id){
        boolean remove = teacherService.removeById(id);
        return remove;
    }

}

