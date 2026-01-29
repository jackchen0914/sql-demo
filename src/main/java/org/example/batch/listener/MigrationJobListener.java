package org.example.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MigrationJobListener implements JobExecutionListener {

    private long startTime;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        startTime = System.currentTimeMillis();
        log.info("======== 迁移任务开始 ==========");
        log.info("Job 名称：{}",jobExecution.getJobInstance().getJobName());
        log.info("开始时间： {}",jobExecution.getStartTime());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        log.info("======== 迁移任务结束 ==========");
        log.info("Job名称： {}",jobExecution.getJobInstance().getJobName());
        log.info("结束状态： {}",jobExecution.getStatus());
        log.info("耗时：{} ms ({} 秒)",duration,duration / 1000);

        if(!jobExecution.getAllFailureExceptions().isEmpty()){
            log.error("执行过程中发生异常：");
            jobExecution.getAllFailureExceptions().forEach(e->log.error("异常信息：",e));
        }
    }
}
