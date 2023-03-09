create table script
(
    id     bigint auto_increment comment '主键ID'
        primary key,
    code   varchar(10) default '' not null comment '编码',
    script text null comment '脚本',
    constraint code_uindex
        unique (code),
    constraint script_code_uindex
        unique (code)
) comment '脚本';

INSERT INTO risk_control.script (id, code, script)
VALUES (1, 'R01', 'def res = [:]
if (input.x == input.y) {
    res[''msg''] = ''Equal''
} else {
    res[''msg''] = ''Not equal''
}
res');
INSERT INTO risk_control.script (id, code, script)
VALUES (2, 'R02', 'def res = [:]
if (input.x > input.y) {
    res[''msg''] = ''Greater''
} else {
    res[''msg''] = ''Less or equal''
}
res');
INSERT INTO risk_control.script (id, code, script)
VALUES (3, 'R03', 'def res = [:]
getBean(ToolsDemo.class).test()
res');
INSERT INTO risk_control.script (id, code, script)
VALUES (4, 'R04', 'def result = [:]
result[''a''] = (3 * 100) as Integer
result');
INSERT INTO risk_control.script (id, code, script)
VALUES (5, 'R05', 'def result = [:]
result[''b''] = (256 - 56) as Integer
result');
INSERT INTO risk_control.script (id, code, script)
VALUES (6, 'R06', 'def result = [:]
result[''sum''] = (input.a as Integer) + (input.b as Integer)
result');
INSERT INTO risk_control.script (id, code, script)
VALUES (7, 'R07', 'getBean(ToolsDemo.class).test()');
