package solver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String fileIn = "";
        String fileOut = "";
        for(int i = 0; i < args.length; i++) {
            if("-in".equals(args[i])) {
                fileIn = args[i + 1];
            } else if("-out".equals(args[i])) {
                fileOut = args[i + 1];
            }
        }
        if(isComplex(fileIn) == true) {
            iMatrix matrix = new iMatrix(fileIn, fileOut);
            matrix.printMatrix();
            matrix.findSolutions();
            matrix.printMatrix();
            System.out.println("Row:" + matrix.nRow +" Col: " + matrix.nCol);
            matrix.writeToFile();
        } else {
            Matrix matrix = new Matrix(fileIn, fileOut);
            matrix.printMatrix();
            matrix.findSolutions();
            matrix.printMatrix();
            System.out.println("Row:" + matrix.nRow +" Col: " + matrix.nCol);
            matrix.writeToFile();
        }
    }
    public static boolean isComplex(String fileIn) {
        boolean check = false;
        try(BufferedReader br = new BufferedReader(new FileReader(fileIn))) {
            String line = "";
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                for(int i = 0; i < line.length(); i++) {
                    if(line.charAt(i) == 'i') {
                        check = true;
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error :" + e.getMessage());
        }
        return check;
    }

}
