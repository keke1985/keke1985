import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::createWindow);
    }

    private static void createWindow() {
        JFrame window = new JFrame("运算实验室");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(520, 390);
        window.setLocationRelativeTo(null);

        JTextField firstInput = new JTextField("2");
        JTextField secondInput = new JTextField("3");
        JTextField expectedInput = new JTextField("5");
        JComboBox<String> modeSelector = new JComboBox<>(new String[] { "计算模式", "测试模式" });
        JLabel resultLabel = new JLabel("等待你的第一次运算", JLabel.CENTER);
        resultLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        resultLabel.setForeground(new Color(30, 41, 59));
        RoundedButton runButton = new RoundedButton("开始执行");

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 12, 12));
        inputPanel.setOpaque(false);
        addInputRow(inputPanel, "第一个数字", firstInput);
        addInputRow(inputPanel, "第二个数字", secondInput);
        addInputRow(inputPanel, "预期结果", expectedInput);
        addInputRow(inputPanel, "运行模式", modeSelector);

        styleInput(firstInput);
        styleInput(secondInput);
        styleInput(expectedInput);
        modeSelector.setFont(new Font("SansSerif", Font.PLAIN, 14));
        modeSelector.setBackground(Color.WHITE);
        modeSelector.setFocusable(false);

        modeSelector.addActionListener(event -> {
            boolean testMode = modeSelector.getSelectedIndex() == 1;
            expectedInput.setEnabled(testMode);
            expectedInput.setBackground(testMode ? Color.WHITE : new Color(226, 232, 240));
        });

        runButton.addActionListener(event -> {
            try {
                int first = Integer.parseInt(firstInput.getText().trim());
                int second = Integer.parseInt(secondInput.getText().trim());
                int actual = add(first, second);

                if (modeSelector.getSelectedIndex() == 0) {
                    resultLabel.setForeground(new Color(30, 41, 59));
                    resultLabel.setText("计算结果：" + actual);
                    return;
                }

                int expected = Integer.parseInt(expectedInput.getText().trim());
                boolean passed = actual == expected;
                resultLabel.setForeground(passed ? new Color(5, 150, 105) : new Color(220, 38, 38));
                resultLabel.setText(passed
                        ? "测试通过：结果为 " + actual
                        : "测试失败：预期 " + expected + "，实际 " + actual);
            } catch (NumberFormatException exception) {
                resultLabel.setForeground(new Color(220, 38, 38));
                resultLabel.setText("请输入有效的整数");
            }
        });

        JLabel titleLabel = new JLabel("运算实验室", JLabel.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        JLabel subtitleLabel = new JLabel("让每一次计算都清晰可见", JLabel.CENTER);
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(224, 231, 255));

        JPanel header = new JPanel(new GridLayout(2, 1, 0, 2));
        header.setOpaque(false);
        header.add(titleLabel);
        header.add(subtitleLabel);

        JPanel inputCard = new JPanel(new BorderLayout(0, 16));
        inputCard.setBackground(new Color(255, 255, 255, 235));
        inputCard.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(255, 255, 255, 120), 1, true),
                BorderFactory.createEmptyBorder(20, 24, 20, 24)));
        inputCard.add(inputPanel, BorderLayout.CENTER);
        inputCard.add(resultLabel, BorderLayout.SOUTH);

        JPanel contentPanel = new GradientPanel();
        contentPanel.setLayout(new BorderLayout(16, 16));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(24, 28, 24, 28));
        contentPanel.add(header, BorderLayout.NORTH);
        contentPanel.add(inputCard, BorderLayout.CENTER);
        contentPanel.add(runButton, BorderLayout.SOUTH);

        window.setContentPane(contentPanel);
        window.setVisible(true);
    }

    private static void addInputRow(JPanel panel, String labelText, java.awt.Component input) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        label.setForeground(new Color(51, 65, 85));
        panel.add(label);
        panel.add(input);
    }

    private static void styleInput(JTextField input) {
        input.setFont(new Font("SansSerif", Font.PLAIN, 15));
        input.setHorizontalAlignment(JTextField.CENTER);
        input.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(203, 213, 225), 1, true),
                BorderFactory.createEmptyBorder(7, 10, 7, 10)));
        input.setPreferredSize(new Dimension(160, 38));
    }

    private static int add(int first, int second) {
        return first + second;
    }

    private static class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D graphics2D = (Graphics2D) graphics.create();
            graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            graphics2D.setPaint(new GradientPaint(0, 0, new Color(79, 70, 229), getWidth(), getHeight(),
                    new Color(14, 165, 233)));
            graphics2D.fillRect(0, 0, getWidth(), getHeight());
            graphics2D.dispose();
        }
    }

    private static class RoundedButton extends JButton {
        RoundedButton(String text) {
            super(text);
            setFont(new Font("SansSerif", Font.BOLD, 15));
            setForeground(Color.WHITE);
            setBackground(new Color(15, 23, 42));
            setBorderPainted(false);
            setFocusPainted(false);
            setContentAreaFilled(false);
            setOpaque(false);
            setMargin(new Insets(12, 20, 12, 20));
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            Graphics2D graphics2D = (Graphics2D) graphics.create();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.setColor(getModel().isRollover() ? new Color(30, 41, 59) : getBackground());
            graphics2D.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);
            graphics2D.dispose();
            super.paintComponent(graphics);
        }
    }
}
