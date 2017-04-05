package com.cpawali.mac

import java.net._

import scala.collection.mutable.ListBuffer

/**
  * Created by chandrashekhar on 4/4/2017.
  */
object JavaOriginalMac extends App{
    GetAddress("mac")
    def GetAddress(addressType: String): String = {
      var address: String = ""
      var buff:ListBuffer[String]=ListBuffer.empty
      var lanIp: InetAddress = null
      try {
        var ipAddress: String = null
        var net = NetworkInterface.getNetworkInterfaces
        while (net.hasMoreElements) {
          val element: NetworkInterface = net.nextElement
          val addresses = element.getInetAddresses
          while (addresses.hasMoreElements && !isVMMac(element.getHardwareAddress)) {
            val ip: InetAddress = addresses.nextElement
            if (ip.isInstanceOf[Inet4Address]) {
              if (ip.isSiteLocalAddress) {
                ipAddress = ip.getHostAddress
                lanIp = InetAddress.getByName(ipAddress)
                buff=buff++List(lanIp.getHostAddress)
              }
            }
          }
        }
        if (lanIp == null) return null
         if (addressType == "mac") {
           println(">>"+lanIp.getHostAddress)
          address = getMacAddress(lanIp)
        }
        else {
          throw new Exception("Specify \"ip\" or \"mac\"")
        }
      }
      catch {
        case ex: UnknownHostException => {
          ex.printStackTrace
        }
        case ex: SocketException => {
          ex.printStackTrace
        }
        case ex: Exception => {
          ex.printStackTrace
        }
      }
      println(buff)
      return address
    }

    private def getMacAddress(ip: InetAddress): String = {
      try {
        val network: NetworkInterface = NetworkInterface.getByInetAddress(ip)
        val mac: Array[Byte] = network.getHardwareAddress
        println(mac.toList.map(b => String.format("%02x", b.asInstanceOf[AnyRef])).mkString(":"))
        mac.toList.map(b => String.format("%02x", b.asInstanceOf[AnyRef])).mkString(":")

      }
      catch {
        case ex: SocketException => {
          ex.getMessage
        }
      }
    }

    def isVMMac(mac: Array[Byte]): Boolean = {
      if (null == mac) return false
      val invalidMacs = Array(Array(0x00, 0x05, 0x69), Array(0x00, 0x1C, 0x14), Array(0x00, 0x0C, 0x29), Array(0x00, 0x50, 0x56), Array(0x08, 0x00, 0x27), Array(0x0A, 0x00, 0x27), Array(0x00, 0x03, 0xFF.toByte), Array(0x00, 0x15, 0x5D))
      for (invalid <- invalidMacs) {
        if (invalid(0) == mac(0) && invalid(1) == mac(1) && invalid(2) == mac(2)) return true
      }
      return false
    }
  }

