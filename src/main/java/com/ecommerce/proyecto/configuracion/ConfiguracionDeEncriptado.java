package com.ecommerce.proyecto.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ConfiguracionDeEncriptado {

    @Bean
    public PasswordEncoder CodificarContrasena() {
        return new BCryptPasswordEncoder();
    }

}
