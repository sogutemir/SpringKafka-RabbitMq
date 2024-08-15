# SpringKafka-RabbitMq

## Overview

This project demonstrates the integration of Spring Boot with RabbitMQ and Kafka for messaging. It consists of multiple microservices that communicate with each other using RabbitMQ and Kafka. The services include:

- **Notification Service**: Handles notifications and listens to messages from RabbitMQ and Kafka.
- **Order Service**: Manages orders and sends messages to RabbitMQ and Kafka.
- **Messaging Logging Service**: Logs messages received from RabbitMQ and Kafka.

## Architecture

The architecture consists of the following components:

- **RabbitMQ**: Used for message queuing.
- **Kafka**: Used for message streaming.
- **PostgreSQL**: Used as the database for storing data.
- **Docker Compose**: Used to orchestrate the services.

## Services

### Notification Service

- **RabbitMQConfig**: Configures RabbitMQ.
- **NotificationService**: Listens to `notification\_queue` and processes messages.
- **NotificationController**: Handles HTTP requests for notifications.

### Order Service

- **RabbitMQConfig**: Configures RabbitMQ.
- **OrderService**: Manages orders and sends messages to RabbitMQ and Kafka.

### Messaging Logging Service

- **RabbitMQConfig**: Configures RabbitMQ.
- **LogMessageService**: Listens to `notification\_queue` and `log\_topic` and logs messages.

## Setup

### Prerequisites

- Docker
- Docker Compose
- Java 17+
- Maven

### Running the Application

1. **Clone the repository**:
    ```sh
    git clone https://github.com/sogutemir/SpringKafka-RabbitMq.git
    cd SpringKafka-RabbitMq
    ```

2. **Build the services**:
    ```sh
    mvn clean install
    ```

3. **Start the services using Docker Compose**:
    ```sh
    docker-compose up --build
    ```

### Accessing the Services

- **Notification Service**: `http://localhost:8084`
- **Order Service**: `http://localhost:8083`
- **Messaging Logging Service**: `http://localhost:8082`
- **RabbitMQ Management Console**: `http://localhost:15672` (default username: `guest`, password: `guest`)
- **Kafka**: `localhost:9092`
- **PostgreSQL**: `localhost:5432`
- **PgAdmin**: `http://localhost:5050` (default email: `admin@admin.com`, password: `admin`)

## API Endpoints

### Notification Service

- **Create Notification**:
    - **URL**: `POST /api/notifications`
    - **Body**:
        ```json
        {
          "userId": 1,
          "message": "This is a test notification"
        }
        ```

- **Get All Notifications**:
    - **URL**: `GET /api/notifications`

- **Get Notification by ID**:
    - **URL**: `GET /api/notifications/{id}`

- **Delete Notification**:
    - **URL**: `DELETE /api/notifications/{id}`

## Configuration

### RabbitMQ Configuration

- **Queue Name**: `notification\_queue`

### Kafka Configuration

- **Topics**:
    - `log\_topic`
    - `order\_topic`

## Docker Compose Configuration

The `docker-compose.yml` file configures the services, networks, and volumes. Key services include:

- **PostgreSQL**: Database service.
- **Zookeeper**: Kafka dependency.
- **Kafka**: Message broker.
- **RabbitMQ**: Message queue.
- **Messaging Logging Service**: Logs messages.
- **Order Service**: Manages orders.
- **Notification Service**: Manages notifications.
- **PgAdmin**: Database management tool.

## Conclusion

This project demonstrates a microservices architecture using Spring Boot, RabbitMQ, and Kafka.
It showcases how to set up and configure messaging between services and provides a foundation for building scalable and resilient applications.