#!/bin/bash
#if [ ! -f "build/jar/Host.jar" ]; then
  ant compile jar
#fi

java -jar build/jar/Host.jar "php bots/php/phpbot.php" "php bots/php/phpbot.php"
