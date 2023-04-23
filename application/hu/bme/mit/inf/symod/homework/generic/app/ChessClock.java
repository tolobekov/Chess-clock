package hu.bme.mit.inf.symod.homework.generic.app;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import hu.bme.mit.inf.symod.homework.generic.tests.ReflectiveTimeInterfaceProvider;
import hu.bme.mit.inf.symod.fa048.homework.chessclock.ChessClockStatemachine;
import hu.bme.mit.inf.symod.fa048.homework.chessclock.IChessClockStatemachine.SCIBeeperOperationCallback;

public class ChessClock extends JFrame {
	private static final long serialVersionUID = 5778094649668091821L;
	
	private final JButton blackButton;
	private final JButton whiteButton;
	private final JButton startButton;
	private final JButton modeButton;
	
	private final JLabel blackLabel;
	private final JLabel whiteLabel;
	private final JLabel mainLabel;
	
	private final JLabel beepLabel;
	private final Timer beepTimer;
	
	private final ChessClockStatemachine chessClock;
	private final Timer refreshTimer;
	
	public ChessClock() throws Exception {
		this.setTitle("Chess Clock");
		this.setPreferredSize(new Dimension(600, 300));
		this.setResizable(false);

		this.chessClock = new ChessClockStatemachine();
    	ReflectiveTimeInterfaceProvider.setTimer(chessClock, ReflectiveTimeInterfaceProvider.providePhysicsTimer());
		
		this.whiteButton = new JButton(new ImageIcon(new ImageIcon("application/WhiteButton.png").getImage().getScaledInstance(200, 80, Image.SCALE_SMOOTH)));
		this.whiteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				chessClock.getSCIButtons().raiseWhiteButton();
				chessClock.runCycle();
			}
		});
		this.blackButton = new JButton(new ImageIcon(new ImageIcon("application/BlackButton.png").getImage().getScaledInstance(200, 80, Image.SCALE_SMOOTH)));
		this.blackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				chessClock.getSCIButtons().raiseBlackButton();
				chessClock.runCycle();
			}
		});
		this.startButton = new JButton("START/RESET");
		this.startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				chessClock.getSCIButtons().raiseStartButton();
				chessClock.runCycle();
			}
		});
		this.modeButton = new JButton("MODE");
		this.modeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				chessClock.getSCIButtons().raiseModeButton();
				chessClock.runCycle();
			}
		});
		
		final Font labelFont = new Font(Font.MONOSPACED, Font.PLAIN, 16);
		
		this.whiteLabel = new JLabel("--:--");
		this.whiteLabel.setBackground(Color.BLACK);
		this.whiteLabel.setForeground(Color.GREEN);
		this.whiteLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
		this.whiteLabel.setFont(labelFont.deriveFont(16));
		this.whiteLabel.setOpaque(true);
		
		this.blackLabel = new JLabel("--:--");
		this.blackLabel.setBackground(Color.BLACK);
		this.blackLabel.setForeground(Color.GREEN);
		this.blackLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
		this.blackLabel.setFont(labelFont.deriveFont(16));
		this.blackLabel.setOpaque(true);
		
		this.mainLabel = new JLabel("-");
		this.mainLabel.setBackground(Color.BLACK);
		this.mainLabel.setForeground(Color.GREEN);
		this.mainLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
		this.mainLabel.setFont(labelFont.deriveFont(16));
		this.mainLabel.setOpaque(true);
		
    	this.refreshTimer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				long whiteDisplay = ChessClock.this.chessClock.getSCIDisplay().getWhiteDisplay();
				ChessClock.this.whiteLabel.setText(whiteDisplay < 0 ? " " : String.format("%02d:%02d", whiteDisplay / 60, whiteDisplay % 60));
				
				long blackDisplay = ChessClock.this.chessClock.getSCIDisplay().getBlackDisplay();
				ChessClock.this.blackLabel.setText(blackDisplay < 0 ? " " : String.format("%02d:%02d", blackDisplay / 60, blackDisplay % 60));
				
				String mainDispay = ChessClock.this.chessClock.getSCIDisplay().getText();
				ChessClock.this.mainLabel.setText(mainDispay + " ");
				
				ChessClock.this.chessClock.runCycle();
			}
		});
	    
		this.beepLabel = new JLabel(getBeepIcon(false));
		chessClock.getSCIBeeper().setSCIBeeperOperationCallback(new SCIBeeperOperationCallback() {
    		@Override public void beep() {
    			ChessClock.this.beepLabel.setIcon(ChessClock.this.getBeepIcon(true));
    			beepTimer.restart();

				try {
					AudioInputStream stream = AudioSystem.getAudioInputStream(new File("application/Whistle.wav"));

	    		    final Clip beepSound = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, stream.getFormat()));
	    		    beepSound.open(stream);
	    			beepSound.start();
	    			
	    			Timer soundTimer = new Timer(500, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
			    			beepSound.stop();
			    			beepSound.drain();
			    			beepSound.close();
						}
					});
	    			soundTimer.setRepeats(false);
	    			soundTimer.start();
				} 
				catch (Exception e) {
		            Logger.getLogger(ChessClock.class.getName()).log(Level.WARNING, null, e);
				}
    		}
    	});
		this.beepTimer = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
    			ChessClock.this.beepLabel.setIcon(ChessClock.this.getBeepIcon(false));
			}
		});
		this.beepTimer.start();
		
		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(layout.createParallelGroup()
			.addGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
					.addComponent(whiteLabel, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(whiteButton, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(startButton, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				)
				.addComponent(beepLabel, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup()
					.addComponent(blackLabel, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(blackButton, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(modeButton, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				)
			)
			.addComponent(mainLabel, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup()
				.addComponent(whiteLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(blackLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			)
			.addGroup(layout.createParallelGroup()
				.addComponent(whiteButton, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(beepLabel, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(blackButton, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			)
			.addGap(30)
			.addComponent(mainLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			.addGroup(layout.createParallelGroup()
				.addComponent(startButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(modeButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			)
		);
		
		this.pack();
		this.chessClock.init();
		this.chessClock.enter();
	}
	
	public void openWindow() {
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.refreshTimer.start();
	}
	
	private ImageIcon getBeepIcon(boolean beep) {
		Image image = new ImageIcon("application/Whistle.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		if(!beep) {
			BufferedImage img = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = img.createGraphics();
			Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, beep ? 0 : 1);
			g.setComposite(c); 
			g.drawImage(image, 0, 0, null);
			g.dispose();
			return new ImageIcon(img);
		}
		else {
			return new ImageIcon(image);
		}
	}

	public static void main(String[] args) {
		try {
			ChessClock chessClock = new ChessClock();
			chessClock.openWindow();
		}
		catch(Exception e) {
            Logger.getLogger(ChessClock.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}
