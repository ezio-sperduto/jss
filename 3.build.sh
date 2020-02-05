#!/bin/sh

# compila
javac jshellscript/JShellScript.java || { echo '-- COMPILAZIONE FALLITA.' ; exit 1; }


# builda jar
echo 'Main-Class: jshellscript.JShellScript'> manifest.mf

jar cmf manifest.mf jss.jar jshellscript/JShellScript.class jshellscript/Operation.class utilita/U.class resources

rm -f dist/jss.jar

mv jss.jar dist/jss.jar

rm -f manifest.mf