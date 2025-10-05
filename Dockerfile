FROM maven:3.9.11-ibm-semeru-21-noble

WORKDIR /app

COPY . .

RUN apt-get update && apt-get install -y wget

RUN wget -O /usr/local/bin/tailwindcss \
	https://github.com/tailwindlabs/tailwindcss/releases/download/v4.1.13/tailwindcss-linux-arm64 \
	&& chmod +x /usr/local/bin/tailwindcss

CMD [ "mvn", "spring-boot:run" ]
