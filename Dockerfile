FROM mysql:latest
ENV MYSQL_DATABASE="test" \
    MYSQL_USER="user" \
    MYSQL_PASSWORD="password" \
    MYSQL_ROOT_PASSWORD="root"
