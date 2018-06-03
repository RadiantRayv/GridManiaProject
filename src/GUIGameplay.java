import java.awt.*;          // access to Container
import java.awt.event.*;    // access to WindowAdapter, WindowEvent
import javax.swing.*;

import javafx.scene.media.*;

import java.net.URL;

public class GUIGameplay
{ 
	private game g;

	private ImageIcon square;
	private ImageIcon blank;
	private ImageIcon red;
	private Image grid;
	private int counter;         // counts seconds
	private int sqX, sqY, sqX2, sqY2, sqX3, sqY3;
	private int redX, redY;
	private int redSize;
	private int size, size2, size3;

	//	private long TimeStart;
	//	private long timediff;
	//	private int h;


	private song s;
	private MediaPlayer player;
	String hitsound;
//	private MediaPlayer hits;

	private notesChart chart;
	private boolean[] currentNotes;
	private double currentTime;
	private int bpm;
	
	private long TimeStart;

	private JPanel cont;
	private JLayeredPane one;
	//	private JLabel sq1;
	//	private JLayeredPane two;
	//	private JLayeredPane three;
	//	private JLayeredPane four;
	//	private JLayeredPane five;
	//	private JLayeredPane six;
	//	private JLayeredPane seven;
	//	private JLayeredPane eight;
	//	private JLayeredPane nine;
	//	private JLabel big;

	//in the method that adds a panel with a square and displays it to the jlayeredpane, store the created layer to a arraylist. Iterate over entire arraylist to resize everything at once in the paint method. After a square is hidden, delete it from the arraylist.

	//BRO LOOK AT GRIDWORLD GUI AND SEE IF THAT HELPS AT ALLr

	InputMap inm;

	public GUIGameplay() 
	{
		cont = new JPanel(null);
		cont.add(one = new JLayeredPane());
		one.setBounds(0, 0, 675, 675);

		inm = cont.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		setKeysNo();

		//		cont.add(two);
		//		cont.add(three);
		//		cont.add(four);
		//		cont.add(five);
		//		cont.add(six);
		//		cont.add(seven);
		//		cont.add(eight);
		//		cont.add(nine);

		ClassLoader cldr = this.getClass().getClassLoader();
		square = new ImageIcon(cldr.getResource("bluesquare.png"));
		blank = new ImageIcon(cldr.getResource("blank.png"));
		red = new ImageIcon(cldr.getResource("redsquare.png"));
		grid = new ImageIcon(cldr.getResource("grid.png")).getImage();
		hitsound = cldr.getResource("hit.wav").toString();


		//
		//		add(cont);

		sqX = 87;
		sqY = 87;
		sqX2 = 238;
		sqY2 = 162;
		sqX3 = 163;
		sqY3 = 237;
		redX = 162;
		redY = 163;
		//		setSize(675, 675);
		//		setVisible(true);
		size = 0;
		size2 = 0;
		size3 = 0;
		redSize = 10;

		counter = 0;
		
		TimeStart = System.currentTimeMillis();
	}

	public JPanel getCont()
	{
		return cont;
	}

	public void addGame(game gg)
	{
		g = gg;
	}

	public void setSong(song ss)
	{
		s = ss;
		bpm = s.getBpm();
	}

	public void startGame(int diff)
	{
		chart = s.getEasy();
	}

	public void startSong()
	{
		player = new MediaPlayer(s.getSong());
		player.play();
	}

	private class note implements Runnable
	{

		private int xfirst;
		private int yfirst;
		private int xsecond;
		private int ysecond;
		private boolean isSpecial;

		note(int x1, int y1, int x2, int y2, boolean special)
		{
			xfirst = x1;
			yfirst = y1;
			xsecond = x2;
			ysecond = y2;
			isSpecial = special;
		}

		public void run() 
		{
			long TimeStart = System.currentTimeMillis();
			long timediff = 0;
			int size = 2;
			JLabel sq1  = new JLabel();
			while(size<225)
			{
				timediff = (System.currentTimeMillis() - TimeStart);
				if(timediff % 25 == 0)
				{
					sq1.setHorizontalAlignment(JLabel.CENTER);
					sq1.setBounds(xfirst, yfirst, xsecond, ysecond);
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							one.add(sq1, 0);
						}
					});


					int h = (int)(timediff/25)*6 + 5;
					ImageIcon scaled;
					if(isSpecial)
					{
						size = h*3;
						scaled = new ImageIcon(red.getImage().getScaledInstance(size, size, java.awt.Image.SCALE_FAST));
					}
					else
					{
						size = h;
						scaled = new ImageIcon(square.getImage().getScaledInstance(size, size, java.awt.Image.SCALE_FAST));
					}
					sq1.setIcon(scaled);


					//				counter++;
					//				System.out.println("time is " + counter);
					//				repaint();
				}
				while((System.currentTimeMillis() - TimeStart) == timediff)
				{

				}
			}
			while(timediff < 1250)
			{
				timediff = (System.currentTimeMillis() - TimeStart);
			}
			sq1.setIcon(blank);

		}
	}

	public void draw(int index)
	{
		note noteThread;
		Thread t;
		switch(index)
		{
		case 0:
			noteThread = new note(0,0,225,225,false);
			t = new Thread(noteThread);
			t.start();
			break;

		case 1:
			noteThread = new note(225,0,225,225,false);
			t = new Thread(noteThread);
			t.start();
			break;

		case 2:
			noteThread = new note(450,0,225,225,false);
			t = new Thread(noteThread);
			t.start();
			break;

		case 3:
			noteThread = new note(0,225,225,225,false);
			t = new Thread(noteThread);
			t.start();
			break;

		case 4:
			noteThread = new note(225,225,225,225,false);
			t = new Thread(noteThread);
			t.start();
			break;

		case 5:
			noteThread = new note(450,225,225,225,false);
			t = new Thread(noteThread);
			t.start();
			break;

		case 6:
			noteThread = new note(0,450,225,225,false);
			t = new Thread(noteThread);
			t.start();
			break;

		case 7:
			noteThread = new note(225,450,225,225,false);
			t = new Thread(noteThread);
			t.start();
			break;

		case 8:
			noteThread = new note(450,450,225,225,false);
			t = new Thread(noteThread);
			t.start();
			break;

		case 9:
			noteThread = new note(225,225,225,225,true);
			t = new Thread(noteThread);
			t.start();
			break;

		}

	}
	
	public void setCurrentNotes(boolean[] n, double time)
	{
		currentNotes = n;
		currentTime = time;
	}

	public void setKeysNo()
	{
		inm.put(KeyStroke.getKeyStroke("1"), "tap");
		cont.getActionMap().put("tap", new tapAction(1));
	}

	private class tapAction extends AbstractAction
	{
		int note;

		tapAction(int n)
		{
			note = n;
		}

		public void actionPerformed(ActionEvent e) 
		{
//			Thread t = new Thread(new Runnable() {
//				public void run() {
					MediaPlayer hits = new MediaPlayer(new Media(hitsound));
					hits.play();
					long hitTimeDiff = System.currentTimeMillis() - TimeStart;
					if(hitTimeDiff < 500 && hitTimeDiff > -500)
					{
						System.out.println("hit");
					}
					
					
//				}
//			});
//
//			t.start();
		}
	}

}


