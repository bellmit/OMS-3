package com.baiyang.oms.rest.config;

import com.baiyang.oms.config.properties.RestProperties;
import com.baiyang.oms.rest.modular.auth.converter.WithSignMessageConverter;
import com.baiyang.oms.core.config.DefaultFastjsonConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 签名校验messageConverter
 *
 * @author fengshuonan
 * @date 2017-08-25 16:04
 */
@Configuration
public class MessageConverConfig {

    @Bean
    @ConditionalOnProperty(prefix = RestProperties.REST_PREFIX, name = "sign-open", havingValue = "true", matchIfMissing = true)
    public WithSignMessageConverter withSignMessageConverter() {
        WithSignMessageConverter withSignMessageConverter = new WithSignMessageConverter();
        DefaultFastjsonConfig defaultFastjsonConfig = new DefaultFastjsonConfig();
        withSignMessageConverter.setFastJsonConfig(defaultFastjsonConfig.fastjsonConfig());
        withSignMessageConverter.setSupportedMediaTypes(defaultFastjsonConfig.getSupportedMediaType());
        return withSignMessageConverter;
    }
}
