#!/bin/bash
#if [ ! -f "build/jar/Host.jar" ]; then
  ant compile jar
#fi

java -jar build/jar/Host.jar "/Users/gu/dev/org.github.pistacchio.streamcommunication/a.out" "/Users/gu/dev/org.github.pistacchio.streamcommunication/a.out"
