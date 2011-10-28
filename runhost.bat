rem if not exist "build\jar\Host.jar" (
  call ant compile jar 
rem )

java -jar build\jar\Host.jar "java -jar cbot.jar" "java -jar cbot.jar"
