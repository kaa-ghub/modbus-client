package com.energoit.modbus.client;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@Data
public class Properties {
    @Value("${application.address:#{null}}")
    private String address;

    @Value("${application.port:#{null}}")
    private int port;
}
