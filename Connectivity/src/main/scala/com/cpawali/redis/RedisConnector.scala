package com.cpawali.redis

import com.redis.RedisClientPool

/**
  * Created by chandrashekhar on 4/7/2017.
  */
object RedisConnector extends App {
  val redisClient = new RedisClientPool("192.168.1.165", 6379,database = 2)

  def lp(msgs: String): Boolean = {
    redisClient.withClient {
      client => client.hset("Test", "1", "Hellow")
    }
  }

  def lpGet: Option[String] = {
    redisClient.withClient {
      client => client.hget("Test", "1")
    }
  }

  //lpGet.map { x => println(x) }
  val dd=lp("dsfd")
  println(dd)
  //  val system = ActorSystem("ScatterGatherSystem")
  //  import system.dispatcher
  //
  //  val timeout = 5 minutes

  //  private[this] def flow[A](noOfRecipients: Int, opsPerClient: Int, keyPrefix: String,
  //                            fn: (Int, String) => A) = {
  //    (1 to noOfRecipients) map {i =>
  //      Future {
  //        fn(opsPerClient, "list_" + i)
  //      }
  //    }
  //  }
  //
  //  // scatter across clients and gather them to do a sum
  //  def scatterGatherWithList(opsPerClient: Int)(implicit clients: RedisClientPool) = {
  //    // scatter
  //    val futurePushes = flow(100, opsPerClient, "list_", listPush)
  //
  //    // concurrent combinator: Future.sequence
  //    val allPushes = Future.sequence(futurePushes)
  //
  //    // sequential combinator: flatMap
  //    val allSum = allPushes flatMap {result =>
  //      // gather
  //      val futurePops = flow(100, opsPerClient, "list_", listPop)
  //      val allPops = Future.sequence(futurePops)
  //      allPops map {members => members.sum}
  //    }
  //    Await.result(allSum, timeout).asInstanceOf[Long]
  //  }
  //
  //  // scatter across clietns and gather the first future to complete
  //  def scatterGatherFirstWithList(opsPerClient: Int)(implicit clients: RedisClientPool) = {
  //    // scatter phase: push to 100 lists in parallel
  //    val futurePushes = flow(100, opsPerClient, "seq_", listPush)
  //
  //    // wait for the first future to complete
  //    val firstPush = Future.firstCompletedOf(futurePushes)
  //
  //    // do a sum on the list whose key we got from firstPush
  //    val firstSum = firstPush map {key =>
  //      listPop(opsPerClient, key)
  //    }
  //    Await.result(firstSum, timeout).asInstanceOf[Int]
  //  }

}
