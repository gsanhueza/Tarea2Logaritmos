package main;

public class SuffixTree {
	State root;
	private String text;

	public SuffixTree(String receivedText) {
		this.text = receivedText;
	}

	public SuffixTree createSuffixTree() {
		State bottom;
		State s;
		int k = 0;
		Pair s_v;

		root = new State();
		bottom = new State();
		for (int  i = 0; i < text.length(); i++ ){
			bottom.addTransition( i, i, root );
		}
		root.sLink = bottom;
		s = root;
		s_v = Pair(s,v);
		for ( int i = 0; i < text.length(); i++ ){
			s_v = upDate( s, k, i);
			s_v = canonize( s, k, i);
		}
		return s_v.getState();
	}

	public Pair upDate(State s, int k, int i){
		State oldr = root;
		Pair end_r = test_and_split(s, k , i-1, text.charAt(i));
		State end = end_r.getState();
		int r = end_r; 
		while(!end_r.)

	}


	public String[] search(String word) {
		String[] ans = {"a", "b", "c"};
		// TODO Retornar lo que corresponde a búsqueda
		return ans;
	}

}
