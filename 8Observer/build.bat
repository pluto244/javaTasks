@echo off
REM Создание директории bin, если она не существует
if not exist bin (
    mkdir bin
)

REM Компиляция всех .java файлов в src и размещение .class файлов в bin
javac -d bin src\observer\*.java src\chat\*.java src\gui\*.java src\ChatDemo.java

REM Запуск программы из bin
java -cp bin gui.ChatGUI
