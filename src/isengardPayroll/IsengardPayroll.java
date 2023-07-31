package isengardPayroll;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

//import com.formdev.flatlaf.FlatDarkLaf;
//import com.formdev.flatlaf.intellijthemes.FlatCobalt2IJTheme;
//import com.formdev.flatlaf.themes.FlatMacDarkLaf;
//import com.formdev.flatlaf.themes.FlatMacLightLaf;

import net.proteanit.sql.DbUtils;
import javax.swing.border.TitledBorder;
import java.awt.Color;

public class IsengardPayroll {
	JFrame frame;
	JFrame frame_1;
	private JTextField textSIL;
	private JTextField textBasic;
	private JTextField textOverTime;
	private JTextField textGrossPay;
	private JTextField textMonthPay;
	private JTextField textNightDifferential;
	private JTextField textPayDate;
	private JTextField textSSS;
	private JTextField textPhilHealth;
	private JTextField textPagIbig;
	private JTextField textTotalDeduction;
	private JTextField textCashBan;
	private JTextField textOthers;
	private JTextField textGrossSalary;
	private JTextField textTotalDeductions;
	private JTextField textNetSalary;
	//-----------------------------------------------------------------------------------------
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	DefaultTableModel model = new DefaultTableModel();
	//-----------------------------------------------------------------------------------------
	double Basic;
	double SIL;
	double OverTime;
	double NightDifferential;
	double MonthPay;
	double Gross;
	
	double TotalBasic;
	double TotalSIL;
	double TotalOverTime;
	double TotalNightDifferential;
	double TotalMonthPay;
	//-----------------------------------------------------------------------------------------
	double SSS;
	double PhilHealth;
	double PagIbig;
	double CashBan;
	double Others;
	double Deduction;
	//-----------------------------------------------------------------------------------------
	double GrossSalary;
	double TotalDeductions;
	double NetSalary;
	//-----------------------------------------------------------------------------------------
	double BasicRate;
	double SILRate;
	double OvertimeRate;
	double NightDiffRate;
	double MonthRate;
	
	private JTextField textBasicRate;
	private JTextField textSILRate;
	private JTextField textOvertimeRate;
	private JTextField textNightDiffRate;
	private JTextField textMonthRate;
	//-----------------------------------------------------------------------------------------

	//-----------------------------------------------------------------------------------------
// 	Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IsengardPayroll window = new IsengardPayroll();
					window.frame_1.setVisible(true);
					window.frame_1.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	
// 	Create the application.
	private JTable table;
	private JTextField textId;
	private JTextField textFirstname2;
	private JTextField textMiddlename2;
	private JTextField textLastname2;
	private JTextField textID2;
	private JTextField textPagIbigNo;
	private JTextField textPhilHealthNo;
	private JTextField textSSSNo;
	private JTextField textContactNo;
	private JTextField textBirthdate;
	private JTextField textAddress;
	private JTextField textAge;
	private JTextField textGender_1;
	private JTextField textLastname;
	private JTextField textMiddlename;
	private JTextField textFirstname;
	private JTextField textEmpRefNo;
	
	public IsengardPayroll() {
		initialize();
		con = ConnectDB();
	}
	public static Connection ConnectDB() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\admin\\eclipse-workspace\\IsengardPayrollManagementSystem\\EmployeeInformation.db");
			return con;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Connection error");
			return null;
		}
	}
