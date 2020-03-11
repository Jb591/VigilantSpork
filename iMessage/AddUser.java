import java.awt.event.*;
import java.util.*;
import javax.swing.Timer;

public class AddUser
{
	String text;
	ArrayList <IMessage> messenger = new ArrayList<>();

	ActionListener listener = new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			for(IMessage i : messenger)
			{
				i.chat(text);
			}
		}
	};

	Timer t = new Timer(100, listener);

	public void adder (IMessage newMessage)
	{
		messenger.add(newMessage);
	}

	public void textChat(String x)
	{
		text = x;
	}

	public AddUser()
	{
		t.start();
	}
}
