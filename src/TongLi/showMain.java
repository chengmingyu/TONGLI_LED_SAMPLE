package TongLi;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;

import javax.swing.Timer;
import javax.swing.JRadioButton;

public class showMain {

	private JFrame frame;
	private JTextField gameTime_textField;
	private JTextField Jie_textField;

	private final String START_TXT = "启动显示";
	private final String END_TXT = "停止显示";
	private final String CLOCK_STOP_TXT = "暂停";
	private final String CLOCK_START_TXT = "启动";
	int iStartFlag;
	int iClock = 0;
	int iPasue = 1;
	int IPASUE = 100000;
	private long iSetTime;
	public long minute = 0;
	public long seconds = 0;
	public long hour = 0;

	private JTextField HostTeamPoint_textField;
	private JTextField GuestTeamPoint_textField;
	private JTextField HostTeamFoul_textField;
	private JTextField GuestTeamFoul_textField;
	private JTextField HostTeamPause_textField;
	private JTextField GuestTeamPause_textField;
	JLabel ClcokLabel = new JLabel("12:00");
	pingMu PINGMU = new pingMu();
	showPing SP = new showPing();

	// 计时器用
	// private int count = 12 * 60;
	private int count;
	private Timer timer;
	private final static int INTERVAL = 1000;
	String strR;
	String strM;
	String strS;
	DecimalFormat df = new DecimalFormat("00");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					showMain window = new showMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	class Task implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		//	while (iPasue == 0) {
				if (count < 1) {
					timer.stop();
				} else {
					count--;
					minute = (count - hour * 3600) / 60;
					seconds = count - hour * 3600 - minute * 60;
					strM = df.format(minute);
					strS = df.format(seconds);

					strR = strM + ":" + strS;
				}
			//	System.out.println(strR);
				ClcokLabel.setText(strR);
				SP.setClock(strR);
			}
	//	}
	}

	public void ClockInit() {
		count = Integer.valueOf(gameTime_textField.getText());
		count = count * 60;
		
		SP.setClock(gameTime_textField.getText()+":00");
		ClcokLabel.setText(gameTime_textField.getText()+":00");

	}

	public void run() {
//		ClockInit();
		timer.start();
	}

	/**
	 * Create the application.
	 */
	public showMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// 比赛默认时间12分钟
		int iGameTime = 12;
		int iGameJie = 0;
		iStartFlag = 0;

		final String strTitle = "抚顺通力 显示屏 比分控制台            V1.0";
		frame = new JFrame();
		frame.setTitle(strTitle);
		frame.setBounds(400, 400, 450, 597);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setUndecorated(false);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);

		Task task = new Task();
		timer = new Timer(INTERVAL, task);

		// -------- 设置部分 START-------
		JLabel controlText = new JLabel("时间及场次设置");
		controlText.setFont(new Font("Microsoft YaHei",
				Font.BOLD | Font.ITALIC, 16));
		controlText.setBounds(169, 10, 130, 27);
		frame.getContentPane().add(controlText);

		gameTime_textField = new JTextField();
		gameTime_textField.setHorizontalAlignment(SwingConstants.CENTER);
		gameTime_textField.setFont(new Font("Microsoft YaHei", Font.BOLD, 22));
		gameTime_textField.setText(Integer.toString(iGameTime));
		gameTime_textField.setBounds(36, 73, 65, 60);
		frame.getContentPane().add(gameTime_textField);
		gameTime_textField.setColumns(10);

		JButton gameTimePlus_button = new JButton("+");
		gameTimePlus_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int tmpGameTime;
				tmpGameTime = Integer.valueOf(gameTime_textField.getText());
				tmpGameTime++;
				gameTime_textField.setText(String.valueOf(tmpGameTime));

			}
		});
		gameTimePlus_button
				.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		gameTimePlus_button.setBounds(98, 73, 51, 30);
		frame.getContentPane().add(gameTimePlus_button);

		JButton gameTimeCut_button = new JButton("-");
		gameTimeCut_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tmpGameTime;
				tmpGameTime = Integer.valueOf(gameTime_textField.getText());
				tmpGameTime--;
				gameTime_textField.setText(String.valueOf(tmpGameTime));
			}
		});
		gameTimeCut_button.setFont(new Font("Microsoft YaHei UI", Font.PLAIN,
				12));
		gameTimeCut_button.setBounds(98, 102, 51, 30);
		frame.getContentPane().add(gameTimeCut_button);

		JLabel gameTimeText = new JLabel("比赛时间");
		gameTimeText.setForeground(Color.RED);
		gameTimeText.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
		gameTimeText.setBounds(62, 50, 63, 13);
		frame.getContentPane().add(gameTimeText);

		JRadioButton halfUP_radioButton = new JRadioButton("上半场");
		halfUP_radioButton.setBounds(189, 62, 98, 40);
		frame.getContentPane().add(halfUP_radioButton);

		JRadioButton halfDOWN_radioButton = new JRadioButton("下半场");
		halfDOWN_radioButton.setBounds(189, 101, 85, 40);
		frame.getContentPane().add(halfDOWN_radioButton);
		frame.setVisible(true);

		ButtonGroup halfRadioButtonGroup = new ButtonGroup();
		halfRadioButtonGroup.add(halfUP_radioButton);
		halfRadioButtonGroup.add(halfDOWN_radioButton);

		JLabel GameJie_label = new JLabel("场次");
		GameJie_label.setForeground(Color.RED);
		GameJie_label.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
		GameJie_label.setBounds(327, 50, 63, 13);
		frame.getContentPane().add(GameJie_label);

		Jie_textField = new JTextField();
		Jie_textField.setText(Integer.toString(iGameJie));
		Jie_textField.setHorizontalAlignment(SwingConstants.CENTER);
		Jie_textField.setFont(new Font("Microsoft YaHei", Font.BOLD, 22));
		Jie_textField.setColumns(10);
		Jie_textField.setBounds(288, 73, 65, 60);
		frame.getContentPane().add(Jie_textField);

		JButton jiePlus_button = new JButton("+");
		jiePlus_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tmpGameJie;
				tmpGameJie = Integer.valueOf(Jie_textField.getText());
				if (tmpGameJie <= 3) {
					tmpGameJie++;
				}
				Jie_textField.setText(String.valueOf(tmpGameJie));
				if (tmpGameJie != 0) {
					halfUP_radioButton.setEnabled(false);
					halfDOWN_radioButton.setEnabled(false);

				}

			}
		});
		jiePlus_button.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		jiePlus_button.setBounds(350, 73, 51, 30);
		frame.getContentPane().add(jiePlus_button);

		JButton jieCut_button_1 = new JButton("-");
		jieCut_button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tmpGameJie;
				tmpGameJie = Integer.valueOf(Jie_textField.getText());
				if (tmpGameJie >= 1) {
					tmpGameJie--;
				}
				Jie_textField.setText(String.valueOf(tmpGameJie));

				if (tmpGameJie == 0) {
					halfDOWN_radioButton.setEnabled(true);
					halfUP_radioButton.setEnabled(true);
				}
			}
		});
		jieCut_button_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		jieCut_button_1.setBounds(350, 102, 51, 30);
		frame.getContentPane().add(jieCut_button_1);
		// -------- 设置部分 END--------

		JButton StartButton = new JButton(START_TXT);
		StartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	//			System.out.println("标示位状态：" + iStartFlag);

				if (iStartFlag == 0) {
					// 启动显示屏
					// iStartFlag ++;
					StartButton.setText(END_TXT);
					iStartFlag = 1;
					// System.out.println("已经启动显示屏");
					// 启动屏幕
					// 初次启动屏幕参数设置
			//		System.out.println("parm Start");
					try {
						String strTmp;
						strTmp = gameTime_textField.getText();
				//		System.out.println(strTmp);
						PINGMU.setStrGameTime(strTmp);
						// P.setStrGameTime(gameTime_textField.getText());
						// System.out.println(HostTeamPoint_textField.getText());
						PINGMU.setStrHostPoint(HostTeamPoint_textField
								.getText());
						PINGMU.setStrHostFoul(HostTeamFoul_textField.getText());
						PINGMU.setStrHostPasue(HostTeamPause_textField
								.getText());
						PINGMU.setStrGuestPoint(GuestTeamPoint_textField
								.getText());
						PINGMU.setStrGuestFoul(GuestTeamFoul_textField
								.getText());
						PINGMU.setStrGuestPasue(GuestTeamPause_textField
								.getText());
				//		System.out.println("parm OK");

						SP.getPingMu(PINGMU);
						SP.run();
						// 场次设定
						int iTmp = Integer.valueOf(Jie_textField.getText());
						if (iTmp == 0) {
							if (halfUP_radioButton.isSelected()) {
								iTmp = 1;
							} else if (halfDOWN_radioButton.isSelected()) {
								iTmp = 2;
							}
						} else {
							iTmp = iTmp + 2;
						}
						SP.setChang(iTmp);

						// 计时器时钟初始化
						ClockInit();

					} catch (Exception e2) {
						// TODO: handle exception
						System.out.println(e2);
					}

				} else if (iStartFlag == 1) {
					// 关闭显示屏
					iStartFlag = 0;
					StartButton.setText(START_TXT);
					// System.out.println("已经关闭显示屏");
					// SP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					SP.dispose();

				}

			}
		});

		StartButton.setFont(new Font("Microsoft YaHei", Font.BOLD, 17));
		StartButton.setBounds(124, 151, 198, 40);
		frame.getContentPane().add(StartButton);

		// -------- 计时部分 START--------

		JLabel label = new JLabel("计时，比分控制");
		label.setFont(new Font("Microsoft YaHei", Font.BOLD | Font.ITALIC, 16));
		label.setBounds(169, 208, 130, 27);
		frame.getContentPane().add(label);

		JLabel label_1 = new JLabel("主队比分");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
		label_1.setBounds(62, 245, 63, 13);
		frame.getContentPane().add(label_1);

		// 主队比分栏
		HostTeamPoint_textField = new JTextField();
		HostTeamPoint_textField.setText("0");
		HostTeamPoint_textField.setHorizontalAlignment(SwingConstants.CENTER);
		HostTeamPoint_textField.setFont(new Font("Microsoft YaHei", Font.BOLD,
				22));
		HostTeamPoint_textField.setColumns(10);
		HostTeamPoint_textField.setBounds(36, 268, 65, 60);
		frame.getContentPane().add(HostTeamPoint_textField);

		JButton HostTeamPointPlus_button = new JButton("+");
		// 主队加分监听器
		HostTeamPointPlus_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int iTmp;
				iTmp = Integer.valueOf(HostTeamPoint_textField.getText());
				iTmp++;
				HostTeamPoint_textField.setText(String.valueOf(iTmp));
				if (iStartFlag == 1) {
					SP.setHostPoint(HostTeamPoint_textField.getText());
				}
			}

		});
		HostTeamPointPlus_button.setFont(new Font("Microsoft YaHei",
				Font.PLAIN, 12));
		HostTeamPointPlus_button.setBounds(98, 268, 51, 30);
		frame.getContentPane().add(HostTeamPointPlus_button);

		JButton HostTeamPoint_button = new JButton("-");
		// 主队减分监听器
		HostTeamPoint_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int iTmp;
				iTmp = Integer.valueOf(HostTeamPoint_textField.getText());
				iTmp--;
				HostTeamPoint_textField.setText(String.valueOf(iTmp));
				if (iStartFlag == 1) {
					SP.setHostPoint(HostTeamPoint_textField.getText());
				}
			}
		});
		HostTeamPoint_button.setFont(new Font("Microsoft YaHei UI", Font.PLAIN,
				12));
		HostTeamPoint_button.setBounds(98, 297, 51, 30);
		frame.getContentPane().add(HostTeamPoint_button);

		JLabel label_2 = new JLabel("客队比分");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
		label_2.setBounds(62, 346, 63, 13);
		frame.getContentPane().add(label_2);

		GuestTeamPoint_textField = new JTextField();
		GuestTeamPoint_textField.setText("0");
		GuestTeamPoint_textField.setHorizontalAlignment(SwingConstants.CENTER);
		GuestTeamPoint_textField.setFont(new Font("Microsoft YaHei", Font.BOLD,
				22));
		GuestTeamPoint_textField.setColumns(10);
		GuestTeamPoint_textField.setBounds(36, 369, 65, 60);
		frame.getContentPane().add(GuestTeamPoint_textField);

		JButton GuestTeamPointPlus_button = new JButton("+");
		// 客队加分监听器
		GuestTeamPointPlus_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int iTmp;
				iTmp = Integer.valueOf(GuestTeamPoint_textField.getText());
				iTmp++;
				GuestTeamPoint_textField.setText(String.valueOf(iTmp));
				if (iStartFlag == 1) {
					SP.setGuestPoint(GuestTeamPoint_textField.getText());
				}
			}
		});
		GuestTeamPointPlus_button.setFont(new Font("Microsoft YaHei",
				Font.PLAIN, 12));
		GuestTeamPointPlus_button.setBounds(98, 369, 51, 30);
		frame.getContentPane().add(GuestTeamPointPlus_button);

		JButton GuestTeamPointCut_button = new JButton("-");
		// 客队减分监听器
		GuestTeamPointCut_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int iTmp;
				iTmp = Integer.valueOf(GuestTeamPoint_textField.getText());
				iTmp--;
				GuestTeamPoint_textField.setText(String.valueOf(iTmp));
				if (iStartFlag == 1) {
					SP.setGuestPoint(GuestTeamPoint_textField.getText());
				}
			}
		});
		GuestTeamPointCut_button.setFont(new Font("Microsoft YaHei UI",
				Font.PLAIN, 12));
		GuestTeamPointCut_button.setBounds(98, 398, 51, 30);
		frame.getContentPane().add(GuestTeamPointCut_button);

		JLabel label_3 = new JLabel("主队犯规");
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
		label_3.setBounds(195, 245, 63, 13);
		frame.getContentPane().add(label_3);

		HostTeamFoul_textField = new JTextField();
		HostTeamFoul_textField.setText("0");
		HostTeamFoul_textField.setHorizontalAlignment(SwingConstants.CENTER);
		HostTeamFoul_textField.setFont(new Font("Microsoft YaHei", Font.BOLD,
				22));
		HostTeamFoul_textField.setColumns(10);
		HostTeamFoul_textField.setBounds(169, 268, 65, 60);
		frame.getContentPane().add(HostTeamFoul_textField);

		JButton HostTeamFoulPlus_button = new JButton("+");
		// 主队犯规加 监听器
		HostTeamFoulPlus_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int iTmp;
				iTmp = Integer.valueOf(HostTeamFoul_textField.getText());
				iTmp++;
				HostTeamFoul_textField.setText(String.valueOf(iTmp));
				if (iStartFlag == 1) {
					SP.setHostFoul(HostTeamFoul_textField.getText());
				}
			}
		});
		HostTeamFoulPlus_button.setFont(new Font("Microsoft YaHei", Font.PLAIN,
				12));
		HostTeamFoulPlus_button.setBounds(231, 268, 51, 30);
		frame.getContentPane().add(HostTeamFoulPlus_button);

		JButton HostTeamFoulCut_button = new JButton("-");
		// 主队犯规减 监听器
		HostTeamFoulCut_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int iTmp;
				iTmp = Integer.valueOf(HostTeamFoul_textField.getText());
				iTmp--;
				HostTeamFoul_textField.setText(String.valueOf(iTmp));
				if (iStartFlag == 1) {
					SP.setHostFoul(HostTeamFoul_textField.getText());
				}
			}
		});
		HostTeamFoulCut_button.setFont(new Font("Microsoft YaHei UI",
				Font.PLAIN, 12));
		HostTeamFoulCut_button.setBounds(231, 297, 51, 30);
		frame.getContentPane().add(HostTeamFoulCut_button);

		JLabel label_4 = new JLabel("客队犯规");
		label_4.setForeground(Color.RED);
		label_4.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
		label_4.setBounds(195, 346, 63, 13);
		frame.getContentPane().add(label_4);

		GuestTeamFoul_textField = new JTextField();
		GuestTeamFoul_textField.setText("0");
		GuestTeamFoul_textField.setHorizontalAlignment(SwingConstants.CENTER);
		GuestTeamFoul_textField.setFont(new Font("Microsoft YaHei", Font.BOLD,
				22));
		GuestTeamFoul_textField.setColumns(10);
		GuestTeamFoul_textField.setBounds(169, 369, 65, 60);
		frame.getContentPane().add(GuestTeamFoul_textField);

		JButton GuestTeamFoulPlus_button = new JButton("+");
		// 客队犯规加 监听器
		GuestTeamFoulPlus_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int iTmp;
				iTmp = Integer.valueOf(GuestTeamFoul_textField.getText());
				iTmp++;
				GuestTeamFoul_textField.setText(String.valueOf(iTmp));
				if (iStartFlag == 1) {
					SP.setGuestFoul(GuestTeamFoul_textField.getText());
				}
			}
		});
		GuestTeamFoulPlus_button.setFont(new Font("Microsoft YaHei",
				Font.PLAIN, 12));
		GuestTeamFoulPlus_button.setBounds(231, 369, 51, 30);
		frame.getContentPane().add(GuestTeamFoulPlus_button);

		JButton GuestTeamFoulCut_button = new JButton("-");
		// 客队犯规减 监听器
		GuestTeamFoulCut_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int iTmp;
				iTmp = Integer.valueOf(GuestTeamFoul_textField.getText());
				iTmp--;
				GuestTeamFoul_textField.setText(String.valueOf(iTmp));
				if (iStartFlag == 1) {
					SP.setGuestFoul(GuestTeamFoul_textField.getText());
				}
			}
		});
		GuestTeamFoulCut_button.setFont(new Font("Microsoft YaHei UI",
				Font.PLAIN, 12));
		GuestTeamFoulCut_button.setBounds(231, 398, 51, 30);
		frame.getContentPane().add(GuestTeamFoulCut_button);

		JLabel label_5 = new JLabel("主队暂停");
		label_5.setForeground(Color.RED);
		label_5.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
		label_5.setBounds(332, 245, 63, 13);
		frame.getContentPane().add(label_5);

		HostTeamPause_textField = new JTextField();
		HostTeamPause_textField.setText("0");
		HostTeamPause_textField.setHorizontalAlignment(SwingConstants.CENTER);
		HostTeamPause_textField.setFont(new Font("Microsoft YaHei", Font.BOLD,
				22));
		HostTeamPause_textField.setColumns(10);
		HostTeamPause_textField.setBounds(306, 268, 65, 60);
		frame.getContentPane().add(HostTeamPause_textField);

		JButton HostTeamPausePlus_button = new JButton("+");
		// 主队暂停加 监听器
		HostTeamPausePlus_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int iTmp;
				iTmp = Integer.valueOf(HostTeamPause_textField.getText());
				iTmp++;
				HostTeamPause_textField.setText(String.valueOf(iTmp));
				if (iStartFlag == 1) {
					SP.setHostPasue(HostTeamPause_textField.getText());
				}
			}
		});
		HostTeamPausePlus_button.setFont(new Font("Microsoft YaHei",
				Font.PLAIN, 12));
		HostTeamPausePlus_button.setBounds(368, 268, 51, 30);
		frame.getContentPane().add(HostTeamPausePlus_button);

		JButton HostTeamPauseCut_button = new JButton("-");
		// 主队暂停减 监听器
		HostTeamPauseCut_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int iTmp;
				iTmp = Integer.valueOf(HostTeamPause_textField.getText());
				iTmp--;
				HostTeamPause_textField.setText(String.valueOf(iTmp));
				if (iStartFlag == 1) {
					SP.setHostPasue(HostTeamPause_textField.getText());
				}
			}
		});
		HostTeamPauseCut_button.setFont(new Font("Microsoft YaHei UI",
				Font.PLAIN, 12));
		HostTeamPauseCut_button.setBounds(368, 297, 51, 30);
		frame.getContentPane().add(HostTeamPauseCut_button);

		JLabel label_6 = new JLabel("客队暂停");
		label_6.setForeground(Color.RED);
		label_6.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
		label_6.setBounds(332, 346, 63, 13);
		frame.getContentPane().add(label_6);

		GuestTeamPause_textField = new JTextField();
		GuestTeamPause_textField.setText("0");
		GuestTeamPause_textField.setHorizontalAlignment(SwingConstants.CENTER);
		GuestTeamPause_textField.setFont(new Font("Microsoft YaHei", Font.BOLD,
				22));
		GuestTeamPause_textField.setColumns(10);
		GuestTeamPause_textField.setBounds(306, 369, 65, 60);
		frame.getContentPane().add(GuestTeamPause_textField);

		JButton GuestTeamPausePlus_button = new JButton("+");
		// 客队暂停加 监听器
		GuestTeamPausePlus_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int iTmp;
				iTmp = Integer.valueOf(GuestTeamPause_textField.getText());
				iTmp++;
				GuestTeamPause_textField.setText(String.valueOf(iTmp));
				if (iStartFlag == 1) {
					SP.setGuestPasue(GuestTeamPause_textField.getText());
				}
			}
		});
		GuestTeamPausePlus_button.setFont(new Font("Microsoft YaHei",
				Font.PLAIN, 12));
		GuestTeamPausePlus_button.setBounds(368, 369, 51, 30);
		frame.getContentPane().add(GuestTeamPausePlus_button);

		JButton GuestTeamPauseCut_button = new JButton("-");
		// 客队暂停减 监听器
		GuestTeamPauseCut_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int iTmp;
				iTmp = Integer.valueOf(GuestTeamPause_textField.getText());
				iTmp--;
				GuestTeamPause_textField.setText(String.valueOf(iTmp));
				if (iStartFlag == 1) {
					SP.setGuestPasue(GuestTeamPause_textField.getText());
				}
			}
		});
		GuestTeamPauseCut_button.setFont(new Font("Microsoft YaHei UI",
				Font.PLAIN, 12));
		GuestTeamPauseCut_button.setBounds(368, 398, 51, 30);
		frame.getContentPane().add(GuestTeamPauseCut_button);

		// 倒计时 时钟暂停按钮
		JButton ClockStopButton = new JButton(CLOCK_STOP_TXT);
		ClockStopButton.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
		// 倒计时时钟暂定按钮监听器
		ClockStopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.stop();
			}
		});
		ClockStopButton.setBounds(240, 478, 71, 40);
		frame.getContentPane().add(ClockStopButton);

		// 计时器显示标签

		ClcokLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 25));
		ClcokLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ClcokLabel.setBounds(50, 464, 96, 60);
		frame.getContentPane().add(ClcokLabel);
		
		JButton ClockStartButton = new JButton("开始");
		ClockStartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				run();
			}
		});
		ClockStartButton.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
		ClockStartButton.setBounds(169, 478, 71, 40);
		frame.getContentPane().add(ClockStartButton);
		
		JButton ClockResetButton = new JButton("复位");
		ClockResetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClockInit();
			}
		});
		ClockResetButton.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
		ClockResetButton.setBounds(311, 478, 71, 40);
		frame.getContentPane().add(ClockResetButton);
		
		JLabel lblNewLabel = new JLabel("版权所有 抚顺通力电子");
		lblNewLabel.setBounds(306, 528, 200, 50);
		frame.getContentPane().add(lblNewLabel);
	}
}
