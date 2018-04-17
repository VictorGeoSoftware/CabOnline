package com.victor.test.cabonline

import android.content.res.Resources
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.victor.test.cabonline.data.BlogRetrievedData
import com.victor.test.cabonline.data.StoreListByCity
import com.victor.test.cabonline.data.models.PhotoDto
import com.victor.test.cabonline.presenter.data.DataPresenter
import com.victor.test.cabonline.repository.JsonRepository
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.io.InputStream

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class DataPresenterTest {
    @Mock private lateinit var mockDataView: DataPresenter.DataView
    @Mock private lateinit var mockedResources: Resources
    @Mock private lateinit var mockJsonRepository: JsonRepository
    @Mock private lateinit var inputStream: InputStream
    @Mock private lateinit var mockedStoreListByCity: StoreListByCity
    @Mock private lateinit var mockedPhotoList: ArrayList<PhotoDto>
    @Mock private lateinit var mockedBlogRetrievedData: BlogRetrievedData
    private lateinit var dataPresenter: DataPresenter
    private lateinit var testScheduler: TestScheduler



    // -------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------ TEST SET UP ------------------------------------------------------------
    private fun createMockedPresenter(): DataPresenter {
        testScheduler = TestScheduler()
        val dataPresenter = DataPresenter(testScheduler, testScheduler, mockJsonRepository)
        dataPresenter.view = mockDataView

        return dataPresenter
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        dataPresenter = createMockedPresenter()
    }



    // -------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------ TEST CASES--------------------------------------------------------------
    @Test
    fun `should load the three json files at the same time with a merge observable`() {
        whenever(mockedResources.openRawResource(R.raw.store_list)).thenReturn(inputStream) /*try to get a not null inputStream */
        whenever(mockedResources.openRawResource(R.raw.photo_list)).thenReturn(inputStream)
        whenever(mockedResources.openRawResource(R.raw.blogs)).thenReturn(inputStream)

        val storeSource: InputStream = mockedResources.openRawResource(R.raw.store_list)
        val photoSource: InputStream = mockedResources.openRawResource(R.raw.photo_list)
        val blogSource: InputStream = mockedResources.openRawResource(R.raw.blogs)

        dataPresenter.loadAllData(storeSource, photoSource, blogSource)
        testScheduler.triggerActions()


        verify(mockDataView, times(1)).onStoreDataRetrieved(mockedStoreListByCity)
        verify(mockDataView, times(1)).onPhotoDataRetrieved(mockedPhotoList)
        verify(mockDataView, times(1)).onBlogDataRetrieved(mockedBlogRetrievedData)
    }

    @Test
    fun `should load the three json files at the same time with a merge observable and return an error`() {
        whenever(mockedResources.openRawResource(R.raw.store_list)).thenReturn(inputStream)
        whenever(mockedResources.openRawResource(R.raw.photo_list)).thenReturn(inputStream)
        whenever(mockedResources.openRawResource(R.raw.blogs)).thenReturn(inputStream)

        val storeSource: InputStream = mockedResources.openRawResource(R.raw.store_list)
        val photoSource: InputStream = mockedResources.openRawResource(R.raw.photo_list)
        val blogSource: InputStream = mockedResources.openRawResource(R.raw.blogs)

        dataPresenter.loadAllData(storeSource, photoSource, blogSource)
        testScheduler.triggerActions()


        verify(mockDataView, times(1)).onRetrievingDataError()
    }
}
