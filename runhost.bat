rem if not exist "build\jar\Host.jar" (
  call ant compile jar 
rem )

java -jar build\jar\Host.jar "python pybot.py" "python pybot.py"
