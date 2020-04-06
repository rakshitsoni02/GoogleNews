import com.rax.googlenews.core.utils.formatPublishedDate
import org.junit.Assert
import org.junit.Test

/**
 * Created by Rax on 02/04/20.
 */
class DateFormatterExtensionsTest {


    @Test
    fun formatPublishedDateTest() {
        Assert.assertEquals("2020-04-02T08:27:39Z".formatPublishedDate(), "02 Apr 2020")
    }

    @Test
    fun formatPublishedDateInvalid() {
        Assert.assertEquals("any".formatPublishedDate(), null)
    }
}

