package android.finite.com.utility;


public class Tuple<FirstT, SecondT> {
    public FirstT first;
    public SecondT second;

    public Tuple(FirstT first, SecondT second) {
        this.first = first;
        this.second = second;
    }
}
