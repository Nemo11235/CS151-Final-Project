public class MancalaTester {
	public static void main(String[] args) {
		Model model = new Model();
		View initiate = new View(model);
		model.attach(initiate);
		initiate.selectStone();
	}
} 