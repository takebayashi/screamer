package models

import java.util.Date

case class Article(slug:String,
                   title:String,
                   body:String,
                   posted:Date) {
  
  val pathId = ("/%tY/%<tm/%<td/" format posted) + slug
  
}

object Article {
  
  def apply(o:com.mongodb.DBObject):Article = {
    Article(o.get("slug").asInstanceOf[String],
            o.get("title").asInstanceOf[String],
            o.get("body").asInstanceOf[String],
            o.get("posted").asInstanceOf[Date])
  }
  
}
