package org.example.player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerInterface extends JFrame implements ActionListener{
    ButtonGroup group = new ButtonGroup();
    JRadioButton kids = new JRadioButton("kids");
    JRadioButton teen = new JRadioButton("teen");
    JRadioButton youngsters = new JRadioButton("youngsters");
    JRadioButton midAged = new JRadioButton("mid aged");
    JRadioButton senior = new JRadioButton("60+");
    JRadioButton showAllButton = new JRadioButton("Show all songs");
    JPanel loginPanel = new JPanel();
    JPanel titlePanel = new JPanel();
    JPanel songListPanel = new JPanel();
    Controller media = new Controller();
    JButton goBackButton = new JButton("Go Back");

    JPanel songPanels(int index) throws Exception {
        JPanel song = new JPanel();
        song.setLayout(new FlowLayout(FlowLayout.LEFT));
        song.setBackground(new Color(19,85,120));
        song.setPreferredSize(new Dimension(400,50));

        Song obj = new Song();
        String[] a = obj.getData();
        String[] b = obj.getLocation();

        JLabel artistName = new JLabel("Artist Name");

        artistName.setForeground(new Color(255,255,255));

        JButton playButton = new JButton(a[index]);
        playButton.setForeground(new Color(255,255,255));
        playButton.setBorderPainted(false);
        playButton.setContentAreaFilled(false);
        playButton.setFocusPainted(false);
        playButton.setOpaque(false);
        playButton.addActionListener(e ->media.play(b[index]));
        song.add(playButton);
        song.add(artistName);
        return  song;
    }

    public void buildFrame(){
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\Kaustubh\\IdeaProjects\\MusicPlayer\\src\\org\\logo.png");
        this.setIconImage(imageIcon.getImage());
        this.setTitle("Music Player");
        this.setSize(400,500);
        this.getContentPane().setBackground(new Color(9,40,56));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);


        JButton stopSongButton = new JButton("Stop");
        stopSongButton.setBounds(110,50,80,40);
        stopSongButton.addActionListener(e -> media.stopSong());

        JButton resetSongButton = new JButton("Reset");
        resetSongButton.setBounds(190,50,80,40);
        resetSongButton.addActionListener(e -> {
            try {
                media.resetSong();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton pausePlaySongButton = new JButton("Pause");
        pausePlaySongButton.setBounds(270,50,80,40);
        pausePlaySongButton.addActionListener(e -> {
            try {
                media.pause();
                if (media.isPaused)pausePlaySongButton.setText("Play");
                else pausePlaySongButton.setText("Pause");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });



        group.add(kids);
        group.add(teen);
        group.add(youngsters);
        group.add(showAllButton);
        group.add(midAged);
        group.add(senior);

        loginPanel.setLayout(new GridLayout(0,1));

        kids.addActionListener(this);
        teen.addActionListener(this);
        youngsters.addActionListener(this);
        midAged.addActionListener(this);
        senior.addActionListener(this);
        showAllButton.addActionListener(this);

        loginPanel.add(kids);
        loginPanel.add(teen);
        loginPanel.add(youngsters);
        loginPanel.add(midAged);
        loginPanel.add(senior);
        loginPanel.add(showAllButton);

        JPanel mediaPanel = new JPanel();
        mediaPanel.setPreferredSize(new Dimension(400,50));
        mediaPanel.setLayout(new FlowLayout());

        mediaPanel.add(stopSongButton);
        mediaPanel.add(pausePlaySongButton);
        mediaPanel.add(resetSongButton);

        goBackButton.addActionListener(this);
        goBackButton.setVisible(false);

        titlePanel.setLayout(new FlowLayout());
        titlePanel.add(new JLabel("Title"));
        titlePanel.add(goBackButton);
        //add to frame
        this.add(titlePanel,BorderLayout.NORTH);
        this.add(loginPanel,BorderLayout.CENTER);
        this.add(mediaPanel,BorderLayout.SOUTH);
        this.setVisible(true);
    }

    void showSongs(int lb, int ub) throws Exception{

        songListPanel.setLayout(new FlowLayout());
        JScrollPane scrollPane = new JScrollPane(songListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        songListPanel.setPreferredSize(new Dimension(400,500));
        for (int i = lb; i<ub;i++){

            songListPanel.add(songPanels(i));
        }
        this.add(scrollPane,BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == kids) {
            loginPanel.setVisible(false);
            try {
                showSongs(0, 3);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        else if (e.getSource() == teen) {
            loginPanel.setVisible(false);
            try {
                showSongs(3, 6);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        else if (e.getSource() == youngsters) {
            loginPanel.setVisible(false);
            goBackButton.setVisible(true);
            try {
                showSongs(6, 9);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        else if (e.getSource() == midAged) {
            loginPanel.setVisible(false);
            goBackButton.setVisible(true);
            try {
                showSongs(9, 12);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        else if (e.getSource() == senior) {
            loginPanel.setVisible(false);
            goBackButton.setVisible(true);
            try {
                showSongs(12, 14);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        else if (e.getSource() == showAllButton) {
            Song song = new Song();
            goBackButton.setVisible(true);
            loginPanel.setVisible(false);
            try {
                showSongs(0, song.getCount());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

        else if (e.getSource() == goBackButton){
            this.remove(songListPanel);
            loginPanel.setVisible(true);
            group.clearSelection();
            goBackButton.setVisible(false);
        }
    }
}