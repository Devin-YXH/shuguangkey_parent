package com.shiguangkey.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shiguangkey.commonutils.R;
import com.shiguangkey.eduservice.entity.EduTeacher;
import com.shiguangkey.eduservice.service.EduTeacherService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "查询所有讲师")
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
    @ApiOperation(value = "根据ID逻辑删除讲师")
    @DeleteMapping("{id}")
    public boolean delTeacher(@PathVariable String id){
        boolean remove = teacherService.removeById(id);
        return remove;
    }

    @ApiOperation(value = "分页查询讲师列表")
    @GetMapping("getTeacherPage/{current}/{limt}")
    public R getTeacherPage(@PathVariable Long current,@PathVariable Long limt){
        Page<EduTeacher> page = new Page<>(current, limt);
        List<EduTeacher> records = page.getRecords();
        long total = page.getTotal();

        return R.ok().data("list",records).data("total",total);
    }

}

