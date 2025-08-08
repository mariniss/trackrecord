# TrackRecord - Portfolio Tracking Application

## Overview

TrackRecord is a comprehensive portfolio management and tracking system built with Kotlin and Spring Boot. It allows users to manage investment portfolios, track securities, record transactions, maintain trading journals, and analyze investment performance.

## Technology Stack

- **Backend**: Kotlin 2.0.0 with Spring Boot 3.3.1
- **Java Version**: JDK 22
- **Database**: H2 Database (embedded). Will move to a production one soon
- **ORM**: Spring Data JPA
- **Frontend**: Thymeleaf templates. Will move to Angualr soon
- **Build Tool**: Gradle (Kotlin DSL)

## Key Features

- **Portfolio Management**: Create and manage multiple investment portfolios with different currencies
- **Security Tracking**: Monitor stocks, bonds, and other securities with current pricing
- **Position Tracking**: Keep track of investment positions across portfolios
- **Cash Flow Management**: Record deposits, withdrawals, and other cash movements
- **Trading Journal**: Maintain detailed journal entries with associated actions
- **Action Recording**: Log BUY, SELL, and other transaction types linked to securities

## Project Structure

```
src/
├── main/
│   ├── kotlin/
│   │   └── org/fm/trackrecord/
│   │       ├── api/          # REST API endpoints
│   │       ├── controller/   # MVC controllers for web interface
│   │       ├── dao/          # Data Access Objects (JPA repositories)
│   │       ├── entity/       # Domain model entities
│   │       ├── service/      # Business logic services
│   │       └── TrackrecordApplication.kt  # Application entry point
```

## Core Domain Model

### Key Entities

- **Portfolio**: Container for positions, cash flows, and journal entries
- **Security**: Investment instrument with ticker symbol, name, and pricing
- **Position**: Holdings of securities within a portfolio
- **Action**: Trading activity (BUY/SELL) linked to securities and journal entries
- **JournalEntry**: Trading journal entries with associated actions
- **CashFlow**: Money movements in and out of portfolios

## Getting Started

### Prerequisites

- JDK 22 or higher
- Gradle build tool

### Running the Application

```bash
# Clone the repository
git clone [repository-url]
cd trackrecord

# Build the application
./gradlew build

# Run the application
./gradlew bootRun
```

The application will be available at `http://localhost:8080`

### Running Tests

```bash
./gradlew test
```

## Development

The project uses standard Spring Boot conventions:

- Controllers handle HTTP requests and render views
- Services contain business logic
- DAOs (Data Access Objects) handle database operations
- Entities define the domain model and database schema

## License

See the [LICENSE](LICENSE) file for details.