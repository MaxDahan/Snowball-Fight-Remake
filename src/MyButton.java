import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JButton;

public class MyButton extends JButton {
    private Color hoverBackgroundColor, pressedBackgroundColor, hoverTextColor, pressedTextColor;
    
    public MyButton() {
        this((String)null);
    }
    public MyButton(String text) {
        super(text);
        super.setContentAreaFilled(false);
    }
    
    protected void paintComponent(final Graphics g) {
        if (this.getModel().isPressed()) {
            g.setColor(this.pressedBackgroundColor);
        } else if (this.getModel().isRollover()) {
            g.setColor(this.hoverBackgroundColor);
        } else g.setColor(this.getBackground());
        
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        if (this.getModel().isPressed()) {
            g.setColor(this.pressedTextColor);
        } else if (this.getModel().isRollover()) {
            g.setColor(this.hoverTextColor);
        } else g.setColor(this.getForeground());
        
        g.drawString(this.getText(), (int)(this.getWidth() / 2 - g.getFontMetrics().getStringBounds(this.getText(), g).getWidth() / 2.0), (int)(this.getHeight() / 2 - g.getFontMetrics().getStringBounds(this.getText(), g).getHeight() / 2.0 + this.getFont().getSize() * 0.83333));
    }
    public void setContentAreaFilled(final boolean b) {
        this.setContentAreaFilled(b);
    }
    
    public Color getHoverBackgroundColor() {
        return this.hoverBackgroundColor;
    }
    public void setHoverBackgroundColor(final Color hoverBackgroundColor) {
        this.hoverBackgroundColor = hoverBackgroundColor;
    }
    
    public Color getPressedBackgroundColor() {
        return this.pressedBackgroundColor;
    }
    public void setPressedBackgroundColor(final Color pressedBackgroundColor) {
        this.pressedBackgroundColor = pressedBackgroundColor;
    }
    
    public Color getHoverTextColor() {
        return this.hoverTextColor;
    }
    public void setHoverTextColor(final Color hoverTextColor) {
        this.hoverTextColor = hoverTextColor;
    }
    
    public Color getPressedTextColor() {
        return this.pressedTextColor;
    }
    public void setPressedTextColor(final Color pressedTextColor) {
        this.pressedTextColor = pressedTextColor;
    }
}