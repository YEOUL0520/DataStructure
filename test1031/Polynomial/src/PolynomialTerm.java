public class PolynomialTerm {
    public double coef;
    public int exp;

    public PolynomialTerm(double coef, int exp){
        this.coef = coef;
        this.exp = exp;
    }

    public String toString(){
        return "("+"coef="+coef+", exp=" + exp +')';
    }
}
