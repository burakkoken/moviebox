package org.codnect.moviebox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MovieboxDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieboxDiscoveryApplication.class, args);
    }

}
