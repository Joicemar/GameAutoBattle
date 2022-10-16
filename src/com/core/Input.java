package com.core;


public class Input {
	
	public static int clickPositionX, clickPositionY;
	
	public Input() {

	}
	
	private static double X;
	private static double Y;
	
	public static double getX() {
		return X;
	}
	public static int getClickPositionX() {
		return clickPositionX;
	}
	public static void setClickPositionX(int clickPositionX) {
		Input.clickPositionX = clickPositionX;
	}
	public static int getClickPositionY() {
		return clickPositionY;
	}
	public static void setClickPositionY(int clickPositionY) {
		Input.clickPositionY = clickPositionY;
	}
	public static void setX(double x) {
		X = x;
	}
	public static double getY() {
		return Y;
	}
	public static void setY(double y) {
		Y = y;
	}
	
//	public double MousePositionX(Imagen e) {
//
//		double angle = Math.atan2(this.X - this.getX() - e.getX(), this.X - this.getX() - e.getX());
//		double targetX = Math.cos(angle);
//		return targetX;
//	}
//	
//	public double MousePositionY(Imagen e) {
//		
//		double angle = Math.atan2(this.X - this.getY() - e.getY(), this.X - this.getX() - e.getX());
//		double targetY = Math.sin(angle);
//		return targetY;
//	}
	
	public static boolean MousePressed;
	
	public static boolean ENTER;
	public static boolean ESCAPE;
	public static boolean W;
	public static boolean A;
	public static boolean S;
	public static boolean D;
	public static boolean SPACE;
	public static boolean RIGHT;
	public static boolean LEFT;
	public static boolean UP;
	public static boolean DOWN;
	
	

	

	
}
