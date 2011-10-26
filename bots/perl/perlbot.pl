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
      print "$code"
    }    
    case "bye!" { print "bye!\n"; exit(0); }
  }
}