package org.tomaszkowalczyk94.pomodorotasksmanager.core.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfigurator {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
