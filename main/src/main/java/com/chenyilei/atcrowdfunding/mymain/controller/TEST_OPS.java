package com.chenyilei.atcrowdfunding.mymain.controller;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @date 2018/12/25- 13:11
 */

import com.chenyilei.atcrowdfunding.bean.User;
import com.chenyilei.atcrowdfunding.common.h.Page;
import com.chenyilei.atcrowdfunding.manager.dao.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @date 2018/12/25- 13:06
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring/spring-*.xml"})
public class TEST_OPS {
    @Autowired
    private UserMapper userMapper ;

    @Autowired
    ProcessEngine processEngine;
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    TaskService taskService;
    @Autowired
    HistoryService historyService;

    /**
     *  {@link RepositoryService}
     *  activiti 测试
     *  1 创建表、部署了一个 process1的 流程定义
     */
    @Test
    public void activitiTest1(){
        processEngine.getRepositoryService().createDeployment()
                .addClasspathResource("process2.bpmn")
                .deploy();

        //查询操作
        DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery();
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
//        processDefinitionQuery.orderByDeploymentId().desc().list()
//        processDefinitionQuery.processDefinitionKey("1").list()
    }

    /**
     *  {@link RepositoryService} 用来保存
     * 2 创建流程实例
     */
    @Test
    public void activitiTest2(){
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        //1 查询出 key为 myProcess_1 的流程
        ProcessDefinition myProcess_1 = processDefinitionQuery.processDefinitionKey("myProcess_2").latestVersion().singleResult();
        //2 用run 开启一个流程实例
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(myProcess_1.getId());

        System.out.println("实例:"+processInstance);
    }

    /**
     *  {@link TaskService}
     * 3 获取 组长审批的任务, 执行任务
     */
    @Test
    public void activitiTest3(){
        TaskQuery taskQuery = taskService.createTaskQuery();
        taskQuery.taskAssignee("zhangsan"); //设置执行人

        List<Task> taskList = taskQuery.list();

        for (Task task : taskList) {
            System.err.println(task.getName()); //显示执行人所有的任务
        }

        //执行任务
        taskService.complete(taskList.get(0).getId()); //用id 决定完成哪个任务
    }

    /**
     *  {@link HistoryService}
     *  4 查看历史的信息
     */
    @Test
    public void activitiTest4(){
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        HistoricProcessInstanceQuery historicProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery();

        //查出 流程实例 id为101 的 历史
        HistoricProcessInstance historicProcessInstance = historicProcessInstanceQuery.processInstanceId("101").finished().singleResult();

    }









    /**
     * {@link UserMapper#insertList(List)}
     * 为数据库添加数据 测试
     */
    @Test
    public void ADD_USER(){
        List list = new ArrayList();
        for(int i = 100;i<200;i++){
            User user = new User();
            user.setLoginacct("user:"+i);
            user.setUserpswd("root");
            user.setCreatetime("2018-12-26 17:38:22");
            user.setEmail("7777");
            user.setUsername("user:"+i);
            list.add(user);
        }
        userMapper.insertList(list);
    }

    /**
     * 测试分页 以及相关数据的显示结果
     */
    @Test
    public void queryUserList() {
        PageHelper.startPage(2,2);
        List<User> users = userMapper.selectAll();
        Page<User> result = new Page<>();
        PageInfo info = new PageInfo(users);
        info.setNavigatePages(2);

        System.err.println("总页数:"+info.getPages());
        System.err.println("当前页数:"+info.getPageNum());
        System.err.println("总条数:"+info.getTotal());

        info.getList().forEach(System.err::println);

        System.err.println("数据"+users);
        System.err.println(info.getNavigateFirstPage());
        System.err.println(info.getNavigateLastPage());
        System.err.println(info.getNavigatePages());
    }

    @Test
    public void testSystem(){
        String dir = System.getProperty("user.dir");
    }

    @Test
    public void Linux(){

    }

}

