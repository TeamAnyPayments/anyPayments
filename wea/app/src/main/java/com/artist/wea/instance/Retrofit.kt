package com.artist.wea.instance

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.artist.wea.service.RegisterService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class Retrofit : AppCompatActivity() {
    object Instance {

        // OkhttpClient 객체
        // 서버와의 통신에서 응답 대기 시간이나 쿠키 관련 설정을 편집하기 위함.
        var builder = OkHttpClient().newBuilder()
        var okHttpClient = builder
            //.cookieJar(JavaNetCookieJar(CookieManager()))
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .addInterceptor(retrofitInterceptor())
            .build()

        // 인터셉터
        // HTTP 통신에서 서버와의 요청(Request)에 보낼 헤더 값을 수정하는 등의 작업 가능
        class retrofitInterceptor : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain) : Response = with(chain) {
                val newRequest = request().newBuilder()
                    // .addHeader("Authorization", "Bearer ${}")
                    .build()
                Log.d("REQUEST:::", newRequest.toString())
                Log.d("REQUEST:::", newRequest.method.toString())
                Log.d("REQUEST:::", newRequest.body.toString())
                Log.d("REQUEST:::", newRequest.headers.toString())
                proceed(newRequest)
            }
        }

        // lazy 패턴을 통해 Retrofit 인스턴스를 생성함
        // url 설정, client 객체 등록 등의 작업 수행
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl("https://api.kabserv.xyz/api/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val api : RegisterService by lazy {
            retrofit.create(RegisterService::class.java)
        }
    }

    companion object {
    }
}