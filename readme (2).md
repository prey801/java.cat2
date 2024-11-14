# Java Registration Form Application

## Project Description
A Java Swing-based registration form application that allows users to enter and store personal details in a MySQL database. The application features a clean GUI with form inputs and a real-time display table of all registered users.

## Features
- User-friendly graphical interface
- Input fields for ID, Name, Gender, Address, and Contact
- Gender selection using radio buttons
- Real-time data display in a table format
- MySQL database integration for data persistence
- Form validation and error handling
- Clear form functionality after successful registration

## Prerequisites
- Java Development Kit (JDK) 17 or higher
- MySQL Server 8.0 or higher
- Maven 3.6 or higher
- NetBeans IDE (recommended) or any Java IDE

## Database Configuration
1. Make sure MySQL Server is running
2. Create a new database:
```sql
CREATE DATABASE javacat2;
```
3. Create the users table:
```sql
USE javacat2;

CREATE TABLE IF NOT EXISTS users (
    id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    gender VARCHAR(10) NOT NULL,
    address TEXT,
    contact VARCHAR(20)
);
```
4. Configure MySQL user:
```sql
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'root';
GRANT ALL PRIVILEGES ON javacat2.* TO 'admin'@'localhost';
FLUSH PRIVILEGES;
```

## Project Structure
```
cat2java/
├── pom.xml
├── README.md
└── src/
    └── main/
        └── java/
            └── com/
                └── mycompany/
                    └── cat2java/
                        ├── Cat2java.java
                        ├── RegistrationForm.java
                        └── connectiondb.java
```

## Building and Running
1. Clone the repository:
```bash
git clone <repository-url>
cd cat2java
```

2. Build the project using Maven:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn exec:java -Dexec.mainClass="com.mycompany.cat2java.Cat2java"
```

Or run directly from your IDE by executing the `Cat2java` class.

## Usage
1. Launch the application
2. Fill in the registration form:
   - Enter ID (required)
   - Enter Name (required)
   - Select Gender (Male/Female)
   - Enter Address
   - Enter Contact information
3. Click "Register" to save the data
4. The table on the right will update automatically to show all registered users
5. Click "Exit" to close the application

## Database Connection
The application connects to MySQL using the following default settings:
- URL: `jdbc:mysql://localhost:3306/javacat2`
- Username: `admin`
- Password: `root`

To modify these settings, update the connection parameters in the `connectToDatabase()` method in `RegistrationForm.java`.

## Dependencies
- MySQL Connector/J
- Java Swing (included in JDK)
- Java AWT (included in JDK)

## Common Issues and Troubleshooting
1. Database Connection Errors:
   - Verify MySQL server is running
   - Check database credentials
   - Ensure database and table exist
   
2. Compilation Errors:
   - Verify JDK installation
   - Check Maven configuration
   - Ensure all dependencies are properly installed

3. Runtime Errors:
   - Check database connection settings
   - Verify file permissions
   - Ensure all required fields are filled before registration

## Contributing
1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request

## License
This project is licensed under the MIT License - see the LICENSE file for details.

## Author
- Created by: [Your Name]
- Student ID: [Your ID]
- Course: CAT 2 Java
