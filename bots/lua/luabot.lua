math.randomseed( os.time() )
line = io.read("*line")

while line do
	if line == "ready?" then
		print ("ready!")
		io.flush()
	elseif line == "generate code" then
		sharp = math.random(0, 24)
		for i=0,24 do
			chr = sharp == i and  "#" or "."
			io.write (chr)
			if (i+1) % 5 == 0 then
				print ()
			end
			io.flush()
		end
	elseif line == "find code" then
		code = ""
		for i=0,4 do
			code = code .. io.read("*line")
		end
		sharp, _ = string.find(code, "#")
		sharp = sharp - 1
		y = math.floor(sharp / 5)
		x = sharp - (y * 5)
		print (x .. " " .. y)
		io.flush()
	elseif line == "bye!" then
		print ("bye!")
		io.flush()
		os.exit()
	end

	line = io.read("*line")
end
