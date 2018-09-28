package q3184;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int R,C,num_S=0,num_W=0;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	static char[][] map;
	static boolean[][] visited;
	
	public static void BFS(int x, int y) {
		int i,j,ax,ay;
		int num_o = 0;
		int num_v = 0;
		
		Queue<Node> q = new LinkedList<>();				
		if(map[x][y] == 'v') num_v++;
		if(map[x][y] == 'o') num_o++;
		q.add(new Node(x,y));
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			Node e = q.poll();
			
			for(i=0;i<4;i++) {
				ax = e.x + dx[i];
				ay = e.y + dy[i];
				
				if(ax < 0 || ax >= R || ay < 0 || ay >=C) continue;
				if(visited[ax][ay]) continue;
				if(map[ax][ay] == '#') continue;
				
				if(map[ax][ay] == 'v') num_v++;
				if(map[ax][ay] == 'o') num_o++;
				q.add(new Node(ax,ay));
				visited[ax][ay] = true;
			}
		}
		
		if(num_o > num_v) num_S += num_o;
		else num_W += num_v;
		
	}
	
	public static void main(String[] args) throws IOException {
		int i,j;
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		
		for(i=0;i<R;i++) {
			String line = in.readLine();
			for(j=0;j<C;j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		for(i=0;i<R;i++) {
			for(j=0;j<C;j++) {
				if(map[i][j] == '#') continue;
				if(visited[i][j]) continue;
				BFS(i,j);
			}
		}
//		System.out.println(num_S + " " + num_W);
		out.write(num_S + " " + num_W);
		out.flush();
		
		in.close();
	}
	
	static class Node {
		int x, y;
		
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
