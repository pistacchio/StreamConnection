#!/usr/local/bin/ruby

stream = $stdin
stream.each do |line|
  case line.chomp
  when "ready?"
    puts "ready!"
    STDOUT.flush
  when "generate code"
    code = "#........................".split(//).sort_by {rand}
    code.each_slice(5).each do |row|
      puts row.join('')
      STDOUT.flush
    end
  when "find code"    
    y = 0
    x = 0
    for _y in (0..4)
     _x = stream.readline.index('#')
     if _x 
       y = _y
       x = _x
     end
    end
    puts "#{x} #{y}"
    STDOUT.flush
  when "bye!"
    puts "bye!"
    STDOUT.flush
    exit
  end
end