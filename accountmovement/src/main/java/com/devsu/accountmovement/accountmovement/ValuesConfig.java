package com.devsu.accountmovement.accountmovement;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource(value="classpath:config.properties", encoding = "UTF-8"),
})
public class ValuesConfig {
    
}
