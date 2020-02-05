#!/bin/sh


if [[ -z $1 ]]; then
    echo "Err: manca file sorgente operazione"
    exit 0;
fi

#name=$1
#name="${name//./_}"
#name='op_'$name



#cat /Users/Ezio/Documents/programmi/JShellScript/pre-op.txt $1 /Users/Ezio/Documents/programmi/JShellScript/post-op.txt > $name'.java'
cat /Users/Ezio/Documents/programmi/JShellScript/pre-op.txt $1 /Users/Ezio/Documents/programmi/JShellScript/post-op.txt > 'JssOp.java'

javac -cp /Users/Ezio/Documents/programmi/JShellScript/jss.jar:. JssOp.java

rm -f JssOp.java

#echo $1
#echo $PWD
#java -cp /Users/Ezio/Documents/programmi/JShellScript/jss.jar:. jshellscript.JShellScript $1
