package top.zang.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 */
@Configuration
@MapperScan({"top.zang.dao","top.zang.mbg.mapper"})
public class MyBatisConfig {
}
