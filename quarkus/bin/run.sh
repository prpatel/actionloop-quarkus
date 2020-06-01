#!/bin/sh
# arg0 <main-class> <source-dir> <target-dir>
cd $3
#BACKUPDIR=$(ls -td /proxy/action/*/ | head -1)

#
# exec java -classpath $2:/app/lib/* org.openwhisk.Main
#exec java -classpath /app/application.jar:/app/lib/* org.openwhisk.Main 3>/tmp/out.file out.file>&3
exec java -classpath /app/application.jar:/app/lib/* org.openwhisk.Main
#java --add-opens java.base/java.util=ALL-UNNAMED --illegal-access=permit -Dfile.encoding=UTF-8 -cp "%s" Launcher "%s" "$@"
#exec java -classpath /app/exec.jar:/app/lib/* io.quarkus.runner.GeneratedMain
