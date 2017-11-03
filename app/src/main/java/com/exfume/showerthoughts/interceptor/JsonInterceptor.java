package com.exfume.showerthoughts.interceptor;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class JsonInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url().newBuilder().addPathSegment(".json").build();
        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}
