# Library-Management-System

A library management system is a software application that helps librarians and library staff to manage and organize the operations and resources of a library. It assists in tasks such as cataloging,
borrowing and returning books, managing member information and tracking book availabilit.

1. Database: The system utilizes MySQL as the database management system to store and manage library-related data. The database would include tables for books, members, transactions, and other relevant information.

2. Java JDBC: Java Database Connectivity (JDBC) is a Java API that provides connectivity to relational databases like MySQL. It allows the Java application to interact with the database, perform queries, and retrieve or modify data.

3. Command Line Interface: The system operates through the Linux terminal, allowing users to input commands and interact with the library management functions. The CLI prompts users to enter options such as adding a new book, searching for a book, issuing or returning books, managing member records, etc.

4. Functionalities:

-> Adding Books: Librarians can input book details such as title, author, ISBN, publication date, and category into the system, which will be stored in the MySQL database.

-> Searching Books: Users can search for books based on different criteria such as title, author, category, or ISBN. The system will query the database and display matching results.

-> Issuing and Returning Books: Librarians can issue books to library members and update the database accordingly. Similarly, when a book is returned, the system updates the status of the book and member's record.

-> Managing Members: The system allows librarians to manage member information, including adding new members, updating member details, and maintaining a record of books issued to each member.
