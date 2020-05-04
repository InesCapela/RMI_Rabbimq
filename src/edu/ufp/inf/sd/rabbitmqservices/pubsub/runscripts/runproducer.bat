@REM ************************************************************************************
@REM Description: run 
@REM Author: Rui S. Moreira
@REM Date: 10/04/2018
@REM ************************************************************************************
@REM Script usage: runsetup <role> (where role should be: producer / consumer)
call setenv producer

@echo  %ABSPATH2CLASSES%
cd %ABSPATH2CLASSES%

%JDK%\bin\java -cp %CLASSPATH% %JAVAPACKAGEROLE%.%PRODUCER_CLASS_PREFIX% %BROKER_HOST% %BROKER_PORT% %BROKER_QUEUE%

cd %ABSPATH2SRC%/%JAVASCRIPTSPATH%