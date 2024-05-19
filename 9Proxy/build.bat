@echo off
REM Компилируем все Java файлы из src и помещаем .class файлы в bin
javac -d bin src/*.java

REM Запуск программы
java -cp bin src.DoorAccessControl
