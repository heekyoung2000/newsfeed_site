## 예약구매 사이트


## Docker
docker-compose.yml
```
version: '3.1'

services:

  db:
    image: mysql
    container_name: mysql-server
    restart: always
    ports:
      - "3306:3306"
    environment:
      MYSQL_DATABASE : dbtest
```
