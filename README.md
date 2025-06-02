# Server Status Checker

Spring Boot приложение для проверки статуса серверов и управления группами серверов.

- Проверка статуса любого URL через REST API
- CRUD для серверов и групп (PostgreSQL, JPA, @OneToMany)

## Запуск

1. Укажите параметры БД в `src/main/resources/application.properties`
2. Соберите и запустите:
3. Примеры запросов:
- Проверка: `GET /checks/status?url=https://google.com`
- CRUD группы: `GET /groups`

---

_Лабораторная работа №2_

![alt text](<Screenshot 2025-06-01 112704.png>)