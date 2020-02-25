package com.gunbombsandangels.engine.gui;

import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.File;
import java.io.IOException;

public class SoundManager {
	
	private Clip clip; 
    private AudioInputStream audioInputStream; 
    private String filePath;
    
    public void playBigGun() {
 	   filePath = "res/Sounds/bigGun.WAV"; 
 		  
         try {
 			audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
 	        clip = AudioSystem.getClip(); 
 	        clip.open(audioInputStream); 
 	         clip.start();; 
 			
 		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} 
     }
    
    public void playSmallGun() {
 	   filePath = "res/Sounds/smallGun.WAV"; 
 		  
         try {
 			audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
 	        clip = AudioSystem.getClip(); 
 	        clip.open(audioInputStream); 
 	         clip.start();; 
 			
 		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} 
     }
	
	public void playAngel() {
	   filePath = "res/Sounds/angel.WAV"; 
		  
        try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
	        clip = AudioSystem.getClip(); 
	        clip.open(audioInputStream); 
	         clip.start();; 
			
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }	
	
	public void playBomb() {
	   filePath = "res/Sounds/bomb.WAV"; 
	  
        try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
	        clip = AudioSystem.getClip(); 
	        clip.open(audioInputStream); 
	         clip.start();; 
			
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
	
	public void playTick() {
	 	   filePath = "res/Sounds/tick.WAV"; 
	 		  
	         try {
	 			audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
	 	        clip = AudioSystem.getClip(); 
	 	        clip.open(audioInputStream); 
	 	         clip.start();; 
	 			
	 		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		} 
	     }
	
}
