package org.despina;

import org.apache.http.auth.AuthSchemeProvider;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.impl.auth.NTLMSchemeFactory;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.util.ArrayList;
import java.util.Arrays;

public class NtlmAuthImplemetation {

   static CloseableHttpClient constructNtlmAuthHttpClient(int connectionTimeout, int connectionRequestTimeout, ArrayList<String> credentials){
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(connectionTimeout * 1000)
                .setConnectionRequestTimeout(connectionRequestTimeout * 1000)
                .setCookieSpec(CookieSpecs.DEFAULT)
                .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.KERBEROS, AuthSchemes.SPNEGO))
                .build();


        PoolingHttpClientConnectionManager connectionManager =  new PoolingHttpClientConnectionManager();

        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(AuthScope.ANY, new NTCredentials(credentials.get(0), credentials.get(1),"despina-dell",credentials.get(2)));

        Registry<AuthSchemeProvider> authSchemeRegistry = RegistryBuilder.<AuthSchemeProvider>create()
                .register(AuthSchemes.NTLM, new NTLMSchemeFactory())
                .build();

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(credsProvider)
                .setDefaultAuthSchemeRegistry(authSchemeRegistry)
                .setConnectionManager(connectionManager)
                .setDefaultCookieStore(new BasicCookieStore())
                .setDefaultRequestConfig(config);

        return httpClientBuilder.build();
    }

}
