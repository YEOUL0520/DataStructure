public class SelectionSort {
    public void sort(int[] A){
        for (int i = 0; i < A.length -1; i++){
            int min = i;
            for (int k = i+1; k <A.length; k++)
                if (A[k] < A[min])
                    min = k;
            int temp = A[i];
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
