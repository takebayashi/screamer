package models

import java.util.Date

case class Article(slug:String,
                   title:String,
                   body:String,
                   posted:Date)

object Article {
  
  def apply(o:com.mongodb.DBObject):Article = {
    Article(o.get("slug").asInstanceOf[String],
            o.get("title").asInstanceOf[String],
            o.get("body").asInstanceOf[String],
            o.get("posted").asInstanceOf[Date])
  }
  
}
