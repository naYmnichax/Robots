# Robots #
Проект по изучению концепций OO-дизайна и разработке приложений MDI на Java
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Task0 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
1. Научиться собирать проект с использованием Apache Maven.
2. Провести рефакторинг и исправить баг (см. комментарии в коде).

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Task1 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
1. Добавить окно с подтверждением на закрытие любого из окон.
2. Добавить пункт меню "Выйти" (с обработкой аналогичной кнопке закрытия приложения).
3. Исправить баг убегания робота за границу окна.
4. Добавить локализацию приложения (пункты меню, кнопки и т.д.).

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Task2 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
1. В методе выхода из приложения сохранять в файл состояние окон приложения (положение на экране, развёрнутое/свёрнутое состояние).
2. При запуске приложения следует восстанавливать состояние окон из сохранённого на предыдущем шаге файла).
3. При запуске приложения необходимо выводить предупреждение о наличии сохранённого состояния и предложение его восстановить (Да/Нет).
4. Выбранная локализация должна сохраняться (в частности, влиять на содержимое окошко с предупреждением о восстановлении из п.3).

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Task3 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
1. Добавить управляемого робота, которого будет преследовать изначальный робот. При набегании на робота его размер уменьшается, 
изначальный робот приостанавливается, чтобы дать время уйти управляемому роботу. 
2. Робот управляется клавишами (WASD).
3. Добавить еду, для роста робота. Спавнится автоматически