# Airline Management System 🛩️

A comprehensive JavaFX desktop application for managing airline operations including flight management, passenger registration, ticket booking, and payment processing. The system features a French-language interface and provides complete CRUD operations for all airline management needs.

![Java](https://img.shields.io/badge/Java-21.0.2-orange)
![JavaFX](https://img.shields.io/badge/JavaFX-19-blue)
![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue)
![License](https://img.shields.io/badge/License-MIT-green)

## 📋 Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [System Architecture](#system-architecture)
- [Database Schema](#database-schema)
- [Installation](#installation)
- [Usage](#usage)
- [Screenshots](#screenshots)
- [Project Structure](#project-structure)
- [Configuration](#configuration)
- [Contributing](#contributing)
- [License](#license)

## ✨ Features

### 🔐 Authentication System
- Secure login with MySQL database validation
- Admin authentication against `admin` table
- Session management and security controls

### ✈️ Flight Management
- **Add Flights** (`AjouteVol`): Create new flights with complete details
  - Flight ID, name, source, and destination
  - Departure and arrival times
  - Available seats and fare pricing
  - Departure date scheduling
- **Modify Flights** (`ModifierVol`): Update and delete existing flights
  - Search functionality for flight management
  - Real-time flight information updates
  - Seat capacity management

### 👥 Passenger Management
- **Register Passengers** (`AjtVoyageur`): Comprehensive passenger registration
  - Passport ID validation
  - Personal details (name, age, contact information)
  - Address and contact management
- **Update Passengers** (`UpdateVoyageur`): Modify passenger records
  - Edit personal information
  - Update contact details
  - Delete passenger records

### 🎫 Ticket Reservation System
- **Book Tickets** (`ReservationTicket`): Complete booking workflow
  - Flight selection from available options
  - Passenger assignment to flights
  - Payment processing (cash/card)
  - Automatic seat allocation
  - Reservation confirmation
- **Ticket Details** (`TicketDetails`): Retrieve and display ticket information
  - Search by ticket number
  - Complete booking information display
  - Passenger and flight details

### 📊 Reporting & Analytics
- **Flight Listings by Date** (`ListeVol_dateDepart`): Filter flights by departure date
- **Flight Search** (`ListeVol_Source_Dep`): Search flights by source and destination
- **Ticket Reports** (`ListeTicket_dateDepart`): Generate ticket reports with date filtering
- Real-time data visualization

### 💳 Payment Processing
- Multiple payment methods support (cash/card)
- Secure payment validation
- Transaction history tracking
- Payment confirmation system

## 🛠️ Technology Stack

### Core Technologies
- **Java 21.0.2**: Primary programming language
- **JavaFX 19**: Desktop application framework
- **FXML**: User interface markup
- **CSS**: Custom styling and themes

### Database
- **MySQL 8.0+**: Primary database management system
- **JDBC**: Database connectivity
- **PreparedStatement**: SQL injection prevention

### Development Tools
- **Eclipse IDE**: Recommended development environment
- **Scene Builder**: FXML visual editor
- **JavaFX Maven Plugin**: Build and deployment

## 🏗️ System Architecture

The application follows the **Model-View-Controller (MVC)** pattern:

### Model Layer
- `Vol.java`: Flight data model
- `TicketListe.java`: Ticket display model
- Database entity models

### View Layer (FXML)
- **Login.fxml**: Authentication interface
- **Home.fxml**: Main dashboard
- **AjouteVol.fxml**: Flight creation form
- **ModifierVol.fxml**: Flight management interface
- **AjtVoyageur.fxml**: Passenger registration
- **UpdateVoyageur.fxml**: Passenger management
- **ReservationTicket.fxml**: Ticket booking interface
- **TicketDetails.fxml**: Ticket information display
- **ListeVol_dateDepart.fxml**: Flight listings by date
- **ListeVol_Source_Dep.fxml**: Flight search interface
- **ListeTicket_dateDepart.fxml**: Ticket reports

### Controller Layer
- **LoginController.java**: Authentication logic
- **HomeController.java**: Dashboard navigation
- **AjouteVolController.java**: Flight creation
- **ModifierVolController.java**: Flight management
- **AjtVoyageurController.java**: Passenger registration
- **UpdateVoyageurController.java**: Passenger management
- **ReservationTicketController.java**: Booking system
- **TicketDetailsController.java**: Ticket display
- **ListeVol_dateDepartController.java**: Flight reports
- **ListeVol_Source_DepController.java**: Flight search
- **ListeTicket_dateDepartController.java**: Ticket analytics

## 🗄️ Database Schema

### Database: `airliners`
**Connection Details:**
- Host: `localhost`
- Database: `airliners`
- User: `root`
- Password: (empty)

### Core Tables

#### `admin` - Administrator Authentication
```sql
CREATE TABLE admin (
    UserName VARCHAR(50) PRIMARY KEY,
    Password VARCHAR(255) NOT NULL
);
```

#### `vol` - Flight Information
```sql
CREATE TABLE vol (
    vol_ID VARCHAR(20) PRIMARY KEY,
    vol_nom VARCHAR(100) NOT NULL,
    source VARCHAR(100) NOT NULL,
    destination VARCHAR(100) NOT NULL,
    heure_depart TIME NOT NULL,
    heure_arrivee TIME NOT NULL,
    nbr_place INT NOT NULL,
    vol_frais DECIMAL(10,2) NOT NULL,
    date_depart DATE NOT NULL
);
```

#### `passager` - Passenger Information
```sql
CREATE TABLE passager (
    passport_ID VARCHAR(20) PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    adresse TEXT,
    telephone VARCHAR(20),
    email VARCHAR(100)
);
```

#### `ticket` - Ticket Records
```sql
CREATE TABLE ticket (
    ticket_num VARCHAR(20) PRIMARY KEY,
    passport_ID VARCHAR(20),
    vol_ID VARCHAR(20),
    date_reservation DATE NOT NULL,
    payment_method ENUM('cash', 'card'),
    total_amount DECIMAL(10,2),
    FOREIGN KEY (passport_ID) REFERENCES passager(passport_ID),
    FOREIGN KEY (vol_ID) REFERENCES vol(vol_ID)
);
```

#### `paiement` - Payment Details
```sql
CREATE TABLE paiement (
    payment_ID INT AUTO_INCREMENT PRIMARY KEY,
    ticket_num VARCHAR(20),
    card_number VARCHAR(20),
    card_holder VARCHAR(100),
    amount DECIMAL(10,2),
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ticket_num) REFERENCES ticket(ticket_num)
);
```

## 📥 Installation

### Prerequisites
- **Java 21.0.2 or higher**
- **JavaFX 19 or higher**
- **MySQL 8.0 or higher**
- **Eclipse IDE** (recommended)

### Database Setup
1. Install MySQL Server
2. Create database:
```sql
CREATE DATABASE airliners;
USE airliners;
```

3. Import the database schema (create tables as shown above)

4. Create default admin user:
```sql
INSERT INTO admin (UserName, Password) VALUES ('admin', 'admin123');
```

### Application Setup
1. Clone the repository:
```bash
git clone https://github.com/souilimaa/Airline-management-System.git
cd Airline-management-System
```

2. Open project in Eclipse IDE

3. Configure JavaFX:
   - Add JavaFX library to project build path
   - Set VM arguments: `--module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml`

4. Update database connection:
   - Modify connection parameters in controller classes if needed
   - Default: `localhost:3306/airliners` with `root` user

5. Build and run:
   - Right-click project → Run As → Java Application
   - Select `Main` class as entry point

## 🚀 Usage

### Getting Started
1. **Login**: Start with admin credentials
2. **Dashboard**: Navigate through main menu options
3. **Flight Management**: Add, modify, or search flights
4. **Passenger Registration**: Register new passengers
5. **Booking**: Create ticket reservations
6. **Reports**: Generate flight and ticket reports

### User Workflows

#### Adding a New Flight
1. Navigate to "Ajouter Vol" (Add Flight)
2. Fill in flight details:
   - Flight ID and name
   - Source and destination airports
   - Departure and arrival times
   - Number of seats available
   - Flight fare
   - Departure date
3. Save flight information

#### Booking a Ticket
1. Go to "Réservation Ticket" (Ticket Reservation)
2. Select passenger (register if new)
3. Choose available flight
4. Select payment method (cash/card)
5. Process payment
6. Generate ticket confirmation

#### Managing Passengers
1. Use "Ajouter Voyageur" (Add Passenger) for new registrations
2. Use "Modifier Voyageur" (Update Passenger) for modifications
3. Provide complete passenger information including passport details



### Login Interface
- User authentication screen
- Clean, professional design
- Security validation

### Main Dashboard
- Central navigation hub
- Module access buttons
- System overview

### Flight Management
- Add/edit flight forms
- Flight search and filtering
- Real-time availability

### Passenger Management
- Registration forms
- Passenger search
- Contact management

### Ticket Booking
- Interactive booking process
- Payment processing
- Confirmation generation

### Reports & Analytics
- Flight listings
- Ticket reports
- Date-based filtering

## 📁 Project Structure

```
Airline-management-System-main/
├── src/
│   ├── application/
│   │   ├── Main.java                          # Application entry point
│   │   ├── LoginController.java               # Authentication logic
│   │   ├── HomeController.java                # Dashboard controller
│   │   ├── AjouteVolController.java           # Flight creation
│   │   ├── ModifierVolController.java         # Flight management
│   │   ├── AjtVoyageurController.java         # Passenger registration
│   │   ├── UpdateVoyageurController.java      # Passenger management
│   │   ├── ReservationTicketController.java   # Ticket booking
│   │   ├── TicketDetailsController.java       # Ticket display
│   │   ├── ListeVol_dateDepartController.java # Flight reports
│   │   ├── ListeVol_Source_DepController.java # Flight search
│   │   ├── ListeTicket_dateDepartController.java # Ticket reports
│   │   ├── Vol.java                          # Flight model
│   │   ├── TicketListe.java                  # Ticket model
│   │   ├── Login.fxml                        # Login UI
│   │   ├── Home.fxml                         # Dashboard UI
│   │   ├── AjouteVol.fxml                    # Add flight UI
│   │   ├── ModifierVol.fxml                  # Modify flight UI
│   │   ├── AjtVoyageur.fxml                  # Add passenger UI
│   │   ├── UpdateVoyageur.fxml               # Update passenger UI
│   │   ├── ReservationTicket.fxml            # Ticket booking UI
│   │   ├── TicketDetails.fxml                # Ticket details UI
│   │   ├── ListeVol_dateDepart.fxml          # Flight list UI
│   │   ├── ListeVol_Source_Dep.fxml          # Flight search UI
│   │   ├── ListeTicket_dateDepart.fxml       # Ticket list UI
│   │   └── application.css                   # Custom styles
│   └── module-info.java                      # Module configuration
├── bin/                                       # Compiled classes
├── build.fxbuild                             # JavaFX build config
└── README.md                                 # This file
```

## ⚙️ Configuration

### Database Configuration
Update database connection parameters in controller classes:

```java
// Database connection settings
private static final String DB_URL = "jdbc:mysql://localhost:3306/airliners";
private static final String DB_USER = "root";
private static final String DB_PASSWORD = "";
```

### JavaFX Configuration
Ensure JavaFX modules are properly configured in `module-info.java`:

```java
module Airline_RS {
    requires javafx.controls;
    requires java.sql;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.base;
    requires javafx.graphics;
    
    opens application to javafx.graphics, javafx.fxml, javafx.base;
}
```

### Styling Configuration
Customize appearance in `application.css`:
- Blue/green theme
- Hover effects
- Professional design elements



### Development Guidelines
- Follow JavaFX best practices
- Maintain MVC architecture
- Add proper error handling
- Include comprehensive comments
- Test database operations thoroughly


## 🐛 Troubleshooting

### Common Issues

#### JavaFX Module Not Found
**Solution**: Add JavaFX to module path
```bash
--module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml
```

#### Database Connection Failed
**Solution**: Verify MySQL service and connection parameters
- Check MySQL service status
- Verify database name and credentials
- Ensure port 3306 is accessible

#### FXML Loading Error
**Solution**: Check FXML file paths and controller references
- Verify FXML files are in correct package
- Check fx:controller attributes
- Ensure proper imports

## 📧 Contact

Project Link: [https://github.com/souilimaa/Airline-management-System](https://github.com/souilimaa/Airline-management-System)

---

**Note**: This is a educational project demonstrating JavaFX desktop application development with database integration. The system provides a comprehensive foundation for airline management operations with room for enhancement and customization.
