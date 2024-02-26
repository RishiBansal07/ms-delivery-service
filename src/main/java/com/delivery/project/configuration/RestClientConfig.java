//package com.delivery.project.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.web.client.RestTemplate;
//import javax.net.ssl.SSLContext;
//import org.apache.http.client.HttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.ssl.SSLContextBuilder;
//import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
//
//@Configuration
//public class RestClientConfig {
//
//    @Bean
//    public RestTemplate restTemplate() throws Exception {
//        SSLContext sslContext = SSLContextBuilder.create()
//                .loadTrustMaterial(new TrustSelfSignedStrategy())
//                .loadKeyMaterial(getClass().getResource("/ssl/PackageSelfService.cer"), "<password_for_certificate>".toCharArray(), "<password_for_key>".toCharArray())
//                .build();
//
//        HttpClient client = HttpClients.custom()
//                .setSSLContext(sslContext)
//                .build();
//
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(client);
//        return new RestTemplate(factory);
//    }
//}
