import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        getFileContents fp = new getFileContents();

        try {
            String filename = fp.getFilePath();

            fp.getAndParseFile(filename);
            while (true) {
                try {
                    System.out.println("Enter the corresponding number for the result set desired:\r\n \r\n1. Euclidean results.\r\n2. Manhattan results\r\n3. Global Euclidean\r\n4. Global Manhattan");
                    Scanner keyboard = new Scanner(System.in);
                    String method = keyboard.nextLine();
                    while (method.isEmpty()) {
                        System.out.println("Invalid input, try again!");
                        System.out.println("Enter the corresponding number for the result set desired:\r\n \r\n1. Euclidean results.\r\n2. Manhattan results\r\n");
                        method = keyboard.nextLine();
                    }
                    if(method.equals("0")){
                        System.out.println("Thank you, goodbye");
                        System.exit(0);
                    }
                    if (method.equals("1")) {
                        System.out.println("The shortest path to the goal is: ");
                        System.out.println("");
                        evaluateStep es = new evaluateStep(fp.getStart(), fp.getGoal(), fp.getMap());
                        //can only run one at a time because of file IO
                        es.searchWithEuclideanDistance();
                        es.printGrid();
                        break;
                    }
                    if (method.equals("2")){
                        System.out.println("The shortest path to the goal is: ");
                        System.out.println("");
                        evaluateStep es = new evaluateStep(fp.getStart(), fp.getGoal(), fp.getMap());
                        //can only run one at a time because of file IO
                        es.searchWithManhattanDistance();
                        es.printGrid();
                        break;
                    }
                    if (method.equals("3")) {
                        System.out.println("The shortest path to the goal is: ");
                        System.out.println("");
                        evaluateStep es = new evaluateStep(fp.getStart(), fp.getGoal(), fp.getMap());
                        //can only run one at a time because of file IO
                        es.searchWithGlobalEuclideanDistance();
                        es.printGrid();
                        break;
                    }
                    if (method.equals("4")) {
                        System.out.println("The shortest path to the goal is: ");
                        System.out.println("");
                        evaluateStep es = new evaluateStep(fp.getStart(), fp.getGoal(), fp.getMap());
                        //can only run one at a time because of file IO
                        es.searchWithGlobalManhattanDistance();
                        es.printGrid();
                        break;
                    }else{
                        System.out.println("Invalid input, enter a valid number or 0 to quit");
                    }
                } catch (Exception e) {
                    System.out.println("Something happened! Please try again!");
                }
            }
        }
        catch(Exception e) {
            System.err.println(e);
        }
    }
}