package models

import com.mongodb.casbah.Imports._
import java.util.Date

object Blog {
  
  def apply(host:String, port:Int, username:String, password:String) = {
    val database = MongoConnection(host, port)("blog")
    database.authenticate(username, password)
    new Blog(database)
  }
  
}

class Blog(val database:MongoDB) {
  
  val collection = database("articles")
  
  def article(slug:String):Option[Article] = {
    collection.findOne(MongoDBObject("slug" -> slug)) match {
      case Some(a) => Some(Article(a))
      case None => None
    }
  }
  
  def articlesBetween(start:Date, end:Date):Seq[Article] = {
    val condition = ("posted" $gte start) ++ ("posted" $lt end)
    collection.find(condition).toList map { a =>
      Article(a)
    }
  }
  
}
