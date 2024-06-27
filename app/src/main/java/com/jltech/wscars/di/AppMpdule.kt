package com.jltech.wscars.di


import DatabaseHelper
import com.jltech.wscars.api.service.WSCarsService
import com.jltech.wscars.repository.OrderRepository
import com.jltech.wscars.repository.WSCarsRepository
import com.jltech.wscars.ui.main.car.CarsViewModel
import com.jltech.wscars.ui.main.home.HomeViewModel
import com.jltech.wscars.ui.main.solicitation.SolicitationViewModel
import com.jltech.wscars.utils.Constants
import com.jltech.wscars.utils.Preferences
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECTION_TIMEOUT = 30 * 1000

val netWorkModule = module {
    single {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .header("accept", "application/json")
                    .header(
                        "Authorization",
                        "Bearer ${get<Preferences>().getToken()}"
                    )
                    .build()
                chain.proceed(newRequest)
            }
            .addInterceptor(logging)
            .connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .followRedirects(true)
            .followSslRedirects(true)
            .build()
    }

    single(named("Retrofit")) {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}

val viaCepApi = module {
    single<Retrofit>(qualifier = named("viaCepRetrofit")) {
        Retrofit.Builder()
            .baseUrl("https://viacep.com.br/ws/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}


val databaseModule = module {

    single {
        Preferences(androidContext())
    }
    single {
        DatabaseHelper(androidContext())
    }
}
val serviceModule = module {
    single {
        get<Retrofit>(qualifier = named("Retrofit")).create(WSCarsService::class.java)
    }

}
val repositoryModule = module {

    single<CoroutineDispatcher> { Dispatchers.Default }

    single {
        WSCarsRepository(get())
    }
    single {
        OrderRepository(get(), get())
    }

}
val viewModelModule = module {
    viewModel {
        HomeViewModel(get())
    }
    viewModel {
        CarsViewModel(get())
    }
    viewModel {
        SolicitationViewModel(get())
    }

}


val listModules =
    listOf(netWorkModule, databaseModule, serviceModule, repositoryModule, viewModelModule, viaCepApi)
