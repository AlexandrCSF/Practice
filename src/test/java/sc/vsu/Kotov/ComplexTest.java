package sc.vsu.Kotov;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class ComplexTest {

    @org.junit.jupiter.api.Test
    void multiplication() {
        Complex num1 = new Complex(5,32);
        Complex num2 = new Complex(12,45);
        Complex expectedResult = new Complex(-1380,609);
        Assertions.assertEquals(expectedResult,num1.multiplication(num2));
    }
}