//	Initialize the contents of the frame.
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLocation(-617, -197);
		frame_1 = new JFrame("Employee and Payroll Management System");
		frame_1.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				Calendar timer =  Calendar.getInstance();
				timer.getTime();
				SimpleDateFormat tTime = new SimpleDateFormat("HH-MM-SS");
				tTime.format(timer.getTime());
				SimpleDateFormat tDate = new SimpleDateFormat("MMM-d-y");
				tDate.format(timer.getTime());
				textPayDate.setText(tDate.format(timer.getTime()));
			}
		});
		frame_1.setBounds(0, 0, 1366, 768);
		frame_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame_1.getContentPane().setLayout(null);
		//-----------------------------------------------------------------------------------------
		JTabbedPane tabbedPane =  new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		tabbedPane.setBounds(10, 11, 1330, 689);
		frame_1.getContentPane().add(tabbedPane);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Employee Management", null, panel_1, null);
		panel_1.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Personal Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(10, 11, 338, 635);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1_1_1_2_8_10 = new JLabel("Pag-Ibig No.");
		lblNewLabel_1_1_1_2_8_10.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_2_8_10.setBounds(10, 490, 113, 25);
		panel_3.add(lblNewLabel_1_1_1_2_8_10);
		
		textPagIbigNo = new JTextField();
		textPagIbigNo.setFont(new Font("Tahoma", Font.BOLD, 14));
		textPagIbigNo.setColumns(10);
		textPagIbigNo.setBounds(152, 490, 176, 25);
		panel_3.add(textPagIbigNo);
		
		JLabel lblNewLabel_1_1_1_2_8_9 = new JLabel("PhilHealth No.");
		lblNewLabel_1_1_1_2_8_9.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_2_8_9.setBounds(10, 451, 113, 25);
		panel_3.add(lblNewLabel_1_1_1_2_8_9);
		
		textSSSNo = new JTextField();
		textSSSNo.setFont(new Font("Tahoma", Font.BOLD, 14));
		textSSSNo.setColumns(10);
		textSSSNo.setBounds(152, 412, 176, 25);
		panel_3.add(textSSSNo);
		
		JLabel lblNewLabel_1_1_1_2_8_8 = new JLabel("SSS No.");
		lblNewLabel_1_1_1_2_8_8.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_2_8_8.setBounds(10, 412, 93, 25);
		panel_3.add(lblNewLabel_1_1_1_2_8_8);
		
		textContactNo = new JTextField();
		textContactNo.setFont(new Font("Tahoma", Font.BOLD, 14));
		textContactNo.setColumns(10);
		textContactNo.setBounds(152, 376, 176, 25);
		panel_3.add(textContactNo);
		
		JLabel lblNewLabel_1_1_1_2_8_7 = new JLabel("Contact Number");
		lblNewLabel_1_1_1_2_8_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_2_8_7.setBounds(10, 376, 132, 25);
		panel_3.add(lblNewLabel_1_1_1_2_8_7);
		
		textBirthdate = new JTextField();
		textBirthdate.setFont(new Font("Tahoma", Font.BOLD, 14));
		textBirthdate.setColumns(10);
		textBirthdate.setBounds(152, 337, 176, 25);
		panel_3.add(textBirthdate);
		
		JLabel lblNewLabel_1_1_1_2_8_2 = new JLabel("Birthdate");
		lblNewLabel_1_1_1_2_8_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_2_8_2.setBounds(10, 337, 93, 25);
		panel_3.add(lblNewLabel_1_1_1_2_8_2);
		
		textAddress = new JTextField();
		textAddress.setFont(new Font("Tahoma", Font.BOLD, 14));
		textAddress.setColumns(10);
		textAddress.setBounds(152, 298, 176, 25);
		panel_3.add(textAddress);
		
		JLabel lblNewLabel_1_1_1_2_8_1 = new JLabel("Address");
		lblNewLabel_1_1_1_2_8_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_2_8_1.setBounds(10, 298, 93, 25);
		panel_3.add(lblNewLabel_1_1_1_2_8_1);
		
		textAge = new JTextField();
		textAge.setFont(new Font("Tahoma", Font.BOLD, 14));
		textAge.setColumns(10);
		textAge.setBounds(152, 259, 176, 25);
		panel_3.add(textAge);
		
		JLabel lblNewLabel_1_1_1_2_8 = new JLabel("Age");
		lblNewLabel_1_1_1_2_8.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_2_8.setBounds(10, 259, 93, 25);
		panel_3.add(lblNewLabel_1_1_1_2_8);
		
		JLabel lblNewLabel_1_1_1_2_7 = new JLabel("Gender");
		lblNewLabel_1_1_1_2_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_2_7.setBounds(10, 220, 93, 25);
		panel_3.add(lblNewLabel_1_1_1_2_7);
		
		textGender_1 = new JTextField();
		textGender_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		textGender_1.setColumns(10);
		textGender_1.setBounds(152, 220, 176, 25);
		panel_3.add(textGender_1);
		
		JLabel lblNewLabel_1_1_1_2_6 = new JLabel("Lastname");
		lblNewLabel_1_1_1_2_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_2_6.setBounds(10, 181, 93, 25);
		panel_3.add(lblNewLabel_1_1_1_2_6);
		
		textLastname = new JTextField();
		textLastname.setFont(new Font("Tahoma", Font.BOLD, 14));
		textLastname.setColumns(10);
		textLastname.setBounds(152, 181, 176, 25);
		panel_3.add(textLastname);
		
		textMiddlename = new JTextField();
		textMiddlename.setFont(new Font("Tahoma", Font.BOLD, 14));
		textMiddlename.setColumns(10);
		textMiddlename.setBounds(152, 142, 176, 25);
		panel_3.add(textMiddlename);
		
		JLabel lblNewLabel_1_1_1_2_5 = new JLabel("Middlename");
		lblNewLabel_1_1_1_2_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_2_5.setBounds(10, 142, 93, 25);
		panel_3.add(lblNewLabel_1_1_1_2_5);
		
		JLabel lblNewLabel_1_1_1_2_4 = new JLabel("Firstname");
		lblNewLabel_1_1_1_2_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_2_4.setBounds(10, 103, 93, 25);
		panel_3.add(lblNewLabel_1_1_1_2_4);
		
		textFirstname = new JTextField();
		textFirstname.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFirstname.setColumns(10);
		textFirstname.setBounds(152, 103, 176, 25);
		panel_3.add(textFirstname);
		
		textEmpRefNo = new JTextField();
		textEmpRefNo.setFont(new Font("Tahoma", Font.BOLD, 14));
		textEmpRefNo.setColumns(10);
		textEmpRefNo.setBounds(152, 64, 176, 25);
		panel_3.add(textEmpRefNo);
		
		JLabel lblNewLabel_1_1_1_2_3 = new JLabel("Emp.Ref.No.");
		lblNewLabel_1_1_1_2_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_2_3.setBounds(10, 64, 93, 25);
		panel_3.add(lblNewLabel_1_1_1_2_3);
		
		JButton btnCreate = new JButton("Save");
		btnCreate.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCreate.setBounds(10, 544, 158, 35);
		panel_3.add(btnCreate);
		
		JButton btnReset_1 = new JButton("Reset");
		btnReset_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnReset_1.setBounds(178, 544, 150, 35);
		panel_3.add(btnReset_1);
		
		textPhilHealthNo = new JTextField();
		textPhilHealthNo.setBounds(152, 450, 176, 25);
		panel_3.add(textPhilHealthNo);
		textPhilHealthNo.setFont(new Font("Tahoma", Font.BOLD, 14));
		textPhilHealthNo.setColumns(10);
		
		JLabel lblNewLabel_1_1_1_2_3_1 = new JLabel("Search ERN");
		lblNewLabel_1_1_1_2_3_1.setBounds(10, 28, 93, 25);
		panel_3.add(lblNewLabel_1_1_1_2_3_1);
		lblNewLabel_1_1_1_2_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		textId = new JTextField();
		textId.setBounds(152, 28, 91, 25);
		panel_3.add(textId);
		textId.setFont(new Font("Tahoma", Font.BOLD, 14));
		textId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {		
				try {
					String query = "SELECT * FROM EmployeeInformation WHERE EmpRefNo=?";
					pst = con.prepareStatement(query);
					pst.setString(1, textId.getText());
					rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
				}catch (Exception el) {
					el.printStackTrace();
				}
			}
		});
		textId.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("Search");
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
			try {
				pst = con.prepareStatement("SELECT EmpRefNo, Firstname, Middlename, Lastname, Gender, Age, Address, Birthdate, ContactNo, SSSNo, PhilHealthNo, PagIbigNo FROM EmployeeInformation WHERE EmpRefNo = ?");
				int id = Integer.parseInt(textId.getText());
				pst.setInt(1, id);
				rs = pst.executeQuery();
				if (rs.next() == false) {
					JOptionPane.showMessageDialog(null, "Record not found");
					textId.setText("");
					textEmpRefNo.setText("");
					textFirstname.setText("");
					textMiddlename.setText("");
					textLastname.setText("");
					textGender_1.setText("");
					textAge.setText("");
					textAddress.setText("");
					textBirthdate.setText("");
					textContactNo.setText("");
					textSSSNo.setText("");
					textPhilHealthNo.setText("");
					textPagIbigNo.setText("");
				} else {
					textEmpRefNo.setText(rs.getString("EmpRefNo"));
					textFirstname.setText(rs.getString("Firstname"));
					textMiddlename.setText(rs.getString("Middlename"));
					textLastname.setText(rs.getString("Lastname"));
					textGender_1.setText(rs.getString("Gender"));
					textAge.setText(rs.getString("Age"));
					textAddress.setText(rs.getString("Address"));
					textBirthdate.setText(rs.getString("Birthdate"));
					textContactNo.setText(rs.getString("ContactNo"));
					textSSSNo.setText(rs.getString("SSSNo"));
					textPhilHealthNo.setText(rs.getString("PhilHealthNo"));
					textPagIbigNo.setText(rs.getString("PagIbigNo"));
				}				
			} catch (Exception el) {
				el.printStackTrace();
			}
			}
		});
		btnNewButton_4.setBounds(253, 28, 75, 24);
		panel_3.add(btnNewButton_4);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnUpdate.setBounds(10, 590, 158, 34);
		panel_3.add(btnUpdate);
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(178, 590, 150, 34);
		panel_3.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textPassField = textId.getText();
				try {
					if(textPassField.equals("")) {
						JOptionPane.showMessageDialog(null, "Please search the Employee Reference Number to delete data.");
					} else {
						String query = "DELETE FROM EmployeeInformation WHERE EmpRefNo = '"+textId.getText()+"' ";
						pst = con.prepareStatement(query);
						pst.execute();
						JOptionPane.showMessageDialog(null, "Data Deleted");
						pst.close();
					}
				} catch (Exception ex){
					ex.printStackTrace();
				}
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				try {
					String query = "UPDATE EmployeeInformation SET Firstname = '"+textFirstname.getText()+"', Middlename = '"+textMiddlename.getText()+"', Lastname = '"+textLastname.getText()+"', Gender = '"+textGender_1.getText()+"', Age = '"+textAge.getText()+"', Address = '"+textAddress.getText()+"', Birthdate = '"+textBirthdate.getText()+"', ContactNo = '"+textContactNo.getText()+"', SSSNo = '"+textSSSNo.getText()+"', PhilHealthNo = '"+textPhilHealthNo.getText()+"', PagIbigNo = '"+textPagIbigNo.getText()+"' WHERE EmpRefNo = '"+textId.getText()+"' ";
					pst = con.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null, "Data Updated");
					pst.close();					
				} catch(Exception el) {
					el.printStackTrace();
				}
			}
		});
		btnReset_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textId.setText(null);
				textEmpRefNo.setText(null);
				textFirstname.setText(null);
				textMiddlename.setText(null);
				textLastname.setText(null);
				textGender_1.setText(null);
				textAge.setText(null);
				textAddress.setText(null);
				textBirthdate.setText(null);
				textContactNo.setText(null);
				textSSSNo.setText(null);
				textPhilHealthNo.setText(null);
				textPagIbigNo.setText(null);
			}
		});
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				try {
					String query = "INSERT INTO EmployeeInformation(EmpRefNo, Firstname, Middlename, Lastname, Gender, Age, Address, Birthdate, ContactNo, SSSNo, PhilHealthNo, PagIbigNo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
					pst = con.prepareStatement(query);
					pst.setString(1, textEmpRefNo.getText());
					pst.setString(2, textFirstname.getText());
					pst.setString(3, textMiddlename.getText());	
					pst.setString(4, textLastname.getText());
					pst.setString(5, textGender_1.getText());
					pst.setString(6, textAge.getText());
					pst.setString(7, textAddress.getText());
					pst.setString(8, textBirthdate.getText());
					pst.setString(9, textContactNo.getText());	
					pst.setString(10, textSSSNo.getText());
					pst.setString(11, textPhilHealthNo.getText());	
					pst.setString(12, textPagIbigNo.getText());
					pst.execute();
					JOptionPane.showMessageDialog(null, "Data Saved");
					pst.close();
				} catch (Exception ev){
					ev.printStackTrace();
				}
			}
		});
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Employee Database", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_7.setBounds(358, 11, 953, 635);
		panel_1.add(panel_7);
		panel_7.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(null);
		scrollPane_1.setBounds(10, 24, 933, 556);
		panel_7.add(scrollPane_1);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"EmpRefNo", "Firstname", "Middlename", "Lastname", "Gender", "Age", "Address", "Birthdate", "ContactNo","SSSNo","PhilHealthNo","PagIbigNo"
			}
		));
		table.setFont(new Font("Tahoma", Font.BOLD, 14));
		scrollPane_1.setViewportView(table);
		
		JButton btnNewButton_1 = new JButton("View Data");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBounds(10, 591, 110, 33);
		panel_7.add(btnNewButton_1);
		
		JButton btnExit_1 = new JButton("Exit");
		btnExit_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnExit_1.setBounds(250, 591, 60, 33);
		panel_7.add(btnExit_1);
		
		JButton btnExit_1_1 = new JButton("Print Table");
		btnExit_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				MessageFormat header = new MessageFormat("Printing in progress");
				MessageFormat footer = new MessageFormat("Page {0, number, integer}");
				try {
					table.print();
				} catch(java.awt.print.PrinterException ev) {
					System.err.format("No Printer found", ev.getMessage());
				}
			}
		});
		btnExit_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnExit_1_1.setBounds(130, 591, 110, 33);
		panel_7.add(btnExit_1_1);
		btnExit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame = new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit", "Employee Information", 
					JOptionPane.YES_NO_OPTION)== JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "SELECT * FROM EmployeeInformation";
					pst = con.prepareStatement(query);
					rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
		//-----------------------------------------------------------------------------------------
		JPanel panel_2 = new JPanel();
		panel_2.setLocation(-6443, -296);
		tabbedPane.addTab("Payroll Management", null, panel_2, null);
		panel_2.setLayout(null);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setLayout(null);
		panel_3_1.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Employee Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3_1.setBounds(10, 11, 856, 112);
		panel_2.add(panel_3_1);
		//-----------------------------------------------------------------------------------------
		JLabel lblNewLabel_1_2_3 = new JLabel("Date of Pay");
		lblNewLabel_1_2_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_3.setBounds(500, 68, 86, 25);
		panel_3_1.add(lblNewLabel_1_2_3);
		
		textPayDate = new JTextField();
		textPayDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		textPayDate.setColumns(10);
		textPayDate.setBounds(596, 68, 135, 25);
		panel_3_1.add(textPayDate);
		
		JButton btnNewButton_3 = new JButton("Load Details");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					pst = con.prepareStatement("select Firstname, Middlename, Lastname, Gender from EmployeeInformation where EmpRefNo = ?");
					int id = Integer.parseInt(textID2.getText());
					pst.setInt(1, id);
					rs = pst.executeQuery();
					if (rs.next() == false) {
						JOptionPane.showMessageDialog(null, "Record not found");
						textID2.setText("");
						textFirstname2.setText("");
						textMiddlename2.setText("");
						textLastname2.setText("");
//						textGender2.setText("");
					} else {
						textFirstname2.setText(rs.getString("Firstname"));
						textMiddlename2.setText(rs.getString("Middlename"));
						textLastname2.setText(rs.getString("Lastname"));
//						textGender2.setText(rs.getString("Gender"));
					}					
				} catch (Exception el) {
					el.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(230, 22, 126, 25);
		panel_3_1.add(btnNewButton_3);
		
		JLabel lblNewLabel_1_2_3_2_1 = new JLabel("Fullname");
		lblNewLabel_1_2_3_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_3_2_1.setBounds(10, 68, 75, 25);
		panel_3_1.add(lblNewLabel_1_2_3_2_1);
		
		textFirstname2 = new JTextField();
		textFirstname2.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFirstname2.setColumns(10);
		textFirstname2.setBounds(95, 69, 125, 25);
		panel_3_1.add(textFirstname2);
		
		textMiddlename2 = new JTextField();
		textMiddlename2.setFont(new Font("Tahoma", Font.BOLD, 14));
		textMiddlename2.setColumns(10);
		textMiddlename2.setBounds(230, 68, 125, 25);
		panel_3_1.add(textMiddlename2);
		
		textLastname2 = new JTextField();
		textLastname2.setFont(new Font("Tahoma", Font.BOLD, 14));
		textLastname2.setColumns(10);
		textLastname2.setBounds(365, 68, 125, 25);
		panel_3_1.add(textLastname2);
		
		JLabel lblNewLabel_1_2_3_2_4 = new JLabel("EmpRefNo");
		lblNewLabel_1_2_3_2_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_3_2_4.setBounds(10, 21, 75, 25);
		panel_3_1.add(lblNewLabel_1_2_3_2_4);
		
		textID2 = new JTextField();
		textID2.setFont(new Font("Tahoma", Font.BOLD, 14));
		textID2.setColumns(10);
		textID2.setBounds(95, 22, 125, 25);
		panel_3_1.add(textID2);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Gross Salary Calculation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(10, 164, 372, 296);
		panel_2.add(panel_4);
		panel_4.setLayout(null);
		//-----------------------------------------------------------------------------------------
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Basic Salary");
		lblNewLabel_1_1_1_2.setBounds(10, 29, 93, 25);
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_4.add(lblNewLabel_1_1_1_2);
		
		textBasic = new JTextField();
		textBasic.setBounds(152, 29, 85, 25);
		textBasic.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_4.add(textBasic);
		textBasic.setColumns(10);
		//-----------------------------------------------------------------------------------------
		JLabel lblNewLabel_1_2_1 = new JLabel("SIL");
		lblNewLabel_1_2_1.setBounds(10, 68, 26, 25);
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_4.add(lblNewLabel_1_2_1);
		
		textSIL = new JTextField();
		textSIL.setBounds(152, 68, 85, 25);
		textSIL.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_4.add(textSIL);
		textSIL.setColumns(10);
		//-----------------------------------------------------------------------------------------
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Over Time");
		lblNewLabel_1_1_1_1_1.setBounds(10, 107, 77, 25);
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_4.add(lblNewLabel_1_1_1_1_1);
		
		textOverTime = new JTextField();
		textOverTime.setBounds(152, 107, 85, 25);
		textOverTime.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_4.add(textOverTime);
		textOverTime.setColumns(10);
		//-----------------------------------------------------------------------------------------
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Night Differential");
		lblNewLabel_1_1_1_1_1_1.setBounds(10, 146, 132, 25);
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_4.add(lblNewLabel_1_1_1_1_1_1);
		
		textNightDifferential = new JTextField();
		textNightDifferential.setBounds(152, 146, 85, 25);
		textNightDifferential.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_4.add(textNightDifferential);
		textNightDifferential.setColumns(10);
		//-----------------------------------------------------------------------------------------
		JLabel lblNewLabel_1_1_1_3 = new JLabel("13th Month Pay");
		lblNewLabel_1_1_1_3.setBounds(10, 185, 117, 25);
		lblNewLabel_1_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_4.add(lblNewLabel_1_1_1_3);
		
		textMonthPay = new JTextField();
		textMonthPay.setBounds(152, 185, 85, 25);
		panel_4.add(textMonthPay);
		textMonthPay.setFont(new Font("Tahoma", Font.BOLD, 14));
		textMonthPay.setColumns(10);
		
		textBasicRate = new JTextField();
		textBasicRate.setFont(new Font("Tahoma", Font.BOLD, 14));
		textBasicRate.setColumns(10);
		textBasicRate.setBounds(264, 29, 85, 25);
		panel_4.add(textBasicRate);
		
		textSILRate = new JTextField();
		textSILRate.setFont(new Font("Tahoma", Font.BOLD, 14));
		textSILRate.setColumns(10);
		textSILRate.setBounds(264, 68, 85, 25);
		panel_4.add(textSILRate);
		
		textOvertimeRate = new JTextField();
		textOvertimeRate.setFont(new Font("Tahoma", Font.BOLD, 14));
		textOvertimeRate.setColumns(10);
		textOvertimeRate.setBounds(264, 107, 85, 25);
		panel_4.add(textOvertimeRate);
		
		textNightDiffRate = new JTextField();
		textNightDiffRate.setFont(new Font("Tahoma", Font.BOLD, 14));
		textNightDiffRate.setColumns(10);
		textNightDiffRate.setBounds(264, 146, 85, 25);
		panel_4.add(textNightDiffRate);
		
		textMonthRate = new JTextField();
		textMonthRate.setFont(new Font("Tahoma", Font.BOLD, 14));
		textMonthRate.setColumns(10);
		textMonthRate.setBounds(264, 185, 85, 25);
		panel_4.add(textMonthRate);
		
		JButton btnNewButton_2 = new JButton("Load");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				BasicRate = 350;
				SILRate = 4.50;
				OvertimeRate = 5.23;
				NightDiffRate = 10.32;
				MonthRate = 25.44;
	
				String BasicRating = String.format("P %.2f", BasicRate);
				textBasicRate.setText(BasicRating);				
				String SILRating = String.format("P %.2f", SILRate);
				textSILRate.setText(SILRating);
				String OvertimeRating = String.format("P %.2f", OvertimeRate);
				textOvertimeRate.setText(OvertimeRating);
				String NightDiffRating = String.format("P %.2f", NightDiffRate);
				textNightDiffRate.setText(NightDiffRating);
				String MonthRating = String.format("P %.2f", MonthRate);
				textMonthRate.setText(MonthRating);
			}
		});
		btnNewButton_2.setBounds(264, 224, 85, 25);
		panel_4.add(btnNewButton_2);
		//-----------------------------------------------------------------------------------------
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_6.setBounds(10, 471, 372, 48);
		panel_2.add(panel_6);
		//-----------------------------------------------------------------------------------------
		JLabel lblNewLabel_1_2_2 = new JLabel("Gross Salary");
		lblNewLabel_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_2.setBounds(10, 11, 85, 25);
		panel_6.add(lblNewLabel_1_2_2);
		
		textGrossPay = new JTextField();
		textGrossPay.setBounds(202, 11, 100, 25);
		panel_6.add(textGrossPay);
		textGrossPay.setFont(new Font("Tahoma", Font.BOLD, 14));
		textGrossPay.setColumns(10);
		//-----------------------------------------------------------------------------------------
		JPanel panel_6_1 = new JPanel();
		panel_6_1.setLayout(null);
		panel_6_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_6_1.setBounds(392, 471, 232, 48);
		panel_2.add(panel_6_1);
		//-----------------------------------------------------------------------------------------
		JLabel lblNewLabel_1_2_2_1 = new JLabel("Total Deduction");
		lblNewLabel_1_2_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_2_1.setBounds(10, 11, 109, 25);
		panel_6_1.add(lblNewLabel_1_2_2_1);
		
		textTotalDeduction = new JTextField();
		textTotalDeduction.setBounds(132, 12, 90, 25);
		panel_6_1.add(textTotalDeduction);
		textTotalDeduction.setFont(new Font("Tahoma", Font.BOLD, 14));
		textTotalDeduction.setColumns(10);
		
		JPanel panel_4_2 = new JPanel();
		panel_4_2.setLayout(null);
		panel_4_2.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Net Pay Calculation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4_2.setBounds(634, 164, 232, 296);
		panel_2.add(panel_4_2);
		//-----------------------------------------------------------------------------------------
		JLabel lblNewLabel_1_1_1_2_2 = new JLabel("Gross Salary");
		lblNewLabel_1_1_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_2_2.setBounds(10, 29, 96, 25);
		panel_4_2.add(lblNewLabel_1_1_1_2_2);
		
		textGrossSalary = new JTextField();
		textGrossSalary.setFont(new Font("Tahoma", Font.BOLD, 14));
		textGrossSalary.setColumns(10);
		textGrossSalary.setBounds(127, 29, 95, 25);
		panel_4_2.add(textGrossSalary);
		//-----------------------------------------------------------------------------------------
		JLabel lblNewLabel_1_2_1_2 = new JLabel("Total Deduction");
		lblNewLabel_1_2_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_2.setBounds(10, 68, 117, 25);
		panel_4_2.add(lblNewLabel_1_2_1_2);
		
		textTotalDeductions = new JTextField();
		textTotalDeductions.setFont(new Font("Tahoma", Font.BOLD, 14));
		textTotalDeductions.setColumns(10);
		textTotalDeductions.setBounds(127, 68, 95, 25);
		panel_4_2.add(textTotalDeductions);
		//-----------------------------------------------------------------------------------------
		JPanel panel_6_2 = new JPanel();
		panel_6_2.setLayout(null);
		panel_6_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_6_2.setBounds(634, 471, 232, 48);
		panel_2.add(panel_6_2);
		//-----------------------------------------------------------------------------------------
		JLabel lblNewLabel_1_2_2_2 = new JLabel("Net Pay");
		lblNewLabel_1_2_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_2_2.setBounds(10, 11, 59, 25);
		panel_6_2.add(lblNewLabel_1_2_2_2);
		
		textNetSalary = new JTextField();
		textNetSalary.setBounds(127, 13, 95, 25);
		panel_6_2.add(textNetSalary);
		textNetSalary.setFont(new Font("Tahoma", Font.BOLD, 14));
		textNetSalary.setColumns(10);
		//-----------------------------------------------------------------------------------------
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setLayout(null);
		panel_1_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1_2.setBounds(876, 11, 435, 48);
		panel_2.add(panel_1_2);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("PAYROLL SLIP");
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1_3_1.setBounds(113, 11, 220, 26);
		panel_1_2.add(lblNewLabel_1_3_1);
		//-----------------------------------------------------------------------------------------
		JPanel rtfPaySlip = new JPanel();
		rtfPaySlip.setToolTipText("");
		rtfPaySlip.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		rtfPaySlip.setBounds(876, 70, 435, 449);
		panel_2.add(rtfPaySlip);
		rtfPaySlip.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 415, 427);
		rtfPaySlip.add(scrollPane);
		
		JTextArea textPaySlip = new JTextArea();
		textPaySlip.setFont(new Font("Monospaced", Font.PLAIN, 15));
		scrollPane.setViewportView(textPaySlip);
		//-----------------------------------------------------------------------------------------
		JButton btnTotal = new JButton("Calculate");
		btnTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//-----------------------------------------------------------------------------------------
				Basic = Double.parseDouble(textBasic.getText());
				SIL = Double.parseDouble(textSIL.getText());
				OverTime = Double.parseDouble(textOverTime.getText());
				NightDifferential = Double.parseDouble(textNightDifferential.getText());
				MonthPay = Double.parseDouble(textMonthPay.getText());
							
				TotalBasic = Basic * BasicRate;
				TotalSIL = SIL * SILRate;
				TotalOverTime = OverTime * OvertimeRate;
				TotalNightDifferential = NightDifferential * NightDiffRate;
				TotalMonthPay = MonthPay * MonthRate;
				
				Gross = TotalBasic + TotalSIL + TotalOverTime + TotalNightDifferential + TotalMonthPay;				
				String GrossPay = String.format("P %.2f", Gross);
				textGrossPay.setText(GrossPay);
				//-----------------------------------------------------------------------------------------				
				SSS = Double.parseDouble(textSSS.getText());
				PhilHealth = Double.parseDouble(textPhilHealth.getText());
				PagIbig = Double.parseDouble(textPagIbig.getText());
				CashBan = Double.parseDouble(textCashBan.getText());
				Others = Double.parseDouble(textOthers.getText());
				
				Deduction = SSS + PhilHealth + PagIbig + CashBan + Others;				
				String TotalDeduction = String.format("P %.2f", Deduction);
				textTotalDeduction.setText(TotalDeduction);
				//-----------------------------------------------------------------------------------------
				String GrossSalary = String.format("P %.2f", Gross);
				textGrossSalary.setText(GrossSalary);				
				String TotalDeductions = String.format("P %.2f", Deduction);
				textTotalDeductions.setText(TotalDeductions);
				NetSalary = Gross - Deduction;
				String NetPay = String.format("P %.2f", NetSalary);
				textNetSalary.setText(NetPay);
			}
		});
		btnTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTotal.setBounds(943, 530, 142, 28);
		panel_2.add(btnTotal);
		
		JButton btnPaySlip = new JButton("Pay Slip");
		btnPaySlip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPaySlip.append("\t       I S E N G A R D\n");
				textPaySlip.append("\t       SECURITY AGENCY\n");
				textPaySlip.append("\t        P A Y S L I P\n\n");
				textPaySlip.append("============================================\n");
				textPaySlip.append(" Emp.Ref.No: " + textID2.getText() + "\n");
				textPaySlip.append(" Name: " + textFirstname2.getText() + " " + textMiddlename2.getText() + " " + textLastname2.getText() +"\n");
				textPaySlip.append(" Date of Pay: " + textPayDate.getText() + "\n");
