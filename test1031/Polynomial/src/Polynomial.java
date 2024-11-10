import java.util.ArrayList;

public class Polynomial {
    private final ArrayList <PolynomialTerm> termArray;

    public Polynomial(){
        termArray = new ArrayList<>();
    }
    public void appendTerm(double coef, int exp){
        termArray.add(new PolynomialTerm(coef, exp));
    }
    @Override
    public String toString(){
        return "다항식: " + termArray;
    }

    public Polynomial add(Polynomial B){
        Polynomial D = new Polynomial();

        int posA = 0, posB = 0;
        double sum;
        while(posA<termArray.size() && posB<B.termArray.size()){
            PolynomialTerm termA = termArray.get(posA);
            PolynomialTerm termB = B.termArray.get(posB);
            switch(Integer.compare(termA.exp, termB.exp)){
                case 1: D.appendTerm(termA.coef, termA.exp);
                posA++;
                break;
                case -1: D.appendTerm(termB.coef, termB.exp);
                posB++;
                break;
                case 0: sum = termA.coef + termB.coef;
                if (sum != 0.0)
                    D.appendTerm(sum, termA.exp);
                posA++;
                posB++;
            }
        }

        for(;posA<termArray.size();posA++)
            D.appendTerm(termArray.get(posA).coef, termArray.get(posA).exp);

        for(;posB<B.termArray.size();posB++)
            D.appendTerm(B.termArray.get(posB).coef, B.termArray.get(posB).exp);

        return D;
    }

    public Polynomial mul(Polynomial B) {
        Polynomial E = new Polynomial();

        // 첫 번째 다항식의 각 항에 대해 두 번째 다항식의 모든 항과 곱하기
        for (PolynomialTerm termA : termArray) {
            for (PolynomialTerm termB : B.termArray) {
                double mulCoef = termA.coef * termB.coef; // 새 항의 계수
                int mulExp = termA.exp + termB.exp; // 새 항의 차수

                // 새 항을 결과 다항식에 추가
                boolean found = false;

                // 기존 항과 비교하여 계수를 합치거나 적절한 위치에 삽입
                for (int i = 0; i < E.termArray.size(); i++) {
                    PolynomialTerm termE = E.termArray.get(i);
                    if (termE.exp == mulExp) {
                        // 같은 차수의 항이 있으면 계수를 합침
                        termE.coef += mulCoef;
                        found = true;
                        break;
                    } else if (termE.exp < mulExp) {
                        // 차수가 작으면 그 앞에 새 항을 삽입
                        E.termArray.add(i, new PolynomialTerm(mulCoef, mulExp));
                        found = true;
                        break;
                    }
                }
                // 기존 항과 일치하는 차수가 없으면 리스트 끝에 추가
                if (!found) {
                    E.appendTerm(mulCoef, mulExp);
                }
            }
        }

        // 계수가 0인 항 제거 (선택 사항)
        for (int i = E.termArray.size() - 1; i >= 0; i--) {
            if (E.termArray.get(i).coef == 0) {
                E.termArray.remove(i);
            }
        }

        return E;
    }

    public static void main(String[] args){
        Polynomial A = new Polynomial();
        Polynomial B = new Polynomial();

        A.appendTerm(1, 2);
        A.appendTerm(2, 1);
        A.appendTerm(1,0);

        B.appendTerm(2, 1);
        B.appendTerm(1, 0);

        System.out.println("A" + A);
        System.out.println("B" + B);
        System.out.println("A+B" + A.mul(B));
    }
}
