//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Model2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.FileImageOutputStream;

public class HeatChart {
    public static final double SCALE_LOGARITHMIC = 0.3D;
    public static final double SCALE_LINEAR = 1.0D;
    public static final double SCALE_EXPONENTIAL = 3.0D;
    private double[][] zValues;
    private Object[] xValues;
    private Object[] yValues;
    private boolean xValuesHorizontal;
    private boolean yValuesHorizontal;
    private Dimension cellSize;
    private Dimension chartSize;
    private int margin;
    private Color backgroundColour;
    private String title;
    private Font titleFont;
    private Color titleColour;
    private Dimension titleSize;
    private int titleAscent;
    private int axisThickness;
    private Color axisColour;
    private Font axisLabelsFont;
    private Color axisLabelColour;
    private String xAxisLabel;
    private String yAxisLabel;
    private Color axisValuesColour;
    private Font axisValuesFont;
    private int xAxisValuesFrequency;
    private int yAxisValuesFrequency;
    private boolean showXAxisValues;
    private boolean showYAxisValues;
    private int xAxisValuesHeight;
    private int xAxisValuesWidthMax;
    private int yAxisValuesHeight;
    private int yAxisValuesAscent;
    private int yAxisValuesWidthMax;
    private Dimension xAxisLabelSize;
    private int xAxisLabelDescent;
    private Dimension yAxisLabelSize;
    private int yAxisLabelAscent;
    private Color highValueColour;
    private Color lowValueColour;
    private int colourValueDistance;
    private double lowValue;
    private double highValue;
    private Point heatMapTL;
    private Point heatMapBR;
    private Point heatMapC;
    private Dimension heatMapSize;
    private double colourScale;

    public HeatChart(double[][] var1) {
        this(var1, min(var1), max(var1));
    }

    public HeatChart(double[][] var1, double var2, double var4) {
        this.zValues = var1;
        this.lowValue = var2;
        this.highValue = var4;
        this.setXValues(0.0D, 1.0D);
        this.setYValues(0.0D, 1.0D);
        this.cellSize = new Dimension(20, 20);
        this.margin = 20;
        this.backgroundColour = Color.WHITE;
        this.title = null;
        this.titleFont = new Font("Sans-Serif", 1, 16);
        this.titleColour = Color.BLACK;
        this.xAxisLabel = null;
        this.yAxisLabel = null;
        this.axisThickness = 2;
        this.axisColour = Color.BLACK;
        this.axisLabelsFont = new Font("Sans-Serif", 0, 12);
        this.axisLabelColour = Color.BLACK;
        this.axisValuesColour = Color.BLACK;
        this.axisValuesFont = new Font("Sans-Serif", 0, 10);
        this.xAxisValuesFrequency = 1;
        this.xAxisValuesHeight = 0;
        this.xValuesHorizontal = false;
        this.showXAxisValues = true;
        this.showYAxisValues = true;
        this.yAxisValuesFrequency = 1;
        this.yAxisValuesHeight = 0;
        this.yValuesHorizontal = true;
        this.highValueColour = Color.BLACK;
        this.lowValueColour = Color.WHITE;
        this.colourScale = 1.0D;
        this.updateColourDistance();
    }

    public double getLowValue() {
        return this.lowValue;
    }

    public double getHighValue() {
        return this.highValue;
    }

    public double[][] getZValues() {
        return this.zValues;
    }

    public void setZValues(double[][] var1) {
        this.setZValues(var1, min(var1), max(var1));
    }

    public void setZValues(double[][] var1, double var2, double var4) {
        this.zValues = var1;
        this.lowValue = var2;
        this.highValue = var4;
    }

    public void setXValues(double var1, double var3) {
        this.xValues = new Object[this.zValues[0].length];

        for(int var5 = 0; var5 < this.zValues[0].length; ++var5) {
            this.xValues[var5] = var1 + (double)var5 * var3;
        }

    }

    public void setXValues(Object[] var1) {
        this.xValues = var1;
    }

