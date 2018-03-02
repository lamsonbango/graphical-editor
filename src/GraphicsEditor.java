import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

public class GraphicsEditor extends javax.swing.JFrame {
	
	private final int APPLET_WIDTH = 700, APPLET_HEIGHT = 500;
	private final Color initialColor = Color.red; // default color starts as red
	
	private Command cmd; // the command being executed
	private Drawing dwg; // the drawing: shapes in order
	private ColorIndicator colorBox; // a GUI component to show the current default color
	
    public GraphicsEditor() {
        cmd = new Command(); // all methods in Command are empty
        dwg = new Drawing(initialColor); // make an empty drawing
        
        // The drawing will appear in a white CanvasPanel.
        CanvasPanel canvasPanel = new CanvasPanel();
        canvasPanel.setBackground(Color.white);
        
        // Make JButton objects for all the command buttons.
        JButton rectButton = new JButton("Rectangle");
        JButton ellipseButton = new JButton("Ellipse");
        JButton lineButton = new JButton("Line");
        JButton moveButton = new JButton("Move");
        JButton deleteButton = new JButton("Delete");
        JButton frontButton = new JButton("Front");
        JButton backButton = new JButton("Back");
        JButton exchangeButton = new JButton("Exchange");
        JButton redButton = new JButton("Red");
        JButton greenButton = new JButton("Green");
        JButton blueButton = new JButton("Blue");
        JButton undoButton = new JButton("Undo");
        JButton redoButton = new JButton("Redo");
        
        
    		JFrame frame = new JFrame("Object-Oriented Graphical Editor");
        
    		frame.setLayout(new GridBagLayout());
    		
    		JTextField textFieldUserName = new JTextField(50);
    		frame.add(textFieldUserName);
    		
    		JMenuBar menuBar = new JMenuBar();
    		JMenu menuFile = new JMenu("File");
    		JMenuItem menuItemExit = new JMenuItem("Exit");
    		menuFile.add(menuItemExit);
    		 
    		menuBar.add(menuFile);
    		 
    		// adds menu bar to the frame
    		frame.setJMenuBar(menuBar);
    		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		
    		frame.setSize(300, 200);
    		frame.setVisible(true);
    }
    
    /**
     * A ColorIndicator shows what the current color is.
     */
    private class ColorIndicator extends JPanel {
      private static final long serialVersionUID = 0;
      
      private final int COLORBOX_WIDTH = 20, COLORBOX_HEIGHT = 20;

      /**
       * Constructor sets the size and border.
       */
      public ColorIndicator() {
        setBorder(BorderFactory.createEtchedBorder());
        setPreferredSize(new Dimension(COLORBOX_WIDTH, COLORBOX_HEIGHT));
      }

      /**
       * Show a new color.
       * @param color the color to show
       */
      public void show(Color color) {
        setBackground(color);
      }
    }
    
    /** 
     * CanvasPanel is the class upon which we actually draw.  It listens
     * for mouse events and calls the appropriate method of the current
     * command.
     */ 
    private class CanvasPanel extends JPanel implements MouseListener,
        MouseMotionListener {
      private static final long serialVersionUID = 0;
      
      /**
       * Constructor just needs to set up the CanvasPanel as a listener.
       */
      public CanvasPanel() {
      	addMouseListener(this);
      	addMouseMotionListener(this);
      }

      /**
       * Paint the whole drawing
       * @page the Graphics object to draw on
       */
      public void paintComponent(Graphics page) {
      	super.paintComponent(page); // execute the paint method of JPanel
      	dwg.draw(page); // have the drawing draw itself
      }

      /**
       * When the mouse is clicked, call the executeClick method of the
       * current command.
       */
      public void mouseClicked(MouseEvent event) {
      	cmd.executeClick(event.getPoint(), dwg);
      	repaint();
      }

      /**
       * When the mouse is pressed, call the executePress method of the
       * current command.
       */
      public void mousePressed(MouseEvent event) {
        cmd.executePress(event.getPoint(), dwg);
        repaint();
      }

      /** 
       * When the mouse is dragged, call the executeDrag method of the
       * current command.
       */
      public void mouseDragged(MouseEvent event) {
        cmd.executeDrag(event.getPoint(), dwg);
        repaint();
      }

      // We don't care about the other mouse events.
      public void mouseReleased(MouseEvent event) { }
      public void mouseEntered(MouseEvent event) { }
      public void mouseExited(MouseEvent event) { }
      public void mouseMoved(MouseEvent event) { }
    }
    
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new GraphicsEditor();
			}
		});
	}
}
