package org.jobsl.riskcontrol.engine

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.stereotype.Component

import javax.annotation.Resource
import java.util.concurrent.CompletionService
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ExecutorCompletionService

@Component
class Executor {
    @Resource
    private ThreadPoolTaskExecutor rcThreadPool;

    def execute(Script script, Map<String, Object> params) {
        println(Thread.currentThread().getName())
        Binding binding = new Binding([input: params])
        script.setBinding(binding)
        script.run()
    }

    def executes(List<List<Script>> scripts, Map<String, Object> params) {
        Map<String, Object> result = new ConcurrentHashMap<>(params)
        CompletionService<Map<String, Object>> completionService = new ExecutorCompletionService<>(rcThreadPool)
        for (List<Script> ss : scripts) {
            ss.parallelStream().forEach(s ->
                    completionService.submit(() -> {
                        execute(s, result)
                    }))
            for (i in 0..<ss.size()) {
                Map<String, Object> temp = completionService.take().get()
                if (temp) {
                    result.putAll(temp)
                }
            }
        }
        return result
    }
}
