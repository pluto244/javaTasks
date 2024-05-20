@echo off
REM Компилируем все Java файлы из src и помещаем .class файлы в bin
javac -d bin src/shapes/*.java src/shapes/decorators/*.java src/ShapeDemo.java


REM Запуск программы
java -cp bin src.ShapeDemo
