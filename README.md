# Unique Tracking Number Generation with Quarkus and Custom Snowflake Algorithm

This project demonstrates a scalable and fault-tolerant unique tracking number generator using Quarkus and a custom implementation of the Snowflake algorithm.

## Introduction

The goal is to generate unique tracking numbers that can handle high concurrency and scale horizontally. The Snowflake algorithm is used to ensure uniqueness and efficiency.

## Why Quarkus?

- **Performance**: Optimized for fast startup times and low memory usage.
- **Developer Productivity**: Live coding and developer-friendly features.
- **Fault Tolerance**: Built-in support for fault tolerance.

## Implementation Details

### Performance

- **Optimized Startup**: Quarkus is designed to have a very fast startup time, which is particularly beneficial for cloud-native environments where applications may be started and stopped frequently.
- **Low Memory Usage**: The application uses Quarkus, which optimizes memory usage and is lightweight compared to traditional Java frameworks. This helps in reducing operational costs and improving efficiency.

### Developer Productivity

- **Live Coding**: The project setup includes Quarkus' live coding feature, allowing developers to see the effects of their code changes instantly without needing to restart the server.
- **Simplified Development**: Quarkus provides a simplified and unified configuration, making it easier for developers to focus on business logic rather than boilerplate code.

### Fault Tolerance

- **Retry Mechanism**: Implemented fault tolerance using the `@Retry` annotation from MicroProfile Fault Tolerance. This ensures that if the generation of the tracking number fails due to transient issues, it will automatically retry up to three times with a specified delay and jitter.
- **Concurrency Control**: Used `ReentrantLock` to handle concurrency and ensure that tracking number generation is thread-safe, preventing race conditions.

## Project Setup

### Prerequisites

- Java 11
- Maven 3.6.3+