package org.jobsl.riskcontrol.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class ThreadPoolConfig {
    @Value("${rcThreadPool.corePoolSize}")
    private Integer corePoolSize;
    @Value("${rcThreadPool.maxPoolSize}")
    private Integer maxPoolSize;
    @Value("${rcThreadPool.queueCapacity}")
    private Integer queueCapacity;

    @Bean(name = "rcThreadPool")
    public ThreadPoolTaskExecutor rcThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        return executor;
    }
}
