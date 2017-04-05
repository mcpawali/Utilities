package com.cpawali.json

import java.text.SimpleDateFormat

import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import org.json4s.{Extraction, DefaultFormats, CustomSerializer}
import org.json4s.JsonAST.{JNull, JString}
import org.json4s.jackson.JsonMethods._

/**
  * Created by chandrashekhar on 8/31/2016.
  */

trait UtilitiesComponent {
  val timeOnlyFormatter = DateTimeFormat.forPattern("HH:mm:ss")
  val dateOnlyFormatter = DateTimeFormat.forPattern("yyyy-MM-dd")

  case object SqlDateSerializer extends CustomSerializer[LocalDate](format => ( {
    case JString(date) => LocalDate.parse(date)
    case JNull => null
  }, {
    case date: LocalDate => JString(date.toString)
  }))

  implicit val formats = (new DefaultFormats {
    override def dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
  } ++ List(SqlDateSerializer))

  implicit class CustomJson(a: Any) {
    def asJson = compact(Extraction.decompose(a))
  }

  def extractEntity[E](json: String)(implicit m: Manifest[E]): E = parse(json).extract[E]
}

object Utilities extends UtilitiesComponent

