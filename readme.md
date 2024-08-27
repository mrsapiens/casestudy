
# Brokage Firm

Brokage Firm is a platform where Admins can manage transactions of customers to create buy or sell orders. The application allows the enrollment of Admin and Customer users, with Admins having the ability to deposit, withdraw money, and create orders on behalf of customers. The project is Dockerized to simplify deployment and environment setup.

## Table of Contents

- [Features](#features)
- [Requirements](#requirements)
- [Setup](#setup)
- [Running the Project](#running-the-project)
- [Usage](#usage)

## Features

- The system has some predefined admin users, customer users, and assets.
- Users can log in using predefined admin credentials and can register new admin users.
- Regular users can self-register with limited authority.
- Admin users can create assets and directly assign them to existing users (for testing purposes).
- Admin users can create, delete, and list existing orders.
- Admin users can deposit and withdraw money from customer accounts.

## Requirements

Before you begin, ensure you have the following tools installed and running on your system:

- [Docker](https://www.docker.com/get-started) 

## Setup

1. **Clone the Repository:**

   Clone the repository to your local machine using the following command:

   ```bash
   git clone https://github.com/mrsapiens/casestudy.git
   ```

2. **Navigate to the Project Directory:**

   ```bash
   cd brokage
   ```

3. **Build Docker Images:**

   Use Docker Compose to build the necessary Docker images. Run the following command in the project root directory:

   ```bash
   sh ./build.sh
   ```

## Running the Project

To run the project using Docker, execute the following command:

```bash
sh ./start.sh
```

## Stopping the Project

To stop the project and remove docker images/containers, execute the following command:

```bash
sh ./stop.sh
```

The application will be accessible via the specified port (e.g., `http://localhost:8080`).

## Usage

1. **Access the Application:**

   Open your web browser and navigate to `http://localhost:8080` to access the Brokage Firm application.

2. **Database access:**

   In order to access h2-console use the following address `http://localhost:8080/h2-console` 

3. **Postman Request:**

	You can use the exported postman collection to send API requests
