package org.jobsl.riskcontrol.dao;

import lombok.Data;

import java.io.Serializable;

/**
 * script
 *
 * @author
 */
@Data
public class Script implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 编码
     */
    private String code;

    /**
     * 脚本
     */
    private String script;

    private static final long serialVersionUID = 1L;
}