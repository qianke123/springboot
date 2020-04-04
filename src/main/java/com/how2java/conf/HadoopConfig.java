package com.how2java.conf;

import org.apache.hadoop.fs.FileSystem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

@Configuration
public class HadoopConfig {

    @Value("${hadoop.URL}")
    private String hadoopURL;

    @Value("${hadoop.user}")
    private String user;

    @Bean
    public FileSystem fileSystem() {
        try {
            FileSystem fileSystem = FileSystem.get(URI.create(hadoopURL), new org.apache.hadoop.conf.Configuration(), user);
            return fileSystem;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
