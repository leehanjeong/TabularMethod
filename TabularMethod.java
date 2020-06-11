import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class TabularMethod {
	int numMinterms, numDontcares;
	int[] minterms, dontcares;
	int[][][] binTerms;
	int[][][] equalNumOne = new int[5][][];
	public void inputTerms() {
		Scanner input = new Scanner(System.in);
		
		System.out.println("minterm의 갯수를 입력하세요: ");
		numMinterms = input.nextInt();
		minterms = new int[numMinterms];
		
		System.out.println("minterm(0~15 사이)을 입력하세요: "); //4 variables
		for(int i=0;i<numMinterms;i++) {
			minterms[i] = input.nextInt();
		}
		
		System.out.println("don't care의 갯수를 입력하세요: ");
		numDontcares = input.nextInt();
		dontcares = new int[numDontcares];
		
		System.out.println("don't care(0~15 사이)을 입력하세요: ");
		for(int i=0;i<numDontcares;i++) {
			dontcares[i] = input.nextInt();
		}
		
		input.close();
	}
	
	public void setBinTerms() {
		int[] m = {1}; //같은 2차원 배열에 들어있는 배열이 minterm의 이진수값을 나타냄을 알려줌
		int[] d = {0}; //같은 2차원 배열에 들어있는 배열이 dontcare의 이진수값을 나타냄을 알려줌
		//int[] numOfOne = {0};
		binTerms = new int[numMinterms + numDontcares][3][]; //[[m or d, bin, numOfOne]]
		
		for(int i=0;i<numMinterms;i++) {
			int[] bin = new int[4]; //4 variables
			int[] numOfOne = {0};
			int idx = 3;
			int decTerm = minterms[i];
			
			while(decTerm > 0) { //bin배열에 minterms[i] 이진수 표현 저장.
				bin[idx] = decTerm%2; 
				if(bin[idx] == 1) {
					numOfOne[0]++; //term의 이진수의 1의 개수
				}
				decTerm /= 2;
				idx--;
			}
			while(idx >= 0) {
				bin[idx] = 0; //남은 2진수 앞자리 0으로 채워줌
				idx--;
			}
			binTerms[i][0] = m;
			binTerms[i][1] = bin;
			binTerms[i][2] = numOfOne;
		}
		
		for(int i=0;i<numDontcares;i++) {
			int[] bin = new int[4];
			int[] numOfOne = {0};
			int idx = 3;
			int decTerm = dontcares[i];
			
			while(decTerm > 0) {
				bin[idx] = decTerm%2;
				if(bin[idx] == 1) {
					numOfOne[0]++;
				}
				decTerm /= 2;
				idx--;
			}
			while(idx >= 0) {
				bin[idx] = 0;
				idx--;
			}
			binTerms[numMinterms + i][0] = d;
			binTerms[numMinterms + i][1] = bin;
			binTerms[numMinterms + i][2] = numOfOne;
		}
	}
	
	public void equalNumOfOne() {
		int[] cnt = {0,0,0,0,0}; // 1의 개수가 0~4인 term의 개수를 저장하는 배열.
		for(int i=0;i<binTerms.length;i++) {
			cnt[binTerms[i][2][0]]++;
		}
		equalNumOne[0] = new int[cnt[0]][4]; //cnt를 이용하여 equalNumOne 생성
		equalNumOne[1] = new int[cnt[1]][4];
		equalNumOne[2] = new int[cnt[2]][4];
		equalNumOne[3] = new int[cnt[3]][4];
		equalNumOne[4] = new int[cnt[4]][4];
		
		for(int j=0;j<binTerms.length;j++) {
			switch(binTerms[j][2][0]) { //binTerms의 1의 개수를 나타내는 배열의 값을 이용하여 equalNumOne에 1의 개수 같은 애들끼리 같은 배열에 넣음.
				case 0: 
					equalNumOne[0][--cnt[0]] = binTerms[j][1];
					break;
				case 1:
					equalNumOne[1][--cnt[1]] = binTerms[j][1];
					break;
				case 2:
					equalNumOne[2][--cnt[2]] = binTerms[j][1];
					break;
				case 3:
					equalNumOne[3][--cnt[3]] = binTerms[j][1];
					break;
				case 4:
					equalNumOne[4][--cnt[4]] = binTerms[j][1];
					break;	
			}
		}
		
		System.out.print("group0 : ");
		for(int k=0;k<equalNumOne[0].length;k++) {
			System.out.print(Arrays.toString(equalNumOne[0][k]));
		}
		System.out.println();
		System.out.print("group1 : ");
		for(int k=0;k<equalNumOne[1].length;k++) {
			System.out.print(Arrays.toString(equalNumOne[1][k]));
		}
		System.out.println();
		System.out.print("group2 : ");
		for(int k=0;k<equalNumOne[2].length;k++) {
			System.out.print(Arrays.toString(equalNumOne[2][k]));
		}
		System.out.println();
		System.out.print("group3 : ");
		for(int k=0;k<equalNumOne[3].length;k++) {
			System.out.print(Arrays.toString(equalNumOne[3][k]));
		}
		System.out.println();
		System.out.print("group4 : ");
		for(int k=0;k<equalNumOne[4].length;k++) {
			System.out.print(Arrays.toString(equalNumOne[4][k]));
		}
	}
	
	public void merge() {
		while(true) {
			int idx = 0;
			int tmp;
			ArrayList<ArrayList<ArrayList<Integer>>> mergeList = new ArrayList<ArrayList<ArrayList<Integer>>>();
			for(int i=0;i<equalNumOne.length;i++) {
				for(int k=0;k<equalNumOne[i].length;k++) {
					
				}
			}
			//ArrayList<ArrayList<Integer> > TablePI = new ArrayList<ArrayList<Integer>>();
			while(idx<equalNumOne.length) {
				for(int i=0;i<equalNumOne[idx].length;i++) {
					for(int j=0;j<equalNumOne[idx+1].length;j++) {
						int diff = 0;
						for(int k=0;k<4;k++) {
							if(equalNumOne[idx][i][k] != equalNumOne[idx+1][j][k]) {
								diff++;
								tmp = k;
							}
						}
						if(diff == 1) {
							//Array 크기 어떻게 바꾸지... Array list는 다차원으론 이용 불가...
						}
					}
				}
					
			}
		}
	}
	//1개수 같은 애들끼리 묶음 (equalNumOne에 묶음)
	//1개수 하나 차이나는 애들 중에 한자리만 다르고 다 같은애들 merge.
	public static void main(String[] args) {
		TabularMethod tm = new TabularMethod();
		tm.inputTerms();
		tm.setBinTerms();
		tm.equalNumOfOne();
	}

}
