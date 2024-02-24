import complex.Complex;
import matrix.Matrix;

import static io.InOut.Out;
import java.util.Scanner;

public class Main {
    public static void testComplex() {
        Scanner scanner = new Scanner(System.in);
        Out("Do you want to work with complex numbers? (yes/no): ");
        String choice = scanner.next().toLowerCase();
        if (choice.equals("yes")) {
            Complex x = new Complex(), y = new Complex();
            Out("Input complex x");
            x.InputC();
            Out("Input complex y");
            y.InputC();
            Out("\nx:");
            x.OutputC();
            Out("Trigonometric form of x:");
            x.OutputTrigonometric();
            Out("\ny:");
            y.OutputC();
            Out("Trigonometric form of y:");
            y.OutputTrigonometric();
            Complex z = y.AddC(x);
            Out("\nx + y = ");
            z.OutputC();
            z = y.MulC(x);
            Out("\nx * y = ");
            z.OutputC();
        }
    }

    public static void testMatrix() {
        Scanner scanner = new Scanner(System.in);
        Out("Do you want to work with matrices? (yes/no): ");
        String choice = scanner.next().toLowerCase();
        if (choice.equals("yes")) {
            Matrix A = new Matrix(3, 3);
            Out("Input matrix A");
            A.InputMatrix();

            Matrix B = new Matrix(3,3 );
            Out("Input matrix B");
            B.InputMatrix();

            Out("\nMatrix A:");
            A.OutputMatrix();
            Out("\nMatrix B:");
            B.OutputMatrix();

            Matrix C = A.AddM(B);
            if (C!= null) {
                Out("\nA + B:");
                C.OutputMatrix();
            }
            C = A.MulM(B);
            if (C!= null) {
                Out("\nA * B:");
                C.OutputMatrix();
            }
            C = A.Transpose();
            Out("\nTransposed A:");
            C.OutputMatrix();
            C = B.Transpose();
            Out("\nTransposed B:");
            C.OutputMatrix();
            Complex detA = A.det();
            if (detA!= null) {
                Out("\nDeterminant of A:");
                detA.OutputC();
            }
            Complex detB = B.det();
            if (detB!= null) {
                Out("\nDeterminant of B:");
                detB.OutputC();
            }
        }
    }

    public static void main(String[] args) {
        testComplex();
        testMatrix();
    }
}