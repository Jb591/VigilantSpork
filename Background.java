package VenMachine;
import java.util.*;

public class Background
{
	Product[] x;
	Bank[] y;
	public Background()
	{
		x = setProduct();
		y = setBank();
	}

	public Product[] setProduct()
	{
		x = new Product[5];
		x[0] = new Product("Coke", 1.50, 10, "A1");
		x[1] = new Product("Dr.Pepper", 1.50, 10, "B2");
		x[2] = new Product("Monster", 1.75, 10, "C3");
		x[3] = new Product("Water", 2.00, 10, "D4");
		x[4] = new Product("Sprite", 1.75, 10, "E5");
		return x;
	}
	public Bank[] setBank()
	{
		y = new Bank[4];
		y[0] = new Bank(10, .25, "Quarters");
		y[1] = new Bank(10, .10, "Dimes");
		y[2] = new Bank(10, .05, "Nickels");
		y[3] = new Bank(10, .01, "Pennies");
		return y;
	}
	public void print()
	{
		for (int i = 0; i < x.length; i++)
		{
			System.out.println((i+1) + ") " + x[i].name + " $" + x[i].price);
		}
	}
}
