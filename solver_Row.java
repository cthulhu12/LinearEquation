package solver;

import java.util.ArrayList;

public class Row {
    ArrayList<Double> row;
    double solution;

    public Row() {
        this.row = new ArrayList<>();
    }
    void multiplyRow(double value) {
        for(int i = 0; i < row.size(); i++) {
            row.set(i, row.get(i) * value);
        }
        solution *= value;
    }
    void divideRow(double value) {
        for(int i = 0; i < row.size(); i++) {
            row.set(i, row.get(i) / value);
        }
        solution /= value;
    }
    boolean isOne(int position) {
        if(row.get(position) == 1) {
            return true;
        } else {
            return false;
        }
    }
    void operationByRow(Row r, double value) {
        for(int i = 0; i < row.size(); i++) {
            row.set(i, row.get(i) + value * r.row.get(i));
        }
        solution = solution + (value * r.solution);
    }
    void printRow() {
        for(int i = 0; i < row.size(); i ++) {
            System.out.print(row.get(i) + " ");
        }
        System.out.print(" | " + solution);
    }
    void swapNumbers(int c1, int c2) {
        double temp = row.get(c1);
        row.set(c1, row.get(c2));
        row.set(c2, temp);
    }
    boolean isZero(int position) {
        if(row.get(position) == 0) {
            return true;
        } else {
            return false;
        }
    }
    boolean isZeroRow() {
        boolean check = true;
        for(double d : row) {
            if(Math.abs(d) != 0) {
                check = false;
            }
        }
        return check;
    }
    boolean isSolution() {
        boolean check = true;
        if(isZeroRow() == true && solution != 0) {
            check = false;
        }
        return check;
    }

}
