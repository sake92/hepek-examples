package examples.multi.wordpress

import com.afrozaar.wordpress.wpapi.{v2 => wp}

// this has nothing to do with Hepek,
// just an ordinary java library! :)
object WpClient {
  import wp.config.ClientFactory
  import wp.config.ClientConfig

  private val baseUrl              = "https://howtodoinjava.com"
  private val username: String     = null
  private val password: String     = null
  private val usePermalinkEndpoint = false
  private val debug                = false

  val client = ClientFactory.fromConfig(
    ClientConfig.of(baseUrl, username, password, usePermalinkEndpoint, debug)
  )
}

object WpData {
  import scala.collection.JavaConverters._

  val posts = { // fetched only once
    val response = WpClient.client.search(
      wp.request.SearchRequest.Builder
        .aSearchRequest(classOf[wp.model.Post])
        .withPagination(50, 1)
        .build()
    )
    response.getList.asScala.toSeq
  }
}
