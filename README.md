# Getting Started

Order-Service implemented in hexagonal architecture with SpringBoot. Added simple Swagger API documentation. 
Service can be run in two profiles:
* `local`
* `docker`

Profile local uses embedded mongoDB.

To build and start service in docker execute bash script: 
```
./docker-run/sh
```

Swagger REST API documentation can be found in
`http://localhost:8080/swagger-ui.html`

### Considerations
* For keeping safety service could be protected with TLS or even with mTLS (depending how much we want to restricted access) TLS encryption could help protect service from attacks such as data breaches (eg. man-in-the-middle attack), and DDoS attacks. It could be protected also with WAF (Web Application Firewall) that helps protect web applications by filtering and monitoring HTTP traffic between a web application and the Internet.
* Authentication could be resolved with OAuth2. It would be good for identifying users and giving permissions (eg. different for modifying products and submitting orders). If we want even more secure, we could protect requests additionally with JWT tokens. 
* To make service redundant we can create kubernetes cluster and deploy service with more than one replicas. If we want more redundancy we could create second cluster in another region. Secondly we have to provide DB redundancy so we could create DB replicas or even geo-replicas.
