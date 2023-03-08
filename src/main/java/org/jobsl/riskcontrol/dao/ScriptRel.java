package org.jobsl.riskcontrol.dao;

import lombok.Data;

import java.io.Serializable;

/**
 * script_rel
 *
 * @author
 */
@Data
public class ScriptRel implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 父脚本code
     */
    private String parentCode;

    /**
     * 子脚本code
     */
    private String childCode;

    private static final long serialVersionUID = 1L;
}