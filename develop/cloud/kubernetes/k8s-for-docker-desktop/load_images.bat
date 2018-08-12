@echo off

set file=images.properties

for /f "tokens=1,2 delims==" %%i in (%file%) do (
rem echo %%i %%j
docker pull %%j
docker tag %%j %%i
docker rmi %%j
)
pause