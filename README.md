
Consumer Driven Contracts Using Pact Framework
============================================



Run Pact Broker
Start pact broker as docker containers


$ cd contract-pact-springboot



$ docker-compose up -d



Run maven build to publish the pacts to the pact broker



cd pact-contract-consumer


mvn clean install pact:publish -Dpact.broker.url=http://localhost:8500




Run maven build at the provider side



cd pact-contract-provider



mvn clean install  -Dpact.verifier.publishResults=true
(or)
mvn test -Dpact.verifier.publishResults=true
