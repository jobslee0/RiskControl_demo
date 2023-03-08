package org.jobsl.riskcontrol;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("org.jobsl.riskcontrol.dao")
@SpringBootApplication
public class RiskControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(RiskControlApplication.class, args);
    }

}
