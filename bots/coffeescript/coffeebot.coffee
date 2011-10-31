rl = require 'readline'
fs = require 'fs'

std = rl.createInterface process.stdin, process.stdout, null

global.readingCode = false;
global.readCode = 'asdf';
global.linesOfCodeRead = 0;

std.on 'line', (line) ->
  if global.readingCode
    global.readCode += line
    if global.linesOfCodeRead == 4
      sharp = global.readCode.indexOf '#'
      y = Math.floor (sharp / 5)
      x = sharp - (y * 5)
      process.stdout.write "#{ x } #{ y }\n"
      
      global.readingCode = false
      global.readCode = ''
      global.linesOfCodeRead = 0
    else
      global.linesOfCodeRead++
  else
    switch line
      when 'ready?' then process.stdout.write 'ready!\n'
      when 'generate code'
        sharp = Math.floor (Math.random() * 25)
        for i in [0..24]
          process.stdout.write (if i == sharp then '#' else '.')
          if (i + 1) % 5 == 0 then (process.stdout.write '\n' )
      when 'find code'
        global.readingCode = true
        global.readCode = ''
        global.linesOfCodeRead = 0
      when 'bye!'
        process.stdout.write 'bye!\n';
        process.exit(code=0);
        
      

          