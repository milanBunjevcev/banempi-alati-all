package rs.bane.alati.client;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AlatiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		//SpringApplication.run(AlatiApplication.class, args);
		new SpringApplicationBuilder(AlatiApplication.class).headless(false).run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Spring Boot Swing App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        JPanel panel = new JPanel(new BorderLayout());
		JTextField text = new JTextField("Spring Boot can be used with Swing apps");
        panel.add(text, BorderLayout.CENTER);
        frame.setContentPane(panel);
        frame.setVisible(true);
	}

}
