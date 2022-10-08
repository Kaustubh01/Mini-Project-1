package org.example.player;

import javax.sound.sampled.*;
import java.io.File;

    public class Controller{
        long currentPosition;
        Clip clip;
        boolean isPaused;

        public void pause() {
            if (clip != null){
                if (!isPaused){
                    currentPosition  = clip.getMicrosecondPosition();
                    clip.stop();
                    isPaused = true;
                }
                else{
                    clip.setMicrosecondPosition(currentPosition);
                    clip.start();
                    isPaused = false;
                }
            }
        }

        public void resetSong(){
                clip.setMicrosecondPosition(0);
        }

        public void stopSong() {
            if (clip != null )
            {
                clip.stop();
                clip = null;
            }

        }


        public void play(String location){
            try {
                if (this.clip != null){
                    clip.stop();
                    clip = null;
                    File file = new File(location);
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
                    clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();

                }
                else {
                    File file = new File(location);
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
                    clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();

                }


            }
            catch (Exception e) {
                System.out.println(e+ "is thrown");
            }
        }
    }
