package com.cpawali.mac

import java.net._

import scala.collection.JavaConversions._
import scala.util.{Failure, Success, Try}

/**
  * Created by chandrashekhar on 3/21/2017.
  */
object MacAddressUtility {

  def getAddress: String = {
    val net = NetworkInterface.getNetworkInterfaces.toSeq
    val result = Try(net.filter(x => !isVMMac(x.getHardwareAddress)).flatMap { network =>
      network.getInetAddresses.filter(ip => ip.isInstanceOf[Inet4Address] && ip.isSiteLocalAddress) map { ip =>
        Some(InetAddress.getByName(ip.getHostAddress))
        getMacAddress(InetAddress.getByName(ip.getHostAddress)) match {
          case Success(str) => str
          case Failure(ex) => ex.getMessage
        }
      }
    })
    if (result.isSuccess && result.get.nonEmpty) {
      val results = result.get.toList
      println(results.last)
      results.last
    } else ""
  }

  private def getMacAddress(ip: InetAddress): Try[String] = {
    Try({
      val network: NetworkInterface = NetworkInterface.getByInetAddress(ip)
      val mac: Array[Byte] = network.getHardwareAddress
      mac.toList.map(b => String.format("%02x", b.asInstanceOf[AnyRef])).mkString("")
    }
    )
  }

  private def isVMMac(mac: Array[Byte]): Boolean = {
    if (null == mac) false else {
      val invalidMacs = Array(Array(0x00, 0x05, 0x69), Array(0x00, 0x1C, 0x14), Array(0x00, 0x0C, 0x29), Array(0x00, 0x50, 0x56),
        Array(0x08, 0x00, 0x27), Array(0x0A, 0x00, 0x27), Array(0x00, 0x03, 0xFF.toByte), Array(0x00, 0x15, 0x5D))
      val x = for (invalid <- invalidMacs) yield {
        invalid(0) == mac(0) && invalid(1) == mac(1) && invalid(2) == mac(2)
      }
      x.forall(_ == true)
    }
  }
}


