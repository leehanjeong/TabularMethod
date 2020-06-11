import java.util.Scanner;
import java.util.Arrays;

public class TabularMethod {
	int numMinterms, numDontcares;
	int[] minterms, dontcares;
	int[][][] binTerms;
	
	public void inputTerms() {
		Scanner input = new Scanner(System.in);
		
		System.out.println("minterm�� ������ �Է��ϼ���: ");
		numMinterms = input.nextInt();
		minterms = new int[numMinterms];
		
		System.out.println("minterm�� �Է��ϼ���: ");
		for(int i=0;i<numMinterms;i++) {
			minterms[i] = input.nextInt();
		}
		
		System.out.println("don't care�� ������ �Է��ϼ���: ");
		numDontcares = input.nextInt();
		dontcares = new int[numDontcares];
		
		System.out.println("don't care�� �Է��ϼ���: ");
		for(int i=0;i<numDontcares;i++) {
			dontcares[i] = input.nextInt();
		}
	}
	
	public void setBinTerms() {
		int[] m = {1}; //���� 2���� �迭�� ����ִ� �迭�� minterm�� ���������� ��Ÿ���� �˷���
		int[] d = {0}; //���� 2���� �迭�� ����ִ� �迭�� dontcare�� ���������� ��Ÿ���� �˷���
		int[] numOfOne = new int[1];
		binTerms = new int[numMinterms + numDontcares][3][]; //[[minterm���� dontcare������ ��Ÿ���� 0 �Ǵ� 1�� ��,[������],1�� ����]]
		
		for(int i=0;i<numMinterms;i++) {
			int[] bin = new int[4];
			int idx = 3;
			int numOne = 0;
			int decTerm = minterms[i];
			
			while(decTerm > 0) { //bin�迭�� minterms[i] ������ ǥ�� ����.
				bin[idx] = decTerm%2; 
				if(bin[idx] == 1) {
					numOne++; //term�� �������� 1�� ����
				}
				decTerm /= 2;
				idx--;
			}
			while(idx >= 0) {
				bin[idx] = 0; //���� 2���� ���ڸ� 0���� ä����
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
