
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;  
import java.awt.event.KeyListener;  
import java.awt.event.MouseEvent;  
import java.awt.event.MouseMotionListener;  
import java.util.Timer;  
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;  
import javax.swing.JPanel;  
  
/* 
 * 1--����+���+�̶� 
 * 2--������ϻ���һ��С�����봦�� 
 * 3--��С������ 
 * 4--��С����˶�����ͷ���ֵ�����жϣ������д������������ʵ��С��ķ��� 
 * 5--������Ȳ�ʵ�������������ƶ� 
 * 6--С���������Ⱥ�û���������ȵ�������� 
 *  
 */  
public class TTQGame extends JFrame implements MouseMotionListener{  

	private static final long serialVersionUID = 1L;  
    private int fw = 1000 ; //���ڵĳߴ�  
    private int fh = 462 ;  
    private TTQP ttqp = null ;  
    private int bx = 200 ; //��ĳ�ʼλ��  
    private int by = 200 ;  
    private int b2r = 40 ; //��ĳߴ�  
    private String direction = "right_down" ; //��ʼ���� ����  
    private Timer timer = new Timer();  
    private int block_x = 350 ;  //���ȵĳ�ʼλ��
    private int block_y = 442 ;  
    private int block_w = 120 ;  
    private int block_h = 50 ;    
    private double speed = 1.0 ;  //�ٶ�
    private int score = 0 ;  //����
    

    private ImageIcon image;
    private String path; // ���ȵĴ洢λ��
    private ImageIcon img;//����ͼ
    private JLabel imgLabel;//������ǩ
    private Container cp;
    
    /*
     * ���캯��
     */
    public TTQGame(String path) { // path�����ȵĴ洢λ��
        this.setAlwaysOnTop(true);  
        this.setUndecorated(true);
       	this.path = path;
        
        //this.getContentPane().setBackground(Color.DARK_GRAY);  
        //layeredPane=new JLayeredPane();
       
        //������������Щ����

        img = new ImageIcon("img.png");//���Ǳ���ͼƬ
        imgLabel = new JLabel(img);//������ͼ���ڱ�ǩ�

        this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
        //ע�������ǹؼ�����������ǩ��ӵ�jframe��LayeredPane����
      	imgLabel.setBounds(0, 0, 1000, 562);//���ñ�����ǩ��λ��
        cp=this.getContentPane();
        cp.setLayout(new BorderLayout());

        ((JPanel)cp).setOpaque(false); //ע����������������Ϊ͸��������LayeredPane����еı���������ʾ������

        ttqp = new TTQP();  
        this.add(ttqp);
        // layeredPane.add(jp,JLayeredPane.DEFAULT_LAYER);
        //layeredPane.add(ttqp,JLayeredPane.MODAL_LAYER);
        //this.setLayeredPane(layeredPane);
        this.setSize(fw, fh + 100);  
        this.setLocationRelativeTo(null);  
        this.setResizable(false);  
        this.setVisible(true);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addMouseMotionListener(this);  
        this.addKeyListener(new KeyListener() {  
            @Override  
            public void keyTyped(KeyEvent e) {}  
            @Override  
            public void keyReleased(KeyEvent e) {}  
            @Override  
            public void keyPressed(KeyEvent e) {if(e.getKeyCode()==32){System.exit(0);}}});//�ո���˳�  
    }  
    
    class TTQP extends JPanel{  
        private static final long serialVersionUID = 1L;  
        public TTQP() {
            this.setOpaque(false);  
            timer.schedule(new TimerTask() {  
                @Override  
                public void run() {  //ʵ��С����ƶ�  
                    /* 
                     * �����ÿһ����������괦�� 
                     */  
                    if("right_down".equalsIgnoreCase(direction)){ //���·����λ�ô���  
                        bx += speed ;  by += speed  ;  
                    }  
                    if("right_up".equalsIgnoreCase(direction)){ //���Ϸ����λ�ô���  
                        bx += speed ;  by -= speed ;  
                    }  
                    if("left_up".equalsIgnoreCase(direction)){//���Ϸ����λ�ô���  
                        bx -= speed ;  by -= speed ;  
                    }  
                    if("left_down".equalsIgnoreCase(direction)){//���·����λ�ô���  
                        bx -= speed ;  by += speed ;  
                    }  
                    /* 
                     * ����÷���ʲô���ʱ���������С��ķ��� 
                     */  
//                  ���С��ײ�y����ֵ>=����ͷ����y����    by + b2r >= block_y+50    
//                  ����С��ײ�x����ֵ>=�����ϱߵ�x����    bx + b2r/2 >= block_x   
//                  ����<=�����ϱߵ�x����ֵ+���ȵĿ��       bx + b2r/2 <= block_x + block_w          
//                  ��ô �����ı䷽��         
//                  ���� ����ֹͣ GAME OVER          
                    if(by + b2r >= block_y+50 && bx + b2r/2 >= block_x && bx + b2r/2 <= block_x + block_w){  
                        if("right_down".equalsIgnoreCase(direction)){ //С����������  
                            direction = "right_up" ;  
                            speed += 0.5 ;  
                            score ++ ;  
                        }else{ //С����������  
                            direction = "left_up" ;  
                            speed += 0.5 ;  
                            score ++;  
                        }  
                    }  
                    if(by + b2r >= fh+100){  
                        JOptionPane.showMessageDialog(ttqp, "GAME OVER !", "�����ʾ��Ϣ",JOptionPane.DEFAULT_OPTION);  
                        Runtime.getRuntime().exit(0);  
                    }  
                      
                    if(bx+b2r>=fw){ //�ұߵ��ж�  
                        if("right_up".equalsIgnoreCase(direction)){ //С������ ����  
                            direction = "left_up" ;  
                        }else{  //С����������  
                            direction = "left_down" ;  
                        }  
                    }  
                    if(by<=0){ //���ߵ��ж�  
                        if("left_up".equalsIgnoreCase(direction)){ //С������ ����  
                            direction = "left_down" ;  
                        }else{  //С����������  
                            direction = "right_down" ;  
                        }  
                    }  
                    if(bx<=0){ //��ߵ��ж�  
                        if("left_up".equalsIgnoreCase(direction)){ //С������ ����  
                            direction = "right_up" ;  
                        }else{  //С����������  
                            direction = "right_down" ;  
                        }  
                    }  
                    TTQP.this.repaint() ;  
                }  
            }, 0 , 10) ;  
        }  
        @Override  
        public void paint(Graphics g) {  
            g.setColor(Color.WHITE);//�����ɫ  
            g.fillOval(bx, by, b2r, b2r);  //����
              
            super.paintComponent(g);
            image = new ImageIcon(path); //����ͼƬ 
            Image imop = image.getImage();
            g.drawImage(imop, block_x, block_y, block_w, block_h+100,this); //��������ͼƬ               
            g.setColor(Color.RED) ;  
            g.drawString("SCORE : " + score, 20, 20) ;  //��ʾ����
        }  
    }  
    
    public static void main(String[] args) {  
        new TTQGame("C:\\Users\\wxr302124\\Desktop\\myFacu.png");  
    }  
    
    @Override  
    public void mouseDragged(MouseEvent e) { //��ק���� ʵ�ֿ������ȵ��ƶ�  
        block_x = e.getX();  
        if (block_x <=0){  
            block_x = 0 ;  
        }  
        if (block_x+block_w >=fw){  
            block_x = fw-block_w ;  
        }  
        this.repaint() ;  
    }  
    
    @Override  
    public void mouseMoved(MouseEvent e) {}  
}
