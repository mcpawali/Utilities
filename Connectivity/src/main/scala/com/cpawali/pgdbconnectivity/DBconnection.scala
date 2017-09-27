package com.cpawali.pgdbconnectivity
import slick.driver.PostgresDriver


/**
 * Created by chandrashekhar on 7/7/2015.
 */
object DBconnection extends PostgresDriver{
  val db=slick.driver.PostgresDriver.backend.Database.forURL("driver",
    user="postgres",password = "admin",driver = "org.postgresql.Driver")

}
