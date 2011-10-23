from random import randint
import sys

while 1:
  l = sys.stdin.readline().strip()
  if l == "ready?":
    print "ready!"
    sys.stdout.flush()