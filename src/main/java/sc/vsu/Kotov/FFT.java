package sc.vsu.Kotov;

public class FFT {
    int N = 100;
    double Delta = 0.01;
    int l = 1;
    int H = 50;
    int L = 10000;
    Complex[] C;
    double[] F;
    double[] Omega;
    Complex[] WaveNumber;
    Complex[] result;
    Complex[] receivedSpectrum;
    int Time = (int) ((L/1500) * 1.5) * N;
    double T = N*Delta;

    public FFT() {
        C = new Complex[Time];
        F = new double[Time];
        Omega = new double[Time];
        WaveNumber = new Complex[Time];
        result = new Complex[Time];
        receivedSpectrum = new Complex[Time];
        transform();
        generateFrequencyArray();
        generateAngularFrequencyArray();
        generateWaveNumberArray();
        calculateSpectrumReceivedSignal();
        reverseTransform();
    }

    public double func(double t) {
        if(t<=T)
        return Math.sin(100*t);
        else return 0;
    }

    public void transform() {
        Complex tmpRes = new Complex(0, 0);

        for (int i = 0; i < Time; i++) {
            for (int j = 0; j < Time - 1; j++) {
                Complex tmp = new Complex(Math.cos(-2 * Math.PI * i * j / Time), Math.sin(-2 * Math.PI * i * j / Time));
                tmp.multiplication(func(j * Delta));
                tmpRes.sum(tmp);
            }
            C[i] = tmpRes;
            tmpRes = new Complex(0, 0);
        }
    }

    public void generateFrequencyArray() {
        double f = 1 / Delta;
        double deltaF = f / Time;
        for (int i = 0; i < Time; i++) {
            F[i] = i * deltaF;
        }
    }

    public void generateAngularFrequencyArray() {
        for (int i = 0; i < Time; i++) {
            Omega[i] = 2 * Math.PI * F[i];
        }
    }

    public void generateWaveNumberArray() {
        int speedOfSoundInWater = 1500;
        for (int i = 0; i < Time; i++) {
            double tmp = Math.pow(Omega[i] / speedOfSoundInWater, 2) - Math.pow(Math.PI * (l - 0.5) / H, 2);
            if (tmp < 0) {
                WaveNumber[i] = new Complex(0, Math.sqrt(-tmp));
            } else WaveNumber[i] = new Complex(Math.sqrt(tmp), 0);
        }
    }

    public void calculateSpectrumReceivedSignal() {
        for (int i = 0; i < Time; i++) {
            if (WaveNumber[i].isReal()) {
                receivedSpectrum[i] = C[i].multiplication(new Complex(Math.cos(-WaveNumber[i].real * L), Math.sin(-WaveNumber[i].real * L)));
            } else receivedSpectrum[i] = C[i];
        }
    }

    public void reverseTransform() {
        Complex tmpRes = new Complex(0, 0);
        for (int i = 0; i < Time; i++) {
            for (int j = 0; j < Time - 1; j++) {
                Complex tmp = new Complex(Math.cos(2 * Math.PI * i * j / Time), Math.sin(2 * Math.PI * i * j / Time));
                tmp = tmp.multiplication(receivedSpectrum[j]);
                tmpRes.sum(tmp);
            }
            tmpRes.multiplication((float)1/Time);
            result[i] = tmpRes;
            tmpRes = new Complex(0, 0);
        }
    }
}