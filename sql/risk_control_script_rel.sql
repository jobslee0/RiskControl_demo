create table script_rel
(
    id          bigint auto_increment comment '主键ID'
        primary key,
    parent_code varchar(10) default '' null comment '父脚本code',
    child_code  varchar(10) default '' null comment '子脚本code'
) comment '脚本关系';

create index child_code_index
    on script_rel (child_code);

create index parent_code_index
    on script_rel (parent_code);

INSERT INTO risk_control.script_rel (id, parent_code, child_code)
VALUES (1, 'R06', 'R04');
INSERT INTO risk_control.script_rel (id, parent_code, child_code)
VALUES (2, 'R06', 'R05');
INSERT INTO risk_control.script_rel (id, parent_code, child_code)
VALUES (3, 'R06', 'R07');
INSERT INTO risk_control.script_rel (id, parent_code, child_code)
VALUES (5, 'R01', 'R07');
