package org.jobsl.riskcontrol.engine

class Compiler {
    private static GroovyShell GROOVY_SHELL = new GroovyShell()

    static def compile = { script -> GROOVY_SHELL.parse(script) }
}
