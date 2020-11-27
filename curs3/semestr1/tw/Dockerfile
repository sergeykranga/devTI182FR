FROM php:7.4.12-apache

RUN apt-get update
RUN apt-get install net-tools
RUN docker-php-ext-install pdo pdo_mysql mysqli
RUN a2enmod rewrite

COPY apache-config/000-default.conf /etc/apache2/sites-available/000-default.conf
COPY apache-config/ports.conf /etc/apache2/ports.conf
