package org.jobsl.riskcontrol.api;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class RiskBody {
    private String code;

    private Map<String, Object> params = new HashMap<>();
}
