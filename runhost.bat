rem if not exist "build\jar\Host.jar" (
  call ant compile jar 
rem )

java -jar build\jar\Host.jar "perl bots/perl/perlbot.pl" "perl bots/perl/perlbot.pl"
