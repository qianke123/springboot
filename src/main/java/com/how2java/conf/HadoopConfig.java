package com.how2java.conf;

import org.apache.hadoop.fs.FileSystem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URI;

@Configuration
public class HadoopConfig {

    @Value("${hadoop.URI}")
    private String hadoopURI;

    @Value("${hadoop.user}")
    private String user;

    @Bean
    public FileSystem fileSystem() throws IOException, InterruptedException {
        org.apache.hadoop.conf.Configuration hadoopConfiguration = new org.apache.hadoop.conf.Configuration();
        FileSystem fileSystem = FileSystem.get(URI.create(hadoopURI), hadoopConfiguration, user);
        return fileSystem;
    }
}
