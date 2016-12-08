package main;

public class SuffixTree {

	private String text;

	public SuffixTree(String receivedText) {
		this.text = receivedText;
	}

	public SuffixTree createSuffixTree() {
		State root;
		State bottom;
		State s;
		int k = 0;
		Pair s_v;

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


	public String[] search(String word) {
		String[] ans = {"a", "b", "c"};
		// TODO Retornar lo que corresponde a búsqueda
		return ans;
	}

}
