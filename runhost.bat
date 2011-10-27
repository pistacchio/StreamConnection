rem if not exist "build\jar\Host.jar" (
  call ant compile jar 
rem )

java -jar build\jar\Host.jar "%windir%\system32\windowspowershell\v1.0\powershell.exe psbot.ps1" "%windir%\system32\windowspowershell\v1.0\powershell.exe psbot.ps1"
