import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class MainPage extends JFrame {

	/* project·�� */
	final static String dir_path = "";
	/* �Ա� */
	final int BOY = 0;
	final int GIRL = 1;
	/* ����  */
	final int WORDS = 0;
	final int CAP = 1;
	final int MOVEMENT = 2;
	final int HAIR = 3;
	final int CLOTHES = 4;
	final int GLASSES = 5;
	final int DECORATION = 6;
	final int EYEBROWS = 7;
	final int EYES = 8;
	final int MOUTH = 9;
	final int FACE = 10;
	final int BACKGROUND = 11;
	final String[] btnName = {
			"������", "ñ��", "����", "����",
			"�·�", "�۾�", "װ��", "üë",
			"�۾�", "���", "����", "����"};
	final int[][] lblLocation = { // �����ϸ���label��λ�ã�(x,y)
			{240, 0}, {80, 20}, {105, 165}, {50, 90}, 
			{120, 172}, {115, 100}, {75, 95}, {125, 80}, 
			{125, 100}, {150, 170}, {50, 90}, {25, 90}, 
	};
	final int[][] icSize = { // �����ϸ���ͼƬ�Ĵ�С��(width, height)
			{150, 150}, {290, 290}, {240, 200}, {350, 350}, 
			{200, 200}, {220, 205}, {300, 280}, {200, 200}, 
			{200, 200}, {150, 100}, {350, 350}, {400, 400}, 
	};
	
	/* Components */
	private JPanel contentPane;
	public int sex; // �Ա�
	public int part; // ���岿λ
	private JPanel pnlAvatar; // ͷ���������
	private JLabel[] lblPart; // ������ʾѡ��ͼƬ��ͼ��
	private JButton btnSave; // ����
	private JButton btnShare; // ����
	private JButton btnReturn; // ����
	private JButton btnReset; // ����
	private JPanel pnlPart; // ��ʾ���岿λ��ѡ��ť
	private ArrayList<JButton> btnParts;
	private JScrollPane scrollPane; // ��������
	private JPanel panel;
	private ArrayList<JButton> btnObjects; // �ҷ�ͼƬ��ť
	private ArrayList<ImageIcon> icons; // �����ڰ�ť����ʾ��icon������Ҫ����
	private ArrayList<ImageIcon> icons_2; // �����ڻ�������ʾ��icon
	private int temp_index_2; // ��ǰ�ǵڼ���ͼƬ
	//����������塣
	BackgroundPanel bgp;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage frame = new MainPage(0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainPage(int sex) {
		this.sex = sex;
		this.part = 0;
		setTitle("OurMYOTee");

		
		
		// ����contentPane
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1028, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//
		//�����������һ��400*300����Ƭ�ȿ��Կ������Խ����
		
		
		// ͷ��������� 
		pnlAvatar = new JPanel();
		pnlAvatar.setBounds(30, 83, 445, 507);
		pnlAvatar.setOpaque(false);
		contentPane.add(pnlAvatar);
		pnlAvatar.setLayout(null);
		lblPart = new JLabel[12]; // ������ʾѡ��ͼƬ��ͼ��
		for (int i = 0; i < 12; i++){
			lblPart[i] = new JLabel("");
			lblPart[i].setBounds(lblLocation[i][0], 
					lblLocation[i][1], 400, 400);
			pnlAvatar.add(lblPart[i]); // ���С��ͼ��������
			/*System.out.println(i);
			if (i==MOUTH)
				System.out.println("!" + i);*/
		}
		
		// ����
		btnSave = new JButton("����");
		btnSave.setBounds(264, 25, 95, 29);
		//btnSave.setOpaque(false);
		contentPane.add(btnSave);
		
		// ����
		btnShare = new JButton("����");
		btnShare.setBounds(380, 25, 95, 29);
		contentPane.add(btnShare);
		
		// ����
		btnReturn = new JButton("����");
		btnReturn.setBounds(30, 25, 95, 29);
		contentPane.add(btnReturn);
		
		// ����
		btnReset = new JButton("����");
		btnReset.setBounds(148, 25, 95, 29);
		contentPane.add(btnReset);
		
		// ѡ�����岿λ 
		pnlPart = new JPanel();
		pnlPart.setBounds(30, 600, 445, 70);
		contentPane.add(pnlPart);
		pnlPart.setLayout(new GridLayout(2, 6, 0, 0));
		btnParts = new ArrayList<JButton>();
		for (int i = 0; i < 12; i++){
			JButton btn = new JButton(btnName[i]);
			btnParts.add(btn);
			pnlPart.add(btn);
		}
		
		
		// ͼƬ 
		scrollPane = new JScrollPane();
		scrollPane.setBounds(504, 25, 419, 650);
		//scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(scrollPane);	
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		
		btnObjects = new ArrayList<JButton>();
		icons = new ArrayList<ImageIcon>();
		icons_2 = new ArrayList<ImageIcon>();
		
		Init();
		bgp=new BackgroundPanel((new ImageIcon(dir_path + "bg_m3.png")).getImage());
		bgp.setBounds(0,0,1010,700);
		contentPane.add(bgp);
		AddListeners();
	}
	
	/*
	 * ��ʼ��, ����Ĭ��ͷ����Ҳ���ͼƬ
	 */
	void Init(){
		// �ҷ���ͼƬ����Ĭ����ʾñ��
		btnObjects.clear();
		icons.clear();
		icons_2.clear();
		panel.removeAll();
		icons = get_icons(sex, part, 0);
		icons_2 = get_icons(sex, part, 1);
		JButton btn;
		ImageIcon ic;
		for (int i = 0; i < icons.size(); i++){
			btnObjects.add(new JButton(""));
			btn = (JButton)btnObjects.get(i);
			ic = (ImageIcon)icons.get(i);
			btn.setIcon(ic);
			btn.setPreferredSize(new Dimension(10,150)); // ���ð�ť�Ĺ̶���С
			panel.add(btn);
		}
		this.revalidate();
		for(int j = 0; j < icons.size(); j++)
		{
			JButton btn__2 = (JButton)btnObjects.get(j);
			// System.out.println("temp_index_2"+temp_index_2);
			btn__2.addActionListener((e)->{
				// System.out.println("jjjjjj");
				temp_index_2 = btnObjects.indexOf(btn__2);
				// System.out.println(temp_index_2);
				lblPart[part].setIcon(icons_2.get(temp_index_2));
			});
		}
		
		// Ĭ��ͷ��
		ImageIcon[] default_icon = new ImageIcon[12];
		String path0, path, p;
		if(sex == 0) 
			path0 = dir_path + "Filters\\0\\";
		else  
			path0 = dir_path + "Filters\\1\\";
		for (int i = 0; i < 12; i++){
			path = path0;
			p = "" + i;
			//System.out.println(p); // ���Ŀ¼�Ƿ���ȷ
			path = path + p;
			//System.out.println(path); // ���Ŀ¼�Ƿ���ȷ
			File dir = new File(path);
			String[] files = dir.list();
			File file0 = new File(dir, files[0]);
			File file1 = new File(dir, files[1]);
			String temp0 = path + "\\" + file0.getName();
			String temp1 = path + "\\" + file1.getName();
			//System.out.println(temp); // ���Ŀ¼�Ƿ���ȷ
			switch (i){
			case WORDS: default_icon[i] = null; break;
			case CAP: default_icon[i] = null; break;
			case MOVEMENT: default_icon[i] = null; break;
			case HAIR: 
				default_icon[i] = new ImageIcon(temp1); 
				default_icon[i].setImage(default_icon[i].getImage().getScaledInstance
						(icSize[i][0], icSize[i][1], Image.SCALE_DEFAULT));
				break;
			case CLOTHES: default_icon[i] = null; break;
			case GLASSES: default_icon[i] = null; break;
			case DECORATION: default_icon[i] = null; break;
			case EYEBROWS: 
				default_icon[i] = new ImageIcon(temp1); 
				default_icon[i].setImage(default_icon[i].getImage().getScaledInstance
						(icSize[i][0], icSize[i][1], Image.SCALE_DEFAULT));
				break;
			case EYES: 
				default_icon[i] = new ImageIcon(temp1); 
				default_icon[i].setImage(default_icon[i].getImage().getScaledInstance
						(icSize[i][0], icSize[i][1], Image.SCALE_DEFAULT));
				break;
			case MOUTH: 
				default_icon[i] = new ImageIcon(temp1); 
				default_icon[i].setImage(default_icon[i].getImage().getScaledInstance
						(icSize[i][0], icSize[i][1], Image.SCALE_DEFAULT));
				break;
			case FACE: 
				default_icon[i] = new ImageIcon(temp1); 
				default_icon[i].setImage(default_icon[i].getImage().getScaledInstance
						(icSize[i][0], icSize[i][1], Image.SCALE_DEFAULT));
				break;
			case BACKGROUND: default_icon[i] = null; break;
			}
			if (default_icon[i] != null)
				lblPart[i].setIcon(default_icon[i]);
			else
				lblPart[i].setIcon(new ImageIcon(temp0));
		}
	}
	
	/*
	 * ��Ӹ�����ť�ļ����¼�
	 */
	void AddListeners(){
		// ѡ�����岿λ����ʾ�ҷ���ͼƬ
		for (int i = 0; i < 12; i++){
			/*if (i==MOUTH){
				System.out.println("!!!");
			}*/
			JButton btn1 = (JButton)btnParts.get(i);
			btn1.addActionListener((event)->{
				//System.out.println("Begin!");
				btnObjects.clear();
				icons.clear();
				icons_2.clear();
				panel.removeAll();
				part = btnParts.indexOf(btn1);
				icons = get_icons(sex, part, 0);
				icons_2 = get_icons(sex, part, 1);
				JButton btn2;
				ImageIcon ic;
				for (int j = 0; j < icons.size(); j++){
					btnObjects.add(new JButton(""));
					btn2 = (JButton)btnObjects.get(j);
					ic = (ImageIcon)icons.get(j);
					btn2.setIcon(ic);
					btn2.setPreferredSize(new Dimension(10,150)); // ���ð�ť�Ĺ̶���С
					panel.add(btn2);
					//System.out.println(((JButton)btnObjects.get(0)).getName());
					//System.out.println("End!");
				}
				this.revalidate();
				for(int j = 0;j < icons.size();j++)
				{
					JButton btn__2 = (JButton)btnObjects.get(j);
					// System.out.println("temp_index_2"+temp_index_2);
					btn__2.addActionListener((e)->{
					// System.out.println("jjjjjj");
					temp_index_2 = btnObjects.indexOf(btn__2);
					// System.out.println(temp_index_2);
					lblPart[part].setIcon(icons_2.get(temp_index_2));});
				}
			});
		}
		
		// ����
		btnSave.addActionListener((event)->{
			new Saver(get_avatar());
		});
		// ����
		btnReturn.addActionListener((event)->{
			this.dispose();
			SelectionPage sp = new SelectionPage();
			sp.setVisible(true);
		});
		//����
		btnReset.addActionListener((event)->{
			Init();
		});
		//����
		btnShare.addActionListener((event)->{
			new Share();
		});
	}
	
	/*
	 * �������յ�ͷ��ͼƬ
	 */
	BufferedImage get_avatar(){
		Dimension imageSize = pnlAvatar.getSize();
	    BufferedImage image = new BufferedImage(imageSize.width,
	            imageSize.height, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g = image.createGraphics();
	    pnlAvatar.paint(g);
	    g.dispose();
	    return image;
	}
	
	/*
	 * ����id��ȡ��Ҫ��ͼƬ����
	 * need=0����ֵ��icons��need=1����ֵ��icons_2��
	 */
	ArrayList<ImageIcon> get_icons(int sex, int part, int need){
		ArrayList <ImageIcon> i = new ArrayList<ImageIcon>();
		String path;
		if(sex == 0) 
			path = dir_path + "Filters\\0\\";
		else  
			path = dir_path + "Filters\\1\\";
		String p = "" + part;
		
		path = path + p;
		// path �ļ���
		// �ڸ�Ŀ¼�������
		File dir = new File(path);
		String [] files = dir.list();
		for(int k = 0; k < files.length; k++){
			File file = new File(dir, files[k]);
			String temp = path + "\\" + file.getName(); // ��ǰͼƬ��·������
			ImageIcon t = new ImageIcon(temp);
			if (need == 1) {
				t.setImage(t.getImage().getScaledInstance(
						icSize[part][0], icSize[part][1], Image.SCALE_DEFAULT));
			}
			i.add(t);
		}
		return i;	
	}
}
