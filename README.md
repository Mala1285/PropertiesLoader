Запускаемый файл проекта out\PropertiesLoader.jar<br>
Инструкция по запуску из командной строки: <br>
В качестве аргумента jar-файлу необходимо передать файл со значениями полей класса, например:<br>
java -jar PropertiesLoader.jar Company.properties<br>
Программа выводит прочитанные поля из файла *.properties. Если поле не задано или некорректно задано (и при этом не имеет значения по умолчанию), то выводится null.
