trim() { echo $1; }

while true; do
  read LINE
  case $LINE in
    "ready?")
      echo "ready!";;
    "generate code")
      SHARP=`expr $RANDOM % 25`
      for I in {0..24}; do
        C="."
        if [ $I = $SHARP ]; then
          C="#"
        fi
        echo -n $C
        if [ `expr $I % 5` = 4 ]; then
          echo ""
        fi
      done;;
    "find code")
      CODE=""
      for I in {0..4}; do
        read L
        CODE=$CODE$L
      done
      SHARP=`expr index "$CODE" "#" - 1`
      Y=`expr $SHARP / 5`
      X=`expr $SHARP - $Y \* 5`
      echo "$X $Y";;
    "bye!")
      echo "bye!"
      exit;;
  esac
done