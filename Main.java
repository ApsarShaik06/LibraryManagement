import java.util.*; // Importing utilities for data structures like List, ArrayList, HashMap, and Scanner.

// Class to represent a student in the library management system.
class Student {
    String name; // The name of the student.
    int id_no; // Unique ID number assigned to the student.
    String stream; // Stream or course of the student (e.g., CS, Mechanical).
    List<String> issuedBooks = new ArrayList<>(); // A list to store the books currently issued by the student.

    // Constructor to initialize a new student with their details.
    Student(String name, int id_no, String stream) {
        this.name = name;
        this.id_no = id_no;
        this.stream = stream;
    }

    // Method to issue a book to the student.
    public void issueBook(String bookName) {
        if (issuedBooks.size() < 2) { // A student can issue a maximum of 2 books.
            issuedBooks.add(bookName); // Add the book to the list of issued books.
            System.out.println("Book '" + bookName + "' issued successfully to " + name);
        } else {
            System.out.println(name + " cannot issue more than 2 books."); // Show warning if limit exceeded.
        }
    }

    // Method to return a book issued by the student.
    public void returnBook(String bookName) {
        if (issuedBooks.remove(bookName)) { // Remove the book from the issued books list.
            System.out.println("Book '" + bookName + "' returned successfully by " + name);
        } else {
            System.out.println(name + " has not issued this book."); // Show warning if the book is not found.
        }
    }

    // Method to display the books currently issued to the student.
    public void displayIssuedBooks() {
        if (issuedBooks.isEmpty()) { // Check if the student has issued any books.
            System.out.println(name + " has no issued books.");
        } else {
            System.out.println(name + " has issued the following books: " + issuedBooks); // Display the list of issued books.
        }
    }
}

// Class to manage the library's book inventory.
class Library {
    private Map<String, Integer> bookInventory = new HashMap<>(); // HashMap to store books and their available quantities.

    // Method to add a book to the library's inventory.
    public void addBook(String bookName) {
        bookInventory.put(bookName, bookInventory.getOrDefault(bookName, 0) + 1); // Add the book or increase its count.
        System.out.println("Book '" + bookName + "' added to the library.");
    }

    // Method to remove a book from the library's inventory.
    public void removeBook(String bookName) {
        if (bookInventory.containsKey(bookName)) { // Check if the book exists.
            int count = bookInventory.get(bookName); // Get the current count of the book.
            if (count > 1) { // If multiple copies exist.
                bookInventory.put(bookName, count - 1); // Decrease the count by one.
                System.out.println("One copy of book '" + bookName + "' removed from the library.");
            } else {
                bookInventory.remove(bookName); // Remove the book completely if only one copy exists.
                System.out.println("Book '" + bookName + "' removed from the library.");
            }
        } else {
            System.out.println("Book '" + bookName + "' not found in the library."); // Warning if the book is not found.
        }
    }

    // Method to check if a book is available in the library.
    public boolean isBookAvailable(String bookName) {
        return bookInventory.containsKey(bookName) && bookInventory.get(bookName) > 0; // Returns true if the book is available.
    }

    // Method to display all the books available in the library.
    public void displayAvailableBooks() {
        if (bookInventory.isEmpty()) { // Check if there are no books in the library.
            System.out.println("No books available in the library.");
        } else {
            System.out.println("Available books: " + bookInventory); // Display the available books and their counts.
        }
    }

    // Method to display the total number of books in the library.
    public void displayTotalBooks() {
        int totalBooks = bookInventory.values().stream().mapToInt(Integer::intValue).sum(); // Calculate the total number of books.
        System.out.println("Total number of books in the library: " + totalBooks); // Display the total count.
    }

    // Method to display the count of each book in the library.
    public void displayBookCount() {
        if (bookInventory.isEmpty()) { // Check if the library is empty.
            System.out.println("No books available in the library.");
        } else {
            System.out.println("Count of each book in the library:");
            bookInventory.forEach((bookName, count) -> {
                System.out.println("Book: " + bookName + ", Count: " + count); // Display the count for each book.
            });
        }
    }
}

// Main class for the Library Management System
public class Main {

    private static Scanner input; // Scanner to read user input.
    private static HashMap<Integer, Student> students = new HashMap<>(); // HashMap to store students by their IDs.
    private static Library library = new Library(); // Instance of the Library class to manage books.
    private static final String adminUsername = "admin"; // Hardcoded admin username.
    private static final String adminPassword = "password"; // Hardcoded admin password.

