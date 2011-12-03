package controllers

import play.api._
import play.api.mvc._

object Blog extends Controller {
  
  val blog = {
    val app = Play.unsafeApplication
    val config = app.configuration
    val host = config.getString("blog.host").getOrElse("localhost")
    val port = config.getString("blog.port").getOrElse("10052").toInt
    val username = config.getString("blog.username").getOrElse("")
    val password = config.getString("blog.password").getOrElse("")
    import models.{Blog => B}
    B(host, port, username, password)
  }
  
  def index = Action {
    Ok(views.html.Blog.index(blog.recentArticles(10)))
  }
  
}
