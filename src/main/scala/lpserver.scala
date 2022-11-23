import org.zeromq.ZMQ;
import java.util.Random;

/*
 * Lazy Pirate server
 * @author Zac Li
 * @email zac.li.cmu@gmail.com 
 */

object lpserver {

  def main(args: Array[String]) {
    val rand = new Random(System.nanoTime())

    val context = ZMQ.context(1)
    val server = context.socket(ZMQ.REP)
    server.bind("tcp://*:5555")

    var cycles = 0;
      try {
          while (true) {
              val request = server.recvStr()
              cycles = cycles + 1

              //  Simulate various problems, after a few cycles
              if (cycles > 3 && rand.nextInt(3) == 0) {
                  println("I: simulating a crash")
                  throw new Exception("break")
              } else if (cycles > 3 && rand.nextInt(3) == 0) {
                  println("I: simulating CPU overload")
                  Thread.sleep(2000)
              }
              println(s"I: normal request ($request)\n")
              Thread.sleep(1000)
              server.send(request)
          }
      }catch{
          case _: Any =>
      }

    server close()
    context term()
  }
}