    public void setYValues(double var1, double var3) {
        this.yValues = new Object[this.zValues.length];

        for(int var5 = 0; var5 < this.zValues.length; ++var5) {
            this.yValues[var5] = var1 + (double)var5 * var3;
        }

    }

    public void setYValues(Object[] var1) {
        this.yValues = var1;
    }

    public Object[] getXValues() {
        return this.xValues;
    }

    public Object[] getYValues() {
        return this.yValues;
    }

    public void setXValuesHorizontal(boolean var1) {
        this.xValuesHorizontal = var1;
    }

    public boolean isXValuesHorizontal() {
        return this.xValuesHorizontal;
    }

    public void setYValuesHorizontal(boolean var1) {
        this.yValuesHorizontal = var1;
    }

    public boolean isYValuesHorizontal() {
        return this.yValuesHorizontal;
    }

    /** @deprecated */
    @Deprecated
    public void setCellWidth(int var1) {
        this.setCellSize(new Dimension(var1, this.cellSize.height));
    }

    /** @deprecated */
    @Deprecated
    public int getCellWidth() {
        return this.cellSize.width;
    }

    /** @deprecated */
    @Deprecated
    public void setCellHeight(int var1) {
        this.setCellSize(new Dimension(this.cellSize.width, var1));
    }

    /** @deprecated */
    @Deprecated
    public int getCellHeight() {
        return this.cellSize.height;
    }

    public void setCellSize(Dimension var1) {
        this.cellSize = var1;
    }

    public Dimension getCellSize() {
        return this.cellSize;
    }

    /** @deprecated */
    @Deprecated
    public int getChartWidth() {
        return this.chartSize.width;
    }

    /** @deprecated */
    @Deprecated
    public int getChartHeight() {
        return this.chartSize.height;
    }

    public Dimension getChartSize() {
        return this.chartSize;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String var1) {
        this.title = var1;
    }

    public String getXAxisLabel() {
        return this.xAxisLabel;
    }

    public void setXAxisLabel(String var1) {
        this.xAxisLabel = var1;
    }

    public String getYAxisLabel() {
        return this.yAxisLabel;
    }

    public void setYAxisLabel(String var1) {
        this.yAxisLabel = var1;
    }

    public int getChartMargin() {
        return this.margin;
    }

    public void setChartMargin(int var1) {
        this.margin = var1;
    }

    public Color getBackgroundColour() {
        return this.backgroundColour;
    }

    public void setBackgroundColour(Color var1) {
        if (var1 == null) {
            var1 = Color.WHITE;
        }

        this.backgroundColour = var1;
    }

    public Font getTitleFont() {
        return this.titleFont;
    }

    public void setTitleFont(Font var1) {
        this.titleFont = var1;
    }

    public Color getTitleColour() {
        return this.titleColour;
    }

    public void setTitleColour(Color var1) {
        this.titleColour = var1;
    }

    public int getAxisThickness() {
        return this.axisThickness;
    }

    public void setAxisThickness(int var1) {
        this.axisThickness = var1;
    }

    public Color getAxisColour() {
        return this.axisColour;
    }

    public void setAxisColour(Color var1) {
        this.axisColour = var1;
    }

    public Font getAxisLabelsFont() {
        return this.axisLabelsFont;
    }

    public void setAxisLabelsFont(Font var1) {
        this.axisLabelsFont = var1;
    }

    public Color getAxisLabelColour() {
        return this.axisLabelColour;
    }

    public void setAxisLabelColour(Color var1) {
        this.axisLabelColour = var1;
    }

    public Font getAxisValuesFont() {
        return this.axisValuesFont;
    }

    public void setAxisValuesFont(Font var1) {
        this.axisValuesFont = var1;
    }

    public Color getAxisValuesColour() {
        return this.axisValuesColour;
    }

    public void setAxisValuesColour(Color var1) {
        this.axisValuesColour = var1;
    }

    public int getXAxisValuesFrequency() {
        return this.xAxisValuesFrequency;
    }

    public void setXAxisValuesFrequency(int var1) {
        this.xAxisValuesFrequency = var1;
    }

