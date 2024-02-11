FROM ubuntu
RUN apt-get update -y
COPY home.html /var/www/html/
CMD ["/usr/sbin/apachectl", "-D", "FOREGROUND"]
