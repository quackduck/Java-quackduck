import java.util.Scanner;



public class RockPaperScissors {
	public Scanner in = new Scanner(System.in);
	public String[] things = {"rock", "paper", "scissors"};

	public static void main(String[] args) {
		new RockPaperScissors().go();

	}

	public void go () {
		while (true) {
			String human = in.nextLine();
			String computer = generate();
			if (human.equals("exit")) {break;}
			if (human.equalsIgnoreCase(things[0]) || human.equalsIgnoreCase(things[1]) || human.equalsIgnoreCase(things[2])) {
				System.out.println(computer);
				System.out.println(eval(computer, human));
			}
			else {System.out.println("What?");}
		}
	}

	public String generate() {
		return things[(int) Math.floor((3 * Math.random()))];
	}

	public String eval(String computerChoice , String humanChoice) {
		if (computerChoice.equalsIgnoreCase(humanChoice)) {return "We both selected the same thing";}
		else if (computerChoice.equalsIgnoreCase(things[0]) && humanChoice.equalsIgnoreCase(things[1])) { return "You win!";}
		else if (humanChoice.equalsIgnoreCase(things[0]) && computerChoice.equalsIgnoreCase(things[1])) { return "You lose";}
		else if (computerChoice.equalsIgnoreCase(things[1]) && humanChoice.equalsIgnoreCase(things[2])) {return "You win!";}
		else if (humanChoice.equalsIgnoreCase(things[1]) && computerChoice.equalsIgnoreCase(things[2])) {return "You lose";}
		else if (computerChoice.equalsIgnoreCase(things[2]) && humanChoice.equalsIgnoreCase(things[0])) {return "You win!";}
		else if (humanChoice.equalsIgnoreCase(things[2]) && computerChoice.equalsIgnoreCase(things[0])) {return "You lose";}
		else {return "YOU ARE STUPID";} 
	}
}
