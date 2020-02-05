#!/bin/sh

# compila
javac jshellscript.JShellScript.java || { echo '-- COMPILAZIONE FALLITA.' ; exit 1; }


# builda jar
cat Main-Class: jshellscript.JShellScript> manifest.mf

jar cmf manifest.mf jss.jar jshellscript/JShellScript.class jshellscript/Operation.class


rm -f manifest.mf