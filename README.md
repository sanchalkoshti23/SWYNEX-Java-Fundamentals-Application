# CyberShield — Cybersecurity Incident Simulator

CyberShield is a beginner-friendly, console-based Java application for tracking simulated cybersecurity incidents. It is an educational project and does **not** perform real security scans or attacks.

## Features

- Add incidents with a title, description, category, and severity.
- View all incidents or find one by ID.
- Validate required text fields, categories, and severity levels.
- Update incident status through the allowed transitions: `OPEN` → `INVESTIGATING` → `RESOLVED`.
- Delete incidents by ID.
- Generate a summary report with incident counts by severity and status.
- Store incidents in an in-memory Java collection while the application runs.

## Technologies

- Java
- Git

## Java concepts demonstrated

- Classes, objects, constructors, fields, and methods
- Packages and imports
- Encapsulation with private fields and getters/setters
- Collections using `List` and `ArrayList`
- Loops and conditional logic
- Console input with `Scanner`
- Input validation and exception handling

## Project structure

```text
CyberShield/
├── src/
│   ├── Main.java
│   ├── model/
│   │   └── SecurityIncident.java
│   ├── service/
│   │   └── IncidentService.java
│   └── util/
│       └── InputValidator.java
└── README.md
```

## Compile and run on Windows

Open a terminal in the project folder and run:

```bat
javac -d out src\Main.java src\model\SecurityIncident.java src\service\IncidentService.java src\util\InputValidator.java
java -cp out Main
```

The first command compiles the Java source files into the `out` folder. The second starts the application.

## Data storage

Incidents are stored in memory while the application is running. They are lost when the application exits; this project does not currently save data to a file or database.

## Testing performed

No automated unit tests are included in this project. The application can be compiled using the command above, and its features can be exercised through the console menu.

## Future improvements

These are possible future enhancements and are **not currently implemented**:

- Save and load incidents using file storage or a database.
- Add automated unit tests.
- Improve input prompts and report formatting.
