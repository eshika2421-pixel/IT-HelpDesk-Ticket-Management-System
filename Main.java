import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<String> tickets = new ArrayList<>();
        ArrayList<String> statuses = new ArrayList<>();

        int ticketId = 1001;
				try {
    File file = new File("tickets.txt");
    Scanner reader = new Scanner(file);

    StringBuilder ticket = new StringBuilder();
    String status = "Open";

    while (reader.hasNextLine()) {
        String line = reader.nextLine();

        if (line.startsWith("Status: ")) {
            status = line.substring(8);
        } else if (line.equals("--------------------")) {
            if (ticket.length() > 0) {
                tickets.add(ticket.toString());
                statuses.add(status);
                ticket.setLength(0);
                status = "Open";
            }
        } else if (!line.startsWith("Ticket ID:") &&
                   ticket.length() == 0 && line.isBlank()) {
            continue;
        } else if (!line.startsWith("Status:")) {
            ticket.append(line).append("\n");
        }
    }

    reader.close();
    ticketId = 1001 + tickets.size();

} catch (FileNotFoundException e) {
    System.out.println("No previous tickets found.");
}

        while (true) {
            System.out.println("\n===== IT HELP DESK SYSTEM =====");
            System.out.println("1. Create Ticket");
            System.out.println("2. View All Tickets");
            System.out.println("3. Update Ticket Status");
            System.out.println("4. Search Ticket");
						System.out.println("5. Exit");
            

            if (!sc.hasNextInt()) {
                System.out.println("Enter a valid number!");
                sc.nextLine();
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter your name: ");
                    String name = sc.nextLine();

                    System.out.print("Describe your issue: ");
                    String issue = sc.nextLine();

                    System.out.print("Category (Hardware/Software/Network): ");
                    String category = sc.nextLine();

                    System.out.print("Priority (Low/Medium/High): ");
                    String priority = sc.nextLine();

                    String ticket =
                        "\nTicket ID: " + ticketId +
                        "\nName: " + name +
                        "\nIssue: " + issue +
                        "\nCategory: " + category +
                        "\nPriority: " + priority;

                    tickets.add(ticket);
                    statuses.add("Open");

										try {
    FileWriter writer = new FileWriter("tickets.txt", true);
    writer.write(ticket + "\nStatus: Open\n");
    writer.write("--------------------\n");
    writer.close();

    System.out.println("Ticket saved to file!");

} catch (IOException e) {
    System.out.println("Error saving ticket: " + e.getMessage());
}

                    System.out.println("Ticket Created Successfully!");
                    System.out.println("Ticket ID: " + ticketId);

                    ticketId++;
                    break;

                case 2:
                    if (tickets.isEmpty()) {
                        System.out.println("No tickets available!");
                    } else {
                        for (int i = 0; i < tickets.size(); i++) {
                            System.out.println(tickets.get(i));
                            System.out.println("Status: " + statuses.get(i));
                            System.out.println("--------------------");
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter Ticket ID: ");

                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid Ticket ID!");
                        sc.nextLine();
                        break;
                    }

                    int id = sc.nextInt();
                    sc.nextLine();

                    int index = id - 1001;

                    if (index >= 0 && index < tickets.size()) {

                        System.out.println("1. Open");
                        System.out.println("2. In Progress");
                        System.out.println("3. Resolved");
                        System.out.print("Choose status: ");

                        if (!sc.hasNextInt()) {
                            System.out.println("Invalid choice!");
                            sc.nextLine();
                            break;
                        }

                        int statusChoice = sc.nextInt();
                        sc.nextLine();

                        switch (statusChoice) {
                            case 1:
                                statuses.set(index, "Open");
                                break;
                            case 2:
                                statuses.set(index, "In Progress");
                                break;
                            case 3:
                                statuses.set(index, "Resolved");
                                break;
                            default:
                                System.out.println("Invalid status!");
                                continue;
                        }

                        System.out.println("Ticket Status Updated Successfully!");

try {
    FileWriter writer = new FileWriter("tickets.txt");

    for (int i = 0; i < tickets.size(); i++) {
        writer.write(tickets.get(i).trim());
        writer.write("\nStatus: " + statuses.get(i));
        writer.write("\n--------------------\n");
    }

    writer.close();
    System.out.println("Status saved successfully!");

} catch (IOException e) {
    System.out.println("Error saving status: " + e.getMessage());
}

                    } else {
                        System.out.println("Ticket Not Found!");
                    }
                    break;

                
case 4:
    System.out.print("Enter Ticket ID to search: ");

    if (!sc.hasNextInt()) {
        System.out.println("Invalid Ticket ID!");
        sc.nextLine();
        break;
    }

    int searchId = sc.nextInt();
    sc.nextLine();

    int searchIndex = searchId - 1001;

    if (searchIndex >= 0 && searchIndex < tickets.size()) {
        System.out.println(tickets.get(searchIndex));
        System.out.println("Status: " + statuses.get(searchIndex));
    } else {
        System.out.println("Ticket Not Found!");
    }
    break;

case 5:
    System.out.println("Thank you for using IT Help Desk!");
    sc.close();
    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}