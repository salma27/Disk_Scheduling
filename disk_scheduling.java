import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class disk_scheduling {
	public static String out = "";

	public static String main( String fileName) throws IOException {
		//File file=new File(fileName);   
		//FileInputStream fis=new FileInputStream(file);
		
		final Scanner sc = new Scanner(new File( fileName ));
		//System.out.println("Please enter the number of requests: ");
		int num = sc.nextInt();
		
		
		System.out.println("Please enter your I/O Requests : ");
		int req = 0;
		final Vector<Integer> requests = new Vector<Integer>();
		for(int i = 0 ; i < num ; i++) {
			//sc.reset();
			req = sc.nextInt(); 
			
			requests.add(req);
		}
		System.out.println("Please Enter the Initial head start cylinder: ");
		//sc.reset();
		int head = sc.nextInt();
		Output output = FCFS(head, requests);
		System.out.println("The sequence of head movement using FCFS:");
		out += "The sequence of head movement using FCFS: ";
		for(int i = 0 ; i < output.sequence.size() ; i++) {
			System.out.print(output.sequence.elementAt(i));
			out += output.sequence.elementAt(i);
			if(i != output.sequence.size() - 1) {
				System.out.print(" , ");
				out += " , ";
			}
		}
		System.out.println();
		out += "\n";
		System.out.println("Total Head Movements using FCFS: " + output.totalHeadMovements);
		System.out.println("---------------------------------------------------------------");
		out += "Total Head Movements using FCFS: " + output.totalHeadMovements + "\n";
		out += "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
		
		output = SSTF(head, requests);
		System.out.println("The sequence of head movement using SSTF:");
		out += "The sequence of head movement using SSTF: ";
		for(int i = 0 ; i < output.sequence.size() ; i++) {
			System.out.print(output.sequence.elementAt(i));
			out += output.sequence.elementAt(i);
			if(i != output.sequence.size() - 1) {
				System.out.print(" , ");
				out += " , ";
			}
		}
		System.out.println();
		out += "\n";
		System.out.println("Total Head Movements using SSTF: " + output.totalHeadMovements);
		System.out.println("---------------------------------------------------------------");
		out += "Total Head Movements using SSTF: " + output.totalHeadMovements + "\n";
		out += "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
		
		output = SCAN(head, requests);
		System.out.println("The sequence of head movement using SCAN:");
		out += "The sequence of head movement using SCAN: ";
		for(int i = 0 ; i < output.sequence.size() ; i++) {
			System.out.print(output.sequence.elementAt(i));
			out += output.sequence.elementAt(i);
			if(i != output.sequence.size() - 1) {
				System.out.print(" , ");
				out += " , ";
			}
		}
		System.out.println();
		out += "\n";
		System.out.println("Total Head Movements using SCAN: " + output.totalHeadMovements);
		out += "Total Head Movements using SCAN: " + output.totalHeadMovements + "\n";
		System.out.println("---------------------------------------------------------------");
		out += "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
		
		output = CSCAN(head, requests);
		System.out.println("The sequence of head movement using C-SCAN:");
		out += "The sequence of head movement using C-SCAN: ";
		for(int i = 0 ; i < output.sequence.size() ; i++) {
			System.out.print(output.sequence.elementAt(i));
			out += output.sequence.elementAt(i);
			if(i != output.sequence.size() - 1) {
				System.out.print(" , ");
				out += " , ";
			}
		}
		System.out.println();
		out += "\n";
		System.out.println("Total Head Movements using C-SCAN: " + output.totalHeadMovements);
		out += "Total Head Movements using C-SCAN: " + output.totalHeadMovements + "\n";
		System.out.println("---------------------------------------------------------------");
		out += "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
		
		/*output = CLOOK(head, requests);
		System.out.println("The sequence of head movement using C-LOOK:");
		out += "The sequence of head movement using C-LOOK: ";
		for(int i = 0 ; i < output.sequence.size() ; i++) {
			System.out.print(output.sequence.elementAt(i));
			out += output.sequence.elementAt(i);
			if(i != output.sequence.size() - 1) {
				System.out.print(" , ");
				out += " , ";
			}
		}
		System.out.println();
		out += "\n";
		System.out.println("Total Head Movements using C-LOOK: " + output.totalHeadMovements);
		out += "Total Head Movements using C-LOOK: " + output.totalHeadMovements + "\n";
		System.out.println("---------------------------------------------------------------");
		out += "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
		*/
		
		output = newOptimized(requests);
		System.out.println("The sequence of head movement using the new optimized algorithm:");
		out += "The sequence of head movement using the new optimized algorithm: ";
		for(int i = 0 ; i < output.sequence.size() ; i++) {
			System.out.print(output.sequence.elementAt(i));
			out += output.sequence.elementAt(i);
			if(i != output.sequence.size() - 1) {
				System.out.print(" , ");
				out += " , ";
			}
		}
		System.out.println();
		out += "\n";
		System.out.println("Total Head Movements using the new optimized algorithm: " + output.totalHeadMovements);
		out += "Total Head Movements using the new optimized algorithm: " + output.totalHeadMovements + "\n";
		System.out.println("---------------------------------------------------------------");
		out += "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
		//sc.close();
		return out;
		
	}

	/*public static Output CLOOK(final int head, final Vector<Integer> requests) {
		final Output output = new Output();
		return output;
	}
*/
	public static Output CSCAN(int head, final Vector<Integer> requests) {
		final Vector<Integer> sequence = new Vector<Integer>();
		final Vector<Integer> tmp = new Vector<Integer>();
		Vector<Integer> vec = new Vector<Integer>();

		int counter = 0;
		int index = 0;
		int min = Integer.MAX_VALUE;
		int sum = 0;

		sequence.add(head);
		while (counter != requests.size()) {
			min = Integer.MAX_VALUE;
			index = 0;
			vec.clear();
			tmp.clear();
			for (int i = 0; i < requests.size(); i++)
				if (!sequence.contains(requests.get(i)))
					tmp.add(requests.get(i));
			vec = getMax(head, tmp);
			// vec = getMin(head, tmp);
			if (vec.size() > 0) {
				for (int i = 0; i < vec.size(); i++) {
					if (min > Math.abs(head - vec.get(i))) {
						min = Math.abs(head - vec.get(i));
						index = i;
					}
				}
				head = vec.get(index);
				sequence.add(head);
				counter++;
			} else {
				if (sequence.lastElement() != 199)
					sequence.add(199);
				sequence.add(0);
				head = 0;
				sum++;
				/*
				 * if(sequence.lastElement() != 0) sequence.add(0); sequence.add(199); head =
				 * 199;
				 */
			}
		}
		final Output output = new Output(sequence);
		System.out.println("C-SCAN head movements:" + (output.totalHeadMovements - sum * 199));
		out += "C-SCAN head movements:" + (output.totalHeadMovements - sum * 199) + "\n";
		return output;

	}

	public static Output SCAN(int head, final Vector<Integer> requests) {
		final Vector<Integer> sequence = new Vector<Integer>();
		final Vector<Integer> seq = new Vector<Integer>();
		final Vector<Integer> tmp = new Vector<Integer>();
		Vector<Integer> vec = new Vector<Integer>();

		int counter = 0;
		boolean down = true;
		int index = 0;
		int min = Integer.MAX_VALUE;

		sequence.add(head);
		seq.add(head);
		while (counter != requests.size()) {
			min = Integer.MAX_VALUE;
			index = 0;
			vec.clear();
			tmp.clear();
			for (int i = 0; i < requests.size(); i++)
				if (!sequence.contains(requests.get(i)))
					tmp.add(requests.get(i));
			if (down) {
				vec = getMin(head, tmp);
				if (vec.size() > 0) {
					for (int i = 0; i < vec.size(); i++) {
						if (min > Math.abs(head - vec.get(i))) {
							min = Math.abs(head - vec.get(i));
							index = i;
						}
					}
					head = vec.get(index);
					sequence.add(head);
					seq.add(head);
					counter++;
				} else {
					down = false;
					if (!down && counter != requests.size()) {
						if (sequence.lastElement() != 0)
							sequence.add(0);
						head = 0;
					}
				}
			} else {
				vec = getMax(head, tmp);
				if (vec.size() > 0) {
					for (int i = 0; i < vec.size(); i++) {
						if (min > Math.abs(head - vec.get(i))) {
							min = Math.abs(head - vec.get(i));
							index = i;
						}
					}
					head = vec.get(index);
					sequence.add(head);
					seq.add(head);
					counter++;
				} else {
					down = true;
					if (down && counter != requests.size()) {
						if (sequence.lastElement() != 199)
							sequence.add(199);
						head = 199;
					}
				}
			}
		}
		Output output = new Output(seq);
		System.out.println("SCAN head movements : " + output.totalHeadMovements);
		out += "SCAN head movements : " + output.totalHeadMovements + "\n";
		output = new Output(sequence);
		return output;
	}

	public static Vector<Integer> getMin(final int head, final Vector<Integer> requests) {
		final Vector<Integer> tmp = new Vector<Integer>();
		for (int i = 0; i < requests.size(); i++)
			if (requests.get(i) < head)
				tmp.add(requests.get(i));
		return tmp;
	}

	public static Vector<Integer> getMax(final int head, final Vector<Integer> requests) {
		final Vector<Integer> tmp = new Vector<Integer>();
		for (int i = 0; i < requests.size(); i++)
			if (requests.get(i) > head)
				tmp.add(requests.get(i));
		return tmp;
	}

	public static Output newOptimized(final Vector<Integer> requests) {
		final Vector<Integer> tmp = new Vector<Integer>();
		tmp.add(0);
		for (int i = 0; i < requests.size(); i++)
			tmp.add(requests.get(i));
		Collections.sort(tmp);
		final Output output = new Output(tmp);
		return output;
	}

	public static Output SSTF(int head, final Vector<Integer> requests) {
		final Vector<Integer> tmp = new Vector<Integer>();
		for (int i = 0; i < requests.size(); i++)
			tmp.add(requests.elementAt(i));
		final Vector<Integer> sequence = new Vector<Integer>();
		sequence.add(head);
		int min = Integer.MAX_VALUE;
		int index = 0;
		while (sequence.size() - 1 != requests.size()) {
			index = 0;
			min = Integer.MAX_VALUE;
			for (int i = 0; i < tmp.size(); i++) {
				if (min > Math.abs(head - tmp.get(i))) {
					min = Math.abs(head - tmp.get(i));
					index = i;
				}
			}

			head = tmp.get(index);
			sequence.add(head);
			tmp.clear();
			for (int i = 0; i < requests.size(); i++)
				if (!sequence.contains(requests.get(i)))
					tmp.add(requests.get(i));

		}
		final Output output = new Output(sequence);
		return output;
	}

	public static Output FCFS(final int head, final Vector<Integer> requests) {
		final Vector<Integer> sequence = new Vector<Integer>();
		sequence.add(head);
		for (int i = 0; i < requests.size(); i++) {
			sequence.add(requests.elementAt(i));
		}
		final Output output = new Output(sequence);
		return output;
	}
}

class Output {
	Vector<Integer> sequence;
	int totalHeadMovements;

	Output() {
		sequence = new Vector<Integer>();
		totalHeadMovements = 0;
	}

	Output(final Vector<Integer> sequence) {
		this.sequence = sequence;
		totalHeadMovements = 0;
		for(int i = 0 ; i < sequence.size() - 1 ; i++) {
			totalHeadMovements += Math.abs(sequence.elementAt(i) - sequence.elementAt(i + 1));
		}
	}
	
}