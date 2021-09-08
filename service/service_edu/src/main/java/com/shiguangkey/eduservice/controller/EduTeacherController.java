package com.shiguangkey.eduservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shiguangkey.commonutils.R;
import com.shiguangkey.eduservice.entity.EduTeacher;
import com.shiguangkey.eduservice.entity.vo.TeacherQuery;
import com.shiguangkey.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 讲师管理前端控制器
 *
 * @author Devin
 * @since 2021-09-03
 */

@Api(description="讲师管理")
@RestController
@RequestMapping("/eduservice/eduteacher")
@CrossOrigin
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    /**
     * 查询所有讲师
     *
     * @return 返回所有讲师数据
     */
    @ApiOperation(value = "查询所有讲师")
    @GetMapping
    public List<EduTeacher> getAllTeacher() {
        return teacherService.list(null);
    }

    /**
     * 根据ID逻辑删除讲师
     *
     * @param id 讲师ID
     * @return 成功或者失败
     */
    @ApiOperation(value = "根据ID逻辑删除讲师")
    @DeleteMapping("{id}")
    public R delTeacher(@PathVariable String id) {
        boolean remove = teacherService.removeById(id);
        if(remove){
            return R.ok();
        }else{
            return R.error();
        }
    }

    /**
     * 分页查询讲师列表
     *
     * @param current 当前页
     * @param limit    每页显示记录数
     * @return 分页后的数据
     */
    @ApiOperation(value = "分页查询讲师列表")
    @PostMapping("getTeacherPage/{current}/{limit}")
    public R getTeacherPage(@PathVariable Long current,
                            @PathVariable Long limit) {
        Page<EduTeacher> page = new Page<>(current, limit);
        teacherService.page(page, null);
        List<EduTeacher> records = page.getRecords();
        long total = page.getTotal();
        return R.ok().data("list", records).data("total", total);
    }

    /**
     * 带条件的分页查询讲师数据
     * @param current 当前页
     * @param limit     每页显示记录数
     * @param teacherQuery  查询实体
     * @return 分页后的数据
     */
    @ApiOperation(value = "带条件的分页查询讲师数据")
    @PostMapping("getTeacherPageVo/{current}/{limit}")
    public R getTeacherPageVo(@PathVariable Long current,
                              @PathVariable Long limit,
                              @RequestBody TeacherQuery teacherQuery) {
        //@RequestBody把json串转换成实体类
        //1、取出查询的条件
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        //2、判断条件是否为空，如果不为空就拼接SQL
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create", end);
        }
        //分页查询
        Page<EduTeacher> page = new Page<>(current, limit);
        teacherService.page(page, wrapper);
        List<EduTeacher> records = page.getRecords();
        long total = page.getTotal();

        return R.ok().data("list", records).data("total", total);
    }

    /**
     * 添加讲师
     * @param eduTeacher 讲师实体
     * @return 添加成功或者失败
     */
    @ApiOperation("添加讲师")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = teacherService.save(eduTeacher);
        if(save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    /**
     * 修改前根据讲师ID查询讲师数据
     * @param id 讲师ID
     * @return 讲师数据
     */
    @ApiOperation("修改前根据讲师ID查询讲师数据")
    @GetMapping("getTeacherById/{id}")
    public R getTeacherById(@PathVariable String id){
        EduTeacher eduTeacher = teacherService.getById(id);
        return R.ok().data("eduTeacher",eduTeacher);
    }

    /**
     * 修改讲师
     * @param eduTeacher 讲师实体
     * @return 修改成功或失败
     */
    @ApiOperation("修改讲师")
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean update = teacherService.updateById(eduTeacher);
        if(update){
            return R.ok();
        }else{
            return R.error();
        }
    }
}

