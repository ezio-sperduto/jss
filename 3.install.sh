#!/bin/sh

# istall app JShellScript


echo 'Istallazione JShellScript (JSS)...'

NOME_DIR=jss
NOME_BIN_LANCIO=jshellscript.sh
NOME_COMANDO=jss

mkdir $APP_CUSTOM/$NOME_DIR

cp dist/*.* $APP_CUSTOM/$NOME_DIR

ln -s $APP_CUSTOM/$NOME_DIR/$NOME_BIN_LANCIO $APP_CUSTOM/bin/$NOME_COMANDO


echo 'Istallato tutto correttamente.'