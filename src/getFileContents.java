import java.io.*;
import java.util.*;

public class getFileContents {
    private int size;
    private int count;

    //initial grid
    private pointerNode[][] map;
    //start position
    private pointerNode start;
    //goal position
    private pointerNode goal;


    public String getFilePath() {
        while (true) {
            try {
                System.out.println("Enter the file path or enter 0 to quit: ");
                Scanner keyboard = new Scanner(System.in);
                String filename = keyboard.nextLine();
                while (filename.isEmpty()) {
                    System.out.println("Invalid input, try again!");
                    System.out.println("Enter the file path or enter 0 to quit: ");
                    filename = keyboard.nextLine();
                }
                if (filename.equals("0")) {
                    System.out.println("Thank you, goodbye");
                    System.exit(0);
                }else{
                    try{
                        BufferedReader br = new BufferedReader(new FileReader(filename));
                        return  filename;
                    }catch (FileNotFoundException e){
                        System.out.println("File Not found, try again!");
                    }
                }
            } catch (Exception e) {
                System.out.println("Something happened! Please try again!");
            }
        }
    }

    //Game of Life//
    private void spacesAllowed(pointerNode n, int row, int column) {
        //if path is not initially blocked
        if (n.getType() != 3) {
            if (row == 0) {
                if (column == 0) {
                    n.addNext(map[row + 1][column]);
                    n.addNext(map[row][column + 1]);
                } else if (column == size - 1) {
                    n.addNext(map[row + 1][column]);
                    n.addNext(map[row][column - 1]);
                } else {
                    n.addNext(map[row + 1][column]);
                    n.addNext(map[row][column + 1]);
                    n.addNext(map[row][column - 1]);
                }
            } else if (row == size - 1) {
                if (column == size - 1) {
                    n.addNext(map[row - 1][column]);
                    n.addNext(map[row][column - 1]);
                } else if (column == 0) {
                    n.addNext(map[row - 1][column]);
                    n.addNext(map[row][column + 1]);
                } else {
                    n.addNext(map[row - 1][column]);
                    n.addNext(map[row][column - 1]);
                    n.addNext(map[row][column + 1]);
                }
            } else if (column == 0) {
                n.addNext(map[row + 1][column]);
                n.addNext(map[row - 1][column]);
                n.addNext(map[row][column + 1]);
            } else if (column == size - 1) {
                n.addNext(map[row + 1][column]);
                n.addNext(map[row - 1][column]);
                n.addNext(map[row][column - 1]);
            } else {
                n.addNext(map[row + 1][column]);
                n.addNext(map[row - 1][column]);
                n.addNext(map[row][column - 1]);
                n.addNext(map[row][column + 1]);
            }
        }
    }


    public void getAndParseFile(String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            size = Integer.parseInt(br.readLine());
            map = new pointerNode[size][size];
        //Create matrix and assign number to each type for easier processing
            while ((line = br.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    // . = 1 -> Can Step
                    if (line.charAt(i) == '.') {
                        map[count][i] = new pointerNode(count, i, 0);

                        // . = 1 -> Can't Step
                    } else if (line.charAt(i) == '+') {
                        map[count][i] = new pointerNode(count, i, 3);

                        // . = 1 -> Start State
                    } else if (line.charAt(i) == 'i') {
                        pointerNode temp = new pointerNode(count, i, 1);
                        map[count][i] = temp;
                        start = temp;

                        // . = 1 -> Goal State
                    } else if (line.charAt(i) == 'g') {
                        pointerNode temp = new pointerNode(count, i, 2);
                        map[count][i] = temp;
                        goal = temp;
                    }
                }
                count++;
            }
            br.close();


            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    spacesAllowed(map[i][j], i, j);
                }
            }
            //String first_number = reader.readLine();
            //int number_of_lines = Integer.parseInt(first_number);
        } catch (IOException e) {
            System.out.println("Something happened while reading your file, try again!");
        }
        catch (NullPointerException e){
            System.out.println("The row length does not match the column height, please restart and try again");
        }
    }

    //returns start position
    public pointerNode getStart() { return start; }

    //returns goal position
    public pointerNode getGoal(){ return goal; }

    //returns current map
    public pointerNode[][] getMap(){ return map; }
}