    public int getYAxisValuesFrequency() {
        return this.yAxisValuesFrequency;
    }

    public void setYAxisValuesFrequency(int var1) {
        this.yAxisValuesFrequency = var1;
    }

    public boolean isShowXAxisValues() {
        return this.showXAxisValues;
    }

    public void setShowXAxisValues(boolean var1) {
        this.showXAxisValues = var1;
    }

    public boolean isShowYAxisValues() {
        return this.showYAxisValues;
    }

    public void setShowYAxisValues(boolean var1) {
        this.showYAxisValues = var1;
    }

    public Color getHighValueColour() {
        return this.highValueColour;
    }

    public void setHighValueColour(Color var1) {
        this.highValueColour = var1;
        this.updateColourDistance();
    }

    public Color getLowValueColour() {
        return this.lowValueColour;
    }

    public void setLowValueColour(Color var1) {
        this.lowValueColour = var1;
        this.updateColourDistance();
    }

    public double getColourScale() {
        return this.colourScale;
    }

    public void setColourScale(double var1) {
        this.colourScale = var1;
    }

    private void updateColourDistance() {
        int var1 = this.lowValueColour.getRed();
        int var2 = this.lowValueColour.getGreen();
        int var3 = this.lowValueColour.getBlue();
        int var4 = this.highValueColour.getRed();
        int var5 = this.highValueColour.getGreen();
        int var6 = this.highValueColour.getBlue();
        this.colourValueDistance = Math.abs(var1 - var4);
        this.colourValueDistance += Math.abs(var2 - var5);
        this.colourValueDistance += Math.abs(var3 - var6);
    }

    public void saveToFile(File var1) throws IOException {
        String var2 = var1.getName();
        int var3 = var2.lastIndexOf(46);
        if (var3 < 0) {
            throw new IOException("Illegal filename, no extension used.");
        } else {
            String var4 = var2.substring(var3 + 1);
            BufferedImage var5;
            if (!var4.toLowerCase().equals("jpg") && !var4.toLowerCase().equals("jpeg")) {
                var5 = (BufferedImage)this.getChartImage(true);
                ImageIO.write(var5, var4, var1);
            } else {
                var5 = (BufferedImage)this.getChartImage(false);
                this.saveGraphicJpeg(var5, var1, 1.0F);
            }

        }
    }

    private void saveGraphicJpeg(BufferedImage var1, File var2, float var3) throws IOException {
        Iterator var4 = ImageIO.getImageWritersByFormatName("jpeg");
        ImageWriter var5 = (ImageWriter)var4.next();
        ImageWriteParam var6 = var5.getDefaultWriteParam();
        var6.setCompressionMode(2);
        var6.setCompressionQuality(var3);
        FileImageOutputStream var7 = new FileImageOutputStream(var2);
        var5.setOutput(var7);
        IIOImage var8 = new IIOImage(var1, (List)null, (IIOMetadata)null);
        var5.write((IIOMetadata)null, var8, var6);
        var5.dispose();
    }

    public Image getChartImage(boolean var1) {
        this.measureComponents();
        this.updateCoordinates();
        int var2 = var1 ? 6 : 5;
        BufferedImage var3 = new BufferedImage(this.chartSize.width, this.chartSize.height, var2);
        Graphics2D var4 = var3.createGraphics();
        var4.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        var4.setColor(this.backgroundColour);
        var4.fillRect(0, 0, this.chartSize.width, this.chartSize.height);
        this.drawTitle(var4);
        this.drawHeatMap(var4, this.zValues);
        this.drawXLabel(var4);
        this.drawYLabel(var4);
        this.drawAxisBars(var4);
        this.drawXValues(var4);
        this.drawYValues(var4);
        return var3;
    }

    public Image getChartImage() {
        return this.getChartImage(false);
    }

