package complex;

import java.io.IOException;

import static io.InOut.Err;
import static io.InOut.In;
import static io.InOut.Out;

public class Complex {
    private double a;
    private double b;
    public Complex(double re, double im) {
        a = re;
        b = im;
    }

    public Complex() {
        this(0, 0);
    }

    public void InputC() {
        a = In("re = ");
        b = In("im = ");
    }
    public void OutputC() {
        Out(a + " + " + b + "i");
    }
    public void OutputTrigonometric() {
        double r = Math.sqrt(a * a + b * b),
                f = Math.atan(b / a);
        Out(String.format("%.2f * (cos(%.2f) + i * sin(%.2f))", r, f, f));
    }

    public double getRe() {return a;}
    public double getIm() {return b;}
    public void setRe(double a) {this.a = a;}
    public void setIm(double b) {this.b = b;}

    public Complex AddC(Complex x) {
        return new Complex(a + x.a, b + x.b);
    }
    public Complex SubC(Complex x) {
        return new Complex(a - x.a, b - x.b);
    }
    public Complex MulC(Complex x) {
        double re = a * x.a - b * x.b,
                im = a * x.b + b * x.a;
        return new Complex(re, im);
    }
    public Complex DivC(Complex x) {
        double re, im;
        try {
            re = (a * x.a + b * x.b) / (x.a * x.a + x.b * x.b);
            im = (b * x.a - a * x.b) / (x.a * x.a + x.b * x.b);
            return new Complex(re, im);
        }
        catch (ArithmeticException e) {
            Err("Division by zero");
            return null;
        }
    }

}