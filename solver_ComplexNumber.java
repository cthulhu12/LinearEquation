package solver;

public class ComplexNumber {
    double Re;
    double Im;
    public ComplexNumber(double Re, double Im) {
        this.Re = Re;
        this.Im = Im;
    }
    public ComplexNumber (String str) {
        double Re;
        double Im;
        int idx = 1;
        boolean isNegative = false;
        if(str.charAt(0) == 'i'){
            this.Re = 0;
            this.Im = 1;
        }else if("-i".equals(str)) {
            this.Re = 0;
            this.Im = -1;
        }else if(isNumber(str)) {
            this.Re = Double.parseDouble(str);
            this.Im = 0;
        }
        else{
            String[] str2 = str.split("(-)|(\\+)");
            if (str2[0].isEmpty() && str2.length > 2 ) {
                Re = 0 - Double.parseDouble(str2[1]);
                idx = 2;
            } else if (str2[0].isEmpty() == false && str2.length > 1) {
                Re = Double.parseDouble(str2[0]);
                idx = 1;
            }  else {
                Re = 0;
                for (int i = 0; i < str.length() - 1; i++) {
                    if (str.charAt(i) == '-') {
                        isNegative = true;
                    }
                }
            }
            for (int i = 1; i < str.length() - 1; i++) {
                if (str.charAt(i) == '-') {
                    isNegative = true;
                }
            }
            if (isNegative == true && !"i".equals(str2[str2.length-1])) {
                Im = 0 - Double.parseDouble(str2[idx].substring(0, str2[idx].length() - 1));
            } else if (isNegative == true && "i".equals(str2[str2.length-1])) {
                Im = -1;
            }else if(isNegative == false && "i".equals(str2[str2.length-1])) {
                Im = 1;
            }else {
                Im = Double.parseDouble(str2[idx].substring(0, str2[idx].length() - 1));
            }
            this.Re = Re;
            this.Im = Im;
        }

    }
    public static boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    ComplexNumber add(ComplexNumber cn) {
        double resultRe = this.Re + cn.Re;
        double resultIm = this.Im + cn.Im;
        return new ComplexNumber(resultRe, resultIm);
    }
    ComplexNumber subtract(ComplexNumber cn) {
        double resultRe = this.Re - cn.Re;
        double resultIm = this.Im - cn.Im;
        return new ComplexNumber(resultRe, resultIm);
    }
    ComplexNumber multiply(ComplexNumber cn) {
        double resultRe = this.Re * cn.Re - this.Im * cn.Im;
        double resultIm = this.Re * cn.Im + this.Im * cn.Re;
        return new ComplexNumber(resultRe, resultIm);
    }
    ComplexNumber divide(ComplexNumber cn) {
        double temp = Math.pow(cn.Re,2) + Math.pow(cn.Im,2);
        double resultRe = (this.Re * cn.Re + this.Im * cn.Im) / temp;
        double resultIm = (this.Im * cn.Re - this.Re * cn.Im) / temp;
        return new ComplexNumber(resultRe, resultIm);
    }
    boolean isZero() {
        if(Im == 0 && Re == 0) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public String toString() {
        if(Re == 0) {
            return "" + Im +"i";
        } else if (Im > 0) {
           return Re + "+" + Im + "i";
        } else if (Im < 0) {
            return Re +""+ Im + "i";
        } else {
            return "" + Re;
        }
    }
}
