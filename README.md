# legacy
Есть некоторая устаревшая, но используемая система legacy-app, содержащая большое количество разных данных, постепенно изменяющихся,
которые нельзя получить сразу одним запросом или перетащить все данные из таблицы через Kafka Connect. 
Хочется, чтобы данные все таки могли использоваться сторонним сервисом, для чего сделать:
1) уведомить о факте изменения данных в таблице, факт события с айдишником будет отправляться в кафку, а данные по id можно получить через публичное REST API
2) через это же REST API можно получить все данные таблицы постранично

Описание legacy-app:
Реализовать REST API для постраничного поиска по таблице с фильтрацией 
Реализовать REST API поиска по Id
Выгружать событие в топик кафки при изменении данных

Техстек:
Java 17, Spring Boot 3, PostgreSQL, Kafka
