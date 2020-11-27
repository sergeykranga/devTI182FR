run:
	docker-compose up --build --force-recreate

ssh-apache:
	docker exec -ti `docker ps | grep tw_apache | awk '{print $$1}'` bash

clear:
	docker rm $$(docker ps -a -q) -f