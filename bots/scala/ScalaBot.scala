import scala.util.Random

object ScalaBot {
    def main(args: Array[String]) {
      while (true)
      {
        var ln = Console.readLine()
        
        ln match
        {
          case "ready?" => { println("ready!") }
          case "generate code" =>
          {
            val rnd = new Random().nextInt(24)
            for (i <- 0 until 25)
            {
              print(
                { if (i == rnd) "#" else "."} + 
                { if (i == 24 || (((i+1) % 5) == 0)) "\n" else ""})
            }            
          }
          case "find code" =>
          {
            var code = ""
            for (i <- 0 until 5) {
              code += Console.readLine()
            }
            val sharp = code.indexOf('#')
            val y = sharp / 5
            val x = sharp - (y * 5)
            println("%d %d" format(x, y))
          }
          case "bye!" => { println("bye!"); System.exit(0) }
          case _ => { System.exit(1) }
        }
      }
    }
  }

ScalaBot.main(args);