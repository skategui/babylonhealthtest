package guillaume.agis.babylonhealth.api

import guillaume.agis.babylonhealth.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Responsible to create the Retrofit Object to create the HTTPClient, responsible to make requests
 */
class RetrofitApiService {

    fun createClient(url: String): Retrofit {
        val client = OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(interceptor)
            }

            addInterceptor { chain ->
                val request = chain
                    .request()
                    .newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")

                chain.proceed(request.build())
            }
            readTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
            connectTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
        }.build()


        return Retrofit.Builder().apply {
            baseUrl(url)
            client(client)
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            addConverterFactory(GsonConverterFactory.create())
        }.build()
    }

    fun <T> createApiService(retrofit: Retrofit, serviceClass: Class<T>): T = retrofit.create(serviceClass)

    companion object {
        private const val TIMEOUT_SEC = 20L
    }
}
