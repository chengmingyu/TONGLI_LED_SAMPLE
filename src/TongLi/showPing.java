package TongLi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import TongLi.pingMu;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;

import org.omg.CORBA.PUBLIC_MEMBER;

public class showPing extends JFrame {
	public showPing() {
	}

	pingMu P = new pingMu();
	private JPanel contentPane;
	public JLabel HostPointShow = new JLabel("0");
	public JLabel GuestPointShow = new JLabel("0");
	public JLabel HostFoulShow = new JLabel("0");
	public JLabel HostPasueShow = new JLabel("0");
	public JLabel GuestFoulShow = new JLabel("0");
	public JLabel GuestPasueShow = new JLabel("0");
	JLabel showHalf_label = new JLabel("第一节");
	JLabel gameTimeShowLable = new JLabel("12:00");
	private final String HALF1 = "上";
	private final String HALF2 = "下";
	private final String CHANG1 = "第一节";
	private final String CHANG2 = "第二节";
	private final String CHANG3 = "第三节";
	private final String CHANG4 = "第四节";

	public int xiaWordsize = 15;
	public int iTmp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					showPing frame = new showPing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void getPingMu(pingMu pp) {
		P.setStrGameHalf(pp.getStrGameHalf());
		P.setStrGameJie(pp.getStrGameJie());
		P.setStrGameTime(pp.getStrGameTime());
		P.setStrGuestFoul(pp.getStrGuestFoul());
		P.setStrGuestPasue(pp.getStrGuestPasue());
		P.setStrGuestPoint(pp.getStrGuestPoint());
		P.setStrHostFoul(pp.getStrHostFoul());
		P.setStrHostPasue(pp.getStrHostPasue());
		P.setStrHostPoint(pp.getStrHostPoint());
	}

	// 主队分数设置
	public void setHostPoint(String value) {
		HostPointShow.setText(value);
		iTmp = Integer.valueOf(value);
		if (iTmp > 9) {
			HostPointShow.setBounds(55, -12, 98, 79);
		}else {
			HostPointShow.setBounds(65, -12, 98, 79);
		}
	}

	// 客队加分设置
	public void setGuestPoint(String value) {
		GuestPointShow.setText(value);
		iTmp = Integer.valueOf(value);
		if (iTmp > 9) {
			GuestPointShow.setBounds(225, -12, 74, 79);
		}else{
			GuestPointShow.setBounds(235, -12, 74, 79);
		}
	}

	// 主队犯规设置
	public void setHostFoul(String value) {
		HostFoulShow.setText(value);
	}

	// 主队暂停设置
	public void setHostPasue(String value) {
		HostPasueShow.setText(value);

	}

	// 客队犯规设置
	public void setGuestFoul(String value) {
		GuestFoulShow.setText(value);
	}

	// 客队暂停设置
	public void setGuestPasue(String value) {
		GuestPasueShow.setText(value);
	}

	// 场次设置
	public void setChang(int value) {
		String strTmp = HALF1;
		;
		switch (value) {
		case 1:
			strTmp = HALF1;
			break;
		case 2:
			strTmp = HALF2;
			break;
		case 3:
			strTmp = CHANG1;
			break;
		case 4:
			strTmp = CHANG2;
			break;
		case 5:
			strTmp = CHANG3;
			break;
		case 6:
			strTmp = CHANG4;
			break;

		default:
			strTmp = HALF1;
		}
		showHalf_label.setText(strTmp);
	}

	public void Close() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void setClock(String value) {
		gameTimeShowLable.setText(value);
	}

