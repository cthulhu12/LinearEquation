package solver;

import java.util.ArrayList;

public class iRow extends Row {
    ArrayList<ComplexNumber> row;
    ComplexNumber solution;

    public iRow() {
        this.row = new ArrayList<>();
    }
    public void multiplyRow(ComplexNumber cn) {
        for(int i = 0; i < row.size(); i++) {
            row.set(i, row.get(i).multiply(cn));
        }
        solution = solution.multiply(cn);
    }
    void divideRow(ComplexNumber cn) {
        for(int i = 0; i < row.size(); i++) {
            row.set(i, row.get(i).divide(cn));
        }
        solution = solution.divide(cn);
    }
    boolean isOne(int position) {
        if(row.get(position).Re == 1 && row.get(position).Im == 0) {
            return true;
        } else {
            return false;
        }
    }
    void operationByRow(iRow r, ComplexNumber cn) {
        for(int i = 0; i < row.size(); i++) {
            row.set(i, row.get(i).add(cn.multiply(r.row.get(i))));
        }
        solution = solution.add(cn.multiply(r.solution));
    }
    void printRow() {
        for(int i = 0; i < row.size(); i ++) {
            System.out.print(row.get(i).toString() + " ");
        }
        System.out.print(" | " + solution.toString());
    }
    void swapNumbers(int c1, int c2) {
        ComplexNumber temp = row.get(c1);
        row.set(c1, row.get(c2));
        row.set(c2, temp);
    }
    boolean isZero(int position) {
        if(row.get(position).Re == 0 && row.get(position).Im == 0) {
            return true;
        } else {
            return false;
        }
    }
    boolean isZeroRow() {
        boolean check = true;
        for(ComplexNumber cn : row) {
            if(Math.abs(cn.Re) != 0 || Math.abs(cn.Im) != 0) {
                check = false;
            }
        }
        return check;
    }
    boolean isSolution() {
        boolean check = true;
        if(isZeroRow() == true && (solution.Re != 0 || solution.Im != 0)) {
            check = false;
        }
        return check;
    }
}