//				textPaySlip.append(" Pay of the Period: " + textPayOfThePeriod.getText() + "\n");
//				textPaySlip.append(" Gender: " + textGender2.getText() + "\n");
				textPaySlip.append("============================================\n");
				textPaySlip.append("\t\t  G R O S S\n");
				textPaySlip.append("============================================\n");
				textPaySlip.append(" Basic Salary: " + TotalBasic + "\n");
				textPaySlip.append(" SIL : " + TotalSIL + "\n");
				textPaySlip.append(" OverTime: " + TotalOverTime + "\n");
				textPaySlip.append(" Night Differential: " + TotalNightDifferential + "\n");
				textPaySlip.append(" 13th Month Pay: " + TotalMonthPay + "\n");
				textPaySlip.append("\t\n");
				textPaySlip.append(" Gross Salary: " + "P " + Gross + "\n");
				textPaySlip.append("============================================\n");
				textPaySlip.append("\t     D E D U C T I O N S\n");
				textPaySlip.append("============================================\n");
				textPaySlip.append(" SSS: " + textSSS.getText() + "\n");
				textPaySlip.append(" PhilHealth: " + textPhilHealth.getText() + "\n");
				textPaySlip.append(" PagIbig: " + textPagIbig.getText() + "\n");
				textPaySlip.append(" Cash Ban: " + textCashBan.getText() + "\n");
				textPaySlip.append(" Others: " + textOthers.getText() + "\n");
				textPaySlip.append("\t\n");
				textPaySlip.append(" Total Deduction: " + textTotalDeduction.getText() + "\n");
				textPaySlip.append("============================================\n");
				textPaySlip.append(" Net Amount Received: " + "P " + NetSalary + "\n");
				textPaySlip.append("============================================\n");
				textPaySlip.append("	I acknowledge to have received from\n");
				textPaySlip.append(" ISENGARD SECURITY AGENCY the amount stated\n");
				textPaySlip.append(" above as full compensation for the service\n");
				textPaySlip.append(" rendered during the period and I have no\n");
				textPaySlip.append(" complaints whatsoever.\n");
				textPaySlip.append("\n");
				textPaySlip.append("      ____________         _____________\n");
				textPaySlip.append("          Date		     Signature");
			}
		});
		btnPaySlip.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnPaySlip.setBounds(1095, 530, 142, 28);
		panel_2.add(btnPaySlip);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textID2.setText(null);
				textFirstname2.setText(null);
				textMiddlename2.setText(null);
				textLastname2.setText(null);
