package ai.beans;

public class ColorBean {
    
    private String foregroundColor;
    private String backgroundColor;
    private boolean visibleBorders;
    
    public ColorBean() {
        
    }

    /**
     * @return the foregroundColor
     */
    public String getForegroundColor() {
        return foregroundColor;
    }

    /**
     * @param foregroundColor the foregroundColor to set
     */
    public void setForegroundColor(String foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    /**
     * @return the backgroundColor
     */
    public String getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * @param backgroundColor the backgroundColor to set
     */
    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * @return the visibleBorders
     */
    public boolean areVisibleBorders() {
        return visibleBorders;
    }

    /**
     * @param visibleBorders the visibleBorders to set
     */
    public void setVisibleBorders(boolean visibleBorders) {
        this.visibleBorders = visibleBorders;
    }
    
}
