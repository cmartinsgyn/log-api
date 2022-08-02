package br.com.system.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("log")
public class SistemaApiProperty {

    private String originPermitida = "http://localhost:4200";

    private final Seguranca seguranca = new Seguranca();

    //getters and setters
    public String getOriginPermitida() {
        return originPermitida;
    }

    public void setOriginPermitida(String originPermitida) {
        this.originPermitida = originPermitida;
    }

    public Seguranca getSeguranca() {
        return seguranca;
    }



   //class
    public static class Seguranca {
        private boolean enableHttps;

        public boolean isEnableHttps() {
            return enableHttps;
        }

        public void setEnableHttps(boolean enableHttps) {
            this.enableHttps = enableHttps;
        }
    }
}
