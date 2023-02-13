Compose-Bin_Info
==================================
# Task
Реализовать Android-приложение со следующими функциями:
1. Пользователь вводит BIN банковской карты и видит всю доступную информацию о нём,
загруженную с https://binlist.net/
2. История предыдущих запросов выводится списком
3. История предыдущих запросов не теряется при перезапуске приложения
4. Нажатие на URL банка, телефон банка, координаты страны отправляет пользователя в
приложение, которое может обработать эти данные (браузер, звонилка, карты)


## HomeScreen
### /features_desqription
+ Поле ввода сохраняет значение при смене состояния
+ Иконка очистки поля ввода появляется только при условии, что в нем содержится хотя бы 1 сивол
+ Для ввода доступны только цифры (буквы и символы игнорируются)
+ При вводе недостаточного количества символов (меньше 4) и нажатии "Get info" появляется тост с соответствующим сообщением
+ При вводе строки длинной более 3 символов и нажатии "Get info" значение, введенное в текстовое поле сохраняется в историю запросов и осуществляется переход на следующий экран

<img src="https://user-images.githubusercontent.com/98952360/213885262-d01d7604-cef4-461c-ac6d-a5b20dcb2c8a.jpg" width=20% height=20%> <img src="https://user-images.githubusercontent.com/98952360/213885265-f946e333-618e-4159-964a-51ba223ac4ad.jpg" width=20% height=20%>
<img src="https://user-images.githubusercontent.com/98952360/213885277-442f8145-3404-4b0e-b62e-94a9a47cc922.jpg" width=20% height=20%>

<img src="https://user-images.githubusercontent.com/98952360/213885423-c2532cfa-560c-4ecc-a77b-e205f92e3615.jpg" width=20% height=20%> <img src="https://user-images.githubusercontent.com/98952360/213885426-e47d824b-407b-48b2-905b-a30e068e94cf.jpg" width=20% height=20%>
<img src="https://user-images.githubusercontent.com/98952360/213885430-c37e2c59-c1ff-4f8b-beab-3c2e4e382ba6.jpg" width=20% height=20%>

## LoadingScreen

<img src="https://user-images.githubusercontent.com/98952360/213885521-9c937d51-0b66-4424-82f2-18f9a8560ab1.jpg" width=20% height=20%> <img src="https://user-images.githubusercontent.com/98952360/213885525-494665c7-7892-4cd4-90b1-c605fe906f5f.jpg" width=20% height=20%>

## ErrorScreen

<img src="https://user-images.githubusercontent.com/98952360/213885595-ee3c26b0-d6eb-4b63-93eb-6e00f9ae0be1.jpg" width=20% height=20%> <img src="https://user-images.githubusercontent.com/98952360/213885602-9b7eb066-1219-4902-83a2-2c1f846a3f36.jpg" width=20% height=20%>

## DetailsScreen
### /features_desqription
+ Отображает информацию по введенному BIN
+ При клике на телефон банка, сайт банка или координаты местоположения (при наличии) предложит открыть приложение способное обработать вышеупомянутые типы данных
+ При осуществлении загрузки данных отображается LoadingScreen
+ При неудачной попытке получить данные (сервер не отвечает, неверный запрос и пр.) отображается ErrorScreen с соответствующей ошибкой
+ Сохраяет информацию при смене состояния

<img src="https://user-images.githubusercontent.com/98952360/213885678-51ab1080-1db0-4f8b-830e-b388e9b2d18a.jpg" width=20% height=20%> <img src="https://user-images.githubusercontent.com/98952360/213885681-b231df4f-64dc-443e-9b4c-c7e58b005fb0.jpg" width=20% height=20%>

<img src="https://user-images.githubusercontent.com/98952360/213885685-a28c7ee6-c166-4272-b4b5-8558b5c8ac86.jpg" width=20% height=20%>

## HistoryScreen
### /features_desqription
+ Отображает список из введенных ранее запросов
+ Сохраняет информацию при смене стостояния
+ Информация не теряется при перезапуске приложения
+ Возможность удаления отдельных элементов списка
+ Возможность очистки списка целиком
+ Плавная анимация удаления элементов списка

<img src="https://user-images.githubusercontent.com/98952360/213885796-59c79d55-2f45-4edc-a7b4-eb0d03280a12.jpg" width=20% height=20%> <img src="https://user-images.githubusercontent.com/98952360/213885800-321443c7-6a10-4507-a944-5ab862cd067c.jpg" width=20% height=20%>

# Stack

+ Kotlin
+ Compose
+ MVVM
+ UI State
+ Material design
+ Navigation-compose
+ Dagger-Hilt
+ Coroutines
+ Retrofit2
+ Room
