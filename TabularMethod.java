import java.util.Scanner;
import java.util.Arrays;

public class TabularMethod {
	int numMinterms, numDontcares;
	int[] minterms, dontcares;
	int[][][] binTerms;
	
	public void inputTerms() {
		Scanner input = new Scanner(System.in);
		
		System.out.println("minterm의 개수를 입력하세요: ");
		numMinterms = input.nextInt();
		minterms = new int[numMinterms];
		
		System.out.println("minterm을 입력하세요: ");
		for(int i=0;i<numMinterms;i++) {
			minterms[i] = input.nextInt();
		}
		
		System.out.println("don't care의 개수를 입력하세요: ");
		numDontcares = input.nextInt();
		dontcares = new int[numDontcares];
		
		System.out.println("don't care을 입력하세요: ");
		for(int i=0;i<numDontcares;i++) {
			dontcares[i] = input.nextInt();
		}
	}
	
	public void setBinTerms() {
		int[] m = {1}; //같은 2차원 배열에 들어있는 배열이 minterm의 이진수값을 나타냄을 알려줌
		int[] d = {0}; //같은 2차원 배열에 들어있는 배열이 dontcare의 이진수값을 나타냄을 알려줌
		int[] numOfOne = new int[1];
		binTerms = new int[numMinterms + numDontcares][3][]; //[[minterm인지 dontcare인지를 나타내는 0 또는 1의 값,[이진수],1의 개수]]
		
		for(int i=0;i<numMinterms;i++) {
			int[] bin = new int[4];
			int idx = 3;
			int numOne = 0;
			int decTerm = minterms[i];
			
			while(decTerm > 0) { //bin배열에 minterms[i] 이진수 표현 저장.
				bin[idx] = decTerm%2; 
				if(bin[idx] == 1) {
					numOne++; //term의 이진수의 1의 개수
				}
				decTerm /= 2;
				idx--;
			}
			while(idx >= 0) {
				bin[idx] = 0; //남은 2진수 앞자리 0으로 채워줌
				idx--;
			}
			numOfOne[0] = numOne;
			binTerms[i][0] = m;
			binTerms[i][1] = bin;
			binTerms[i][2] = numOfOne;
		}
		
		for(int i=0;i<numDontcares;i++) {
			int[] bin = new int[4];
			int idx = 3;
			int numOne = 0;
			int decTerm = dontcares[i];
			
			while(decTerm > 0) {
				bin[idx] = decTerm%2;
				if(bin[idx] == 1) {
					numOne++;
				}
				decTerm /= 2;
				idx--;
			}
			while(idx >= 0) {
				bin[idx] = 0;
				idx--;
			}
			numOfOne[0] = numOne;
			binTerms[numMinterms + i][0] = d;
			binTerms[numMinterms + i][1] = bin;
			binTerms[numMinterms + i][2] = numOfOne;
		}
	}

	public static void main(String[] args) {
		TabularMethod tm = new TabularMethod();
		tm.inputTerms();
		tm.setBinTerms();
		for(int i=0;i<tm.numMinterms + tm.numDontcares;i++) { //test
			System.out.println(Arrays.toString(tm.binTerms[i][1]));
		}
		
	}

}
