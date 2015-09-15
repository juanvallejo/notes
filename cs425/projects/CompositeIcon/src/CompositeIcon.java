/**
 * CompositeIcon. Draws multiple icons (with the same logic used for a single icon).
 * 
 * @author juanvallejo
 * @date 3/19/15
 */

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.*;

public class CompositeIcon implements Icon {

	private Icon[] icons;
	private int[][] locations;
	
	private int iconCount;
	private int width;
	private int height;
	
	public CompositeIcon() {
		
		icons 		= new Icon[3];
		locations 	= new int[3][2];
		width		= 0;
		height 		= 0;
		iconCount	= 0;
		
	}
	
	public void paintIcon(Component component, Graphics graphics, int x, int y) {
		
		for(int i = 0; i < icons.length; i++) {
			icons[i].paintIcon(component, graphics, locations[i][0], locations[i][1]);
		}
		
	}

	public int getIconWidth() {
		
		return width;
		
	}

	public int getIconHeight() {

		return height;
		
	}

	/**
	 * adds the icon to the collection of icons and supply methods to draw each icon at location x,y
	 * @param icon to draw on the frame
	 * @param x coordinate of icon
	 * @param y coordinate of icon
	 */
	public void addIcon(Icon icon, int x, int y) {
		
		icons[iconCount] 		= icon;
		locations[iconCount][0] = x;
		locations[iconCount][1] = y;
		
		if(x + icon.getIconWidth() > width) {
			width = x + icon.getIconWidth();
		}
		
		if(y + icon.getIconHeight() > height) {
			height = y + icon.getIconHeight();
		}
		
		iconCount++;
				
	}
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		Container panel = frame.getContentPane();
		panel.setLayout(new BorderLayout());
		CompositeIcon icon = new CompositeIcon();
		
		try {
			
			icon.addIcon(new ImageIcon(new URL("http://th02.deviantart.net/fs71/150/f/2013/103/2/7/java_dock_icon_by_excurse-d61mi0t.png")), 10, 10);
			icon.addIcon(new ImageIcon(new URL("http://fc03.deviantart.net/fs20/f/2007/274/9/8/3D_Java_icon_by_BrightKnight.png")), 200, 200);
			icon.addIcon(new ImageIcon(new URL("http://www.bravegnu.org/blog/icons/java.png")), 5, 370);
					
		} catch (MalformedURLException e) {
			System.err.println("Apparently, somebody cannot type a URL");
		}
		
		panel.add(new JLabel(icon));
		
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
}