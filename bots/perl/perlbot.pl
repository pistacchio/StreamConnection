#!/usr/local/bin/perl

use Switch;

$|++;

while (<STDIN>)
{
  chomp;
  sleep(0.01);  
    
  switch($_)
  {
    case "ready?" { print "ready!\n"; }
    case "generate code"
    {
      $dots = ".........................";
      substr($dots, int(rand(24)), 1) = '#';
      @code = $dots =~ /(.{1,5})/g;
      foreach(@code) { print $_ . "\n" }
    }
    case "find code"
    {
      my $code;
      for ($row = 0; $row < 5; $row++)
      {
        $code = $code . <STDIN>
      }
      $code =~ s/\n//g;
      $sharp = index($code, '#');
      $y = int($sharp / 5);
      $x = $sharp - ($y * 5);
      print "$x $y\n";
    }    
    case "bye!" { print "bye!\n"; exit(0); }
  }
}