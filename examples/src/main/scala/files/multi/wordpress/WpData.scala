package files.multi.wordpress

import scala.jdk.CollectionConverters.*
import com.afrozaar.wordpress.wpapi.v2.config.ClientConfig
import com.afrozaar.wordpress.wpapi.v2.config.ClientFactory
import com.afrozaar.wordpress.wpapi.v2.request.SearchRequest
import com.afrozaar.wordpress.wpapi.v2.model.Post

object WpData:

  val baseUrl = "https://thoughts-on-java.org"

  val perPage = 10
  
  // fetched only once per "hepek" invocation
  val posts: Seq[Post] = locally {
    // just an ordinary java library..
    val client = ClientFactory.fromConfig(
      ClientConfig.of(baseUrl, null, null, false, false)
    )
    val response = client.search(
      SearchRequest.Builder
        .aSearchRequest(classOf[Post])
        .withPagination(50, 1)
        .build()
    )
    response.getList.asScala.toSeq
  }

  val pageCount = posts.length / perPage
