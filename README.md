# Weather Forecast
Данное приложение предназначено для просмотра погоды. Оно использует открытое API Open Weather. Возможно определение погоды с использованием геолокации. Так же можно ввести город вручную.    
При первом запуске, приложение запрашивает разрешение на использование геолокации. Если разрешить, то происходит переход на главный экран. Если запретить, то пользователь остается на экране выбора локации. В поиске можно найти город и получить погоду по выбранному городу. Также можно выбрать “пользовать геолокацию”. (Это возможно, если пользователь разрешил использование геолокации).    
____
![Экран выбора ллокации](https://user-images.githubusercontent.com/71686887/132749309-87b26bd8-4ef6-468b-90e1-449f6df71d22.jpg)      
____
На главном экране находится подробная информация о сегодняшнем дне, а также список с кратким прогнозом на следующие пять дней. Чтобы перейти к более детальной информации, необходимо нажать на стрелочку в правой части элемента списка.       
____
![Главный экран](https://user-images.githubusercontent.com/71686887/132749451-c28a68a3-d9dd-4d9e-add8-3dde7f4dfe4b.jpg)    
____
Чтобы вернуться на главную страницу, необходимо нажать кнопку назад.     
____
![Экран деталей](https://user-images.githubusercontent.com/71686887/132749423-f30f9005-4252-4a71-a5ac-542a6fec7517.jpg)    
____
При нажатии на кнопку смены локации происходит переход на фрагмент с поиском города.    
____
### Технологии    
____
Стек    
- Kotlin
- MVVM/MVI
- Retrofit
- Coroutines/Flow
- Jetpack Navigation  
____  
В качестве архитектуры использовался шаблон MVVM с элементами MVI. Каждая “фича” (набор view для показа информации о погоде, списки) имеет собственный набор состояний, что позволяет очень удобно отслеживать ошибки и состоянием загрузки.    
Для связи с api используется retrofit. Навигация выполнена с помощью Jetpack Navigation. Для асинхронных запросов к серверу использовались Coroutines/Flow.
____
Вы можете скачать APK по [ссылке](https://drive.google.com/file/d/11fKRkaoUtyDHrBvHSlRxa2fvcv6hsPFV/view?usp=sharing)
