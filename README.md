# Движок форума/доски объявлений (только backend).

PS: ✅ - означает, что функционал для этого пункта реализован

# Суть задачи:

✅Есть топики (темы), в каждом топике может быть одно или более сообщений.

✅Движок должен обеспечивать хранение в БД (Postgres) и CRUD операции с топиками (темами) и сообщениями в топиках.

✅Топик должен содержать заголовок (название темы). Топик не может быть пустым, т.е. должен содержать как минимум одно сообщение.

✅Сообщение должно содержать имя (ник) автора, текст сообщения, дату создания.

✅Сообщение обязательно должно относиться к одному из топиков.

# Необходимо реализовать клиентский REST-API позволяющий пользователю:

· ✅ получать список топиков

· ✅ получать сообщения в указанном топике

· ✅ создать топик (с первым сообщением в нем)

· ✅ создать сообщение в указанном топике

· ✅ отредактировать свое сообщение

· ✅ удалить свое сообщение

✅Реализовать аутентификацию пользователей (JWT + Resfresh Tokens)

✅Реализовать REST-API администратора. Администратор может редактировать и удалять любые сообщения и топики.

Язык – Kotlin(Spring Boot)

Автоматизация сборки – Gradle

Хранилище – Postgres

# КАК ЭТО РАБОТАЕТ:

Регистрация пользователя, аутентифицкация пользователя. Пользователю выдается Bearer токен, по которому происходит доступ к ресурам

Если пользователь имеет роль user, то ему доступен только клиентский API

Если пользователь имеет роль admin, то ему доступен клиентский API и API для админов

Если пользователь не аутентифицирован, то он не имеет доступа к ресурсам

# Остальное

Настройки аутентификации с JWT описаны: https://codersee.com/spring-boot-3-spring-security-6-with-kotlin-jwt/
