package hu.bme.mit.inf.symod.homework.generic.tests;

import org.junit.Test;

public class TestCases {
	public static void main(String[] args) {
		TestCases testCases = new TestCases();
		try{ testCases.base1(); }catch(AssertionError e) {}try{ testCases.base2(); }catch(AssertionError e) {}try{ testCases.optionCycle(); }catch(AssertionError e) {}try{ testCases.checkSetStartTimeForWhite(); }catch(AssertionError e) {}try{ testCases.checkSetStartTimeForBlack(); }catch(AssertionError e) {}try{ testCases.checkSetBonusTimeForWhite(); }catch(AssertionError e) {}try{ testCases.checkSetBonusTimeForBlack(); }catch(AssertionError e) {}try{ testCases.checkSetStartPlayer(); }catch(AssertionError e) {}try{ testCases.checkEffectSetStartPlayer(); }catch(AssertionError e) {}try{ testCases.checkEffectSetStartTimeForWhite(); }catch(AssertionError e) {}try{ testCases.checkEffectSetStartTimeForBlack(); }catch(AssertionError e) {}try{ testCases.checkEffectSetBonusTimeForWhite(); }catch(AssertionError e) {}try{ testCases.checkEffectSetBonusTimeForBlack(); }catch(AssertionError e) {}try{ testCases.checkResetInOptions(); }catch(AssertionError e) {}try{ testCases.checkStartInGame(); }catch(AssertionError e) {}
	}
	
	@Test(timeout=10000)
	public void base1() {
		TesterAPI c = new TesterAPI("base1","Basic test: Pressing each button.");
	c.pushBlack();
	c.pushWhite();
	c.pushMod();
	c.pushStart();
	System.out.println("base1 Succeeded!");
	}
	
	@Test(timeout=10000)
	public void base2() {
		TesterAPI c = new TesterAPI("base2","Basic test: Waiting for 3 sec.");
	c.clockForward(3000);
	System.out.println("base2 Succeeded!");
	}
	
	@Test(timeout=10000)
	public void optionCycle() {
		TesterAPI c = new TesterAPI("optionCycle","By pushing the MOD button in the menu each options are available, and are set to the default values.");
	c.expectText("White begins");
	c.pushMod();
	c.expectBeep(true);
	c.expectText("White initial time");
	c.expectWhiteNumber(120);
	c.pushMod();
	c.expectBeep(true);
	c.expectText("Black initial time");
	c.expectBlackNumber(120);
	c.pushMod();
	c.expectBeep(true);
	c.expectText("White increment time");
	c.expectWhiteNumber(0);
	c.pushMod();
	c.expectBeep(true);
	c.expectText("Black increment time");
	c.expectBlackNumber(0);
	c.pushMod();
	c.expectBeep(true);
	c.expectText("White begins");
	System.out.println("optionCycle Succeeded!");
	}
	
	@Test(timeout=10000)
	public void checkSetStartTimeForWhite() {
		TesterAPI c = new TesterAPI("checkSetStartTimeForWhite","Checking the upper and lower bounds of the target option for the White player. First, the value is increased from default to maximal, plus one more time to check if it stops. Then, it decreased to minimal and checked again it it stops.");
	c.pushMod();
	c.expectWhiteNumber(120);
	c.pushWhite();
	c.expectWhiteNumber(125);
	c.pushWhite();
	c.expectWhiteNumber(130);
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.expectWhiteNumber(170);
	c.pushWhite();
	c.expectWhiteNumber(175);
	c.pushWhite();
	c.expectWhiteNumber(180);
	c.pushWhite();
	c.expectBeep(true);
	c.expectWhiteNumber(180);
	c.pushBlack();
	c.expectWhiteNumber(175);
	c.pushBlack();
	c.expectWhiteNumber(170);
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.expectWhiteNumber(70);
	c.pushBlack();
	c.expectWhiteNumber(65);
	c.pushBlack();
	c.expectWhiteNumber(60);
	c.pushBlack();
	c.expectWhiteNumber(60);
	c.expectBeep(true);
	System.out.println("checkSetStartTimeForWhite Succeeded!");
	}
	
