package com.chenyilei.atcrowdfunding.mymain.listenr;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @date 2019/01/16- 16:29
 */
public class MailListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        System.err.println("发生了邮件");
    }
}
