package hu.bme.mit.inf.symod.homework.generic.tests;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

import hu.bme.mit.inf.symod.fa048.homework.chessclock.ChessClockStatemachine;

public class ReflectiveTimeInterfaceProvider {
	public static boolean isTimerAvailable() {
		return iTimer()!= null;
	}
	
	public static Class<?> iTimer() {
		try {
			return Class.forName("hu.bme.mit.inf.symod.fa048.homework.ITimer");
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
	
	public static Class<?> iTimerCallback() {
		if(isTimerAvailable()) {
			try {
				return Class.forName("hu.bme.mit.inf.symod.fa048.homework.ITimerCallback");
			} catch (ClassNotFoundException e) {
				//e.printStackTrace();
				return null;
			}
		}
		else return null;
	}
	
	public static void setTimer(ChessClockStatemachine machine, Object timer) {
		if(timer != null && isTimerAvailable()) {
			try {
				machine.getClass().getMethod("setTimer", iTimer()).invoke(machine, timer);
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException
					| SecurityException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Object provideLogicTimer(final LogicTimer timer) {
		if(isTimerAvailable()) {
			return Proxy.newProxyInstance(
				ReflectiveTimeInterfaceProvider.iTimer().getClassLoader(),
				new Class<?>[]{ReflectiveTimeInterfaceProvider.iTimer()}, 
				new LogicTimerInvocationHandler(timer));
		}
		else return null;
	}
	
	@SuppressWarnings("deprecation")
	public static Object providePhysicsTimer() {
		if(isTimerAvailable()) {
			try {
				return Class.forName("hu.bme.mit.inf.symod.fa048.homework.TimerService").newInstance();
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
		}
		else return null;
	}
	
	public static void timeElapsed(Object callback, int eventID) {
		if(isTimerAvailable()) {
			try {
				iTimerCallback().getMethod("timeElapsed", int.class).invoke(callback, new Object[]{eventID});
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException
					| SecurityException e) {
				e.printStackTrace();
			}
		}
	}
}
