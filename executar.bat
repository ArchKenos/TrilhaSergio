@echo off
chcp 65001 > nul
echo ====================================================
echo   Compilando Sistema de Eventos de Porto Alegre...
echo ====================================================

if not exist bin mkdir bin

javac -d bin -sourcepath src src/model/*.java src/view/*.java src/controller/*.java src/Main.java

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo [ERRO] Falha na compilação do projeto Java!
    pause
    exit /b %ERRORLEVEL%
)

echo Compilação concluída com sucesso.
echo.
echo ====================================================
echo   Iniciando a aplicação...
echo ====================================================
echo.

java -cp bin Main

pause
