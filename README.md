# Vehicle Manager

A modern web application built with **Java 21**, **Spring Boot**, and **Vaadin** to help you **track and manage your vehicles**, including maintenance intervals, service history, expenses, and receipts.

Built with:
- Java 21
- Spring Boot
- Vaadin (UI)
- Maven

---

## Features

- Manage multiple vehicles
- Track service history and maintenance intervals
- Log expenses and attach receipts
- Works offline, with a clean browser-based UI
- Local data persistence

---

## How to Run

### 1. Install Java 21+

Download and install the **Java 21 JDK** from one of the following sources:

- [https://jdk.java.net/21/](https://jdk.java.net/21/)
- [https://adoptium.net/temurin/releases/?version=21](https://adoptium.net/temurin/releases/?version=21)

Verify that `java` is available in your terminal:

```bash
java --version
```

### 2. Installing the Application

- Download the `vehiclemanagement-*.jar` file from `target` folder.

- Create a folder named `persistence` on your computer and copy inside the content of `persistence` folder.

- Run the application with the command:

```bash
java -Dbase.path="PATH_TO_WORKING_DIR" -jar vehiclemanagement-*.jar
```

replacing `PATH_TO_WORKING_DIR` with the full path to the folder containing the `persistence` folder you previously created. 
In this folder the application stores your vehicle's files.

![folder](/doc/img/img001.png)

![folder](/doc/img/img002.png)

### 3. Access the Application

Once the application is running, open your browser and visit [`http://localhost:9493`](http://localhost:9493) to access the application.

---


## Building the jar

To build the `jar` run:

```bash
mvn com.github.eirslett:frontend-maven-plugin:1.10.0:install-node-and-npm -DnodeVersion="v20.18.0"
mvn clean package -P production
```


## Future Improvements

Planned features for upcoming versions:

- **Import/Export vehicle data**  
  Support for exporting and importing vehicle history and service records as `.zip` files, allowing easier backup, sharing, and migration between systems.

- **Filter documents by type**
  Add the ability to filter and view attached documents based on their type (e.g., receipts, service invoices), improving navigation without changing the existing organization.

