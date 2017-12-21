
import java.awt.*;
import java.io.*;  
import javax.swing.*;
public class WelcomePage extends JFrame {
	
	//����һ������
	Container ct;
	//����������塣
	BackgroundPanel bgp;

	//����һ����ť������֤�����ǵ�ȷ�Ǵ����˱���ͼƬ��������һ��ͼƬ��
	JButton jb;

	public static void main(String[] args) throws IOException {
		new WelcomePage(); 
		Music sound = new Music("background.wav");
		InputStream stream =new ByteArrayInputStream(sound.getSamples());
		// play the sound
		sound.play(stream);
	}
	
	public WelcomePage() {
		setTitle("OurMYOTee");
		//�������κβ��ַ�ʽ��
		ct=this.getContentPane();
		this.setLayout(null);

		//�����������һ��400*300����Ƭ�ȿ��Կ������Խ����
		bgp=new BackgroundPanel((new ImageIcon("bgp.png")).getImage());
		bgp.setBounds(0,0,1010,700);
		ct.add(bgp);

		//������ť
		Icon i = new ImageIcon("point.png");
		jb=new JButton();
		jb.setIcon(i);
		jb.setBounds(440,100,140,76);
		ct.add(jb);
		jb.addActionListener((event)->{ // ��ʾѡ�����
			this.dispose();
			SelectionPage p = new SelectionPage();
			p.setVisible(true);
		});

		this.setSize(1028,750);
		this.setLocation(0,0);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}

class BackgroundPanel extends JPanel {
	Image im;
	public BackgroundPanel(Image im) {
		this.im=im;
		this.setOpaque(true);
	}
	//Draw the back ground.
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this);
	}
}