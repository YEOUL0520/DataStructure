public class SelectionSort {
    public void sort(int[] A){
        for (int i = 0; i < A.length -1; i++){ //배열 인덱스 0부터 끝에서 두번째까지(맨 끝은 비교 필요x)
            int min = i; //min값의 인덱스를 i로 설정
            for (int k = i+1; k <A.length; k++) //배열 인덱스 1부터 마지막까지 비교
                if (A[k] < A[min]) //k값이 더 작을 경우
                    min = k; //min인덱스를 k로 지정
            int temp = A[i]; //min값과 i값을 swap (제일 작은 값이 맨 앞으로 오게 됨)
            A[i] = A[min];
            A[min] = temp;
        }
    }

    public static void main(String[] args){
        SelectionSort driver = new SelectionSort();
        int[] input = {10, 4, 5, 2, 1, 8, 3, 6};
        driver.sort(input);
        for (int i = 0; i < input.length; i++)
            System.out.print(input[i] + "");
    }
}
