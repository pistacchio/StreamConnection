from random import randint
import sys

while 1:
  l = sys.stdin.readline().strip()
  if l == "ready?":
    print "ready!"
    sys.stdout.flush()
  if l == 'generate code':
    code_as_string = ['.' for x in range(25)]
    sharp_position = randint(0, 24)
    code_as_string[sharp_position] = '#'
    split_5 = [code_as_string[i:i+5] for i in range(0, len(code_as_string), 5)]
    for row in split_5:
      print ''.join(row)
      sys.stdout.flush()
  if l == 'find code':
    code = []
    for i in range(5):
      code.append(sys.stdin.readline().strip())
    x = 0
    y = 0

    for y, row in enumerate(code):
      x = row.find('#')
      if x != -1:
        print "%d %d" % (x, y)
        sys.stdout.flush()
        break;
  if l == 'bye!':
    print 'bye!'
    sys.stdout.flush()
    sys.exit(0)
        
    