//				textGender2.setText(null);
				textBasic.setText(null);
				textSIL.setText(null);
				textOverTime.setText(null);
				textNightDifferential.setText(null);
				textMonthPay.setText(null);
				textGrossPay.setText(null);
				textSSS.setText(null);
				textPhilHealth.setText(null);
				textPagIbig.setText(null);
				textCashBan.setText(null);
				textOthers.setText(null);
				textTotalDeduction.setText(null);
				textPaySlip.setText(null);
				textGrossSalary.setText(null);
				textTotalDeductions.setText(null);
				textNetSalary.setText(null);
				
				textBasicRate.setText(null);
				textSILRate.setText(null);
				textOvertimeRate.setText(null);
				textNightDiffRate.setText(null);
				textMonthRate.setText(null);
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnReset.setBounds(1095, 569, 142, 28);
		panel_2.add(btnReset);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				frame = new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit", "Payroll System",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION){
					System.exit(0);
				}
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnExit.setBounds(1019, 608, 142, 28);
		panel_2.add(btnExit);
		//-----------------------------------------------------------------------------------------
		JPanel panel_4_1 = new JPanel();
		panel_4_1.setBounds(392, 164, 232, 296);
		panel_2.add(panel_4_1);
		panel_4_1.setLayout(null);
		panel_4_1.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Deduction", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		//-----------------------------------------------------------------------------------------
		JLabel lblNewLabel_1_1_1_2_1 = new JLabel("SSS");
		lblNewLabel_1_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_2_1.setBounds(10, 29, 30, 25);
		panel_4_1.add(lblNewLabel_1_1_1_2_1);
		
		textSSS = new JTextField();
		textSSS.setFont(new Font("Tahoma", Font.BOLD, 14));
		textSSS.setColumns(10);
		textSSS.setBounds(132, 29, 90, 25);
		panel_4_1.add(textSSS);
		//-----------------------------------------------------------------------------------------
		JLabel lblNewLabel_1_2_1_1 = new JLabel("PhilHealth");
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_1.setBounds(10, 68, 78, 25);
		panel_4_1.add(lblNewLabel_1_2_1_1);
		
		textPhilHealth = new JTextField();
		textPhilHealth.setFont(new Font("Tahoma", Font.BOLD, 14));
		textPhilHealth.setColumns(10);
		textPhilHealth.setBounds(132, 68, 90, 28);
		panel_4_1.add(textPhilHealth);
		//-----------------------------------------------------------------------------------------
		JLabel lblNewLabel_1_1_1_1_1_2 = new JLabel("Pag-IBIG");
		lblNewLabel_1_1_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_1_1_2.setBounds(10, 107, 69, 25);
		panel_4_1.add(lblNewLabel_1_1_1_1_1_2);
		
		textPagIbig = new JTextField();
		textPagIbig.setFont(new Font("Tahoma", Font.BOLD, 14));
		textPagIbig.setColumns(10);
		textPagIbig.setBounds(132, 107, 90, 25);
		panel_4_1.add(textPagIbig);
		//-----------------------------------------------------------------------------------------
		JLabel lblNewLabel_1_1_1_1_1_2_1 = new JLabel("Cash Ban");
		lblNewLabel_1_1_1_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_1_1_2_1.setBounds(10, 146, 68, 25);
		panel_4_1.add(lblNewLabel_1_1_1_1_1_2_1);
		
		textCashBan = new JTextField();
		textCashBan.setFont(new Font("Tahoma", Font.BOLD, 14));
		textCashBan.setColumns(10);
		textCashBan.setBounds(132, 146, 90, 25);
		panel_4_1.add(textCashBan);
		//-----------------------------------------------------------------------------------------
		JLabel lblNewLabel_1_1_1_1_1_2_2 = new JLabel("Others");
		lblNewLabel_1_1_1_1_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_1_1_2_2.setBounds(10, 185, 51, 25);
		panel_4_1.add(lblNewLabel_1_1_1_1_1_2_2);
		
		textOthers = new JTextField();
		textOthers.setBounds(132, 185, 90, 25);
		panel_4_1.add(textOthers);
		textOthers.setFont(new Font("Tahoma", Font.BOLD, 14));
		textOthers.setColumns(10);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MessageFormat header = new MessageFormat("Printing in progress");
				MessageFormat footer = new MessageFormat("Page {0, number, integer}");				
				try {
					textPaySlip.print();
				} catch(java.awt.print.PrinterException ev) {
					System.err.format("No Printer found", ev.getMessage());
				}
			}
		});
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnPrint.setBounds(943, 569, 142, 28);
		panel_2.add(btnPrint);
	}
}
