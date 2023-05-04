# Project Title
Short description of the project.

### Prerequisites
* Java 11 or higher
* MySQL server
* Maven

### Installation
1. Clone the repository to your local machine.
2. Open the project in your preferred IDE (e.g. IntelliJ IDEA, Eclipse, VS Code).
3. Configure the database connection in the application.properties file.
4. Run the project using Maven.

### Usage
The API provides the following endpoints:
* `GET /tasks`: Get a list of all tasks.
* `POST /tasks`: Create a new task.
* `GET /tasks/{id}`: Get a single task by ID.
* `PUT /tasks/{id}`: Update a task by ID.
* `DELETE /tasks/{id}`: Delete a task by ID.

Each task object has the following properties:
* `id`: Long (a unique identifier for the task).
* `title`: String (title of the task).
* `description`: String (description of the task).
* `completed`: Boolean (flag indicating whether the task has been completed).

### Examples