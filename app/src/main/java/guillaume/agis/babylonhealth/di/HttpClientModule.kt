package guillaume.agis.babylonhealth.di

import dagger.Module
import dagger.Provides
import guillaume.agis.babylonhealth.BuildConfig
import guillaume.agis.babylonhealth.api.ApiService
import guillaume.agis.babylonhealth.api.HttpErrorUtils
import guillaume.agis.babylonhealth.api.RetrofitApiService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class HttpClientModule {

    @Provides
    @Singleton
    fun createHttpClient(): Retrofit {
        return RetrofitApiService().createClient(BuildConfig.API_BASE_URL)
    }

    @Provides
    @Singleton
    fun createHttpErrorUtils(): HttpErrorUtils {
        return HttpErrorUtils()
    }


    @Provides
    @Singleton
    fun createApiService(retrofit: Retrofit): ApiService {
        return RetrofitApiService().createApiService(retrofit, ApiService::class.java)
    }

}