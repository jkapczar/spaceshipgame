package spaceshipgame.model;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *A töltényeket és ellenséges objetumokat megvalosító osztály.
 */

public class MovingCircle extends Circle{
	
	private double speed;
	private Point2D direction;
	private Point2D start;
	private Point2D end;
	

	public MovingCircle(double x, double y,double r,Color c) {
		this.setRadius(r);
		this.setFill(c);
		if (c.equals(Color.RED)) {
			this.setLayoutX(x);
			this.setLayoutY(y);
			setSpeed(1);
			getDirection(x, y, Game.centerx, Game.centery);
			
		}else if (c.equals(Color.GREEN)) {
			this.setLayoutX(Game.centerx);
			this.setLayoutY(Game.centery);
			setSpeed(2);
			getDirection(this.getLayoutX(), this.getLayoutY(), x, y);
		}
	}
	
	/**
	 *Két pont között meghatároz egy egységnyi irányvektort.
	 */
	public void getDirection(double Fx,double Fy,double Tx,double Ty){
		setStart(new Point2D(Fx, Fy));
		setEnd(new Point2D(Tx, Ty));
		
		setDirection(end.subtract(start).normalize());
			
	}
	
	
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public Point2D getDirection() {
		return direction;
	}
	public void setDirection(Point2D direction) {
		this.direction = direction;
	}
	public Point2D getStart() {
		return start;
	}
	public void setStart(Point2D start) {
		this.start = start;
	}
	public Point2D getEnd() {
		return end;
	}
	public void setEnd(Point2D end) {
		this.end = end;
	}
	
}
