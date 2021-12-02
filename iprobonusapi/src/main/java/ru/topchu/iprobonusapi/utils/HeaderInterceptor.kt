package ru.topchu.iprobonusapi.utils
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(val accessKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("AccessKey", this@HeaderInterceptor.accessKey)
                .build()
        )
    }
}