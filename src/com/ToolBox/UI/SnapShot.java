package com.ToolBox.UI;

import com.ToolBox.capture.GraphicsUtils;
import com.ToolBox.capture.ScreenWindow;
import com.ToolBox.history.Intent;
import org.joda.time.DateTime;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * ��Ļ��ͼС����
 *
 * @author ���
 */
class SnapShot extends JFrame {

    private static final long serialVersionUID = 2593517177840717431L;
    private final static int SMALL = 2;
    private final static int MEDIUM = 4;
    private final static int LARGE = 6;
    private final static int OVAL = 0;
    private final static int RECT = 1;
    private final static int PEN = 2;

    private final static String title = "��ͼС����";
    private final static String start = "��ʼ��ͼ�����Ҽ��˳���";
    private final static String oval = "Բ��";
    private final static String rect = "����";
    private final static String pen = "����";
    private final static String S = "С";
    private final static String M = "��";
    private final static String L = "��";
    private final static String save = "����";
    private final static String copy = "����";

    private int size = MEDIUM;
    private int flag = PEN;
    private JButton snapButton;
    private JLabel imageLabel;
    private JToggleButton btnOval;
    private JToggleButton btnRect;
    private JToggleButton btnPen;
    private JToggleButton btnSmall;
    private JToggleButton btnMedium;
    private JToggleButton btnLarge;
    private JButton btnCopy;
    private JButton btnSave;
    private BufferedImage bi;

    private int x, y;   //��¼�������
    private int startx, starty;

    /**
     * ��ͼ���
     */
    SnapShot() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(700, 400);
        setTitle(title);
        setLocationRelativeTo(null);

