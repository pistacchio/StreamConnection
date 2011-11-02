<?php
  error_reporting(0);

  while(1)
  {
    $line = stream_get_line(STDIN, 1024, PHP_EOL);
    switch($line)
    {
      case 'ready?':
        echo "ready!\n";
        flush();
        break;
      case 'generate code':
        $sharp = rand(0, 24);
        for ($i = 0; $i < 25; $i++)
        {
          echo $i == $sharp ? '#' : '.';
          if (($i + 1) % 5 == 0) { echo "\n"; }
          flush();
        }
        break;
      case 'find code':
        $code = '';
        for ($i = 0; $i < 5; $i++)
        {
          $code .= stream_get_line(STDIN, 1024, PHP_EOL);
        }
        $sharp = strpos($code, '#');
        $y = floor($sharp / 5);
        $x = $sharp - ($y * 5);
        echo "$x $y\n";
        flush();
        break;
      case 'bye!':
        echo "bye!\n";
        flush();
        exit(0);
        break;
    }
  }
?>