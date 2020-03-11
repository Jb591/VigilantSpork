import java.util.*;
import java.text.DecimalFormat;

public class Background 
{
	Product[] x;
	Bank[] y;
	Choice z;
	public Background()
	{
		x = setProduct();
		y = setBank();
		z = new Choice();
	}

	//set the products by name|value|amount|code
	
	public Product[] setProduct()
	{
		x = new Product[5];
		x[0] = new Product("Coke     ", 1.50, 2, "A1");
		x[1] = new Product("Dr.Pepper", 1.50, 3, "B2");
		x[2] = new Product("Monster  ", 1.75, 4, "C3");
		x[3] = new Product("Water    ", 2.00, 3, "D4");
		x[4] = new Product("Sprite   ", 1.75, 4, "E5");
		return x;
	}
	
	//set the bank by amount|value|name
	
	public Bank[] setBank()
	{
		y = new Bank[4];
		y[0] = new Bank(10, .25, "Quarters ");
		y[1] = new Bank(10, .10, "Dimes    ");
		y[2] = new Bank(10, .05, "Nickels  ");
		y[3] = new Bank(10, .01, "Pennies  ");
		return y;
	}
	
	//Debugging Method : printVending
	
	public void printVending(Background func)
	{
		double value = getTotal(func);
		DecimalFormat dec = new DecimalFormat("#0.00");
		if (func.z.choice.contentEquals("Product"))
		{
			System.out.println("\nProduct	  Price Code Stock");
			System.out.println("--------------------------");
			for (int i = 0; i < func.x.length; i++)
			{
				System.out.println(func.x[i].name + " $" + dec.format(func.x[i].price) + "   " + func.x[i].code + "   " + func.x[i].stock);
			}
		}

		else if (func.z.choice.contentEquals("Bank"))
		{
			System.out.println("\nCurrency Stock");
			System.out.println("--------------");
			for (int i = 0; i < func.y.length; i++)
			{
				System.out.println(func.y[i].name + " " + func.y[i].num);
			}
			System.out.println("\nCurrent total : $" + dec.format(value));
		}
		else if (func.z.choice.contentEquals("Savings"))
		{
			System.out.println("Amount saved : $" + dec.format(func.z.collected));
		}
	}
	
	//Will give back coins if too much is added
	
	public void deAdder(Background func)
	{
		func.z.gTotal = 0;
		DecimalFormat dec = new DecimalFormat("#0.00");
		Background stock = new Background();
		for (int i = 0; i < func.y.length; i ++)
		{
			stock.y[i].num = 0;
		}
		while (func.z.round < 0)
		{
			for (int i = 0; i < func.y.length; i++)
			{
				
				if (func.z.total < 0 && (func.z.total + func.y[i].value) < 0)
				{
					dec.format(func.z.total);
					func.z.total = func.z.total + func.y[i].value;
					func.z.round = (int) (func.z.total * 100);
					func.z.gTotal = func.z.gTotal + stock.y[i].value;
					func.y[i].num --;
					stock.y[i].num++;
				}
			}
		}
		System.out.println("The following are to be returned : ");
		for (int i = 0; i < func.y.length; i++)
		{
			if (stock.y[i].num != 0)
			{
				System.out.println(stock.y[i].name + " : " + stock.y[i].num);
			}
		}
		System.out.println("Total Returned : " + dec.format(func.z.gTotal));
	}
	
	//will add to the bank 
	//Subtract from the stock 
	//Give back if too little is given
	//Give back if too much is given 
	
