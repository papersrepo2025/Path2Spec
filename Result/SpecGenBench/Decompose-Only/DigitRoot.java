class DigitRoot {
    //@ ensures (num >= 0) ==> (\result >= 0 && \result <= 9);
    //@ ensures (num >= 0 && num < 10) ==> (\result == num);
    public int digitRoot(int num) {
        while (num >= 10) {
            int sum = 0;
            //@ maintaining sum >= 0;
            //@ maintaining num >= 0;
            //@ decreases num;
            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }
            num = sum;
        }
        return num;
    }
}