    // Method to add a new student to the system.
    static void addStudent() {
        System.out.println("Enter Student Name: ");
        String name = input.next(); // Read the student's name.
        System.out.println("Enter Student ID Number: ");
        int id = input.nextInt(); // Read the student's ID.
        System.out.println("Enter Student Stream: ");
        String stream = input.next(); // Read the student's stream.

        Student newStudent = new Student(name, id, stream); // Create a new Student object.
        students.put(id, newStudent); // Add the student to the students HashMap.
        System.out.println("Student '" + name + "' (ID: " + id + ") added successfully!");
    }

    // Method for the admin to log in.
    static boolean adminLogin() {
        System.out.println("Enter admin username: ");
        String username = input.next(); // Read the admin's username.
        System.out.println("Enter admin password: ");
        String password = input.next(); // Read the admin's password.

        // Validate the admin's credentials.
        if (username.equals(adminUsername) && password.equals(adminPassword)) {
            System.out.println("Admin login successful!");
            return true; // Return true if login is successful.
        } else {
            System.out.println("Invalid credentials. Try again.");
            return false; // Return false if login fails.
        }
    }

    // Method to display the admin's menu options.
    static void adminMenu() {
        boolean exitAdminMenu = false; // Boolean to control the admin menu loop.
        while (!exitAdminMenu) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Display Available Books");
            System.out.println("4. Display Total Books");
            System.out.println("5. Display Book Count");
            System.out.println("6. Add Student");
            System.out.println("7. Exit Admin Menu");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt(); // Get the admin's choice.

            switch (choice) {
                case 1:
                    System.out.println("Enter the name of the book to add: ");
                    String bookToAdd = input.next(); // Read the book's name.
                    library.addBook(bookToAdd); // Add the book to the library.
                    break;
                case 2:
                    System.out.println("Enter the name of the book to remove: ");
                    String bookToRemove = input.next(); // Read the book's name.
                    library.removeBook(bookToRemove); // Remove the book from the library.
                    break;
                case 3:
                    library.displayAvailableBooks(); // Display all available books.
                    break;
                case 4:
                    library.displayTotalBooks(); // Display the total number of books.
                    break;
                case 5:
                    library.displayBookCount(); // Display the count of each book.
                    break;
                case 6:
                    addStudent(); // Add a new student to the system.
                    break;
                case 7:
                    exitAdminMenu = true; // Exit the admin menu.
                    System.out.println("Exiting Admin Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method for the student to log in.
    static void studentLogin() {
        System.out.println("Enter your student ID: ");
        int studentId = input.nextInt(); // Read the student's ID.

        // Check if the student exists in the system.
        if (students.containsKey(studentId)) {
            Student currentStudent = students.get(studentId); // Retrieve the student's information.
            System.out.println("Student login successful for " + currentStudent.name + "!");
            studentMenu(currentStudent); // Call the student menu for the logged-in student.
        } else {
            System.out.println("Student not found. Please try again.");
        }
    }

    // Method to display the student's menu options.
    static void studentMenu(Student student) {
        boolean exitStudentMenu = false; // Boolean to control the student menu loop.
        while (!exitStudentMenu) {
            System.out.println("\n--- Student Menu ---");
            System.out.println("1. Issue Book");
            System.out.println("2. Return Book");
            System.out.println("3. Display Issued Books");
            System.out.println("4. Exit Student Menu");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt(); // Get the student's choice.

            switch (choice) {
                case 1:
                    System.out.println("Enter the name of the book to issue: ");
                    String bookToIssue = input.next(); // Read the book's name.
                    if (library.isBookAvailable(bookToIssue)) { // Check if the book is available in the library.
                        student.issueBook(bookToIssue); // Issue the book to the student.
                    } else {
                        System.out.println("Book '" + bookToIssue + "' is not available.");
                    }
                    break;
                case 2:
                    System.out.println("Enter the name of the book to return: ");
                    String bookToReturn = input.next(); // Read the book's name.
                    student.returnBook(bookToReturn); // Return the book issued by the student.
                    break;
                case 3:
                    student.displayIssuedBooks(); // Display the books issued by the student.
                    break;
                case 4:
                    exitStudentMenu = true; // Exit the student menu.
                    System.out.println("Exiting Student Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        input = new Scanner(System.in); // Initialize Scanner for input.

        boolean exitProgram = false; // Boolean to control the main loop.
        while (!exitProgram) {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Admin Login");
            System.out.println("2. Student Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt(); // Get the user's choice.

            switch (choice) {
                case 1:
                    if (adminLogin()) { // Admin login validation.
                        adminMenu(); // Display the admin menu upon successful login.
                    }
                    break;
                case 2:
                    studentLogin(); // Handle student login.
                    break;
                case 3:
                    exitProgram = true; // Exit the program.
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        input.close(); // Close the Scanner to prevent resource leaks.
    }
}
