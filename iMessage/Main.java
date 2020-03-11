import java.util.*;
import java.io.InputStream;

public class Main
{
	public static void main(String[] args)
	{
		int numUsers;
		AddUser userAdd = new AddUser();
		Scanner input = new Scanner (System.in);
		System.out.println("Enter the number of users : ");
		numUsers = input.nextInt();

		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				for (int i = 1; i <= numUsers; i++)
				{
					new MessageBox(i, userAdd);
	            	MessageBox.textBox(i, userAdd);
	            }
			}
		});
	}
}
