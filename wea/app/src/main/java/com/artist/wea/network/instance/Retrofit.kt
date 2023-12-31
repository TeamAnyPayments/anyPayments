package com.artist.wea.network.instance

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.mutableStateOf
import com.artist.wea.network.service.ArtistService
import com.artist.wea.network.service.RegisterService
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
                    .addHeader("Authorization", "Bearer ${token.value}")
                    .build()
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
        // api url 도메인에 따른 분기
        val user : RegisterService by lazy {
            retrofit.create(RegisterService::class.java)
        }
        val artist : ArtistService by lazy {
            retrofit.create((ArtistService::class.java))
        }
        // val concert
    }

    companion object {
        val token = mutableStateOf("")
    }
}