	@Test(timeout=10000)
	public void checkSetStartTimeForBlack() {
		TesterAPI c = new TesterAPI("checkSetStartTimeForBlack","Checking the upper and lower bounds of the target optionfor the Black player. First, the value is increased from default to maximal, plus one more time to check if it stops. Then, it decreased to minimal and checked again it it stops.");
	c.pushMod();
	c.pushMod();
	c.expectBlackNumber(120);
	c.pushWhite();
	c.expectBlackNumber(125);
	c.pushWhite();
	c.expectBlackNumber(130);
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.expectBlackNumber(170);
	c.pushWhite();
	c.expectBlackNumber(175);
	c.pushWhite();
	c.expectBlackNumber(180);
	c.pushWhite();
	c.expectBeep(true);
	c.expectBlackNumber(180);
	c.pushBlack();
	c.expectBlackNumber(175);
	c.pushBlack();
	c.expectBlackNumber(170);
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.expectBlackNumber(70);
	c.pushBlack();
	c.expectBlackNumber(65);
	c.pushBlack();
	c.expectBlackNumber(60);
	c.pushBlack();
	c.expectBlackNumber(60);
	c.expectBeep(true);
	System.out.println("checkSetStartTimeForBlack Succeeded!");
	}
	
	@Test(timeout=10000)
	public void checkSetBonusTimeForWhite() {
		TesterAPI c = new TesterAPI("checkSetBonusTimeForWhite","Checking the upper and lower bounds of the target option for the White player. First, the value is increased from default to maximal, plus one more time to check if it stops. Then, it decreased to minimal and checked again it it stops.");
	c.pushMod();
	c.pushMod();
	c.pushMod();
	c.expectWhiteNumber(0);
	c.pushWhite();
	c.expectWhiteNumber(5);
	c.pushWhite();
	c.expectWhiteNumber(10);
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.expectWhiteNumber(50);
	c.pushWhite();
	c.expectWhiteNumber(55);
	c.pushWhite();
	c.expectWhiteNumber(60);
	c.pushWhite();
	c.expectBeep(true);
	c.expectWhiteNumber(60);
	c.pushBlack();
	c.expectWhiteNumber(55);
	c.pushBlack();
	c.expectWhiteNumber(50);
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.expectWhiteNumber(10);
	c.pushBlack();
	c.expectWhiteNumber(5);
	c.pushBlack();
	c.expectWhiteNumber(0);
	c.pushBlack();
	c.expectWhiteNumber(0);
	c.expectBeep(true);
	System.out.println("checkSetBonusTimeForWhite Succeeded!");
	}
	
	@Test(timeout=10000)
	public void checkSetBonusTimeForBlack() {
		TesterAPI c = new TesterAPI("checkSetBonusTimeForBlack","Checking the upper and lower bounds of the target optionfor the Black player. First, the value is increased from default to maximal, plus one more time to check if it stops. Then, it decreased to minimal and checked again it it stops.");
	c.pushMod();
	c.pushMod();
	c.pushMod();
	c.pushMod();
	c.expectBlackNumber(0);
	c.pushWhite();
	c.expectBlackNumber(5);
	c.pushWhite();
	c.expectBlackNumber(10);
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.expectBlackNumber(50);
	c.pushWhite();
	c.expectBlackNumber(55);
	c.pushWhite();
	c.expectBlackNumber(60);
	c.pushWhite();
	c.expectBeep(true);
	c.expectBlackNumber(60);
	c.pushBlack();
	c.expectBlackNumber(55);
	c.pushBlack();
	c.expectBlackNumber(50);
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.expectBlackNumber(10);
	c.pushBlack();
	c.expectBlackNumber(5);
	c.pushBlack();
	c.expectBlackNumber(0);
	c.pushBlack();
	c.expectBlackNumber(0);
	c.expectBeep(true);
	System.out.println("checkSetBonusTimeForBlack Succeeded!");
	}
	
	@Test(timeout=10000)
	public void checkSetStartPlayer() {
		TesterAPI c = new TesterAPI("checkSetStartPlayer","Checking whether the initial player can be set.");
	c.expectText("White begins");
	c.pushBlack();
	c.expectText("Black begins");
	c.pushBlack();
	c.expectText("Black begins");
	c.pushMod();
	c.pushMod();
	c.pushMod();
	c.pushMod();
	c.pushMod();
	c.expectText("Black begins");
	c.pushWhite();
	c.expectText("White begins");
	c.pushWhite();
	c.expectText("White begins");
	System.out.println("checkSetStartPlayer Succeeded!");
	}
	
