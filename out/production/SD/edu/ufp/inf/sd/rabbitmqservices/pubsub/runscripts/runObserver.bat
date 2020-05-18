@REM ************************************************************************************
@REM Description: run 
@REM Author: Rui S. Moreira
@REM Date: 10/04/2018
@REM ************************************************************************************
@REM Script usage: runclient <role> (where role should be: producer / consumer)
call setenv consumer

@echo  %ABSPATH2CLASSES%
cd %ABSPATH2CLASSES%

%JDK%\bin\java -cp %CLASSPATH% %JAVAPACKAGEROLE%.%@set OBSERVER_CLASS_PREFIX% %BROKER_HOST% %BROKER_PORT% %BROKER_QUEUE%

cd %ABSPATH2SRC%/%JAVASCRIPTSPATH%