package controllers

import play.api._
import play.api.mvc._
import org.scala_tools.time.Imports._

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
  
  def year(year:Int) = Action {
    val newYear = new DateTime(year, 1, 1, 0, 0)
    val nextYear = newYear + 1.year
    val articles = blog.articlesBetween(newYear.toDate(), nextYear.toDate())
    Ok(views.html.Blog.year(year, articles))
  }
  
  def month(year:Int, month:Int) = Action {
    val newMonth = new DateTime(year, month, 1, 0, 0)
    val nextMonth = newMonth + 1.month
    val articles = blog.articlesBetween(newMonth.toDate(), nextMonth.toDate())
    Ok(views.html.Blog.month(year, month, articles))
  }
  
  def detail(year:Int, month:Int, day:Int, slug:String) = Action {
    import models.Article
    
    val date = new DateTime(year, month, day, 0, 0)
    val nextDay = date + 1.day
    blog.articlesBetween(date.toDate(), nextDay.toDate()) find { a =>
      a.slug == slug
    } match {
      case Some(a) => Ok(views.html.Blog.detail(a))
      case None => NotFound
    }
  }
  
}
