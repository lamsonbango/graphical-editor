import java.awt.Point;

/**
 * Cmd.java
 * Command class for when users want to move an object to the back.
 * 
 * @author Lam Ngo
 * @see Command
 * @version 01/20/2017
 */
public class BackCmd extends Command {
	private Shape myShape; // An instance variable to record what shape has just been clicked on
	
	/**
	 * If a shape is clicked on, the drawing object will move that object to the back of its shape list.
	 * @param p: point clicked
	 * @param dwg: a drawing object
	 */
	public void executeClick(Point p, Drawing dwg){
		myShape = dwg.getFrontmostContainer(p);
		if (myShape != null){
			dwg.moveToBack(myShape);
		}
	}
}
