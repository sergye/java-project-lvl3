setup:
	./gradlew wrapper --gradle-version 7.0

clean:
	./gradlew clean

install:
	./gradlew clean install

test:
	./gradlew test

run:
	./build/install/app/bin/app

check-updates:
	./gradlew dependencyUpdates

lint:
	./gradlew checkstyleMain

build:
	./gradlew clean build

.PHONY: build