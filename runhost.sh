#!/bin/bash
#if [ ! -f "build/jar/Host.jar" ]; then
  ant compile jar
#fi

java -jar build/jar/Host.jar "ruby /Users/gu/dev/org.github.pistacchio.streamcommunication/bots/ruby/rubybot.rb" "ruby /Users/gu/dev/org.github.pistacchio.streamcommunication/bots/ruby/rubybot.rb"
