package org.jobsl.riskcontrol.engine

import org.springframework.stereotype.Component

import javax.annotation.Resource

@Component
class ExecutorAdapter {
    @Resource
    private ScriptCache scriptCache;
    @Resource
    private Executor executor;

    def execute(String code, Map<String, Object> params) {
        Script script = scriptCache.getScriptCache(code)
        if (!script) {
            return null
        }
        return executor.execute(script, params)
    }

    def executes(String code, Map<String, Object> params) {
        List<List<Script>> scripts = scriptCache.getScriptsByLevelRel(code)
        if (!scripts) {
            return null
        }
        return executor.executes(scripts, params)
    }
}
