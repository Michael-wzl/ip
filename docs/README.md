# Dude User Guide

**Dude** is a command-line task management chatbot that helps you keep track of your todos, deadlines, and events. It is simple, fast, and saves your tasks automatically to disk.

---

## Quick Start

1. Ensure you have Java 17 or above installed.
2. Download the latest `dude.jar` from the releases page.
3. Open a terminal, navigate to the folder containing the jar file, and run:
   ```
   java -jar dude.jar
   ```
4. Type commands and press Enter to interact with Dude.

---

## Features

> **Notes about the command format:**
> - Words in `UPPER_CASE` are parameters to be supplied by the user.
>   e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter: `todo read book`.
> - Date parameters accept the `yyyy-MM-dd` format (e.g. `2025-09-30`) and will be displayed in a friendly format (e.g. `Sep 30 2025`). Plain text dates (e.g. `Sunday`) are also accepted.

### Adding a todo: `todo`

Adds a task without any date/time.

Format: `todo DESCRIPTION`

Example:
```
todo read book
```
Expected output:
```
 Got it. I've added this task:
   [T][ ] read book
 Now you have 1 tasks in the list.
```

### Adding a deadline: `deadline`

Adds a task that needs to be done before a specific date/time.

Format: `deadline DESCRIPTION /by DATE`

Examples:
```
deadline submit report /by Sunday
deadline return book /by 2025-12-02
```
Expected output:
```
 Got it. I've added this task:
   [D][ ] submit report (by: Sunday)
 Now you have 2 tasks in the list.
```

### Adding an event: `event`

Adds a task that starts and ends at specific times.

Format: `event DESCRIPTION /from START /to END`

Examples:
```
event project meeting /from Mon 2pm /to 4pm
event conference /from 2025-10-01 /to 2025-10-03
```
Expected output:
```
 Got it. I've added this task:
   [E][ ] project meeting (from: Mon 2pm to: 4pm)
 Now you have 3 tasks in the list.
```

### Listing all tasks: `list`

Shows all tasks in the task list.

Format: `list`

Expected output:
```
 Here are the tasks in your list:
 1.[T][ ] read book
 2.[D][ ] submit report (by: Sunday)
 3.[E][ ] project meeting (from: Mon 2pm to: 4pm)
```

### Marking a task as done: `mark`

Marks the specified task as done.

Format: `mark TASK_NUMBER`

Example:
```
mark 1
```
Expected output:
```
 Nice! I've marked this task as done:
   [T][X] read book
```

### Unmarking a task: `unmark`

Marks the specified task as not done.

Format: `unmark TASK_NUMBER`

Example:
```
unmark 1
```
Expected output:
```
 OK, I've marked this task as not done yet:
   [T][ ] read book
```

### Deleting a task: `delete`

Deletes the specified task from the list.

Format: `delete TASK_NUMBER`

Example:
```
delete 2
```

### Finding tasks by keyword: `find`

Finds tasks whose descriptions contain the given keyword.

Format: `find KEYWORD`

Example:
```
find book
```
Expected output:
```
 Here are the matching tasks in your list:
 1.[T][ ] read book
```

### Exiting the program: `bye`

Exits the application.

Format: `bye`

Expected output:
```
 Bye. Hope to see you again soon!
```

---

## Saving data

Dude automatically saves your task data to a file (`data/dude.txt`) after every change. There is no need to save manually.

## Editing the data file

Task data is stored in `data/dude.txt`. Advanced users may edit this file directly, but incorrect formatting may cause data to be lost on the next load.

---

## Command Summary

| Action       | Format                                          |
|--------------|-------------------------------------------------|
| **Todo**     | `todo DESCRIPTION`                              |
| **Deadline** | `deadline DESCRIPTION /by DATE`                 |
| **Event**    | `event DESCRIPTION /from START /to END`         |
| **List**     | `list`                                          |
| **Mark**     | `mark TASK_NUMBER`                              |
| **Unmark**   | `unmark TASK_NUMBER`                            |
| **Delete**   | `delete TASK_NUMBER`                            |
| **Find**     | `find KEYWORD`                                  |
| **Exit**     | `bye`                                           |