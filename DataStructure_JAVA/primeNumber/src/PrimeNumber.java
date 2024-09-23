/* 22112144_류효정
 *
 * 파일명: "PrimeNumber.java"
 * 프로그램의 목적 및 기본 기능:
 *   - 입력받은 문자열의 부분 문자열로 만들 수 있는 모든 소수 출력
 * 프로그램 작성자: 류효정 (2024년 09월 12일)
 * 최종 수정 및 보완: 류효정 (2024년 09월 20일)
 * =================================================================================================
 * 프로그램 수정/보완 이력
 * =================================================================================================
 * 프로그램 수정/보완작업자        일자                수정/보완 내용
 * 류효정                    2024.09.12         파일 입력 및 출력 완료
 * 류효정                    2024.09.14         출력 방식 변화 (소수 개수 출력 추가)
 * 류효정                    2024.09.20         강의자료의 permutation 이용한 구현
 * =================================================================================================
 */


import java.util.*;

public class PrimeNumber {

    int count = 0;
    HashSet<Integer> subset = new HashSet<>();
    List<Integer> sortedSubset = new ArrayList<Integer>();

    //Permutation 함수를 사용한 구현, swap을 사용하는 것이 공간적인 측면서 보다 효율적이라 판단됨.
    public void Permutation(char[] A, int k){
        String perm  = "";

        if(k== A.length -1){
            for(int i = 0; i < A.length; i++)
            {
                perm += A[i];
                subset.add(Integer.valueOf(perm));
            }

        }
        else{
            for (int i = k; i < A.length; i++){
                swap(A, i, k);
                Permutation(A, k+1);
                swap(A, i, k);
            }
        }
    }

    private void swap(char[] A, int i, int k){ char temp = A[k]; A[k] = A[i]; A[i] = temp; }

    public void findPrimeInSubset (HashSet<Integer> subset){
        for (int number : subset) {
            if (isPrime(number)) {
                sortedSubset.add(number);
                count++;
            }
        }
        Collections.sort(sortedSubset);
        //return sortedSubset;
    }

    public static boolean isPrime(int n) {
        if (n <= 1) return false; // 1과 0은 소수가 아님
        if (n <= 3) return true;  // 2와 3은 소수임

        // 2와 3으로 나눠지는 경우 소수가 아님
        if (n % 2 == 0 || n % 3 == 0)
            return false;

        // i부터 squrt(n)까지(에라토스테네스의 체), 예외 처리를 한 0123과 2로 나눠지는 경우인 4를 제외하고
        // 5부터 시작하여 6단위로 증가하면서 검사 (6k+2, 6k+5인 경우만 판별)
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0)
                return false;
        }

        return true;
    }

    public static void main(String[] args) {

        PrimeNumber driver = new PrimeNumber();

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Enter the number string: ");
        String numbers = scanner.nextLine();
        scanner.close();

        char[] numberC = numbers.toCharArray();

        //driver.selectSubset("",numbers);
        driver.Permutation(numberC, 0);
        driver.findPrimeInSubset(driver.subset);

        System.out.println("Primes: " + driver.count + "개 " + driver.sortedSubset);
    }
}
