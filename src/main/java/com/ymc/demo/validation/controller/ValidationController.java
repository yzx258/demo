package com.ymc.demo.validation.controller;

import com.ymc.demo.validation.DemoModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author yucw
 * @date 2020/1/17 9:39
 * javax.validation技术栈demo
 */
@Api(value = "/validation", description = "javax.validation用法（DEMO）")
@RestController
public class ValidationController {

    /**
     * 请求OR响应格式
     */
    private static final String JSON_UTF8 = "application/json;charset=UTF-8";

    /**
     * 测试get请求
     * @param id
     * @return
     */
    @ApiOperation(value = "测试get请求")
    @RequestMapping(value = "/validation/getObjectById/{id}", method = RequestMethod.GET)
    public String getObjectById(@PathVariable("id") String id)
    {
        System.out.println("根据ID查询数据：" + id);
        return "test:"+id;
    }

    /**
     * 测试保存数据时，校验部分参数不能为空
     * @param demoModel
     * @return
     */
    @ApiOperation(value = "测试保存数据时，校验部分参数不能为空")
    @RequestMapping(value = "/validation/saveObject",method = RequestMethod.POST,
            consumes = JSON_UTF8,produces = JSON_UTF8)
    public DemoModel saveObject(@Valid @RequestBody DemoModel demoModel)
    {
        System.out.println("根据DemoModel保存数据：" + demoModel);
        return demoModel;
    }

    /**
     * 测试修改数据，校验部分参数
     * @param id
     * @return
     */
    @ApiOperation(value = "测试修改数据，校验部分参数")
    @RequestMapping(value = "/validation/updateObject/{id}",method = RequestMethod.PUT,
            consumes = JSON_UTF8,produces = JSON_UTF8)
    public DemoModel updateObject(@PathVariable("id") String id,@Valid @RequestBody DemoModel demoModel)
    {
        System.out.println("根据ID修改对象："+id);
        return demoModel;
    }
}
