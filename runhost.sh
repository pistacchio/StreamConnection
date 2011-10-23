#!/bin/bash
#if [ ! -f "build/jar/Host.jar" ]; then
  ant compile jar
#fi

java -jar build/jar/Host.jar "/opt/local/bin/python /Users/gu/dev/org.github.pistacchio.streamcommunication/pybot.py"
