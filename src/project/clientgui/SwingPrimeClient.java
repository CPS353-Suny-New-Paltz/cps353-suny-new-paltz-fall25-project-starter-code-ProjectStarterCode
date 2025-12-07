package project.clientgui;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.Dimension;

import java.io.File;
import java.nio.file.Files;

import java.util.List;
import java.util.ArrayList;

import project.intercompute.FastInterComputeAPIImpl;
import project.intercompute.InterComputeAPI;
import project.intercompute.InterRequest;

public class SwingPrimeClient extends JFrame {

    private final InterComputeAPI engine = new FastInterComputeAPIImpl();

    private static final Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 18);
    private static final Font LABEL_FONT = new Font("SansSerif", Font.PLAIN, 16);
    private static final Font BUTTON_FONT = new Font("SansSerif", Font.BOLD, 16);

    private JTextField inlineInputField;
    private JLabel inlineResultLabel;
    private JLabel inlineStatusLabel;

    private JLabel fileInputLabel;
    private JLabel fileOutputLabel;
    private JLabel fileStatusLabel;

    private File inputFile = null;
    private File outputFile = null;

    public SwingPrimeClient() {
        super("Compute Engine Client");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Look and feel could not be set; using default.
        }
        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Inline Input", createInlinePanel());
        tabs.add("File Input/Output", createFilePanel());

        add(tabs);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 350);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createInlinePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel title = new JLabel("Inline Prime Computation");
        title.setFont(TITLE_FONT);
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel prompt = new JLabel("Enter a number:");
        prompt.setFont(LABEL_FONT);
        prompt.setAlignmentX(CENTER_ALIGNMENT);

        inlineInputField = new JTextField();
        inlineInputField.setFont(LABEL_FONT);
        inlineInputField.setMaximumSize(new Dimension(300, 30));

        JButton computeButton = new JButton("Compute");
        computeButton.setFont(BUTTON_FONT);
        computeButton.setPreferredSize(new Dimension(150, 40));
        computeButton.setMaximumSize(new Dimension(200, 40));
        computeButton.setAlignmentX(CENTER_ALIGNMENT);
        computeButton.addActionListener(e -> runInlineCompute());

        inlineResultLabel = new JLabel("Result:");
        inlineResultLabel.setFont(LABEL_FONT);
        inlineResultLabel.setAlignmentX(CENTER_ALIGNMENT);

        inlineStatusLabel = new JLabel("Status: Ready");
        inlineStatusLabel.setFont(LABEL_FONT);
        inlineStatusLabel.setAlignmentX(CENTER_ALIGNMENT);

        panel.add(title);
        panel.add(Box.createVerticalStrut(12));
        panel.add(prompt);
        panel.add(Box.createVerticalStrut(6));
        panel.add(inlineInputField);
        panel.add(Box.createVerticalStrut(12));
        panel.add(computeButton);
        panel.add(Box.createVerticalStrut(12));
        panel.add(inlineResultLabel);
        panel.add(Box.createVerticalStrut(4));
        panel.add(inlineStatusLabel);

        return panel;
    }

    private void runInlineCompute() {
        try {
            int n = Integer.parseInt(inlineInputField.getText().trim());
            int result = engine.processRequest(new InterRequest(n));

            if (result < 0) {
                inlineResultLabel.setText("Result: (no prime <= " + n + ")");
            } else {
                inlineResultLabel.setText("Result: " + result);
            }
            inlineStatusLabel.setText("Status: Success");
        } catch (NumberFormatException ex) {
            inlineStatusLabel.setText("Status: Invalid number format.");
        }
    }

    private JPanel createFilePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel title = new JLabel("File-Based Prime Computation");
        title.setFont(TITLE_FONT);
        title.setAlignmentX(CENTER_ALIGNMENT);

        JButton chooseInputBtn = new JButton("Choose Input File");
        chooseInputBtn.setFont(BUTTON_FONT);
        chooseInputBtn.setAlignmentX(CENTER_ALIGNMENT);
        chooseInputBtn.setMaximumSize(new Dimension(250, 40));
        chooseInputBtn.addActionListener(e -> chooseInputFile());

        fileInputLabel = new JLabel("Input File: (none)");
        fileInputLabel.setFont(LABEL_FONT);
        fileInputLabel.setAlignmentX(CENTER_ALIGNMENT);

        JButton chooseOutputBtn = new JButton("Choose Output File");
        chooseOutputBtn.setFont(BUTTON_FONT);
        chooseOutputBtn.setAlignmentX(CENTER_ALIGNMENT);
        chooseOutputBtn.setMaximumSize(new Dimension(250, 40));
        chooseOutputBtn.addActionListener(e -> chooseOutputFile());

        fileOutputLabel = new JLabel("Output File: (none)");
        fileOutputLabel.setFont(LABEL_FONT);
        fileOutputLabel.setAlignmentX(CENTER_ALIGNMENT);

        JButton runJobBtn = new JButton("Run Job");
        runJobBtn.setFont(BUTTON_FONT);
        runJobBtn.setAlignmentX(CENTER_ALIGNMENT);
        runJobBtn.setMaximumSize(new Dimension(200, 45));
        runJobBtn.addActionListener(e -> runFileJob());

        fileStatusLabel = new JLabel("Status: Waiting");
        fileStatusLabel.setFont(LABEL_FONT);
        fileStatusLabel.setAlignmentX(CENTER_ALIGNMENT);

        panel.add(title);
        panel.add(Box.createVerticalStrut(15));
        panel.add(chooseInputBtn);
        panel.add(Box.createVerticalStrut(6));
        panel.add(fileInputLabel);
        panel.add(Box.createVerticalStrut(12));
        panel.add(chooseOutputBtn);
        panel.add(Box.createVerticalStrut(6));
        panel.add(fileOutputLabel);
        panel.add(Box.createVerticalStrut(15));
        panel.add(runJobBtn);
        panel.add(Box.createVerticalStrut(12));
        panel.add(fileStatusLabel);

        return panel;
    }

    private void chooseInputFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            inputFile = chooser.getSelectedFile();
            fileInputLabel.setText("Input File: " + inputFile.getAbsolutePath());
        }
    }

    private void chooseOutputFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            outputFile = chooser.getSelectedFile();
            fileOutputLabel.setText("Output File: " + outputFile.getAbsolutePath());
        }
    }

    private void runFileJob() {
        if (inputFile == null || outputFile == null) {
            fileStatusLabel.setText("Status: Choose both files.");
            return;
        }

        try {
            List<String> lines = Files.readAllLines(inputFile.toPath());
            List<Integer> nums = lines.stream()
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(Integer::parseInt)
                    .toList();

            List<Integer> outputs = new ArrayList<>();
            for (int n : nums) {
                outputs.add(engine.processRequest(new InterRequest(n)));
            }

            List<String> outLines = outputs.stream()
                    .map(String::valueOf)
                    .toList();
            Files.write(outputFile.toPath(), outLines);

            fileStatusLabel.setText("Status: Job complete. " + outputs.size() + " results written.");
        } catch (Exception ex) {
            fileStatusLabel.setText("Status: Error - " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new SwingPrimeClient();
    }
}