        initUI();
        initLayout();
        createAction();
        setVisible(true);
    }

    private void initUI() {
        snapButton = new JButton(start);
        imageLabel = new JLabel();

        btnOval = new JToggleButton(oval);
        btnRect = new JToggleButton(rect);
        btnPen = new JToggleButton(pen, true);
        ButtonGroup shape = new ButtonGroup();
        shape.add(btnOval);
        shape.add(btnRect);
        shape.add(btnPen);

        btnSmall = new JToggleButton(S);
        btnMedium = new JToggleButton(M, true);
        btnLarge = new JToggleButton(L);
        ButtonGroup size = new ButtonGroup();
        size.add(btnSmall);
        size.add(btnMedium);
        size.add(btnLarge);

        btnSave = new JButton(save);
        btnCopy = new JButton(copy);

    }

    private void initLayout() {
        setIconImage(Toolkit.getDefaultToolkit()
                .getImage(new Resource().toolboxURL));

        JPanel pane = new JPanel();
        pane.setBackground(Color.WHITE);
        pane.add(imageLabel);
        JScrollPane imgScrollPane = new JScrollPane(pane);

        Container container = getContentPane();
        container.setBackground(Color.WHITE);
        BorderLayout layout = new BorderLayout(2, 2);
        container.setLayout(layout);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        container.add(BorderLayout.NORTH, panel);
        panel.setOpaque(false);
        panel.add(snapButton);
        panel.add(btnOval);
        panel.add(btnRect);
        panel.add(btnPen);
        panel.add(btnSmall);
        panel.add(btnMedium);
        panel.add(btnLarge);
        panel.add(btnCopy);
        panel.add(btnSave);
        container.add(BorderLayout.CENTER, imgScrollPane);
        container.add(BorderLayout.SOUTH, new JLabel());
        container.add(BorderLayout.EAST, new JLabel());
        container.add(BorderLayout.WEST, new JLabel());

    }

    private void createAction() {
        snapButton.addActionListener(e -> {
            setVisible(false);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            try {
                JFrame screenShot = new ScreenWindow();
                screenShot.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        setVisible(true);
                        BufferedImage image = (BufferedImage) Intent.getObj(ScreenWindow.serialVersionUID,
                                ScreenWindow.imageIntent);
                        imageLabel.setIcon(new ImageIcon(image));
                    }
                });
            } catch (AWTException | InterruptedException e1) {
                e1.printStackTrace();
            }


        });

        btnOval.addActionListener(e -> flag = OVAL);

        btnRect.addActionListener(e -> flag = RECT);

        btnPen.addActionListener(e -> flag = PEN);

        btnSmall.addActionListener(e -> size = SMALL);

        btnMedium.addActionListener(e -> size = MEDIUM);

        btnLarge.addActionListener(e -> size = LARGE);

        btnCopy.addActionListener(e -> {
            Image image = ((ImageIcon) imageLabel.getIcon()).getImage();
            GraphicsUtils.setClipboardImage(image);
        });

        btnSave.addActionListener(e -> {

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            switch (fileChooser.showOpenDialog(null)) {
                case JFileChooser.APPROVE_OPTION:
                    Path path = fileChooser.getSelectedFile().toPath();
                    File file = new File(path.toString() + "\\screen capture " +
                            DateTime.now().toString(" yyyy-MM-dd HH_mm_ss") + ".png");
                    Image image = ((ImageIcon) imageLabel.getIcon()).getImage();
                    try {
                        ImageIO.write((BufferedImage) image, "png", file);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                default:
                    throw new IllegalArgumentException();

            }
        });

        imageLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                startx = e.getX();
                starty = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                x = e.getX();
                y = e.getY();

                Image img = ((ImageIcon) imageLabel.getIcon()).getImage();

                bi = new BufferedImage(img.getWidth(null), img.getHeight(null),
                        BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = (Graphics2D) bi.getGraphics();

                g2d.drawImage(img, 0, 0, null);

                g2d.setColor(Color.RED);
                g2d.setStroke(new BasicStroke(size, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
                Graphics g;
                switch (flag) {
                    case OVAL:
                        g2d.drawOval(Math.min(startx, x) - 1, Math.min(starty, y) - 1,
                                Math.abs(x - startx) + 1, Math.abs(y - starty) + 1);
                        g2d.dispose();
                        g = imageLabel.getGraphics();
                        g.drawImage(bi, 0, 0, null);
                        g.dispose();
                        break;
                    case RECT:
                        g2d.drawRect(Math.min(x, startx) - 1, Math.min(y, starty) - 1,
                                Math.abs(startx - x) + 1, Math.abs(starty - y) + 1);
                        g2d.dispose();
                        g = imageLabel.getGraphics();
                        g.drawImage(bi, 0, 0, null);
                        g.dispose();
                        break;
                    default:
                        break;
                }
                g2d.dispose();

                imageLabel.setIcon(new ImageIcon(bi));
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        imageLabel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                x = e.getX();
                y = e.getY();

                Image img = ((ImageIcon) imageLabel.getIcon()).getImage();

                BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = (Graphics2D) bi.getGraphics();

                g2d.drawImage(img, 0, 0, null);

                g2d.setColor(Color.RED);
                g2d.setStroke(new BasicStroke(size, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
                Graphics g;
                switch (flag) {
                    case OVAL:
                        g2d.drawOval(Math.min(x, startx) - 1, Math.min(y, starty) - 1,
                                Math.abs(startx - x) + 1, Math.abs(starty - y) + 1);
                        g2d.dispose();
                        g = imageLabel.getGraphics();
                        g.drawImage(bi, 0, 0, null);
                        g.dispose();
                        break;
                    case RECT:
                        g2d.drawRect(Math.min(x, startx) - 1, Math.min(y, starty) - 1,
                                Math.abs(startx - x) + 1, Math.abs(starty - y) + 1);
                        g2d.dispose();
                        g = imageLabel.getGraphics();
                        g.drawImage(bi, 0, 0, null);
                        g.dispose();
                        break;
                    case PEN:
                        g2d.drawOval(x, y, size, size);   //Java��û���ṩ��Ļ��ƣ�ʹ�������յ�Ϊͬһ����Ļ��ߴ���
                        g2d.dispose();

                        imageLabel.setIcon(new ImageIcon(bi));
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

    }
}
