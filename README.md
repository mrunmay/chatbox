# ChatBox Application [![Java CI with Maven](https://github.com/mrunmay/chatbox/actions/workflows/maven.yml/badge.svg)](https://github.com/mrunmay/chatbox/actions/workflows/maven.yml)

ChatBox is a messaging application built with Spring Boot and RabbitMQ for messaging functionality.

## Features

- Register users to participate in the chat.
- Send and receive messages in real-time.
- Delete messages for a specific user.

## Technologies Used

- Java 17
- Spring Boot 3.2.3
- RabbitMQ
- Swagger (for API documentation)

## Deployment
- The deployment process is automated through a GitHub Actions workflow, which builds a runnable "Fat JAR" of the application.

## GitHub Repository
- All infrastructure and application code are hosted in a public GitHub repository. You can access the code, configuration, and workflow details at [GitHub Repository Link](https://github.com/mrunmay/chatbox).


## Getting Started

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/chatbox.git

1.Set up RabbitMQ on your local machine or use a cloud provider.

2.Configure RabbitMQ connection details in application.properties.

3.Build the application using Maven:

```
git clone https://github.com/mrunmay/chatbox.git
 ```

4.Run the application:

```
java -jar target/chatbox.jar
```

5.Access the Swagger UI for API documentation:

```
http://localhost:8080/swagger-ui/index.html
```

## API Endpoints

- POST `/api/user/register`: Register a new user.
```
curl --location 'localhost:8080/api/user/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "mrunmaya",
    "password": "mypassword",
    "email": "mrunmaya@mmail.com"
}'
```

- POST `/api/messages/send`: Send a new message.
```
curl --location 'localhost:8080/api/message/send' \
--header 'Content-Type: application/json' \
--data '{
    "sender":"john",
    "content":"how are you"
}'
```

- GET `/api/messages`: Get all messages.
```
curl --location 'localhost:8080/api/message/all'
```
- GET `/api/messages/delete/{user}`: Delete messages for a specific user.
```
curl --location 'localhost:8080/api/message/deleteAllFor/nick'
```
## Conclusion
In summary, the ChatBox application showcases effective usage of Spring Boot, Spring Data JPA, and RabbitMQ for creating a reliable and scalable chat messaging system. With features like user registration, real-time messaging, and exception handling, ChatBox demonstrates modern development practices and can serve as a foundation for building more complex chat applications.