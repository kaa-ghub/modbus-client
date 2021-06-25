package com.energoit.modbus.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class ModbusService {
    private final Properties properties;

    @PostConstruct
    public void start() throws Exception {
        TCPModbus.callTCPModbus(properties.getAddress(), properties.getPort());
    }

}
