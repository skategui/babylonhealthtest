package guillaume.agis.babylonhealth.api

import com.google.gson.Gson
import guillaume.agis.babylonhealth.model.PostDao
import guillaume.agis.babylonhealth.utils.DataBuilder
import okhttp3.ResponseBody
import org.junit.Test

class ExtTest {

    private val gson = Gson()

    @Test
    fun `verify can convert Responsebody to List of Object`() {
        val list = DataBuilder.providePostsDaoList()
        val content = gson.toJson(list)
        val responseBody = ResponseBody.create(null, content)
        responseBody.toObjectsList<PostDao>().test()
            .assertValue(list)
    }
}

