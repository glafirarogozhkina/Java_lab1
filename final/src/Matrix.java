package matrix;

import complex.Complex;

import static io.InOut.*;

public class Matrix {
    private int rows, cols;
    private Complex[][] matr;
    private boolean isComplex = false;

    public int getRows() {return rows;}
    public int getCols() {return cols;}
    public boolean getComplex() {return isComplex;}
    public Complex[][] getMatr() {return matr;}
    public Complex getElem(int i, int j) {return matr[i][j];}
    public void setRows(int r) {rows = r;}
    public void setCols(int c) {cols = c;}
    public void setComplex(boolean isC) {isComplex = isC;}
    public void setMatr(Complex[][] m) {matr = m;}

    public Matrix() {
        rows = 0; cols = 0;
    }
    public Matrix(int n, int m) {
        rows = n;
        cols = m;
        matr = new Complex[rows][cols];
    }
    public Matrix(Complex[][] matrix) {
        matr = matrix;
        rows = matr.length;
        cols = matr[0].length;
        isComplex = true;
    }
    public Matrix(double[][] matrix) {
        rows = matrix.length;
        cols = matrix[0].length;
        matr = new Complex[rows][cols];
        for (int i = 0; i < rows; ++i)
            for (int j = 0; j < cols; ++j) {
                Complex x = new Complex(matrix[i][j], 0);
                matr[i][j] = x;
            }
    }

    public void InputMatrix() {
        isComplex = (In("1 - real numbers, 2 - complex: ") == 2);
        rows = (int)In("Number of rows: ");
        cols = (int)In("Number of columns: ");
        matr = new Complex[rows][cols];
        for (int i = 0; i < rows; ++i)
            for (int j = 0; j < cols; ++j)
            {
                if (isComplex) {
                    Out("Input matr[" + i + "][" + j + "]");
                    Complex x = new Complex();
                    x.InputC();
                    matr[i][j] = x;
                }
                else {
                    Complex x = new Complex(In("matr[" + i + "][" + j + "] = "), 0);
                    matr[i][j] = x;
                }
            }
    }

    public void OutputMatrix() {
        for (int i = 0; i < rows; ++i)
            for (int j = 0; j < cols; ++j)
            {
                if (isComplex) {
                    Out(String.format("matr[%d][%d] = ", i, j));
                    matr[i][j].OutputC();
                }
                else {
                    Out(String.format("matr[%d][%d] = %.2f", i, j, matr[i][j].getRe()));
                }
            }
    }

    public Matrix AddM(Matrix x) {
        if (x.getRows() != rows || x.getCols() != cols)
        {
            Err("Size of matrices doesn't match");
            return null;
        }

        Complex[][] zmatr = new Complex[rows][cols],
                xmatr = x.getMatr();
        for (int i = 0; i < rows; ++i)
            for (int j = 0; j < cols; ++j)
                zmatr[i][j] = matr[i][j].AddC(xmatr[i][j]);
        return new Matrix(zmatr);
    }

    public Matrix SubM(Matrix x) {
        if (x.getRows() != rows || x.getCols() != cols)
        {
            Err("Size of matrices doesn't match");
            return null;
        }

        Complex[][] zmatr = new Complex[rows][cols];
        for (int i = 0; i < rows; ++i)
            for (int j = 0; j < cols; ++j)
                zmatr[i][j] = matr[i][j].SubC(x.getElem(i, j));
        return new Matrix(zmatr);
    }

    public Matrix MulM(Matrix x) {
        if (cols != x.getRows()) {
            Err("Size of matrices doesn't match");
            return null;
        }

        Complex[][] zmatr = new Complex[rows][x.getCols()];

        for (int i = 0; i < rows; ++i)
            for (int j = 0; j < x.getCols(); ++j) {
                zmatr[i][j] = new Complex();
                for (int k = 0; k < cols; ++k)
                    zmatr[i][j] = zmatr[i][j].AddC(matr[i][k].MulC(x.getElem(k, j)));
            }

        return new Matrix(zmatr);
    }

    public Matrix Transpose() {
        Complex[][] zmatr = new Complex[cols][rows];
        for (int i = 0; i < rows; ++i)
            for (int j = 0; j < cols; ++j)
                zmatr[j][i] = matr[i][j];
        return new Matrix(zmatr);
    }

    public Complex det() {
        if (rows!= cols) {
            Out("Non square matrix");
            return null;
        }
        return D(matr, rows);
    }

    private Complex D(Complex[][] matrix, int n) {
        if (n == 1)
            return matrix[0][0];
        if (n == 2)
            return matrix[0][0].MulC(matrix[1][1]).SubC(matrix[1][0].MulC(matrix[0][1]));

        Complex[][] minor = new Complex[n-1][n-1];
        Complex result = new Complex();
        int sign = 1;
        for (int i = 0; i < n; ++i) {
            int r = 0, c = 0;
            for (int j = 1; j < n; ++j) {
                for (int k = 0; k < n; ++k) {
                    if (k!= i) {
                        minor[r][c] = matrix[j][k];
                        ++c;
                        if (c == n-1) {
                            ++r;
                            c = 0;
                        }
                    }
                }
            }
            result = result.AddC(new Complex(sign, 0).MulC(matrix[0][i].MulC(D(minor, n-1))));
            sign *= -1;
        }
        return result;
    }
}