import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static class Station implements Comparable<Station> {
		public int location;
		public int cost;
		public Station pred;
		public int length;

		public Station(int location, int cost) {
			this.location = location;
			this.cost = cost;
			this.pred = null;
			this.length = 0;
		}

		public int compareTo(Station s1) {
			return this.cost - s1.cost;
		}

	}

	public static void main(String args[]) {

		try {

			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String[] line1 = in.readLine().split("\\s+");// list of all stations
			int n = Integer.parseInt(line1[0]);// total possible stations
			int M = Integer.parseInt(line1[1]);// total length of highway
			int m = Integer.parseInt(line1[2]);// space between two stations
			PriorityQueue<Station> min = new PriorityQueue<Station>(n);
			Station[] Stations = new Station[n];
			for (int i = 0; i < n; i++) {
				String[] line = in.readLine().split("\\s");
				Station s1 = new Station(Integer.parseInt(line[0]), 
						Integer.parseInt(line[1]));
				Stations[i] = s1;
			}
			for (int j = 0; j < n; j++) {
				if (Stations[j].location <= m) {
					min.add(Stations[j]);
					Stations[j].pred = Stations[j];
				} else {
					Station sm = min.peek();
					while (sm.location < Stations[j].location - m) {
						min.remove();
						sm=min.peek();
					}
					Stations[j].cost = sm.cost + Stations[j].cost;
					Stations[j].pred = sm;
					min.add(Stations[j]);
				}
			}
			Station ms = Stations[n - 1];
			for (int i = n - 1; i > 0; i--) {
				if (Stations[i].location >= M - m) {
					if (Stations[i].cost <= ms.cost) {
						ms = Stations[i];
					}
				}
			}
			System.out.println(ms.cost);
			String s = ms.location + "";
			while (ms.pred != ms) {
				s = ms.pred.location + " " + s;
				ms = ms.pred;
			}
			System.out.println(s);

		} catch (IOException io) {
			io.printStackTrace();
		}
	}
}