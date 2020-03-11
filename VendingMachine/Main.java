import java.text.DecimalFormat;
import java.util.*;

public class Main 
{

	public static void main(String[] args)
	{
		Background func = new Background();
		DecimalFormat dec = new DecimalFormat("#0.00");
		boolean b = false;
		String choice;
		System.out.println("You may enter 'Product'|'Bank'|'Savings' while choosing an item to check condition\n");
		do
		{
				System.out.println("	Welcome to the Vending Machine Program\nProduct	    Price    Code");
				System.out.println("-------------------------");
				for (int i = 0; i < func.x.length; i++)
				{
					System.out.println(func.x[i].name + "   $" + dec.format(func.x[i].price) + "     " + func.x[i].code);
				}
				System.out.println("\nChoose an item : ");
				Scanner input = new Scanner(System.in);
				choice = input.nextLine();
				b = func.sanityCheck(func, choice);
				while (b == false)
				{
					if (choice.contentEquals("Product") || choice.contentEquals("Bank") || choice.contentEquals("Savings"))
					{
						System.out.println("\nChoose an item : ");
						choice = input.nextLine();
						b = func.sanityCheck(func, choice);
					}
					else
					{
						System.out.println("Code Not Found!! Enter A New Code : ");
						choice = input.nextLine();
						b = func.sanityCheck(func, choice);
					}
				}
				func.adder(func, input);
		}
		while(true);
	}
}
