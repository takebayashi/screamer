package models

import com.mongodb.casbah.Imports._

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
  
}
