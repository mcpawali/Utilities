package com.cpawali.pgdbconnectivity

import java.sql.Timestamp
import java.sql.Timestamp

import slick.driver.PostgresDriver.api._
import slick.lifted.Tag

import scala.concurrent.ExecutionContext.Implicits.global
/**
 * Created by chandrashekhar on 7/15/2015.
 */
class Priority(tag: Tag) extends Table[(Long, String, Boolean, Boolean, Long, Timestamp, Long, Timestamp)](tag, Some("asset"), "Priority") {
  def id = column[Long]("PriorityId", O.AutoInc, O.PrimaryKey)

  def priorityName = column[Option[String]]("PriorityName", O.Length(50, varying = true), O.Default(None))

  def isRemoved = column[Boolean]("IsRemoved")

  def isPendingForApproval = column[Boolean]("IsPendingForApproval", O.Default(false))

  def createdBy = column[Long]("CreatedBy")

  def createDate = column[java.sql.Timestamp]("CreateDate")

  def modifiedBy = column[Long]("ModifiedBy")

  def modifiedDate = column[java.sql.Timestamp]("ModifiedDate")

  def * = (id, priorityName.get, isRemoved, isPendingForApproval, createdBy, createDate, modifiedBy, modifiedDate)
}


