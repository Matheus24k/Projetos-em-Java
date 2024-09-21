package org.example;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Som {
    private Clip clip;

    public void tocarSom(String caminho) {
        try {
            File arquivoSom = new File(caminho);
            if (!arquivoSom.exists()) {
                System.err.println("Arquivo não encontrado: " + caminho);
                return; // Sai do método se o arquivo não existir
            }

            if (clip != null && clip.isRunning()) {
                clip.stop(); // Para o som anterior se estiver tocando
            }

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(arquivoSom);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public void pararSom() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }


    public boolean isPlaying() {
        return clip != null && clip.isRunning();
    }
}
