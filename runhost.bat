rem if not exist "build\jar\Host.jar" (
  call ant compile jar 
rem )

java -jar build\jar\Host.jar "C:\Tcl\bin\tclsh.exe tclbot.tcl" "C:\Tcl\bin\tclsh.exe tclbot.tcl"