    private void measureComponents() {
        BufferedImage var1 = new BufferedImage(1, 1, 2);
        Graphics2D var2 = var1.createGraphics();
        FontMetrics var3;
        if (this.title != null) {
            var2.setFont(this.titleFont);
            var3 = var2.getFontMetrics();
            this.titleSize = new Dimension(var3.stringWidth(this.title), var3.getHeight());
            this.titleAscent = var3.getAscent();
        } else {
            this.titleSize = new Dimension(0, 0);
        }

        if (this.xAxisLabel != null) {
            var2.setFont(this.axisLabelsFont);
            var3 = var2.getFontMetrics();
            this.xAxisLabelSize = new Dimension(var3.stringWidth(this.xAxisLabel), var3.getHeight());
            this.xAxisLabelDescent = var3.getDescent();
        } else {
            this.xAxisLabelSize = new Dimension(0, 0);
        }

        if (this.yAxisLabel != null) {
            var2.setFont(this.axisLabelsFont);
            var3 = var2.getFontMetrics();
            this.yAxisLabelSize = new Dimension(var3.stringWidth(this.yAxisLabel), var3.getHeight());
            this.yAxisLabelAscent = var3.getAscent();
        } else {
            this.yAxisLabelSize = new Dimension(0, 0);
        }

        Object[] var4;
        int var5;
        int var6;
        Object var7;
        int var8;
        if (this.showXAxisValues) {
            var2.setFont(this.axisValuesFont);
            var3 = var2.getFontMetrics();
            this.xAxisValuesHeight = var3.getHeight();
            this.xAxisValuesWidthMax = 0;
            var4 = this.xValues;
            var5 = var4.length;

            for(var6 = 0; var6 < var5; ++var6) {
                var7 = var4[var6];
                var8 = var3.stringWidth(var7.toString());
                if (var8 > this.xAxisValuesWidthMax) {
                    this.xAxisValuesWidthMax = var8;
                }
            }
        } else {
            this.xAxisValuesHeight = 0;
        }

        if (this.showYAxisValues) {
            var2.setFont(this.axisValuesFont);
            var3 = var2.getFontMetrics();
            this.yAxisValuesHeight = var3.getHeight();
            this.yAxisValuesAscent = var3.getAscent();
            this.yAxisValuesWidthMax = 0;
            var4 = this.yValues;
            var5 = var4.length;

            for(var6 = 0; var6 < var5; ++var6) {
                var7 = var4[var6];
                var8 = var3.stringWidth(var7.toString());
                if (var8 > this.yAxisValuesWidthMax) {
                    this.yAxisValuesWidthMax = var8;
                }
            }
        } else {
            this.yAxisValuesHeight = 0;
        }

        int var9 = this.zValues[0].length * this.cellSize.width;
        int var10 = this.zValues.length * this.cellSize.height;
        this.heatMapSize = new Dimension(var9, var10);
        boolean var11 = false;
        if (this.yValuesHorizontal) {
            var5 = this.yAxisValuesWidthMax;
        } else {
            var5 = this.yAxisValuesHeight;
        }

        boolean var12 = false;
        if (this.xValuesHorizontal) {
            var6 = this.xAxisValuesHeight;
        } else {
            var6 = this.xAxisValuesWidthMax;
        }

        int var13 = var9 + 2 * this.margin + this.yAxisLabelSize.height + var5 + this.axisThickness;
        var8 = var10 + 2 * this.margin + this.xAxisLabelSize.height + var6 + this.titleSize.height + this.axisThickness;
        this.chartSize = new Dimension(var13, var8);
    }

    private void updateCoordinates() {
        int var1 = this.margin + this.axisThickness + this.yAxisLabelSize.height;
        var1 += this.yValuesHorizontal ? this.yAxisValuesWidthMax : this.yAxisValuesHeight;
        int var2 = this.titleSize.height + this.margin;
        this.heatMapTL = new Point(var1, var2);
        var1 = this.heatMapTL.x + this.heatMapSize.width;
        var2 = this.heatMapTL.y + this.heatMapSize.height;
        this.heatMapBR = new Point(var1, var2);
        var1 = this.heatMapTL.x + this.heatMapSize.width / 2;
        var2 = this.heatMapTL.y + this.heatMapSize.height / 2;
        this.heatMapC = new Point(var1, var2);
    }

