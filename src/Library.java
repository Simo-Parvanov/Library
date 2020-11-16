import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Library {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Map<String, Integer> library = new HashMap<>();
        Map<String, Integer> clone = new HashMap<>();
        try {
            File myObj = new File("resources\\files\\items.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine().substring(2);
                library.putIfAbsent(data, 0);
                library.put(data, library.get(data) + 1);
                clone.putIfAbsent(data,0);
                clone.put(data, clone.get(data) + 1);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        while (true) {
            System.out.println("Please select one of the following options: ");
            System.out.println(
                    "To rent a book / magazine, press 1:\n" +
                            "To return a book / magazine, press 2\n" +
                            "For a complete list of publications in the library, press 3\n" +
                            "To exit the library, press 0\n");
            int option = Integer.parseInt(scan.nextLine());
            boolean end = false;
            switch (option){
                case 0:
                    end = true;
                    break;
                case 1:
                    String searchRent = input();
                    if (library.containsKey(searchRent)){
                        if (library.get(searchRent) > 0){
                            library.put(searchRent, library.get(searchRent) - 1);
                            System.out.println("You rented the edition successfully!\n");
                        }else {
                            System.out.println("The edition is not available at the moment!\n");
                        }
                    }else {
                        System.out.println("There is no such edition, please try again!\n");
                    }
                    break;

                case 2:
                    String returnBook = input();
                    if (library.containsKey(returnBook)){
                        if (library.get(returnBook) < clone.get(returnBook)){
                            library.put(returnBook, library.get(returnBook) + 1);
                            System.out.println("Successfully returned edition.\n");
                        }else {
                            System.out.println("All editions are available, you have mistaken the library!\n");
                        }
                    }else {
                        System.out.println("There is no such edition, please try again!\n");
                    }
                    break;
                case 3:
                    library.forEach((a,b) -> {
                        String[] split = a.split(";");
                        System.out.println(split[0] + " <----> " + split[1] + " | availability -> " + b
                                + " | total number ->" + clone.get(a));
                    });
                    System.out.println();
                    break;
            }
            if (end){
                break;
            }

        }
    }
    public static String input(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the name of the book or magazine:");
        String name = scan.nextLine();
        System.out.println("Enter an author name or release date:");
        String author = scan.nextLine();
        return name + ";" + author;
    }
}

