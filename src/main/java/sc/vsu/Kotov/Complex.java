package sc.vsu.Kotov;

import java.util.Objects;

public class Complex {
    double real;
    double imaginary;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public void sum(Complex num){
        this.real += num.real;
        this.imaginary += num.imaginary;
    }
    public void sum(double num){
        this.real += num;
    }
    public void multiplication(double num){
        this.real *= num;
        this.imaginary *= num;
    }

    @Override
    public String toString() {
        if(imaginary < 0)
        return String.valueOf(real) + imaginary + 'i';
        else if (imaginary > 0){
            return String.valueOf(real) + '+' + imaginary + 'i';
        }
        else {
            return String.valueOf(real);
        }
    }
    public double module(){
        return Math.sqrt(Math.pow(real,2) + Math.pow(imaginary,2));
    }
    public Complex multiplication(Complex num){
        return new Complex(this.real* num.real - this.imaginary*num.imaginary, this.imaginary* num.real + this.real* num.imaginary);
    }
    public boolean isReal(){
        return this.imaginary == 0;
    }
    public boolean isImaginary(){
        return this.real == 0 && this.imaginary != 0;
    }
    public boolean isComplex(){
        return this.imaginary != 0 && this.real != 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Complex complex = (Complex) o;
        return Double.compare(complex.real, real) == 0 && Double.compare(complex.imaginary, imaginary) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(real, imaginary);
    }
}
