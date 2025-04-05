# hw1- Manual Review

The homework will be based on this project named "Expense Tracker",where users will be able to add/remove daily transaction. 

## Compile

To compile the code from terminal, use the following command:
```
cd src
javac ExpenseTrackerApp.java
java ExpenseTrackerApp
```

You should be able to view the GUI of the project upon successful compilation. 

## Java Version
This code is compiled with ```openjdk 17.0.7 2023-04-18```. Please update your JDK accordingly if you face any incompatibility issue.

## Functionality
This application helps users keep track of their expenses by:
- Allowing addition of valid transactions with an **amount** and a **category**.
- Displaying a running total of all expenses in the table.
- Providing basic **input validation** to ensure amounts and categories are valid.

In addition, this application includes the following:
- **MVC Architecture**: Separation of concerns among `Model`, `View`, and `Controller`.
- **Input Validation**: Ensures amount is within valid range and category belongs to a defined set (`food`, `travel`, `bills`, `entertainment`, `other`).
- **Timestamping**: Each transaction is tagged with the date and time it was created.