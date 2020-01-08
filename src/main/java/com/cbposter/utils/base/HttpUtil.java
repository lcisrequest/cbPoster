package com.cbposter.utils.base;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/**
 * @Auther: lc
 * @Date: 2020/1/8 15:49
 */
public class HttpUtil {
    private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);

    public HttpUtil() {
    }

    public static InputStream downFile(String src) throws IOException {
        return downFile(URI.create(src));
    }

    public static InputStream downFile(URI uri) throws IOException {
        HttpResponse httpResponse;
        try {
            Request request = Request.Get(uri);
            HttpHost httpHost = URIUtils.extractHost(uri);
            if (StringUtils.isNotEmpty(httpHost.getHostName())) {
                request.setHeader("Host", httpHost.getHostName());
            }

            request.addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
            httpResponse = request.execute().returnResponse();
        } catch (Exception var4) {
            log.error("远程请求失败，url=" + uri, var4);
            throw new FileNotFoundException();
        }

        int code = httpResponse.getStatusLine().getStatusCode();
        if (code != 200) {
            throw new FileNotFoundException();
        } else {
            return httpResponse.getEntity().getContent();
        }
    }
}