    private void drawTitle(Graphics2D var1) {
        if (this.title != null) {
            int var2 = this.margin / 2 + this.titleAscent;
            int var3 = this.chartSize.width / 2 - this.titleSize.width / 2;
            var1.setFont(this.titleFont);
            var1.setColor(this.titleColour);
            var1.drawString(this.title, var3, var2);
        }

    }

    private void drawHeatMap(Graphics2D var1, double[][] var2) {
        int var3 = var2.length;
        int var4 = var2[0].length;
        BufferedImage var5 = new BufferedImage(this.heatMapSize.width, this.heatMapSize.height, 2);
        Graphics2D var6 = var5.createGraphics();

        for(int var7 = 0; var7 < var4; ++var7) {
            for(int var8 = 0; var8 < var3; ++var8) {
                var6.setColor(this.getCellColour(var2[var8][var7], this.lowValue, this.highValue));
                int var9 = var7 * this.cellSize.width;
                int var10 = var8 * this.cellSize.height;
                var6.fillRect(var9, var10, this.cellSize.width, this.cellSize.height);
            }
        }

        var1.drawImage(var5, this.heatMapTL.x, this.heatMapTL.y, this.heatMapSize.width, this.heatMapSize.height, (ImageObserver)null);
    }

    private void drawXLabel(Graphics2D var1) {
        if (this.xAxisLabel != null) {
            int var2 = this.chartSize.height - this.margin / 2 - this.xAxisLabelDescent;
            int var3 = this.heatMapC.x - this.xAxisLabelSize.width / 2;
            var1.setFont(this.axisLabelsFont);
            var1.setColor(this.axisLabelColour);
            var1.drawString(this.xAxisLabel, var3, var2);
        }

    }

    private void drawYLabel(Graphics2D var1) {
        if (this.yAxisLabel != null) {
            int var2 = this.heatMapC.y + this.yAxisLabelSize.width / 2;
            int var3 = this.margin / 2 + this.yAxisLabelAscent;
            var1.setFont(this.axisLabelsFont);
            var1.setColor(this.axisLabelColour);
            AffineTransform var4 = var1.getTransform();
            AffineTransform var5 = (AffineTransform)var4.clone();
            var4.rotate(Math.toRadians(270.0D), (double)var3, (double)var2);
            var1.setTransform(var4);
            var1.drawString(this.yAxisLabel, var3, var2);
            var1.setTransform(var5);
        }

    }

    private void drawAxisBars(Graphics2D var1) {
        if (this.axisThickness > 0) {
            var1.setColor(this.axisColour);
            int var2 = this.heatMapTL.x - this.axisThickness;
            int var3 = this.heatMapBR.y;
            int var4 = this.heatMapSize.width + this.axisThickness;
            int var5 = this.axisThickness;
            var1.fillRect(var2, var3, var4, var5);
            var2 = this.heatMapTL.x - this.axisThickness;
            var3 = this.heatMapTL.y;
            var4 = this.axisThickness;
            var5 = this.heatMapSize.height;
            var1.fillRect(var2, var3, var4, var5);
        }

    }

    private void drawXValues(Graphics2D var1) {
        if (this.showXAxisValues) {
            var1.setColor(this.axisValuesColour);

            for(int var2 = 0; var2 < this.xValues.length; ++var2) {
                if (var2 % this.xAxisValuesFrequency == 0) {
                    String var3 = this.xValues[var2].toString();
                    var1.setFont(this.axisValuesFont);
                    FontMetrics var4 = var1.getFontMetrics();
                    int var5 = var4.stringWidth(var3);
                    int var6;
                    int var7;
                    if (this.xValuesHorizontal) {
                        var6 = var2 * this.cellSize.width + (this.cellSize.width / 2 - var5 / 2);
                        var6 += this.heatMapTL.x;
                        var7 = this.heatMapBR.y + var4.getAscent() + 1;
                        var1.drawString(var3, var6, var7);
                    } else {
                        var6 = this.heatMapTL.x + var2 * this.cellSize.width + this.cellSize.width / 2 + this.xAxisValuesHeight / 2;
                        var7 = this.heatMapBR.y + this.axisThickness + var5;
                        AffineTransform var8 = var1.getTransform();
                        AffineTransform var9 = (AffineTransform)var8.clone();
                        var8.rotate(Math.toRadians(270.0D), (double)var6, (double)var7);
                        var1.setTransform(var8);
                        var1.drawString(var3, var6, var7);
                        var1.setTransform(var9);
                    }
                }
            }

        }
    }

