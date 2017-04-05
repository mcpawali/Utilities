package com.cpawali.pgdbconnectivity

import slick.lifted.TableQuery
import scala.concurrent.ExecutionContext.Implicits.global
import slick.driver.PostgresDriver.api._
/**
  * Created by Cpawali on 04-Apr-17.
  */
object Transaction {
  import DBconnection._

  def getHotels() = {
    val priority = TableQuery[Priority]
    val ress = db.run(priority.result)
    ress.map(c => println("Result==>" + c))
  }
}

object simpleRun extends App {
  val res = Transaction.getHotels()
  Thread.sleep(5000)

}
