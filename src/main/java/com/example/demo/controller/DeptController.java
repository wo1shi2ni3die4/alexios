package com.example.demo.controller;

import com.example.demo.pojo.Dept;
import com.example.demo.pojo.Result;
import com.example.demo.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class DeptController {
    //日志
    //private static Logger log= LoggerFactory.getLogger(DeptController.class);
    //@RequestMapping(value = "/depts",method = RequestMethod.GET)
    @Autowired
    private DeptService deptService;
    @GetMapping("/depts")
    public Result list(){
        log.info("查询全部部门数据");
        List<Dept> deptList=deptService.list();
        return Result.success(deptList);
    }
    @DeleteMapping("/depts/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除部门:{}",id);
        deptService.delete(id);
        return Result.success();
    }
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept){
        log.info("新增部门:{}",dept);
        deptService.add(dept);
        return Result.success();
    }
    @GetMapping("depts/{id}")
    public Result selectById(@PathVariable Integer id){
        log.info("查询部门:{}",id);
        Dept dept=deptService.selectById(id);
        return Result.success(dept);
    }
    @PutMapping("depts")
    public Result update(@RequestBody Dept dept){
        log.info("修改部门:{}",dept);
        deptService.update(dept);
        return Result.success();
    }

}
