import java.util.Scanner;
import java.util.Vector;

public class disk_scheduling {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the number of requests: ");
		int num = sc.nextInt();
		System.out.println("Please enter your I/O Requests : ");
		int req = 0;
		Vector<Integer> requests = new Vector<Integer>();
		for(int i = 0 ; i < num ; i++) {
			sc.reset();
			req = sc.nextInt();
			requests.add(req);
		}
		System.out.println("Please Enter the Initial head start cylinder: ");
		sc.reset();
		int head = sc.nextInt();
		Output output = FCFS(head, requests);
		System.out.println("The sequence of head movement using FCFS:");
		for(int i = 0 ; i < output.sequence.size() ; i++) {
			System.out.print(output.sequence.elementAt(i));
			if(i != output.sequence.size() - 1)
				System.out.print(" , ");
		}
		System.out.println();
		System.out.println("Total Head Movements using FCFS: " + output.totalHeadMovements);
		
		output = SSTF(head, requests);
		System.out.println("The sequence of head movement using SSTF:");
		for(int i = 0 ; i < output.sequence.size() ; i++) {
			System.out.print(output.sequence.elementAt(i));
			if(i != output.sequence.size() - 1)
				System.out.print(" , ");
		}
		System.out.println();
		System.out.println("Total Head Movements using SSTF: " + output.totalHeadMovements);
		
	}
	public static Output SSTF(int head, Vector<Integer> requests) {
		Vector<Integer> tmp = new Vector<Integer>();
		for(int i = 0 ; i < requests.size() ; i++)
			tmp.add(requests.elementAt(i));
		Vector<Integer> sequence = new Vector<Integer>();
		sequence.add(head);
		int min = Integer.MAX_VALUE;
		int index = 0;
		while(sequence.size() - 1 !=  requests.size()) {
			index = 0;
			min = Integer.MAX_VALUE;
			for(int i = 0 ; i < tmp.size() ; i++) {
				if(min > Math.abs(head - tmp.get(i))) {
					min = Math.abs(head - tmp.get(i));
					index = i;
				}
			}
			
			head = tmp.get(index);
			sequence.add(head);
			tmp.clear();
			for(int i = 0 ; i < requests.size() ; i++)
				if(!sequence.contains(requests.get(i)))
					tmp.add(requests.get(i));
				
			
		}
		Output output = new Output(sequence);
		return output;
	}
	public static Output FCFS(int head, Vector<Integer> requests) {
		Vector<Integer> sequence = new Vector<Integer>();
		sequence.add(head);
		for(int i = 0 ; i < requests.size() ; i++) {
			sequence.add(requests.elementAt(i));
		}
		Output output = new Output(sequence);
		return output;
	}
}
class Output{
	Vector<Integer> sequence;
	int totalHeadMovements;
	Output(){
		sequence = new Vector<Integer>();
		totalHeadMovements = 0;
	}
	Output(Vector<Integer> sequence){
		this.sequence = sequence;
		totalHeadMovements = 0;
		for(int i = 0 ; i < sequence.size() - 1 ; i++) {
			totalHeadMovements += Math.abs(sequence.elementAt(i) - sequence.elementAt(i + 1));
		}
	}
}