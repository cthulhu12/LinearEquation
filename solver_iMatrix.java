package solver;

import java.io.*;
import java.util.ArrayList;

public class iMatrix {
    ArrayList<iRow> matrix;
    int nCol;
    int nRow;
    String inFile;
    String outFile;
    public iMatrix(String inFile, String outFile) {
        this.inFile = inFile;
        this.outFile = outFile;
        this.matrix = new ArrayList<>();
        readFromFile();
    }
    void readFromFile() {
        try(BufferedReader br = new BufferedReader(new FileReader(inFile))) {
            String line = "";
            line = br.readLine();
            String[] str = line.split(" ");
            this.nCol = Integer.parseInt(str[0]);
            this.nRow = Integer.parseInt(str[1]);
            while ((line = br.readLine()) != null) {
                str = line.split(" ");
                iRow r = new iRow();
                for (int i = 0; i < nCol; i++) {
                    r.row.add(new ComplexNumber(str[i]));
                }
                r.solution = new ComplexNumber(str[nCol]);
                matrix.add(r);
            }

        } catch (IOException e) {
            System.out.println("Error :" + e.getMessage());
        }
    }
    void printMatrix(){
        for(int i = 0; i < nRow; i++) {
            matrix.get(i).printRow();
            System.out.println();
        }
        System.out.println("------------------");
    }
    void leadingOne(int position) {
        if(matrix.get(position).isOne(position) == false) {
            matrix.get(position).divideRow(matrix.get(position).row.get(position));
        }
    }
    void zeroUnder(int position) {
        for(int i = position + 1; i < nRow; i++) {
            ComplexNumber cn = new ComplexNumber(0,0);
            matrix.get(i).operationByRow(matrix.get(position), cn.subtract(matrix.get(i).row.get(position)));
        }
    }
    void zeroOver(int position) {
        for(int i = position - 1; i >= 0; i--) {
            ComplexNumber cn = new ComplexNumber(0,0);
            matrix.get(i).operationByRow(matrix.get(position), cn.subtract(matrix.get(i).row.get(position)));
        }
    }
    void swapRow(int r1, int r2) {
        iRow temp = matrix.get(r1);
        matrix.set(r1, matrix.get(r2));
        matrix.set(r2, temp);
    }
    void swapColumn(int c1, int c2) {
        for(iRow r : matrix) {
            r.swapNumbers(c1, c2);
        }
    }
    int searchColumn(int row, int col) {
        for(int i = row; i < nRow; i++) {
            if(matrix.get(i).isZero(col) == false) {
                return i;
            }
        }
        return -1;
    }
    int[] searchColumns(int col, int row) {
        int resultRow;
        for(int i = col; i < nCol; i++) {
            resultRow = searchColumn(row, i);
            if(resultRow >= 0) {
                int[] swapInfo = {resultRow, i};
                return swapInfo;
            }
        }
        int[] swapInfo = {-1, -1};
        return swapInfo;
    }
    void swapIfZero(int col, int row) {
        if(matrix.get(row).isZero(col) == true) {
            int[] swapInfo = searchColumns(col, row);
            if(swapInfo[0] > 0) {
                swapRow(row, swapInfo[0]);
            } else if(swapInfo[1] > 0) {
                swapColumn(col, swapInfo[1]);
            }
        }
    }
    boolean isSolution() {
        boolean check = true;
        for (iRow r : matrix) {
            check = r.isSolution();
        }
        return check;
    }
    void deleteZeroRows() {
        for(int i = 0; i < nRow; i++) {
            if(matrix.get(i).isZeroRow() == true && matrix.get(i).solution.isZero()){
                matrix.remove(i);
                setnRow(nRow - 1);
            }
        }
    }
    void findSolutions() {
        int size;
        if(nRow < nCol) {
            size = nRow;
        } else {
            size = nCol;
        }
        for(int i = 0; i < size; i++) {
            if(matrix.get(i).isZeroRow() == false) {
                swapIfZero(i, i);
                leadingOne(i);
                zeroUnder(i);
            } else {
                deleteZeroRows();
                size--;
            }
        }
        for(int i = size - 1; i >= 0; i--) {
            zeroOver(i);
        }
        deleteZeroRows();
    }
    void writeToFile() {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(outFile))) {
            if(isSolution() == true && isInfitite() == false) {
                for (iRow r : matrix) {
                    bw.write(r.solution.toString() + "\n");
                }
            } else if(isSolution() == false) {
                bw.write("No solutions");
            } else {
                bw.write("Infinitely many solutions");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    void setnRow(int value) {
        this.nRow = value;
    }
    boolean isInfitite() {
        if(nRow != nCol) {
            return true;
        }
        else {
            return false;
        }
    }
}