    private void drawYValues(Graphics2D var1) {
        if (this.showYAxisValues) {
            var1.setColor(this.axisValuesColour);

            for(int var2 = 0; var2 < this.yValues.length; ++var2) {
                if (var2 % this.yAxisValuesFrequency == 0) {
                    String var3 = this.yValues[var2].toString();
                    var1.setFont(this.axisValuesFont);
                    FontMetrics var4 = var1.getFontMetrics();
                    int var5 = var4.stringWidth(var3);
                    int var6;
                    int var7;
                    if (this.yValuesHorizontal) {
                        var6 = this.margin + this.yAxisLabelSize.height + (this.yAxisValuesWidthMax - var5);
                        var7 = this.heatMapTL.y + var2 * this.cellSize.height + this.cellSize.height / 2 + this.yAxisValuesAscent / 2;
                        var1.drawString(var3, var6, var7);
                    } else {
                        var6 = this.margin + this.yAxisLabelSize.height + this.yAxisValuesAscent;
                        var7 = this.heatMapTL.y + var2 * this.cellSize.height + this.cellSize.height / 2 + var5 / 2;
                        AffineTransform var8 = var1.getTransform();
                        AffineTransform var9 = (AffineTransform)var8.clone();
                        var8.rotate(Math.toRadians(270.0D), (double)var6, (double)var7);
                        var1.setTransform(var8);
                        var1.drawString(var3, var6, var7);
                        var1.setTransform(var9);
                    }
                }
            }

        }
    }

    private Color getCellColour(double var1, double var3, double var5) {
        double var7 = var5 - var3;
        double var9 = var1 - var3;
        double var11 = var9 / var7;
        int var13 = this.getColourPosition(var11);
        int var14 = this.lowValueColour.getRed();
        int var15 = this.lowValueColour.getGreen();
        int var16 = this.lowValueColour.getBlue();

        for(int var17 = 0; var17 < var13; ++var17) {
            int var18 = var14 - this.highValueColour.getRed();
            int var19 = var15 - this.highValueColour.getGreen();
            int var20 = var16 - this.highValueColour.getBlue();
            if (Math.abs(var18) >= Math.abs(var19) && Math.abs(var18) >= Math.abs(var20)) {
                var14 = this.changeColourValue(var14, var18);
            } else if (Math.abs(var19) >= Math.abs(var20)) {
                var15 = this.changeColourValue(var15, var19);
            } else {
                var16 = this.changeColourValue(var16, var20);
            }
        }

        return new Color(var14, var15, var16);
    }

    private int getColourPosition(double var1) {
        return (int)Math.round((double)this.colourValueDistance * Math.pow(var1, this.colourScale));
    }

    private int changeColourValue(int var1, int var2) {
        if (var2 < 0) {
            return var1 + 1;
        } else {
            return var2 > 0 ? var1 - 1 : var1;
        }
    }

    public static double max(double[][] var0) {
        double var1 = 0.0D;

        for(int var3 = 0; var3 < var0.length; ++var3) {
            for(int var4 = 0; var4 < var0[var3].length; ++var4) {
                var1 = var0[var3][var4] > var1 ? var0[var3][var4] : var1;
            }
        }

        return var1;
    }

    public static double min(double[][] var0) {
        double var1 = 1.7976931348623157E308D;

        for(int var3 = 0; var3 < var0.length; ++var3) {
            for(int var4 = 0; var4 < var0[var3].length; ++var4) {
                var1 = var0[var3][var4] < var1 ? var0[var3][var4] : var1;
            }
        }

        return var1;
    }
}