	@Test(timeout=10000)
	public void checkEffectSetStartPlayer() {
		TesterAPI c = new TesterAPI("checkEffectSetStartPlayer","Checks the result of changing the initial player.");
	c.pushBlack();
	c.pushStart();
	c.expectText("Black moves");
	System.out.println("checkEffectSetStartPlayer Succeeded!");
	}
	
	@Test(timeout=10000)
	public void checkEffectSetStartTimeForWhite() {
		TesterAPI c = new TesterAPI("checkEffectSetStartTimeForWhite","Checks the result of changeing the initial time for player Whtie.");
	c.pushMod();
	c.expectText("White initial time");
	c.expectWhiteNumber(120);
	c.pushWhite();
	c.expectText("White initial time");
	c.expectWhiteNumber(125);
	c.pushStart();
	c.expectText("White moves");
	c.expectWhiteNumber(125);
	c.clockForward(3000);
	c.expectWhiteNumber(122);
	System.out.println("checkEffectSetStartTimeForWhite Succeeded!");
	}
	
	@Test(timeout=10000)
	public void checkEffectSetStartTimeForBlack() {
		TesterAPI c = new TesterAPI("checkEffectSetStartTimeForBlack","Checks the result of changeing the initial time for player Black.");
	c.pushMod();
	c.pushMod();
	c.expectText("Black initial time");
	c.expectBlackNumber(120);
	c.pushWhite();
	c.expectText("Black initial time");
	c.expectBlackNumber(125);
	c.pushStart();
	c.pushWhite();
	c.expectBeep(false);
	c.expectText("Black moves");
	c.expectBlackNumber(125);
	c.clockForward(3000);
	c.expectBlackNumber(122);
	System.out.println("checkEffectSetStartTimeForBlack Succeeded!");
	}
	
	@Test(timeout=10000)
	public void checkEffectSetBonusTimeForWhite() {
		TesterAPI c = new TesterAPI("checkEffectSetBonusTimeForWhite","Checks the result of changeing the initial time for player Whtie.");
	c.pushMod();
	c.pushMod();
	c.pushMod();
	c.expectText("White increment time");
	c.expectWhiteNumber(0);
	c.pushWhite();
	c.expectText("White increment time");
	c.expectWhiteNumber(5);
	c.pushStart();
	c.expectText("White moves");
	c.expectWhiteNumber(120);
	c.pushWhite();
	c.expectBeep(false);
	c.expectWhiteNumber(125);
	System.out.println("checkEffectSetBonusTimeForWhite Succeeded!");
	}
	
	@Test(timeout=10000)
	public void checkEffectSetBonusTimeForBlack() {
		TesterAPI c = new TesterAPI("checkEffectSetBonusTimeForBlack","Checks the result of changeing the initial time for player Black.");
	c.pushMod();
	c.pushMod();
	c.pushMod();
	c.pushMod();
	c.expectText("Black increment time");
	c.expectBlackNumber(0);
	c.pushWhite();
	c.expectText("Black increment time");
	c.expectBlackNumber(5);
	c.pushStart();
	c.pushWhite();
	c.expectBeep(false);
	c.expectText("Black moves");
	c.expectBlackNumber(120);
	c.pushBlack();
	c.expectBeep(false);
	c.expectBlackNumber(125);
	System.out.println("checkEffectSetBonusTimeForBlack Succeeded!");
	}
	
	@Test(timeout=10000)
	public void checkResetInOptions() {
		TesterAPI c = new TesterAPI("checkResetInOptions","Checking the effect of pushing the START/RESET button in the options.");
	c.pushMod();
	c.expectText("White initial time");
	c.expectWhiteNumber(120);
	c.pushWhite();
	c.expectText("White initial time");
	c.expectWhiteNumber(125);
	c.pushStart();
	c.expectText("White moves");
	System.out.println("checkResetInOptions Succeeded!");
	}
	
	@Test(timeout=10000)
	public void checkStartInGame() {
		TesterAPI c = new TesterAPI("checkStartInGame","Checking the START/RESET button in the game");
	c.expectText("White begins");
	c.pushStart();
	c.expectText("White moves");
	c.pushStart();
	c.expectText("White begins");
	System.out.println("checkStartInGame Succeeded!");
	}
	
}