	/**
	 * Create the frame.
	 */
	public void run() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 72, 330, 120);
		setAlwaysOnTop(true);
		setUndecorated(true);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		// contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		// contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 主队比分显示标签
		HostPointShow.setText(P.getStrHostPoint());
		HostPointShow.setFont(new Font("Microsoft YaHei", Font.BOLD, 50));
		HostPointShow.setForeground(Color.WHITE);
		HostPointShow.setBounds(65, -12, 98, 79);
		contentPane.add(HostPointShow);

		// // 中间冒号显示标签
		// JLabel lblNewLabel = new JLabel(":");
		// lblNewLabel.setForeground(Color.WHITE);
		// lblNewLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 30));
		// lblNewLabel.setBounds(165, 10, 26, 24);
		// contentPane.add(lblNewLabel);

		// 客队比分显示标签
		GuestPointShow.setText(P.getStrGuestPoint());
		GuestPointShow.setForeground(Color.WHITE);
		GuestPointShow.setFont(new Font("Microsoft YaHei", Font.BOLD, 50));
		GuestPointShow.setBounds(235, -12, 74, 79);
		contentPane.add(GuestPointShow);

		JLabel lblNewLabel_1 = new JLabel("犯规:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Microsoft YaHei", Font.PLAIN,
				xiaWordsize));
		lblNewLabel_1.setBounds(26, 97, 38, 13);
		contentPane.add(lblNewLabel_1);

		JLabel label = new JLabel("暂停:");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Microsoft YaHei", Font.PLAIN, xiaWordsize));
		label.setBounds(104, 97, 38, 13);
		contentPane.add(label);

		JLabel label_1 = new JLabel("犯规:");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Microsoft YaHei", Font.PLAIN, xiaWordsize));
		label_1.setBounds(195, 97, 38, 13);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("暂停:");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Microsoft YaHei", Font.PLAIN, xiaWordsize));
		label_2.setBounds(266, 97, 38, 13);
		contentPane.add(label_2);

		// 场次显示标签
		showHalf_label.setHorizontalAlignment(SwingConstants.CENTER);
		showHalf_label.setFont(new Font("Microsoft YaHei", Font.PLAIN, 17));
		showHalf_label.setForeground(Color.WHITE);
		showHalf_label.setBounds(132, 16, 74, 24);
		contentPane.add(showHalf_label);

		// 倒计时 时钟显示标签
		gameTimeShowLable.setForeground(Color.WHITE);
		gameTimeShowLable.setFont(new Font("Microsoft YaHei", Font.BOLD, 25));
		gameTimeShowLable.setBounds(138, 52, 74, 24);
		contentPane.add(gameTimeShowLable);

		// 主队犯规数设置
		HostFoulShow.setText(P.getStrHostFoul());
		HostFoulShow.setHorizontalAlignment(SwingConstants.CENTER);
		HostFoulShow.setForeground(Color.WHITE);
		HostFoulShow.setFont(new Font("Microsoft YaHei", Font.PLAIN,
				xiaWordsize));
		HostFoulShow.setBounds(55, 96, 38, 13);
		contentPane.add(HostFoulShow);

		// 主队暂停数设置
		HostPasueShow.setText(P.getStrHostPasue());
		HostPasueShow.setHorizontalAlignment(SwingConstants.CENTER);
		HostPasueShow.setForeground(Color.WHITE);
		HostPasueShow.setFont(new Font("Microsoft YaHei", Font.PLAIN,
				xiaWordsize));
		HostPasueShow.setBounds(132, 97, 38, 13);
		contentPane.add(HostPasueShow);

		// 客队犯规数设置
		GuestFoulShow.setText(P.getStrGuestFoul());
		GuestFoulShow.setHorizontalAlignment(SwingConstants.CENTER);
		GuestFoulShow.setForeground(Color.WHITE);
		GuestFoulShow.setFont(new Font("Microsoft YaHei", Font.PLAIN,
				xiaWordsize));
		GuestFoulShow.setBounds(226, 96, 38, 13);
		contentPane.add(GuestFoulShow);

		// 客队暂停数设置
		GuestPasueShow.setText(P.getStrGuestPasue());
		GuestPasueShow.setHorizontalAlignment(SwingConstants.CENTER);
		GuestPasueShow.setForeground(Color.WHITE);
		GuestPasueShow.setFont(new Font("Microsoft YaHei", Font.PLAIN,
				xiaWordsize));
		GuestPasueShow.setBounds(292, 97, 38, 13);
		contentPane.add(GuestPasueShow);
	}
}
