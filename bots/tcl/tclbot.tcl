#!/usr/local/bin/tclsh
#for { set i 0 } { $i <= 10000 } { incr i } {
#  set sharp [expr { int(floor(rand() * 25))}]
#  puts $sharp
#}

while {[gets stdin line] >= 0} {
    switch $line {
      "ready?" {
        puts stdout "ready!"
        flush stdout
      }
      "generate code" {        
        set sharp [expr { int(floor(rand() * 25))}]
        for { set i 0 } { $i <= 24 } { incr i } {
          if {$i == $sharp} { set chr {#}
          } else { set chr {.} }
          puts -nonewline stdout $chr
          if {[expr ($i + 1) % 5] == 0} { puts stdout "" }
          flush stdout
        }
      }
      "find code" {
        set code ""
        for { set i 0 } { $i < 5 } { incr i } {
          gets stdin line
          set code $code$line
        }
        set sharp [string first {#} $code]        
        set y [expr int($sharp / 5)]
        set x [expr $sharp - ($y * 5)]
        puts stdout "$x $y"
        flush stdout
      }
      "bye!" {
        puts stdout "bye!"
        flush stdout
        exit 0
      }
    }
}