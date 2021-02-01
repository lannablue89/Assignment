package com.nab.assignment.ui.weather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nab.assignment.di.NetworkModule
import com.nab.assignment.model.Status
import com.nab.assignment.network.WeatherService
import com.nab.assignment.test_util.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class WeatherViewModelUnitTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineScopeRule = MainCoroutineScopeRule()

    private var mockWebServer = MockWebServer()
    private val gson = NetworkModule.providesGson()

    private lateinit var weatherService: WeatherService
    private lateinit var weatherRepository: WeatherRepository
    private lateinit var weatherViewModel: WeatherViewModel

    @Before
    fun setup() {

        mockWebServer = MockWebServer()

        val okHttpClient = OkHttpClient.Builder()
            .dispatcher(okhttp3.Dispatcher(OkHttpTestExecutor(mainCoroutineScopeRule.dispatcher)))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        weatherService = retrofit.create(WeatherService::class.java)
        weatherRepository = WeatherRepository(weatherService)
        weatherViewModel = WeatherViewModel(weatherRepository)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun successCase() = mainCoroutineScopeRule.runBlockingTest {
        mockWebServer.enqueue(MockResponse().setBodyFromFile("weather.json"))

        weatherViewModel.runTest {
            weatherViewModel.data.observeForTesting {
                weatherViewModel.fetchData()

                weatherViewModel.data.captureValues {
                    weatherViewModel.fetchSearchData("saigon")
                    MatcherAssert.assertThat(values[0]?.status, Matchers.equalTo(Status.LOADING))
                    MatcherAssert.assertThat(values[1]?.status, Matchers.equalTo(Status.SUCCESS))
                    MatcherAssert.assertThat(values[1]?.data, Matchers.hasSize(1))
                }
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun errorCase() = mainCoroutineScopeRule.runBlockingTest {
        mockWebServer.enqueue(MockResponse().setBodyFromFile("weather-error.json"))

        weatherViewModel.runTest {
            weatherViewModel.data.observeForTesting {
                weatherViewModel.fetchData()
                weatherViewModel.data.captureValues {
                    weatherViewModel.fetchSearchData("saigon")
                    MatcherAssert.assertThat(values[0]?.status, Matchers.equalTo(Status.LOADING))
                    MatcherAssert.assertThat(values[1]?.status, Matchers.equalTo(Status.ERROR))
                    MatcherAssert.assertThat(values[1]?.error?.message, Matchers.equalTo("error-message"))
                }
            }
        }
    }

}