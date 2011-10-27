import System
import IO
import System.Random
import List
import Data.Maybe

sharpOrDot :: Int -> Int -> Char
sharpOrDot n rnd
  | n == rnd  = '#'
  | otherwise = '.'

split5 :: [[Char]] -> [Char] -> [[Char]]
split5 acc [] = acc
split5 acc l =
  split5 (acc ++ [(take 5 l)]) (drop 5 l)

read5 :: [Char] -> Int -> IO [Char]
read5 acc 0 = return acc
read5 acc n = do
  line <- getLine 
  read5 (acc ++ line) (n - 1)
  
findCode c = 
  let sharp = findIndex (== '#') c
  in case sharp of
    Just s -> let 
                y = round ((fromIntegral s) / 5)
                x = s - (y * 5)
                in (show x) ++ " " ++ (show y)
    Nothing -> "0 0"
  
generateCode :: Int -> [[Char]]
generateCode rnd =
  let codeString = [sharpOrDot i rnd  | i <- [0..24]]
  in split5 [] codeString 

putStrLnS s = do
  putStrLn s
  hFlush stdout
  
loop :: IO a
loop = do
  line <- getLine
  case line of
    "ready?" -> do
      putStrLnS "ready!"
    "generate code" -> do
      r <- (randomIO :: IO Int)
      c <- return (generateCode (abs (rem r 24)))      
      mapM_ putStrLnS c
    "find code" -> do
      c <- read5 [] 5
      putStrLnS (findCode c)
    "bye!" -> do
      putStrLnS "bye!"
      exitWith ExitSuccess
    _ -> do
      putStrLnS "bye!"
      exitWith ExitSuccess      
  loop
  

main = do
  loop