import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.clarotest.domain.use_cases.GetEntriesUseCase
import com.example.clarotest.ui.details.DetailsFragment
import com.example.clarotest.ui.home.HomeFragment
import com.example.clarotest.ui.home.HomeViewModel
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    private val getEntriesUseCase: GetEntriesUseCase = mockk()
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var homeViewModel: HomeViewModel

    @JvmField
    @Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        homeViewModel = HomeViewModel(getEntriesUseCase)
    }

    @Test
    fun fragment_ShouldNOT_be_Null() {
        // Given
        val fragment = HomeFragment()
        // When

        // Then
        Assert.assertNotNull(fragment)
    }

    @Test
    fun detail_fragment_ShouldNOT_be_Null() {
        // Given
        val fragment = DetailsFragment()
        // When

        // Then
        Assert.assertNotNull(fragment)
    }

//    @Test
//     fun `test get all entries`() = runBlocking  {
//        // Arrange
//        val entries = listOf(
//            EntryDetails("AdoptAPet", "Resource to help get pets adopted", "apiKey", true, "yes", "https://www.adoptapet.com/public/apis/pet_list.html", "Animals"),
//            EntryDetails("Axolotl", "Collection of axolotl pictures and facts", "", true, "no", "https://theaxolotlapi.netlify.app/", "Animals")
//        )
//        val resource = Resource.Success(entries)
//        coEvery { getEntriesUseCase() } returns flowOf(
//            resource
//        )
//
//        // Act
//        homeViewModel.getAllEntries()
//        delay(4000)
//        // Assert
//        val expected = Resource.Success(entries)
//
//        val actual = homeViewModel.entries.value
//        assert(homeViewModel.entries.value is Resource.Success)
//
//        assertEquals(expected, actual)
//    }


}
