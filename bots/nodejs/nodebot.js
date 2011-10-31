var rl = require('readline');

var std = rl.createInterface(process.stdin, process.stdout, null);
global.readingCode = false;
global.readCode = 'asdf';
global.linesOfCodeRead = 0;

std.on('line', function(line)
{
  if (global.readingCode == true)
  {
    global.readCode += line;
    if (global.linesOfCodeRead == 4)
    {
      var sharp = global.readCode.indexOf('#');
      var y = Math.floor(sharp / 5);
      var x = sharp - (y * 5);
      console.log('%d %d', x, y);
      
      global.readingCode = false;
      global.readCode = '';
      global.linesOfCodeRead = 0;      
    }
    else
    {
      global.linesOfCodeRead++;      
    }
  }
  else
  {
    switch(line)
    {
      case 'ready?':
        console.log('ready!');
        break;
      case 'generate code':
        var sharp = Math.floor(Math.random()*25);
        for (i = 0; i < 25; i++)
        {        
          process.stdout.write(i == sharp ? '#' : '.');
          if ((i + 1) % 5 == 0) process.stdout.write('\n');        
        }
        break;
      case 'find code':
        global.readingCode = true;
        global.readCode = '';
        global.linesOfCodeRead = 0;
        break;
      case 'bye!':
        console.log('bye!');
        process.exit(code=0);
        break;
    }
  }
});