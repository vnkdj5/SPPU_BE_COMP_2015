class state {
	int arr[];
	state paraent;
	int h = 0;

	state(state s1) {
		this.arr = new int[9];
		for (int i = 0; i < s1.arr.length; i++) {
			this.arr[i] = s1.arr[i];
		}

	}

	state() {
		arr = new int[9];
	}
}