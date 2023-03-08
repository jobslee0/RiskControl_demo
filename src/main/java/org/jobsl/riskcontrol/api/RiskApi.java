package org.jobsl.riskcontrol.api;

import org.jobsl.riskcontrol.engine.ExecutorAdapter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class RiskApi {
    @Resource
    private ExecutorAdapter executorAdapter;

    @PostMapping("/execute")
    public Object execute(@RequestBody RiskBody body) {
        return executorAdapter.execute(body.getCode(), body.getParams());
    }

    @PostMapping("/executes")
    public Object executes(@RequestBody RiskBody body) {
        return executorAdapter.executes(body.getCode(), body.getParams());
    }
}
