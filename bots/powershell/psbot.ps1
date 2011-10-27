while (1)
{
	$line = $Host.UI.ReadLine()
	switch($line)
	{
		"ready?" { echo "ready!"; [Console]::Out.Flush(); break; }
		"generate code"
		{ 
			$rand = New-Object system.random
			$codestring = "..........................".ToCharArray()
			$codestring[$rand.next(0, 24)] = '#'
			for ($i = 0; $i -lt 25; $i += 5)
			{
				echo ([string]::Join("", $codestring[$i..($i+5)]))
				[Console]::Out.Flush()
			}
		}
		"find code"
		{
			$code = ""
			for ($i = 0; $i -lt 5; $i++) { $code += $Host.UI.ReadLine() }
			$sharp = $code.IndexOf("#")
			$y = [int]($sharp / 5)
			$x = $sharp - ($y * 5)
			echo "${x} ${y}"
			[Console]::Out.Flush();
		}
		"bye" { echo "bye!"; [Console]::Out.Flush(); break; }
	}
}