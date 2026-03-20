package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 数据迁移专用线程池配置
 * IO 密集型场景：核心线程数 = CPU核心数 * 2
 */
@Configuration
public class MigrationThreadPoolConfig {

    /**
     * 核心线程数：按年份分片，最大并发年数，设为 12 以覆盖各种年份跨度
     * 最大线程数：适当放大，应对突发
     * 队列容量：有界队列，防止无限制提交导致 OOM
     * 拒绝策略：CallerRunsPolicy，让调用方线程兜底执行，不丢任务
     */
    @Bean(name = "migrationThreadPool")
    public ThreadPoolExecutor migrationThreadPool() {
        int corePoolSize = 12;
        int maximumPoolSize = 20;
        long keepAliveSeconds = 60L;
        int queueCapacity = 50;

        return new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveSeconds,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(queueCapacity),
                r -> {
                    Thread t = new Thread(r);
                    // 线程名由业务层在 processOneYear 中按年份动态设置
                    // 这里给一个临时名，避免显示 Thread-N
                    t.setName("migration-year-pending");
                    t.setDaemon(false);
                    return t;
                },
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }
}