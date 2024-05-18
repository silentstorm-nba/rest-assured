
# API Test Automation Project

This project contains automated tests for API endpoints using Rest Assured, TestNG, and Log4j. The project is structured to follow best practices in API test automation and includes tests for various endpoints provided by a sample API.

## Project Structure

```
apitests
├── src
│   ├── main
│   └── test
│       ├── java
│       │   └── com
│       │       └── endpoints
│       │           ├── AlbumsTest.java
│       │           ├── BaseTest.java
│       │           ├── CommentsTest.java
│       │           ├── PhotosTest.java
│       │           ├── PostsTest.java
│       │           ├── TodosTest.java
│       │           └── UsersTest.java
│       └── resources
│           └── log4j2.xml
│           └── testNG.xml
├── target
├── pom.xml
└── README.md
```

## Prerequisites

- Java Development Kit
- Apache Maven 3.6.0 or higher

## Setup

1. **Install JDK**:
   - Download and install the JDK from the [Oracle JDK download page](https://www.oracle.com/java/technologies/javase-downloads.html) or [AdoptOpenJDK](https://adoptopenjdk.net/).

2. **Set JAVA_HOME**:
   - Ensure `JAVA_HOME` is set to the JDK path and added to the `PATH` environment variable.

3. **Install Maven**:
   - Download and install Maven from the [Maven download page](https://maven.apache.org/download.cgi).

4. **Clone the Repository**:
   - Clone this repository to your local machine.

5. **Navigate to the Project Directory**:
   ```sh
   cd path/to/your/project
   ```

6. **Verify Configuration**:
   - Ensure `JAVA_HOME` and `M2_HOME` are set correctly and added to the `PATH`.

## Running Tests

1. **Open Terminal**:
   - Open a terminal or command prompt.

2. **Navigate to the Project Directory**:
   ```sh
   cd path/to/your/project
   ```

3. **Run Tests**:
   - Run the following Maven command to execute the tests:
     ```sh
     mvn clean test
     ```

4. **View Logs**:
   - Logs will be displayed in the terminal. The logging level and format are configured in the `log4j2.xml` file.

## Project Dependencies

The project uses the following dependencies:

- **Rest Assured**: For API testing.
- **TestNG**: For test management and execution.
- **Log4j**: For logging.

These dependencies are specified in the `pom.xml` file.

### `pom.xml`

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.apitests</groupId>
    <artifactId>apitests</artifactId>
    <version>1.0-SNAPSHOT</version>

    <dependencies>
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>rest-assured</artifactId>
            <version>4.4.0</version>
        </dependency>
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>7.4.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-core</artifactId>
            <version>2.14.1</version>
        </dependency>
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-api</artifactId>
            <version>2.14.1</version>
        </dependency>
    </dependencies>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
    </properties>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.0.0-M5</version>
                <configuration>
                    <suiteXmlFiles>
                        <suiteXmlFile>src/test/testNG.xml</suiteXmlFile>
                    </suiteXmlFiles>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

## Log4j Configuration

The logging configuration is specified in the `log4j2.xml` file located in `src/test/resources`.

### `log4j2.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
        </Console>
    </Appenders>
    <Loggers>
        <Root level="info">
            <AppenderRef ref="Console"/>
        </Root>
        <Logger name="com.endpoints" level="debug" additivity="false">
            <AppenderRef ref="Console"/>
        </Logger>
    </Loggers>
</Configuration>
```

## TestNG Configuration

The `testNG.xml` file specifies the test suite configuration and is located in `src/test`.

### `testNG.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="ApiTestSuite">
    <test name="ApiTests">
        <classes>
            <class name="com.endpoints.PostsTest"/>
            <class name="com.endpoints.CommentsTest"/>
            <class name="com.endpoints.AlbumsTest"/>
            <class name="com.endpoints.PhotosTest"/>
            <class name="com.endpoints.TodosTest"/>
            <class name="com.endpoints.UsersTest"/>
        </classes>
    </test>
</suite>
```

## License

This project is licensed under the MIT License. See the LICENSE file for details.

## Thanks

Thanks to [JSONPlaceholder](https://jsonplaceholder.typicode.com/) for providing the sample API used in this project.
