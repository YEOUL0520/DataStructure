public class BinarySearch {
    //비교 함수를 구현
    private int compare(int x, int y) {
        return (x < y) ? -1 : (x > y) ? 1 : 0; //x가 y보다 작을 경우(if) -1, 외의 경우 중 x가 y보다 클 경우(else if) 1, 그 외의 경우(else) 0 반환
    }
    public int bsearch(int[] A, int key, int left, int right){
        if (left<=right){
            int middle = (left+right) / 2; //중간에 위치한 값을 middle값으로 지정
            switch(compare(A[middle], key)){
                case -1: return bsearch(A, key, middle+1, right); //key값이 더 클때
                case 1: return bsearch(A, key, left, middle-1); //middle값이 더 클때
                case 0: return middle; //key값과 middle값이 같을때
            }
        }
        return -1; //not found
    }

    public static void main(String[] args){
        BinarySearch driver = new BinarySearch();
        int[] input = {1,3,4,5,7,10};
        int findnumber = 7;
        System.out.print("Key index of the findnumber "+findnumber+" is "+driver.bsearch(input, findnumber, 0, input.length-1));
    }
}
