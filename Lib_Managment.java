//STEP 1. Import required packages

import java.sql.*;
import java.util.*;

public class Lib_Managment {

   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
   static final String DB_URL = "jdbc:mysql://localhost/IIITBLibrary?useSSL=false";
   static final String USER = "yash";
   static final String PASS = "kyk123";

   public static void main(String[] args) {
      Connection conn = null;
      Statement stmt = null;

      try {

         Class.forName(JDBC_DRIVER);

         System.out.println("Connecting to database...");
         conn = DriverManager.getConnection(DB_URL, USER, PASS);

         System.out.println("Creating statement...");
         stmt = conn.createStatement();
         Scanner scan = new Scanner(System.in);

         System.out.println("\nWelcome To IIITB Librar Managment System\n\n");
         menu(stmt, scan);

         stmt.close();
         conn.close();
         scan.close();
      } catch (SQLException se) {
         se.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (stmt != null)
               stmt.close();
         } catch (SQLException se2) {
         }
         try {
            if (conn != null)
               conn.close();
         } catch (SQLException se) {
            se.printStackTrace();
         }
      }
      // System.out.println("End of Code");
   }
   // ------------------------------------ ALL MENUS ----------------------------------------

   static void menu(Statement stmt, Scanner scan) {
      System.out.println("Login to the system as a :- ");
      System.out.println("1. Student");
      System.out.println("2. Archivist");
      System.out.println("3. Lib Head");
      System.out.println("4. Exit");
      System.out.println("");
      int k = Integer.parseInt(scan.nextLine());

      blankScreen();

      switch (k) {
         case 1:
            stu_menu(stmt, scan);
            break;

         case 2:
            arch_check(stmt, scan);
            break;

         case 3:
            lib_head_check(stmt, scan);
            break;

         case 4:
            System.out.println("\nThank you, Visit us again!\n\n");
            System.exit(0);

         default:
            blankScreen();
            System.out.println("Please enter a valid number\n");
            break;
      }

      menu(stmt, scan);
   }

   static void stu_menu(Statement stmt, Scanner scan) {
      System.out.println("Please select one from below-");
      System.out.println("1. Get the list of available books");
      System.out.println("2. Get the list of all the books in the library");
      System.out.println("3. Exit");
      System.out.println("");

      int k = Integer.parseInt(scan.nextLine());

      blankScreen();
      switch (k) {
         case 1:
            book_lst(stmt, scan, true);
            break;

         case 2:
            book_lst(stmt, scan, false);
            break;

         case 3:
            return;

         default:
            blankScreen();
            System.out.println("Please enter a valid number\n");
            break;
      }
      stu_menu(stmt, scan);
   }

   static void arch_menu(Statement stmt, Scanner scan) {
      System.out.println("Please select one from below");
      System.out.println("1. Get the list of all books");
      System.out.println("2. Get the list of available books");
      System.out.println("3. Issue a book");
      System.out.println("4. Return a book");
      System.out.println("5. Add a book to the library");
      System.out.println("6. Delete a book");
      System.out.println("7. Exit");
      System.out.println("");

      int k = Integer.parseInt(scan.nextLine());

      switch (k) {

         case 1:
            book_lst(stmt, scan, false);
            break;
         case 2:
            book_lst(stmt, scan, true);
            break;
         case 3:
            book_issue(stmt, scan);
            break;
         case 4:
            book_return(stmt, scan);
            break;
         case 5:
            book_add(stmt, scan);
            break;
         case 6:
            book_delete(stmt, scan);
            break;

         case 7:
            return;

         default:
            blankScreen();
            System.out.println("Please Enter a Valid number\n");
            break;
      }
      arch_menu(stmt, scan);
   }

   static void lib_head_menu(Statement stmt, Scanner scan) {
      System.out.println("Please select appropriate option-");
      System.out.println("1. Get the list of registered students in the library");
      System.out.println("2. Get thte list of registered archivists in the library");
      System.out.println("3. Add a new student");
      System.out.println("4. Delete an existing student");
      System.out.println("5. Add a new archivist");
      System.out.println("6. Delete an existing archivist");
      System.out.println("7. Exit");
      System.out.println("");

      int k = Integer.parseInt(scan.nextLine());

      switch (k) {
         case 1:
            stu_lst(stmt, scan);
            break;
         case 2:
            arch_lst(stmt, scan);
            break;
         case 3:
            stu_add(stmt, scan);
            break;
         case 4:
            stu_remove(stmt, scan);
            break;
         case 5:
            arch_add(stmt, scan);
            break;
         case 6:
            arch_remove(stmt, scan);
            break;

         case 7:
            return;
         default:
            blankScreen();
            System.out.println("Please Enter a Valid number\n");
            break;
      }
      lib_head_menu(stmt, scan);
   }
   // -------------------------------------- ALL BOOK RELATED METHODS ------------------------------------

   static boolean book_lst(Statement stmt, Scanner scan, boolean availability) {
      boolean no_books = true;
      try {
         String sql = "select * from book";
         ResultSet rs = stmt.executeQuery(sql);

         System.out.println("List of available books: \n");
         while (rs.next()) {
            String book_id = rs.getString("book_id");
            String book_name = rs.getString("book_name");
            String book_author = rs.getString("book_author");
            String published_in = rs.getString("published_in");
            String issued_by = rs.getString("issued_by");

            if (availability == true) {
               if (issued_by == null) {
                  System.out.println("Book ID: " + book_id);
                  System.out.println("Book name: " + book_name);
                  System.out.println("Book author: " + book_author);
                  System.out.println("Book published in: " + published_in);
                  System.out.println("Book issued by: None");
                  System.out.println("");
                  no_books = false;
               }
            } else {
               System.out.println("Book ID: " + book_id);
               System.out.println("Book name: " + book_name);
               System.out.println("Book author: " + book_author);
               System.out.println("Book published in: " + published_in);
               System.out.println("Book issued by: " + issued_by);
               System.out.println("");
               no_books = false;
            }
         }

         if (no_books == true)
            System.out.println("There are no books available, please visit another day! Thank you!\n");

         rs.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return no_books;
   }

   static void book_issue(Statement stmt, Scanner scan) {
      try {
         boolean no_book = book_lst(stmt, scan, true);
         if (no_book == false) {
            System.out.print("Enter the book ID that you want to issue: ");
            String book_id = scan.nextLine();

            System.out.print("Enter your Roll number: ");
            String stu_id = scan.nextLine();

            blankScreen();
            String sql = String.format("update book set issued_by = '%s' where book_id = '%s'", stu_id, book_id);
            int res = stmt.executeUpdate(sql);
            String sql1 = String.format("update student set issued_book_id= '%s' where stu_roll_no = '%s' ", book_id,
                  stu_id);
            int res2 = stmt.executeUpdate(sql1);

            if (res != 0 && res2 != 0)
               System.out.println("Issued Successfully\n");

            else
               System.out.println("Could not issue the book, please try again. \n");
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   static void book_return(Statement stmt, Scanner scan) {
      try {
         System.out.print("Enter your Roll number: ");
         String stu_id = scan.nextLine();
         
         System.out.print("Enter the book ID that you want to return: ");
         String book_id = scan.nextLine();

         blankScreen();
         String sql = String.format("update book set issued_by = NULL where book_id = '%s'", book_id);
         int res = stmt.executeUpdate(sql);
         String sql1 = String.format("update student set issued_book_id = NULL where stu_roll_no = '%s'", stu_id);
         int res1 = stmt.executeUpdate(sql1);

         if (res != 0 && res1 != 0)
            System.out.println("Book returned Successfully\n");

         else
            System.out.println("Could not return the book, please try again. \n");

      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   static void book_add(Statement stmt, Scanner scan) {
      try {
         System.out.print("\nEnter the ID of the new book that you want to add to the library: ");
         String book_id = scan.nextLine();
         System.out.print("\nEnter the name of the new book that you want to add to the library: ");
         String book_name = scan.nextLine();
         System.out.print("\nEnter the name of the author of the new book that you want to add to the library: ");
         String book_author = scan.nextLine();
         System.out.print("\nEnter publication year of the new book that you want to add to the library: ");
         Integer published_in = Integer.parseInt(scan.nextLine());

         blankScreen();

         String sql = String.format("insert into book values('%s', '%s', '%s', '%d', NULL);", book_id, book_name,
               book_author, published_in);
         int res = stmt.executeUpdate(sql);

         if (res != 0)
            System.out.println("Book has been added to the library successfully\n");
         else
            System.out.println("Could not add the book to the library, please try again\n");
      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   static void book_delete(Statement stmt, Scanner scan) {
      try {
         System.out.print("Enter the book ID that you want to delete from the library: ");
         String book_id = scan.nextLine();

         blankScreen();

         String sql = String.format("DELETE FROM book where book_id = '%s'", book_id);
         int result = stmt.executeUpdate(sql);

         if (result != 0)
            System.out.println("Book has been deleted from the library successfully.\n");
         else
            System.out.println("Could not delete the book, please try again\n");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   // -------------------------------- ALL STUDENT RELATED METHODS ------------------------------

   static void stu_lst(Statement stmt, Scanner scan) {
      try {
         String sql = "select * from student";
         ResultSet rs = stmt.executeQuery(sql);

         System.out.println("List of Students registered in the library: \n");
         while (rs.next()) {
            String stu_id = rs.getString("stu_roll_no");
            String stu_name = rs.getString("stu_name");

            System.out.println("Student Roll Number: " + stu_id);
            System.out.println("Student Name: " + stu_name);
            System.out.println("");
         }
         rs.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   static void stu_add(Statement stmt, Scanner scan) {
      try {
         System.out.print("Enter the Roll no of the student that you want to add to the library: ");
         String stu_roll_no = scan.nextLine();
         System.out.print("Enter the name of the student: ");
         String stu_name = scan.nextLine();

         blankScreen();
         String sql = String.format("insert into student values ('%s', '%s', NULL);", stu_roll_no, stu_name);
         int res = stmt.executeUpdate(sql);

         if (res != 0)
            System.out.println("Student added to the library successully\n");
         else
            System.out.println("Could not add student to the library, please try again\n");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   static void stu_remove(Statement stmt, Scanner scan) {
      try {
         System.out.print("Enter the Student Roll No that you want to delete from the library: ");
         String stu_id = scan.nextLine();

         blankScreen();

         String sql = String.format("DELETE FROM student where stu_roll_no = '%s'", stu_id);
         int result = stmt.executeUpdate(sql);

         if (result != 0)
            System.out.println("Student has been deleted from the library successfully.\n");
         else
            System.out.println("Could not delete Student, please try again\n");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   // ------------------------------------ ALL ARCHIVIST RELATED METHODS ----------------------------

   static void arch_lst(Statement stmt, Scanner scan) {
      try {
         String sql = "select * from archivist";
         ResultSet rs = stmt.executeQuery(sql);

         System.out.println("List of Archivists registered in the library: \n");
         while (rs.next()) {
            String arch_id = rs.getString("archivist_id");
            String arch_name = rs.getString("archivist_name");

            System.out.println("Archivist ID: " + arch_id);
            System.out.println("Archivist Name: " + arch_name);
            System.out.println("");
         }
         rs.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   static void arch_add(Statement stmt, Scanner scan) {
      try {
         System.out.print("Enter the ID of the archivist that you want to add to the library: ");
         String archivist_id = scan.nextLine();
         System.out.print("Enter the name of the archivist: ");
         String archivist_name = scan.nextLine();
         System.out.print("Enter the password that you want to use: ");
         String archivist_pass = scan.nextLine();

         blankScreen();
         String sql = String.format("insert into archivist values ('%s', '%s', '%s');", archivist_id, archivist_name,
               archivist_pass);
         int res = stmt.executeUpdate(sql);

         if (res != 0)
            System.out.println("Archivist added to the library successully\n");
         else
            System.out.println("Could not add Archivist to the library, please try again\n");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   static void arch_remove(Statement stmt, Scanner scan) {
      try {
         System.out.print("Enter the Archivist ID that you want to delete from the library: ");
         String arch_id = scan.nextLine();

         blankScreen();

         String sql = String.format("DELETE FROM archivist where archivist_id = '%s'", arch_id);
         int result = stmt.executeUpdate(sql);

         if (result != 0)
            System.out.println("Archivist has been deleted from the library successfully.\n");
         else
            System.out.println("Could not delete Archivist, please try again\n");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   // --------------------------------- ALL HELPER METHODS -------------------------------

   static void blankScreen() {
      System.out.print("\033[H\033[2J");
      System.out.flush();
   }

   static boolean check(Statement stmt, Scanner scan, boolean isLibHead) {
      System.out.print("Enter ID: ");
      String ID = scan.nextLine();
      System.out.print("Enter Password: ");
      String pass = scan.nextLine();

      blankScreen();
      boolean checked = false;

      if (isLibHead == false) {

         try {
            String sql = "SELECT * from archivist";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
               String potential_id = rs.getString("archivist_id");
               String potential_pass = rs.getString("archivist_pass");

               if (potential_id.equals(ID) && potential_pass.equals(pass)) {
                  checked = true;
                  break;
               }
            }
         } catch (SQLException se) {
         }
      } else {
         try {
            String sql = "SELECT * from lib_head";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
               String potential_id = rs.getString("lib_head_id");
               String potential_pass = rs.getString("lib_head_pass");

               if (potential_id.equals(ID) && potential_pass.equals(pass)) {
                  checked = true;
                  break;
               }
            }
         } catch (SQLException se) {
         }
      }

      return checked;
   }

   static void arch_check(Statement stmt, Scanner scan) {
      if (check(stmt, scan, false) == true)
         arch_menu(stmt, scan);

      else {
         System.out.print("Invalid credentials. Do you want to try again? (Yes/No)");
         String k = scan.nextLine();

         if (k.equals("Yes"))
            arch_check(stmt, scan);

         else
            return;
      }
   }

   static void lib_head_check(Statement stmt, Scanner scan) {
      if (check(stmt, scan, true) == true)
         lib_head_menu(stmt, scan);

      else {
         System.out.print("Invalid credentials. Do you want to try again? (Yes/No)");
         String k = scan.nextLine();

         if (k.equals("Yes"))
            lib_head_check(stmt, scan);

         else
            return;
      }
   }
}
