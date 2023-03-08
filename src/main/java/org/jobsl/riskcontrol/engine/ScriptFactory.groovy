package org.jobsl.riskcontrol.engine

class ScriptFactory {
    static String getScriptByTemplate(String rule) {
        return "import static cn.hutool.extra.spring.SpringUtil.*;\n" +
                "import org.jobsl.riskcontrol.commons.*;\n" +
                "        \n" +
                "        def rule = {\n" +
                "            input -> \n" +
                "                ${rule}\n" +
                "        }\n" +
                "        \n" +
                "        rule(input)"
    }
}
