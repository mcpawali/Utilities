package com.cpawali

import org.joda.time.LocalDate
import org.scalatest.{Matchers, WordSpec}

/**
  * Created by Cpawali on 05-Apr-17.
  */
case class A(name: String, dob: LocalDate)
case class B(name: String, dob: LocalDate)

class JsonUtilitiesTest extends WordSpec with Matchers {

  import com.cpawali.json.Utilities._

  "JsonUtilitiesTest" must {
    "UtilitiesComponent Parse successfully" in {
      val aa = A("Cpawali", LocalDate.parse("2015-02-02"))
      val parsedResult = aa.asJson
      parsedResult shouldBe """{"name":"Cpawali","dob":"2015-02-02"}"""
    }

    "UtilitiesComponent extract successfully" in {
      val json = """{"name":"Cpawali","dob":"2015-02-02"}"""
      val extracted = extractEntity[A](json)
      extracted shouldBe an [B]
    }
  }

}
