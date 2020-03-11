import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MessageBox extends JPanel implements IMessage
{
	private JTextField JTF;
	private AddUser tp;
	private JTextArea JTA;
	private static JButton buttonSend;
	private int numUsers;

	public void chat(String now)
	{
		JTA.setText(now);
	}

	public MessageBox(int x, AddUser y)
	{
		super(new GridBagLayout());

		numUsers = x;
		tp = y;

		tp.adder(this);

		JLabel label = new JLabel ("Enter Text : ");

		JTF = new JTextField(25);

		JTA = new JTextArea(5, 25);
		JTA.setEditable(false);
		JTA.setLineWrap(true);
		JTA.setWrapStyleWord(true);

		JScrollPane JSP = new JScrollPane(JTA);

		buttonSend = new JButton("Send");
		buttonSend.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0)
					{
						String typedText;
						String text = JTF.getText();
						JTA.append("User " + numUsers + " : ");
						JTA.append(text + "\n");
						JTF.setText("");

						typedText = JTA.getText();

						tp.textChat(typedText);
					}
				});

		GridBagConstraints GBC = new GridBagConstraints();
		GBC.gridwidth = GridBagConstraints.REMAINDER;
		GBC.fill = GridBagConstraints.HORIZONTAL;

        add(JSP, GBC);
        add(label);
        add(JTF, GBC);
        add(buttonSend,GBC);
	}

	public static void textBox(int x, AddUser y)
	{
		JFrame jf = new JFrame();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jf.add(new MessageBox(x,y));
		jf.setSize(500,200);
		jf.setLocation((x-1) * 525, 0);
		jf.setTitle("User " + Integer.toString(x));
		jf.setVisible(true);
	}
}
