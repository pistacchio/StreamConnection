BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) 

String input

while ((line = br.readLine()) != -1)
{
  switch(line)
  {
    case "ready?":
      println "ready!"
      break;  
    case "generate code":
      random = new Random()
      sharp = random.nextInt(24)
      (0..24).each
      { i ->
        print ((i == sharp) ? "#" : ".")
        if (i == 24 || ((i+1) % 5) == 0) println ""
      }
      break;
    case "find code":
      code = ""
      5.times { code += br.readLine() }
      sharp = code.indexOf('#')
      y = (int)Math.floor(sharp / 5)
      x = sharp - (y * 5)
      println "${x} ${y}"
      break;
    case "bye!":
      println "bye!"
      System.exit(0)
      break;
  }
}