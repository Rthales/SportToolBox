FROM gradle

MAINTAINER Marcel Haas

WORKDIR /app
COPY ./ /app
ENV PASSWD=MYS3cur3Password

CMD gradle run
EXPOSE 8080