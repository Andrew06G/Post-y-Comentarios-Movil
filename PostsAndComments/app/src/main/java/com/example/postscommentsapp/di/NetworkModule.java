package com.example.postscommentsapp.di;

import com.example.postscommentsapp.data.remote.JSONPlaceholderService;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import javax.inject.Singleton;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public JSONPlaceholderService provideJsonPlaceholderService(Retrofit retrofit) {
        return retrofit.create(JSONPlaceholderService.class);
    }
}
