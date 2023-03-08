package org.jobsl.riskcontrol.engine

import com.google.common.collect.Lists
import com.google.common.graph.GraphBuilder
import com.google.common.graph.MutableGraph
import groovy.util.logging.Slf4j
import org.apache.commons.collections.CollectionUtils
import org.jobsl.riskcontrol.dao.ScriptDao
import org.jobsl.riskcontrol.dao.ScriptRelDao
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct
import javax.annotation.Resource
import java.util.concurrent.ConcurrentHashMap

@Slf4j
@Component
class ScriptCache {
    private static Map<String, Script> SCRIPT_CACHE = new ConcurrentHashMap<>()
    private static MutableGraph<Script> GRAPH = GraphBuilder.directed().build();

    @Resource
    private ScriptDao scriptDao;
    @Resource
    private ScriptRelDao scriptRelDao;

    @PostConstruct
    void init() {
        initScript()
        initScriptRel()
    }

    private void initScript() {
        def scripts = scriptDao.selectList(null)
        if (CollectionUtils.isNotEmpty(scripts)) {
            scripts.forEach(s -> {
                SCRIPT_CACHE.put(s.getCode(), Compiler.compile(ScriptFactory.getScriptByTemplate(s.getScript())))
            })
        }
//        log.info("SCRIPT_CACHE=${SCRIPT_CACHE}")
        log.info('script cache init finished...')
    }

    private void initScriptRel() {
        def scriptRels = scriptRelDao.selectList(null)
        if (CollectionUtils.isNotEmpty(scriptRels)) {
            scriptRels.forEach(sr -> {
                GRAPH.addNode(SCRIPT_CACHE.get(sr.getParentCode()))
                GRAPH.addNode(SCRIPT_CACHE.get(sr.getChildCode()))
                GRAPH.putEdge(SCRIPT_CACHE.get(sr.getParentCode()), SCRIPT_CACHE.get(sr.getChildCode()))
            })
        }
//        log.info("GRAPH=${GRAPH}")
        log.info('script rel init finished...')
    }

    Script getScriptCache(String code) {
        return SCRIPT_CACHE.get(code)
    }

    List<List<Script>> getScriptsByLevelRel(String code) {
        Script parent = SCRIPT_CACHE.get(code)
        List<List<Script>> result = new ArrayList<>()
        Map<Script, Integer> depths = new HashMap<>()
        Map<Script, List<Script>> parents = new HashMap<>()
        Queue<Script> queue = new ArrayDeque<>()
        depths.put(parent, 0)
        parents.put(parent, new ArrayList<>())
        queue.offer(parent)
        while (!queue.isEmpty()) {
            Script currentNode = queue.poll()
            int currentDepth = depths.get(currentNode)
            if (result.size() <= currentDepth) {
                result.add(new ArrayList<>())
            }
            result.get(currentDepth).add(currentNode)
            for (Script child : GRAPH.successors(currentNode)) {
                if (!depths.containsKey(child)) {
                    depths.put(child, currentDepth + 1)
                    parents.put(child, new ArrayList<>(Collections.singleton(currentNode)))
                    queue.offer(child)
                } else if (depths.get(child) == currentDepth + 1) {
                    parents.get(child).add(currentNode)
                }
            }
        }
        return Lists.reverse(result)
    }
}