	@SuppressWarnings("resource")
	public void adder(Background func, Scanner input)
	{
		boolean check;
		String formatter;
		Background stock = new Background();
		for (int i = 0; i < func.y.length; i ++)
		{
			stock.y[i].num = 0;
		}
		input = new Scanner (System.in);
		DecimalFormat dec = new DecimalFormat("#0.00");
		int tally;
		func.z.total = func.x[func.z.index].price;
		func.z.round = (int) (func.z.total * 100);
		System.out.println("The item purchasing : " + func.x[func.z.index].name);
		System.out.println("Your Total : " + func.x[func.z.index].price);
		for (int i= 0; i < func.y.length; i++)
		{
			if (func.z.total > 0)	
			{
				formatter = func.y[i].name.replaceAll("\\s", "");
				System.out.println("\nEnter the number of " + formatter + " : ");
				tally = input.nextInt();
				check = cBank(tally);
				while (check == false)
				{
					System.out.println("You've entered too many!! Try Again : ");
					tally = input.nextInt();
					check = cBank(tally);
				}
				func.z.total = func.z.total - (tally * func.y[i].value);
				func.z.round = (int) (func.z.total * 100) ;
				stock.y[i].num = stock.y[i].num + tally;
				func.y[i].num = func.y[i].num + tally;
				if (func.z.total > (0))
				{
					System.out.println("Your new Total : " + dec.format(func.z.total));
				}
			}
			else if (func.z.total <= 0)
				{
					formatter = func.x[func.z.index].name.replaceAll("\\s", "");
					i = 4;
					System.out.println(formatter  + " has been dispensed");
				}
		}
		if (func.z.total > 0)
		{
			System.out.println("You enetered insufficient funds!! Now returning : ");
			for (int i = 0; i < func.y.length; i++)
			{
				if (stock.y[i].num != 0)
				{
					formatter = func.y[i].name.replaceAll("\\s", "");
					System.out.println(formatter + " : " + stock.y[i].num);
					func.y[i].num = func.y[i].num - stock.y[i].num;
				}
			}
		}
		else if (func.z.round < 0)
		{
			func.deAdder(func);
		}
		reset(func);
	}
	
	//The methods below are to be placed in a class that will be inherited by Background
	
	//Gathers the total that are available in the Vending Machine
	
	public double getTotal(Background func)
	{
		double total = 0; 
		for (int i = 0; i < func.y.length; i++)
		{
			total = total + (func.y[i].value * func.y[i].num);
		}
		return total;
	}
	
	//This method will prevent a crazy amount of coins to be inputed
	
	public boolean cBank(int tally)
	{
		while(tally > 10)
		{
			return false;
		}
		return true;
	}
	
	//will check for Sanity in the users choices
	
	public boolean sanityCheck(Background back, String ans)
	{
		boolean check = false;
		back.z.choice = ans;
		for (int i = 0; i < back.x.length; i++)
		{
			if (ans.equals(x[i].code))
			{
				back.z.index = i;
				back.x[i].stock--;
				return true;
			}
			else if (back.z.choice.equals("Product") || back.z.choice.contentEquals("Bank") || back.z.choice.contentEquals("Savings"))
			{
				back.printVending(back);
				return check;
			}
		}

		return check;
	}
	
	//Will reset if:
	//Out of Products
	//Out of coins
	//Has an excessive amount of coins and place in a savings
	
	public void reset(Background func)
	{
		String formatter;
		double tally = 0;
		Background rSet = new Background();
		for (int i = 0; i < func.x.length; i++)
		{
			if (func.x[i].stock == 0)
			{
				formatter = func.x[i].name.replaceAll("\\s", "");
				System.out.println("We've ran out of " + formatter + "!! Now Reseting The amount of " + formatter);
				func.x[i].stock = rSet.x[i].stock;
			}
		}
		for (int i = 0; i < func.y.length; i++)
		{
			if (func.y[i].num == 0)
			{
				formatter = func.y[i].name.replaceAll("\\s", "");
				System.out.println("We've ran out of " + func.y[i].name + "!! Now Reseting The amount of " + func.y[i].name);
				func.y[i].num = rSet.y[i].num;
				//Get the money out of the Savings and add to it but nah
			}
			else if (func.y[i].num > 15)
			{
				formatter = func.y[i].name.replaceAll("\\s", "");
				System.out.println("We've exceeded the capacity!! Now Reseting The amount of " + formatter);
				tally = func.y[i].num - rSet.y[i].num;
				func.y[i].num = rSet.y[i].num;
				func.z.collected = (func.z.collected + (tally * func.y[i].value));
			}
		}
	}
}
