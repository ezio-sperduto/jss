#!/bin/sh


#if [[ -z $1 ]]; then
#    echo "Err: manca operazione"
#    exit 0;
#fi

#if [[ $1 != *'.class' ]]; then
#    echo "Err: il parametro operazione deve terminare con .class"
#    exit 0;
#fi


#echo $1
#echo $PWD

#java -cp /Users/Ezio/Documents/programmi/JShellScript/jss.jar:. jshellscript.JShellScript $1

java -cp /Users/Ezio/Documents/programmi/JShellScript/jss.jar:. jshellscript.JShellScript JssOp.class
