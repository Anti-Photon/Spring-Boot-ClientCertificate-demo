package com.wstutorial.spingboot.security.clientcertificat.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "mtls-config", ignoreUnknownFields = true)
public class AppConfig {

    private List<String> whitelist;

    private Integer http_port;

    private Boolean enabled;

    private Map<String, String> endpoints;

    public List<String> getWhitelist() {
        return whitelist;
    }

    public Integer getHttp_port() {
        return http_port;
    }

    public Map<String, String> getEndpoints() {
        return endpoints;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setWhitelist(List<String> whitelist) {
        this.whitelist = whitelist;
    }

    public void setHttp_port(Integer http_port) {
        this.http_port = http_port;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setEndpoints(Map<String, String> endpoints) {
        this.endpoints = endpoints;
    }
}
